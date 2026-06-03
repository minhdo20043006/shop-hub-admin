package com.example.demo.service;

import com.example.demo.dtos.AccountInfoDTO;
import com.example.demo.dtos.LoginResponseDTO;

public interface AuthService {
	public LoginResponseDTO login(String username, String password);
	public Object getCurrentAccount();
	

}
