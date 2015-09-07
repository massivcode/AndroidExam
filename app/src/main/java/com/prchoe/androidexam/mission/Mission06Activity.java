package com.prchoe.androidexam.mission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.prchoe.androidexam.R;
import com.prchoe.androidexam.interfaces.Layout;

public class Mission06Activity extends AppCompatActivity implements Layout {

    private Button mToggleButton;
    private Button mSendButton;
    private LinearLayout mSearchBar;
    private EditText mHttpET;
    private WebView mWebView;
    private Animation flowAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission06);

        initView();
        initListener();
        flowAnim = AnimationUtils.loadAnimation(this, R.anim.flow);
    }


    @Override
    public void initView() {
        mToggleButton = (Button)findViewById(R.id.button_toggle);
        mSendButton = (Button)findViewById(R.id.button_send);
        mSearchBar = (LinearLayout)findViewById(R.id.layout);
        mHttpET = (EditText)findViewById(R.id.http_edit_text);
        mWebView = (WebView)findViewById(R.id.webView);
    }

    @Override
    public void initListener() {
        mToggleButton.setOnClickListener(this);
        mSendButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_send:

                String url =  "http://"+ mHttpET.getText().toString();
                Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();
                // 웹뷰에서 자바스크립트실행가능
                mWebView.getSettings().setJavaScriptEnabled(true);
                // 구글홈페이지 지정
                mWebView.loadUrl(url);
                // WebViewClient 지정
                mWebView.setWebViewClient(new WebViewClient());


                break;
            case R.id.button_toggle:
                flowAnim.setAnimationListener(new FlowAnimationListener());
                mToggleButton.startAnimation(flowAnim);
                mSearchBar.startAnimation(flowAnim);
                if(mSearchBar.getVisibility() == View.GONE) {
                    mToggleButton.setText("△");

                    mSearchBar.setVisibility(View.VISIBLE);
                } else {
                    mToggleButton.setText("▽");
                    mSearchBar.setVisibility(View.GONE);
                }
                break;
        }

    }

    private final class FlowAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
