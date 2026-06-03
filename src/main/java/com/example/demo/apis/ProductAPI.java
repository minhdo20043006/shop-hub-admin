package com.example.demo.apis;

import java.util.List;

import com.example.demo.dtos.ProductDTO;
import com.example.demo.dtos.ProductReviewDTO;
import com.example.demo.dtos.ProductUpdateStatusDTO;
import com.example.demo.enums.ProductStatus;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductAPI {

	@GET("product/all/find-all-product-active")
	Call<List<ProductDTO>> findAllActive(@Query("status") ProductStatus status);

	@GET("product/all/find-by-category-and-status")
	Call<List<ProductDTO>> findByCategoryAndStatus(@Query("categoryId") Integer categoryId,
			@Query("status") ProductStatus status);

	@GET("product/all/find-by-id/{id}")
	Call<ProductDTO> finById(@Path("id") Integer id);

	@GET("product/all/find-by-keyword")
	Call<List<ProductDTO>> findByKeyword(@Query("keyword") String keyword);

	// find by category
	@GET("product/all/find-by-category-id/{categoryId}")
	Call<List<ProductDTO>> findByCategoryId(@Path("categoryId") Integer categoryId);

	// find by price range
	@GET("product/all/find-by-price-range/{code}")
	Call<List<ProductDTO>> findByPriceRange(@Path("code") String code);

	// admin find by stock quantity
	@GET("product/ad/find-by-stock-quantity/{quantity}")
	Call<List<ProductDTO>> findByStockQuantity(@Path("quantity") int quantity);

	// admin quantity between
	@GET("product/ad/find-by-stock-quantity-between/{min}/{max}")
	Call<List<ProductDTO>> findByStockBetween(@Path("min") int min, @Path("max") int max);

	// admin find by status
	@GET("product/ad/find-by-status")
	Call<List<ProductDTO>> findByStatus(@Query("status") ProductStatus status);

	// newest products
	@GET("product/all/find-by-new-product/{limit}")
	Call<List<ProductDTO>> findNewestProducts(@Path("limit") int limit);

	// best seller
	@GET("product/all/find-by-best-seller-product/{limit}")
	Call<List<ProductDTO>> findBestSellerProducts(@Path("limit") int limit);

	// discount > 50%
	@GET("product/all/find-by-discount-over-50")
	Call<List<ProductDTO>> findDiscountOver50();

	// category + price
	@GET("product/all/find-by-category-and-price-code")
	Call<List<ProductDTO>> findByCategoryAndPriceCode(@Query("categoryId") Integer categoryId,
			@Query("code") String code);

	// seller create product
	@POST("product/se/create")
	Call<Void> createProduct(@Body ProductDTO productDTO);

	// seller update product
	@PUT("product/se/update")
	Call<Void> updateProduct(@Body ProductDTO productDTO);

	// admin update status
	@PUT("product/ad/update-status-admin")
	Call<Void> updateStatus(@Query("id") Integer id, @Body ProductUpdateStatusDTO dto);

	// delete product
	@DELETE("product/as/delete/{id}")
	Call<Void> deleteProduct(@Path("id") Integer id);

	@GET("product/review/findall")
	Call<List<ProductReviewDTO>> findAllReview();

	@GET("product/review/find-by-product-id/{productId}")
	Call<List<ProductReviewDTO>> findReviewByProductId(@Path("productId") Integer productId);

	@POST("product/review/create")
	Call<Void> createReview(@Body ProductReviewDTO reviewDTO);

	@PUT("product/review/update")
	Call<Void> updateReview(@Body ProductReviewDTO reviewDTO);

	@DELETE("product/review/delete/{id}")
	Call<Void> deleteReview(@Path("id") Integer id);

	@GET("product/all/find-by-seller-and-statuses")
	Call<List<ProductDTO>> getProductsBySeller(@Query("sellerId") Integer sellerId,
			@Query("statuses") List<ProductStatus> statuses);
	
	
	// find by category
	@GET("product/all/find-by-seller-id/{sellerId}")
	Call<List<ProductDTO>> findBySellerId(@Path("sellerId") Integer sellerId);
}
