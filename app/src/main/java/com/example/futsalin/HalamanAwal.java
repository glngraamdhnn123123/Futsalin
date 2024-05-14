package com.example.futsalin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class HalamanAwal extends AppCompatActivity {

    Button button1;
    TextView textView, textViewButton;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awal);

        textView = findViewById(R.id.textView1);
        textViewButton = findViewById(R.id.buttonregisawal);
        button1 = (Button) findViewById(R.id.button1);
        imageView = findViewById(R.id.imageView4);
        imageView = findViewById(R.id.imageView5);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent button1 = new Intent(getApplicationContext(), HalamanLogin.class);
                startActivity(button1);
            }
        });
        textViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), HalamanRegister.class);
                startActivity(i);
            }
        });
    }
}