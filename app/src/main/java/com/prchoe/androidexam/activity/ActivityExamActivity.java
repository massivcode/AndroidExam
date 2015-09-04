
package com.prchoe.androidexam.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.prchoe.androidexam.R;
import com.prchoe.androidexam.interfaces.Layout;

public class ActivityExamActivity extends AppCompatActivity implements Layout {

    public static final int REQUEST_CODE_STRING = 1000;
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
        mNameET = (EditText) findViewById(R.id.name_edit_text);
        mPhoneET = (EditText) findViewById(R.id.phone_edit_text);
    }

    @Override
    public void initListener() {
        findViewById(R.id.sendBtn).setOnClickListener(this);
        findViewById(R.id.sendBtn2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {



        switch (v.getId()) {
            case R.id.sendBtn:
                btn1Click();
                break;
            case R.id.sendBtn2:
                btn2Click();
                break;
        }



    }

    private void btn1Click() {
        // TargetActivity로 이동
        Intent intent = new Intent(getApplicationContext(), TargetActivity.class);

        // Data 추가
        intent.putExtra("name", mNameET.getText().toString());
        intent.putExtra("phone", mPhoneET.getText().toString());

        startActivity(intent);
    }

    private void btn2Click() {
        // TargetActivity로 이동
        Intent intent = new Intent(getApplicationContext(), TargetActivity.class);

        // Data 추가
        intent.putExtra("name", mNameET.getText().toString());
        intent.putExtra("phone", mPhoneET.getText().toString());

        // ======= 주는 곳 ========
        //
        // 데이터를 돌려받기 위해서는 startActivityForResult로 호출 해야 됨
        // 리퀘스트 코드는 원하는 값을 받기 위한 약속같은 것
        startActivityForResult(intent, REQUEST_CODE_STRING);
    }

    // ========= 받는 곳 ===========
    //
    // startActivityForResult로 호출 후에 데이터를 리턴 받는 곳
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 결과가 OK냐
        if(resultCode == RESULT_OK) {
            // RequestCode에 따라 처리를 나눔
            if(requestCode == REQUEST_CODE_STRING) {
                // data가 왔으면
                if(data != null) {
                    String result = data.getStringExtra("result");
                    Toast.makeText(getApplicationContext(), "result : " + result, Toast.LENGTH_SHORT).show();
                }
            }

        } else {
            Toast.makeText(getApplicationContext(), "에러", Toast.LENGTH_SHORT).show();
        }
    }
}
