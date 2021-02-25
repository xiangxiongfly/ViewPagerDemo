package com.example.viewpager2demo.tabs;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class TabAdapter extends FragmentStateAdapter {

    private final ArrayList<SimpleFragment> mFragmentList;

    public TabAdapter(@NonNull FragmentActivity fragmentActivity, ArrayList<SimpleFragment> fragmentList) {
        super(fragmentActivity);
        mFragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return mFragmentList.size();
    }
}
