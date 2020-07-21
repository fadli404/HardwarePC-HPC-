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

public class CustomAdapterforMOBO extends RecyclerView.Adapter<CustomAdapterforMOBO.MyViewHolderforMOBO> {

    private Context context;
    Activity activity;
    private ArrayList mobo_id, mobo_nama, mobo_quantity, mobo_price;

    CustomAdapterforMOBO(Activity activity,
                         Context context,
                         ArrayList mobo_id,
                         ArrayList mobo_nama,
                         ArrayList mobo_quantity,
                         ArrayList mobo_price){
        this.activity = activity;
        this.context = context;
        this.mobo_id = mobo_id;
        this.mobo_nama = mobo_nama;
        this.mobo_quantity = mobo_quantity;
        this.mobo_price = mobo_price;
    }

    @NonNull
    @Override
    public MyViewHolderforMOBO onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row_mobo, parent, false);
        return new MyViewHolderforMOBO(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderforMOBO holder, final int position) {
        holder.mobo_id_txt.setText(String.valueOf(mobo_id.get(position)));
        holder.mobo_nama_txt.setText(String.valueOf(mobo_nama.get(position)));
        holder.mobo_quantity_txt.setText(String.valueOf(mobo_quantity.get(position)));
        holder.mobo_price_txt.setText(String.valueOf(mobo_price.get(position)));
        holder.mainLayout_mobo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Updatemobo.class);
                intent.putExtra("id", String.valueOf(mobo_id.get(position)));
                intent.putExtra("nama", String.valueOf(mobo_nama.get(position)));
                intent.putExtra("quantity", String.valueOf(mobo_quantity.get(position)));
                intent.putExtra("price", String.valueOf(mobo_price.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mobo_id.size();
    }

    public class MyViewHolderforMOBO extends RecyclerView.ViewHolder {

        TextView mobo_id_txt, mobo_nama_txt, mobo_quantity_txt, mobo_price_txt;
        LinearLayout mainLayout_mobo;

        public MyViewHolderforMOBO(@NonNull View itemView) {
            super(itemView);
            mobo_id_txt = itemView.findViewById(R.id.mobo_id_txt);
            mobo_nama_txt = itemView.findViewById(R.id.mobo_nama_txt);
            mobo_quantity_txt = itemView.findViewById(R.id.mobo_quantity_txt);
            mobo_price_txt = itemView.findViewById(R.id.mobo_price_txt);

            mainLayout_mobo = itemView.findViewById(R.id.mainLayout_mobo);
        }
    }
}
