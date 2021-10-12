package com.example.lab_a1_a2_android_ritraj_812652.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab_a1_a2_android_ritraj_812652.Model.Product;
import com.example.lab_a1_a2_android_ritraj_812652.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<Product>listData;
    Context context;
    public ProductAdapter(List<Product> listData, Context context) {
        this.listData = listData;
        this.context=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lyt_product_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int i)
    {
        Product product=listData.get(i);

        holder.txtProductID.setText(String.valueOf(product.getId()));
        holder.txtProductName.setText(product.getName());
        holder.txtProductDesc.setText(product.getDescription());
        holder.txtProductPrice.setText(product.getPrice()+" $ ");
        holder.txtProviderName.setText(product.getProvider_name());
        holder.txtProviderEmail.setText(product.getProvider_email());
        holder.txtProviderPhone.setText(product.getProvider_phone());
        holder.txtProviderLat.setText(String.valueOf(product.getProvider_lat()));
        holder.txtProviderLong.setText(String.valueOf(product.getProvider_lng()));

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView txtProductID;
        private TextView txtProductName;
        private TextView txtProductDesc;
        private TextView txtProductPrice;
        private TextView txtProviderName;
        private TextView txtProviderEmail;
        private TextView txtProviderPhone;
        private TextView txtProviderLat;
        private TextView txtProviderLong;

        public ViewHolder(View itemView)
        {
            super(itemView);

            txtProductID= itemView.findViewById(R.id.txtProductID);
            txtProductName= itemView.findViewById(R.id.txtProductName);
            txtProductDesc= itemView.findViewById(R.id.txtProductDesc);
            txtProductPrice= itemView.findViewById(R.id.txtProductPrice);
            txtProviderName= itemView.findViewById(R.id.txtProviderName);
            txtProviderEmail= itemView.findViewById(R.id.txtProviderEmail);
            txtProviderPhone= itemView.findViewById(R.id.txtProviderPhone);
            txtProviderLat= itemView.findViewById(R.id.txtProviderLat);
            txtProviderLong= itemView.findViewById(R.id.txtProviderLong);

        }
    }

}

