package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.apis.APIClient;
import com.example.demo.apis.OrderAPI;
import com.example.demo.dtos.OrdersDTO;
import com.example.demo.dtos.ProductDTO;
import com.example.demo.entities.Orders;
import com.example.demo.enums.OrderStatus;

import retrofit2.Response;

@Service
public class OrderServiceImpl implements OrderService {

	private final OrderAPI orderAPI = APIClient.getClient().create(OrderAPI.class);

	@Override
	public List<OrdersDTO> getOrders(Integer accountId) {
		try {
			Response<List<OrdersDTO>> res = orderAPI.getOrders(accountId).execute();
			return res.isSuccessful() ? res.body() : List.of();
		} catch (Exception e) {
			e.printStackTrace();
			return List.of();
		}
	}

	@Override
	public Integer createOrder(Integer accountId, OrdersDTO orderDTO) {
		try {
			Response<Integer> res = orderAPI.createOrder(accountId, orderDTO).execute();

			if (res.isSuccessful() && res.body() != null) {
				return res.body(); // ✅ orderId
			}

			return null;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean updateOrderStatus(Integer orderId, String status) {
		try {
			Response<Void> res = orderAPI.updateStatus(orderId, status).execute();
			return res.isSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public OrdersDTO finById(Integer id) {
		try {
			Response<OrdersDTO> res = orderAPI.finById(id).execute();
			return res.isSuccessful() ? res.body() : null;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<OrdersDTO> getOrdersBySeller(Integer sellerId, List<OrderStatus> statuses) {
		try {
			Response<List<OrdersDTO>> res = orderAPI.getOrdersBySellerAndStatuses(sellerId, statuses).execute();

			if (res.isSuccessful()) {
				return res.body();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return List.of();
	}

	@Override
	public List<OrdersDTO> findAllOrder() {
		try {
			Response<List<OrdersDTO>> res = orderAPI.findAllOrder().execute();
			return res.isSuccessful() ? res.body() : List.of();
		} catch (Exception e) {
			e.printStackTrace();
			return List.of();
		}
	}

}
