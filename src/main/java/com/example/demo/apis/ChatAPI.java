package com.example.demo.apis;

import java.util.List;

import com.example.demo.dtos.ChatMessageDTO;
import com.example.demo.dtos.ChatRoomDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ChatAPI {

    @POST("chat/create")
    Call<Integer> createOrGetChat(@Header("Authorization") String token, @Query("senderId") Integer senderId,
            @Query("recipientId") Integer recipientId);

    @GET("chat/user/{userId}")
    Call<List<ChatRoomDTO>> getUserChats(@Header("Authorization") String token, @Path("userId") Integer userId);

    @POST("chat/send")
    Call<ChatMessageDTO> sendMessage(@Header("Authorization") String token, @Body ChatMessageDTO chatMessageDTO);

    @GET("chat/{chatId}/messages")
    Call<com.example.demo.dtos.PageDTO<ChatMessageDTO>> getChatMessages(@Header("Authorization") String token,
            @Path("chatId") Integer chatId,
            @Query("page") int page, @Query("size") int size);
    // Note: Return Object because Retrofit/Gson might have trouble with Page<T>
    // directly if without custom adapter.
    // Or we can create a PageDTO wrapper. For now, Object or Map is safer for raw
    // JSON inspection if unsure.
    // Better: Helper class for Page response
}
