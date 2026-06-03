package com.example.demo.apis;

import java.util.List;

import com.example.demo.dtos.PromotionAccountDTO;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PromotionAccountAPI {

	// POST api/promotion-account/ad/assign?promotionId=1&accountId=2
	@POST("promotion-account/ad/assign")
	Call<String> assign(@Query("promotionId") Integer promotionId, @Query("accountId") Integer accountId);

	// DELETE api/promotion-account/ad/remove?promotionId=1&accountId=2
	@DELETE("promotion-account/ad/remove")
	Call<String> remove(@Query("promotionId") Integer promotionId, @Query("accountId") Integer accountId);

	// GET api/promotion-account/ad/find-by-account/{accountId}
	@GET("promotion-account/ad/find-by-account/{accountId}")
	Call<List<PromotionAccountDTO>> findByAccount(@Path("accountId") Integer accountId);

	// GET api/promotion-account/ad/valid/{accountId}
	@GET("promotion-account/ad/valid/{accountId}")
	Call<List<PromotionAccountDTO>> findValidByAccount(@Path("accountId") Integer accountId);
}
