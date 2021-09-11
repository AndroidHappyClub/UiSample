package com.water.uisample.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class FragmentPagerListAdapter extends FragmentPagerAdapter {

    private final ArrayList<Fragment> _fragments;

    public FragmentPagerListAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        _fragments = fragments;
    }


    @Override
    public int getCount() {
        return _fragments.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        System.out.println("position Destroy" + position);
        super.destroyItem(container, position, object);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return _fragments.get(position);
    }


}

