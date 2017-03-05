package com.luciferldy.viewpagerindicator.simple;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luciferldy.viewpagerindicator.ViewPagerIndicator;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        final ViewPagerIndicator indicator = new ViewPagerIndicator(getBaseContext());
        SimplePagerAdapter adapter = new SimplePagerAdapter(pager.getContext());
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i(LOG_TAG, String.format("onPageScrolled position = %d, positionOffset = %f", position, positionOffset));
                indicator.moveIndicator(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                Log.i(LOG_TAG, "onPageSelected position = " + position);
                indicator.setCurrentPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        pager.setCurrentItem(0);
    }

    class SimplePagerAdapter extends PagerAdapter {

        private Random random = new Random();
        private int mSize;

        public SimplePagerAdapter(Context context) {
            super();
            mSize = 5;
        }

        @Override
        public int getCount() {
            return mSize;
        }

        public void setCount(int count) {
            this.mSize = count;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView textView = new TextView(container.getContext());
            textView.setText(String.valueOf(position + 1));
            textView.setBackgroundColor(0xff000000 | random.nextInt(0x00ffffff));
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(48);
            container.addView(textView, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            return textView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        public void addItem() {
            mSize++;
            notifyDataSetChanged();
        }

        public void removeItem() {
            mSize--;
            mSize = mSize < 0 ? 0 : mSize;
            notifyDataSetChanged();
        }
    }
}
