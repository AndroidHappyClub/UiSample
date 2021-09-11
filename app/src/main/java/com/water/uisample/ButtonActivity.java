package com.water.uisample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class ButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.water.uisample.databinding.ActivityButtonBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_button);

        binding.btnTwo.setOnClickListener(v -> {
            if(binding.btnTwo.getText().toString().equals("按钮不可用")){
                binding.btnOne.setEnabled(false);
                binding.btnTwo.setText("按钮可用");
            }else{
                binding.btnOne.setEnabled(true);
                binding.btnTwo.setText("按钮不可用");
            }
        });
    }
}
