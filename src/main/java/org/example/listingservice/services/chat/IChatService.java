package org.example.listingservice.services.chat;

import jakarta.transaction.Transactional;
import org.example.listingservice.dtos.ChatMessageDTO;
import org.example.listingservice.repositories.ConversationRepository;
import org.example.listingservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.example.listingservice.exceptions.DataNotFoundException;
import org.example.listingservice.models.ChatMessage;
import org.example.listingservice.models.Conversation;
import org.example.listingservice.models.User;
import org.example.listingservice.repositories.ChatMessageRepository;
import org.example.listingservice.responses.chat.ChatMessageResponse;
import org.example.listingservice.responses.chat.Connected;
import org.example.listingservice.responses.chat.ConversationResponse;
import org.example.listingservice.responses.chat.ListConnected;

import org.springframework.cache.CacheManager;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class IChatService implements ChatService {
    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;
    private final ConversationRepository conversationRepository;


    @Transactional
    @Override
    public ChatMessageResponse saveMessage(ChatMessageDTO dto) throws DataNotFoundException {
        User sender = userRepository.findById(dto.getSenderId())
                .orElseThrow(() -> new DataNotFoundException("Sender not found"));
        User receiver = userRepository.findById(dto.getReceiverId())
                .orElseThrow(() -> new DataNotFoundException("Receiver not found"));

        Conversation conversation = findOrCreateConversation(dto.getSenderId(), dto.getReceiverId(), sender, receiver);

        ChatMessage message = ChatMessage.builder()
                .conversation(conversation)
                .message(dto.getContent())
                .receiver(receiver)
                .status(false)
                .sender(sender)
                .build();
        ChatMessage savedMessage = chatMessageRepository.saveAndFlush(message);

        return ChatMessageResponse.fromChatMessage(savedMessage);
    }

    @Transactional
    @Override
    public ConversationResponse getChatHistory(Long senderId, Long receiverId) throws DataNotFoundException {
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new DataNotFoundException("Sender not found"));
        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new DataNotFoundException("Receiver not found"));

        Conversation conversation = findOrCreateConversation(senderId, receiverId, sender, receiver);
        return ConversationResponse.fromConversation(conversation);
    }


    private Conversation findOrCreateConversation(Long senderId, Long receiverId, User sender, User receiver) {
        Optional<Conversation> conversation = conversationRepository.findBySender_IdAndReceiver_Id(senderId, receiverId);

        if (conversation.isEmpty()) {
            conversation = conversationRepository.findBySender_IdAndReceiver_Id(receiverId, senderId);
        }
        return conversation.orElseGet(() -> conversationRepository.save(
                Conversation.builder().sender(sender).receiver(receiver).build()));
    }

    @Override
    public List<ConversationResponse> getAllCommunication(Long senderId) {
        return null;
    }

    @Override
    public ConversationResponse startConversation(ChatMessageDTO dto) {
        // Placeholder for implementation
        return null;
    }

    @Override
    public ListConnected getAllConnectedByUserId(Long userId) {
       List<Conversation> conversations = conversationRepository.findAllBySender_IdOrReceiver_Id(userId, userId);
        List<Connected> connecteds = conversations.stream()
                .map(Conversation::getMessages)
                .map(messages -> messages.get(messages.size() - 1))
                .map(message -> Connected.toConnected(message, userId))
                .toList();

        return ListConnected.builder()
                .connecteds(connecteds)
                .build();
    }
}
