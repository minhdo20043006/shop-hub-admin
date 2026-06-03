package com.example.demo.service;

import java.util.List;

import com.example.demo.dtos.OrdersDTO;
import com.example.demo.dtos.ProductDTO;
import com.example.demo.entities.Orders;
import com.example.demo.enums.OrderStatus;

public interface OrderService {

	List<OrdersDTO> getOrders(Integer accountId);

	public Integer createOrder(Integer accountId, OrdersDTO orderDTO);

	boolean updateOrderStatus(Integer orderId, String status);

	OrdersDTO finById(Integer id);

	public List<OrdersDTO> getOrdersBySeller(Integer sellerId, List<OrderStatus> statuses);

	public List<OrdersDTO> findAllOrder();
}
