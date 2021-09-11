package com.water.uisample;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.water.uisample.databinding.ActivityListViewBinding;

public class ListViewActivity extends AppCompatActivity {
    //数据源列表
    private final String[] _ls = { "姓名：张三", "性别：男", "年龄：25", "居住地：青岛",
            "邮箱：itshixun@gmail.com" };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置Activity的布局
        com.water.uisample.databinding.ActivityListViewBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_list_view);
        //获取id为listview的ListView组件

        binding.lvMain.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, _ls));
        binding.lvMain.setOnItemClickListener((parent, view, position, id) -> Toast.makeText(ListViewActivity.this,
                "您选择了" + _ls[position], Toast.LENGTH_SHORT).show());
    }
}
