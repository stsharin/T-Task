package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, "Login.db", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(fname text,lname text,email text primary key,phone text,password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
    }

    public boolean insert(String fname, String lname, String email, String phone, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fname", fname);
        contentValues.put("lname", lname);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        contentValues.put("password", password);

        long ins = db.insert("user", null, contentValues);
        if (ins==-1){
            return false;
        }
        else return true;
    }

    // if email exists
    public boolean chkemail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=?", new String[]{email});

        if (cursor.getCount()>0) return false;
        else return true;
    }
}
