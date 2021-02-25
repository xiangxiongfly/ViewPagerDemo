package com.example.viewpagerdemo.guide;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.viewpagerdemo.R;
import com.example.viewpagerdemo.UiUtils;

import java.util.ArrayList;

//引导页
public class GuideActivity extends AppCompatActivity implements ViewTreeObserver.OnGlobalLayoutListener, ViewPager.OnPageChangeListener {
    private int[] imgIds = {R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4};
    private ViewPager vpGuide;
    private LinearLayout indicator;
    private LinearLayout indicator2;
    private ArrayList<ImageView> imageViewList = new ArrayList<>();
    private View pointSel;
    private int distance;
    private Context context;
    private ArrayList<View> dotList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        context = this;
        initViews();
        initData();

        pointSel.getViewTreeObserver().addOnGlobalLayoutListener(this);
        vpGuide.setAdapter(new GuideAdapter(imageViewList));
        vpGuide.addOnPageChangeListener(this);
    }

    private void initData() {
        int width = UiUtils.dp2px(context, 10);
        for (int i = 0; i < imgIds.length; i++) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(imgIds[i]);
            imageViewList.add(imageView);

            View point = new View(this);
            point.setBackgroundResource(R.drawable.shape_dot_normal);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, width);
            if (i != 0) {
                params.leftMargin = width;
            }
            point.setLayoutParams(params);
            indicator.addView(point);
        }

        dotList = new ArrayList<>(imgIds.length);
        for (int i = 0; i < imgIds.length; i++) {
            View dot = new View(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, width);
            if (i != 0) {
                params.leftMargin = width;
            } else {
                dot.setSelected(true);
            }
            dot.setLayoutParams(params);
            dot.setBackgroundResource(R.drawable.selector_dot);
            dotList.add(dot);
            indicator2.addView(dot);
        }
    }

    private void initViews() {
        vpGuide = findViewById(R.id.vpGuide);
        indicator = findViewById(R.id.indicator);
        indicator2 = findViewById(R.id.indicator2);
        pointSel = findViewById(R.id.pointSel);
    }

    @Override
    public void onGlobalLayout() {
        pointSel.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        distance = indicator.getChildAt(1).getLeft() - indicator.getChildAt(0).getLeft();
    }

    /**
     * ViewPager 滑动监听
     *
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int leftMargin = (int) (position * distance + positionOffset * distance);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) pointSel.getLayoutParams();
        params.leftMargin = leftMargin;
        pointSel.setLayoutParams(params);
    }

    /**
     * ViewPager 选中监听
     *
     * @param position 选中坐标
     */
    @Override
    public void onPageSelected(int position) {
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

    }
}

