package com.water.uisample;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.water.uisample.adapter.PagerListAdapter;

import java.util.ArrayList;

public class ViewPagerThreeActivity extends AppCompatActivity {
    private ViewPager vpThree;
    private ArrayList<View> _views;
    private ArrayList<String> _titles;
    private PagerListAdapter _adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_three);
        vpThree = (ViewPager) findViewById(R.id.vpThree);
        _views = new ArrayList<View>();
        LayoutInflater li = getLayoutInflater();
        _views.add(li.inflate(R.layout.page_ad_one,null,false));
        _views.add(li.inflate(R.layout.page_ad_two,null,false));
        _views.add(li.inflate(R.layout.page_ad_three, null, false));
        _views.add(li.inflate(R.layout.page_ad_four, null, false));
        _titles = new ArrayList<String>();
        _titles.add("第一页");
        _titles.add("第二页");
        _titles.add("第三页");
        _titles.add("第四页");
        _adapter = new PagerListAdapter(_views, _titles);
        vpThree.setAdapter(_adapter);
    }
}
