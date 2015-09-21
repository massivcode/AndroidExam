package com.prchoe.androidexam.database;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.prchoe.androidexam.R;
import com.prchoe.androidexam.database.helper.UserDbHelper;
import com.prchoe.androidexam.database.util.UserUtil;

/**
 * Created by massivCode on 2015-09-18.
 *
 * DB 연습 -
 * 로그인 Activity
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private UserDbHelper mUserDbHelper;

    private LinearLayout mParentLayout;

    private EditText mEditTextEmail, mEditTextPassword;
    private TextInputLayout mTIL1, mTIL2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        mParentLayout = (LinearLayout)findViewById(R.id.login_layout);
        mTIL1 = (TextInputLayout)findViewById(R.id.til_1);
        mTIL2 = (TextInputLayout)findViewById(R.id.til_2);
        mEditTextEmail = (EditText)findViewById(R.id.edit_email);
        mEditTextPassword = (EditText)findViewById(R.id.edit_password);

        mTIL1.setErrorEnabled(true);
        mTIL2.setErrorEnabled(true);

        findViewById(R.id.tv_sign_up).setOnClickListener(this);
        findViewById(R.id.btn_login).setOnClickListener(this);

        mUserDbHelper = new UserDbHelper(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_sign_up:
                startActivity(new Intent(this, SignUpActivity.class));
                break;
            case R.id.btn_login:
                if(checkNull() == false) {
                    //Todo : 로그인

                    boolean result = UserUtil.login(this, mEditTextEmail.getText().toString(), mEditTextPassword.getText().toString());

                    if(result) {
                        Snackbar.make(mParentLayout, "방문을 환영합니다.", Snackbar.LENGTH_LONG).show();
                    } else {
                        Snackbar.make(mParentLayout, "로그인에 실패하였습니다", Snackbar.LENGTH_LONG).show();
                    }
                }
                break;
        }

    }

    private boolean checkNull() {
        boolean isNull = true;

        if(mEditTextEmail.getText().toString().isEmpty()) {
            mTIL1.setError("공백입니다.");
        }

        if(mEditTextPassword.getText().toString().isEmpty()) {
            mTIL2.setError("공백입니다.");
        }

        if(!mEditTextEmail.getText().toString().isEmpty() && !mEditTextPassword.getText().toString().isEmpty()) {
            isNull = false;
        }

        return isNull;
    }
}
