package com.water.uisample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
        btnOne = (Button) findViewById(R.id.btnOne);
        btnTwo = (Button) findViewById(R.id.btnTwo);
        btnThree = (Button) findViewById(R.id.btnThree);
        btnFour = (Button) findViewById(R.id.btnFour);
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