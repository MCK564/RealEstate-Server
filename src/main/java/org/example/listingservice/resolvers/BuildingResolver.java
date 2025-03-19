package org.example.listingservice.resolvers;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.example.listingservice.exceptions.DataNotFoundException;
import org.example.listingservice.responses.building.BuildingEditListResponse;
import org.example.listingservice.responses.building.BuildingResponse;
import org.example.listingservice.services.buildings.BuildingService;
import org.springframework.graphql.data.method.annotation.Argument;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BuildingResolver {
    private final BuildingService buildingService;

    public BuildingResolver(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @QueryMapping
    public BuildingEditListResponse buildings(){
        Map<String,Object> map = new HashMap<>();
        List<String> types = new ArrayList<>();
        return  buildingService.getBuildingEdits(map,0,20, types);
    }

    @QueryMapping
    public BuildingResponse building(@Argument Long id) throws DataNotFoundException {
        return buildingService.getById(id);
    }
}
