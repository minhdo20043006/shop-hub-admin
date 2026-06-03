package com.example.demo.apis;

import java.util.List;

import com.example.demo.dtos.AccountDTO;
import com.example.demo.dtos.OrderItemDTO;
import com.example.demo.dtos.ProductDTO;
import com.example.demo.dtos.SellerApprovedStatusDTO;
import com.example.demo.dtos.SellerProfileDTO;
import com.example.demo.dtos.SellerReviewDTO;
import com.example.demo.dtos.SellerStatusDTO;
import com.example.demo.entities.Account;
import com.example.demo.enums.ApprovedStatus;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SellerAPI {

	@GET("seller/find-all")
	Call<List<SellerProfileDTO>> findAllSeller();

	@POST("seller/create")
	Call<Void> createSeller(@Body SellerProfileDTO sellerProfileDTO);

	@PUT("seller/profile/update")
	Call<Void> updateSeller(@Body SellerProfileDTO sellerProfileDTO);

	@DELETE("seller/profile/delete/{id}")
	Call<Void> deleteSeller(@Path("id") Integer id);

	@GET("seller/review/findall")
	Call<List<SellerReviewDTO>> findAllReview();

	@GET("seller/review/find-by-seller-id/{sellerId}")
	Call<List<SellerReviewDTO>> findReviewBySellerId(@Path("sellerId") Integer sellerId);

	@POST("seller/review/create")
	Call<Void> createReview(@Body SellerReviewDTO sellerReviewDTO);

	@PUT("seller/review/update")
	Call<Void> updateReview(@Body SellerReviewDTO sellerReviewDTO);

	@DELETE("seller/review/delete/{id}")
	Call<Void> deleteReview(@Path("id") Integer id);

	@GET("seller/exists-by-account/{accountId}")
	Call<Boolean> existsByAccountId(@Path("accountId") Integer accountId);

	@GET("seller/find-by-account-id/{accountId}")
	Call<SellerProfileDTO> findByAccountId(@Path("accountId") Integer accountId);

	@GET("seller/status-by-account/{accountId}")
	Call<SellerStatusDTO> getSellerStatusByAccountId(@Path("accountId") Integer accountId);

	@GET("seller/products-sold/{sellerId}")
	Call<List<OrderItemDTO>> getProductsSoldBySeller(@Path("sellerId") Integer sellerId);

	@GET("seller/find-by-status/{status}")
	Call<List<SellerProfileDTO>> findByStatus(@Path("status") ApprovedStatus status);

	@PUT("seller/profile/update-status/{id}")
	Call<Void> updateSellerStatus(@Path("id") Integer id, @Body SellerApprovedStatusDTO statusDto);

	@GET("seller/find-by-id/{id}")
	Call<SellerProfileDTO> finById(@Path("id") Integer id);

}
