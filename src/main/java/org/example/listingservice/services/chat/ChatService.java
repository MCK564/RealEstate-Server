package org.example.listingservice.services.chat;

import org.example.listingservice.dtos.ChatMessageDTO;
import org.example.listingservice.exceptions.DataNotFoundException;
import org.example.listingservice.responses.chat.ConversationResponse;
import org.example.listingservice.responses.chat.ListConnected;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChatService {
    Object saveMessage(ChatMessageDTO dto) throws DataNotFoundException;
    ConversationResponse getChatHistory(Long senderId, Long receiverId) throws DataNotFoundException;
    List<ConversationResponse> getAllCommunication(Long senderId);
    ConversationResponse startConversation(ChatMessageDTO dto);
    ListConnected getAllConnectedByUserId(Long userId);
}
