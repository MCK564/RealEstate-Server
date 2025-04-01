package org.example.listingservice.controllers;


import org.example.listingservice.dtos.ChatMessageDTO;
import org.example.listingservice.responses.chat.ChatMessageResponse;
import org.example.listingservice.services.chat.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatService chatService;


    @MessageMapping("/message")
    public ChatMessageResponse sendMessage(@Payload ChatMessageDTO dto) throws Exception {
        Long senderId = dto.getSenderId();
        Long receiverId = dto.getReceiverId();

        ChatMessageResponse response = (ChatMessageResponse) chatService.saveMessage(dto);

        String chatRoom = (senderId < receiverId) ?
                String.format("/chatroom/%s-%s", senderId.toString(), receiverId.toString())
                :  String.format("/chatroom/%s-%s", receiverId.toString(), senderId.toString());

        messagingTemplate.convertAndSend(chatRoom, response);
        return response;
    }






}
