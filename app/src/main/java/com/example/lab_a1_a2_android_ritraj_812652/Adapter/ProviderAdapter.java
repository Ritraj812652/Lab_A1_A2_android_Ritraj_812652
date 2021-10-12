package com.example.lab_a1_a2_android_ritraj_812652.Adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab_a1_a2_android_ritraj_812652.Activities.ShowAllProductsActivity;
import com.example.lab_a1_a2_android_ritraj_812652.Model.Provider;
import com.example.lab_a1_a2_android_ritraj_812652.R;
import java.util.List;

public class ProviderAdapter extends RecyclerView.Adapter<ProviderAdapter.ViewHolder> {
    private List<Provider>listData;
    Context context;
    public ProviderAdapter(List<Provider> listData, Context context) {
        this.listData = listData;
        this.context=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lyt_provider_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int i)
    {
        Provider provider=listData.get(i);

        holder.txtProviderName.setText(provider.getProvider_name());
        holder.txtProviderEmail.setText(provider.getProvider_email());
        holder.txtProviderPhone.setText(provider.getProvider_phone());
        holder.txtProviderLat.setText(String.valueOf(provider.getProvider_lat()));
        holder.txtProviderLong.setText(String.valueOf(provider.getProvider_lng()));
        holder.txtCount.setText(String.valueOf(provider.getCount()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n=new Intent(context, ShowAllProductsActivity.class);
                n.putExtra("PHONE",provider.getProvider_phone());
                n.putExtra("NAME",provider.getProvider_name());
                context.startActivity(n);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView txtProviderName;
        private TextView txtProviderEmail;
        private TextView txtProviderPhone;
        private TextView txtProviderLat;
        private TextView txtProviderLong;
        private TextView txtCount;

        public ViewHolder(View itemView)
        {
            super(itemView);


            txtProviderName= itemView.findViewById(R.id.txtProviderName);
            txtProviderEmail= itemView.findViewById(R.id.txtProviderEmail);
            txtProviderPhone= itemView.findViewById(R.id.txtProviderPhone);
            txtProviderLat= itemView.findViewById(R.id.txtProviderLat);
            txtProviderLong= itemView.findViewById(R.id.txtProviderLong);
            txtCount=itemView.findViewById(R.id.txtNum);

        }
    }

}
