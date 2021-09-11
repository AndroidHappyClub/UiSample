package com.water.uisample.adapter;

import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class FragmentPagerListAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> _fras;

    public FragmentPagerListAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        _fras = fragments;
    }


    @Override
    public int getCount() {
        return _fras.size();
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        System.out.println("position Destory" + position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        return _fras.get(position);
    }


}

