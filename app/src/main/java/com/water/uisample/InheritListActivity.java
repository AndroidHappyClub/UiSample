package com.water.uisample;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class InheritListActivity extends ListActivity {
    //数据源列表
    private final String[] _ls = { "姓名：张三", "性别：男", "年龄：25", "居住地：青岛",
            "邮箱：itshixun@gmail.com" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ListView _lv = getListView();
        setListAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, _ls));
        _lv.setOnItemClickListener((parent, view, position, id) -> Toast.makeText(InheritListActivity.this,
                "您选择了" + _ls[position], Toast.LENGTH_LONG).show());
        //设置ListView作为显示
        super.onCreate(savedInstanceState);
    }
}
