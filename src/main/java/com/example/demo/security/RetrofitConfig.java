package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class RetrofitConfig {

    @Bean
    public Retrofit retrofit(JwtInterceptor jwtInterceptor) {

        OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(jwtInterceptor)
            .build();

        return new Retrofit.Builder()
            .baseUrl("http://localhost:8081/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();
    }
}
