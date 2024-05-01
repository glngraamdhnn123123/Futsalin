package com.example.futsalin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class MainActivity6a extends AppCompatActivity {
    Button button1, button2;
    private DatePicker datePicker;
    private RadioGroup durationRadioGroup;
    private TextView totalPriceTextView, namalapDetail, alamatDetail, hargaDetail;
    private TextInputLayout tanggalPesan;
    private EditText tanggal_pesan;
    int day, month, year, hour, minute;
    int myday, myMonth, myYear, myHour, myMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity6a);

      // button1 = (Button) findViewById(R.id.button_back);
        button2 = (Button) findViewById(R.id.button_order);
       // datePicker = findViewById(R.id.datePicker);
        durationRadioGroup = findViewById(R.id.rb_jam);
        totalPriceTextView = findViewById(R.id.text_total_price);
        tanggalPesan = findViewById(R.id.edittext_tanggal);
        tanggal_pesan = findViewById(R.id.tanggal_pesan);
        namalapDetail = findViewById(R.id.namaLap_detail);
        hargaDetail = findViewById(R.id.hargaLap);
        alamatDetail = findViewById(R.id.alamatLap_detail);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            namalapDetail.setText(bundle.getString("nama_lapangan"));
            alamatDetail.setText(bundle.getString("alamat_lapangan"));
            hargaDetail.setText(bundle.getString("harga_lapangan"));
        }
//        durationRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                calculateTotalPrice();
//            }
//        });

//        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
//            @Override
//            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                calculateTotalPrice();
//            }
//        });

        calculateTotalPrice();
    }

    private void calculateTotalPrice() {
        int selectedRadioButtonId = durationRadioGroup.getCheckedRadioButtonId();

        if (selectedRadioButtonId != -1) {
            int totalPrice = 0;

            if (selectedRadioButtonId == R.id.rb_1) {
                totalPrice = 200000;
            }
            else if (selectedRadioButtonId == R.id.rb_2) {
                totalPrice = 400000;
            }
            totalPriceTextView.setText("Total Harga: Rp. " + totalPrice);
        }

        tanggal_pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity6a.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        myYear = year;
                        myday = dayOfMonth;
                        myMonth = month;
                        tanggal_pesan.setText(myday + "/" + myMonth + "/" + myYear);

                        //openTimePicker();
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    private void openTimePicker() {
        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity6a.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                myHour = hourOfDay;
                myMinute = minute;
                tanggal_pesan.setText(myYear + "/" + myMonth + "/" + myday + "/" + myHour + "/" + myMinute);
            }
        }, hour, minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }
}