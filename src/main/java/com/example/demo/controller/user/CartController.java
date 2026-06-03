package com.example.demo.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dtos.AccountInfoDTO;
import com.example.demo.dtos.CartDTO;
import com.example.demo.dtos.OrdersDTO;
import com.example.demo.entities.Account;
import com.example.demo.entities.Orders;
import com.example.demo.service.CartService;
import com.example.demo.service.OrderService;
import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("cart")
public class CartController {

	@Autowired
	private CartService cartService;

	// XEM GIỎ HÀNG
	@Autowired
	private OrderService orderService;

	@GetMapping({ "", "/", "index" })
	public String index(Model model, HttpSession session) {

		AccountInfoDTO acc = (AccountInfoDTO) session.getAttribute("ACCOUNT");

		if (acc == null) {
			return "redirect:/auth/login";
		}

		List<CartDTO> cartItems = cartService.getCart(acc.getId());
		Float total = cartService.getTotal(acc.getId());

		model.addAttribute("cartItems", cartItems);
		model.addAttribute("total", total);

		return "cart/index";
	}

	// MUA NGAY → ĐẨY VÀO CART

	@PostMapping("buy-now")
	public String buyNow(@RequestParam Integer productId, @RequestParam(defaultValue = "1") Integer quantity,
			HttpSession session) {
		AccountInfoDTO acc = (AccountInfoDTO) session.getAttribute("ACCOUNT");

		if (acc == null) {
			return "redirect:/auth/login";
		}

		CartDTO dto = new CartDTO();
		dto.setAccountId(acc.getId());
		dto.setProductId(productId);
		dto.setQuantityCart(quantity);

		cartService.addToCart(dto);
		return "redirect:/cart";
	}

	// XÓA ITEM KHỎI CART

	@PostMapping("remove")
	public String removeItem(@RequestParam Integer cartItemId) {
		cartService.removeCartItem(cartItemId);
		return "redirect:/cart";
	}

	// CHECKOUT

	@GetMapping("checkout")
	public String checkout(Model model, HttpSession session) {

		AccountInfoDTO acc = (AccountInfoDTO) session.getAttribute("ACCOUNT");
		if (acc == null) {
			return "redirect:/auth/login";
		}

		List<CartDTO> cartItems = cartService.getCart(acc.getId());
		if (cartItems.isEmpty()) {
			return "redirect:/cart";
		}

		double subtotal = cartService.getTotal(acc.getId());

		model.addAttribute("account", acc);
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("subtotal", subtotal);
		model.addAttribute("discount", 0);
		model.addAttribute("total", subtotal);

		return "cart/checkout";
	}

	// THANH TOÁN (TẠM THỜI)

	@PostMapping("checkout")
	public String doCheckout(HttpSession session, RedirectAttributes redirect) {

		AccountInfoDTO acc = (AccountInfoDTO) session.getAttribute("ACCOUNT");
		if (acc == null)
			return "redirect:/auth/login";

		OrdersDTO orderDTO = new OrdersDTO();
		orderDTO.setPaymentMethodId(1); // PayPal

		Integer orderId = orderService.createOrder(acc.getId(), orderDTO);

		if (orderId == null) {
			return "redirect:/cart?error=order_failed";
		}

		redirect.addAttribute("orderId", orderId);
		return "redirect:/cart/pay";
	}

	@GetMapping("thankyou")
	public String thankyou() {
		return "cart/thankyou";
	}

	@GetMapping("pay")
	public String payPage(@RequestParam Integer orderId, Model model) {
		OrdersDTO ordersDTO = orderService.finById(orderId);

		System.out.println("ORDER ID = " + ordersDTO.getId()); // debug

		model.addAttribute("total", ordersDTO.getTotalAmount());
		model.addAttribute("orderId", ordersDTO.getId());

		return "cart/pay";
	}

	@PostMapping("/update")
	@ResponseBody
	public String updateCart(
			@RequestParam Integer cartItemId,
			@RequestParam Integer quantity,
			HttpSession session) {

		AccountInfoDTO acc = (AccountInfoDTO) session.getAttribute("ACCOUNT");

		if (acc == null) {
			return "NOT_LOGIN";
		}

		CartDTO dto = new CartDTO();
		dto.setId(cartItemId);
		dto.setQuantityCart(quantity);
		dto.setAccountId(acc.getId());

		boolean ok = cartService.updateCartItem(cartItemId, dto);
		return ok ? "OK" : "FAIL";
	}

}
