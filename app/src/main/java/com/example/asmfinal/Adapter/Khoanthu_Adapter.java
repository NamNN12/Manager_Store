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
import com.example.asmfinal.Dialog.Bottom_sheet_edit_thu;
import com.example.asmfinal.R;
import com.example.asmfinal.model.GiaoDich;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.example.asmfinal.TabFragment.Tab_Khoanthu.khoanthu_adapter;
import static com.example.asmfinal.TabFragment.Tab_Khoanthu.rv_thu;

public class Khoanthu_Adapter extends RecyclerView.Adapter<Khoanthu_Adapter.MyViewHolder> {
    private ArrayList<GiaoDich> ds_thu;
    private Context context;
    GiaodichDAO giaoDichDAO;

    public Khoanthu_Adapter(ArrayList<GiaoDich> ds_thu, Context context) {
        this.ds_thu = ds_thu;
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
            img_xoa_thu = v.findViewById(R.id.img_xoa_pl);
            img_edit_thu = v.findViewById(R.id.img_edit_pl);
        }
    }

    @Override
    public Khoanthu_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khoan_thu, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_tieude.setText(ds_thu.get(position).Tieude);
        holder.tv_ngay.setText(ds_thu.get(position).Ngay+"");
       //Dinh dang hien thi so tien
        DecimalFormat formatter = new DecimalFormat("#,###");
        String s = formatter.format(ds_thu.get(position).Tien);
        holder.tv_tien.setText(s);
        holder.img_xoa_thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Bạn có chắc muốn xóa "+ds_thu.get(position).Tieude);
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                giaoDichDAO = new GiaodichDAO(context);
                                giaoDichDAO.delete(ds_thu.get(position).MaGD);
                                Toast.makeText(context, "Xóa thành công "+ds_thu.get(position).Tieude, Toast.LENGTH_SHORT).show();
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
                args.putString("MaGD", ds_thu.get(position).MaGD+"");
                args.putString("Tieude", ds_thu.get(position).Tieude+"");
                args.putString("Ngay", ds_thu.get(position).Ngay+"");
                args.putString("GhiChu", ds_thu.get(position).GhiChu+"");
                args.putFloat("Tien", ds_thu.get(position).Tien);
                args.putString("MaLoai", ds_thu.get(position).MaLoai+"");
               Bottom_sheet_edit_thu bottom_sheet = new Bottom_sheet_edit_thu();

               bottom_sheet.setArguments(args);
              bottom_sheet.show(((AppCompatActivity) context).getSupportFragmentManager(),bottom_sheet.getTag());
          }
     });
  }
    @Override
    public int getItemCount() {
        return ds_thu.size();
    }

    public void capnhat(){
        ds_thu = giaoDichDAO.getKhoanThu_Chi("Thu");
        khoanthu_adapter = new Khoanthu_Adapter(ds_thu, context);
        rv_thu.setAdapter(khoanthu_adapter);
    }


}

