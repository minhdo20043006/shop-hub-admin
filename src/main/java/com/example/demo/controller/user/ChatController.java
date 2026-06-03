package com.example.demo.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dtos.ChatRoomDTO;
import com.example.demo.service.ChatService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    // AJAX Endpoint: List my chats
    @GetMapping("/list")
    @ResponseBody
    public ResponseEntity<List<ChatRoomDTO>> getMyChats(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("id");
        String token = (String) session.getAttribute("JWT");
        if (userId == null) {
            return ResponseEntity.status(401).build();
        }
        List<ChatRoomDTO> chats = chatService.getUserChats(token, userId);
        return ResponseEntity.ok(chats);
    }

    // AJAX Endpoint: Get specific chat messages
    @GetMapping("/{chatId}/messages")
    @ResponseBody
    public ResponseEntity<com.example.demo.dtos.PageDTO<com.example.demo.dtos.ChatMessageDTO>> getChatMessages(
            @PathVariable Integer chatId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            HttpSession session) {
        String token = (String) session.getAttribute("JWT");
        return ResponseEntity.ok(chatService.getChatMessages(token, chatId, page, size));
    }

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<Integer> createChat(@RequestParam Integer recipientId, HttpSession session) {
        Integer senderId = (Integer) session.getAttribute("id");
        String token = (String) session.getAttribute("JWT");

        if (senderId == null) {
            return ResponseEntity.status(401).body(null);
        }

        Integer chatId = chatService.createOrGetChat(token, senderId, recipientId);

        if (chatId == null) {
            return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.ok(chatId);
    }

    // AJAX Endpoint: Send message
    @PostMapping("/send")
    @ResponseBody
    public ResponseEntity<com.example.demo.dtos.ChatMessageDTO> sendMessage(
            @org.springframework.web.bind.annotation.RequestBody com.example.demo.dtos.ChatMessageDTO chatMessageDTO,
            HttpSession session) {
        Integer senderId = (Integer) session.getAttribute("id");
        String token = (String) session.getAttribute("JWT");
        if (senderId == null) {
            return ResponseEntity.status(401).build();
        }
        chatMessageDTO.setSenderId(senderId);
        return ResponseEntity.ok(chatService.sendMessage(token, chatMessageDTO));
    }
}
