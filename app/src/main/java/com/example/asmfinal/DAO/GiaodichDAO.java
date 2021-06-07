package com.example.asmfinal.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asmfinal.model.GiaoDich;
import com.example.asmfinal.model.LoaiGD;
import com.example.asmfinal.Database.DbHelper;
import com.example.asmfinal.model.GiaoDich;
import com.example.asmfinal.model.LoaiGD;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GiaodichDAO {
    SQLiteDatabase db1;
    DbHelper db;


    public GiaodichDAO(Context context) {
        db = new DbHelper(context);
    }

    public void them(GiaoDich gd) {
        db1 = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Tieude", gd.Tieude);
        values.put("Ngay", gd.Ngay);
        values.put("Tien", gd.Tien);
        values.put("GhiChu", gd.GhiChu);
        values.put("MaLoai", gd.MaLoai);

        db1.insert("GiaoDich",null,values);
    }

    public ArrayList<LoaiGD> getThu(){
        ArrayList<LoaiGD> ds_pl = new ArrayList<>();
        db1 = db.getWritableDatabase();
        Cursor cursor = db1.rawQuery("SELECT * FROM LoaiGD WHERE TrangThai LIKE 'Thu'", null);
        if (cursor.moveToFirst()){
            do {
                int maloai = cursor.getInt(0);
                String tenloai = cursor.getString(1);
                String trangthai = cursor.getString(2);
                ds_pl.add(new LoaiGD(maloai,tenloai,trangthai));
            } while (cursor.moveToNext());
        }
        return ds_pl;
    }

    public ArrayList<LoaiGD> getChi(){
        ArrayList<LoaiGD> ds_pl = new ArrayList<>();
        db1 = db.getWritableDatabase();
        Cursor cursor = db1.rawQuery("SELECT * FROM LoaiGD WHERE TrangThai = '"+"Chi"+"'", null);
        if (cursor.moveToFirst()){
            do {
                int maloai = cursor.getInt(0);
                String tenloai = cursor.getString(1);
                String trangthai = cursor.getString(2);
                ds_pl.add(new LoaiGD(maloai,tenloai,trangthai));
            } while (cursor.moveToNext());
        }
        return ds_pl;
    }

    public String getTen(String maloai){
        String ten = "";
        db1 = db.getWritableDatabase();
        Cursor cursor = db1.rawQuery("SELECT TenLoai FROM LoaiGD WHERE MaLoai = '"+maloai+"'",null);
        if (cursor.moveToFirst()){
            do {
                ten = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        return ten;
    }
    public String getBetweenDay(String date1, String date2){
           String total= "";
        db1 = db.getWritableDatabase();
        Cursor cursor = db1.rawQuery("SELECT SUM(Tien) FROM GiaoDich WHERE ngay BETWEEN '2020-07-20' AND '2020-07-21'", null);
        if (cursor.moveToFirst()){
            do {
                total = cursor.getString(0);
            } while (cursor.moveToNext());
        }

           return total;
    }


    public double getTotalthubyDate(String date1, String date2){
        float total = (float) 0.0;
        db1 = db.getWritableDatabase();
        Cursor cursor = db1.rawQuery("SELECT SUM(Tien) FROM GiaoDich,LoaiGD WHERE Ngay BETWEEN '"+date1+"' AND '"+date2+"' AND GiaoDich.MaLoai = LoaiGD.MaLoai AND LoaiGD.TrangThai LIKE 'Thu'",null);
        if (cursor.moveToFirst()){
            do {
                total = cursor.getFloat(0);

            }while (cursor.moveToNext());
        }
        return total;
    }
    public double getTotalthu(){
        float total = (float) 0.0;
        db1 = db.getWritableDatabase();
        Cursor cursor = db1.rawQuery("SELECT SUM(Tien) FROM GiaoDich INNER JOIN LoaiGD ON GiaoDich.MaLoai = LoaiGD.MaLoai AND LoaiGD.TrangThai LIKE 'Thu'",null);
        if (cursor.moveToFirst()){
            do {
                total = cursor.getFloat(0);

            }while (cursor.moveToNext());
        }
        return total;
    }



    public ArrayList<GiaoDich> getKhoanThu_Chi(String trangthai){
        ArrayList<GiaoDich> ds_giaodich = new ArrayList<>();
        db1 = db.getWritableDatabase();
        Cursor cursor = db1.rawQuery("SELECT * FROM GiaoDich INNER JOIN LoaiGD ON GiaoDich.MaLoai = LoaiGD.MaLoai AND LoaiGD.TrangThai LIKE '"+trangthai+"'",null);
        if (cursor.moveToFirst()){
            do {
                Date date1 = null;
                int Magiaodich = cursor.getInt(0);
                String Tieude = cursor.getString(1);
                String Ngay = cursor.getString(2);
                float Tien = Float.parseFloat(cursor.getString(3));
                String ghichu = cursor.getString(4);
                int Maloai = cursor.getInt(5);
                ds_giaodich.add(new GiaoDich(Magiaodich,Tieude,Ngay,Tien,ghichu,Maloai));
            } while (cursor.moveToNext());
        }
        return ds_giaodich;
    }

    public ArrayList<GiaoDich> get_Tk_Thu(String date1, String date2){
        ArrayList<GiaoDich> ds_giaodich = new ArrayList<>();
        db1 = db.getWritableDatabase();
        Cursor cursor = db1.rawQuery("SELECT * FROM GiaoDich,LoaiGD WHERE ngay BETWEEN '"+date1+"' AND '"+date2+"' AND GiaoDich.MaLoai = LoaiGD.MaLoai AND LoaiGD.TrangThai LIKE 'Thu'" ,null);
        if (cursor.moveToFirst()){
            do {
                int Magiaodich = cursor.getInt(0);
                String Tieude = cursor.getString(1);
                String Ngay = cursor.getString(2);
                float Tien = Float.parseFloat(cursor.getString(3));
                String Mota = cursor.getString(4);
                int Maloai = cursor.getInt(5);
                ds_giaodich.add(new GiaoDich(Magiaodich,Tieude,Ngay,Tien,Mota,Maloai));
            } while (cursor.moveToNext());
        }
        return ds_giaodich;
    }

    public void delete(int id){
        db1 = db.getWritableDatabase();
        db1.delete("GiaoDich","MaGD=?", new String[]{id+""});
    }
    public void update(GiaoDich gd){
        db1 = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Tieude", gd.Tieude);
        values.put("Ngay", gd.Ngay);
        values.put("Tien", gd.Tien);
        values.put("GhiChu", gd.GhiChu);
        values.put("MaLoai", gd.MaLoai);
        db1.update("GiaoDich",values, "MaGD=?", new String[]{gd.MaGD+""});
    }
}
