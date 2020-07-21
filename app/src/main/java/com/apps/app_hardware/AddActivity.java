package com.apps.app_hardware;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText nama_input, quantity_input, price_input;
    Button add_vga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nama_input = findViewById(R.id.nama_input);
        quantity_input = findViewById(R.id.quantity_input);
        price_input = findViewById(R.id.price_input);
        add_vga = findViewById(R.id.add_vga);
        add_vga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addVGA(nama_input.getText().toString().trim(),
                        Integer.valueOf(quantity_input.getText().toString().trim()),
                        Integer.valueOf(price_input.getText().toString().trim()));
            }
        });
    }
}