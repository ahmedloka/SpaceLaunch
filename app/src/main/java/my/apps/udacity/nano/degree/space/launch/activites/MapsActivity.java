package my.apps.udacity.nano.degree.space.launch.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.NavUtils;
import my.apps.udacity.nano.degree.space.launch.R;
import my.apps.udacity.nano.degree.space.launch.utilites.Constant;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.HashMap;

public class MapsActivity extends AppCompatActivity {

    private WebView mWebView;
    private ProgressBar mProgressBar;
    private AppCompatImageView mBack;


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        NavUtils.navigateUpFromSameTask(this);
        Animatoo.animateSwipeRight(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        initViews();


    }


    private void initViews() {
        mProgressBar = findViewById(R.id.progressBar);
        mBack = findViewById(R.id.imageNavigationIcon);
        mBack.setOnClickListener(v -> {
            onBackPressed();
        });

        mWebView = findViewById(R.id.webview);

        Intent intent = getIntent();
        if (getIntent().hasExtra(Constant.MAP_URL)) {
            String url = intent.getExtras().get(Constant.MAP_URL).toString();
            openWebSite(url);
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void openWebSite(String url) {
        mWebView.getSettings().setJavaScriptEnabled(true); // enable javascript
        final Activity activity = this;

        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }

            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }
        });

        mWebView.loadUrl(url);
    }

}
