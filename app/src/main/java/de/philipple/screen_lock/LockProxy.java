/*
 * SPDX-License-Identifier: MPL-2.0
 *   Copyright (c) 2024 Philipp Le <philipp@philipple.de>.
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */


package de.philipple.screen_lock;

import android.accessibilityservice.AccessibilityService;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.NonNull;

/**
 * This class wraps functionality of other classes in convenience functions.
 */
public class LockProxy {
    /**
     * This functions sends an intent to the Android system to show the
     * Accessibility Service Settings.
     *
     * @param context Context form which the intent should be sent
     */
    public static void showAccessibilitySettings(@NonNull Context context) {
        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            final String message = String.format("Calling Accessibility Settings failed. Reason: %s", e.getMessage());
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Constructs a dialog which requests the user to the Accessibility Service
     * Settings of the Android system. The user is informed why this is
     * necessary. This dialog should be showed if the LockAccessibilityService
     * has not been started yet.
     *
     * @param context Context which shall execute the dialog
     * @return Dialog instance
     */
    public static Dialog makeAlertDialog(@NonNull Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.no_service_alert_dialog_title);
        builder.setMessage(R.string.accessibility_service_permissions_info);
        builder.setPositiveButton(R.string.no_service_alert_dialog_positive_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                showAccessibilitySettings(context);
            }
        });
        builder.setNegativeButton(R.string.no_service_alert_dialog_negative_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        return builder.create();
    }

    /**
     * This function performs a screen lock. It can only work if the
     * LockAccessibilityService is active. Otherwise, it throws a
     * LockAccessibilityServiceUnavailable exception. The screen is locked
     * immediately. It can then be unlocked using any configured unlock
     * method.
     */
    public static void lockScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            if (LockAccessibilityService.instance != null) {
                LockAccessibilityService.instance.performGlobalAction(AccessibilityService.GLOBAL_ACTION_LOCK_SCREEN);
            } else {
                throw new LockAccessibilityServiceUnavailable();
            }
        } else {
            throw new LockAccessibilityServiceUnavailable();
        }
    }
}
