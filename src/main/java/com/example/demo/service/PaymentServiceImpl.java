package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.apis.APIClient;
import com.example.demo.apis.PaymentAPI;

import retrofit2.Response;

@Service
public class PaymentServiceImpl implements PaymentService {

	private final PaymentAPI paymentAPI = APIClient.getClient().create(PaymentAPI.class);

	@Override
	public String createPayment(Integer orderId, float amount) {
		try {
			Response<String> res = paymentAPI.createPayment(orderId, amount).execute();
			return res.isSuccessful() ? res.body() : null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String paymentSuccess(String token) {
		try {
			Response<String> res = paymentAPI.paymentSuccess(token).execute();
			return res.isSuccessful() ? res.body() : null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String paymentCancel() {
		try {
			Response<String> res = paymentAPI.paymentCancel().execute();
			return res.isSuccessful() ? res.body() : null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
