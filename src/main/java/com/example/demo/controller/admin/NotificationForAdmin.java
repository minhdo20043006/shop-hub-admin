package com.example.demo.controller.admin;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dtos.NotificationDTO;
import com.example.demo.enums.CategoryStatus;
import com.example.demo.enums.NotificationType;
import com.example.demo.enums.ProductStatus;
import com.example.demo.enums.ReceiverType;
import com.example.demo.service.CategoryService;
import com.example.demo.service.NotificationService;
import com.example.demo.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping({ "admin/notification" })
public class NotificationForAdmin {

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@GetMapping("add")
	public String add(Model model) {

		model.addAttribute("notification", new NotificationDTO());

		model.addAttribute("types", NotificationType.values());
		model.addAttribute("receivers", ReceiverType.values());

		model.addAttribute("products", productService.findAllActive(ProductStatus.ACTIVE));
		model.addAttribute("categories", categoryService.findAllByStatusActive(CategoryStatus.ACTIVE));

		return "admin/notification/add";
	}

	@PostMapping("add")
	public String addPost(
			@ModelAttribute("notification") NotificationDTO dto,
			RedirectAttributes ra) {

		boolean ok = notificationService.create(dto);

		if (ok) {
			ra.addFlashAttribute("success", "Tạo notification thành công");
		} else {
			ra.addFlashAttribute("error", "Tạo notification thất bại");
		}

		return "redirect:/admin/notification/add";
	}

	@GetMapping("list")
	public String list(Model model, HttpSession session) {

		// ví dụ lấy sellerId / accountId từ session
		Integer accountId = (Integer) session.getAttribute("accountId");

		model.addAttribute("notifications", notificationService.findByAccount(accountId));

		return "seller/notification/list";
	}
}
