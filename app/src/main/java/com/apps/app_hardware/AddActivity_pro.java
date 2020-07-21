package com.apps.app_hardware;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity_pro extends AppCompatActivity {
    EditText namaPRO_input, quantityPRO_input, pricePRO_input;
    Button add_pro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pro);
        namaPRO_input = findViewById(R.id.namaPRO_input);
        quantityPRO_input = findViewById(R.id.quantityPRO_input);
        pricePRO_input = findViewById(R.id.pricePRO_input);
        add_pro = findViewById(R.id.add_pro);
        add_pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelperforPRO myDB = new MyDatabaseHelperforPRO(AddActivity_pro.this);
                myDB.addPRO(namaPRO_input.getText().toString().trim(),
                        Integer.valueOf(quantityPRO_input.getText().toString().trim()),
                        Integer.valueOf(pricePRO_input.getText().toString().trim()));
            }
        });
    }
}