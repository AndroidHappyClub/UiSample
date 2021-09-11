package com.water.uisample;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.water.uisample.databinding.ActivityRadioButtonBinding;

public class RadioButtonActivity extends AppCompatActivity implements
        CompoundButton.OnCheckedChangeListener {

    private ActivityRadioButtonBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_radio_button);

        /* 方法1：获得单选按钮值的方法（切记，要为每个RadioButton添加一个id，不然单选功能会生效）*/
        binding.radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioGroupBtn = findViewById(checkedId);
            Toast.makeText(getApplicationContext(), "按钮组值发生改变,你选了" +
                    radioGroupBtn.getText(), Toast.LENGTH_LONG).show();
        });

        /* 方法2：通过单击其他按钮获取选中单选按钮的值 */
        //为radioGroup设置一个监听器:setOnCheckedChanged()
        binding.btnPost.setOnClickListener(v -> {
            for (int i = 0; i < binding.radioGroup.getChildCount(); i++) {
                RadioButton rd = (RadioButton) binding.radioGroup.getChildAt(i);
                if (rd.isChecked()) {
                    Toast.makeText(getApplicationContext(), "点击提交按钮,获取你选择的是:" +
                            rd.getText(), Toast.LENGTH_LONG).show();
                    break;
                }
            }
        });

        binding.cbOne.setOnCheckedChangeListener(this);
        binding.cbTwo.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(compoundButton.isChecked()) Toast.makeText(this,compoundButton.getText().toString(),
                Toast.LENGTH_SHORT).show();
    }
}