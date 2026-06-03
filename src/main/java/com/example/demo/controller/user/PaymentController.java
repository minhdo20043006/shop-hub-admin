package com.example.demo.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.PaymentService;

@Controller
@RequestMapping("/payment/paypal")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping("/create")
	public String createPayment(@RequestParam("orderId") Integer orderId, @RequestParam("total") float total,
			ModelMap modelMap) {
		String redirectUrl = paymentService.createPayment(orderId, total);
		modelMap.put("orderId", orderId);
		if (redirectUrl != null) {

			return "redirect:" + redirectUrl;

		}
		return "redirect:/cart/checkout?error=payment";
	}

	@GetMapping("/success")
	public String paymentSuccess(@RequestParam("token") String token, @RequestParam("PayerID") String payerId) {
		paymentService.paymentSuccess(token);
		return "redirect:/order/success";
	}

	@GetMapping("/cancel")
	public String paymentCancel() {
		paymentService.paymentCancel();
		return "redirect:/cart/checkout";
	}
}
