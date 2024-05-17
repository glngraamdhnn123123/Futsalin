package com.example.futsalin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterPesanan extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<UserDate> dataList;

    public AdapterPesanan(Context context, List<UserDate> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_pesanan, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.lapPesanan.setText(dataList.get(position).getName());
        holder.tanggalPesanan.setText(dataList.get(position).getTanggal());
        holder.waktuPesanan.setText(dataList.get(position).getWaktu());
        holder.durasiPesanan.setText(dataList.get(position).getDurasi());
        holder.totalPesanan.setText(dataList.get(position).getTotal());

        holder.cardPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HalamanRiwayat.class);
                intent.putExtra("Nama Lapangan", dataList.get(holder.getAdapterPosition()).getName());
                intent.putExtra("Tanggal", dataList.get(holder.getAdapterPosition()).getTanggal());
                intent.putExtra("Waktu", dataList.get(holder.getAdapterPosition()).getWaktu());
                intent.putExtra("Durasi", dataList.get(holder.getAdapterPosition()).getDurasi());
                intent.putExtra("Total", dataList.get(holder.getAdapterPosition()).getTotal());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return dataList.size();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder {

    TextView lapPesanan, tanggalPesanan, waktuPesanan, durasiPesanan, totalPesanan;
    CardView cardPesanan;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        lapPesanan = itemView.findViewById(R.id.namaLap);
        tanggalPesanan = itemView.findViewById(R.id.tanggalPesanan);
        waktuPesanan = itemView.findViewById(R.id.jamPesanan);
        durasiPesanan = itemView.findViewById(R.id.durasiPesanan);
        totalPesanan = itemView.findViewById(R.id.totalPesanan);
        cardPesanan = itemView.findViewById(R.id.gridPesanan);

    }

}