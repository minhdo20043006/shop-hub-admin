package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.apis.APIClient;
import com.example.demo.apis.PromotionCategoryAPI;
import com.example.demo.dtos.PromotionCategoryDTO;

import retrofit2.Response;

@Service
public class PromotionCategoryServiceImpl implements PromotionCategoryService {

    private final PromotionCategoryAPI api =
            APIClient.getClient().create(PromotionCategoryAPI.class);

    @Override
    public boolean assign(Integer promotionId, Integer categoryId) {
        try {
            Response<String> res =
                    api.assignToCategory(promotionId, categoryId).execute();
            return res.isSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean remove(Integer promotionId, Integer categoryId) {
        try {
            Response<String> res =
                    api.removeFromCategory(promotionId, categoryId).execute();
            return res.isSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<PromotionCategoryDTO> findByCategory(Integer categoryId) {
        try {
            Response<List<PromotionCategoryDTO>> res =
                    api.findByCategory(categoryId).execute();
            return res.body();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
