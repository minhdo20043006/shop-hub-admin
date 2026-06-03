package com.example.demo.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.enums.CategoryStatus;
import com.example.demo.service.AccountService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.PromotionAccountService;
import com.example.demo.service.PromotionCategoryService;
import com.example.demo.service.PromotionService;

@Controller
@RequestMapping("/admin/promotioncategory")
public class PromotionCategoryAdminController {

	@Autowired
	private PromotionCategoryService promotionCategoryService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private PromotionService promotionService;

	@GetMapping("/assign")
	public String showAssignPage(@RequestParam Integer categoryId, Model model) {

		model.addAttribute("category", categoryService.findById(categoryId));

		model.addAttribute("promotions", promotionService.findAll());

		return "admin/promotioncategory/assign";
	}

	@PostMapping("/assign")
	public String assignPromotion(@RequestParam Integer promotionId, @RequestParam Integer categoryId) {

		promotionCategoryService.assign(promotionId, categoryId);

		return "redirect:/admin/promotioncategory/list";
	}

	// Remove promotion khỏi account
	@PostMapping("/remove")
	public String remove(@RequestParam Integer promotionId, @RequestParam Integer categoryId) {

		promotionCategoryService.remove(promotionId, categoryId);
		return "redirect:/admin/promotioncategory/listpfc/" + categoryId;
	}

	// Xem danh sách promotion theo ct
	@GetMapping("/list")
	public String viewByAccount(Model model) {
		model.addAttribute("categories", categoryService.findAllByStatusActive(CategoryStatus.ACTIVE));

		return "admin/promotioncategory/list";
	}

	@GetMapping("/listpfc/{categoryId}")
	public String findByCategory(Model model, @PathVariable("categoryId") Integer categoryId) {
		model.addAttribute("promotionfcs", promotionCategoryService.findByCategory(categoryId));

		return "admin/promotioncategory/listpfc";
	}
}