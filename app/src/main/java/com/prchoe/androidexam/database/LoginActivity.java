package com.prchoe.androidexam.database;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.prchoe.androidexam.R;

/**
 * Created by massivCode on 2015-09-18.
 *
 * DB 연습 -
 * 로그인 Activity
 */
public class LoginActivity extends AppCompatActivity {

    private com.beardedhen.androidbootstrap.BootstrapButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
    }
}
