package com.prchoe.androidexam.layout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.prchoe.androidexam.R;

public class FrameLayoutActivity extends AppCompatActivity {

    private Button mChangeBtn;
    private ImageView mImageView1, mImageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_layout);

        mChangeBtn = (Button)findViewById(R.id.change_btn);
        mImageView1 = (ImageView)findViewById(R.id.image1);
        mImageView2 = (ImageView)findViewById(R.id.image2);

        mChangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                changeImage();
            }
        });


    }

    public void changeImage() {

        if(mImageView1.getVisibility() == View.VISIBLE) {
            mImageView1.setVisibility(View.INVISIBLE);
            mImageView2.setVisibility(View.VISIBLE);
        } else {
            mImageView1.setVisibility(View.VISIBLE);
            mImageView2.setVisibility(View.INVISIBLE);
        }



    }

}
