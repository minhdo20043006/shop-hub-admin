package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.apis.APIClient;
import com.example.demo.apis.CategoryAPI;
import com.example.demo.apis.ProductAPI;
import com.example.demo.dtos.CategoryDTO;
import com.example.demo.dtos.CategoryUpdateStatusDTO;
import com.example.demo.entities.Account;
import com.example.demo.entities.Category;
import com.example.demo.enums.CategoryStatus;

import retrofit2.Response;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryAPI categoryAPI = APIClient.getClient().create(CategoryAPI.class);

	@Override
	public List<CategoryDTO> findAllByStatusActive(CategoryStatus status) {
		try {
			Response<List<CategoryDTO>> res = categoryAPI.findAllActive(status).execute();

			return res.isSuccessful() ? res.body() : null;

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<CategoryDTO> findAllForAdmin() {
		try {
			Response<List<CategoryDTO>> res = categoryAPI.findAllForAdmin().execute();

			return res.isSuccessful() ? res.body() : null;

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean Create(CategoryDTO dto) {
		try {
			Response<Void> res = categoryAPI.createCategory(dto).execute();

			return res.isSuccessful();

		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean Update(Integer id, CategoryDTO dto) {
		try {
			Response<Void> res = categoryAPI.updateCategory(id, dto).execute();

			return res.isSuccessful();

		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean UpdateStatusCategory(Integer id, CategoryUpdateStatusDTO dto) {
		try {
			Response<Void> res = categoryAPI.updateStatusCategory(id, dto).execute();

			return res.isSuccessful();

		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean Delete(int id) {
		try {
			Response<Void> res = categoryAPI.deleteCategory(id).execute();

			return res.isSuccessful();

		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public Category findById(Integer id) {
		try {
			Response<Category> res = categoryAPI.findById(id).execute();

			if (!res.isSuccessful()) {
				throw new RuntimeException("Không lấy được account");
			}

			return res.body();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
