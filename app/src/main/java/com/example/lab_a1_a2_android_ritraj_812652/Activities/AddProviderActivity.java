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
import com.example.lab_a1_a2_android_ritraj_812652.Model.Provider;
import com.example.lab_a1_a2_android_ritraj_812652.R;

public class AddProviderActivity extends AppCompatActivity implements  View.OnClickListener{


    EditText editTextProviderName;
    EditText editTextProviderEmail;
    EditText editTextProviderPhone;
    EditText editTextProviderLat;
    EditText editTextProviderLong;
    Button btnSave;

    Provider providerToBeEdited;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_provider);
        if (getSupportActionBar() != null){
            {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
                getSupportActionBar().setTitle("Add Provider");
            }}
        initViews();

        if(getIntent().getExtras()!=null)
        {
            handleExtras();
        }
    }

    private void handleExtras()
    {

        providerToBeEdited= (Provider) getIntent().getExtras().getSerializable("Provider");
        editTextProviderName.setText(providerToBeEdited.getProvider_name());
        editTextProviderEmail.setText(providerToBeEdited.getProvider_email());
        editTextProviderPhone.setText(providerToBeEdited.getProvider_phone());
        editTextProviderLat.setText(String.valueOf(providerToBeEdited.getProvider_lat()));
        editTextProviderLong.setText(String.valueOf(providerToBeEdited.getProvider_lng()));
        btnSave.setText("Update");
    }

    private void initViews()
    {

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

            for(EditText editText:new EditText[]{editTextProviderName,editTextProviderEmail,editTextProviderPhone,editTextProviderLat,editTextProviderLong})
            {
                if(editText.getText().toString().isEmpty())
                {
                    editText.setError("Required Field");
                    editText.requestFocus();
                    return;
                }
            }

            Provider provider=new Provider();

            provider.setProvider_name(editTextProviderName.getText().toString());
            provider.setProvider_email(editTextProviderEmail.getText().toString());
            provider.setProvider_phone(editTextProviderPhone.getText().toString());
            provider.setProvider_lat(Double.parseDouble(editTextProviderLat.getText().toString()));
            provider.setProvider_lng(Double.parseDouble(editTextProviderLong.getText().toString()));
            Dao dao = DataBase.getInstance(this).dao();

            if(providerToBeEdited!=null)
            {

                provider.setUid(providerToBeEdited.getUid());
              //  dao.updateProvider(provider);
                Toast.makeText(this,"Data Updated",Toast.LENGTH_LONG).show();
            }
            else
            {

              //  dao.insertProvider(provider);
                Toast.makeText(this,"Data Added",Toast.LENGTH_LONG).show();
            }




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