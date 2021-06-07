package com.example.asmfinal.Dialog;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.asmfinal.Adapter.Adapter_sp_thu;
import com.example.asmfinal.Adapter.Khoanchi_Adapter;
import com.example.asmfinal.Adapter.Khoanthu_Adapter;
import com.example.asmfinal.DAO.GiaodichDAO;
import com.example.asmfinal.R;
import com.example.asmfinal.model.GiaoDich;
import com.example.asmfinal.model.LoaiGD;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.asmfinal.TabFragment.Tab_Khoanchi.khoanchi_adapter;
import static com.example.asmfinal.TabFragment.Tab_Khoanchi.rv_chi;

public class BottomSheet_add_chi extends BottomSheetDialogFragment {

    EditText edt_tieude,edttien,edtgichu;
    TextView tv_ngay,tv_trangthai;
    Spinner sp_thu;
    Button btnadd_gd;
    GiaodichDAO giaoDichDAO;
    ArrayList<LoaiGD> ds_loaigd_thu;
    ArrayList<GiaoDich> ds_thu;
    Adapter_sp_thu adapterSpThu;
    String trangthai_;

    public  void Bottom_sheet(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_gdi,container,false);
        edt_tieude= view.findViewById(R.id.edt_tieude);
        edttien= view.findViewById(R.id.edt_tien);
        edtgichu= view.findViewById(R.id.edt_ghichu);
        tv_ngay = view.findViewById(R.id.tv_ngay);
        tv_trangthai = view.findViewById(R.id.tv_trangthai);
        sp_thu= view.findViewById(R.id.sp_pl_giaodich);
        btnadd_gd= view.findViewById(R.id.btn_thempl);

        Bundle getdata = getArguments();
        trangthai_= getdata.getString("trangthai");
        tv_trangthai.setText(trangthai_);

        giaoDichDAO = new GiaodichDAO(getContext());
        ds_loaigd_thu = new ArrayList<>();

        ds_loaigd_thu = giaoDichDAO.getChi();
        adapterSpThu = new Adapter_sp_thu(ds_loaigd_thu,getContext());
        sp_thu.setAdapter(adapterSpThu);

        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);

        final int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        final int months = cal.get(Calendar.MONTH);
        final int years = cal.get(Calendar.YEAR);

        tv_ngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int date = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        calendar.set(i,i1,i2);
                        tv_ngay.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },years,months,dayOfWeek);
                datePickerDialog.show();
            }
        });





        btnadd_gd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = tv_ngay.getText().toString();
                String ten = edt_tieude.getText().toString();
                float tien = Float.parseFloat(edttien.getText().toString());
                String ghichu = edtgichu.getText().toString();
                int index = sp_thu.getSelectedItemPosition();
                int Maloai = ds_loaigd_thu.get(index).MaLoai;

                GiaoDich gd = new GiaoDich(ten,date,tien,ghichu,Maloai);
                giaoDichDAO = new GiaodichDAO(getContext());
                giaoDichDAO.them(gd);
                capnhat();
                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

        return view;
    }
    public void capnhat(){
        ds_thu = giaoDichDAO.getKhoanThu_Chi("Chi");
        khoanchi_adapter = new Khoanchi_Adapter(ds_thu, getContext());
        rv_chi.setAdapter(khoanchi_adapter);
    }

}
