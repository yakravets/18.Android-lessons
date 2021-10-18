package com.example.androidstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.androidstore.dto.ProductDTO;
import com.example.androidstore.dto.ProductImageDTO;
import com.example.androidstore.network.services.ProductService;
import com.example.androidstore.productview.ProductAdapter;
import com.example.androidstore.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {

    private ProductAdapter adapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        recyclerView = findViewById(R.id.rcv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1,
                LinearLayoutManager.VERTICAL, false));
        CommonUtils.showLoading(this);
        ProductActivity myActivity = this;
        ProductService.getInstance()
                .getProductsApi()
                .all()
                .enqueue(new Callback<List<ProductDTO>>() {
                    @Override
                    public void onResponse(Call<List<ProductDTO>> call, Response<List<ProductDTO>> response) {
                        List<ProductDTO> list = response.body();
                        adapter = new ProductAdapter(list);
                        recyclerView.setAdapter(adapter);
                        CommonUtils.hideLoading();
                    }

                    @Override
                    public void onFailure(Call<List<ProductDTO>> call, Throwable t) {
                        CommonUtils.hideLoading();
                    }
                });
//        List<ProductDTO> list= new ArrayList<>();
//        list.add(new ProductDTO("Привіт", 23.56, "1.jpg"));
//        list.add(new ProductDTO("Сало", 78.35, "1.jpg"));
//        adapter = new ProductAdapter(list);
//        recyclerView.setAdapter(adapter);
    }
}