package org.example.listingservice.responses.chat;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.listingservice.models.ChatMessage;
import org.example.listingservice.models.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Connected {
    private Long id;

    @JsonProperty("last_message")
    private String lastMessage;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("avatar_url")
    private String avatarUrl;

    @JsonProperty("sender_name")
    private String senderName;

    public static Connected toConnected(ChatMessage message, Long userId){
        User connected = message.getSender().getId().equals(userId)
                ? message.getReceiver() : message.getSender();

        return Connected.builder()
                .avatarUrl(connected.getAvatar())
                .id(connected.getId())
                .senderName(message.getSender().getFullName())
                .lastMessage(message.getMessage())
                .fullName(connected.getFullName())
                .build();
    }
}
