package com.example.asmfinal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.asmfinal.Adapter.TabAdapter;
import com.example.asmfinal.TabFragment.Tab_Khoanchi;
import com.example.asmfinal.TabFragment.Tab_Khoanthu;
import com.google.android.material.tabs.TabLayout;


public class Fragment_thuchi extends Fragment {
        private TabAdapter adapter;
        private TabLayout tabLayout;
        private ViewPager viewPager;

    public Fragment_thuchi() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thuchi, container, false);
        viewPager= view.findViewById(R.id.viewPager);
        tabLayout= view.findViewById(R.id.tabLayout);

        adapter= new TabAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new Tab_Khoanthu(),"Khoản thu");
        adapter.addFragment(new Tab_Khoanchi(),"Khoản chi");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}