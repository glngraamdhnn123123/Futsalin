package com.example.futsalin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class HalamanTanggal extends AppCompatActivity {
    Button button1, button2;
    DatabaseReference databaseReference;
    RadioButton durasi;
    private DatePicker datePicker;
    private RadioGroup durationRadioGroup;
    private TextView total, namalapDetail, alamatDetail, hargaDetail;
    private TextInputLayout tanggalPesan, waktuPesan;
    private ShapeableImageView gambarDetail;
    private TimePicker timePicker;

    Context context;

    private EditText tanggal, waktu;
    int day, month, year, hour, minute;
    int myday, myMonth, myYear, myHour, myMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanggal);

        // button1 = (Button) findViewById(R.id.button_back);
        button2 = (Button) findViewById(R.id.button_order);
        // datePicker = findViewById(R.id.datePicker);
        durationRadioGroup = findViewById(R.id.rb_jam);
        total = findViewById(R.id.text_total_price);
        tanggalPesan = findViewById(R.id.edittext_tanggal);
        tanggal = findViewById(R.id.tanggal_pesan);
        waktuPesan = findViewById(R.id.edittext_waktu);
        waktu = findViewById(R.id.waktu_pesan);
        namalapDetail = findViewById(R.id.namaLap_detail);
        hargaDetail = findViewById(R.id.hargaLap);
        alamatDetail = findViewById(R.id.alamatLap_detail);
        gambarDetail = findViewById(R.id.gambarLap);
        durasi = findViewById(R.id.rb_1);
        durasi = findViewById(R.id.rb_2);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            namalapDetail.setText(bundle.getString("nama_lapangan"));
            alamatDetail.setText(bundle.getString("alamat_lapangan"));
            hargaDetail.setText(bundle.getString("harga_lapangan"));
            String urlLapangan = bundle.getString("gambar_lapangan");
            Glide.with(this).load(urlLapangan).into(gambarDetail);
        }

        durationRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                calculateTotalPrice();
            }
        });

//        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
//            @Override
//            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                calculateTotalPrice();
//            }
//        });

        calculateTotalPrice();
    }

    private void calculateTotalPrice () {
        Bundle bundleGet = getIntent().getExtras();
        String hargaAwal = bundleGet.getString("harga_lapangan");
        hargaAwal = hargaAwal.replaceAll("[^\\d]", "");

        int hargaAsli = Integer.parseInt(hargaAwal);
        int selectedRadioButtonId = durationRadioGroup.getCheckedRadioButtonId();

        if (selectedRadioButtonId != 0) {
            int totalPrice = 0;
            if (selectedRadioButtonId == R.id.rb_1) {
                totalPrice = hargaAsli * 1;
            } else if (selectedRadioButtonId == R.id.rb_2) {
                totalPrice = hargaAsli * 2;
            }
            total.setText("Total Harga: Rp. " + totalPrice);
        }

        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(HalamanTanggal.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        myYear = year;
                        myday = dayOfMonth;
                        myMonth = month + 1;
                        String formattedDay = String.format("%02d", myday);
                        String formattedMonth = String.format("%02d", myMonth);
                        tanggal.setText(formattedDay + "/" + formattedMonth + "/" + myYear);

                        //openTimePicker();
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        waktu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR_OF_DAY);
                minute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(HalamanTanggal.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        myHour = hourOfDay;
                        myMinute = minute;
                        String formattedHour = String.format("%02d", myHour);
                        String formattedMinute = String.format("%02d", myMinute);
                        waktu.setText(formattedHour + ":" + formattedMinute);
                    }
                }, hour, minute, DateFormat.is24HourFormat(HalamanTanggal.this));
                timePickerDialog.show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertData();

                String pilihtanggal, pilihwaktu, pilihdurasi;
                pilihtanggal = String.valueOf(tanggal.getText());
                pilihwaktu = String.valueOf(waktu.getText());
                pilihdurasi = String.valueOf(durasi.getText());

                if (TextUtils.isEmpty(pilihtanggal)){
                    Toast.makeText(HalamanTanggal.this, "Pilih tanggal", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(pilihwaktu)){
                    Toast.makeText(HalamanTanggal.this, "Pilih waktu", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(pilihdurasi)){
                    Toast.makeText(HalamanTanggal.this, "Pilih durasi", Toast.LENGTH_SHORT).show();
                    return;
                }
                
            }
        });
    }
    private void InsertData () {
        String name = namalapDetail.getText().toString();
        String pilihtanggal = tanggal.getText().toString();
        String pilihwaktu = waktu.getText().toString();
        String pilihdurasi = durasi.getText().toString();
        String totalbayar = total.getText().toString();
        String iduser = databaseReference.push().getKey();

        UserDate userDate = new UserDate(name, pilihtanggal, pilihwaktu, pilihdurasi, totalbayar);
        databaseReference.child("order").child(iduser).setValue(userDate)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(HalamanTanggal.this, "Sukses", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), HalamanPembayaran.class);
                            startActivity(i);
                            finish();
                        }
                    }
                });
    }
}