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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dtos.ProductDTO;
import com.example.demo.dtos.SellerProfileDTO;
import com.example.demo.enums.ApprovedStatus;
import com.example.demo.enums.ProductStatus;
import com.example.demo.service.ProductService;
import com.example.demo.service.SellerService;

@Controller
@RequestMapping({ "admin/productad/" })
public class ProductProfileForAdminController {

	@Autowired
	private ProductService productService;

	@Autowired
	private SellerService sellerService;

	@GetMapping({ "productprofile", "", "/" })
	public String Index(ModelMap modelMap, Model model) {
		List<ProductDTO> products = productService.findByStatus(ProductStatus.PENDING);

		model.addAttribute("products", products);

		return "admin/productad/productprofile";
	}

	@PostMapping("/active/{id}")
	public String approveProduct(@PathVariable Integer id, RedirectAttributes redirect) {

		boolean success = productService.updateProductStatus(id, ProductStatus.ACTIVE);

		if (success) {
			redirect.addFlashAttribute("success", "Duyệt sản phẩm thành công!");
		} else {
			redirect.addFlashAttribute("error", "Duyệt sản phẩm thất bại!");
		}

		return "redirect:/admin/productad/productprofile";
	}

	@PostMapping("/banned/{id}")
	public String rejectProduct(@PathVariable Integer id, RedirectAttributes redirect) {

		boolean success = productService.updateProductStatus(id, ProductStatus.BANNED);

		if (success) {
			redirect.addFlashAttribute("success", "Đã từ chối sản phẩm!");
		} else {
			redirect.addFlashAttribute("error", "Từ chối sản phẩm thất bại!");
		}

		return "redirect:/admin/productad/productprofile";
	}

}
