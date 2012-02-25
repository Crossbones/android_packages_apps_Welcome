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
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemProperties;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;


public class Welcome extends Activity {

    public static final String PREFS_NAME = "Welcome";
    public static final String ROM_VERSION = "rom_version";
    private static final String ROM_VERSION_PROP = "ro.goo.version";


    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.welcome);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    public void page0(View view) {
        setContentView(R.layout.welcome);
    }

    public void page1(View view) {
        setContentView(R.layout.welcome_changelog);

        TextView changelogVersion = (TextView) findViewById(R.id.changelog_version);
        String version =  getRomVersion();
        changelogVersion.append(" " + version);

        //TODO: Add changelog dyanically via file
    }

    public void page2(View view) {
        setContentView(R.layout.welcome_contact);
    }

    public void page3(View view) {
        setContentView(R.layout.welcome_donate);
    }

    public void finishWelcome(View view) {
        String romVersion = getRomVersion();

        SharedPreferences prefs = this.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor prefEditor = prefs.edit();
        prefEditor.putString(ROM_VERSION, romVersion);
        prefEditor.commit();

        finish();
    }

    public void launchDonate(View view) {
        Intent browse = new Intent();
        browse.setAction(Intent.ACTION_VIEW);
        browse.setData(Uri.parse(getString(R.string.donate_url)));
        startActivity(browse);
    }

    public String getRomVersion() {
        String version =  SystemProperties.get(ROM_VERSION_PROP);
        return version;
    }
}
