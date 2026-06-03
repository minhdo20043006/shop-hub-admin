package com.example.demo.service;

import java.util.List;

import com.example.demo.dtos.CartDTO;

public interface CartService {
	List<CartDTO> getCart(Integer accountId);

	boolean addToCart(CartDTO cartDTO);

	boolean updateCartItem(Integer id, CartDTO cartDTO);

	boolean removeCartItem(Integer id);

	Float getTotal(Integer accountId);
}
