package com.prakruthi.ui.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.prakruthi.R;

public class WebViewInvoice extends AppCompatActivity {

    String invoiceDownload;

    WebView invoiceDownload1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_invoice);
        invoiceDownload=getIntent().getStringExtra("invoiceDownload");
        invoiceDownload1=findViewById(R.id.invoiceDownload11);



        WebSettings webSettings = invoiceDownload1.getSettings();
        webSettings.setJavaScriptEnabled(true);

        invoiceDownload1.setWebViewClient(new WebViewClient() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(
                    WebView view, WebResourceRequest request) {

                return true;
            }

            @Override
            public void onPageStarted(
                    WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                //SHOW LOADING IF IT ISNT ALREADY VISIBLE
            }

            @Override
            public void onPageFinished(WebView view, String url) {

            }
        });
        invoiceDownload1.loadUrl(invoiceDownload);
    }
}