package com.example.demo.service;

import java.util.List;

import com.example.demo.dtos.AccountDTO;
import com.example.demo.entities.Account;

public interface AccountService {
	public List<AccountDTO> findAll();
	
	public Account findById(Integer id);
	
	public boolean create(AccountDTO accountDto);

	public boolean update(AccountDTO accountDTO);

	public boolean delete(Integer id);
}
