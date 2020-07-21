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

public class CustomAdapterforPRO extends RecyclerView.Adapter<CustomAdapterforPRO.MyViewHolderforPRO> {

    private Context context;
    private ArrayList pro_id, pro_nama, pro_quantity, pro_price;

    Activity activity;

    CustomAdapterforPRO(Activity activity,
                        Context context,
                        ArrayList pro_id,
                        ArrayList pro_nama,
                        ArrayList pro_quantity,
                        ArrayList pro_price){
        this.activity = activity;
        this.context = context;
        this.pro_id = pro_id;
        this.pro_nama = pro_nama;
        this.pro_quantity = pro_quantity;
        this.pro_price = pro_price;

    }

    @NonNull
    @Override
    public MyViewHolderforPRO onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row_pro, parent, false);
        return new MyViewHolderforPRO(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderforPRO holder, final int position) {

        holder.pro_id_txt.setText(String.valueOf(pro_id.get(position)));
        holder.pro_nama_txt.setText(String.valueOf(pro_nama.get(position)));
        holder.pro_quantity_txt.setText(String.valueOf(pro_quantity.get(position)));
        holder.pro_price_txt.setText(String.valueOf(pro_price.get(position)));
        holder.mainLayout_pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Updatepro.class);
                intent.putExtra("id", String.valueOf(pro_id.get(position)));
                intent.putExtra("nama", String.valueOf(pro_nama.get(position)));
                intent.putExtra("quantity", String.valueOf(pro_quantity.get(position)));
                intent.putExtra("price", String.valueOf(pro_price.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return pro_id.size();
    }

    public class MyViewHolderforPRO extends RecyclerView.ViewHolder {

        TextView pro_id_txt, pro_nama_txt, pro_quantity_txt, pro_price_txt;
        LinearLayout mainLayout_pro;

        public MyViewHolderforPRO(@NonNull View itemView) {
            super(itemView);

            pro_id_txt = itemView.findViewById(R.id.pro_id_txt);
            pro_nama_txt = itemView.findViewById(R.id.pro_nama_txt);
            pro_quantity_txt = itemView.findViewById(R.id.pro_quantity_txt);
            pro_price_txt = itemView.findViewById(R.id.pro_price_txt);

            mainLayout_pro = itemView.findViewById(R.id.mainLayout_pro);
        }
    }
}
