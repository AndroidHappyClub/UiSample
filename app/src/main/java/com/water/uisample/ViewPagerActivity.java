package com.water.uisample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.water.uisample.databinding.ActivityViewPagerBinding;

public class ViewPagerActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityViewPagerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_pager);
        initViews();
    }

    private void initViews() {
        binding.btnOne.setOnClickListener(this);
        binding.btnTwo.setOnClickListener(this);
        binding.btnThree.setOnClickListener(this);
        binding.btnFour.setOnClickListener(this);
    }

    /**
     * Reasons for using if statements:
     * <p>Resource IDs will be non-final by default in Android Gradle Plugin version 8.01,
     * avoid using them in switch case statements<p/>
     * @param view View
     */
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnOne){
            Intent it = new Intent(ViewPagerActivity.this, ViewPagerOneActivity.class);
            startActivity(it);
        }
        if(view.getId() == R.id.btnTwo){
            Intent it = new Intent(ViewPagerActivity.this, ViewPagerTwoActivity.class);
            startActivity(it);
        }
        if(view.getId() == R.id.btnThree){
            Intent it = new Intent(ViewPagerActivity.this, ViewPagerThreeActivity.class);
            startActivity(it);
        }
        if(view.getId() == R.id.btnFour){
            Intent it = new Intent(ViewPagerActivity.this, ViewPagerFourActivity.class);
            startActivity(it);
        }
    }
}