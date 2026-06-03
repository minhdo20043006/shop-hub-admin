package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.apis.APIClient;
import com.example.demo.apis.AccountAPI;
import com.example.demo.apis.ProductAPI;
import com.example.demo.dtos.ProductDTO;
import com.example.demo.dtos.ProductReviewDTO;
import com.example.demo.dtos.ProductUpdateStatusDTO;
import com.example.demo.dtos.SellerApprovedStatusDTO;
import com.example.demo.enums.ProductStatus;

import retrofit2.Response;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductAPI productAPI = APIClient.getClient().create(ProductAPI.class);

	@Override
	public List<ProductDTO> findAllActive(ProductStatus status) {
		try {
			Response<List<ProductDTO>> res = productAPI.findAllActive(status).execute();
			return res.isSuccessful() ? res.body() : null;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<ProductDTO> findByKeyword(String keyword) {
		try {
			Response<List<ProductDTO>> res = productAPI.findByKeyword(keyword).execute();
			return res.isSuccessful() ? res.body() : null;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<ProductDTO> findByCategoryId(Integer categoryId) {
		try {
			Response<List<ProductDTO>> res = productAPI.findByCategoryId(categoryId).execute();
			return res.isSuccessful() ? res.body() : null;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<ProductDTO> findByPriceRange(String code) {
		try {
			Response<List<ProductDTO>> res = productAPI.findByPriceRange(code).execute();
			return res.isSuccessful() ? res.body() : null;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean createProduct(ProductDTO dto) {
		try {
			Response<Void> res = productAPI.createProduct(dto).execute();
			return res.isSuccessful();
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateProduct(ProductDTO dto) {
		try {
			Response<Void> res = productAPI.updateProduct(dto).execute();
			return res.isSuccessful();
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteProduct(Integer id) {
		try {
			Response<Void> res = productAPI.deleteProduct(id).execute();
			return res.isSuccessful();
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<ProductReviewDTO> findReviewByProductId(Integer productId) {
		try {
			Response<List<ProductReviewDTO>> res = productAPI.findReviewByProductId(productId).execute();
			return res.isSuccessful() ? res.body() : null;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean createReview(ProductReviewDTO dto) {
		try {
			Response<Void> res = productAPI.createReview(dto).execute();
			return res.isSuccessful();
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateReview(ProductReviewDTO dto) {
		try {
			Response<Void> res = productAPI.updateReview(dto).execute();
			return res.isSuccessful();
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteReview(Integer id) {
		try {
			Response<Void> res = productAPI.deleteReview(id).execute();
			return res.isSuccessful();
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public ProductDTO finById(Integer id) {
		try {
			Response<ProductDTO> res = productAPI.finById(id).execute();
			return res.isSuccessful() ? res.body() : null;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<ProductDTO> findByCategoryAndActive(Integer categoryId) {
		try {
			Response<List<ProductDTO>> res = productAPI.findByCategoryAndStatus(categoryId, ProductStatus.ACTIVE)
					.execute();

			return res.isSuccessful() ? res.body() : null;

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<ProductDTO> findByCategoryAndPriceCode(Integer categoryId, String code) {
		try {
			Response<List<ProductDTO>> res = productAPI.findByCategoryAndPriceCode(categoryId, code).execute();

			return res.isSuccessful() ? res.body() : null;

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<ProductDTO> findNewestProducts(int limit) {
		try {
			Response<List<ProductDTO>> res = productAPI.findNewestProducts(limit).execute();

			return res.isSuccessful() ? res.body() : null;

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<ProductDTO> findBySellerAndStatuses(Integer sellerId, List<ProductStatus> statuses) {
		try {
			Response<List<ProductDTO>> res = productAPI.getProductsBySeller(sellerId, statuses).execute();
			return res.isSuccessful() ? res.body() : null;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<ProductDTO> findBySellerId(Integer sellerId) {
		try {
			Response<List<ProductDTO>> res = productAPI.findBySellerId(sellerId).execute();
			return res.isSuccessful() ? res.body() : null;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int countPendingProducts() {
		try {
			Response<List<ProductDTO>> res = productAPI.findAllActive(ProductStatus.PENDING).execute();

			if (res.isSuccessful() && res.body() != null) {
				return res.body().size();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean updateProductStatus(Integer productId, ProductStatus status) {
		try {
			ProductUpdateStatusDTO dto = new ProductUpdateStatusDTO();
			dto.setStatus(status);
			Response<Void> res = productAPI.updateStatus(productId, dto).execute();
			return res.isSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<ProductDTO> findByStatus(ProductStatus status) {
		try {
			Response<List<ProductDTO>> res = productAPI.findByStatus(status).execute();
			return res.isSuccessful() ? res.body() : null;
		} catch (Exception e) {
			return null;
		}
	}

}
