package com.ltd.tandung.amazon_shopping.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by letandung on 08/10/2017.
 */

public class DataUsername extends SQLiteOpenHelper {
    //    public static String TB_GIOHANG = "GIOHANG";
//    public static String TB_GIOHANG_MASP = "MASP";
//    public static String TB_GIOHANG_TENSP = "TENSP";
//    public static String TB_GIOHANG_GIATIEN = "GIATIEN";
//    public static String TB_GIOHANG_HINHANH = "HINHANH";
//
//    public static String TB_YEUTHICH__GIOHANG = "YEUTHICH";
//    public static String TB_YEUTHICH_MASP = "MASP";
//    public static String TB_YEUTHICH_TENSP = "TENSP";
//    public static String TB_YEUTHICH_GIATIEN = "GIATIEN";
//    public static String TB_YEUTHICH_HINHANH = "HINHANH";
    public static String TB_USER = "users";
    public static String TB_USER_ID = "id";
    public static String TB_USER_USERNAME = "username";
    public static String TB_USER_PASSWORD = "password";

    public DataUsername(Context context) {
        super(context, "SQLUSER", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
//        String tbGiohang = "CREATE TABLE" + TB_GIOHANG + "(" + TB_GIOHANG_MASP + "INTEGER PRIMARY KEY," +
//                TB_GIOHANG_TENSP + "TEXT," + TB_GIOHANG_GIATIEN + "REAL," + TB_GIOHANG_HINHANH + "BLOB)";
//
//        String tbYeuthich = "CREATE TABLE" + TB_YEUTHICH__GIOHANG + "(" + TB_YEUTHICH_MASP + "INTEGER PRIMARY KEY," +
//                TB_YEUTHICH_TENSP + "TEXT," + TB_YEUTHICH_GIATIEN + "REAL," + TB_YEUTHICH_HINHANH + "BLOB)";
        String tbUser = "CREATE TABLE" + TB_USER + "(" + TB_USER_ID + "INTEGER PRIMARY KEY," + TB_USER_USERNAME + "TEXT," + TB_USER_PASSWORD + "TEXT)";
//        db.execSQL(tbGiohang);
//        db.execSQL(tbYeuthich);
        db.execSQL(tbUser);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TB_USER_USERNAME, username);
        values.put(TB_USER_PASSWORD, password);
        long id=db.insert(TB_USER,null,values);

    }
    public boolean getUser(String email, String pass){
        //HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "select * from  " + TB_USER + " where " +
                TB_USER_USERNAME + " = " + "'"+email+"'" + " and " + TB_USER_PASSWORD + " = " + "'"+pass+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {

            return true;
        }
        cursor.close();
        db.close();

        return false;
    }
}
