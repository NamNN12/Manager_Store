package com.example.asmfinal.model;

public class GiaoDich {
        public  int MaGD;
        public  String Tieude;
        public  String Ngay;
        public  float Tien;
        public String GhiChu;
        public  int MaLoai;


        public GiaoDich(String tieude, String ngay, float tien, String ghichu, int maloai) {
            Tieude = tieude;
            Ngay = ngay;
            Tien = tien;
            GhiChu = ghichu;
            MaLoai = maloai;
        }
    public GiaoDich() {
    }

        public GiaoDich(int MaGD, String tieude, String Ngay, float Tien, String GhiChu,int MaLoai) {
            this.MaGD = MaGD;
            this.Tieude = tieude;
            this.Ngay = Ngay;
            this.Tien = Tien;
            this.GhiChu = GhiChu;
            this.MaLoai=MaLoai;
        }
}
