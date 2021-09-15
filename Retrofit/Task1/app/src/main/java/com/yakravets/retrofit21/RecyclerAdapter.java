package com.yakravets.retrofit21;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yakravets.retrofit21.dto.Product;

import java.util.ArrayList;
import java.util.Collection;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ProductViewHolder>{

    private final ArrayList<Product> products = new ArrayList<>();;

    class ProductViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView price;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
        }

        @SuppressLint("SetTextI18n")
        private void bind(Product product) {
            name.setText(product.getName());
            price.setText(Double.toString(product.getPrice()));
        }
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.bind(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_fragment_item, parent, false);
        return new ProductViewHolder(view);
    }

    public void setItems(Collection<Product> products){
        this.products.addAll(products);
        notifyDataSetChanged();
    }

    public void clearData(){
        this.products.clear();
        notifyDataSetChanged();
    }
}
