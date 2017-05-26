package com.usearch.adapters;


import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.usearch.R;
import com.usearch.models.Product;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class HistoryAdapter extends RealmRecyclerViewAdapter<Product, HistoryAdapter.MyViewHolder > {

    private Context context;

    public HistoryAdapter(Context context, @Nullable OrderedRealmCollection<Product> data, boolean autoUpdate) {
        super(data, autoUpdate);
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.item_product, parent);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Product product = getData().get(position);
        holder.bind(product);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName;
        private TextView txtPrice;
        private Product product;

        public MyViewHolder(View itemView) {
            super(itemView);

            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);
        }

        public void bind(Product product){
            this.product = product;
            txtName.setText(product.getName());
            txtPrice.setText(String.format("R$ %.2f", product.getPrice()));
        }
    }

}
