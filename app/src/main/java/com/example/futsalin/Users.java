package com.example.futsalin;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Users {

    private String name;
    private String alamat;
    private String  harga;

    public Users() {
    }

    public Users(String name, String alamat, String harga) {
        this.name = name;
        this.alamat = alamat;
        this.harga = harga;
    }

    public String getName() {
        return name;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getHarga() {
        return harga;
    }
}
