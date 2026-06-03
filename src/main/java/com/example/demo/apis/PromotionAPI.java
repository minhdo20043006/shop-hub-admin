package com.example.demo.apis;

import java.util.List;

import com.example.demo.dtos.PromotionDTO;
import com.example.demo.dtos.PromotionUpdateStatusDTO;
import com.example.demo.enums.PromotionStatus;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PromotionAPI {

	/* ================= USER ================= */

	// GET api/promotion/all/find-all-promotion-active?statusPromotion=ACTIVE
	@GET("promotion/all/find-all-promotion-active")
	Call<List<PromotionDTO>> findAllActive(@Query("statusPromotion") PromotionStatus statusPromotion);

	// GET api/promotion/user/find-valid-promotion-by-product/{productId}
	@GET("promotion/user/find-valid-promotion-by-product/{productId}")
	Call<List<PromotionDTO>> findValidPromotionByProduct(@Path("productId") Integer productId);

	/* ================= ADMIN ================= */

	// GET api/promotion/ad/find-all-for-admin
	@GET("promotion/ad/find-all-for-admin")
	Call<List<PromotionDTO>> findAllForAdmin();

	// GET api/promotion/ad/find-by-status-for-admin?statusPromotion=ACTIVE
	@GET("promotion/ad/find-by-status-for-admin")
	Call<List<PromotionDTO>> findByStatusForAdmin(@Query("statusPromotion") PromotionStatus statusPromotion);

	// GET api/promotion/ad/find-by-product-id/{productId}
	@GET("promotion/ad/find-by-product-id/{productId}")
	Call<List<PromotionDTO>> findByProductIdForAdmin(@Path("productId") Integer productId);

	// GET api/promotion/au/find-by-category-id/{categoryId}
	@GET("promotion/au/find-by-category-id/{categoryId}")
	Call<List<PromotionDTO>> findByCategoryId(@Path("categoryId") Integer categoryId);

	// POST api/promotion/ad/create
	@POST("promotion/ad/create")
	Call<Void> createPromotion(@Body PromotionDTO promotionDTO);

	// PUT api/promotion/ad/update/{id}
	@PUT("promotion/ad/update/{id}")
	Call<Void> updatePromotion(@Path("id") Integer id, @Body PromotionDTO promotionDTO);

	// PUT api/promotion/ad/update-status-promotion?id=1
	@PUT("promotion/ad/update-status-promotion")
	Call<Void> updateStatusPromotion(@Query("id") Integer id, @Body PromotionUpdateStatusDTO updateStatusDto);

	// DELETE api/promotion/ad/delete/{id}
	@DELETE("promotion/ad/delete/{id}")
	Call<Void> deletePromotion(@Path("id") Integer id);
}
