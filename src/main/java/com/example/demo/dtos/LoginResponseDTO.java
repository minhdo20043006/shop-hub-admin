package com.example.demo.dtos;

import java.util.List;

public class LoginResponseDTO {

    private String accessToken;
    private List<String> roles;
    private AccountInfoDTO account;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public AccountInfoDTO getAccount() {
        return account;
    }

    public void setAccount(AccountInfoDTO account) {
        this.account = account;
    }
}
