package com.example.lab_a1_a2_android_ritraj_812652.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab_a1_a2_android_ritraj_812652.Adapter.ProductAdapter;
import com.example.lab_a1_a2_android_ritraj_812652.Dao.Dao;
import com.example.lab_a1_a2_android_ritraj_812652.Database.DataBase;
import com.example.lab_a1_a2_android_ritraj_812652.Model.Product;
import com.example.lab_a1_a2_android_ritraj_812652.R;

import java.util.ArrayList;
import java.util.List;

public class ShowAllProductsActivity extends AppCompatActivity {


    SearchView searchView;
    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    List<Product> products;
    Dao dao;
    String PHONE;
    String NAME;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_products);

        PHONE=getIntent().getExtras().getString("PHONE");
        NAME=getIntent().getExtras().getString("NAME");
        dao= DataBase.getInstance(this).dao();


        searchView=findViewById(R.id.searchView);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setSwipeListener();
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

        if (getSupportActionBar() != null){
            {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
                getSupportActionBar().setTitle("All Products");
            }}
    }


    private void setSwipeListener()
    {


        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                showmDialog(viewHolder.getAdapterPosition());
                productAdapter.notifyDataSetChanged();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    void showmDialog(int adapterPosition)
    {
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.lyt_swip_dialog);
        dialog.show();

        Button btnDelete=dialog.findViewById(R.id.btnDelete);
        Button btnUpdate=dialog.findViewById(R.id.btnUpdate);
        Button btnCall=dialog.findViewById(R.id.btnCall);
        Button btnMessage=dialog.findViewById(R.id.btnMessage);
        Button btnEmail=dialog.findViewById(R.id.btnEmail);
        Button btnLocation=dialog.findViewById(R.id.btnLocation);



        View.OnClickListener onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                dialog.dismiss();


                if(view==btnDelete)
                {
                    dao.deleteProduct(products.get(adapterPosition));
                    products.remove(adapterPosition);
                    productAdapter.notifyDataSetChanged();
                }


                if(view==btnUpdate)
                {
                    Intent n=new Intent(ShowAllProductsActivity.this,AddProductActivity.class);
                    n.putExtra("Product",products.get(adapterPosition));
                    startActivity(n);
                }

                if(view==btnLocation)
                {
                    Intent n=new Intent(ShowAllProductsActivity.this, MapsActivity.class);
                    n.putExtra("Product",products.get(adapterPosition));
                    startActivity(n);
                }

                if(view==btnCall)
                {

                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+products.get(adapterPosition).getProvider_phone()));
                    startActivity(intent);
                }

                if(view==btnMessage)
                {

                    Intent messageIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+products.get(adapterPosition).getProvider_phone()));
                    startActivity(messageIntent);
                }

                if(view==btnEmail)
                {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:"+products.get(adapterPosition).getProvider_email()));
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


    private void filter(String newText)
    {


        products.clear();
        products.addAll(dao.getAllProducts());
        if(!newText.isEmpty())
        {
            List<Product> filteredList=new ArrayList<>();
            for(Product product:products)
            {

                if(product.getName().toLowerCase().contains(newText.toLowerCase()) || product.getDescription().toLowerCase().contains(newText.toLowerCase()))
                {
                    filteredList.add(product);
                }
            }

            products.clear();
            products.addAll(filteredList);

        }
        productAdapter.notifyDataSetChanged();
    }



    private void updateUI()
    {
        products=dao.getAllProductsWithPhone(PHONE,NAME);
        productAdapter=new ProductAdapter(products,this);
        recyclerView.setAdapter(productAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        if(item.getItemId()==android.R.id.home)
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
