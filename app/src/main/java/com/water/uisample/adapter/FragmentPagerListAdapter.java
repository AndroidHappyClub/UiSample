package com.water.uisample.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

public class FragmentPagerListAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> _fras = null;

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

