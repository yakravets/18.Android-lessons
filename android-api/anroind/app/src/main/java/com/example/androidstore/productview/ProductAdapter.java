package com.example.androidstore.productview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.NetworkImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.androidstore.application.HomeApplication;
import com.example.androidstore.constans.Urls;
import com.example.androidstore.dto.ProductDTO;
import com.example.androidstore.network.ImageRequester;
import com.example.androidstore.R;

import java.util.Date;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductCardViewHolder> {
    private List<ProductDTO> modelList;
    private ImageRequester imageRequester;

    public ProductAdapter(List<ProductDTO> modelList) {
        this.modelList = modelList;
        imageRequester = ImageRequester.getInstance();
    }

    @NonNull
    @Override
    public ProductCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater
                .from(parent.getContext()).inflate(R.layout.product_card, parent, false);
        return new ProductCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCardViewHolder holder, int position) {
        if (modelList != null && position < modelList.size()) {
            ProductDTO model = modelList.get(position);
            holder.prodName.setText(model.getName());
            holder.prodPrice.setText(String.valueOf(model.getPrice()));
            int i = (int) (new Date().getTime()/1000);
            String url = Urls.BASE+"/images/" + model.getImage()+"?data="+i;
            //HomeApplication.getAppContext();
            Glide.with(HomeApplication.getAppContext())
                    .load(url)
                    .circleCrop()
                    .apply(new RequestOptions().override(300, 300))
                .into(holder.prodImage);
//            imageRequester.setImageFromUrl(holder.prodImage, url);
        }
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
