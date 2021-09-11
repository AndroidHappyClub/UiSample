package com.water.uisample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.water.uisample.adapter.PagerListAdapter;

import java.util.ArrayList;

public class ViewPagerOneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.water.uisample.databinding.ActivityViewPagerOneBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_view_pager_one);

        LinearLayout linearLayout = new LinearLayout(this);

        ArrayList<View> _views = new ArrayList<>();
        LayoutInflater li = getLayoutInflater();
        _views.add(li.inflate(R.layout.page_ad_one,linearLayout,false));
        _views.add(li.inflate(R.layout.page_ad_two,linearLayout,false));
        _views.add(li.inflate(R.layout.page_ad_three,linearLayout,false));
        PagerListAdapter _adapter = new PagerListAdapter(_views);
        binding.vpOne.setAdapter(_adapter);
    }
}
