package org.example.listingservice.services.like;

import org.example.listingservice.responses.building.BuildingListResponse;
import lombok.RequiredArgsConstructor;
import org.example.listingservice.constant.MessageKeys;
import org.example.listingservice.dtos.LikeDTO;
import org.example.listingservice.exceptions.DataNotFoundException;
import org.example.listingservice.models.Building;
import org.example.listingservice.models.Love;
import org.example.listingservice.models.User;
import org.example.listingservice.repositories.BuildingRepository;
import org.example.listingservice.repositories.LoveRepository;
import org.example.listingservice.repositories.UserRepository;
import org.example.listingservice.responses.building.BuildingResponse;
import org.example.listingservice.responses.user.UserListResponse;
import org.example.listingservice.responses.user.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class LikeService implements  ILikeService{
    private final LoveRepository loveRepository;
    private final UserRepository userRepository;
    private final BuildingRepository buildingRepository;

    @Override
    public UserListResponse getAllByBuildingId(Long id) {
        List<Love> likes = loveRepository.findAllByBuilding_Id(id);
        List<UserResponse> userResponses= likes.stream().map(Love::getUser).toList()
                .stream().map(UserResponse::fromUser).toList();
        return UserListResponse.builder()
                .userResponses(userResponses)
                .build();
    }

    @Override
    public String like(LikeDTO dto) throws DataNotFoundException {
        Long buildingId = dto.getBuildingId();
        Long userId = dto.getUserId();
        if(dto.getLike().equals(true)){
            if(loveRepository.existsByUser_IdAndBuilding_Id(userId,buildingId)){
                return MessageKeys.LIKE_UNSUCCESSFULLY;
            }
            Building existingBuilding = buildingRepository.findById(buildingId)
                    .orElseThrow(()-> new DataNotFoundException("Can not find building with id= "+buildingId));
            User existingUser = userRepository.findById(userId)
                    .orElseThrow(()-> new DataNotFoundException("Can not find user with id= "+userId));
            Love newLike = Love.builder()
                    .building(existingBuilding)
                    .user(existingUser)
                    .build();
            Love savedLike = loveRepository.saveAndFlush(newLike);
            if(savedLike!=null) return MessageKeys.LIKE_SUCCESSFULLy;
            return MessageKeys.LIKE_UNSUCCESSFULLY;
        }else{
            Love existingLove = loveRepository.findByUser_IdAndBuilding_Id(userId,buildingId);
            loveRepository.delete(existingLove);
            return MessageKeys.DISLIKE_SUCCESSFULLy;
        }

    }

    @Override
    public String dislike(Long id) throws DataNotFoundException {
        Love existingLike = loveRepository.findById(id)
                .orElseThrow(()-> new DataNotFoundException("Can not find like with id = "+id));
        loveRepository.delete(existingLike);
        return MessageKeys.DISLIKE_SUCCESSFULLy;
    }

    @Override
    public BuildingListResponse getAllByUserId(Long id) {
        List<Love> likes = loveRepository.findAllByUser_Id(id);
        List<BuildingResponse> buildingResponses= likes.stream().map(Love::getBuilding).toList()
                .stream().map(BuildingResponse::fromBuilding).toList();
        return BuildingListResponse.builder()
                .buildings(buildingResponses)
                .build();

    }
}
