package com.example.futsalin;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Users {

    private String name;
    private String alamat;
    private String  harga;
    private String  gambar;

    public Users() {
    }

    public Users(String name, String alamat, String harga, String gambar) {
        this.name = name;
        this.alamat = alamat;
        this.harga = harga;
        this.gambar = gambar;
    }

    public String getName() {
        return name;
    }
    public String getAlamat() {
        return alamat;
    }
    public String getGambar() {
        return gambar;
    }
    public String getHarga() {
        return harga;
    }
}
