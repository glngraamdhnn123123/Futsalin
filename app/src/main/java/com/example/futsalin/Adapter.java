package com.example.futsalin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    Context context;
    ArrayList<Users> list, list1;

    public Adapter(Context context, ArrayList<Users> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.grid_lapangan,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder holder, int position) {
        Users user = list.get(position);
        holder.namaLapangan.setText(user.getName());
        holder.hargaLapangan.setText(user.getHarga());
        holder.alamatLapangan.setText(user.getAlamat());
        Glide.with(context).load(user.getGambar()).into(holder.gambarLapangan);
        holder.setUser(user);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView namaLapangan, alamatLapangan, hargaLapangan;
        ImageView gambarLapangan;
        Users user ;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            namaLapangan = itemView.findViewById(R.id.namaLap);
            alamatLapangan = itemView.findViewById(R.id.alamatLap);
            hargaLapangan = itemView.findViewById(R.id.hargaLap);
            gambarLapangan = itemView.findViewById(R.id.gambarLap);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && user != null) {
                        String name = namaLapangan.getText().toString();
                        String alamat = alamatLapangan.getText().toString();
                        String harga = hargaLapangan.getText().toString();
                        String gambar = user.getGambar();

                        Intent i = new Intent(itemView.getContext(), HalamanTanggal.class);
                        i.putExtra("nama_lapangan", name);
                        i.putExtra("alamat_lapangan", alamat);
                        i.putExtra("harga_lapangan", harga);
                        i.putExtra("gambar_lapangan", gambar);

                        itemView.getContext().startActivity(i);
                    }
                }


            });
        }
        public void setUser(Users user) {
            this.user = user;
        }
    }
}