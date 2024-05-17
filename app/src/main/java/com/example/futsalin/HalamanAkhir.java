package com.example.futsalin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class HalamanAkhir extends AppCompatActivity {
    ImageView home, order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akhir);

        home = findViewById(R.id.imageHome);
        order = findViewById(R.id.imageOrder);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent button1 = new Intent(getApplicationContext(), HalamanLapangan.class);
                startActivity(button1);
                finish();
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent button2 = new Intent(getApplicationContext(), HalamanRiwayat.class);
                startActivity(button2);
                finish();
            }
        });
    }
}