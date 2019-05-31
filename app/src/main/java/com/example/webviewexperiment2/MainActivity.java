package com.example.webviewexperiment2;

import android.content.DialogInterface;
import android.net.http.SslError;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webview = (WebView) findViewById(R.id.webview);

        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient(){

            public boolean shouldOverrideUrlLoading(WebView view, String url){
                System.out.println("MainActivity.shouldOverrideUrlLoading");
                view.loadUrl(url);
//                firstLoad = true;

                return true;

            }

            // when the page is finished loading
            public void onPageFinished(WebView view, String url){
                System.out.println("MainActivity.onPageFinished");
            }

            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                System.out.println("MainActivity.onReceivedSslError");
                System.out.println("view = [" + view + "], handler = [" + handler + "], error = [" + error + "]");
                handler.proceed();
            }


            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl){

                System.out.println("view = " + view);
                System.out.println("MainActivity.onReceivedError");
                System.out.println("view = [" + view + "], errorCode = [" + errorCode + "], description = [" + description + "], failingUrl = [" + failingUrl + "]");

                Toast.makeText(getBaseContext(), "Could Not load. " + description, Toast.LENGTH_SHORT).show();


            }

        });

//        browser.loadUrl("https://www.javatpoint.com/");

    }
}
