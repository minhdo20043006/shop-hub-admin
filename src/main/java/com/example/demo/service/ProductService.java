package com.example.demo.service;

import java.util.List;

import com.example.demo.dtos.ProductDTO;
import com.example.demo.dtos.ProductReviewDTO;
import com.example.demo.enums.ProductStatus;

public interface ProductService {
	List<ProductDTO> findAllActive(ProductStatus status);

	List<ProductDTO> findByStatus(ProductStatus status);
	
	ProductDTO finById(Integer id);

	List<ProductDTO> findByKeyword(String keyword);

	List<ProductDTO> findByCategoryId(Integer categoryId);
	
	List<ProductDTO> findBySellerId(Integer sellerId);

	List<ProductDTO> findByPriceRange(String code);

	boolean createProduct(ProductDTO dto);

	boolean updateProduct(ProductDTO dto);

	boolean deleteProduct(Integer id);

	List<ProductReviewDTO> findReviewByProductId(Integer productId);

	boolean createReview(ProductReviewDTO dto);

	boolean updateReview(ProductReviewDTO dto);

	boolean deleteReview(Integer id);

	List<ProductDTO> findByCategoryAndActive(Integer categoryId);

	List<ProductDTO> findByCategoryAndPriceCode(Integer categoryId, String code);

	List<ProductDTO> findNewestProducts(int limit);

	List<ProductDTO> findBySellerAndStatuses(Integer sellerId, List<ProductStatus> statuses);
	
	int countPendingProducts();
	
	boolean updateProductStatus(Integer productId, ProductStatus status);



}
