/*
 * Copyright (C) 2012 Crossbones Software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.crossbones.welcome;

import android.app.Activity;
import android.content.Context;
//import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class Welcome extends Activity implements OnClickListener {

   // private final String FIRST_BOOT = "first_boot";

    ViewPager mViewPager;

    private Button mDonateButton;
    private Button mFinishButton;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        //SharedPreferences prefs = getPreferences(0);

       // if (prefs.getBoolean(FIRST_BOOT, true)) {
       //     SharedPreferences.Editor prefEditor = prefs.edit();
       //     prefEditor.putBoolean(FIRST_BOOT, false);
       //     prefEditor.commit();

        WelcomePagerAdapter adapter = new WelcomePagerAdapter();

        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.welcome_pager);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);

        setContentView(mViewPager);

//            mDonateButton = (Button) findViewById(R.id.page3_donate_button);
//            mDonateButton.setOnClickListener(this);
//            mFinishButton = (Button) findViewById(R.id.page3_finish_button);
//            mFinishButton.setOnClickListener(this);
        //} else {
        //    finish();
        //}
    }

    @Override
    public void onClick(View v) {
        if (v == mDonateButton) {
            Donate donate = new Donate();
            donate.launchDonate();
        }
        if (v == mFinishButton) {
            return;
        }
    }

    private class WelcomePagerAdapter extends PagerAdapter {

        public int getCount() {
            return 4;
        }

        public Object instantiateItem(View collection, int position) {
            LayoutInflater inflater = (LayoutInflater) collection.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            int resId = 0;
            switch (position) {
            case 0:
                resId = R.layout.page0;
                break;
            case 1:
                resId = R.layout.page1;
                break;
            case 2:
                resId = R.layout.page2;
                break;
            case 3:
                resId = R.layout.page3;
                break;
            }

            View view = inflater.inflate(resId, null);

            ((ViewPager) collection).addView(view, 0);

            return view;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == ((View) arg1);
        }

        @Override
        public Parcelable saveState() {
            return null;
        }
    }
}
