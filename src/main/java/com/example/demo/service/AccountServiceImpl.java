package com.example.demo.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.apis.APIClient;
import com.example.demo.apis.AccountAPI;

import com.example.demo.dtos.AccountDTO;
import com.example.demo.entities.Account;
import com.example.demo.enums.StatusAccount;

import retrofit2.Response;

@Service
public class AccountServiceImpl implements AccountService {

	private final AccountAPI accountAPI = APIClient.getClient().create(AccountAPI.class);

	@Override
	public List<AccountDTO> findAll() {
		try {
			Response<List<AccountDTO>> res = accountAPI.findAll().execute();

			if (!res.isSuccessful()) {
				throw new RuntimeException("Không lấy được account");
			}

			return res.body();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean create(AccountDTO accountDto) {
		try {
			if (accountDto == null) {
				return false;
			}

			Response<Void> response = accountAPI.create(accountDto).execute();

			return response.isSuccessful();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(AccountDTO accountDTO) {
		try {
			Response<Void> res = accountAPI.update(accountDTO).execute();
			return res.isSuccessful();
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean delete(Integer id) {
		try {
			Response<Void> res = accountAPI.delete(id).execute();
			return res.isSuccessful();
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Account findById(Integer id) {
		try {
			Response<Account> res = accountAPI.findById(id).execute();

			if (!res.isSuccessful()) {
				throw new RuntimeException("Không lấy được account");
			}

			return res.body();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
