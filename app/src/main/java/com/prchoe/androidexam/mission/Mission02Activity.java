package com.prchoe.androidexam.mission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.prchoe.androidexam.R;

public class Mission02Activity extends AppCompatActivity implements View.OnClickListener{

    private Button mSendBtn, mCloseBtn;
    private EditText mMessageEditText;
    private TextView mMessageLengthTV;
    private String message;
    private int messageLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission02);

        initView();
        initListener();
    }

    private void initView() {
        mSendBtn = (Button)findViewById(R.id.sendBtn);
        mCloseBtn = (Button)findViewById(R.id.closeBtn);
        mMessageEditText = (EditText)findViewById(R.id.messageEditText);
        mMessageLengthTV = (TextView)findViewById(R.id.messageLengthTV);
    }

    private void initListener() {
        mSendBtn.setOnClickListener(this);
        mCloseBtn.setOnClickListener(this);

        mMessageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                getMessage();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sendBtn:
                Toast.makeText(getApplicationContext(), message , Toast.LENGTH_SHORT).show();
                break;
            case R.id.closeBtn:
                finish();
                break;
        }

    }

    private void getMessage() {
        message = mMessageEditText.getText().toString();
        messageLength = mMessageEditText.getText().length();

        mMessageLengthTV.setText(messageLength + " / 80 바이트");
    }
}
