package com.example.asmfinal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.asmfinal.TabFragment.Fragment_tk_chi;
import com.example.asmfinal.TabFragment.Fragment_tk_thu;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class Fragment_Thongke extends Fragment {

    public Fragment_Thongke() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongke, container, false);

        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_nv);
        if (savedInstanceState == null){
            loadFragment(new Fragment_tk_thu());
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.item1:
                        Toast.makeText(getContext(), "Bạn chọn thống kê thu", Toast.LENGTH_SHORT).show();
                        fragment = new Fragment_tk_thu();
                        loadFragment(fragment);
                        return true;
                    case R.id.item2:
                        Toast.makeText(getContext(), "Bạn chọn thống kê chi", Toast.LENGTH_SHORT).show();
                        fragment = new Fragment_tk_chi();
                        loadFragment(fragment);
                        return true;
                }
                return false;
            }
        });
        return view;
    }
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fr_, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}