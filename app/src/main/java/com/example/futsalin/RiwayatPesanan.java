 package com.example.futsalin;

import android.os.Bundle;
import android.view.Gravity;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

 public class RiwayatPesanan extends AppCompatActivity {

    RecyclerView recyclerView;
    List<UserDate> dataList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_pesanan);

        recyclerView = findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(RiwayatPesanan.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(RiwayatPesanan.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progres_layout_pesanan);
        AlertDialog dialog = builder.create();
        dialog.show();

        for (UserDate userDate : dataList) {
            
        }
        new ArrayList<>();

        AdapterPesanan adapterPesanan = new AdapterPesanan(RiwayatPesanan.this, dataList);
        recyclerView.setAdapter(adapterPesanan);

        databaseReference = FirebaseDatabase.getInstance().getReference("Riwayat Pemesanan");
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