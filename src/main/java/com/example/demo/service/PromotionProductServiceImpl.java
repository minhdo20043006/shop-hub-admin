package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.apis.APIClient;
import com.example.demo.apis.PromotionProductAPI;
import com.example.demo.dtos.PromotionProductDTO;

import retrofit2.Response;

@Service
public class PromotionProductServiceImpl implements PromotionProductService {

    private final PromotionProductAPI api =
            APIClient.getClient().create(PromotionProductAPI.class);

    @Override
    public boolean assign(Integer promotionId, Integer productId) {
        try {
            Response<String> res =
                    api.assignToProduct(promotionId, productId).execute();
            return res.isSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean remove(Integer promotionId, Integer productId) {
        try {
            Response<String> res =
                    api.removeFromProduct(promotionId, productId).execute();
            return res.isSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<PromotionProductDTO> findByProduct(Integer productId) {
        try {
            Response<List<PromotionProductDTO>> res =
                    api.findByProduct(productId).execute();
            return res.body();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
