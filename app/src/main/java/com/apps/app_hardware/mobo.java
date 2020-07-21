package com.apps.app_hardware;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class mobo extends AppCompatActivity {
    RecyclerView recyclerView_mobo;
    FloatingActionButton add_button_mobo;
    TextView mobo_nodata;

    MyDatabaseHelperforMOBO myDB;
    ArrayList<String> mobo_id, mobo_nama, mobo_quantity, mobo_price;
    CustomAdapterforMOBO customAdapterforMOBO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobo);

        recyclerView_mobo = findViewById(R.id.recyclerView_mobo);
        add_button_mobo = findViewById(R.id.add_button_mobo);
        mobo_nodata = findViewById(R.id.mobo_nodata);
        add_button_mobo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mobo.this, AddActivity_mobo.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelperforMOBO(mobo.this);
        mobo_id = new ArrayList<>();
        mobo_nama = new ArrayList<>();
        mobo_quantity = new ArrayList<>();
        mobo_price = new ArrayList<>();

        storeDataInArrays();

        customAdapterforMOBO = new CustomAdapterforMOBO(mobo.this, this, mobo_id, mobo_nama, mobo_quantity, mobo_price);
        recyclerView_mobo.setAdapter(customAdapterforMOBO);
        recyclerView_mobo.setLayoutManager(new LinearLayoutManager(mobo.this));

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.mobo);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.mobo:
                        return true;
                    case R.id.vga:
                        startActivity(new Intent(getApplicationContext(),MenuUtama.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.processor:
                        startActivity(new Intent(getApplicationContext(),processor.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount()==0){
            mobo_nodata.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                mobo_id.add(cursor.getString(0));
                mobo_nama.add(cursor.getString(1));
                mobo_quantity.add(cursor.getString(2));
                mobo_price.add(cursor.getString(3));
            }
            mobo_nodata.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_hapus, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete_all){
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hapus semua");
        builder.setMessage("Apakah anda yakin ingin menghapus semua data ?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelperforMOBO myDB = new MyDatabaseHelperforMOBO(mobo.this);
                myDB.deleteAllDataMOBO();
                recreate();
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