package com.example.demo.apis;

import java.util.List;

import com.example.demo.dtos.NotificationDTO;
import com.example.demo.dtos.ProductDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface NotificationAPI {
	// get all notifications by account
	@GET("notification/all/list/{accountId}")
	Call<List<NotificationDTO>> findByAccount(@Path("accountId") Integer accountId);

	// get unread notifications
	@GET("notification/all/unread/{accountId}")
	Call<List<NotificationDTO>> findUnreadByAccount(@Path("accountId") Integer accountId);

	// mark one notification as read
	@PUT("notification/all/read/{notificationId}")
	Call<Boolean> markAsRead(@Path("notificationId") Integer notificationId);

	// mark all notifications as read
	@PUT("notification/all/read-all/{accountId}")
	Call<Boolean> markAllAsRead(@Path("accountId") Integer accountId);

	// create notification
	@POST("notification/create")
	Call<Boolean> create(@Body NotificationDTO notificationDTO);
	
	@GET("notification/all/find-by-id/{id}")
	Call<NotificationDTO> finById(@Path("id") Integer id);
}
