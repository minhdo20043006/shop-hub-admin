package com.example.demo.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dtos.OrdersDTO;
import com.example.demo.dtos.PromotionDTO;
import com.example.demo.dtos.PromotionUpdateStatusDTO;
import com.example.demo.enums.DiscountTypePromotion;
import com.example.demo.enums.PromotionStatus;
import com.example.demo.service.OrderService;
import com.example.demo.service.PromotionService;

@Controller
@RequestMapping({ "admin/order" })
public class OrderForAdminController {

	@Autowired
	private OrderService orderService;

	@GetMapping({ "index", "/", "" })
	public String index(Model model) {

		List<OrdersDTO> orders = orderService.findAllOrder();
		System.out.print("==========" + orders);
		model.addAttribute("orders", orders);

		return "admin/order/index";
	}

	

}
