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

public class processor extends AppCompatActivity {
    RecyclerView recyclerView_pro;
    FloatingActionButton add_button_pro;
    TextView pro_nodata;

    MyDatabaseHelperforPRO myDB;
    ArrayList<String> pro_id, pro_nama, pro_quantity, pro_price;
    CustomAdapterforPRO customAdapterforPRO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processor);

        recyclerView_pro = findViewById(R.id.recyclerView_pro);
        add_button_pro = findViewById(R.id.add_button_pro);
        pro_nodata = findViewById(R.id.pro_nodata);
        add_button_pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(processor.this, AddActivity_pro.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelperforPRO(processor.this);
        pro_id = new ArrayList<>();
        pro_nama = new ArrayList<>();
        pro_quantity = new ArrayList<>();
        pro_price = new ArrayList<>();

        storeDataInArrays();

        customAdapterforPRO = new CustomAdapterforPRO(processor.this, this, pro_id, pro_nama, pro_quantity, pro_price);
        recyclerView_pro.setAdapter(customAdapterforPRO);
        recyclerView_pro.setLayoutManager(new LinearLayoutManager(processor.this));

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
        Cursor cursor = myDB.readAllDataPRO();
        if (cursor.getCount()==0){
            pro_nodata.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                pro_id.add(cursor.getString(0));
                pro_nama.add(cursor.getString(1));
                pro_quantity.add(cursor.getString(2));
                pro_price.add(cursor.getString(3));
            }
            pro_nodata.setVisibility(View.GONE);
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
                MyDatabaseHelperforPRO myDB = new MyDatabaseHelperforPRO(processor.this);
                myDB.deleteAllDataPRO();
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