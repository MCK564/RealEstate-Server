package org.example.listingservice.responses.building;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuildingEditListResponse {
    private int totalPages;
    private List<BuildingEditResponse> buildingEditResponses = new ArrayList<>();
}
