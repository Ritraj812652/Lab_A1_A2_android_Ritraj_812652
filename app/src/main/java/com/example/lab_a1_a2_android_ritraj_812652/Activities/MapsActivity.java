package com.example.lab_a1_a2_android_ritraj_812652.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.lab_a1_a2_android_ritraj_812652.Model.Product;
import com.example.lab_a1_a2_android_ritraj_812652.Model.Provider;
import com.example.lab_a1_a2_android_ritraj_812652.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Product product;
    Provider provider;
    double lattitude;
    double longitude;
    String ProviderName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.


        if(getIntent().getExtras()!=null)
        {

            if(getIntent().getExtras().getSerializable("Product")!=null)
            {
                product= (Product) getIntent().getExtras().getSerializable("Product");
                lattitude=product.getProvider_lat();
                longitude=product.getProvider_lng();
                ProviderName=product.getProvider_name();

            }
            if(getIntent().getExtras().getSerializable("Provider")!=null)
            {
                provider= (Provider) getIntent().getExtras().getSerializable("Provider");
                lattitude=provider.getProvider_lat();
                longitude=provider.getProvider_lng();
                ProviderName=provider.getProvider_name();
            }


        }


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (getSupportActionBar() != null){
            {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
                getSupportActionBar().setTitle("Provider Location");
            }}
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        updateUI();
    }


    private void updateUI()
    {

        if(mMap!=null)
        {
            LatLng latLng = new LatLng(lattitude,longitude);
            mMap.addMarker(new MarkerOptions().position(latLng).title(ProviderName));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
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