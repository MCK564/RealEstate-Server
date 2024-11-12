package org.example.listingservice.responses.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.listingservice.responses.user.UserResponse;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ListConnected {
    private List<Connected> connecteds = new ArrayList<>();


}
