package com.example.lab_a1_a2_android_ritraj_812652.Database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.lab_a1_a2_android_ritraj_812652.Dao.Dao;
import com.example.lab_a1_a2_android_ritraj_812652.Model.Product;
import com.example.lab_a1_a2_android_ritraj_812652.Model.Provider;

@Database(entities = {Product.class, Provider.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract Dao dao();
}
