package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.apis.APIClient;
import com.example.demo.apis.NotificationAPI;
import com.example.demo.dtos.NotificationDTO;

import retrofit2.Response;

@Service
public class NotificationServiceImpl implements NotificationService {

	private final NotificationAPI notificationAPI = APIClient.getClient().create(NotificationAPI.class);

	@Override
	public List<NotificationDTO> findByAccount(Integer accountId) {
		try {
			Response<List<NotificationDTO>> res = notificationAPI.findByAccount(accountId).execute();
			return res.isSuccessful() ? res.body() : null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<NotificationDTO> findUnreadByAccount(Integer accountId) {
		try {
			Response<List<NotificationDTO>> res = notificationAPI.findUnreadByAccount(accountId).execute();

			if (!res.isSuccessful() || res.body() == null) {
				return List.of();
			}
			return res.body();

		} catch (Exception e) {
			e.printStackTrace();
			return List.of();
		}
	}

	@Override
	public boolean markAsRead(Integer notificationId) {
		try {
			Response<Boolean> res = notificationAPI.markAsRead(notificationId).execute();
			return res.isSuccessful() && Boolean.TRUE.equals(res.body());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean markAllAsRead(Integer accountId) {
		try {
			Response<Boolean> res = notificationAPI.markAllAsRead(accountId).execute();
			return res.isSuccessful() && Boolean.TRUE.equals(res.body());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean create(NotificationDTO notificationDTO) {
		try {
			Response<Boolean> res = notificationAPI.create(notificationDTO).execute();
			return res.isSuccessful() && Boolean.TRUE.equals(res.body());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public NotificationDTO finById(Integer id) {
		try {
			Response<NotificationDTO> res = notificationAPI.finById(id).execute();
			return res.isSuccessful() ? res.body() : null;
		} catch (Exception e) {
			return null;
		}
	}
}
