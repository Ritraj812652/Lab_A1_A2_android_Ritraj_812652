package com.example.lab_a1_a2_android_ritraj_812652.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Product implements Serializable
{

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "price")
    public double price;


    @ColumnInfo(name = "provider_name")
    public String provider_name;

    @ColumnInfo(name = "provider_email")
    public String provider_email;

    @ColumnInfo(name = "provider_phone")
    public String provider_phone;

    @ColumnInfo(name = "provider_lat")
    public double provider_lat;

    @ColumnInfo(name = "provider_lng")
    public double provider_lng;



    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public double getProvider_lng() {
        return provider_lng;
    }

    public void setProvider_lng(double provider_lng) {
        this.provider_lng = provider_lng;
    }


}
