package com.apps.app_hardware;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity_mobo extends AppCompatActivity {

    EditText namaMOBO_input, quantityMOBO_input, priceMOBO_input;
    Button add_mobo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mobo);

        namaMOBO_input = findViewById(R.id.namaMOBO_input);
        quantityMOBO_input = findViewById(R.id.quantityMOBO_input);
        priceMOBO_input = findViewById(R.id.priceMOBO_input);
        add_mobo = findViewById(R.id.add_mobo);
        add_mobo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelperforMOBO myDB = new MyDatabaseHelperforMOBO(AddActivity_mobo.this);
                myDB.addMOBO(namaMOBO_input.getText().toString().trim(),
                        Integer.valueOf(quantityMOBO_input.getText().toString().trim()),
                        Integer.valueOf(priceMOBO_input.getText().toString().trim()));
            }
        });
    }
}