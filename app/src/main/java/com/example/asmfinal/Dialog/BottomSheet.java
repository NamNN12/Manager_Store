package com.example.asmfinal.Dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.asmfinal.Adapter.LoaiGDAdapter;
import com.example.asmfinal.DAO.LoaiGDDAO;
import com.example.asmfinal.model.LoaiGD;
import com.example.asmfinal.R;
import com.example.asmfinal.model.LoaiGD;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

import static com.example.asmfinal.Fragment_phanloai.loaiGDAdapters;
import static com.example.asmfinal.Fragment_phanloai.rv_loaigd;

public class BottomSheet extends BottomSheetDialogFragment {
    EditText edt_tenloai;
    Spinner sp_loai;
    Button btn_them;
    LoaiGD loaiGD;
    ArrayList<LoaiGD> ds_phanloai;
    LoaiGDDAO loaiGDDAO;
    public BottomSheet(){
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_phanloai, container, false);
        edt_tenloai = view.findViewById(R.id.edt_tenloai);
        sp_loai = view.findViewById(R.id.sp_phanloai);
        btn_them = view.findViewById(R.id.btn_thempl);
        loaiGDDAO  = new LoaiGDDAO(getContext());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_loai.setAdapter(adapter);

        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten_loai = edt_tenloai.getText().toString();
                String phanloai = sp_loai.getSelectedItem().toString();
                LoaiGD pl = new LoaiGD(ten_loai,phanloai);
                loaiGDDAO.them(pl);
                capnhat();
                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });


        return view;
    }


    public void capnhat(){
        ds_phanloai = new ArrayList<>();
        ds_phanloai = loaiGDDAO.getAll();
        loaiGDAdapters = new LoaiGDAdapter(ds_phanloai, getContext());
        rv_loaigd.setAdapter(loaiGDAdapters);
    }
}
