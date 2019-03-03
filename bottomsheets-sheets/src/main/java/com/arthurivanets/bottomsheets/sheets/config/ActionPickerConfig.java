/*
 * Copyright 2017 Arthur Ivanets, arthur.ivanets.l@gmail.com
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

package com.arthurivanets.bottomsheets.sheets.config;

import com.arthurivanets.bottomsheets.config.BaseConfig;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

/**
 * Configuration contract used to implement the concrete configs
 * for the {@link com.arthurivanets.bottomsheets.sheets.BaseActionPickerBottomSheet}-based bottom sheets.
 */
public interface ActionPickerConfig extends BaseConfig {

    /**
     * Retrieves the bottom sheet title.
     *
     * @return the bottom sheet title
     */
    @NonNull
    CharSequence getTitle();

    /**
     * Retrieves the bottom sheet title text color.
     *
     * @return the bottom sheet title text color
     */
    @ColorInt
    int getTitleTextColor();

    /**
     * Retrieves the bottom sheet title text size.
     *
     * @return the bottom sheet title text size
     */
    float getTitleTextSize();

}