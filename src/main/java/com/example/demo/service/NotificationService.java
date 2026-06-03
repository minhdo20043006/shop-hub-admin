package com.example.demo.service;

import java.util.List;

import com.example.demo.dtos.NotificationDTO;

public interface NotificationService {
	List<NotificationDTO> findByAccount(Integer accountId);

	List<NotificationDTO> findUnreadByAccount(Integer accountId);

	boolean markAsRead(Integer notificationId);

	boolean markAllAsRead(Integer accountId);

	boolean create(NotificationDTO notificationDTO);

	public NotificationDTO finById(Integer id);
}
