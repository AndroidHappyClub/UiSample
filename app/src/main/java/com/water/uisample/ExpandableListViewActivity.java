package com.water.uisample;

import android.content.Context;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.water.uisample.adapter.ExpandableListAdapter;
import com.water.uisample.model.BaseItem;

import java.util.ArrayList;

public class ExpandableListViewActivity extends AppCompatActivity {

    private ArrayList<BaseItem> _gData;
    private ArrayList<ArrayList<BaseItem>> _iData = null;
    private Context _ctx;

    public ExpandableListViewActivity() {
        _gData = null;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view);
        bindViews();
    }

    private void bindViews() {
        _ctx = ExpandableListViewActivity.this;
        ExpandableListView evlMain = findViewById(R.id.evlMain);

        //数据准备
        _gData = new ArrayList<>();
        _iData = new ArrayList<>();
        _gData.add(new BaseItem("水果"));
        _gData.add(new BaseItem("分享"));

        ArrayList<BaseItem> _lData = new ArrayList<>();

        // 水果
        _lData.add(new BaseItem(R.mipmap.ic_fruit_caomei,"草莓"));
        _lData.add(new BaseItem(R.mipmap.ic_fruit_chengzi,"橙子"));
        _lData.add(new BaseItem(R.mipmap.ic_fruit_lanmei,"蓝莓"));
        _lData.add(new BaseItem(R.mipmap.ic_fruit_yingtao,"樱桃"));
        _iData.add(_lData);
        // 国旗
        _lData = new ArrayList<>();
        _lData.add(new BaseItem(R.mipmap.ic_share_qq_normal, "QQ"));
        _lData.add(new BaseItem(R.mipmap.ic_share_renren_normal, "人人"));
        _lData.add(new BaseItem(R.mipmap.ic_share_facebook_normal, "脸书"));
        _lData.add(new BaseItem(R.mipmap.ic_share_sinaweibo_normal, "微博"));
        _lData.add(new BaseItem(R.mipmap.ic_share_wechat_normal, "微信"));
        _iData.add(_lData);

        ExpandableListAdapter _adapter = new ExpandableListAdapter(_gData, _iData, _ctx);
        evlMain.setAdapter(_adapter);

        // 列表设置点击事件
        evlMain.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            Toast.makeText(_ctx, "你点击了：" + _iData.get(groupPosition).
                    get(childPosition).getName(), Toast.LENGTH_SHORT).show();
            return true;
        });
    }
}
