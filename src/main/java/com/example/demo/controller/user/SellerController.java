package com.example.demo.controller.user;

import java.lang.ProcessBuilder.Redirect;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dtos.AccountInfoDTO;
import com.example.demo.dtos.OrderItemDTO;
import com.example.demo.dtos.OrdersDTO;
import com.example.demo.dtos.ProductDTO;
import com.example.demo.dtos.SellerProfileDTO;
import com.example.demo.dtos.SellerReviewDTO;
import com.example.demo.entities.Product;
import com.example.demo.entities.SellerProfile;
import com.example.demo.enums.ApprovedStatus;
import com.example.demo.enums.CategoryStatus;
import com.example.demo.enums.OrderStatus;
import com.example.demo.enums.ProductStatus;
import com.example.demo.enums.StatusAccount;
import com.example.demo.service.CategoryService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import com.example.demo.service.SellerService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/seller")
public class SellerController {

	@Autowired
	private SellerService sellerService;

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/index")
	public String listSeller(Model model) {
		model.addAttribute("sellers", sellerService.findAllSeller());

		return "seller/index";
	}

	@GetMapping("/create")
	public String createForm(Model model) {
		model.addAttribute("seller", new SellerProfile());
		return "seller/create";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute("seller") SellerProfileDTO sellerProfileDTO, HttpSession session,
			RedirectAttributes redirect) {
		AccountInfoDTO acc = (AccountInfoDTO) session.getAttribute("ACCOUNT");
		if (acc == null)
			return "redirect:/auth/login";

		sellerProfileDTO.setAccountId(acc.getId());
		sellerProfileDTO.setApprovedStatus(ApprovedStatus.PENDING);
		sellerProfileDTO.setLogo("defaule.png");
		sellerProfileDTO.setAvgRating(0);
		sellerProfileDTO.setReviewCount(0);
		sellerProfileDTO.setCreatedAt(new Date());
		sellerProfileDTO.setUpdatedAt(new Date());

		boolean success = sellerService.createSeller(sellerProfileDTO);

		if (success) {
			redirect.addFlashAttribute("msg", "Đăng ký thành công");
			return "redirect:/seller/login";
		} else {
			redirect.addFlashAttribute("error", "Đăng ký thất bại");
			return "redirect:/seller/index";
		}
	}

	@GetMapping("/edit/{id}")
	public String editForm(@PathVariable Integer id, Model model) {

		return "seller/edit";
	}

	@PostMapping("/update/{id}")
	public String update(@PathVariable Integer id, @ModelAttribute("seller") SellerProfileDTO sellerProfileDTO) {

		sellerService.updateSeller(sellerProfileDTO);
		return "redirect:/seller/index";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		sellerService.deleteSeller(id);
		return "redirect:/seller/list";
	}

	@GetMapping("/review/{sellerId}")
	public String listReview(@PathVariable Integer sellerId, Model model) {

		model.addAttribute("reviews", sellerService.findReviewBySellerId(sellerId));
		model.addAttribute("sellerId", sellerId);

		return "seller/review";
	}

	@PostMapping("/review/create")
	public String createReview(@ModelAttribute SellerReviewDTO sellerReviewDTO) {

		sellerService.createReview(sellerReviewDTO);
		return "redirect:/seller/review/" + sellerReviewDTO.getSellerProfileId();
	}

	@PostMapping("/review/update/{id}")
	public String updateReview(@PathVariable Integer id, @ModelAttribute SellerReviewDTO sellerReviewDTO) {

		sellerService.updateReview(sellerReviewDTO);
		return "redirect:/seller/review/" + sellerReviewDTO.getSellerProfileId();
	}

	@GetMapping("/review/delete/{id}/{sellerId}")
	public String deleteReview(@PathVariable Integer id, @PathVariable Integer sellerId) {

		sellerService.deleteReview(id);
		return "redirect:/seller/review/" + sellerId;
	}

	@GetMapping("/add")
	public String addProduct(Model model, ModelMap modelMap) {
		model.addAttribute("product", new ProductDTO());
		modelMap.put("categories", categoryService.findAllByStatusActive(CategoryStatus.ACTIVE));
		return "seller/add";
	}

