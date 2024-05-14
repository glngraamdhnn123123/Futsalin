package com.example.futsalin;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailPesanan extends AppCompatActivity {

    TextView detailNama, detailTanggal, detailWaktu, detailDurasi, detailTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pesanan);

        detailNama = findViewById(R.id.lapPesanan);
        detailTanggal = findViewById(R.id.tanggalPesanan);
        detailWaktu = findViewById(R.id.waktuPesanan);
        detailDurasi = findViewById(R.id.durasiPesanan);
        detailTotal = findViewById(R.id.totalPesanan);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detailNama.setText(bundle.getString("Nama Lapangan"));
            detailTanggal.setText(bundle.getString("Tanggal"));
            detailWaktu.setText(bundle.getString("Waktu"));
            detailDurasi.setText(bundle.getString("Durasi"));
            detailTotal.setText(bundle.getString("NTotal"));
        }

    }
}