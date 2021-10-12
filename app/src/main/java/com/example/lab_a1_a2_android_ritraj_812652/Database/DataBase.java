package com.example.lab_a1_a2_android_ritraj_812652.Database;

import android.content.Context;

import androidx.room.Room;

public class DataBase
{

    static AppDatabase db ;
    public  static AppDatabase  getInstance(Context context)
    {

        if(db!=null)
        {
            return db;
        }
        else
        {
            return  db=Room.databaseBuilder(context, AppDatabase.class, "User").allowMainThreadQueries().build();
        }


    }

}