	@PostMapping("/add")
	public String addProduct(@ModelAttribute("product") ProductDTO productDTO, HttpSession session,
			RedirectAttributes redirect) {

		AccountInfoDTO acc = (AccountInfoDTO) session.getAttribute("ACCOUNT");
		if (acc == null) {
			return "redirect:/auth/login";
		}

		SellerProfileDTO seller = sellerService.findByAccountId(acc.getId());
		System.out.print("-----------------" + acc.getId());
		if (seller == null) {
			redirect.addFlashAttribute("error", "Bạn chưa đăng ký bán hàng");
			return "redirect:/seller/create";
		}

		if (seller.getApprovedStatus() == ApprovedStatus.PENDING) {
			redirect.addFlashAttribute("error", "Hồ sơ bán hàng đang chờ phê duyệt");

		}

		if (seller.getApprovedStatus() != ApprovedStatus.APPROVED) {
			redirect.addFlashAttribute("error", "Tài khoản bán hàng không hợp lệ");

		}

		productDTO.setSellerProfileId(seller.getId());
		productDTO.setStatus(ProductStatus.PENDING);
		productDTO.setPhoto("default.png");
		productDTO.setIsFeatured(false);
		productDTO.setIsNew(false);
		productDTO.setAvgRating(0);
		productDTO.setReviewCount(0);
		productDTO.setCreatedAt(new Date());
		productDTO.setUpdatedAt(new Date());

		try {
			productService.createProduct(productDTO);
			redirect.addFlashAttribute("msg", "Tạo sản phẩm thành công");
			return "redirect:/seller/index";
		} catch (Exception e) {
			e.printStackTrace();
			redirect.addFlashAttribute("error", "Tạo sản phẩm thất bại");
			return "redirect:/seller/add";
		}
	}

	@GetMapping("/productos")
	public String ProductOnSell(Model model, ModelMap modelMap, HttpSession session,
			@RequestParam(required = false) List<ProductStatus> statuses) {
		AccountInfoDTO acc = (AccountInfoDTO) session.getAttribute("ACCOUNT");
		if (acc == null) {
			return "redirect:/auth/login";
		}

		SellerProfileDTO seller = sellerService.findByAccountId(acc.getId());
		System.out.print("--------------" + seller.getId());
		if (statuses == null || statuses.isEmpty()) {
			statuses = List.of(ProductStatus.ACTIVE, ProductStatus.PENDING, ProductStatus.INACTIVE,
					ProductStatus.OUT_OF_STOCK, ProductStatus.BANNED);
		}

		List<ProductDTO> products = productService.findBySellerAndStatuses(seller.getId(), statuses);

		model.addAttribute("products", products);
		model.addAttribute("currentStatus", statuses);

		return "seller/productos";
	}

	@GetMapping("/editproduct/{productId}")
	public String Edit(Model model, ModelMap modelMap, HttpSession session,
			@PathVariable("productId") Integer productId) {
		AccountInfoDTO acc = (AccountInfoDTO) session.getAttribute("ACCOUNT");
		if (acc == null) {
			return "redirect:/auth/login";
		}
		modelMap.put("categories", categoryService.findAllByStatusActive(CategoryStatus.ACTIVE));
		model.addAttribute("product", productService.finById(productId));

		return "seller/editproduct";
	}

	@PostMapping("/editproduct")
	public String EditProduct(@ModelAttribute("product") ProductDTO productDTO, HttpSession session,
			RedirectAttributes redirect) {

		AccountInfoDTO acc = (AccountInfoDTO) session.getAttribute("ACCOUNT");
		if (acc == null) {
			return "redirect:/auth/login";
		}

		SellerProfileDTO seller = sellerService.findByAccountId(acc.getId());
		System.out.print("-----------------" + acc.getId());
		productDTO.setIsFeatured(false);
		productDTO.setIsNew(false);
		productDTO.setAvgRating(0);
		productDTO.setReviewCount(0);

		productDTO.setStatus(ProductStatus.PENDING);
		productDTO.setUpdatedAt(new Date());

		try {
			productService.updateProduct(productDTO);
			redirect.addFlashAttribute("msg", "Sửa sản phẩm thành công");
			return "redirect:/seller/productos";
		} catch (Exception e) {
			e.printStackTrace();
			redirect.addFlashAttribute("error", "Sửa sản phẩm thất bại");
			return "redirect:/seller/editproduct/" + productDTO.getId();
		}
	}

	@PostMapping("/deleteproduct/{id}")
	public String deleteProduct(@PathVariable Integer id, RedirectAttributes redirectAttributes, HttpSession session) {

		// Check login seller
		AccountInfoDTO acc = (AccountInfoDTO) session.getAttribute("ACCOUNT");

		if (acc == null) {
			return "redirect:/auth/login";
		}

		boolean success = productService.deleteProduct(id);

		if (success) {
			redirectAttributes.addFlashAttribute("successMessage", "Xóa sản phẩm thành công!");
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "Xóa sản phẩm thất bại!");
		}

		return "redirect:/seller/productos";
	}

	@GetMapping("/productssold")
	public String productsSold(Model model, HttpSession session) {

		AccountInfoDTO acc = (AccountInfoDTO) session.getAttribute("ACCOUNT");

		if (acc == null) {
			return "redirect:/auth/login";
		}

		// Lấy seller theo account
		SellerProfileDTO seller = sellerService.findByAccountId(acc.getId());

		List<OrderItemDTO> orderItems = sellerService.getProductsSoldBySeller(seller.getId());

		model.addAttribute("items", orderItems);

		return "seller/productssold";
	}

	@GetMapping("/chat")
	public String chat() {
		return "seller/chat";
	}

}
