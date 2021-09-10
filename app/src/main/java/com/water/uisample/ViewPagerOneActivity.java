package com.water.uisample;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.water.uisample.adapter.PagerListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerOneActivity extends AppCompatActivity {
    private ViewPager vpOne;
    private ArrayList<View> _views;
    private PagerListAdapter _adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_one);
        vpOne = (ViewPager) findViewById(R.id.vpOne);

        _views = new ArrayList<View>();
        LayoutInflater li = getLayoutInflater();
        _views.add(li.inflate(R.layout.page_ad_one,null,false));
        _views.add(li.inflate(R.layout.page_ad_two,null,false));
        _views.add(li.inflate(R.layout.page_ad_three,null,false));
        _adapter = new PagerListAdapter(_views);
        vpOne.setAdapter(_adapter);
    }
}