package com.example.demo.service;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.apis.APIClient;

import com.example.demo.apis.SellerAPI;
import com.example.demo.dtos.OrderItemDTO;
import com.example.demo.dtos.ProductDTO;
import com.example.demo.dtos.SellerApprovedStatusDTO;
import com.example.demo.dtos.SellerProfileDTO;
import com.example.demo.dtos.SellerReviewDTO;
import com.example.demo.dtos.SellerStatusDTO;
import com.example.demo.enums.ApprovedStatus;
import com.example.demo.enums.ProductStatus;

import retrofit2.Response;

@Service
public class SellerServiceImpl implements SellerService {

	private final SellerAPI sellerAPI = APIClient.getClient().create(SellerAPI.class);

	public List<SellerProfileDTO> findAllSeller() {
		try {
			Response<List<SellerProfileDTO>> res = sellerAPI.findAllSeller().execute();
			return res.isSuccessful() ? res.body() : null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean createSeller(SellerProfileDTO sellerProfileDTO) {
		try {
			Response<Void> res = sellerAPI.createSeller(sellerProfileDTO).execute();
			return res.isSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateSeller(SellerProfileDTO sellerProfileDTO) {
		try {
			Response<Void> res = sellerAPI.updateSeller(sellerProfileDTO).execute();
			return res.isSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteSeller(Integer id) {
		try {
			Response<Void> res = sellerAPI.deleteSeller(id).execute();
			return res.isSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<SellerReviewDTO> findAllReview() {
		try {
			Response<List<SellerReviewDTO>> res = sellerAPI.findAllReview().execute();
			return res.isSuccessful() ? res.body() : null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<SellerReviewDTO> findReviewBySellerId(Integer sellerId) {
		try {
			Response<List<SellerReviewDTO>> res = sellerAPI.findReviewBySellerId(sellerId).execute();
			return res.isSuccessful() ? res.body() : null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean createReview(SellerReviewDTO sellerReviewDTO) {
		try {
			Response<Void> res = sellerAPI.createReview(sellerReviewDTO).execute();
			return res.isSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateReview(SellerReviewDTO sellerReviewDTO) {
		try {
			Response<Void> res = sellerAPI.updateReview(sellerReviewDTO).execute();
			return res.isSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteReview(Integer id) {
		try {
			Response<Void> res = sellerAPI.deleteReview(id).execute();
			return res.isSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean hasSellerProfile(Integer accountId) {
		try {
			Response<Boolean> res = sellerAPI.existsByAccountId(accountId).execute();
			return res.isSuccessful() && Boolean.TRUE.equals(res.body());
		} catch (Exception e) {
			return false;
		}
	}

	public SellerStatusDTO getSellerStatusByAccountId(Integer accountId) {
		try {
			Response<SellerStatusDTO> res = sellerAPI.getSellerStatusByAccountId(accountId).execute();
			return res.isSuccessful() ? res.body() : null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public SellerProfileDTO findByAccountId(Integer accountId) {
		try {
			Response<SellerProfileDTO> res = sellerAPI.findByAccountId(accountId).execute();
			return res.isSuccessful() ? res.body() : null;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<OrderItemDTO> getProductsSoldBySeller(Integer sellerId) {
		try {
			Response<List<OrderItemDTO>> response = sellerAPI.getProductsSoldBySeller(sellerId).execute();

			if (response.isSuccessful() && response.body() != null) {
				return response.body();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}

	@Override
	public int countPendingSellers() {
		try {
			Response<List<SellerProfileDTO>> res = sellerAPI.findByStatus(ApprovedStatus.PENDING).execute();

			if (res.isSuccessful() && res.body() != null) {
				return res.body().size();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public boolean updateSellerStatus(Integer id, ApprovedStatus status) {
		try {
			SellerApprovedStatusDTO dto = new SellerApprovedStatusDTO();
			dto.setApprovedStatus(status);
			Response<Void> res = sellerAPI.updateSellerStatus(id, dto).execute();
			return res.isSuccessful();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public SellerProfileDTO finById(Integer id) {
		try {
			Response<SellerProfileDTO> res = sellerAPI.finById(id).execute();
			return res.isSuccessful() ? res.body() : null;
		} catch (Exception e) {
			return null;
		}
	}

}
