package com.yakravets.retrofit21.network;

import com.yakravets.retrofit21.dto.Product;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiProduct {

    @GET("/api/product/v1/all")
    Call<ArrayList<Product>> getProducts();

}
