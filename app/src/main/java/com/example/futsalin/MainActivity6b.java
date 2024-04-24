package com.example.futsalin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity6b extends AppCompatActivity {

    Button button1, button2;
    private DatePicker datePicker;
    private RadioGroup durationRadioGroup;
    private TextView totalPriceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity6b);

        button1 = (Button) findViewById(R.id.button_back);
        button2 = (Button) findViewById(R.id.button_order);
        datePicker = findViewById(R.id.datePicker);
        durationRadioGroup = findViewById(R.id.radioGroup_duration);
        totalPriceTextView = findViewById(R.id.text_total_price);

        durationRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                calculateTotalPrice();
            }
        });

        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calculateTotalPrice();
            }
        });

        calculateTotalPrice();
    }

    private void calculateTotalPrice() {
        int selectedRadioButtonId = durationRadioGroup.getCheckedRadioButtonId();

        if (selectedRadioButtonId != -1) {
            int totalPrice = 0;

            if (selectedRadioButtonId == R.id.radio_1_hour) {
                totalPrice = 150000;
            }
            else if (selectedRadioButtonId == R.id.radio_2_hours) {
                totalPrice = 300000;
            }

            totalPriceTextView.setText("Total Harga: Rp. " + totalPrice);
        }
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent button1 = new Intent(getApplicationContext(), MainActivity5.class);
                startActivity(button1);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent button2 = new Intent(getApplicationContext(), MainActivity7.class);
                startActivity(button2);
            }
        });
    }
}