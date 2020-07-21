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

public class Updatemobo extends AppCompatActivity {
    EditText namaMOBO_input, quantityMOBO_input, priceMOBO_input;
    Button update_button, delete_button;

    String id, nama, quantity, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatemobo);

        namaMOBO_input = findViewById(R.id.update_nama_inputMOBO);
        quantityMOBO_input = findViewById(R.id.update_quantity_inputMOBO);
        priceMOBO_input = findViewById(R.id.update_price_inputMOBO);
        update_button = findViewById(R.id.update_mobo);
        delete_button = findViewById(R.id.delete_mobo);

        getAndSetIntentDataMOBO();

        //set actionbar agar sesuai nama data yang diupdate
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(nama);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelperforMOBO myDB = new MyDatabaseHelperforMOBO(Updatemobo.this);
                nama = namaMOBO_input.getText().toString().trim();
                quantity = quantityMOBO_input.getText().toString().trim();
                price = priceMOBO_input.getText().toString().trim();
                myDB.updateDataMOBO(id, nama, quantity, price);
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

    void getAndSetIntentDataMOBO(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("nama") &&
                getIntent().hasExtra("quantity") && getIntent().hasExtra("price")){
            //get
            id = getIntent().getStringExtra("id");
            nama = getIntent().getStringExtra("nama");
            quantity = getIntent().getStringExtra("quantity");
            price = getIntent().getStringExtra("price");
            //set
            namaMOBO_input.setText(nama);
            quantityMOBO_input.setText(quantity);
            priceMOBO_input.setText(price);
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
                MyDatabaseHelperforMOBO myDB = new MyDatabaseHelperforMOBO(Updatemobo.this);
                myDB.deleteOneRowMOBO(id);
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