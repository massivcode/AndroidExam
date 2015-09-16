package com.prchoe.androidexam.viewpager;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.prchoe.androidexam.R;
import com.prchoe.androidexam.fragment.ColorFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * FragmentStatePagerAdapter vs FragmentPagerAdapter
 *
 * FragmentPagerAdapter : 메모리에 페이지를 미리 로드를 해놓고 사용해서 속도가 빠르다.
 *
 * FragmentStatePagerAdapter : 메모리에 페이지를 로드하는 것을 조절할 수 있다.
 * 비교적 화면전환시 속도가 떨어지지만, 메모리를 절약할 수 있다.
 *
 */

/**
 * Created by massivCode on 2015-09-16.
 *
 * AppCompatActivity extends FragmentActivity
 */
public class ScreenSlideActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    private TabLayout mTabLayout;

    private List<Fragment> mList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);

        mViewPager = (ViewPager)findViewById(R.id.viewpager);

        mTabLayout = (TabLayout)findViewById(R.id.tap_layout);

        // 짝퉁데이터
        mList = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            mList.add(new ColorFragment());
            mTabLayout.addTab(mTabLayout.newTab().setText("Tab " + (i + 1)));
        }

        ScreenSlidePagerAdapter adapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), mList);

        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    // 그냥 PagerAdapter는 화면을 fragment로 안만들었을 때 사용한다.
    // 메모리 관리가 필요할 때 FragmentStatePagerAdapter 사용
    // 괜찮으면 FragmentPagerAdapter 사용
    private class ScreenSlidePagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> mmList;

        public ScreenSlidePagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            mmList = list;
        }

        @Override
        public Fragment getItem(int position) {
            return mmList.get(position);
        }

        @Override
        public int getCount() {
            return mmList.size();
        }
    }
}
