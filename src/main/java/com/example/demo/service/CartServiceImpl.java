package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.apis.APIClient;
import com.example.demo.apis.CartAPI;
import com.example.demo.dtos.CartDTO;

import retrofit2.Response;

@Service
public class CartServiceImpl implements CartService {

	private final CartAPI cartAPI = APIClient.getClient().create(CartAPI.class);

	@Override
	public List<CartDTO> getCart(Integer accountId) {
		try {
			Response<List<CartDTO>> res = cartAPI.getCart(accountId).execute();
			return res.isSuccessful() ? res.body() : List.of();
		} catch (Exception e) {
			e.printStackTrace();
			return List.of();
		}
	}

	@Override
	public boolean addToCart(CartDTO cartDTO) {
		try {
			Response<Void> res = cartAPI.addToCart(cartDTO).execute();
			return res.isSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateCartItem(Integer id, CartDTO cartDTO) {
		try {
			Response<Void> res = cartAPI.updateCartItem(id, cartDTO).execute();
			return res.isSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean removeCartItem(Integer id) {
		try {
			Response<Void> res = cartAPI.removeCartItem(id).execute();
			return res.isSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Float getTotal(Integer accountId) {
		try {
			Response<Float> res = cartAPI.getTotal(accountId).execute();
			return res.isSuccessful() ? res.body() : 0f;
		} catch (Exception e) {
			e.printStackTrace();
			return 0f;
		}
	}
}
