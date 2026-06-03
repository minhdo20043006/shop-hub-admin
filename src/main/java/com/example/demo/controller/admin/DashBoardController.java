package com.example.demo.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.ProductService;
import com.example.demo.service.SellerService;

@Controller
@RequestMapping({ "admin" })
public class DashBoardController {

	@Autowired
	private ProductService productService;

	@Autowired
	private SellerService sellerService;

	@GetMapping({ "dashboard", "", "/" })
	public String Index(ModelMap modelMap, Model model) {
		int pendingCount = productService.countPendingProducts();
		int pendingSellerCount = sellerService.countPendingSellers();
		model.addAttribute("pendingCount", pendingCount);
		System.out.print("-------------"+pendingSellerCount);
		model.addAttribute("pendingSellerCount", pendingSellerCount);

		return "admin/dashboard";
	}

	
}
