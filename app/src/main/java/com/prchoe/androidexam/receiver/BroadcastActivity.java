package com.prchoe.androidexam.receiver;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by massivCode on 2015-09-16.
 */
public class BroadcastActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button button = new Button(this);
        button.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        button.setText("브로드 캐스트 발송");
        button.setOnClickListener(this);

        LinearLayout linearLayout = new LinearLayout(this);
        ViewGroup.LayoutParams params =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(params);
        linearLayout.addView(button);

        setContentView(linearLayout);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent("android.intent.action.MY_BROADCAST");
        // 일반적으론 이게 쓰임
        // 브로드캐스트는 비동기적으로 쏘아지기 때문에,
        // 1번부터 10번까지 send해도
        // 1번부터 10번 순으로 리시브 받지 못할 수도 있음.
        sendBroadcast(intent);

        // 순서대로 Broadcast 쏘기
//        sendOrderedBroadcast();
    }


    MyReceiver mReceiver = new MyReceiver();

    @Override
    // 화면이 보일 때
    protected void onResume() {
        super.onResume();

        // MyReceiver가 받을 Intent 정의
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.MY_BROADCAST");
        filter.addAction(Intent.ACTION_BATTERY_LOW);
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
//        우선순위 설정
//        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);

        // local 브로드캐스트 리시버 등록
        registerReceiver(mReceiver, filter);
    }

    @Override
    // 화면이 안보일 때
    protected void onPause() {
        super.onPause();

        unregisterReceiver(mReceiver);
    }
}
