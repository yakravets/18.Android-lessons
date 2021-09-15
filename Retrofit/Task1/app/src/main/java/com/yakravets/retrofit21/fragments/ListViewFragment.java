package com.yakravets.retrofit21.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.yakravets.retrofit21.R;
import com.yakravets.retrofit21.dto.Product;
import com.yakravets.retrofit21.network.services.ApiService;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListViewFragment extends Fragment {

    private ArrayAdapter<String> adapter;
    private ListView listView;
    private ProgressBar progressBar;
    private ArrayList<String> products;

    public static Fragment newInstance() {
        return new ListViewFragment();
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_view_fragment, container, false);

        Button btn = view.findViewById(R.id.btn1);
        btn.setOnClickListener(this::onClickGetData);

        listView = view.findViewById(R.id.list);
        progressBar = view.findViewById(R.id.pBar);

        products = new ArrayList<>();
        adapter = new ArrayAdapter<>(
                view.getContext(),
                android.R.layout.simple_list_item_1,
                products);

        return view;
    }

    private void onClickGetData(View view){

        listView.setVisibility(View.GONE);
        listView.setAdapter(null);
        products.clear();

        progressBar.setVisibility(View.VISIBLE);

        ApiService.getInstance()
            .apiProduct()
            .getProducts()
            .enqueue(
                    new Callback<ArrayList<Product>>() {
                        @Override
                        public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {

                            listView.setAdapter(adapter);

                            for (Product p : Objects.requireNonNull(response.body())) {
                                products.add(p.getName() + " " + p.getPrice());
                            }

                            listView.setVisibility(View.VISIBLE);
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
