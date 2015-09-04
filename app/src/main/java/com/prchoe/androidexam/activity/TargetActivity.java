package com.prchoe.androidexam.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.prchoe.androidexam.R;

public class TargetActivity extends AppCompatActivity implements View.OnClickListener {

    private String mName;
    private String mPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        mName = getIntent().getStringExtra("name");
        mPhone = getIntent().getStringExtra("phone");

        Toast.makeText(this, "name : " + mName + " phone : " + mPhone, Toast.LENGTH_SHORT).show();

        findViewById(R.id.finish_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        // 돌려 줄 데이타를 Intent에 설정
        Intent intent = new Intent();
        intent.putExtra("result", mName + ", " + mPhone);

        // 결과와 데이터를 함께 돌려준다
        setResult(RESULT_OK, intent);

        // Activity 종료
        finish();
    }
}
