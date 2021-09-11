package com.water.uisample;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.water.uisample.databinding.ActivityTextViewBinding;

import java.lang.reflect.Field;

public class TextViewActivity extends AppCompatActivity {

    private ActivityTextViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_text_view);

        InsertImage();
    }

    // 在Java中修改图片大小与位置
    private void ResizeTextViewImage()
    {
        Drawable[] ds = binding.tvShowImage.getCompoundDrawables();
        ds[1].setBounds(0, 0, 50, 50);
        binding.tvShowImage.setCompoundDrawables(ds[0], ds[1],
                ds[2], ds[3]);
    }

    // 添加HTML文本
    private void SetHtmlText()
    {
        String sina = "<a href = 'http://sina.cn'>新浪</a>";
        binding.tvSina.setText(Html.fromHtml(sina));
        binding.tvSina.setMovementMethod(LinkMovementMethod.getInstance());
    }

    // 插入图片
    private void InsertImage()
    {
        String img = "<img src = 'emo'/><br>";
        binding.tvSina.setText(Html.fromHtml(img, source -> {
            Drawable draw = null;
            try {
                Field field = R.drawable.class.getField(source);
                int resourceId = Integer.parseInt(field.get(null).toString());
                draw = ContextCompat.getDrawable(this,resourceId);
                assert draw != null;
                draw.setBounds(0, 0,
                        draw.getIntrinsicWidth(), draw.getIntrinsicHeight());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return draw;
        }, null));
    }

    // 定制文本
    private void SetSpannableString()
    {
        int st = Spanned.SPAN_EXCLUSIVE_EXCLUSIVE ;
        String str = "背景超链接样式删除线下划线颜色图片";
        SpannableString span = new SpannableString(str);
//1.设置背景色
        span.setSpan(new ForegroundColorSpan(Color.RED), 0, 1, st);
//2.用超链接标记文本
        span.setSpan(new URLSpan("tel:88888888"), 2, 4, st);
//3.用样式标记文本（斜体）
        span.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 5, 6,st);
//4.用删除线标记文本
        span.setSpan(new StrikethroughSpan(), 7, 9, st);
//5.用下划线标记文本
        span.setSpan(new UnderlineSpan(), 10, 12, st);
//6.用颜色标记
        span.setSpan(new ForegroundColorSpan(Color.GREEN), 13, 14, st);
//7.获取Drawable资源
        Drawable d = ContextCompat.getDrawable(this,R.drawable.left);
        assert d != null;
        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
//8.创建ImageSpan,然后用ImageSpan来替换文本
        ImageSpan imageSpan = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
        span.setSpan(imageSpan, 15, 16, st);
        binding.tvSina.setText(span);
    }

    // 部分可点击
    private void PartClick(){
        binding.tvSina.setHighlightColor(getResources().getColor(android.R.color.transparent));
        SpannableString spannableInfo = new SpannableString("测试"+"："+"点击我！");
        spannableInfo.setSpan(new MyClickableSpan(clickListener),3,7,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.tvSina.setText(spannableInfo);
        binding.tvSina.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private final View.OnClickListener clickListener= v -> Toast.makeText(TextViewActivity.this,
            "点击成功....", Toast.LENGTH_SHORT).show();

    class MyClickableSpan extends ClickableSpan {
        private final View.OnClickListener mListener;
        public MyClickableSpan(View.OnClickListener l) {
            mListener = l;
        }
        @Override
        public void onClick(@NonNull View v) {
            mListener.onClick(v);
        }
        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(getResources().
                    getColor(R.color.colorAccent));
        }
    }
}