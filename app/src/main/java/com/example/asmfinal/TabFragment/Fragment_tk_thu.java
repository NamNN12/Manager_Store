package com.example.asmfinal.TabFragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asmfinal.Adapter.Tk_thu_Adapter;
import com.example.asmfinal.DAO.GiaodichDAO;
import com.example.asmfinal.model.GiaoDich;
import com.example.asmfinal.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Fragment_tk_thu extends Fragment {
    Button btn_date1, btn_date2;
    TextView tv_total;
    RecyclerView rv_tk_thu;
    public static Tk_thu_Adapter khoanthu_adapter;
    public static ArrayList<GiaoDich> ds_tk_thu;
    public static ArrayList<GiaoDich> ds_tk_thus;
    GiaodichDAO giaodichdao;
    double total_1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tk_thu, container, false);
        btn_date1 = view.findViewById(R.id.btn_date1);
        btn_date2 = view.findViewById(R.id.btn_date2);
        tv_total = view.findViewById(R.id.tv_total);
        rv_tk_thu = view.findViewById(R.id.rv_tk_thu);
        rv_tk_thu.setLayoutManager(new LinearLayoutManager(getContext()));
        giaodichdao = new GiaodichDAO(getContext());

        DecimalFormat formatter = new DecimalFormat("#,###");
        total_1 = giaodichdao.getTotalthu();
        String s = formatter.format(total_1);
        tv_total.setText("Tổng tiền: "+s+" VNĐ");

        btn_date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date1();
            }
        });
        btn_date2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date2();
            }
        });

        ds_tk_thu = new ArrayList<>();
        giaodichdao = new GiaodichDAO(getContext());

        ds_tk_thu = giaodichdao.getKhoanThu_Chi("Thu");
        khoanthu_adapter = new Tk_thu_Adapter(ds_tk_thu, getContext());
        rv_tk_thu.setAdapter(khoanthu_adapter);
        return view;
    }
    private void Date1(){
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);

        final int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        final int months = cal.get(Calendar.MONTH);
        final int years = cal.get(Calendar.YEAR);
        final Calendar calendar = Calendar.getInstance();
        int date = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                calendar.set(i,i1,i2);
                btn_date1.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },years,months,dayOfWeek);
        datePickerDialog.show();
    }

    private void Date2(){
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);

        final int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        final int months = cal.get(Calendar.MONTH);
        final int years = cal.get(Calendar.YEAR);
        final Calendar calendar = Calendar.getInstance();
        int date = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                calendar.set(i,i1,i2);
                btn_date2.setText(simpleDateFormat.format(calendar.getTime()));

                ds_tk_thus = giaodichdao.get_Tk_Thu(btn_date1.getText().toString(), btn_date2.getText().toString());
                khoanthu_adapter = new Tk_thu_Adapter(ds_tk_thus, getContext());
                rv_tk_thu.setAdapter(khoanthu_adapter);

                String date1 = btn_date1.getText().toString();
                DecimalFormat formatter = new DecimalFormat("#,###");
                float total_2 = (float) giaodichdao.getTotalthubyDate(date1, simpleDateFormat.format(calendar.getTime()));
                String s = formatter.format(total_2);
                tv_total.setText("Tổng tiền: "+s+"VNĐ");




            }
        },years,months,dayOfWeek);


        datePickerDialog.show();

    }

}
