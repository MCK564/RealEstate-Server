package org.example.listingservice.responses.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.example.listingservice.models.ChatMessage;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessageResponse {
    private Long id;


    @JsonProperty("sender_id")
    private Long senderId;


    @JsonProperty("receiver_id")
    private Long receiverId;

    private String message;
    private Boolean status;
    private LocalDateTime timestamp;

    public static ChatMessageResponse fromChatMessage(ChatMessage message){
        return ChatMessageResponse.builder()
                .id(message.getId())
                .message(message.getMessage())
                .status(message.getStatus())
                .senderId(message.getSender().getId())
                .receiverId(message.getConversation().getReceiver().getId())
                .timestamp(message.getTimestamp())
                .build();
    }
}
