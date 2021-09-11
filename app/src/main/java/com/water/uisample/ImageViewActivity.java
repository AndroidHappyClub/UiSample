package com.water.uisample;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.water.uisample.databinding.ActivityImageViewBinding;

public class ImageViewActivity extends AppCompatActivity {

    private ActivityImageViewBinding binding;
    //国旗数组 中国 德国 英国
    int[] flag = {R.drawable.flag_china, R.drawable.flag_germany, R.drawable.flag_britain};
    String[] flagNames = {"中国","德国","英国"};
    //当前页 默认第一页
    int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_image_view);

        //注册监听器
        binding.imBack.setOnClickListener(v -> {
            if(currentPage == 0){
                Toast.makeText(ImageViewActivity.this,
                        "第一页，前面没有了", Toast.LENGTH_SHORT).show();
                return;
            }
            //上翻一页
            currentPage-- ;
            //设置国旗图片
            binding.ivFlag.setImageResource(flag[currentPage]);
            //设置国旗名字
            binding.tvFlag.setText(flagNames[currentPage]);
        });
        binding.imForward.setOnClickListener(v -> {
            if(currentPage == (flag.length-1)){
                Toast.makeText(ImageViewActivity.this,
                        "最后一页，后面没有了", Toast.LENGTH_SHORT).show();
                return;
            }
            //下翻一页
            currentPage++;
            //设置国旗图片
            binding.ivFlag.setImageResource(flag[currentPage]);
            //设置国旗名字
            binding.tvFlag.setText(flagNames[currentPage]);
        });
    }
}
