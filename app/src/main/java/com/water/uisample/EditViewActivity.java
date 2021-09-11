package com.water.uisample;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditViewActivity extends AppCompatActivity {
    private EditText etExpression;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_view);

        // PopKeyBoard();
        addExpression();
    }

    // 设置EditText获得焦点，同时弹出小键盘
    private void PopKeyBoard()
    {
        EditText etHint = findViewById(R.id.etHint);
        etHint.requestFocus();
    }

    // 带表情的EditText
    private  void addExpression()
    {
        Button btnAddExpression = findViewById(R.id.btnAddExpression);
        etExpression = findViewById(R.id.etExpression);
        btnAddExpression.setOnClickListener(v -> {
            SpannableString spanStr = new SpannableString("!");
            Drawable drawable = EditViewActivity.this.getResources().getDrawable(R.drawable.cancel);
            drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
            ImageSpan span = new ImageSpan(drawable,ImageSpan.ALIGN_BASELINE);
            spanStr.setSpan(span,0,1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            int cursor = etExpression.getSelectionStart();
            etExpression.getText().insert(cursor, spanStr);
            Toast.makeText(EditViewActivity.this, etExpression.getText(), Toast.LENGTH_SHORT).show();
        });
    }
}