package com.water.uisample.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class PagerListAdapter extends PagerAdapter {
    private ArrayList<View> _views;
    private ArrayList<String> _titles;

    public PagerListAdapter() {
    }

    public PagerListAdapter(ArrayList<View> views)
    {
        _views = views;
    }

    public PagerListAdapter(ArrayList<View> views,
                            ArrayList<String> titles)
    {
        _views = views;
        _titles = titles;
    }

    /* 获得viewpager中有多少个view */
    @Override
    public int getCount() {
        return _views.size();
    }

    // 判断instantiateItem(ViewGroup, int)函数所返回来的Key与一个页面视图是否是
    // 代表的同一个视图(即它俩是否是对应的，对应的表示同一个View)，通常我们直接写
    // return view == object 就可以了
    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }

    // ①将给定位置的view添加到ViewGroup(容器)中,创建并显示出来
    // ②返回一个代表新增页面的Object(key),通常都是直接返回view本身就可以了,
    // 当然你也可以自定义自己的key,但是key和每个view要一一对应的关系
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(_views.get(position));
        return _views.get(position);
    }

    // 移除一个给定位置的页面。适配器有责任从容器中删除这个视图。这是为了确保在
    // finishUpdate(viewGroup)返回时视图能够被移除。
    @Override
    public void destroyItem(ViewGroup container,
                            int position, Object object) {
        container.removeView(_views.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return _titles.get(position);
    }
}
