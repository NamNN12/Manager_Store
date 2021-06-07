package com.example.asmfinal.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asmfinal.Dialog.BottomSheetedt_loai;
import com.example.asmfinal.model.LoaiGD;
import com.example.asmfinal.DAO.LoaiGDDAO;
import com.example.asmfinal.R;

import java.util.ArrayList;

import static com.example.asmfinal.Fragment_phanloai.loaiGDAdapters;
import static com.example.asmfinal.Fragment_phanloai.rv_loaigd;

public class LoaiGDAdapter extends RecyclerView.Adapter<LoaiGDAdapter.MyViewHolder> {
    public ArrayList<LoaiGD> ds_loaigd;
    private Context context;
    LoaiGDDAO loaiGDDAO;

    public LoaiGDAdapter(ArrayList<LoaiGD> ds_loaigd, Context context) {
        this.ds_loaigd = ds_loaigd;
        this.context = context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_ten;
        public TextView tv_trangthai;
        public ImageView img_xoa_pl;
        public ImageView img_edit_pl;
        public MyViewHolder(View v) {
            super(v);
            tv_ten = v.findViewById(R.id.tv_tenloai);
            tv_trangthai = v.findViewById(R.id.tv_trangthai);
            img_xoa_pl = v.findViewById(R.id.img_xoa_pl);
            img_edit_pl = v.findViewById(R.id.img_edit_pl);
        }
    }


    @NonNull
    @Override
    public LoaiGDAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phanloai, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
    holder.tv_ten.setText(ds_loaigd.get(position).TenLoai);
    holder.tv_trangthai.setText(ds_loaigd.get(position).TrangThai);
        holder.img_xoa_pl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Bạn có chắc muốn xóa "+ds_loaigd  .get(position).TenLoai);
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int MaLoai) {
                                loaiGDDAO = new LoaiGDDAO(context);
                                loaiGDDAO.delete(ds_loaigd.get(position).MaLoai);
                                Toast.makeText(context, "Xoa Thanh Cong"+ds_loaigd.get(position).TenLoai, Toast.LENGTH_SHORT).show();
                                capnhat();
                                dialog.cancel();
                            }
                        });
                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int MaLoai) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
        holder.img_edit_pl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("MaLoai", ds_loaigd.get(position).MaLoai+"");
                args.putString("TenLoai", ds_loaigd.get(position).TenLoai+"");
                args.putString("TrangThai", ds_loaigd.get(position).TrangThai+"");
                BottomSheetedt_loai bottomSheetedt_loai= new BottomSheetedt_loai();
//                bottomSheetedt_loai.show(((AppCompatActivity)context).getSupportFragmentManager(),"TAG");
                bottomSheetedt_loai.setArguments(args);
                bottomSheetedt_loai.show(((AppCompatActivity) context).getSupportFragmentManager(),bottomSheetedt_loai.getTag());
            }
        });
    }
    public void capnhat(){
        ds_loaigd= (ArrayList<LoaiGD>) loaiGDDAO.getAll();
        loaiGDAdapters = new LoaiGDAdapter(ds_loaigd,context);
        rv_loaigd.setAdapter(loaiGDAdapters);
    }


    @Override
    public int getItemCount() {
        return ds_loaigd.size();
    }
}
