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

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Field;

public class TextViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);

        InsertImage();
    }

    // 在Java中修改图片大小与位置
    private void ResizeTextViewImage()
    {
        TextView tv = (TextView) findViewById(
                R.id.tvShowImage);
        Drawable[] ds = tv.getCompoundDrawables();
        ds[1].setBounds(0, 0, 50, 50);
        tv.setCompoundDrawables(ds[0], ds[1],
                ds[2], ds[3]);
    }

    // 添加HTML文本
    private void SetHtmlText()
    {
        TextView tv = (TextView) findViewById(R.id.tvSina);
        String sina = "<a href = 'http://sina.cn'>新浪</a>";
        tv.setText(Html.fromHtml(sina));
        tv.setMovementMethod(LinkMovementMethod.getInstance());
    }

    // 插入图片
    private void InsertImage()
    {
        TextView tv = (TextView) findViewById(R.id.tvSina);
        String img = "<img src = 'emo'/><br>";
        tv.setText(Html.fromHtml(img, new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                Drawable draw = null;
                try {
                    Field field = R.drawable.class.getField(source);
                    int resourceId = Integer.parseInt(field.get(null).toString());
                    draw = getResources().getDrawable(resourceId);
                    draw.setBounds(0, 0,
                            draw.getIntrinsicWidth(), draw.getIntrinsicHeight());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return draw;
            }
        }, null));
    }

    // 定制文本
    private void SetSpannableString()
    {
        TextView tv = (TextView) findViewById(R.id.tvSina);
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
        Drawable d = getResources().getDrawable(R.drawable.left);
        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
//8.创建ImageSpan,然后用ImageSpan来替换文本
        ImageSpan imgspan = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
        span.setSpan(imgspan, 15, 16, st);
        tv.setText(span);
    }

    // 部分可点击
    private void PartClick(){
        TextView tv = (TextView) findViewById(R.id.tvSina);
        tv.setHighlightColor(getResources().getColor(android.R.color.transparent));
        SpannableString spanableInfo = new SpannableString("测试"+"："+"点击我！");
        spanableInfo.setSpan(new MyClickableSpan(clickListener),3,7,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(spanableInfo);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(TextViewActivity.this,
                    "点击成功....", Toast.LENGTH_SHORT).show();
        }
    };

    class MyClickableSpan extends ClickableSpan {
        private final View.OnClickListener mListener;
        public MyClickableSpan(View.OnClickListener l) {
            mListener = l;
        }
        @Override
        public void onClick(View v) {
            mListener.onClick(v);
        }
        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(getResources().
                    getColor(R.color.colorAccent));
        }
    }
}