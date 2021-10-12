package com.example.lab_a1_a2_android_ritraj_812652.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab_a1_a2_android_ritraj_812652.Dao.Dao;
import com.example.lab_a1_a2_android_ritraj_812652.Database.DataBase;
import com.example.lab_a1_a2_android_ritraj_812652.Model.Product;
import com.example.lab_a1_a2_android_ritraj_812652.R;

public class AddProductActivity extends AppCompatActivity  implements  View.OnClickListener{


    EditText editTextProductID;
    EditText editTextProductName;
    EditText editTextProductDescription;
    EditText editTextProductPrice;
    EditText editTextProviderName;
    EditText editTextProviderEmail;
    EditText editTextProviderPhone;
    EditText editTextProviderLat;
    EditText editTextProviderLong;
    Button btnSave;

    Product productToBeEdited;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        if (getSupportActionBar() != null){
            {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
                getSupportActionBar().setTitle("Add Product");
            }}
        initViews();

        if(getIntent().getExtras()!=null)
        {
            handleExtras();

        }
    }

    private void handleExtras()
    {
        productToBeEdited= (Product) getIntent().getExtras().getSerializable("Product");
        editTextProductID.setText(String.valueOf(productToBeEdited.getId()));
        editTextProductName.setText(productToBeEdited.getName());
        editTextProductDescription.setText(productToBeEdited.getDescription());
        editTextProductPrice.setText(String.valueOf(productToBeEdited.getPrice()));
        editTextProviderName.setText(productToBeEdited.getProvider_name());
        editTextProviderEmail.setText(productToBeEdited.getProvider_email());
        editTextProviderPhone.setText(productToBeEdited.getProvider_phone());
        editTextProviderLat.setText(String.valueOf(productToBeEdited.getProvider_lat()));
        editTextProviderLong.setText(String.valueOf(productToBeEdited.getProvider_lng()));
        btnSave.setText("Update");

    }

    private void initViews()
    {

        editTextProductID=findViewById(R.id.editTextProductID);
        editTextProductName=findViewById(R.id.editTextProductName);
        editTextProductDescription=findViewById(R.id.editTextProductDescription);
        editTextProductPrice=findViewById(R.id.editTextProductPrice);
        editTextProviderName=findViewById(R.id.editTextProviderName);
        editTextProviderEmail=findViewById(R.id.editTextProviderEmail);
        editTextProviderPhone=findViewById(R.id.editTextProviderPhone);
        editTextProviderLat=findViewById(R.id.editTextProviderLat);
        editTextProviderLong=findViewById(R.id.editTextProviderLong);
        btnSave=findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

    }


    @Override
    public void onClick(View view)
    {

        if(view==btnSave)
        {

            for(EditText editText:new EditText[]{editTextProductID,editTextProductName,editTextProductDescription,editTextProductPrice,editTextProviderName,editTextProviderEmail,editTextProviderPhone,editTextProviderLat,editTextProviderLong})
            {
                if(editText.getText().toString().isEmpty())
                {
                    editText.setError("Required Field");
                    editText.requestFocus();
                    return;
                }
            }

            Product product=new Product();
            product.setId(Integer.parseInt(editTextProductID.getText().toString()));
            product.setName(editTextProductName.getText().toString());
            product.setDescription(editTextProductDescription.getText().toString());
            product.setPrice(Double.parseDouble(editTextProductPrice.getText().toString()));
            product.setProvider_name(editTextProviderName.getText().toString());
            product.setProvider_email(editTextProviderEmail.getText().toString());
            product.setProvider_phone(editTextProviderPhone.getText().toString());
            product.setProvider_lat(Double.parseDouble(editTextProviderLat.getText().toString()));
            product.setProvider_lng(Double.parseDouble(editTextProviderLong.getText().toString()));


            Dao dao = DataBase.getInstance(this).dao();
            if(productToBeEdited!=null)
            {
                product.setUid(productToBeEdited.getUid());
                dao.updateProduct(product);
                Toast.makeText(this,"Data Updated",Toast.LENGTH_LONG).show();
            }
            else
            {
                dao.insertProduct(product);
                Toast.makeText(this,"Data Added",Toast.LENGTH_LONG).show();
            }
            onBackPressed();
        }

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
