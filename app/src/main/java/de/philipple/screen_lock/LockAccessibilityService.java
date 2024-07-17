/*
 * SPDX-License-Identifier: MPL-2.0
 *   Copyright (c) 2024 Philipp Le <philipp@philipple.de>.
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */


package de.philipple.screen_lock;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

/**
 * This service is started by the Android system as an Accessibility Service.
 * Accessibility Services have the permission to lock the screen on user's
 * behalf. The user must first grant permission before the Android system
 * starts this service. As soon as it is started, LockProxy can perform the
 * screen lock action. For this job, the service stores its reference in a
 * singleton variable. LockProxy can then access the instance. On stop of the
 * service, the reference is cleared. This Accessibility Service does not
 * process any events. It only locks the screen.
 */
public class LockAccessibilityService extends AccessibilityService {

    /**
     * Singleton reference to an instance of this class for later access to
     * the Accessibility Service functionality.
     */
    public static LockAccessibilityService instance = null;

    /**
     * This function is called when the Android system starts the service if
     * the user has enabled it as an Accessibility Service. It stores a
     * reference to this class in a singleton variable for later access to
     * the Accessibility Service functionality.
     */
    @Override
    public void onServiceConnected() {
        super.onServiceConnected();
        Toast.makeText(this, "Lock Screen service started", Toast.LENGTH_SHORT).show();
        instance = this;
    }

    /**
     * The Android system invokes this function when the service is terminated.
     * This might happen when the system shuts down or the user requests to
     * deactivate the Accessibility Service. The singleton reference is cleared.
     * Its functionality cannot be used later.
     *
     * @param intent Intent which is the reason for the unbind
     */
    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this, "Lock Screen service terminating", Toast.LENGTH_SHORT).show();
        instance = null;
        return super.onUnbind(intent);
    }

    /**
     * This function must be implement. Since no accessibility events are
     * processed, the function body is left empty.
     *
     * @param accessibilityEvent Information about the accessibility event
     */
    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {

    }

    /**
     * This function must be implement. Since no accessibility events are
     * processed, the function body is left empty.
     */
    @Override
    public void onInterrupt() {

    }

}
