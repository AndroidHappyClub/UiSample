package com.water.uisample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.water.uisample.adapter.DataAdapter;
import com.water.uisample.model.Book;

import java.util.ArrayList;
import java.util.List;

public class ImageListViewActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener{

    private Context _context;
    private ListView lvBook;
    private DataAdapter<Book> _adapter = null;
    private List<Book> _books = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list_view);
        _context = ImageListViewActivity.this;

        bindViews();

        _books.add(new Book(R.drawable.lu_bin_xun_piao_liu_ji,
                "鲁滨逊漂流记","（英）丹尼尔·笛福"));
        _books.add(new Book(R.drawable.zhong_guo_xiao_shuo_shi_lue,
                "中国小说史略","（中）鲁迅"));
        _books.add(new Book(R.drawable.ku_xiao_niao,
                "虎啸鸟","（德）乌韦·蒂姆"));
    }

    private void bindViews() {

        lvBook = (ListView) findViewById(R.id.lvBook);

        //数据初始化
        _books = new ArrayList<Book>();
        _books.add(new Book(R.drawable.lu_bin_xun_piao_liu_ji,
                "鲁滨逊漂流记","（英）丹尼尔·笛福"));
        _books.add(new Book(R.drawable.zhong_guo_xiao_shuo_shi_lue,
                "中国小说史略","（中）鲁迅"));
        _books.add(new Book(R.drawable.ku_xiao_niao,
                "虎啸鸟","（德）乌韦·蒂姆"));

        // Adapter初始化
        _adapter = new DataAdapter<Book>((ArrayList)_books,
                R.layout.list_view_item) {
            @Override
            public void bindViews(ViewHolder holder, Book obj) {
                holder.setImageResource(R.id.ivThumb,obj.getThumb());
                holder.setText(R.id.tvName,obj.getName());
                holder.setText(R.id.tvAuthor,obj.getAuthor());
            }
        };

        //ListView设置下Adapter：
        lvBook.setAdapter(_adapter);
        lvBook.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView,
                            View view, int position, long l) {
        Toast.makeText(_context,"你点击了第" + position + "项",Toast.LENGTH_SHORT).show();
    }
}
