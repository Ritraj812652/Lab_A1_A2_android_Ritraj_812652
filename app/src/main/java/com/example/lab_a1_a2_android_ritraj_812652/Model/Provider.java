package com.example.lab_a1_a2_android_ritraj_812652.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Provider implements Serializable
{

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "id")
    public String id;


    @ColumnInfo(name = "provider_name")
    public String provider_name;

    @ColumnInfo(name = "provider_email")
    public String provider_email;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvider_name() {
        return provider_name;
    }

    public void setProvider_name(String provider_name) {
        this.provider_name = provider_name;
    }

    public String getProvider_email() {
        return provider_email;
    }

    public void setProvider_email(String provider_email) {
        this.provider_email = provider_email;
    }

    public String getProvider_phone() {
        return provider_phone;
    }

    public void setProvider_phone(String provider_phone) {
        this.provider_phone = provider_phone;
    }

    public double getProvider_lat() {
        return provider_lat;
    }

    public void setProvider_lat(double provider_lat) {
        this.provider_lat = provider_lat;
    }



    @ColumnInfo(name = "provider_phone")
    public String provider_phone;

    @ColumnInfo(name = "provider_lat")
    public double provider_lat;

    public double getProvider_lng() {
        return provider_lng;
    }

    public void setProvider_lng(double provider_lng) {
        this.provider_lng = provider_lng;
    }

    @ColumnInfo(name = "provider_lng")
    public double provider_lng;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    int count;


}

