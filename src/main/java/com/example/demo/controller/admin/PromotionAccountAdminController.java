package com.example.demo.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.AccountService;
import com.example.demo.service.PromotionAccountService;
import com.example.demo.service.PromotionService;

@Controller
@RequestMapping("/admin/promotionaccount")
public class PromotionAccountAdminController {

	@Autowired
	private PromotionAccountService promotionAccountService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private PromotionService promotionService;

	@GetMapping("/assign")
	public String showAssignPage(@RequestParam Integer accountId, Model model) {

		model.addAttribute("account", accountService.findById(accountId));

		model.addAttribute("promotions", promotionService.findAll());

		return "admin/promotionaccount/assign";
	}

	@PostMapping("/assign")
	public String assignPromotion(@RequestParam Integer promotionId, @RequestParam Integer accountId) {

		promotionAccountService.assign(promotionId, accountId);

		return "redirect:/admin/promotionaccount/list";
	}

	// Remove promotion khỏi account
	@PostMapping("/remove")
	public String remove(@RequestParam Integer promotionId, @RequestParam Integer accountId) {

		promotionAccountService.remove(promotionId, accountId);
		return "redirect:/admin/promotionaccount/listpfa/" + accountId;
	}

	// Xem danh sách promotion theo account
	@GetMapping("/list")
	public String viewByAccount(Model model) {
		model.addAttribute("accounts", accountService.findAll());

		return "admin/promotionaccount/list";
	}

	@GetMapping("/listpfa/{accountId}")
	public String findByAccount(Model model, @PathVariable("accountId") Integer accountId) {
		model.addAttribute("promotionfas", promotionAccountService.findByAccount(accountId));

		return "admin/promotionaccount/listpfa";
	}
}