package com.example.asmfinal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asmfinal.Adapter.LoaiGDAdapter;
import com.example.asmfinal.DAO.LoaiGDDAO;
import com.example.asmfinal.Dialog.BottomSheet;
import com.example.asmfinal.model.LoaiGD;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Fragment_phanloai extends Fragment {



    public Fragment_phanloai() {
        // Required empty public constructor
    }

    FloatingActionButton pl;
   public static LoaiGDAdapter loaiGDAdapters;
   public static RecyclerView rv_loaigd;
   ArrayList<LoaiGD> ds_loaigd;
   LoaiGDDAO loaiGDDAO;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_phanloai, container, false);
        pl = view.findViewById(R.id.fl_pl);
        rv_loaigd = view.findViewById(R.id.rv_pl);
        rv_loaigd.setLayoutManager(new LinearLayoutManager(getContext()));
        ds_loaigd = new ArrayList<>();

        loaiGDDAO= new LoaiGDDAO(getContext());

        pl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheet bottomSheet= new BottomSheet();
                bottomSheet.show(getFragmentManager(),"tag");

            }
        });
        ds_loaigd= (ArrayList<LoaiGD>) loaiGDDAO.getAll();
         loaiGDAdapters = new LoaiGDAdapter(ds_loaigd,getContext());
         rv_loaigd.setAdapter(loaiGDAdapters);
        return view;
    }
}