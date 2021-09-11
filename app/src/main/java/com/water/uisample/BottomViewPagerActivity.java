package com.water.uisample;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.water.uisample.adapter.FragmentPagerListAdapter;
import com.water.uisample.fragment.FragmentSample;
import com.water.uisample.fragment.FragmentThree;
import com.water.uisample.fragment.FragmentTwo;

import java.util.ArrayList;

public class BottomViewPagerActivity extends AppCompatActivity implements
        RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    private RadioGroup rgTabBar;
    private ViewPager _vp;
    private ArrayList<Fragment> _fras;

    private FragmentPagerListAdapter _adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_view_pager);

        _fras = new ArrayList<Fragment>();
        _fras.add(new FragmentSample());
        _fras.add(new FragmentTwo());
        _fras.add(new FragmentThree());
        _fras.add(new FragmentSample());
        _adapter = new FragmentPagerListAdapter(getSupportFragmentManager(),
                _fras);

        bindViews();

        rgTabBar.check(R.id.rbChannel);
    }

    private void bindViews() {
        rgTabBar = (RadioGroup) findViewById(R.id.rgTabBar);
        rgTabBar.setOnCheckedChangeListener(this);

        _vp = (ViewPager) findViewById(R.id.vpContainter);
        _vp.setAdapter(_adapter);
        _vp.setCurrentItem(0);
        _vp.addOnPageChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton rb = (RadioButton) findViewById(checkedId);

        if (rb!= null)
            _vp.setCurrentItem(Integer.parseInt(rb.getTag().toString()));
    }


    //重写ViewPager页面切换的处理方法
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
        if (state == 2) {
            ((RadioButton)rgTabBar.getChildAt(
                    _vp.getCurrentItem())).setChecked(true);
        }
    }
}
