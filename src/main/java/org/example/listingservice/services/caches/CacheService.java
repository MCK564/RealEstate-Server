package org.example.listingservice.services.caches;

import com.github.benmanes.caffeine.cache.Cache;
import org.example.listingservice.models.Building;
import org.example.listingservice.models.User;
import org.example.listingservice.responses.building.BuildingListResponse;
import org.example.listingservice.responses.user.UserListResponse;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CacheService implements ICacheService {
    private final CacheManager cacheManager;
    private static final String userCacheString = "users";
    private static final String buildingCacheString = "buildings";
    private static final String userListsCacheString = "userLists";
    private static final String buildingListsCacheString = "buildingLists";

    public CacheService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    private Cache<Object, Object> getCache(String cacheName){
        org.springframework.cache.Cache cache = cacheManager.getCache(cacheName);
        if(cache instanceof CaffeineCache caffeineCache){
            return caffeineCache.getNativeCache();
        }
        throw new RuntimeException("Cache not found");
    }


    @Override
    public User getUserFromCache(Long id) {
        Cache<Object,Object> userCache = getCache(userCacheString);
        return (User) userCache.getIfPresent(id);
    }

    @Override
    public Building getBuildingFromCache(Long id) {
        Cache<Object, Object> buildingCache = getCache(buildingCacheString);
        return (Building) buildingCache.getIfPresent(id);
    }

    @Override
    public Boolean deleteUserFromCache(Long id) {
        Cache<Object,Object> userCache = getCache(userCacheString);
        userCache.invalidate(id);
        return true;
    }

    @Override
    public Boolean deleteBuildingFromCache(Long id) {
        Cache<Object, Object> buildingCache = getCache(buildingCacheString);
        buildingCache.invalidate(id);
        return true;
    }

    @Override
    public List<UserListResponse> getAllUsersFromCache(String value) {
        Cache<Object, Object> userCache = getCache(userCacheString);
        Map<Object, Object> cacheMap = userCache.asMap();

        return cacheMap.values().stream()
                .filter(obj -> obj instanceof UserListResponse)
                .map(obj -> (UserListResponse) obj)
                .collect(Collectors.toList());
    }

    @Override
    public List<BuildingListResponse> getAllBuildingsFromCache(String value) {
        Cache<Object,Object> buildingCache = getCache(buildingCacheString);
        Map<Object, Object> cacheMap = buildingCache.asMap();

        return cacheMap.values().stream()
                .filter(obj -> obj instanceof BuildingListResponse)
                .map(obj -> (BuildingListResponse) obj)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean deleteUserByIdFromCache(Long id) {
       Cache<Object, Object> userListsCache = getCache(userListsCacheString);
       Map<Object, Object> cacheMap = userListsCache.asMap();

       cacheMap = cacheMap.entrySet().stream()
               .filter(entry -> entry.getValue() instanceof UserListResponse)
               .filter(entry -> {
                   UserListResponse userList = (UserListResponse) entry.getValue();
                   return userList.getUserResponses().stream()
                           .anyMatch(userResponse -> userResponse.getId().equals(id));
                       }

               ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return true;

    }

    @Override
    public Boolean deleteBuildingByIdFromCache(Long id) {
        Cache<Object, Object> buildingListCache = getCache(buildingListsCacheString);
        Map<Object, Object> cacheMap = buildingListCache.asMap();
        cacheMap = cacheMap.entrySet().stream()
                .filter(entry -> entry.getValue() instanceof BuildingListResponse)
                .filter(entry ->{
                    BuildingListResponse buildingList = (BuildingListResponse) entry.getValue();
                    return buildingList.getBuildings().stream()
                            .anyMatch(buildingResponse -> buildingResponse.getId().equals(id));
                }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return false;
    }
}
