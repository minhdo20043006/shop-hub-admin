package com.example.demo.service;

import java.io.File;
import java.util.List;

import com.example.demo.dtos.ProductImagesDTO;

public interface ProductImagesService {
	boolean uploadImages(Integer productId, List<File> files);

	boolean setPrimaryImage(Integer imageId);

	boolean deleteImage(Integer imageId);

	List<ProductImagesDTO> findByProduct(Integer productId);
}
