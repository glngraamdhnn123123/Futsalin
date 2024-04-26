package com.example.futsalin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class CityDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_detail);

        Intent i = getIntent();
        String name = i.getStringExtra("nama_lapangan");
        String alamat = i.getStringExtra("alamat_lapangan");
        String harga = i.getStringExtra("harga_lapangan");
        String gambar = i.getStringExtra("gambar_lapangan");
    }
}