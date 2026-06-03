package com.example.demo.service;

import java.util.List;

import com.example.demo.dtos.PromotionDTO;
import com.example.demo.dtos.PromotionUpdateStatusDTO;
import com.example.demo.enums.PromotionStatus;

public interface PromotionService {

    List<PromotionDTO> findAll();

    List<PromotionDTO> findByStatus(PromotionStatus status);

    List<PromotionDTO> findByProductId(Integer productId);

    List<PromotionDTO> findByCategoryId(Integer categoryId);

    boolean create(PromotionDTO dto);

    boolean update(Integer id, PromotionDTO dto);

    boolean updateStatus(Integer id, PromotionUpdateStatusDTO dto);

    boolean delete(Integer id);
}

