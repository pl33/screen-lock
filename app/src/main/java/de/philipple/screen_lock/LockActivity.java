/*
 * SPDX-License-Identifier: MPL-2.0
 *   Copyright (c) 2024 Philipp Le <philipp@philipple.de>.
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */


package de.philipple.screen_lock;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

/**
 * The LockActivity is started from the launcher icon.
 * If the Accessibility Service is activated, the screen is locked. If not,
 * the SetupAccessibilityServiceFragment is shown.
 */
public class LockActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            LockProxy.lockScreen();
            finishAndRemoveTask();
        } catch (LockAccessibilityServiceUnavailable e) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new SetupAccessibilityServiceFragment()).commit();
        }
    }
}
