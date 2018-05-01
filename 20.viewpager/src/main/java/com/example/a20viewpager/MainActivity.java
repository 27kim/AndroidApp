package com.example.a20viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager pager = findViewById(R.id.viewPager);
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter{
        ArrayList<Fragment> fragments;

        public  MyPagerAdapter(FragmentManager manager){
            super(manager);
            fragments = new ArrayList();
            fragments.add(new OneFragment());
            fragments.add(new ThreeFragment());
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }
    }
}
