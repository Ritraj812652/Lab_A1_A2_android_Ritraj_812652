package com.example.lab_a1_a2_android_ritraj_812652.Activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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
