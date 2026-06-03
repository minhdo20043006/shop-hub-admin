package com.example.demo.service;

import java.util.List;

import com.example.demo.dtos.PromotionAccountDTO;

public interface PromotionAccountService {

	  boolean assign(Integer promotionId, Integer accountId);

	    boolean remove(Integer promotionId, Integer accountId);

	    List<PromotionAccountDTO> findByAccount(Integer accountId);

	    List<PromotionAccountDTO> findValidByAccount(Integer accountId);
}
