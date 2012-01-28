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
import android.content.SharedPreferences;
//import android.support.v4.view.PagerAdapter;
//import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;


public class Welcome extends Activity {

    private final String FIRST_BOOT = "first_boot";

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        SharedPreferences prefs = getPreferences(0);

        if ((prefs.getBoolean(FIRST_BOOT, true)) == true) {
            SharedPreferences.Editor prefEditor = prefs.edit();
            prefEditor.putBoolean(FIRST_BOOT, false);
            prefEditor.commit();

            //setContentView(R.layout.welcome);
            setContentView(R.layout.donate);
        }
    }
}
