package com.example.demo.service;

import java.util.List;

import com.example.demo.dtos.CategoryDTO;
import com.example.demo.dtos.CategoryUpdateStatusDTO;
import com.example.demo.entities.Category;
import com.example.demo.enums.CategoryStatus;

public interface CategoryService {

	List<CategoryDTO> findAllByStatusActive(CategoryStatus status);

	List<CategoryDTO> findAllForAdmin();

	boolean Create(CategoryDTO dto);

	boolean Update(Integer id, CategoryDTO dto);

	boolean UpdateStatusCategory(Integer id, CategoryUpdateStatusDTO dto);

	boolean Delete(int id);
	public Category findById(Integer id);
}
