package com.example.futsalin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class HalamanBerhasilRegis extends AppCompatActivity {
    Button button66;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berhasil_regis);

        button66 = (Button) findViewById(R.id.button66);

        button66.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent button1 = new Intent(getApplicationContext(), HalamanLogin.class);
                startActivity(button1);
            }
        });
    }
}