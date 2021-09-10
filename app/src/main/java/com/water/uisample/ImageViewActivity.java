package com.water.uisample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ImageViewActivity extends AppCompatActivity implements View.OnClickListener {
    //国旗对应的ImageView
    ImageView ivFlag;
    TextView tvFlag;
    //上一页
    ImageButton imBack;
    //下一页
    ImageButton imForward;
    //国旗数组 中国 德国 英国
    int[] flag = {R.drawable.flag_china, R.drawable.flag_germany, R.drawable.flag_britain};
    String[] flagNames = {"中国","德国","英国"};
    //当前页 默认第一页
    int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        //初始化组件
        ivFlag = (ImageView) findViewById(R.id.ivFlag);
        //国旗名称
        tvFlag = (TextView)findViewById(R.id.tvFlag);
        //上一页、下一页
        imBack = (ImageButton)findViewById(R.id.imBack);
        imForward = (ImageButton)findViewById(R.id.imForward);
        //注册监听器
        imBack.setOnClickListener(this);
        imForward.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imBack:
                if(currentPage == 0){
                    Toast.makeText(ImageViewActivity.this,
                            "第一页，前面没有了", Toast.LENGTH_SHORT).show();
                    return;
                }
                //上翻一页
                currentPage-- ;
                //设置国旗图片
                ivFlag.setImageResource(flag[currentPage]);
                //设置国旗名字
                tvFlag.setText(flagNames[currentPage]);
                break;
            case R.id.imForward:
                if(currentPage == (flag.length-1)){
                    Toast.makeText(ImageViewActivity.this,
                            "最后一页，后面没有了", Toast.LENGTH_SHORT).show();
                    return;
                }
                //下翻一页
                currentPage++;
                //设置国旗图片
                ivFlag.setImageResource(flag[currentPage]);
                //设置国旗名字
                tvFlag.setText(flagNames[currentPage]);
                break;
            default:
                break;
        }
    }
}
