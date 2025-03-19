package org.example.listingservice.services.caches;

import org.example.listingservice.models.Building;
import org.example.listingservice.models.User;
import org.example.listingservice.responses.building.BuildingListResponse;
import org.example.listingservice.responses.user.UserListResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICacheService
{
    User getUserFromCache(Long id);
    Building getBuildingFromCache(Long id);
    Boolean deleteUserFromCache(Long id);
    Boolean deleteBuildingFromCache(Long id);
    List<UserListResponse> getAllUsersFromCache(String value);
    List<BuildingListResponse> getAllBuildingsFromCache(String value);
    Boolean deleteUserByIdFromCache(Long id);
    Boolean deleteBuildingByIdFromCache(Long id);
}
