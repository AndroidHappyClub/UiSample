package com.water.uisample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.water.uisample.adapter.PagerListAdapter;

import java.util.ArrayList;

public class ViewPagerTwoActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.water.uisample.databinding.ActivityViewPagerTwoBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_view_pager_two);

        LinearLayout linearLayout = new LinearLayout(this);

        ArrayList<View> _views = new ArrayList<>();
        LayoutInflater li = getLayoutInflater();
        _views.add(li.inflate(R.layout.page_ad_one, linearLayout,false));
        _views.add(li.inflate(R.layout.page_ad_two,linearLayout,false));
        _views.add(li.inflate(R.layout.page_ad_three, linearLayout, false));
        _views.add(li.inflate(R.layout.page_ad_four, linearLayout, false));
        ArrayList<String> _titles = new ArrayList<>();
        _titles.add("第一页");
        _titles.add("第二页");
        _titles.add("第三页");
        _titles.add("第四页");

        PagerListAdapter _adapter = new PagerListAdapter(_views, _titles);

        binding.vpTwo.setAdapter(_adapter);
    }
}
