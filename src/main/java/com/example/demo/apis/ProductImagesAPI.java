package com.example.demo.apis;

import java.util.List;

import com.example.demo.dtos.ProductImagesDTO;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ProductImagesAPI {
	
	@Multipart
	@POST("product-images/upload/{productId}")
	Call<String> uploadImages(@Path("productId") Integer productId, @Part List<MultipartBody.Part> files);

	@PUT("product-images/set-primary/{imageId}")
	Call<String> setPrimaryImage(@Path("imageId") Integer imageId);

	
	@DELETE("product-images/{imageId}")
	Call<String> deleteImage(@Path("imageId") Integer imageId);

	
	@GET("product-images/product/{productId}")
	Call<List<ProductImagesDTO>> findByProduct(@Path("productId") Integer productId);
}
