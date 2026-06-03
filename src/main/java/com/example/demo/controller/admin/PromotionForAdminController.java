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

import com.example.demo.dtos.PromotionDTO;
import com.example.demo.dtos.PromotionUpdateStatusDTO;
import com.example.demo.enums.DiscountTypePromotion;
import com.example.demo.enums.PromotionStatus;
import com.example.demo.service.PromotionService;

@Controller
@RequestMapping({ "admin/promotion" })
public class PromotionForAdminController {

	@Autowired
	private PromotionService promotionService;

	@GetMapping({ "index", "/", "" })
	public String index(Model model) {

		List<PromotionDTO> promotions = promotionService.findAll();
		System.out.print("==========" + promotions);
		model.addAttribute("promotions", promotions);

		return "admin/promotion/index";
	}

	@GetMapping("add")
	public String add(Model model) {

	
		model.addAttribute("discountTypes", DiscountTypePromotion.values());
		model.addAttribute("statuses", PromotionStatus.values());
		model.addAttribute("promotion", new PromotionDTO());

		return "admin/promotion/add";
	}

	@PostMapping("add")
	public String addPost(@ModelAttribute("promotion") PromotionDTO promotion, Model model) {

		boolean ok = promotionService.create(promotion);

		if (!ok) {
			model.addAttribute("error", "Tạo promotion thất bại");
			return "admin/promotion/add";
		}

		return "redirect:/admin/promotion";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable Integer id) {

		promotionService.delete(id);

		return "redirect:/admin/promotion";

	}

	@PostMapping("/update-status")
	public String updateStatusPromotion(@RequestParam Integer id, @RequestParam PromotionStatus statusPromotion) {

		PromotionUpdateStatusDTO dto = new PromotionUpdateStatusDTO();
		dto.setStatusPromotion(statusPromotion);

		promotionService.updateStatus(id, dto);

		return "redirect:/admin/promotion/index";
	}

}
