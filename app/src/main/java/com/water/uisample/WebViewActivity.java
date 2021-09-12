package com.water.uisample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.water.uisample.databinding.ActivityWebViewBinding;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityWebViewBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_web_view);

        //获取webView对象，并加载百度首页
        String htmlBuffer = "<html>" +
                "<body>请点击<a href=\"http://www.baidu.com\">百度</a></body>" +
                "</html>";
        /*
        baseUrl：基础目录
        data：被加载的内容
        mimeType ：指定资源的媒体类型
        Encoding ：设置网页的编码格式
        historyUrl ：历史记录字段 */
        binding.wvMain.loadDataWithBaseURL("", htmlBuffer,
                "text/html","UTF-8","");
    }
}
