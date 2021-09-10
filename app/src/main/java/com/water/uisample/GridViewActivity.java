package com.water.uisample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.water.uisample.adapter.DataAdapter;
import com.water.uisample.model.ShareData;

import java.util.ArrayList;

public class GridViewActivity extends AppCompatActivity {
    private Context _context;
    private GridView _gvMain;
    private BaseAdapter _adapter = null;
    private ArrayList<ShareData> _data = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        _context = GridViewActivity.this;
        _gvMain = (GridView) findViewById(R.id.gvShare);

        _data = new ArrayList<ShareData>();
        _data.add(new ShareData(R.mipmap.ic_share_facebook_normal, "facebook"));
        _data.add(new ShareData(R.mipmap.ic_share_kakao_normal, "kakao"));
        _data.add(new ShareData(R.mipmap.ic_share_kakaostory_normal, "kakaostory"));
        _data.add(new ShareData(R.mipmap.ic_share_qq_normal, "qq"));
        _data.add(new ShareData(R.mipmap.ic_share_qqzone_normal, "qqzone"));
        _data.add(new ShareData(R.mipmap.ic_share_renren_normal, "renren"));
        _data.add(new ShareData(R.mipmap.ic_share_sinaweibo_normal, "sinaweibo"));
        _data.add(new ShareData(R.mipmap.ic_share_timeline_normal, "timeline"));
        _data.add(new ShareData(R.mipmap.ic_share_twitter_normal, "twitter"));
        _data.add(new ShareData(R.mipmap.ic_share_wechat_normal, "wechat"));
        _data.add(new ShareData(R.mipmap.ic_share_more_normal, "more"));

        _adapter = new DataAdapter<ShareData>(_data, R.layout.grid_view_item) {
            @Override
            public void bindViews(ViewHolder holder, ShareData obj) {
                holder.setImageResource(R.id.ivThumb, obj.getThumb());
                holder.setText(R.id.tvTitle, obj.getName());
            }
        };

        _gvMain.setAdapter(_adapter);

        _gvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(_context, "你点击了第 " + position + " 项",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
