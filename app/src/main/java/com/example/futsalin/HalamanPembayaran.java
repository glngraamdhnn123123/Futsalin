package com.example.futsalin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class HalamanPembayaran extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);

        button = findViewById(R.id.buttonBayar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HalamanPembayaran.this, "Pembayaran berhasil, selamat bermain.", Toast.LENGTH_SHORT).show();
                Intent button1 = new Intent(getApplicationContext(), HalamanAkhir.class);
                startActivity(button1);
            }
        });
    }
}