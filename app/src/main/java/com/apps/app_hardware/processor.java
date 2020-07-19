package com.apps.app_hardware;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class processor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processor);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.processor);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.mobo:
                        startActivity(new Intent(getApplicationContext(),mobo.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.vga:
                        startActivity(new Intent(getApplicationContext(),MenuUtama.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.processor:
                        return true;
                    case R.id.psu:
                        startActivity(new Intent(getApplicationContext(),psu.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.ram:
                        startActivity(new Intent(getApplicationContext(),ram.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}