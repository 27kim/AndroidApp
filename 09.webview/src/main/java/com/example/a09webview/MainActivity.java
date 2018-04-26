package com.example.a09webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button lineBtn;
    Button barBtn;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webview);
        lineBtn = findViewById(R.id.btn_chart_line);
        barBtn = findViewById(R.id.btn_chart_bar);

        lineBtn.setOnClickListener(this);
        barBtn.setOnClickListener(this);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        webView.loadUrl("file:///android_asset/test.html");
        webView.addJavascriptInterface(new JavascriptTest(), "android");
        webView.setWebViewClient(new MyWebClient());
        webView.setWebChromeClient(new MyWebChrome());
    }

    @Override
    public void onClick(View v) {
        if (v == lineBtn) {
            webView.loadUrl("javascript:lineChart()");
        } else {
            webView.loadUrl("javascript:barChart()");
        }
    }

    class JavascriptTest {
        @JavascriptInterface
        public String getChartData() {
            StringBuffer buffer = new StringBuffer();
            buffer.append("[");
            for (int i = 0; i < 14; i++) {
                buffer.append("[" + i +","+Math.sin(i) + "]");
                if(i<13) buffer.append(",");
            }
            buffer.append("]");
            return  buffer.toString();
        }
    }

    class MyWebClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Toast.makeText(MainActivity.this, url, Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    class MyWebChrome extends WebChromeClient{
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            //아래 주석 처리 되어 있으면 shouldOverrideUrlLoading 동작 안함
            result.confirm();
            return true;
        }
    }
}

