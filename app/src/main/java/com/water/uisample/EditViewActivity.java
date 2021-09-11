package com.water.uisample;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.water.uisample.databinding.ActivityEditViewBinding;

public class EditViewActivity extends AppCompatActivity {

    private ActivityEditViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_edit_view);

        PopKeyBoard();
        addExpression();
    }

    // 设置EditText获得焦点，同时弹出小键盘
    private void PopKeyBoard()
    {
        binding.etHint.requestFocus();
    }

    // 带表情的EditText
    private  void addExpression()
    {
        binding.btnAddExpression.setOnClickListener(v -> {
            SpannableString spanStr = new SpannableString("!");
            Drawable drawable = ContextCompat.getDrawable(this,R.drawable.cancel);
            assert drawable != null;
            drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
            ImageSpan span = new ImageSpan(drawable,ImageSpan.ALIGN_BASELINE);
            spanStr.setSpan(span,0,1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            int cursor = binding.etExpression.getSelectionStart();
            binding.etExpression.getText().insert(cursor, spanStr);
            Toast.makeText(EditViewActivity.this, binding.etExpression.getText(), Toast.LENGTH_SHORT).show();
        });
    }
}