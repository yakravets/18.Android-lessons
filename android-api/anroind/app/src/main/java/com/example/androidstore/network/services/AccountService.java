package com.example.androidstore.network.services;

import com.example.androidstore.constans.Urls;
import com.example.androidstore.network.AccountApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AccountService {
    private static AccountService instance;
    private static final String BASE_URL = Urls.BASE;
    private Retrofit retrofit;

    public AccountService() {
        OkHttpClient.Builder client = new OkHttpClient
                .Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
    }

    public static AccountService getInstance() {
        if (instance == null)
            instance = new AccountService();
        return instance;
    }

    public AccountApi getJSONApi()
    {
        return retrofit.create(AccountApi.class);
    }
}
