package com.example.demo.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.apis.APIClient;
import com.example.demo.apis.ProductAPI;
import com.example.demo.apis.ProductImagesAPI;
import com.example.demo.dtos.ProductImagesDTO;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

@Service
public class ProductImagesServiceImpl implements ProductImagesService {

	private final ProductImagesAPI productImagesAPI = APIClient.getClient().create(ProductImagesAPI.class);

	@Override
	public boolean uploadImages(Integer productId, List<File> files) {
		try {
			List<MultipartBody.Part> parts = new ArrayList<>();

			for (File file : files) {
				RequestBody requestFile = RequestBody.create(file, MediaType.parse("image/*"));

				MultipartBody.Part body = MultipartBody.Part.createFormData("files", file.getName(), requestFile);

				parts.add(body);
			}

			Response<String> response = productImagesAPI.uploadImages(productId, parts).execute();

			return response.isSuccessful();

		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean setPrimaryImage(Integer imageId) {
		try {
			Response<String> response = productImagesAPI.setPrimaryImage(imageId).execute();

			return response.isSuccessful();
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteImage(Integer imageId) {
		try {
			Response<String> response = productImagesAPI.deleteImage(imageId).execute();

			return response.isSuccessful();
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<ProductImagesDTO> findByProduct(Integer productId) {
		try {
			Response<List<ProductImagesDTO>> response = productImagesAPI.findByProduct(productId).execute();

			if (response.isSuccessful()) {
				return response.body();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ArrayList<>();
	}
}
