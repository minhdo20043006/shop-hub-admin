package com.example.demo.service;

public interface PaymentService {
	String createPayment(Integer orderId, float amount);

	String paymentSuccess(String token);

	String paymentCancel();
}
