package com.example.lab_a1_a2_android_ritraj_812652.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lab_a1_a2_android_ritraj_812652.Adapter.ProviderAdapter;
import com.example.lab_a1_a2_android_ritraj_812652.Dao.Dao;
import com.example.lab_a1_a2_android_ritraj_812652.Database.DataBase;
import com.example.lab_a1_a2_android_ritraj_812652.Model.Product;
import com.example.lab_a1_a2_android_ritraj_812652.Model.Provider;
import com.example.lab_a1_a2_android_ritraj_812652.R;

import java.util.ArrayList;
import java.util.List;

public class ProviderFragment extends Fragment  {




    RecyclerView recyclerView;
    ProviderAdapter providerAdapter;
    List<Provider> providerList=new ArrayList<>();
    Dao dao;
    androidx.appcompat.widget.SearchView searchView;
    List<Provider> Original;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_provider, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    private void initViews(View view)
    {

        dao= DataBase.getInstance(getActivity()).dao();

        recyclerView=view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchView=view.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });

        //  setSwipeListener();

    }

    private void filter(String newText)
    {

        providerList.clear();
        providerList.addAll(Original);
        if(!newText.isEmpty())
        {
            List<Provider> filteredList=new ArrayList<>();
            for(Provider provider:providerList)
            {

                if(provider.getProvider_name().toLowerCase().contains(newText.toLowerCase()))
                {
                    filteredList.add(provider);
                }
            }

            providerList.clear();
            providerList.addAll(filteredList);

        }
        providerAdapter.notifyDataSetChanged();
    }


   /* private void setSwipeListener()
    {


        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                showDialog(viewHolder.getAdapterPosition());
                providerAdapter.notifyDataSetChanged();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    private void showDialog(int adapterPosition)
    {
        Dialog dialog=new Dialog(getActivity());
        dialog.setContentView(R.layout.lyt_swip_dialog);
        dialog.show();

        Button btnDelete=dialog.findViewById(R.id.btnDelete);
        Button btnUpdate=dialog.findViewById(R.id.btnUpdate);
        Button btnCall=dialog.findViewById(R.id.btnCall);
        Button btnMessage=dialog.findViewById(R.id.btnMessage);
        Button btnEmail=dialog.findViewById(R.id.btnEmail);
        Button btnLocation=dialog.findViewById(R.id.btnLocation);
        btnDelete.setVisibility(View.GONE);


        View.OnClickListener onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                dialog.dismiss();


                if(view==btnDelete)
                {


                    dao.deleteProvider(providerList.get(adapterPosition));
                    providerList.remove(adapterPosition);
                    providerAdapter.notifyDataSetChanged();
                }


                if(view==btnUpdate)
                {
                    Intent n=new Intent(getActivity(),AddProviderActivity.class);
                    n.putExtra("Provider",providerList.get(adapterPosition));
                    startActivity(n);
                }

                if(view==btnLocation)
                {
                    Intent n=new Intent(getActivity(), MapsActivity.class);
                    n.putExtra("Provider",providerList.get(adapterPosition));
                    startActivity(n);
                }

                if(view==btnCall)
                {

                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+providerList.get(adapterPosition).getProvider_phone()));
                    startActivity(intent);
                }

                if(view==btnMessage)
                {

                    Intent messageIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+providerList.get(adapterPosition).getProvider_phone()));
                    startActivity(messageIntent);
                }

                if(view==btnEmail)
                {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:"+providerList.get(adapterPosition).getProvider_email()));
                    intent.putExtra(Intent.EXTRA_EMAIL,"");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "");
                    startActivity(intent);
                }


            }
        };


        btnDelete.setOnClickListener(onClickListener);
        btnUpdate.setOnClickListener(onClickListener);
        btnCall.setOnClickListener(onClickListener);
        btnMessage.setOnClickListener(onClickListener);
        btnEmail.setOnClickListener(onClickListener);
        btnLocation.setOnClickListener(onClickListener);


    }

    */


    @Override
    public void setMenuVisibility(final boolean visible) {
        if (visible) {
            updateUI();
        }
        super.setMenuVisibility(visible);
    }

    private void updateUI()
    {
        providerList.clear();
        for(Product product:dao.getAllProducts())
        {

            if(!contains(product))
            {
                Provider provider=new Provider();
                provider.setProvider_name(product.getProvider_name());
                provider.setProvider_phone(product.getProvider_phone());
                provider.setProvider_email(product.getProvider_email());
                provider.setProvider_lat(product.getProvider_lat());
                provider.setProvider_lng(product.getProvider_lng());
                providerList.add(provider);
            }

        }
        putcount(providerList);
        providerAdapter =new ProviderAdapter(providerList,getActivity());
        recyclerView.setAdapter(providerAdapter);
    }

    private boolean contains(Product product)
    {

        for(Provider provider:providerList)
        {
            if(
                    provider.getProvider_name().equalsIgnoreCase(product.getProvider_name()) &&
                            provider.getProvider_phone().equalsIgnoreCase(product.getProvider_phone()))
            {
                return  true;
            }

        }
        return false;
    }

    private void putcount(List<Provider> providerList)
    {

        Original=new ArrayList<>();
        for(Provider provider:providerList)
        {

            List<Product> allProductsWithPhone = dao.getAllProductsWithPhone(provider.getProvider_phone(),provider.getProvider_name());
            provider.setCount(allProductsWithPhone.size());
            Original.add(provider);
        }

        providerList.clear();
        providerList.addAll(Original);

    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }


}