package com.example.demo.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.ProductService;

@Controller
@RequestMapping({ "home", "", "/" })
public class HomeController {

	@Autowired
	private ProductService productService;

	@GetMapping({ "index", "", "/" })
	public String Index(ModelMap modelMap) {
		modelMap.put("newProducts", productService.findNewestProducts(3));

		return "home/index";
	}

	@GetMapping({ "index2" })
	public String Index2() {

		return "home/index2";
	}
}
