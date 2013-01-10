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

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;


public class BootCompleteReceiver extends BroadcastReceiver {

    private static final String WELCOME_INTENT = "com.crossbones.welcome.AboutActivity";
    private static final String TAG = "WelcomeBootReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        AboutActivity aboutActivity = new AboutActivity();

        SharedPreferences prefs = context.getSharedPreferences(aboutActivity.PREFS_NAME, 0);

        String previousRomVersion = prefs.getString(aboutActivity.ROM_VERSION, "0.0.0");
        String currentRomVersion = Utils.getRomVersion();

        Log.d(TAG, "Previous ROM Version: " + previousRomVersion);
        Log.d(TAG, "Current ROM Version: " + currentRomVersion);

        if (!currentRomVersion.equals(previousRomVersion)) {
        //if (currentRomVersion.equals(previousRomVersion)) { //DEBUGGING
            Log.d(TAG, "Running Welcome Activity");

            Intent i = new Intent();
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.setClassName(context, WELCOME_INTENT);
            context.startActivity(i);
        } else {
            Log.d(TAG, "Welcome Activity has already run");
        }

    }
}
