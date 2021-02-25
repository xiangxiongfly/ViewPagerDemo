package com.example.viewpager2demo.guide;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.viewpager2demo.R;
import com.example.viewpager2demo.UiUtils;

import java.util.ArrayList;

public class GuideActivity extends AppCompatActivity {
    private int[] imgIds = {R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4};
    private LinearLayout indicator;
    private LinearLayout indicator2;
    private ViewPager2 viewPager2;
    private ArrayList<View> dotList;
    private Context context;
    private int distance;
    private View point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        context = this;
        initViews();
        initData();

        indicator2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                indicator2.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                distance = indicator2.getChildAt(1).getLeft() - indicator2.getChildAt(0).getLeft();
            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                int leftMargin = (int) (distance * (position + positionOffset));
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) point.getLayoutParams();
                params.leftMargin = leftMargin;
                point.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                for (int i = 0; i < dotList.size(); i++) {
                    if (i == position) {
                        dotList.get(i).setSelected(true);
                    } else {
                        dotList.get(i).setSelected(false);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    private void initViews() {
        indicator = findViewById(R.id.indicator);
        indicator2 = findViewById(R.id.indicator2);
        viewPager2 = findViewById(R.id.viewPager2);
        point = findViewById(R.id.point);

        GuideAdapter mAdapter = new GuideAdapter(this, imgIds);
        viewPager2.setAdapter(mAdapter);
    }

    private void initData() {
        int width = UiUtils.dp2px(context, 10);

        for (int i = 0; i < imgIds.length; i++) {
            View dot = new View(context);
            dot.setBackgroundResource(R.drawable.shape_dot_normal);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, width);
            if (i != 0) {
                params.leftMargin = width;
            }
            dot.setLayoutParams(params);
            indicator.addView(dot);
        }

        dotList = new ArrayList<>(imgIds.length);
        for (int i = 0; i < imgIds.length; i++) {
            View dot = new View(context);
            dot.setBackgroundResource(R.drawable.selector_dot);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, width);
            if (i != 0) {
                params.leftMargin = width;
            } else {
                dot.setSelected(true);
            }
            dot.setLayoutParams(params);
            indicator2.addView(dot);
            dotList.add(dot);
        }
    }
}