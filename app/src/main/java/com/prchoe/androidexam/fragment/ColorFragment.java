package com.prchoe.androidexam.fragment;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.prchoe.androidexam.R;

import java.util.Random;

/**
 * A placeholder fragment containing a simple view.
 *
 * 생성시 랜덤한 색깔이 적용되는 Fragment
 */
public class ColorFragment extends Fragment {

    private ImageView mImageView;

    public ColorFragment() {

    }

    // View를 만드는 곳
    // 여기서 해라.
    @Nullable // view가 null일 수 있다
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 레이아웃, 붙일 부모 뷰, 루트냐 아니냐
        View view = inflater.inflate(R.layout.fragment_color, container, false);
        mImageView = (ImageView)view.findViewById(R.id.iv_image);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mImageView.setBackgroundColor(getRandomColor());
    }

    private int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    public void setColor(int color) {
        mImageView.setBackgroundColor(color);
    }
}
