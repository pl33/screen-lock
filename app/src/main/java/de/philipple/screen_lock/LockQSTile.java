/*
 * SPDX-License-Identifier: MPL-2.0
 *   Copyright (c) 2024 Philipp Le <philipp@philipple.de>.
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */


package de.philipple.screen_lock;

import android.service.quicksettings.TileService;
import android.widget.Toast;
import android.app.Dialog;

/**
 * The user might add a Quick Settings Tile to access the screen lock quickly.
 * This class represents the tile and handles the tap event on the tile.
 * The tap events either locks the screen, if the Accessibility Service is
 * activated, or prompts the user to go the Android Accessibility Service
 * Settings, if the service is deactivated.
 */
public class LockQSTile extends TileService {
    @Override
    public void onTileAdded() {
        super.onTileAdded();
    }

    @Override
    public void onStartListening() {
        super.onStartListening();
    }

    @Override
    public void onStopListening() {
        super.onStopListening();
    }

    @Override
    public void onClick() {
        super.onClick();
        try {
            LockProxy.lockScreen();
        } catch (LockAccessibilityServiceUnavailable e) {
            Dialog dialog = LockProxy.makeAlertDialog(this);
            showDialog(dialog);
        }
    }

    @Override
    public void onTileRemoved() {
        super.onTileRemoved();
    }
}
