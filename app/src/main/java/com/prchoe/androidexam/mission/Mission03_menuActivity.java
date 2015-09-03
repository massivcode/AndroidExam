package com.prchoe.androidexam.mission;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.prchoe.androidexam.R;

/**
 * Created by massivCode on 2015-09-03.
 */
public class Mission03_menuActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mBtn1, mBtn2, mBtn3;
    private Button mDialogClose;
    private Intent intent;
    private AlertDialog.Builder mBuilder;
    private String message;
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

    private void setMDialog() {
        mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle(message);
        mBuilder.setPositiveButton("닫기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Mission03_menuActivity.this.finish();
            }
        });

        AlertDialog dialog = mBuilder.create();
        dialog.show();
    }



    private void setMessage(Button button) {
        message = button.getText().toString();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_1:
                setMessage(mBtn1);
                intent.putExtra("message", message);
                setResult(1, intent);
                setMDialog();
                break;
            case R.id.btn_2:
                setMessage(mBtn2);
                intent.putExtra("message", message);
                setResult(2, intent);
                setMDialog();
                break;
            case R.id.btn_3:
                setMessage(mBtn3);
                intent.putExtra("message", message);
                setResult(3, intent);
                setMDialog();
                break;
        }
    }
}