package com.example.demo.apis;

import java.util.List;

import com.example.demo.dtos.PromotionAccountDTO;
import com.example.demo.dtos.PromotionCategoryDTO;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PromotionCategoryAPI {

	
	
	
	@POST("promotion-category/ad/assign")
	Call<String> assignToCategory(@Query("promotionId") Integer promotionId, @Query("categoryId") Integer categoryId);

	// DELETE api/promotion-account/ad/remove?promotionId=1&accountId=2
	@DELETE("promotion-category/ad/remove")
	Call<String> removeFromCategory(@Query("promotionId") Integer promotionId, @Query("categoryId") Integer categoryId);

	// GET api/promotion-account/ad/find-by-account/{accountId}
	@GET("promotion-category/ad/find-by-category/{categoryId}")
	Call<List<PromotionCategoryDTO>> findByCategory(@Path("categoryId") Integer categoryId);

}
