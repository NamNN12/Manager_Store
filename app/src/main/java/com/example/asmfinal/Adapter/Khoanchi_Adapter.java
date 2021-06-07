package com.example.asmfinal.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asmfinal.DAO.GiaodichDAO;
import com.example.asmfinal.Dialog.Bottom_sheet_edit_chi;
import com.example.asmfinal.R;
import com.example.asmfinal.model.GiaoDich;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.example.asmfinal.TabFragment.Tab_Khoanchi.khoanchi_adapter;
import static com.example.asmfinal.TabFragment.Tab_Khoanchi.rv_chi;


public class Khoanchi_Adapter extends RecyclerView.Adapter<Khoanchi_Adapter.MyViewHolder> {
    private ArrayList<GiaoDich> ds_chi;
    private Context context;
    GiaodichDAO giaoDichDAO;

    public Khoanchi_Adapter(ArrayList<GiaoDich> ds_chi, Context context) {
        this.ds_chi = ds_chi;
        this.context = context;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_tieude;
        public TextView tv_ngay;
        public TextView tv_tien;
        public  TextView tv_trangthai;
        private ImageView img_xoa_thu;
        private ImageView img_edit_thu;
        public MyViewHolder(View v) {
            super(v);
            tv_tieude = v.findViewById(R.id.tv_tieude);
            tv_ngay = v.findViewById(R.id.tv_ngay);
            tv_tien = v.findViewById(R.id.tv_tien);
            tv_trangthai=v.findViewById(R.id.tv_trangthai);
            img_xoa_thu = v.findViewById(R.id.img_xoa_thu);
            img_edit_thu = v.findViewById(R.id.img_edit_thu);
        }
    }
    @Override
    public Khoanchi_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khoan_chi, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_tieude.setText(ds_chi.get(position).Tieude);
        holder.tv_ngay.setText(ds_chi.get(position).Ngay+"");
        //Dinh dang hien thi so tien
        DecimalFormat formatter = new DecimalFormat("#,###");
        String s = formatter.format(ds_chi.get(position).Tien);
        holder.tv_tien.setText(s);
        holder.img_xoa_thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Bạn có chắc muốn xóa "+ds_chi.get(position).Tieude);
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                giaoDichDAO = new GiaodichDAO(context);
                                giaoDichDAO.delete(ds_chi.get(position).MaGD);
                                Toast.makeText(context, "Xóa thành công "+ds_chi.get(position).Tieude, Toast.LENGTH_SHORT).show();
                                capnhat();
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

        holder.img_edit_thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("MaGD", ds_chi.get(position).MaGD+"");
                args.putString("Tieude", ds_chi.get(position).Tieude+"");
                args.putString("Ngay", ds_chi.get(position).Ngay+"");
                args.putString("GhiChu", ds_chi.get(position).GhiChu+"");
                args.putDouble("Tien", ds_chi.get(position).Tien);
                args.putString("MaLoai", ds_chi.get(position).MaLoai+"");

                Bottom_sheet_edit_chi bottom_sheet = new Bottom_sheet_edit_chi();
                //bottom_sheet.show(((AppCompatActivity)context).getSupportFragmentManager(),"TAG");
                bottom_sheet.setArguments(args);
                bottom_sheet.show(((AppCompatActivity) context).getSupportFragmentManager(),bottom_sheet.getTag());
            }
        });

    }
    @Override
    public int getItemCount() {
        return ds_chi.size();
    }

    public void capnhat(){
        ds_chi = giaoDichDAO.getKhoanThu_Chi("Chi");
        khoanchi_adapter = new Khoanchi_Adapter(ds_chi, context);
        rv_chi.setAdapter(khoanchi_adapter);
    }


}

