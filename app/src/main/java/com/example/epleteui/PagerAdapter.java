package com.example.epleteui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;
import java.util.Objects;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragmentList;

    public PagerAdapter(FragmentManager fragment, List<Fragment> list) {
        super(fragment);
        this.fragmentList = list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get( position );
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
