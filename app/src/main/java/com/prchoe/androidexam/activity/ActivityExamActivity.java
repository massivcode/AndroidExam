package com.prchoe.androidexam.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.prchoe.androidexam.R;
import com.prchoe.androidexam.interfaces.Layout;

public class ActivityExamActivity extends AppCompatActivity implements Layout {

    private EditText mNameET, mPhoneET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_exam);

        initView();
        initListener();


    }

    @Override
    public void initView() {
        mNameET = (EditText)findViewById(R.id.name_edit_text);
        mPhoneET = (EditText)findViewById(R.id.phone_edit_text);
    }

    @Override
    public void initListener() {
        findViewById(R.id.sendBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TargetActivity로 이동
        Intent intent = new Intent(getApplicationContext(), TargetActivity.class);

        // Data 추가
        intent.putExtra("name", mNameET.getText().toString());
        intent.putExtra("phone", mPhoneET.getText().toString());


        startActivity(intent);

    }
}
