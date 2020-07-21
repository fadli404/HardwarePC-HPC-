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

public class UpdateActivity extends AppCompatActivity {

    EditText nama_input, quantity_input, price_input;
    Button update_button, delete_button;

    String id, nama, quantity, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        nama_input = findViewById(R.id.update_nama_input);
        quantity_input = findViewById(R.id.update_quantity_input);
        price_input = findViewById(R.id.update_price_input);
        update_button = findViewById(R.id.update_vga);
        delete_button = findViewById(R.id.delete_vga);

        getAndSetIntentData();

        //set actionbar agar sesuai nama data yang diupdate
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(nama);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                nama = nama_input.getText().toString().trim();
                quantity = quantity_input.getText().toString().trim();
                price = price_input.getText().toString().trim();
                myDB.updateData(id, nama, quantity, price);
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

    void getAndSetIntentData(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("nama") &&
                getIntent().hasExtra("quantity") && getIntent().hasExtra("price")){
            //get
            id = getIntent().getStringExtra("id");
            nama = getIntent().getStringExtra("nama");
            quantity = getIntent().getStringExtra("quantity");
            price = getIntent().getStringExtra("price");
            //set
            nama_input.setText(nama);
            quantity_input.setText(quantity);
            price_input.setText(price);
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
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
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