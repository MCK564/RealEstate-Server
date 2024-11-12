package org.example.listingservice.responses.like;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class LikeListResponse {
    private List<LikeResponse> likes = new ArrayList<>();
    int totalLikes;
}
