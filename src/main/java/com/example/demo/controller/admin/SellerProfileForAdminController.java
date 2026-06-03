package com.example.demo.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dtos.ProductDTO;
import com.example.demo.dtos.SellerProfileDTO;
import com.example.demo.enums.ApprovedStatus;
import com.example.demo.enums.ProductStatus;
import com.example.demo.service.ProductService;
import com.example.demo.service.SellerService;

@Controller
@RequestMapping({ "admin/sellerad/" })
public class SellerProfileForAdminController {

	@Autowired
	private ProductService productService;

	@Autowired
	private SellerService sellerService;

	@GetMapping({ "profile", "", "/" })
	public String Index(ModelMap modelMap, Model model) {
		List<SellerProfileDTO> sellerProfiles = sellerService.findAllSeller();

		model.addAttribute("sellerProfiles", sellerProfiles);

		return "admin/sellerad/profile";
	}

	@GetMapping("approve/{id}")
	public String approveSeller(@PathVariable Integer id) {

		sellerService.updateSellerStatus(id, ApprovedStatus.APPROVED);
		return "redirect:/admin/sellerad/profile";
	}

	@GetMapping("reject/{id}")
	public String rejectSeller(@PathVariable Integer id) {

		sellerService.updateSellerStatus(id, ApprovedStatus.REJECTED);
		return "redirect:/admin/sellerad/profile";
	}

	@GetMapping("/detail/{sellerId}")
	public String sellerDetail(@PathVariable Integer sellerId, Model model) {

		SellerProfileDTO seller = sellerService.finById(sellerId);

		if (seller == null) {
			return "redirect:/admin/sellerad?error=notfound";
		}

		List<ProductDTO> products = productService.findBySellerAndStatuses(sellerId, List.of(ProductStatus.ACTIVE,
				ProductStatus.PENDING, ProductStatus.INACTIVE, ProductStatus.OUT_OF_STOCK, ProductStatus.BANNED));

		model.addAttribute("seller", seller);
		model.addAttribute("products", products);

		return "admin/sellerad/detail";
	}

	@PostMapping("/deleteproduct/{id}")
	public String deleteProduct(@PathVariable Integer id, @RequestParam Integer sellerId) {

		productService.deleteProduct(id);
		return "redirect:/admin/sellerad/detail/" + sellerId;
	}

}
