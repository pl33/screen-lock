/*
 * SPDX-License-Identifier: MPL-2.0
 *   Copyright (c) 2024 Philipp Le <philipp@philipple.de>.
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */


package de.philipple.screen_lock;

/**
 * This exception is thrown when LockAccessibilityService is not available.
 * Reasons could be:
 * - API version is less than 28
 * - LockAccessibilityService is not started because user has not granted
 *   Accessibility Service permissions
 */
public class LockAccessibilityServiceUnavailable extends RuntimeException {

}
