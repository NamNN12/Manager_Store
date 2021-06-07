package com.example.asmfinal.model;

public class LoaiGD {
    public  int MaLoai;
    public  String TenLoai;
    public String TrangThai;

    public LoaiGD() {
    }

    public LoaiGD(int maloai, String tenloai, String trangthai) {
        this.MaLoai = maloai;
        this.TenLoai = tenloai;
        this.TrangThai = trangthai;
    }
    public LoaiGD(int maloai, String tenloai) {
        this.MaLoai = maloai;
        this.TenLoai = tenloai;
    }
    public LoaiGD(String tenLoai, String trangthai) {
        TenLoai = tenLoai;
        TrangThai = trangthai;
    }


}
