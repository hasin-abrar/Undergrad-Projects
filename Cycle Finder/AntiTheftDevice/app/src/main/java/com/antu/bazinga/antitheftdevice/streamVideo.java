package com.antu.bazinga.antitheftdevice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;

public class streamVideo extends AppCompatActivity {
    private EditText ipEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream_video);
        ipEditText = (EditText) findViewById(R.id.et_ip);
    }

    public void playVideo(View v)
    {
        String ipaddress = String.valueOf(ipEditText.getText());
        if(ipaddress.equals("")){
            ipaddress = "172.20.24.149";
        }

        String frameVideo = "<html><body><br> <iframe width=\"320\" height=\"315\" src=\"http://" + ipaddress + ":8080/browserfs.html\" frameborder=\"0\" allowfullscreen></iframe></body></html>";

        WebView displayVideo = (WebView) findViewById(R.id.webView);
        displayVideo.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        WebSettings webSettings = displayVideo.getSettings();
        webSettings.setJavaScriptEnabled(true);
        displayVideo.loadData(frameVideo, "text/html", "utf-8");



        //this.playvideo("http://www.youtube.com/watch?v=Hxy8BZGQ5Jo");

    }


}

