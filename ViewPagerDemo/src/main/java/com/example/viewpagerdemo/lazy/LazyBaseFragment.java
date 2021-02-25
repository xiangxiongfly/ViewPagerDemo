package com.example.viewpagerdemo.lazy;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class LazyBaseFragment extends Fragment {
    private boolean isViewInited = false;
    private boolean isVisibleToUser = false;
    private boolean isDataInited = false;

    protected Context mContext;
    protected View mView;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = view;
        initViews(mView);
    }

    /**
     * 布局加载完后获取数据
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.isViewInited = true;
        prepareFetchData();
    }

    /**
     * 页面可见时获取数据
     */
    @Override
    public void setUserVisibleHint(boolean isVisible) {
        super.setUserVisibleHint(isVisible);
        this.isVisibleToUser = isVisible;
        if (this.isVisibleToUser) {
            prepareFetchData();
        }
    }

    public void prepareFetchData() {
        prepareFetchData(false);
    }

    /**
     * 是否强制刷新数据
     */
    public void prepareFetchData(boolean forceUpdate) {
        if (isViewInited && isVisibleToUser && (!isDataInited || forceUpdate)) {
            this.isDataInited = true;
            loadData();
        }
    }

    protected abstract int getLayoutId();

    protected abstract void initViews(View view);

    protected abstract void loadData();

    @Override
    public void onDestroy() {
        super.onDestroy();
        mView = null;
    }
}
