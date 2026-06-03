package com.example.demo.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.apis.APIClient;
import com.example.demo.apis.ChatAPI;
import com.example.demo.dtos.ChatRoomDTO;

import retrofit2.Response;

@Service
public class ChatServiceImpl implements ChatService {

    @Override
    public Integer createOrGetChat(String token, Integer senderId, Integer recipientId) {
        try {
            ChatAPI chatAPI = APIClient.getClient().create(ChatAPI.class);
            Response<Integer> response = chatAPI.createOrGetChat("Bearer " + token, senderId, recipientId).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                // FAILED
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ChatRoomDTO> getUserChats(String token, Integer userId) {
        try {
            ChatAPI chatAPI = APIClient.getClient().create(ChatAPI.class);
            Response<List<ChatRoomDTO>> response = chatAPI.getUserChats("Bearer " + token, userId).execute();
            if (response.isSuccessful()) {
                return response.body();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public com.example.demo.dtos.PageDTO<com.example.demo.dtos.ChatMessageDTO> getChatMessages(String token,
            Integer chatId, int page,
            int size) {
        try {
            ChatAPI chatAPI = APIClient.getClient().create(ChatAPI.class);
            Response<com.example.demo.dtos.PageDTO<com.example.demo.dtos.ChatMessageDTO>> response = chatAPI
                    .getChatMessages("Bearer " + token, chatId, page, size).execute();
            if (response.isSuccessful()) {
                return response.body();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public com.example.demo.dtos.ChatMessageDTO sendMessage(String token,
            com.example.demo.dtos.ChatMessageDTO chatMessageDTO) {
        try {
            ChatAPI chatAPI = APIClient.getClient().create(ChatAPI.class);
            Response<com.example.demo.dtos.ChatMessageDTO> response = chatAPI
                    .sendMessage("Bearer " + token, chatMessageDTO).execute();
            if (response.isSuccessful()) {
                return response.body();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
