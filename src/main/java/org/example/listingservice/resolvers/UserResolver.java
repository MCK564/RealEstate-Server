package org.example.listingservice.resolvers;


import org.example.listingservice.exceptions.DataNotFoundException;
import org.example.listingservice.responses.user.UserListResponse;
import org.example.listingservice.responses.user.UserResponse;
import org.example.listingservice.services.user.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class UserResolver  {
    private final UserService userService;


    public UserResolver(UserService userService) {
        this.userService = userService;
    }

    @QueryMapping
    public UserListResponse users(){
        return userService.getAllByKeyword("",0,10);
    }

    @QueryMapping
    public UserResponse user(@Argument Long id) throws DataNotFoundException {
        return userService.getById(id);
    }



}
