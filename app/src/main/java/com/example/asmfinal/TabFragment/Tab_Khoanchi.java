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

import com.example.asmfinal.Adapter.Khoanchi_Adapter;
import com.example.asmfinal.DAO.GiaodichDAO;
import com.example.asmfinal.Dialog.BottomSheet_add_chi;
import com.example.asmfinal.R;
import com.example.asmfinal.model.GiaoDich;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Tab_Khoanchi extends Fragment {
    FloatingActionButton fl_khoanchi;
    public static RecyclerView rv_chi;
    public static Khoanchi_Adapter khoanchi_adapter;
    public static ArrayList<GiaoDich> ds_khoanchi;
    GiaodichDAO giaodichdao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.tab_khoanchi,container,false);
        rv_chi = view.findViewById(R.id.rv_khoanchi);
        rv_chi.setLayoutManager(new LinearLayoutManager(getContext()));
        fl_khoanchi = view.findViewById(R.id.fl_khoanchi);
        fl_khoanchi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("trangthai", "Loai chi");

                BottomSheet_add_chi bottom_sheet = new BottomSheet_add_chi();
                //bottom_sheet.show(((AppCompatActivity)context).getSupportFragmentManager(),"TAG");
                bottom_sheet.setArguments(args);
                bottom_sheet.show(getFragmentManager(),bottom_sheet.getTag());
            }
        });
        ds_khoanchi = new ArrayList<>();
        giaodichdao = new GiaodichDAO(getContext());

        ds_khoanchi = giaodichdao.getKhoanThu_Chi("Chi");
        khoanchi_adapter = new Khoanchi_Adapter(ds_khoanchi, getContext());
        rv_chi.setAdapter(khoanchi_adapter);
        return view;
    }
}
