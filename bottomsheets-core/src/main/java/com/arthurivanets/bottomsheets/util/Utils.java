/*
 * Copyright 2017 Arthur Ivanets, arthur.ivanets.work@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.arthurivanets.bottomsheets.util;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;

import androidx.annotation.NonNull;

/**
 * A set of the general project utils.
 */
public final class Utils {

    public static final int API_VERSION = Build.VERSION.SDK_INT;

    public static final boolean IS_AT_LEAST_JELLY_BEAN = (API_VERSION >= Build.VERSION_CODES.JELLY_BEAN);
    public static final boolean IS_AT_LEAST_KITKAT = (API_VERSION >= Build.VERSION_CODES.KITKAT);
    public static final boolean IS_AT_LEAST_LOLLIPOP = (API_VERSION >= Build.VERSION_CODES.LOLLIPOP);
    public static final boolean IS_AT_LEAST_MARSHMALLOW = (API_VERSION >= Build.VERSION_CODES.M);
    public static final boolean IS_AT_LEAST_NOUGAT = (API_VERSION >= Build.VERSION_CODES.N);
    public static final boolean IS_AT_LEAST_OREO = (API_VERSION >= Build.VERSION_CODES.O);

    /**
     * Retrieves the size (height) of the system status bar.
     *
     * @param context a valid context
     * @return the retrieved size (height) of the system status bar
     */
    public static int getStatusBarSize(@NonNull Context context) {
        Preconditions.nonNull(context);

        final Resources resources = context.getResources();
        final int resourceId = resources.getIdentifier(
            "status_bar_height",
            "dimen",
            "android"
        );

        return resources.getDimensionPixelSize(resourceId);
    }

    /**
     * Retrieves the size (height) of the system navigation bar.
     *
     * @param context a valid context
     * @return the retrieved size (height) of the system navigation bar
     */
    public static int getNavigationBarSize(@NonNull Context context) {
        Preconditions.nonNull(context);

        final Resources resources = context.getResources();
        final int resourceId = resources.getIdentifier(
            "navigation_bar_height",
            "dimen",
            "android"
        );

        return resources.getDimensionPixelSize(resourceId);
    }

}
