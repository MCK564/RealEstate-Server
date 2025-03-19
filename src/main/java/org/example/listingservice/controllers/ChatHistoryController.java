package org.example.listingservice.controllers;

import lombok.RequiredArgsConstructor;
import org.example.listingservice.dtos.ChatMessageDTO;
import org.example.listingservice.exceptions.DataNotFoundException;
import org.example.listingservice.services.chat.ChatService;
import org.example.listingservice.services.token.TokenService;
import org.example.listingservice.utils.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/conversations")
@RequiredArgsConstructor
public class ChatHistoryController {
    private final ChatService chatService;
    private final JwtUtils jwtUtils;
    private final TokenService tokenService;

    @GetMapping("/{senderId}/{receiverId}")
    public ResponseEntity<?> getChatHistory(
          @PathVariable("senderId") Long senderId,
            @PathVariable("receiverId") Long receiverId
    ) throws DataNotFoundException {
        return ResponseEntity.ok(chatService.getChatHistory(senderId,receiverId));
    }

    @PostMapping("")
    public ResponseEntity<?> sendMessage(@RequestBody ChatMessageDTO dto) throws DataNotFoundException{
        return ResponseEntity.ok(chatService.saveMessage(dto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getAllUserConnectedToMe(@RequestHeader("Authorization")String token,
                                                     @PathVariable("userId")Long userId)
    {
        return ResponseEntity.ok(chatService.getAllConnectedByUserId(userId));
    }

}
