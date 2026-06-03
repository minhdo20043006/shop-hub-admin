package com.example.demo.controller.user;

import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.dtos.AccountDTO;
import com.example.demo.dtos.AccountInfoDTO;
import com.example.demo.dtos.NotificationDTO;
import com.example.demo.dtos.OrdersDTO;
import com.example.demo.dtos.SellerStatusDTO;
import com.example.demo.entities.Notification;
import com.example.demo.service.NotificationService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import com.example.demo.service.SellerService;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

@ControllerAdvice
public class GlobalControllerAdvice {

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private SellerService sellerService;

	@Autowired
	private OrderService orderService;

	@ModelAttribute
	public void addGlobalAttributes(HttpSession session, Model model) {

		System.out.println("=== GLOBAL ADVICE RUNNING ===");

		model.addAttribute("currentNotifications", List.of());
		model.addAttribute("currentAccount", null);

		Object accObj = session.getAttribute("ACCOUNT");
		System.out.println("SESSION ACCOUNT = " + accObj);

		if (accObj == null) {
			System.out.println("❌ ACCOUNT NULL -> STOP");
			return;
		}

		AccountInfoDTO acc = (AccountInfoDTO) accObj;
		System.out.println("✅ ACCOUNT ID = " + acc.getId());

		List<NotificationDTO> notis = notificationService.findUnreadByAccount(acc.getId());

		System.out.println("🔔 NOTIFICATIONS = " + notis);
		System.out.println("🔔 SIZE = " + (notis == null ? "null" : notis.size()));

		if (notis == null) {
			notis = List.of();
		}

		List<OrdersDTO> odercs = orderService.getOrders(acc.getId());

		model.addAttribute("currentOrder", odercs);
		model.addAttribute("currentAccount", acc);
		model.addAttribute("currentNotifications", notis);
		SellerStatusDTO sellerStatus = sellerService.getSellerStatusByAccountId(acc.getId());

		model.addAttribute("sellerStatus", sellerStatus);
	}

}
