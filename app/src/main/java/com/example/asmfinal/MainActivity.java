package com.example.asmfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.colorOrange));

        drawer= findViewById(R.id.drawer_layout);
        toolbar=findViewById(R.id.toobar1);

        setSupportActionBar(toolbar);
        ActionBar ab=getSupportActionBar();

        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

            NavigationView nv =(NavigationView)findViewById(R.id.nvView);
        if(savedInstanceState == null){
            nv.setCheckedItem(R.id.nav_second_fragment);
            Fragment_thuchi fragmentOne= new Fragment_thuchi();

            FragmentManager manager =getSupportFragmentManager();
            manager.beginTransaction()
                    .replace(R.id.flContent,fragmentOne)
                    .commit();

        }
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.nav_first_fragment:
                        Toast.makeText(MainActivity.this, "Ban chon menu 1", Toast.LENGTH_SHORT).show();
                        Fragment_thuchi fragmentOne= new Fragment_thuchi();
                        FragmentManager manager =getSupportFragmentManager();
                        manager.beginTransaction()
                                .replace(R.id.flContent,fragmentOne)
                                .commit();
                        break;
                    case R.id.nav_second_fragment:
                        Toast.makeText(MainActivity.this, "Ban chon menu 2", Toast.LENGTH_SHORT).show();
                        Fragment_phanloai fragment2 = new Fragment_phanloai();

                        FragmentManager manager2=getSupportFragmentManager();
                        manager2.beginTransaction()
                                .replace(R.id.flContent,fragment2)
                                .commit();
                        break;

                    case R.id.nav_third_fragment:
                        Toast.makeText(MainActivity.this, "Ban chon menu 3", Toast.LENGTH_SHORT).show();
                        Fragment_Thongke fragmentThree = new Fragment_Thongke();

                        FragmentManager manager3=getSupportFragmentManager();
                        manager3.beginTransaction()
                                .replace(R.id.flContent,fragmentThree)
                                .commit();
                        break;
                    case R.id.sub1:
                        Toast.makeText(MainActivity.this, "Ban chon Submenu 1", Toast.LENGTH_SHORT).show();
                        FragmentGioithieu fragmentGioithieu = new FragmentGioithieu();

                        FragmentManager manager4=getSupportFragmentManager();
                        manager4.beginTransaction()
                                .replace(R.id.flContent,fragmentGioithieu)
                                .commit();
                        break;

                    case R.id.sub2:
                        Toast.makeText(MainActivity.this, "Ban chon Submenu 2", Toast.LENGTH_SHORT).show();
                        break;}
               item.setChecked(true);
                drawer.closeDrawers();
                return false;
            }

        });


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home)
            drawer.openDrawer(GravityCompat.START);

        return super.onOptionsItemSelected(item);
    }

}