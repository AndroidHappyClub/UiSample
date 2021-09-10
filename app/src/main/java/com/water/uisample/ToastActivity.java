package com.water.uisample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ToastActivity extends AppCompatActivity {
    private Button btnNormal;
    private Button btnCustom;
    private Context _context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        _context = ToastActivity.this;
        btnCustom = (Button) findViewById(R.id.btnCustom);
        btnNormal = (Button) findViewById(R.id.btnNormal);

        btnCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomToast("我的自定义吐司！", Toast.LENGTH_SHORT);
            }
        });

        btnNormal = findViewById(R.id.btnNormal);
        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(_context,"常规吐司！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void CustomToast(String str, int showTime){
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_toast_view,
                (ViewGroup) findViewById(R.id.llContainer));
        ImageView ivLogo = (ImageView) view.findViewById(R.id.ivLogo);
        ivLogo.setImageDrawable(getDrawable(R.drawable.above));
        TextView tvMsg = (TextView) view.findViewById(R.id.tvMsg);
        tvMsg.setText(str);
        Toast toast = new Toast(_context);
        // toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }
}