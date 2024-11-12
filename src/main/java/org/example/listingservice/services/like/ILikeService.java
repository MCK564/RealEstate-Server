package org.example.listingservice.services.like;

import org.example.listingservice.dtos.LikeDTO;
import org.example.listingservice.responses.building.BuildingListResponse;
import org.example.listingservice.exceptions.DataNotFoundException;
import org.example.listingservice.responses.user.UserListResponse;
import org.springframework.stereotype.Service;

@Service
public interface ILikeService {
    UserListResponse getAllByBuildingId(Long id);
    String like(LikeDTO dto) throws DataNotFoundException;
    String dislike(Long id) throws DataNotFoundException;
    BuildingListResponse getAllByUserId(Long id);

}
