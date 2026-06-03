package com.example.demo.service;

import java.util.List;

import com.example.demo.dtos.PromotionProductDTO;

public interface PromotionProductService {

    boolean assign(Integer promotionId, Integer productId);

    boolean remove(Integer promotionId, Integer productId);

    List<PromotionProductDTO> findByProduct(Integer productId);
}

