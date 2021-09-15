package com.yakravets.retrofit21.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.yakravets.retrofit21.R;
import com.yakravets.retrofit21.RecyclerAdapter;
import com.yakravets.retrofit21.dto.Product;
import com.yakravets.retrofit21.network.services.ApiService;

import java.util.ArrayList;
import java.util.Collection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private RecyclerAdapter recyclerAdapter;

    public static Fragment newInstance() {
        return new RecyclerFragment();
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_fragment, container, false);

        Button btn = view.findViewById(R.id.btn2);
        btn.setOnClickListener(this::onClickGetData);

        progressBar = view.findViewById(R.id.pBar);

        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerAdapter = new RecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);
        return view;
    }

    private void onClickGetData(View view){

        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        ApiService.getInstance()
                .apiProduct()
                .getProducts()
                .enqueue(
                        new Callback<ArrayList<Product>>() {
                            @Override
                            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                                Collection<Product> products = response.body();
                                recyclerAdapter.setItems(products);
                                recyclerView.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.GONE);
                            }

                            @Override
                            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
                                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                );
    }
}


