package com.example.asmfinal.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asmfinal.Database.DbHelper;
import com.example.asmfinal.Database.DbHelper;
import com.example.asmfinal.model.LoaiGD;

import java.util.ArrayList;
import java.util.List;

public class LoaiGDDAO {

    DbHelper dbHelper;
    public LoaiGDDAO(Context context) {
        dbHelper = new DbHelper(context);
    }
    public ArrayList<LoaiGD> getAll(){
        ArrayList<LoaiGD> ds_pl = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cs = db.query("LoaiGD", null, null, null, null, null, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            int maLoai = cs.getInt( 0);
            String tenLoai = cs.getString(1);
            String trangThai = cs.getString(2);

            ds_pl.add(new LoaiGD(maLoai, tenLoai, trangThai));
            cs.moveToNext();
        }
        cs.close();
        return ds_pl;
    }
    public void them(LoaiGD loaiGD){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenLoai", loaiGD.TenLoai);
        values.put("TrangThai", loaiGD.TrangThai);
        db.insert("LoaiGD",null, values);

    }

    public void update(LoaiGD loaiGD){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenLoai", loaiGD.TenLoai);
        values.put("TrangThai", loaiGD.TrangThai);
        db.update("LoaiGD", values, "MaLoai=?",new String[]{loaiGD.MaLoai+""});


    }

    public void delete(int maLoai){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("LoaiGD", "MaLoai=?",new String[]{maLoai+""});

    }

}
