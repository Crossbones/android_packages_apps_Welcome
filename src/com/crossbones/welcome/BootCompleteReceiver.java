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
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings.System;


public class BootCompleteReceiver extends BroadcastReceiver {

    private final String WELCOME_INTENT = "com.crossbones.welcome.Welcome";
    private final String SYSTEM_FIRST_BOOT = "system_first_boot";

    @Override
    public void onReceive(Context context, Intent intent) {
        ContentResolver cr = context.getContentResolver();

        String firstBoot = System.getString(cr, SYSTEM_FIRST_BOOT);

        if (firstBoot != "0") {
            System.putString(cr, SYSTEM_FIRST_BOOT, "0");
            Intent i = new Intent();
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.setClassName(context, WELCOME_INTENT);
            context.startActivity(i);
        }

    }
}
