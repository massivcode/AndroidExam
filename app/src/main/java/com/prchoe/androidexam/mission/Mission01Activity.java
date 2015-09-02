package com.prchoe.androidexam.mission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.prchoe.androidexam.R;

public class Mission01Activity extends AppCompatActivity implements ImageView.OnClickListener {

    private ImageView mTopImage, mBottomImage;
    private Button mUp_btn, mDown_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission01);

        initView();
        initListener();

    }

    @Override
    public void onClick(View v) {
        move(v.getId());
    }

    private void initView() {
        mTopImage = (ImageView) findViewById(R.id.topImage);
        mBottomImage = (ImageView) findViewById(R.id.bottomImage);
        mUp_btn = (Button) findViewById(R.id.up_btn);
        mDown_btn = (Button) findViewById(R.id.down_btn);
    }

    private void initListener() {
        mUp_btn.setOnClickListener(this);
        mDown_btn.setOnClickListener(this);
    }

    private void move(int id) {
        switch (id) {
            case R.id.up_btn:
                moveUp();
                break;
            case R.id.down_btn:
                moveDown();
                break;
        }
    }

    private void moveUp() {
        if (mBottomImage.getResources() == null) {

        } else {
            mBottomImage.setImageDrawable(null);
            mTopImage.setImageResource(R.drawable.bada1);
        }
    }

    private void moveDown() {
        if (mTopImage.getResources() == null) {
        } else {
            mTopImage.setImageDrawable(null);
            mBottomImage.setImageResource(R.drawable.bada1);
        }

    }


}
