package com.example.asmfinal.TabFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asmfinal.Adapter.Khoanthu_Adapter;
import com.example.asmfinal.DAO.GiaodichDAO;
import com.example.asmfinal.Dialog.BottomSheet_add_thu;
import com.example.asmfinal.R;
import com.example.asmfinal.model.GiaoDich;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Tab_Khoanthu extends Fragment {
    FloatingActionButton fl_khoanthu;
    public static RecyclerView rv_thu;
    public static Khoanthu_Adapter khoanthu_adapter;
    public static ArrayList<GiaoDich> ds_khoanthu;
    GiaodichDAO giaoDichDAO;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.tab_khoanthu,container,false);
        fl_khoanthu = view.findViewById(R.id.fl_khoan_thu);
        rv_thu = view.findViewById(R.id.rv_khoan_thu);
        rv_thu.setLayoutManager(new LinearLayoutManager(getContext()));

        fl_khoanthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("trangthai", "Loai thu");

                BottomSheet_add_thu bottom_sheet = new BottomSheet_add_thu();

                bottom_sheet.setArguments(args);
                bottom_sheet.show(getFragmentManager(),bottom_sheet.getTag());
            }
        });
        ds_khoanthu = new ArrayList<>();
        giaoDichDAO = new GiaodichDAO(getContext());

        ds_khoanthu = giaoDichDAO.getKhoanThu_Chi("Thu");
        khoanthu_adapter = new Khoanthu_Adapter(ds_khoanthu, getContext());
        rv_thu.setAdapter(khoanthu_adapter);

        return view;
    }
}
