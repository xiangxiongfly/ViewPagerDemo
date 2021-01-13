package com.example.viewpagerdemo.guide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.viewpagerdemo.R;
import com.example.viewpagerdemo.UiUtils;

import java.util.ArrayList;

//引导页
public class GuideActivity extends AppCompatActivity implements ViewTreeObserver.OnGlobalLayoutListener, ViewPager.OnPageChangeListener {
    private int[] imgIds = {R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4};
    private ViewPager vpGuide;
    private LinearLayout indicator;
    private ArrayList<ImageView> imageViewList = new ArrayList<>();
    private View pointSel;
    private int distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initViews();
        initData();
        pointSel.getViewTreeObserver().addOnGlobalLayoutListener(this);
        vpGuide.setAdapter(new GuideAdapter(imageViewList));
        vpGuide.addOnPageChangeListener(this);
    }

    private void initData() {
        int width = UiUtils.dp2px(this, 10);
        for (int i = 0; i < imgIds.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(imgIds[i]);
            imageViewList.add(imageView);

            View point = new View(this);
            point.setBackgroundResource(R.drawable.shape_normal);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, width);
            if (i != 0) {
                params.leftMargin = width;
            }
            point.setLayoutParams(params);
            indicator.addView(point);
        }
    }

    private void initViews() {
        vpGuide = findViewById(R.id.vpGuide);
        indicator = findViewById(R.id.indicator);
        pointSel = findViewById(R.id.pointSel);
    }

    @Override
    public void onGlobalLayout() {
        pointSel.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        distance = indicator.getChildAt(1).getLeft() - indicator.getChildAt(0).getLeft();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int leftMargin = (int) (position * distance + positionOffset * distance);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) pointSel.getLayoutParams();
        params.leftMargin = leftMargin;
        pointSel.setLayoutParams(params);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

