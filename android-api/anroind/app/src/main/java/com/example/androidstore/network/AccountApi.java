package com.example.androidstore.network;

import com.example.androidstore.dto.RegisterDTO;
import com.example.androidstore.dto.RegisterResultDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AccountApi {
    @POST("/api/account/register")
    public Call<RegisterResultDTO> Registration(@Body RegisterDTO registerDTO);
}
