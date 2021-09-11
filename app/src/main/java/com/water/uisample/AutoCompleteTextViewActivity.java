package com.water.uisample;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class AutoCompleteTextViewActivity extends AppCompatActivity {

    private static final String[] data = new String[]{
            "小猪猪", "小狗狗", "小鸡鸡", "小猫猫", "小咪咪"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.water.uisample.databinding.ActivityAutoCompleteTextViewBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_auto_complete_text_view);

        Context _context = AutoCompleteTextViewActivity.this;

        ArrayAdapter<String> adapter = new ArrayAdapter<>(_context,
                android.R.layout.simple_dropdown_item_1line, data);
        binding.ACTVContent.setAdapter(adapter);

        binding.MACTVContent.setAdapter(adapter);
        // 设置分隔符
        binding.MACTVContent.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }
}
