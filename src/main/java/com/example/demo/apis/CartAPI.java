package com.example.demo.apis;

import java.util.List;

import com.example.demo.dtos.CartDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CartAPI {

    // get cart by account
    @GET("cart/{accountId}")
    Call<List<CartDTO>> getCart(@Path("accountId") Integer accountId);

    // add to cart
    @POST("cart/add")
    Call<Void> addToCart(@Body CartDTO cartDTO);

    // update cart item
    @PUT("cart/update/{id}")
    Call<Void> updateCartItem(@Path("id") Integer id,
                              @Body CartDTO cartDTO);

    // remove cart item
    @DELETE("cart/remove/{id}")
    Call<Void> removeCartItem(@Path("id") Integer id);

    // get total amount
    @GET("cart/total/{accountId}")
    Call<Float> getTotal(@Path("accountId") Integer accountId);
}
