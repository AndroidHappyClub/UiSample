package com.water.uisample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.water.uisample.adapter.DataAdapter;
import com.water.uisample.model.Fruit;

import java.util.ArrayList;

public class SpinnerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner cbOne;
    private Spinner cbTwo;
    private Context _context;

    private ArrayList<Fruit> _data = null;
    private BaseAdapter _adadpter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        _context = SpinnerActivity.this;
        _data = new ArrayList<Fruit>();

        bindViews();
    }


    private void bindViews() {
        cbOne = (Spinner) findViewById(R.id.cbOne);
        cbTwo = (Spinner) findViewById(R.id.cbTwo);

        _data.add(new Fruit(R.mipmap.ic_fruit_huangtao, "黄桃"));
        _data.add(new Fruit(R.mipmap.ic_fruit_pingguo, "苹果"));
        _data.add(new Fruit(R.mipmap.ic_fruit_qingningmeng, "青柠檬"));
        _data.add(new Fruit(R.mipmap.ic_fruit_shiliu, "石榴"));
        _data.add(new Fruit(R.mipmap.ic_fruit_sangshen, "桑葚"));
        _data.add(new Fruit(R.mipmap.ic_fruit_hamigua, "哈密瓜"));
        _data.add(new Fruit(R.mipmap.ic_fruit_yingtao, "樱桃"));

        _adadpter = new DataAdapter<Fruit>(_data, R.layout.spinner_item_fruit) {
            @Override
            public void bindViews(ViewHolder holder, Fruit obj) {
                holder.setImageResource(R.id.ivThumb, obj.getThumb());
                holder.setText(R.id.tvName, obj.getName());
            }
        };

        cbOne.setAdapter(_adadpter);
        cbTwo.setAdapter(_adadpter);
        cbOne.setOnItemSelectedListener(this);
        cbTwo.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.cbOne:
                Toast.makeText(_context, "您选择的水果是：" +
                                parent.getItemAtPosition(position).toString(),
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.cbTwo:
                TextView tv = (TextView) view.findViewById(R.id.tvName);
                Toast.makeText(_context, "您选择的水果是：" +
                                tv.getText().toString(),
                        Toast.LENGTH_SHORT).show();

                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
