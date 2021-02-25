package com.example.viewpagerdemo.lazy;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.viewpagerdemo.R;

public class SimpleFragment extends LazyBaseFragment {

    private Button btnUpdate;
    private TextView desc;
    private String content;

    public static SimpleFragment newInstance(String content) {
        SimpleFragment simpleFragment = new SimpleFragment();
        Bundle bundle = new Bundle();
        bundle.putString("content", content);
        simpleFragment.setArguments(bundle);
        return simpleFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_simple;
    }

    @Override
    protected void initViews(View view) {
        Bundle args = getArguments();
        content = args.getString("content");

        btnUpdate = mView.findViewById(R.id.btnUpdate);
        desc = mView.findViewById(R.id.desc);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepareFetchData(true);
            }
        });
    }

    @Override
    protected void loadData() {
        desc.setText(content);
        Log.e("TAG", content);
        Toast.makeText(mContext, content, Toast.LENGTH_SHORT).show();
    }
}
