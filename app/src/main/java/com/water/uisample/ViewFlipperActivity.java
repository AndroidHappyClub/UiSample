package com.water.uisample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.water.uisample.databinding.ActivityViewFlipperBinding;

public class ViewFlipperActivity extends AppCompatActivity
        implements View.OnTouchListener,
        View.OnClickListener {

    private float touchDownX;  // 手指按下的X坐标

    private ActivityViewFlipperBinding binding;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_flipper);

        binding.vfStatic.setOnTouchListener(this);

        binding.vfDynamic.addView(createView(R.mipmap.bg_one, "one"));
        binding.vfDynamic.addView(createView(R.mipmap.bg_two,"two"));
        binding.vfDynamic.addView(createView(R.mipmap.bg_three,"three"));
        binding.vfDynamic.addView(createView(R.mipmap.bg_four,"four"));
        binding.vfDynamic.addView(createView(R.mipmap.bg_five,"five"));
        binding.vfDynamic.setOnTouchListener(this);
        binding.vfDynamic.setOnClickListener(this);
        binding.vfDynamic.startFlipping();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ViewFlipper vf;

        if (v == binding.vfStatic)
            vf = binding.vfStatic;
        else
            vf = binding.vfDynamic;

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // 取得左右滑动时手指按下的X坐标
            touchDownX = event.getX();

            return v == binding.vfStatic;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            // 取得左右滑动时手指松开的X坐标
            //手指松开的X坐标
            float touchUpX = event.getX();
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
        ImageView iv = (ImageView) binding.vfDynamic.getCurrentView();
        Toast.makeText(ViewFlipperActivity.this, (String)iv.getTag(),
                Toast.LENGTH_SHORT).show();
    }
}