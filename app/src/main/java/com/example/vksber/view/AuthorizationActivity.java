package com.example.vksber.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.vksber.R;

public class AuthorizationActivity extends AppCompatActivity {

    private String authorizationURL = "http://oauth.vk.com/authorize?client_id=7193448&scope=photos,friends&redirect_uri=oauth.vk.com/blank.html&display=touch&response_type=token&state=123456";

    private WebView webView;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authorazation_layout);

        preferences = this.getSharedPreferences("myPrefs", MODE_PRIVATE);

        if (preferences.getString("token", "") == null) {
            webView = findViewById(R.id.webView);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new MyWebViewClient());
            webView.loadUrl(authorizationURL);
        }
            else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    @SuppressLint("ApplySharedPref")
    @Override
    protected void onStop() {
        super.onStop();
        String apiToken = webView.getUrl();
        preferences.edit().putString("token", apiToken).commit();
    }

    private class MyWebViewClient extends WebViewClient {
        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}


