package org.example.listingservice.converter;

import org.example.listingservice.utils.MapUtils;
import lombok.RequiredArgsConstructor;
import org.example.listingservice.builders.BuildingSearchBuilder;
import org.example.listingservice.dtos.BuildingDTO;
import org.example.listingservice.dtos.CommunicationDTO;
import org.example.listingservice.dtos.RegisterDTO;
import org.example.listingservice.dtos.UserDTO;
import org.example.listingservice.models.Building;
import org.example.listingservice.models.Communication;
import org.example.listingservice.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class Converter {
    private final ModelMapper modelMapper;
    public Communication fromCommunicationDTO (CommunicationDTO dto){
        return modelMapper.map(dto, Communication.class);
    }

    public User fromUserDTO(UserDTO dto){
        return modelMapper.map(dto, User.class);
    }

    public BuildingSearchBuilder toBuildingSearchBuilder(Map<String,Object> conditions, List<String> type){
        return  BuildingSearchBuilder.builder()
                .name(MapUtils.getObject(conditions,"name",String.class))
                .district(MapUtils.getObject(conditions,"district",String.class))
                .street(MapUtils.getObject(conditions,"street",String.class))
                .ward(MapUtils.getObject(conditions,"ward",String.class))
                .numberOfBasement(MapUtils.getObject(conditions,"numberOfBasement",Integer.class))
                .floorArea(MapUtils.getObject(conditions,"floorArea",Double.class))
                .rentPriceFrom(MapUtils.getObject(conditions,"rentPriceFrom",Integer.class))
                .rentPriceTo(MapUtils.getObject(conditions,"rentPriceTo",Integer.class))
                .rentAreaFrom(MapUtils.getObject(conditions,"rentAreaFrom",Integer.class))
                .rentAreaTo(MapUtils.getObject(conditions,"rentAreaTo",Integer.class))
                .ownerName(MapUtils.getObject(conditions,"owner",String.class))
                .type(type!= null ?type:null)
                .managerName(MapUtils.getObject(conditions,"managerName",String.class))
                .managerPhoneNumber(MapUtils.getObject(conditions,"managerPhoneNumber",String.class)).build();
    }

    public Building toBuildingFromBuildingDTO(BuildingDTO buildingDTO){

        return modelMapper.map(buildingDTO,Building.class);
    }

    public User fromRegisterDTO(RegisterDTO dto){
        return modelMapper.map(dto,User.class);
    }



}
