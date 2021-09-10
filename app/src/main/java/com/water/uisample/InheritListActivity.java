package com.water.uisample;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class InheritListActivity extends ListActivity {
    //数据源列表
    private String[] _ls = { "姓名：张三", "性别：男", "年龄：25", "居住地：青岛",
            "邮箱：itshixun@gmail.com" };
    private ListView _lv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        _lv =getListView();
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, _ls));
        _lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(InheritListActivity.this,
                        "您选择了" + _ls[position], Toast.LENGTH_LONG).show();
            }
        });
        //设置ListView作为显示
        super.onCreate(savedInstanceState);
    }
}
