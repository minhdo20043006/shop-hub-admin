package com.example.demo.apis;

import java.util.List;

import com.example.demo.dtos.PromotionProductDTO;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PromotionProductAPI {

    @POST("api/promotion-product/ad/assign")
    Call<String> assignToProduct(
        @Query("promotionId") Integer promotionId,
        @Query("productId") Integer productId
    );

    @DELETE("api/promotion-product/ad/remove")
    Call<String> removeFromProduct(
        @Query("promotionId") Integer promotionId,
        @Query("productId") Integer productId
    );

    @GET("api/promotion-product/ad/find-by-product/{productId}")
    Call<List<PromotionProductDTO>> findByProduct(
        @Path("productId") Integer productId
    );
}
