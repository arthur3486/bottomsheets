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

import com.arthurivanets.bottomsheets.config.BaseConfigBuilder;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

/**
 * Configuration Builder contract used to implement the concrete
 * {@link com.arthurivanets.bottomsheets.sheets.BaseActionPickerBottomSheet}-based config builders.
 */
public interface ActionPickerConfigBuilder<BT extends ActionPickerConfigBuilder, CT> extends BaseConfigBuilder<BT, CT> {

    /**
     * Sets the bottom sheet title.
     *
     * @param title the bottom sheet title
     * @return the current instance of the configuration builder (for chaining purposes)
     */
    BT title(@NonNull CharSequence title);

    /**
     * Sets the bottom sheet title text color.
     *
     * @param textColor the bottom sheet title text color
     * @return the current instance of the configuration builder (for chaining purposes)
     */
    BT titleTextColor(@ColorInt int textColor);

    /**
     * Sets the bottom sheets title text size.
     *
     * @param textSize the bottom sheet title text size
     * @return the current instance of the configuration builder (for chaining purposes)
     */
    BT titleTextSize(float textSize);

}