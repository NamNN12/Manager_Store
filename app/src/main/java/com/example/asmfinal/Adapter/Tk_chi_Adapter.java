package com.example.asmfinal.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.asmfinal.DAO.GiaodichDAO;
import com.example.asmfinal.R;
import com.example.asmfinal.model.GiaoDich;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Tk_chi_Adapter extends RecyclerView.Adapter<Tk_chi_Adapter.MyViewHolder> {
    private ArrayList<GiaoDich> ds_chi;
    private Context context;
    GiaodichDAO giaodichdao;

    public Tk_chi_Adapter(ArrayList<GiaoDich> ds_chi, Context context) {
        this.ds_chi = ds_chi;
        this.context = context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_tieude;
        public TextView tv_ngay;
        public TextView tv_tien;
        private ImageView img_xoa_thu;
        private ImageView img_edit_thu;
        public MyViewHolder(View v) {
            super(v);
            tv_tieude = v.findViewById(R.id.tv_tieude);
            tv_ngay = v.findViewById(R.id.tv_ngay);
            tv_tien = v.findViewById(R.id.tv_tien);
            img_xoa_thu = v.findViewById(R.id.img_xoa_thu);
            img_edit_thu = v.findViewById(R.id.img_edit_thu);
        }
    }

    @Override
    public Tk_chi_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

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

    }
    @Override
    public int getItemCount() {
        return ds_chi.size();
    }

}

