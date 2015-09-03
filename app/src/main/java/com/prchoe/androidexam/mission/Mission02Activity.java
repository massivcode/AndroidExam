
package com.prchoe.androidexam.mission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.prchoe.androidexam.R;

public class Mission02Activity extends AppCompatActivity implements View.OnClickListener {

    private Button mSendBtn, mCloseBtn;
    private EditText mMessageEditText;
    private TextView mMessageLengthTV;
    private String message = "";
    private int messageLength = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission02);

        initView();
        initListener();
    }

    private void initView() {
        mSendBtn = (Button) findViewById(R.id.sendBtn);
        mCloseBtn = (Button) findViewById(R.id.closeBtn);
        mMessageEditText = (EditText) findViewById(R.id.messageEditText);
        mMessageLengthTV = (TextView) findViewById(R.id.messageLengthTV);
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
                if (messageLength >= 80) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mMessageEditText.getWindowToken(), 0);
                    Toast.makeText(getApplicationContext(), "80 바이트를 초과하였습니다.", Toast.LENGTH_SHORT)
                            .show();
                    mMessageEditText.setText(message);
                } else {
                    message = s.toString();
                    messageLength = message.getBytes().length;
                    mMessageLengthTV.setText(messageLength + " / 80 바이트");

                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sendBtn:
                String result = "문자 내용 : " + message + "\r\n\r\n문자 길이 : " + messageLength + " 바이트";
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                break;
            case R.id.closeBtn:
                finish();
                break;
        }

    }

}
