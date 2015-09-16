package com.prchoe.androidexam.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.prchoe.androidexam.R;

import java.util.Random;

public class FragmentActivity extends AppCompatActivity implements View.OnClickListener {

    private ColorFragment mColorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // xml단에서 fragmentView에 fragmentClass를 추가 안했을 경우, 프래그먼트 추가
//        getSupportFragmentManager().beginTransaction().add(R.id.frag_color, new ColorFragment()).commit();

        mColorFragment = (ColorFragment)getSupportFragmentManager().findFragmentById(R.id.frag_color);

        findViewById(R.id.btn_color).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        mColorFragment.setColor(getRandomColor());
    }

    private int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}
