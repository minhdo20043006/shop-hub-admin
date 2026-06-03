package com.example.demo.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dtos.AccountInfoDTO;
import com.example.demo.dtos.OrdersDTO;
import com.example.demo.entities.Orders;
import com.example.demo.service.OrderService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

	@Autowired
	private OrderService orderService;

	@GetMapping({ "", "/", "/index" })
	public String index(Model model, HttpSession session) {

		AccountInfoDTO acc = (AccountInfoDTO) session.getAttribute("ACCOUNT");

		if (acc == null) {
			return "redirect:/auth/login";
		}

		OrdersDTO orderDTO = new OrdersDTO();

		Integer orderId = orderService.createOrder(acc.getId(), orderDTO);
		model.addAttribute("orderId", orderId);

		if (orderId == null) {
			return "redirect:/cart?error=checkout";
		}

		model.addAttribute("orderId", orderId);

		return "checkout/index";
	}
}
