 package com.example.futsalin;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

 public class HalamanRiwayat extends AppCompatActivity {

    RecyclerView recyclerView;
    List<UserDate> dataList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;

     @Override
     public void onBackPressed() {
         super.onBackPressed();
         startActivity(new Intent(HalamanRiwayat.this, HalamanAkhir.class));
         finish();
     }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_pesanan);

        recyclerView = findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(HalamanRiwayat.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(HalamanRiwayat.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progres_layout_pesanan);
        AlertDialog dialog = builder.create();
        dialog.show();

        dataList = new ArrayList<>();

        AdapterPesanan adapterPesanan = new AdapterPesanan(HalamanRiwayat.this, dataList);
        recyclerView.setAdapter(adapterPesanan);

        databaseReference = FirebaseDatabase.getInstance().getReference("order");
        dialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    UserDate userDate = itemSnapshot.getValue(UserDate.class);
                    dataList.add(userDate);
                }
                adapterPesanan.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });
    }
}