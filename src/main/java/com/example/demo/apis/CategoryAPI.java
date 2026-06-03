package com.example.demo.apis;

import java.util.List;

import com.example.demo.dtos.CategoryDTO;
import com.example.demo.dtos.CategoryUpdateStatusDTO;
import com.example.demo.entities.Account;
import com.example.demo.entities.Category;
import com.example.demo.enums.CategoryStatus;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CategoryAPI {

	// lấy category ACTIVE
	@GET("category/all/find-all-category-active")
	Call<List<CategoryDTO>> findAllActive(@Query("status") CategoryStatus status);

	// admin: lấy tất cả category
	@GET("category/ad/find-all")
	Call<List<CategoryDTO>> findAllForAdmin();

	// admin: tạo category
	@POST("category/ad/create")
	Call<Void> createCategory(@Body CategoryDTO dto);

	// admin: update category
	@PUT("category/ad/update")
	Call<Void> updateCategory(@Query("id") Integer id, @Body CategoryDTO dto);

	// admin: update status category
	@PUT("category/ad/update-status-category")
	Call<Void> updateStatusCategory(@Query("id") Integer id, @Body CategoryUpdateStatusDTO dto);

	// admin: delete
	@DELETE("category/ad/delete/{id}")
	Call<Void> deleteCategory(@Path("id") Integer id);
	
	@GET("account/all/find-by-id/{id}")
	Call<Category> findById(@Path("id") Integer id);
}
