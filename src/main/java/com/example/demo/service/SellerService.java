package com.example.demo.service;

import java.util.List;

import com.example.demo.dtos.AccountDTO;
import com.example.demo.dtos.OrderItemDTO;
import com.example.demo.dtos.ProductDTO;
import com.example.demo.dtos.SellerProfileDTO;
import com.example.demo.dtos.SellerReviewDTO;
import com.example.demo.dtos.SellerStatusDTO;
import com.example.demo.entities.Account;
import com.example.demo.enums.ApprovedStatus;

public interface SellerService {
	public List<SellerProfileDTO> findAllSeller();

	public boolean createSeller(SellerProfileDTO sellerProfileDTO);

	public boolean updateSeller(SellerProfileDTO sellerProfileDTO);

	public boolean deleteSeller(Integer id);

	public List<SellerReviewDTO> findAllReview();

	public List<SellerReviewDTO> findReviewBySellerId(Integer sellerId);

	public boolean createReview(SellerReviewDTO sellerReviewDTO);

	public boolean updateReview(SellerReviewDTO sellerReviewDTO);

	public boolean deleteReview(Integer id);

	public boolean hasSellerProfile(Integer accountId);

	public SellerStatusDTO getSellerStatusByAccountId(Integer accountId);

	public SellerProfileDTO findByAccountId(Integer accountId);

	List<OrderItemDTO> getProductsSoldBySeller(Integer sellerId);
	
	int countPendingSellers();
	
	public boolean updateSellerStatus(Integer id, ApprovedStatus status); 
	
	SellerProfileDTO finById(Integer id);

}
