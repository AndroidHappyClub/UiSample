package com.water.uisample;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class DialogActivity extends AppCompatActivity implements View.OnClickListener {

    private Context _context;
    private boolean[] _selectedItems;

    private AlertDialog _ad = null;

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.water.uisample.databinding.ActivityDialogBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_dialog);
        _context = DialogActivity.this;

        binding.btnNormalDialog.setOnClickListener(this);
        binding.btnListDialog.setOnClickListener(this);
        binding.btnSingleDialog.setOnClickListener(this);
        binding.btnMultipleDialog.setOnClickListener(this);
        binding.btnCustomDialog.setOnClickListener(this);
        binding.btnCircleDialog.setOnClickListener(this);
        binding.btnBarDialog.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        AlertDialog.Builder _builder;

        // 显示普通对话框
        if (v.getId() == R.id.btnNormalDialog) {
            _ad = null;
            _builder = new AlertDialog.Builder(_context);
            _ad = _builder.setIcon(R.mipmap.ic_sina_logo)
                    .setTitle("系统提示：")
                    .setMessage("这是一个最普通的AlertDialog,\n带有三个按钮，分别是取消，中立和确定")
                    .setNegativeButton("取消", (dialog, which) -> Toast.makeText(_context, "你点击了取消按钮！", Toast.LENGTH_SHORT).show())
                    .setPositiveButton("确定", (dialog, which) -> Toast.makeText(_context, "你点击了确定按钮！", Toast.LENGTH_SHORT).show())
                    .setNeutralButton("中立", (dialog, which) -> Toast.makeText(_context, "你点击了中立按钮！", Toast.LENGTH_SHORT).show()).create();             // 创建AlertDialog对象
            _ad.show();
        }

        // 显示普通列表对话框
        if (v.getId() == R.id.btnListDialog) {
            final String[] lesson = new String[]{"语文", "数学", "英语", "化学", "生物", "物理", "体育"};
            _ad = null;
            _builder = new AlertDialog.Builder(_context);
            _ad = _builder.setIcon(R.mipmap.ic_camera_viewer_effect_pressed)
                    .setTitle("选择你喜欢的课程")
                    .setItems(lesson, (dialog, which) -> Toast.makeText(getApplicationContext(), "你选择了" + lesson[which], Toast.LENGTH_SHORT).show()).create();
            _ad.show();
        }

        // 显示单选列表对话框
        if (v.getId() == R.id.btnSingleDialog) {
            final String[] fruits = new String[]{"苹果", "雪梨", "香蕉", "葡萄", "西瓜"};
            _ad = null;
            _builder = new AlertDialog.Builder(_context);
            _ad = _builder.setIcon(R.mipmap.ic_settings_photowonder)
                    .setTitle("选择你喜欢的水果，只能选一个哦！")
                    .setSingleChoiceItems(fruits, 0, (dialog, which) -> Toast.makeText(getApplicationContext(), "你选择了" + fruits[which], Toast.LENGTH_SHORT).show())
                    .setPositiveButton("确定", (dialog, which) -> Toast.makeText(_context, "你点击了确定按钮！", Toast.LENGTH_SHORT).show()).create();
            _ad.show();
        }

        // 显示多选列表对话框
        if (v.getId() == R.id.btnMultipleDialog) {
            final String[] menu = new String[]{"水煮豆腐", "萝卜牛腩", "酱油鸡", "胡椒猪肚鸡"};
            // 定义一个用来记录个列表项状态的boolean数组
            _selectedItems = new boolean[]{false, false, false, false};
            _ad = null;
            _builder = new AlertDialog.Builder(_context);
            _ad = _builder.setIcon(R.mipmap.ic_camera_viewer_seft_pressed)
                    .setMultiChoiceItems(menu, _selectedItems, (dialog, which, isChecked) -> _selectedItems[which] = isChecked)
                    .setPositiveButton("确定", (dialog, which) -> {
                        StringBuilder result = new StringBuilder();
                        for (int i = 0; i < _selectedItems.length; i++) {
                            if (_selectedItems[i])
                                result.append(menu[i]).append(" ");
                        }
                        Toast.makeText(getApplicationContext(), "客官你点了:" + result, Toast.LENGTH_SHORT).show();
                    })
                    .create();
            _ad.show();
        }

        // 显示自定义布局对话框
        if (v.getId() == R.id.btnCustomDialog) {
            // 加载自定义的那个View
            LayoutInflater inflater = DialogActivity.this.getLayoutInflater();
            View view = inflater.inflate(R.layout.dialog_custom_layout,
                    null, false);
            _builder = new AlertDialog.Builder(_context);
            _builder.setView(view);
            _builder.setCancelable(false);
            _ad = _builder.create();

            view.findViewById(R.id.btnCancle).setOnClickListener(v1 -> _ad.dismiss());

            ((TextView) view.findViewById(R.id.tvTitle)).setText("通知");

            ((TextView) view.findViewById(R.id.tvContent)).setText("3月17日，十三届全国人大一次会议在北京人民大会堂举行第五次全体会议。王岐山当选为中华人民共和国副主席。这是王岐山进行宪法宣誓。");

            view.findViewById(R.id.btnBlog).setOnClickListener(v12 -> {
                Toast.makeText(getApplicationContext(), "访问博客",
                        Toast.LENGTH_SHORT).show();
                Uri uri = Uri.parse("http://blog.sina.cn");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                _ad.dismiss();
            });

            view.findViewById(R.id.btnClose).setOnClickListener(v13 -> {
                Toast.makeText(getApplicationContext(), "对话框已关闭！",
                        Toast.LENGTH_SHORT).show();
                _ad.dismiss();
            });

            _ad.show();
        }

        if (v.getId() == R.id.btnCircleDialog) {
            // 滚动等待对话框
            final ProgressDialog cd = new ProgressDialog(
                    _context);
            cd.setIcon(R.mipmap.ic_camera_viewer_effect_pressed);
            cd.setTitle("等待");
            cd.setMessage("正在加载....");
            cd.setCancelable(false);
            cd.show();
            new Thread(() -> {
                try {
                    Thread.sleep(15000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    cd.dismiss();
                }
            }).start();
        }

        if (v.getId() == R.id.btnBarDialog) {
            // 滚动等待对话框
            final ProgressDialog bd = new ProgressDialog(
                    DialogActivity.this);// 得到一个对象
            bd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL); // 设置为矩形进度条
            bd.setTitle("提示");
            bd.setMessage("数据加载中，请稍后...");
            bd.setIcon(R.mipmap.ic_camera_viewer_effect_pressed);
            // 设置进度条是否为不明确
            bd.setIndeterminate(false);
            bd.setCancelable(true);
            // 设置进度条的最大值
            bd.setMax(100);
            // 设置当前默认进度为 0
            bd.setProgress(0);
            // 设置第二条进度值为1000
            bd.setSecondaryProgress(1000);
            // 显示进度条
            bd.show();
            count = 0;

            new Thread() {
                public void run() {
                    while (count <= 100) {
                        bd.setProgress(count++);
                        try {
                            Thread.sleep(100);  //暂停 0.1秒
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    bd.dismiss();
                }
            }.start();
        }
    }
}