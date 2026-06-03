package com.example.demo.service;

import java.util.List;

import com.example.demo.dtos.PromotionCategoryDTO;

public interface PromotionCategoryService {

    boolean assign(Integer promotionId, Integer categoryId);

    boolean remove(Integer promotionId, Integer categoryId);

    List<PromotionCategoryDTO> findByCategory(Integer categoryId);
}
