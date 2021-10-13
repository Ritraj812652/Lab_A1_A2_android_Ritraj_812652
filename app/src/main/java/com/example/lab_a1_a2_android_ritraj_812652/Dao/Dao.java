package com.example.lab_a1_a2_android_ritraj_812652.Dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.lab_a1_a2_android_ritraj_812652.Model.Product;
import com.example.lab_a1_a2_android_ritraj_812652.Model.Provider;

import java.util.List;

@androidx.room.Dao
public interface Dao
{

    @Query("SELECT * FROM product")
    List<Product> getAllProducts();


    @Insert
    void insertProduct(Product product);
    @Update
    void updateProduct(Product product);

    @Delete
    void deleteProduct(Product product);

    @Query("SELECT * FROM provider")
    List<Provider> getAllProviders();


    @Query("SELECT * FROM product WHERE provider_phone LIKE :phone AND  provider_name LIKE :name")
    List<Product> getAllProductsWithPhone(String phone,String name);

}
