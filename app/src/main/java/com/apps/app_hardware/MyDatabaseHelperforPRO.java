package com.apps.app_hardware;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelperforPRO extends SQLiteOpenHelper {
    //init database
    private Context context;
    private static final String DATABASE_NAME = "list_pro.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "myListPRO";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "nama";
    private static final String COLUMN_QUANTITY = "quantity";
    private static final String COLUMN_PRICE = "price";

    MyDatabaseHelperforPRO(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //membuat database untuk table mobo
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_QUANTITY + " INTEGER, " +
                COLUMN_PRICE + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
    //Tambah mobo
    void addPRO(String namaPRO, int quantityPRO, int pricePRO){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, namaPRO);
        cv.put(COLUMN_QUANTITY, quantityPRO);
        cv.put(COLUMN_PRICE, pricePRO);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }
    //baca semua data
    Cursor readAllDataPRO(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateDataPRO(String row_id, String nama, String quantity, String price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, nama);
        cv.put(COLUMN_QUANTITY, quantity);
        cv.put(COLUMN_PRICE, price);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRowPRO(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllDataPRO(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}
