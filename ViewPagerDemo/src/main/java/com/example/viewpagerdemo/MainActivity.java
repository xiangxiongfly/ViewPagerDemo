package com.example.viewpagerdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.viewpagerdemo.guide.GuideActivity;
import com.example.viewpagerdemo.lazy.LazyActivity;
import com.example.viewpagerdemo.tab.TabVpActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnGuide;
    private Context context;
    private Button btnTabLayout;
    private Button btnLazy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        initViews();
    }

    private void initViews() {
        btnGuide = findViewById(R.id.btnGuide);
        btnGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, GuideActivity.class));
            }
        });

        btnTabLayout = findViewById(R.id.btnTabLayout);
        btnTabLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, TabVpActivity.class));
            }
        });

        btnLazy = findViewById(R.id.btnLazy);
        btnLazy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, LazyActivity.class));
            }
        });
    }
}