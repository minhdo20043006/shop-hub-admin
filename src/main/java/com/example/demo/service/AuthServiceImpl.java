package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.apis.APIClient;
import com.example.demo.apis.AuthAPI;
import com.example.demo.dtos.LoginRequestDTO;
import com.example.demo.dtos.LoginResponseDTO;

import jakarta.servlet.http.HttpSession;
import retrofit2.Response;

@Service
public class AuthServiceImpl implements AuthService {

	private final AuthAPI authAPI;
	private final HttpSession session;

	@Autowired
	public AuthServiceImpl(HttpSession session) {
		this.session = session;
		this.authAPI = APIClient.getClient().create(AuthAPI.class);
	}

	@Override
	public LoginResponseDTO login(String username, String password) {
		try {
			LoginRequestDTO request = new LoginRequestDTO(username, password);

			Response<LoginResponseDTO> response = authAPI.login(request).execute();

			if (!response.isSuccessful() || response.body() == null) {
				throw new RuntimeException("Login thất bại");
			}

			LoginResponseDTO data = response.body();
			session.setAttribute("JWT", data.getAccessToken());
			session.setAttribute("ACCOUNT", data.getAccount());
			session.setAttribute("id", data.getAccount().getId());
			session.setAttribute("ROLES", data.getRoles());

			return data;

		} catch (Exception e) {
			throw new RuntimeException("Không thể kết nối Auth API", e);
		}
	}

	@Override
	public Object getCurrentAccount() {
		Object account = session.getAttribute("ACCOUNT");

		if (account == null) {
			throw new RuntimeException("Chưa đăng nhập");
		}

		return account;
	}
}
