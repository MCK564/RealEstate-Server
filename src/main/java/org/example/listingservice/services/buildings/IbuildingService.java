package org.example.listingservice.services.buildings;

import org.example.listingservice.dtos.BuildingDTO;
import org.example.listingservice.responses.building.BuildingEditListResponse;
import org.example.listingservice.responses.building.BuildingEditResponse;
import org.example.listingservice.responses.building.BuildingListResponse;
import org.example.listingservice.exceptions.DataNotFoundException;
import org.example.listingservice.responses.building.BuildingResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IbuildingService {
    BuildingListResponse findByCondition(Map<String,Object> conditions, int page, int limit, List<String> type);
    void deleteById(Long id);

    ResponseEntity<?> createOrUpdate(BuildingDTO dto) throws DataNotFoundException;

    BuildingListResponse findByOwnerId(Long id);
    BuildingListResponse getRelativeBuildingsByBuildingId(Long id) throws DataNotFoundException;

    BuildingListResponse getSomePopularBuilding();

    BuildingResponse getById(Long id) throws DataNotFoundException;
    BuildingEditResponse getBuildingEditById(Long id) throws DataNotFoundException;
    BuildingEditListResponse getBuildingEdits(Map<String,Object> conditions, int page, int limit, List<String> type);
    BuildingEditListResponse getBuildingEditsByUserId (Long id);

}
