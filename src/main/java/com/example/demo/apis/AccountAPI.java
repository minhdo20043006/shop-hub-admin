package com.example.demo.apis;

import java.util.List;
import java.util.Map;

import com.example.demo.dtos.AccountDTO;
import com.example.demo.entities.Account;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AccountAPI {
	@GET("account/ad/find-all")
	Call<List<AccountDTO>> findAll();

	@GET("account/all/find-by-id/{id}")
	Call<Account> findById(@Path("id") Integer id);

	@POST("account/all/create")
	Call<Void> create(@Body AccountDTO accountDTO);

	@PUT("account/all/update")
	Call<Void> update(@Body AccountDTO accountDTO);

	@DELETE("account/all/delete/{id}")
	Call<Void> delete(@Path("id") Integer id);

}
