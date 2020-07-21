package com.apps.app_hardware;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Updatepro extends AppCompatActivity {

    EditText namaPRO_input, quantityPRO_input, pricePRO_input;
    Button update_button, delete_button;

    String id, nama, quantity, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatepro);

        namaPRO_input = findViewById(R.id.update_nama_inputPRO);
        quantityPRO_input = findViewById(R.id.update_quantity_inputPRO);
        pricePRO_input = findViewById(R.id.update_price_inputPRO);
        update_button = findViewById(R.id.update_pro);
        delete_button = findViewById(R.id.delete_pro);

        getAndSetIntentDataPRO();

        //set actionbar agar sesuai nama data yang diupdate
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(nama);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelperforPRO myDB = new MyDatabaseHelperforPRO(Updatepro.this);
                nama = namaPRO_input.getText().toString().trim();
                quantity = quantityPRO_input.getText().toString().trim();
                price = pricePRO_input.getText().toString().trim();
                myDB.updateDataPRO(id, nama, quantity, price);
                finish();

            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentDataPRO(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("nama") &&
                getIntent().hasExtra("quantity") && getIntent().hasExtra("price")){
            //get
            id = getIntent().getStringExtra("id");
            nama = getIntent().getStringExtra("nama");
            quantity = getIntent().getStringExtra("quantity");
            price = getIntent().getStringExtra("price");
            //set
            namaPRO_input.setText(nama);
            quantityPRO_input.setText(quantity);
            pricePRO_input.setText(price);
        }else{
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hapus " + nama);
        builder.setMessage("Apakah anda yakin ingin menghapus " +nama + "?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelperforPRO myDB = new MyDatabaseHelperforPRO(Updatepro.this);
                myDB.deleteOneRowPRO(id);
                finish();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}