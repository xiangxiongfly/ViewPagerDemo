package com.example.viewpagerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.viewpagerdemo.guide.GuideActivity;
import com.example.viewpagerdemo.tab.TabVpActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnGuide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        btnGuide = findViewById(R.id.btnGuide);
        Button btnTabLayout = findViewById(R.id.btnTabLayout);
        btnGuide.setOnClickListener(this);
        btnTabLayout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGuide:
                startActivity(new Intent(this, GuideActivity.class));
                break;
            case R.id.btnTabLayout:
                startActivity(new Intent(this, TabVpActivity.class));
                break;
        }
    }
}