package com.water.uisample;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.water.uisample.databinding.ActivityBottomViewPagerBinding;
import com.water.uisample.fragment.FragmentSample;
import com.water.uisample.fragment.FragmentThree;
import com.water.uisample.fragment.FragmentTwo;

public class BottomViewPagerActivity extends AppCompatActivity implements
        RadioGroup.OnCheckedChangeListener {

    private ScreenFragmentAdapter screenFragmentAdapter;

    private ActivityBottomViewPagerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_bottom_view_pager);

        screenFragmentAdapter = new ScreenFragmentAdapter(this);

        bindViews();

        binding.rgTabBar.check(R.id.rbChannel);
    }

    private void bindViews() {
        binding.rgTabBar.setOnCheckedChangeListener(this);

        binding.vpContainer.setAdapter(screenFragmentAdapter);
        binding.vpContainer.setCurrentItem(0);
        binding.vpContainer.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                ((RadioButton) binding.rgTabBar.getChildAt(position)).setChecked(true);
            }
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton rb = findViewById(checkedId);

        if (rb!= null)
            binding.vpContainer.setCurrentItem(Integer.parseInt(rb.getTag().toString()));
    }

    private static class ScreenFragmentAdapter extends FragmentStateAdapter {

        public ScreenFragmentAdapter(AppCompatActivity appCompatActivity){
            super(appCompatActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch(position) {
                case 1:
                    return new FragmentTwo();

                case 2:
                    return new FragmentThree();

                default:
                    return new FragmentSample();
            }
        }

        @Override
        public int getItemCount() {
            return 4;
        }
    }
}