package com.yakravets.retrofit21;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.yakravets.retrofit21.dto.Product;
import com.yakravets.retrofit21.network.services.ApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<String> adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list);
    }

    public void onClickGetData(View view){
        ApiService.getInstance()
            .apiProduct()
            .getProducts()
            .enqueue(
                    new Callback<ArrayList<Product>>() {
                        @Override
                        public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {

                            final ArrayList<String> products = new ArrayList<>();

                            listView.setAdapter(null);
                            adapter = new ArrayAdapter<String>(
                                    getApplicationContext(),
                                    android.R.layout.simple_list_item_1,
                                    products);
                            listView.setAdapter(adapter);

                            for (Product p : response.body()) {
                                products.add(p.getName() + " " + p.getPrice());
                            }
                        }

                        @Override
                        public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
            );

    }
}