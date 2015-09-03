package com.prchoe.androidexam.mission;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.prchoe.androidexam.R;

/**
 * Created by massivCode on 2015-09-03.
 */
public class Mission03_menuActivity extends Activity implements View.OnClickListener{

    private Button mBtn1, mBtn2, mBtn3;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission03_1);

        initView();
        initListener();

        intent = new Intent();
    }

    private void initView() {
        mBtn1 = (Button)findViewById(R.id.btn_1);
        mBtn2 = (Button)findViewById(R.id.btn_2);
        mBtn3 = (Button)findViewById(R.id.btn_3);
    }

    private void initListener() {
        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);
        mBtn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_1:
                intent.putExtra("message", mBtn1.getText().toString());
                setResult(1, intent);
                finish();
                break;
            case R.id.btn_2:
                intent.putExtra("message", mBtn2.getText().toString());
                setResult(2, intent);
                finish();
                break;
            case R.id.btn_3:
                intent.putExtra("message", mBtn3.getText().toString());
                setResult(3, intent);
                finish();
                break;
        }
    }
}