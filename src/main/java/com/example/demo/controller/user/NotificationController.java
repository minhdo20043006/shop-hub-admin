package com.example.demo.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dtos.NotificationDTO;
import com.example.demo.service.NotificationService;

@Controller
@RequestMapping("/notification")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	@GetMapping("/open/{id}")
	public String open(@PathVariable Integer id) {

		NotificationDTO noti = notificationService.finById(id);

		notificationService.markAsRead(id);

		switch (noti.getTypeNotification()) {

		case CATEGORY:
			return "redirect:/product?categoryId=" + noti.getCategoryId() + "&fromNoti=" + id;

		case PRODUCT:
			return "redirect:/product/productDetail/" + noti.getProductId() + "/" + noti.getCategoryId() + "?fromNoti="
					+ id;

		default:
			return "redirect:/";
		}
	}
}
