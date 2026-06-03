package com.example.demo.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpSession;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Component
public class JwtInterceptor implements Interceptor {

    @Autowired
    private HttpSession session;

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request original = chain.request();

        String jwt = (String) session.getAttribute("JWT");

        if (jwt != null) {
            Request request = original.newBuilder()
                    .addHeader("Authorization", "Bearer " + jwt)
                    .build();
            return chain.proceed(request);
        }

        return chain.proceed(original);
    }
}
