package com.water.uisample;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import com.water.uisample.adapter.PagerListAdapter;
import com.water.uisample.databinding.ActivityViewPagerFourBinding;

import java.util.ArrayList;

public class ViewPagerFourActivity extends AppCompatActivity implements
        View.OnClickListener,
        ViewPager.OnPageChangeListener {

    private ActivityViewPagerFourBinding binding;

    private final String[] _titles = {"第一页", "第二页", "第三页", "第四页"};

    private final int[] _pages = {
            R.layout.page_ad_one,
            R.layout.page_ad_two,
            R.layout.page_ad_three,
            R.layout.page_ad_four
    };
    private int _offset = 0;//移动条图片的偏移量
    private int _currIndex = 0;//当前页面的编号
    private int _lineWidth;// 移动条图片的长度
    //移动条滑动一页的距离
    //滑动条移动两页的距离

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_pager_four);
        initViews();
    }


    private void initViews() {

        for (int index = 0; index < _titles.length; index++) {
            TextView tv = new TextView(this);
            tv.setText(_titles[index]);
            tv.setTextColor(Color.rgb(0, 0, 0));
            tv.setGravity(Gravity.CENTER);
            tv.setTag(index);
            tv.setOnClickListener(this);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            );
            lp.gravity = Gravity.CENTER;

            binding.llTitle.addView(tv, lp);
        }

        //下划线动画的相关设置：
        _lineWidth = BitmapFactory.decodeResource(getResources(), R.mipmap.line).getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int sw = dm.widthPixels;// 获取分辨率宽度
        _offset = (sw / _titles.length - _lineWidth) / 2;// 计算偏移量
        Matrix matrix = new Matrix();
        matrix.postTranslate(_offset, 0);
        binding.ivCursor.setImageMatrix(matrix);// 设置动画初始位置

        //往ViewPager填充View，同时设置点击事件与页面切换事件
        ArrayList<View> _views = new ArrayList<>();
        LayoutInflater li = getLayoutInflater();
        for (int id : _pages) {
            _views.add(li.inflate(id, null, false));
        }
        binding.vpFour.setAdapter(new PagerListAdapter(_views));
        binding.vpFour.setCurrentItem(0);          //设置ViewPager当前页，从0开始算

        binding.vpFour.addOnPageChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        binding.vpFour.setCurrentItem((int) v.getTag());
    }

    @Override
    public void onPageSelected(int index) {
        Animation animation;

        int from = _lineWidth * _currIndex + _offset * _currIndex * 2;
        int to = _lineWidth * index + _offset * index * 2;

        animation = new TranslateAnimation(from, to, 0, 0);

        _currIndex = index;
        animation.setFillAfter(true);// true表示图片停在动画结束位置
        animation.setDuration(300); //设置动画时间为300毫秒
        binding.ivCursor.startAnimation(animation);//开始动画
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }
}