package com.water.uisample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewActivity extends AppCompatActivity {
    //数据源列表
    private String[] _ls = { "姓名：张三", "性别：男", "年龄：25", "居住地：青岛",
            "邮箱：itshixun@gmail.com" };
    private ListView _lvMain = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置Activity的布局
        setContentView(R.layout.activity_list_view);
        //获取id为listview的ListView组件
        _lvMain = findViewById(R.id.lvMain);
        _lvMain.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, _ls));
        _lvMain.setOnItemClickListener((parent, view, position, id) -> Toast.makeText(ListViewActivity.this,
                "您选择了" + _ls[position], Toast.LENGTH_SHORT).show());
    }
}
