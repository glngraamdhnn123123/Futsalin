package com.example.futsalin;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class UserDate {
    private String name;
    private String tanggal;
    private String waktu;
    private String durasi;
    private String total;

    public UserDate() {
    }

    public UserDate(String name, String tanggal, String waktu, String durasi, String total) {
        this.name = name;
        this.tanggal = tanggal;
        this.waktu = waktu;
        this.durasi = durasi;
        this.total = total;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getWaktu() {
        return waktu;
    }

    public String getDurasi() {
        return durasi;
    }
    public String getTotal() {
        return total;
    }
    public String getName() {
        return name;
    }
}
