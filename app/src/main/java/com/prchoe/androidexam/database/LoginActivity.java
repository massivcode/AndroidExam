package com.prchoe.androidexam.database;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.prchoe.androidexam.R;
import com.prchoe.androidexam.database.helper.UserDbHelper;

/**
 * Created by massivCode on 2015-09-18.
 *
 * DB 연습 -
 * 로그인 Activity
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private UserDbHelper mUserDbHelper;

    private LinearLayout mParentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        mParentLayout = (LinearLayout)findViewById(R.id.parent_layout);

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
                // Todo 로그인 처리
                long insertedId = mUserDbHelper.insert("test2", "test2", "test2");
                if(insertedId != -1) {
                    Snackbar.make(mParentLayout, "회원가입이 성공하였습니다. insertId : " + insertedId, Snackbar.LENGTH_LONG).show();
                }

                break;
        }

    }
}
