package com.water.uisample;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

public class ViewFlipperActivity extends AppCompatActivity
        implements View.OnTouchListener,
        View.OnClickListener {
    private ViewFlipper _svf;
    private ViewFlipper _dvf;
    private float touchDownX;  // 手指按下的X坐标
    private float touchUpX;  //手指松开的X坐标

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper);

        _svf = (ViewFlipper) findViewById(R.id.vfStatic);
        _svf.setOnTouchListener(this);

        _dvf = (ViewFlipper) findViewById(R.id.vfDynamic);
        _dvf.addView(createView(R.mipmap.bg_one, "one"));
        _dvf.addView(createView(R.mipmap.bg_two,"two"));
        _dvf.addView(createView(R.mipmap.bg_three,"three"));
        _dvf.addView(createView(R.mipmap.bg_four,"four"));
        _dvf.addView(createView(R.mipmap.bg_five,"five"));
        _dvf.setOnTouchListener(this);
        _dvf.setOnClickListener(this);
        _dvf.startFlipping();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ViewFlipper vf;

        if (v == _svf)
            vf =_svf;
        else
            vf = _dvf;

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // 取得左右滑动时手指按下的X坐标
            touchDownX = event.getX();

            if (v == _svf)
                return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            // 取得左右滑动时手指松开的X坐标
            touchUpX = event.getX();
            if (touchUpX - touchDownX > 100 || touchDownX - touchUpX > 100) {
                // 从左往右，看前一个View
                if (touchUpX - touchDownX > 100) {
                    // 显示上一屏动画
                    vf.setInAnimation(AnimationUtils.loadAnimation(this,
                            R.anim.right_in));
                    vf.setOutAnimation(AnimationUtils.loadAnimation(this,
                            R.anim.right_out));
                    // 显示上一屏的View
                    vf.showPrevious();
                    // 从右往左，看后一个View
                } else if (touchDownX - touchUpX > 100) {
                    //显示下一屏的动画
                    vf.setInAnimation(AnimationUtils.loadAnimation(this,
                            R.anim.left_in));
                    vf.setOutAnimation(AnimationUtils.loadAnimation(this,
                            R.anim.left_out));
                    // 显示下一屏的View
                    vf.showNext();
                }
                return true;
            }
        }
        // setOnTouchListener 和 setOnClickListener 同时使用时，
        // onTouch 的返回值要设为 false，这样既可以保证按下移动
        // 抬起事件可以被监听，并且点击事件也会被监听。
        return false;
    }

    private View createView(int resid, Object tag) {
        ImageView iv = new ImageView(this);
        iv.setImageResource(resid);
        iv.setTag(tag);
        // setOnClickListener和setOnTouchListener冲突
        // iv.setOnClickListener(this);
        return iv;
    }

    @Override
    public void onClick(View view) {
        ImageView iv = (ImageView)_dvf.getCurrentView();
        Toast.makeText(ViewFlipperActivity.this, (String)iv.getTag(),
                Toast.LENGTH_SHORT).show();
    }
}