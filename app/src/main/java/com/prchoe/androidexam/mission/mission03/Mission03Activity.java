package com.prchoe.androidexam.mission.mission03;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.prchoe.androidexam.R;

public class Mission03Activity extends AppCompatActivity implements View.OnClickListener{

    private Button mBtnLogin;
    private final int FLAG_1 = 1;
    private final int FLAG_2 = 2;
    private final int FLAG_3 = 3;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission03);

        initView();
        initListener();
        initIntent();
    }

    private void initView() {
        mBtnLogin = (Button)findViewById(R.id.btn_login);
    }

    private void initListener() {
        mBtnLogin.setOnClickListener(this);
    }

    private void initIntent() {
        intent = new Intent(getApplicationContext(), Mission03_menuActivity.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                startActivityForResult(intent, 0);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case FLAG_1:
                Toast.makeText(getApplicationContext(), data.getStringExtra("message"), Toast.LENGTH_SHORT).show();
                break;
            case FLAG_2:
                Toast.makeText(getApplicationContext(), data.getStringExtra("message"), Toast.LENGTH_SHORT).show();
                break;
            case FLAG_3:
                Toast.makeText(getApplicationContext(), data.getStringExtra("message"), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}


