package com.example.demo.service;

import java.util.List;

import com.example.demo.dtos.ChatRoomDTO;
import com.example.demo.dtos.ChatMessageDTO;
import com.example.demo.dtos.PageDTO;

public interface ChatService {
    Integer createOrGetChat(String token, Integer senderId, Integer recipientId);

    List<ChatRoomDTO> getUserChats(String token, Integer userId);

    PageDTO<ChatMessageDTO> getChatMessages(String token, Integer chatId, int page, int size);

    ChatMessageDTO sendMessage(String token, ChatMessageDTO chatMessageDTO);
}
