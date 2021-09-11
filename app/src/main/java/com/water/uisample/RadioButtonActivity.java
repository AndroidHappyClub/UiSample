package com.water.uisample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RadioButtonActivity extends AppCompatActivity implements
        CompoundButton.OnCheckedChangeListener {
    private RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_button);
        rg = findViewById(R.id.radioGroup);

        /* 方法1：获得单选按钮值的方法（切记，要为每个RadioButton添加一个id，不然单选功能会生效）*/
        rg.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radbtn = findViewById(checkedId);
            Toast.makeText(getApplicationContext(), "按钮组值发生改变,你选了" +
                    radbtn.getText(), Toast.LENGTH_LONG).show();
        });

        /* 方法2：通过单击其他按钮获取选中单选按钮的值 */
        Button btnPost = findViewById(R.id.btnPost);
        //为radioGroup设置一个监听器:setOnCheckedChanged()
        btnPost.setOnClickListener(v -> {
            for (int i = 0; i < rg.getChildCount(); i++) {
                RadioButton rd = (RadioButton) rg.getChildAt(i);
                if (rd.isChecked()) {
                    Toast.makeText(getApplicationContext(), "点击提交按钮,获取你选择的是:" +
                            rd.getText(), Toast.LENGTH_LONG).show();
                    break;
                }
            }
        });

        ((CheckBox)findViewById(R.id.cbOne)).setOnCheckedChangeListener(this);
        ((CheckBox)findViewById(R.id.cbTwo)).setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(compoundButton.isChecked()) Toast.makeText(this,compoundButton.getText().toString(),
                Toast.LENGTH_SHORT).show();
    }
}