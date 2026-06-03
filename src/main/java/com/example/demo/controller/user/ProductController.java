package com.example.demo.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dtos.NotificationDTO;
import com.example.demo.dtos.ProductDTO;
import com.example.demo.entities.Orders;
import com.example.demo.enums.CategoryStatus;
import com.example.demo.enums.ProductStatus;
import com.example.demo.service.CategoryService;
import com.example.demo.service.NotificationService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductImagesService;
import com.example.demo.service.ProductService;

@Controller
@RequestMapping({ "product" })
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductImagesService productImagesService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private NotificationService notificationService;
	


	@GetMapping({ "index", "", "/" })
	public String list(@RequestParam(required = false) String keyword,
			@RequestParam(required = false) Integer categoryId, @RequestParam(required = false) String priceCode,
			@RequestParam(required = false) Integer fromNoti, @RequestParam(required = false) Integer notiId,
			ModelMap modelMap) {

		List<ProductDTO> products;

		if (categoryId != null && priceCode != null && !priceCode.isEmpty()) {
			products = productService.findByCategoryAndPriceCode(categoryId, priceCode);
		}

		else if (categoryId != null) {
			products = productService.findByCategoryAndActive(categoryId);
		}

		else if (priceCode != null && !priceCode.isEmpty()) {
			products = productService.findByPriceRange(priceCode);
		}

		else if (keyword != null && !keyword.isBlank()) {
			products = productService.findByKeyword(keyword);
		}

		else {
			products = productService.findAllActive(ProductStatus.ACTIVE);
		}

		if (fromNoti != null) {
			NotificationDTO noti = notificationService.finById(fromNoti);
			modelMap.put("notiMessage", noti.getMessageNotification());
			modelMap.put("notiTitle", noti.getTitleNotification());
		}
		


		modelMap.put("products", products);
		modelMap.put("categories", categoryService.findAllByStatusActive(CategoryStatus.ACTIVE));
		modelMap.put("selectedPriceCode", priceCode);
		modelMap.put("selectedCategory", categoryId);
		modelMap.put("keyword", keyword);
		modelMap.put("selectedPrice", priceCode);

		return "product/index";
	}

	@GetMapping("/productDetail/{id}/{categoryId}")
	public String productDetail(@PathVariable("id") Integer id, @PathVariable("categoryId") Integer categoryId,
			ModelMap modelMap) {
		modelMap.put("productRelateds", productService.findByCategoryAndActive(categoryId));
		modelMap.put("product", productService.finById(id));
		modelMap.put("productImg", productImagesService.findByProduct(id));

		return "product/productDetail";
	}

	@GetMapping("/search")
	public String search(@RequestParam String keyword, Model model) {

		model.addAttribute("products", productService.findByKeyword(keyword));

		return "product/list";
	}

	@GetMapping("/category/{id}")
	public String category(@PathVariable Integer id, Model model) {

		model.addAttribute("productRelateds", productService.findByCategoryId(id));

		return "product/list";
	}

	@GetMapping("/price/{code}")
	public String price(@PathVariable String code, Model model) {

		model.addAttribute("products", productService.findByPriceRange(code));

		return "product/list";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {

		productService.deleteProduct(id);

		return "redirect:/product/list";
	}
	
	
}
