package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.apis.APIClient;
import com.example.demo.apis.PromotionAccountAPI;
import com.example.demo.dtos.PromotionAccountDTO;

import retrofit2.Response;

@Service
public class PromotionAccountServiceImpl implements PromotionAccountService {

	 private final PromotionAccountAPI api =
	            APIClient.getClient().create(PromotionAccountAPI.class);

	    @Override
	    public boolean assign(Integer promotionId, Integer accountId) {
	        try {
	            Response<String> res =
	                    api.assign(promotionId, accountId).execute();

	            return res.isSuccessful();

	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    @Override
	    public boolean remove(Integer promotionId, Integer accountId) {
	        try {
	            Response<String> res =
	                    api.remove(promotionId, accountId).execute();

	            return res.isSuccessful();

	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    @Override
	    public List<PromotionAccountDTO> findByAccount(Integer accountId) {
	        try {
	            Response<List<PromotionAccountDTO>> res =
	                    api.findByAccount(accountId).execute();

	            return res.isSuccessful() && res.body() != null
	                    ? res.body()
	                    : List.of();

	        } catch (Exception e) {
	            e.printStackTrace();
	            return List.of();
	        }
	    }

	    @Override
	    public List<PromotionAccountDTO> findValidByAccount(Integer accountId) {
	        try {
	            Response<List<PromotionAccountDTO>> res =
	                    api.findValidByAccount(accountId).execute();

	            return res.isSuccessful() && res.body() != null
	                    ? res.body()
	                    : List.of();

	        } catch (Exception e) {
	            e.printStackTrace();
	            return List.of();
	        }
	    }
}
