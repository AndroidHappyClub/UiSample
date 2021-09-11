package com.water.uisample;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

public class ToastActivity extends AppCompatActivity {

    private Context _context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.water.uisample.databinding.ActivityToastBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_toast);
        _context = ToastActivity.this;

        binding.btnCustom.setOnClickListener(v -> CustomToast());

        binding.btnNormal.setOnClickListener(v -> Toast.makeText(_context,"常规吐司！", Toast.LENGTH_SHORT).show());
    }

    private void CustomToast(){
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_toast_view,
                findViewById(R.id.llContainer));
        ImageView ivLogo = view.findViewById(R.id.ivLogo);
        ivLogo.setImageDrawable(ContextCompat.getDrawable(_context,R.drawable.above));
        TextView tvMsg = view.findViewById(R.id.tvMsg);
        tvMsg.setText("我的自定义吐司！");
        Toast toast = new Toast(_context);
        // toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }
}