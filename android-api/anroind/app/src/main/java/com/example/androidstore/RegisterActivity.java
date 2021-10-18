package com.example.androidstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.androidstore.application.HomeApplication;
import com.example.androidstore.dto.ProductDTO;
import com.example.androidstore.dto.RegisterDTO;
import com.example.androidstore.dto.RegisterResultDTO;
import com.example.androidstore.network.services.AccountService;
import com.example.androidstore.network.services.ProductService;
import com.example.androidstore.productview.ProductAdapter;
import com.example.androidstore.utils.CommonUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch(item.getItemId()) {
            case R.id.mhome:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.mregister:
                intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                return true;
            case R.id.mproducts:
                intent = new Intent(this, ProductActivity.class);
                startActivity(intent);
                return true;
            case R.id.mproductadd:
                intent = new Intent(this, ProductAddActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void OnClickButtonRegister(View view)
    {
        final TextInputEditText email = findViewById(R.id.textInputEmail);
        final TextInputLayout passwordLayout = findViewById(R.id.textFieldPassword);
        final TextInputEditText password = findViewById(R.id.textInputPassword);
//        if(phone.getText().toString().isEmpty())
//        {
//            phoneLayout.setError("Не вказали телефон");
//        }
//        else {
//            phoneLayout.setError(null);
//        }
//
//        Log.d("btnRegInfo", email.getText().toString());
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setEmail(email.getText().toString());
        registerDTO.setPassword(password.getText().toString());
        RegisterActivity myActivity = this;
        AccountService.getInstance()
                .getJSONApi()
                .Registration(registerDTO)
                .enqueue(new Callback<RegisterResultDTO>() {
                    @SneakyThrows
                    @Override
                    public void onResponse(Call<RegisterResultDTO> call, Response<RegisterResultDTO> response) {
                        if(!response.isSuccessful()) {
                            String message = response.errorBody().string();
                            String res = "sdfsdf";
                        }
                        RegisterResultDTO myDto = response.body();
                        String data = myDto.getToken();
                    }

                    @Override
                    public void onFailure(Call<RegisterResultDTO> call, Throwable t) {
                        CommonUtils.hideLoading();
                    }
                });



    }

    public void OnClickProductsActivity(View view) {
        Intent intent = new Intent(this, ProductActivity.class);
        startActivity(intent);
    }
}