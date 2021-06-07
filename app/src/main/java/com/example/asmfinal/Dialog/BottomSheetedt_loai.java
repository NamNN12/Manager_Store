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
import com.example.asmfinal.R;
import com.example.asmfinal.model.LoaiGD;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

import static com.example.asmfinal.Fragment_phanloai.loaiGDAdapters;
import static com.example.asmfinal.Fragment_phanloai.rv_loaigd;

public class BottomSheetedt_loai extends BottomSheetDialogFragment {

    EditText edt_edit_l;
    Spinner sp_edit_l;
    Button btnupdate_l;
    LoaiGDDAO loaiGDDAO;
    ArrayList<LoaiGD> ds_loaigd;
    int MaLoai;
    public  void Bottom_sheet(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_edt_phanloai,container,false);
        edt_edit_l= view.findViewById(R.id.edt_tenloai_edt);
        sp_edit_l= view.findViewById(R.id.sp_phanloai_edt);
        btnupdate_l= view.findViewById(R.id.btn_update_edt);
        loaiGDDAO= new LoaiGDDAO(getContext());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_edit_l.setAdapter(adapter);

        Bundle mArgs = getArguments();
        MaLoai = Integer.parseInt(mArgs.getString("MaLoai"));
        String ten_loai = mArgs.getString("TenLoai");
        String trang_thai = mArgs.getString("TrangThai");
        selectSpinnerValue(sp_edit_l, trang_thai);

        edt_edit_l.setText(ten_loai);
        btnupdate_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten_loai =edt_edit_l.getText().toString();
                String loai = sp_edit_l.getSelectedItem().toString();
                LoaiGD loaiGD = new LoaiGD(MaLoai,ten_loai,loai);
                loaiGDDAO.update(loaiGD);
                capnhat();
                Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

        return view;
    }
    private void selectSpinnerValue(Spinner spinner, String myString)
    {
        int index = 0;
        for(int i = 0; i < spinner.getCount(); i++){
            if(spinner.getItemAtPosition(i).toString().equals(myString)){
                spinner.setSelection(i);
                break;
            }
        }
    }


    public void capnhat(){
        ds_loaigd = new ArrayList<>();
        ds_loaigd= (ArrayList<LoaiGD>) loaiGDDAO.getAll();
        loaiGDAdapters = new LoaiGDAdapter(ds_loaigd,getContext());
        rv_loaigd.setAdapter(loaiGDAdapters);
    }
}
