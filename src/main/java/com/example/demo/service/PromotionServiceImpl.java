package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.apis.APIClient;
import com.example.demo.apis.PromotionAPI;
import com.example.demo.dtos.PromotionDTO;
import com.example.demo.dtos.PromotionUpdateStatusDTO;
import com.example.demo.enums.PromotionStatus;

import retrofit2.Response;


@Service
public class PromotionServiceImpl implements PromotionService {

    private final PromotionAPI api =
            APIClient.getClient().create(PromotionAPI.class);

    

    @Override
    public List<PromotionDTO> findAll() {
        try {
            Response<List<PromotionDTO>> res =
                    api.findAllForAdmin().execute();

            return res.isSuccessful() && res.body() != null
                    ? res.body()
                    : List.of();

        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public List<PromotionDTO> findByStatus(PromotionStatus status) {
        try {
            Response<List<PromotionDTO>> res =
                    api.findByStatusForAdmin(status).execute();

            return res.isSuccessful() && res.body() != null
                    ? res.body()
                    : List.of();

        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public List<PromotionDTO> findByProductId(Integer productId) {
        try {
            Response<List<PromotionDTO>> res =
                    api.findByProductIdForAdmin(productId).execute();

            return res.isSuccessful() && res.body() != null
                    ? res.body()
                    : List.of();

        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public List<PromotionDTO> findByCategoryId(Integer categoryId) {
        try {
            Response<List<PromotionDTO>> res =
                    api.findByCategoryId(categoryId).execute();

            return res.isSuccessful() && res.body() != null
                    ? res.body()
                    : List.of();

        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

  

    @Override
    public boolean create(PromotionDTO dto) {
        try {
            Response<Void> res =
                    api.createPromotion(dto).execute();

            return res.isSuccessful();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Integer id, PromotionDTO dto) {
        try {
            Response<Void> res =
                    api.updatePromotion(id, dto).execute();

            return res.isSuccessful();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateStatus(Integer id, PromotionUpdateStatusDTO dto) {
        try {
            Response<Void> res =
                    api.updateStatusPromotion(id, dto).execute();

            return res.isSuccessful();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        try {
            Response<Void> res =
                    api.deletePromotion(id).execute();

            return res.isSuccessful();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}


