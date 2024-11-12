package org.example.listingservice.services.buildings;

import org.example.listingservice.builders.BuildingSearchBuilder;
import org.example.listingservice.converter.Converter;
import org.example.listingservice.dtos.BuildingDTO;
import org.example.listingservice.repositories.BuildingRepository;
import org.example.listingservice.repositories.UserRepository;
import org.example.listingservice.responses.building.BuildingEditListResponse;
import org.example.listingservice.responses.building.BuildingEditResponse;
import org.example.listingservice.responses.building.BuildingListResponse;
import lombok.RequiredArgsConstructor;
import org.example.listingservice.constant.MessageKeys;
import org.example.listingservice.exceptions.DataNotFoundException;
import org.example.listingservice.models.Building;
import org.example.listingservice.models.RentArea;
import org.example.listingservice.models.User;
import org.example.listingservice.repositories.RentAreaRepository;
import org.example.listingservice.repositories.Specifications;
import org.example.listingservice.responses.building.BuildingResponse;
import org.hibernate.annotations.Cache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BuildingService  implements  IbuildingService{

    private final BuildingRepository buildingRepository;
    private final Converter converter;
    private final RentAreaRepository rentAreaRepository;
    private final UserRepository userRepository;


    @Override
    @Cacheable(value = "buildingsByConditions", key="#conditions + '_'+ #page + '_' + #limit +'_' + #type")
    public BuildingListResponse findByCondition(Map<String, Object> conditions, int page, int limit, List<String> type) {
        int totalPages = 0;
        PageRequest pageRequest = PageRequest.of(page,limit);
        BuildingSearchBuilder builder = converter.toBuildingSearchBuilder(conditions,type);
        Specification<Building> spec = Specifications.searchProductByConditions(builder,type);
        Page<Building> pages = buildingRepository.findAll(spec,pageRequest);
        totalPages = pages.getTotalPages();
        List<Building> buildings = pages.getContent();
        List<BuildingResponse> buildingResponses = buildings
                .stream().map(BuildingResponse::fromBuilding).toList();
        return BuildingListResponse.builder()
                .buildings(buildingResponses)
                .totalPages(totalPages)
                .build();
    }

    @Override
    @Transactional
    @Caching(evict = {
            @CacheEvict(value="buildingsByOwner", key="#id"),
            @CacheEvict(value="buildings",key="#id"),
            @CacheEvict(value="buildingsByConditions", allEntries = true)
    })
    public void deleteById(Long id) {
        if(buildingRepository.existsById(id)){
            buildingRepository.deleteById(id);
        }
    }

    @Override
    @CachePut(value="buildings", key="#dto.id")
    public ResponseEntity<?> createOrUpdate(BuildingDTO dto) throws DataNotFoundException {

        Building building = converter.toBuildingFromBuildingDTO(dto);
        int isUpdate = 0;
        if(dto.getId()!=null){
            isUpdate = 1;
            building =  buildingRepository.findById(dto.getId()).orElseThrow(()
                    ->new DataNotFoundException(MessageKeys.DATA_NOT_FOUND));
            rentAreaRepository.deleteAllByBuilding_Id(building.getId());
        }

        User owner = userRepository.findById(dto.getOwnerId()).orElseThrow(()
                ->new DataNotFoundException(MessageKeys.DATA_NOT_FOUND));
        if(isUpdate==1){
            if(owner.getRemainPost()<=0){
                return ResponseEntity.badRequest().body(MessageKeys.OUT_OF_POSTS);
            }
            owner.setRemainPost(owner.getRemainPost()-1);

        }
        building.setUser(owner);
        building.setDistrict(dto.getDistrict());
        building.setRentPriceDescription(dto.getRentPriceDescription());
        Building savedBuilding = buildingRepository.saveAndFlush(building);
        List<RentArea> rentAreas =new ArrayList<>();
        for(Integer value:dto.getRentAreas()){
            RentArea newRentArea = RentArea.builder()
                    .building(savedBuilding)
                    .value(Long.valueOf(value.toString()))
                    .build();
            RentArea savedRentArea = rentAreaRepository.saveAndFlush(newRentArea);
            rentAreas.add(savedRentArea);
        }
        userRepository.save(owner);
        savedBuilding.setRentAreas(rentAreas);
        Building savedBuilding2 = buildingRepository.saveAndFlush(savedBuilding);
        return ResponseEntity.ok().body(BuildingResponse.fromBuilding(savedBuilding2));
    }

    @Override
    @Cacheable(value="buildingsByOwner", key="#id")
    public BuildingListResponse findByOwnerId(Long id) {
        List<Building> buildings = buildingRepository.findByUser_Id(id);
         List<BuildingResponse> buildingResponses = buildings
                .stream().map(BuildingResponse::fromBuilding).toList();
        return BuildingListResponse.builder().buildings(buildingResponses).build();
    }


    @Override
    @Cacheable(value="relativeBuildingsById", key="#id")
    public BuildingListResponse getRelativeBuildingsByBuildingId(Long id) throws DataNotFoundException {
        Building existingBuilding = buildingRepository.findById(id)
                .orElseThrow(()-> new DataNotFoundException("Can not find building with id = "+id));

        int totalPages = 0;
        Sort.Direction sort = Sort.Direction.ASC;
//        PageRequest pageRequest = PageRequest.of(0,30,sort);
//        Specification<Building> spec = Specifications.getSomeRelateWithBuilding(existingBuilding);
//        Page<Building> pages = buildingRepository.findAll(spec,pageRequest);
//        totalPages = pages.getTotalPages();
//        List<Building> buildings = pages.getContent();
        List<Building> buildings = buildingRepository.findAllByDistrictOrWardLikeOrStreetLikeOrderByCreatedDateDesc(existingBuilding.getDistrict(),existingBuilding.getWard(),existingBuilding.getStreet());
        List<BuildingResponse> buildingResponses = buildings
                .stream().map(BuildingResponse::fromBuilding).toList();
        return BuildingListResponse.builder()
                .buildings(buildingResponses)
                .totalPages(totalPages)
                .build();

    }

    @Override
    public BuildingListResponse getSomePopularBuilding() {
        List<Building> buildings = buildingRepository.findTop30BuildingsWithMostLikes();
        List<BuildingResponse> buildingResponses = buildings
                .stream().map(BuildingResponse::fromBuilding).toList();
        return BuildingListResponse.builder().buildings(buildingResponses).build();
    }

    @Override
    public BuildingResponse getById(Long id) throws DataNotFoundException {
        Building existingBuilding = buildingRepository.findById(id)
                .orElseThrow(()-> new DataNotFoundException("Can not find building with id = "+id));
        return BuildingResponse.fromBuilding(existingBuilding);
    }

    @Override
    public BuildingEditResponse getBuildingEditById(Long id) throws DataNotFoundException {
        Building existingBuilding = buildingRepository.findById(id)
                .orElseThrow(()-> new DataNotFoundException("Can not find building with id = "+id));
        return BuildingEditResponse.fromBuilding(existingBuilding);
    }

    @Override
    public BuildingEditListResponse getBuildingEdits(Map<String, Object> conditions, int page, int limit, List<String> type) {
        int totalPages = 0;
        PageRequest pageRequest = PageRequest.of(page,limit);
        BuildingSearchBuilder builder = converter.toBuildingSearchBuilder(conditions,type);
        Specification<Building> spec = Specifications.searchProductByConditions(builder,type);
        Page<Building> pages = buildingRepository.findAll(spec,pageRequest);
        totalPages = pages.getTotalPages();
        List<Building> buildings = pages.getContent();
        List<BuildingEditResponse> buildingResponses = buildings
                .stream().map(BuildingEditResponse::fromBuilding).toList();
        return BuildingEditListResponse.builder()
                .totalPages(totalPages)
                .buildingEditResponses(buildingResponses)
                .build();
    }

    @Override
    public BuildingEditListResponse getBuildingEditsByUserId(Long id) {
        List<Building> buildings = buildingRepository.findTop30BuildingsWithMostLikes();
        List<BuildingEditResponse> buildingResponses = buildings
                .stream().map(BuildingEditResponse::fromBuilding).toList();
        return BuildingEditListResponse.builder()
                .buildingEditResponses(buildingResponses)
                .build();
    }


}
