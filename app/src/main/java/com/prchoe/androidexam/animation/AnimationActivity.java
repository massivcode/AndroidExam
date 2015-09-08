package com.prchoe.androidexam.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.prchoe.androidexam.R;

/**
 * Created by massivCode on 2015-09-08.
 */
public class AnimationActivity extends Activity implements View.OnClickListener {

    private ImageView mImageView;
    private Animation mScaleAndRotateAnimaton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        findViewById(R.id.btnStart).setOnClickListener(this);
        mImageView = (ImageView)findViewById(R.id.image_view);
        mScaleAndRotateAnimaton = AnimationUtils.loadAnimation(this, R.anim.anim_scale_rotate);

        // 애니메이션 시작, 끝, 반복할 때
        mScaleAndRotateAnimaton.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mImageView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        mImageView.startAnimation(mScaleAndRotateAnimaton);
    }
}
