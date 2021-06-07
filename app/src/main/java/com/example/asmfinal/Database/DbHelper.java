package com.example.asmfinal.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    static final String DbName = "QlChiTieu";
    static final int Version = 5;
    public DbHelper(Context context){
        super(context,DbName,null,Version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlLGD ="CREATE TABLE LoaiGD(MaLoai integer primary key autoincrement," +
                "TenLoai text ,TrangThai text)";
        db.execSQL(sqlLGD);
        sqlLGD = "INSERT INTO LoaiGD(TenLoai, TrangThai) VALUES ('Lai ngan hang', 'Thu')";
        db.execSQL(sqlLGD);
        sqlLGD = "INSERT INTO LoaiGD(TenLoai, TrangThai) VALUES ('Luong', 'Thu')";
        db.execSQL(sqlLGD);
        sqlLGD = "INSERT INTO LoaiGD(TenLoai, TrangThai) VALUES ('Ban hang','Thu')";
        db.execSQL(sqlLGD);
        sqlLGD = "INSERT INTO LoaiGD(TenLoai, TrangThai) VALUES ('Thu no', 'Thu')";
        db.execSQL(sqlLGD);
        sqlLGD = "INSERT INTO LoaiGD(TenLoai, TrangThai) VALUES ('Sinh hoat hang ngay', 'Chi')";
        db.execSQL(sqlLGD);
        sqlLGD = "INSERT INTO LoaiGD(TenLoai, TrangThai) VALUES ('Dong tien hoc','Chi')";
        db.execSQL(sqlLGD);
        sqlLGD = "INSERT INTO LoaiGD(TenLoai, TrangThai) VALUES ('Du lich' , 'Chi')";
        db.execSQL(sqlLGD);

        String sqlGD = "CREATE TABLE GiaoDich(MaGD integer primary key autoincrement," +
                "Tieude text, Ngay text, Tien Float , GhiChu Text, " +
                " MaLoai integer references LoaiGD(MaLoai))";
        db.execSQL(sqlGD);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql1 = "DROP TABLE GiaoDich ;";
        db.execSQL(sql1);
        String sql2 = "DROP TABLE LoaiGD ;";
        db.execSQL(sql2);
        onCreate(db);
    }

    
}
