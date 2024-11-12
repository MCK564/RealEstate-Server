package org.example.listingservice.responses.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.listingservice.models.Conversation;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConversationResponse
{
    private Long id;
    List<ChatMessageResponse> messages = new ArrayList<>();

    public static ConversationResponse fromConversation(Conversation conversation){
        return ConversationResponse.builder()
                .id(conversation.getId())
                .messages(conversation.getMessages().stream().map(ChatMessageResponse::fromChatMessage).toList())
                .build();
    }
}
