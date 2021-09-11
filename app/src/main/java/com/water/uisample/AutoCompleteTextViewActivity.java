package com.water.uisample;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

public class AutoCompleteTextViewActivity extends AppCompatActivity {

    private AutoCompleteTextView _actvContent;
    private MultiAutoCompleteTextView _mactvContent;
    private Context _context;

    private static final String[] data = new String[]{
            "小猪猪", "小狗狗", "小鸡鸡", "小猫猫", "小咪咪"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete_text_view);
        _actvContent = (AutoCompleteTextView) findViewById(R.id.actvContent);
        _mactvContent = (MultiAutoCompleteTextView) findViewById(R.id.mactvContent);
        _context = AutoCompleteTextViewActivity.this;

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(_context,
                android.R.layout.simple_dropdown_item_1line, data);
        _actvContent.setAdapter(adapter);

        _mactvContent.setAdapter(adapter);
        // 设置分隔符
        _mactvContent.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }
}
