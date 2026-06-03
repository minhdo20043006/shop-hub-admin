package com.example.demo.apis;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PaymentAPI {

	// create paypal payment → return approve url
	@GET("payment/paypal/create")
	Call<String> createPayment(@Query("orderId") Integer orderId, @Query("amount") float amount);

	// payment success callback
	@GET("payment/paypal/success")
	Call<String> paymentSuccess(@Query("token") String token);

	// payment cancel
	@GET("payment/paypal/cancel")
	Call<String> paymentCancel();
}
	