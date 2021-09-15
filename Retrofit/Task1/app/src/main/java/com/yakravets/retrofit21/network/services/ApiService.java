package com.yakravets.retrofit21.network.services;

import com.yakravets.retrofit21.network.ApiProduct;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    private static ApiService mInstance;
    private static final String BASE_URL = "http://192.168.1.11:5000";
    private Retrofit mRetrofit;

    private ApiService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiService getInstance() {
        if (mInstance == null) {
            mInstance = new ApiService();
        }
        return mInstance;
    }

    public ApiProduct apiProduct()
    {
        return mRetrofit.create(ApiProduct.class);
    }
}
