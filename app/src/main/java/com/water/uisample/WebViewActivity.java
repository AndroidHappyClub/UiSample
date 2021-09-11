package com.water.uisample;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class WebViewActivity extends AppCompatActivity {
    //定义WebView类型的变量
    private WebView wvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        //获取webview对象，并加载百度首页
        wvMain =(WebView)findViewById(R.id.wvMain);
        StringBuffer htmlBuffer = new StringBuffer();
        htmlBuffer.append("<html>");
          htmlBuffer.append("<body>请点击<a href=\"http://www.baidu.com\">百度</a></body>");
        htmlBuffer.append("</html>");
        /*
        baseUrl：基础目录
        data：被加载的内容
        mimeType ：指定资源的媒体类型
        Encoding ：设置网页的编码格式
        historyUrl ：历史记录字段 */
        wvMain.loadDataWithBaseURL("",htmlBuffer.toString(),
                "text/html","UTF-8","");
    }
}
