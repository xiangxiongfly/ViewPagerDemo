package com.example.viewpager2demo.tabs;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.viewpager2demo.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class TabActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        viewPager2 = findViewById(R.id.viewPager2);
        tabLayout = findViewById(R.id.tabLayout);

        ArrayList<SimpleFragment> fragmentList = new ArrayList<>();
        fragmentList.add(SimpleFragment.newInstance("AAA"));
        fragmentList.add(SimpleFragment.newInstance("BBB"));
        fragmentList.add(SimpleFragment.newInstance("CCC"));
        fragmentList.add(SimpleFragment.newInstance("DDD"));
        ArrayList<String> titleList = new ArrayList<>();
        titleList.add("One");
        titleList.add("Two");
        titleList.add("Three");
        titleList.add("Four");

        TabAdapter mAdapter = new TabAdapter(this, fragmentList);
        viewPager2.setAdapter(mAdapter);

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titleList.get(position));
            }
        }).attach();
    }
}