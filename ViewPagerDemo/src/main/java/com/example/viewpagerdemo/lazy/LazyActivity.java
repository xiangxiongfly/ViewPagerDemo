package com.example.viewpagerdemo.lazy;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.viewpagerdemo.R;

import java.util.ArrayList;

public class LazyActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ArrayList<SimpleFragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lazy);
        viewPager = findViewById(R.id.viewPager);
        fragments = new ArrayList<>();
        fragments.add(SimpleFragment.newInstance("AAA"));
        fragments.add(SimpleFragment.newInstance("BBB"));
        fragments.add(SimpleFragment.newInstance("CCC"));
        fragments.add(SimpleFragment.newInstance("DDD"));

        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(fragments.size());
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}