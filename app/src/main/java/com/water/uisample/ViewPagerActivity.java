package com.water.uisample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ViewPagerActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnOne;
    private Button btnTwo;
    private Button btnThree;
    private Button btnFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        initViews();
    }

    private void initViews() {
        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
        btnThree = findViewById(R.id.btnThree);
        btnFour = findViewById(R.id.btnFour);
        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnFour.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOne:
                Intent it =new Intent(ViewPagerActivity.this,
                        ViewPagerOneActivity.class);
                startActivity(it);
                break;
            case R.id.btnTwo:
                startActivity(new Intent(this, ViewPagerTwoActivity.class));
                break;
            case R.id.btnThree:
                startActivity(new Intent(this, ViewPagerThreeActivity.class));
                break;
            case R.id.btnFour:
                startActivity(new Intent(this, ViewPagerFourActivity.class));
                break;
        }
    }
}