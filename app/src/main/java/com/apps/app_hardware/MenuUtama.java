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

public class MenuUtama extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    TextView vga_nodata;

    MyDatabaseHelper myDB;
    ArrayList<String> vga_id, vga_nama, vga_quantity, vga_price;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);

//untuk tombol tambah list_vga
        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        vga_nodata = findViewById(R.id.vga_nodata);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(MenuUtama.this, AddActivity.class);
               startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(MenuUtama.this);
        vga_id = new ArrayList<>();
        vga_nama = new ArrayList<>();
        vga_quantity = new ArrayList<>();
        vga_price = new ArrayList<>();

        storeDataInArrays();
        customAdapter = new CustomAdapter(MenuUtama.this, this, vga_id, vga_nama, vga_quantity, vga_price);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MenuUtama.this));


//untuk bottomnavigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.vga);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.mobo:
                        startActivity(new Intent(getApplicationContext(),mobo.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.vga:
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
            vga_nodata.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                vga_id.add(cursor.getString(0));
                vga_nama.add(cursor.getString(1));
                vga_quantity.add(cursor.getString(2));
                vga_price.add(cursor.getString(3));
            }
            vga_nodata.setVisibility(View.GONE);
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
                MyDatabaseHelper myDB = new MyDatabaseHelper(MenuUtama.this);
                myDB.deleteAllData();
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