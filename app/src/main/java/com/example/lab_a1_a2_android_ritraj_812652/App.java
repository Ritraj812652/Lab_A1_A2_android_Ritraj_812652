package com.example.lab_a1_a2_android_ritraj_812652;

import android.app.Application;
import android.content.Context;

import com.example.lab_a1_a2_android_ritraj_812652.Dao.Dao;
import com.example.lab_a1_a2_android_ritraj_812652.Database.DataBase;
import com.example.lab_a1_a2_android_ritraj_812652.Model.Product;

import java.util.Random;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if(isFirstRun(this))
        {
            addData(this);
            getSharedPreferences("com.example.productapp",MODE_PRIVATE).edit().putBoolean("isFirstRun",false).apply();
        }
    }

    private void addData(Context context)
    {
        Dao dao = DataBase.getInstance(context).dao();

        dao.insertProduct(getProduct("Nike Air Max 90","Shoes","160","Nike","support@nike.com","18008066453",28.7041,77.1025));
        dao.insertProduct(getProduct("Nike Pegasus Air Zoom 38","Shoes","170","Nike","support@nike.com","18008066453",28.7041,77.1025));
        dao.insertProduct(getProduct("Nike Air Force 1","Shoes","120","Nike","support@nike.com","18008066453",28.7041,77.1025));
        dao.insertProduct(getProduct("NMD_R1 PrimeBlue","Shoes","180","Adidas","support@adidas.com","18008066444",43.6532,79.3832));
        dao.insertProduct(getProduct("Nike Air Max Plus","Shoes","220","Nike","support@nike.com","18008066453",28.7041,77.1025));
        dao.insertProduct(getProduct("Stan Smith","Shoes","110","Adidas","support@adidas.com","18008066444",43.6532,79.3832));
        dao.insertProduct(getProduct("Superstar","Shoes","100","Adidas","support@adidas.com","18008066444",43.6532,79.3832));
        dao.insertProduct(getProduct("Gazelle","Shoes","100","Adidas","support@adidas.com","18008066444",43.6532,79.3832));
        dao.insertProduct(getProduct("Mercedes F1 RS Connect","Shoes","120","Puma","support@puma.com","18008066555",13.8140,100.0373));
        dao.insertProduct(getProduct("Puma x BALR","Shoes","120","Puma","support@puma.com","18008066555",13.8140,100.0373));

    }

    private Product getProduct(String Name, String desc, String price, String provName, String provEmail, String provphone, double lat, double lng)
    {
        Product product=new Product();
        product.setId(new Random().nextInt());
        product.setName(Name);
        product.setDescription(desc);
        product.setPrice(Double.parseDouble(price));
        product.setProvider_name(provName);
        product.setProvider_email(provEmail);
        product.setProvider_phone(provphone);
        product.setProvider_lat(lat);
        product.setProvider_lng(lng);
        return product;
    }

    private boolean isFirstRun(Context context)
    {
        return context.getSharedPreferences("com.example.productapp",MODE_PRIVATE).getBoolean("isFirstRun",true);



    }
}
