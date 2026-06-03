package com.example.demo.apis;

import java.util.List;

import com.example.demo.dtos.OrdersDTO;
import com.example.demo.dtos.ProductDTO;
import com.example.demo.enums.OrderStatus;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OrderAPI {

	// get orders by account
	@GET("order/{accountId}")
	Call<List<OrdersDTO>> getOrders(@Path("accountId") Integer accountId);

	@POST("order/create/{accountId}")
	Call<Integer> createOrder(@Path("accountId") Integer accountId, @Body OrdersDTO orderDTO);

	// update order status (admin/seller/shipper)
	@PUT("order/update/status/{id}")
	Call<Void> updateStatus(@Path("id") Integer orderId, @Body String status);

	@GET("order/find-by-id/{id}")
	Call<OrdersDTO> finById(@Path("id") Integer id);

	@GET("order/find-by-seller-status")
	Call<List<OrdersDTO>> getOrdersBySellerAndStatuses(@Query("sellerId") Integer sellerId,
			@Query("statuses") List<OrderStatus> statuses);

	@GET("order/find-all")
	Call<List<OrdersDTO>> findAllOrder();

}
