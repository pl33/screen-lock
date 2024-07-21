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
 * The SettingsActivity is invoked on explicit user request by either long
 * tapping the tile or selecting the Settings Shortcut on the launcher icon.
 * The activity shows the SetupAccessibilityServiceFragment.
 */
public class SettingsActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new SetupAccessibilityServiceFragment()).commit();
    }
}
