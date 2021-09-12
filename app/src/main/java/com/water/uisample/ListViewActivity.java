package com.water.uisample;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {
    //数据源列表
    private final List<String> _ls = new ArrayList<>();

    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置Activity的布局
        com.water.uisample.databinding.ActivityListViewBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_list_view);

        _ls.add("姓名：张三");
        _ls.add("性别：男");
        _ls.add("年龄：25");
        _ls.add("居住地：青岛");
        _ls.add("邮箱：itshixun@gmail.com");

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, _ls);

        binding.lvMain.setAdapter(arrayAdapter);

        binding.lvMain.setOnItemClickListener(
            (parent, view, position, id) ->
            Toast.makeText(
                    ListViewActivity.this,
                    "您选择了" + _ls.get(position),
                    Toast.LENGTH_SHORT
            ).show()
        );

        binding.btnAdd.setOnClickListener(v -> arrayAdapter.add(binding.etAdd.getText().toString()));
    }
}
