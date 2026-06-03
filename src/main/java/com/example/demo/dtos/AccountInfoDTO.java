package com.example.demo.dtos;

public class AccountInfoDTO {

	private Integer id;
	private String username;
	private String email;
	private String fullName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public AccountInfoDTO(Integer id, String username, String email, String fullName) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.fullName = fullName;
	}

	public AccountInfoDTO() {
		super();
	}

}
