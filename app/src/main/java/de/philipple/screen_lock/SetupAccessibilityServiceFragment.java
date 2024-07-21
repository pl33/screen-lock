/*
 * SPDX-License-Identifier: MPL-2.0
 *   Copyright (c) 2024 Philipp Le <philipp@philipple.de>.
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */


package de.philipple.screen_lock;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * This fragment represents the main view which displays information about
 * this app to the user. The user might also choose to go to the Android
 * Accessibility Service Settings. This view shall be shown if the Accessibility
 * Service is not enabled or on explicit user request.
 */
public class SetupAccessibilityServiceFragment extends Fragment implements View.OnClickListener {

    public SetupAccessibilityServiceFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setup_admin, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        view.findViewById(R.id.fragment_setup_accessibility_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fragment_setup_accessibility_button) {
            requestAccessibilitySetup();
        }
    }

    /**
     * Handles the click event of the buttons which should redirect the user
     * to the Android's Accessibility Settings.
     */
    private void requestAccessibilitySetup() {
        final Activity activity = getActivity();
        if (null == activity) {
            return;
        }
        LockProxy.showAccessibilitySettings(activity);
        activity.finish();
    }

}
