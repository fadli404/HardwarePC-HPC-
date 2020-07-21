package com.apps.app_hardware;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList vga_id, vga_nama, vga_quantity, vga_price;


    CustomAdapter(Activity activity,
                  Context context,
                  ArrayList vga_id,
                  ArrayList vga_nama,
                  ArrayList vga_quantity,
                  ArrayList vga_price){
        this.activity = activity;
        this.context = context;
        this.vga_id = vga_id;
        this.vga_nama = vga_nama;
        this.vga_quantity = vga_quantity;
        this.vga_price = vga_price;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.vga_id_txt.setText(String.valueOf(vga_id.get(position)));
        holder.vga_nama_txt.setText(String.valueOf(vga_nama.get(position)));
        holder.vga_quantity_txt.setText(String.valueOf(vga_quantity.get(position)));
        holder.vga_price_txt.setText(String.valueOf(vga_price.get(position)));
        //onClick update database
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(vga_id.get(position)));
                intent.putExtra("nama", String.valueOf(vga_nama.get(position)));
                intent.putExtra("quantity", String.valueOf(vga_quantity.get(position)));
                intent.putExtra("price", String.valueOf(vga_price.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return vga_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView vga_id_txt, vga_nama_txt, vga_quantity_txt, vga_price_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            vga_id_txt = itemView.findViewById(R.id.vga_id_txt);
            vga_nama_txt = itemView.findViewById(R.id.vga_nama_txt);
            vga_quantity_txt = itemView.findViewById(R.id.vga_quantity_txt);
            vga_price_txt = itemView.findViewById(R.id.vga_price_txt);
            //init mainlayout untuk update database
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
