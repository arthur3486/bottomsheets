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

package com.arthurivanets.bottomsheets.config;

import android.view.animation.Interpolator;

import com.arthurivanets.bottomsheets.util.Builder;

import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;

/**
 * Configuration Builder contract used to implement the concrete {@link com.arthurivanets.bottomsheets.BottomSheet}
 * config builders.
 */
public interface BaseConfigBuilder<BT extends BaseConfigBuilder, CT> extends Builder<CT> {

    /**
     * Sets the background dim amount (The Background dimming color alpha).
     *
     * @param dimAmount the dim amount to be used for the background dimming
     * @return the current instance of the configuration builder (for chaining purposes)
     */
    @NonNull
    BT dimAmount(@FloatRange(from = 0.0f, to = 1.0f) float dimAmount);

    /**
     * Sets the sheet's top corner radius.
     *
     * @param cornerRadius the sheet's corner radius
     * @return the current instance of the configuration builder (for chaining purposes)
     */
    @NonNull
    BT sheetCornerRadius(float cornerRadius);

    /**
     * Sets the size of the top gap, that is the gap between the bottom edge of the system status bar
     * and the top edge of the sheet, which is utilized when the sheet's height
     * exceeds the available height (in which case the specified top gap size is used to offset
     * the sheet vertically by that exact amount).
     *
     * @param topGapSize the top gap size
     * @return the current instance of the configuration builder (for chaining purposes)
     */
    @NonNull
    BT topGapSize(float topGapSize);

    /**
     * <br>
     *      Sets the sheet's maximum width.
     * <br>
     *     (This is especially useful in cases when your device is in landscape orientation, or you're
     *     using a tablet, as in such cases the full screen width would be too much for the sheet,
     *     so it'd be a great idea to restrict your sheet's width in order to make it more visually appealing)
     * <br>
     *
     * @param maxWidth the maximum width that the sheet can ever have
     * @return the current instance of the configuration builder (for chaining purposes)
     */
    @NonNull
    BT maxSheetWidth(float maxWidth);

    /**
     * Sets the top padding that will be added to the bottom sheet view container.
     *
     * @param extraPaddingTop the extra padding from the top
     * @return the current instance of the configuration builder (for chaining purposes)
     */
    @NonNull
    BT extraPaddingTop(float extraPaddingTop);

    /**
     * Sets the bottom padding that will be added to the bottom sheet view container.
     *
     * @param extraPaddingBottom the extra padding from the bottom
     * @return the current instance of the configuration builder (for chaining purposes)
     */
    @NonNull
    BT extraPaddingBottom(float extraPaddingBottom);

    /**
     * Sets the background dimming color.
     *
     * @param dimColor the color to be used for the background dimming
     * @return the current instance of the configuration builder (for chaining purposes)
     */
    @NonNull
    BT dimColor(@ColorInt int dimColor);

    /**
     * Sets the sheet's background color.
     *
     * @param color the sheet background color
     * @return the current instance of the configuration builder (for chaining purposes)
     */
    @NonNull
    BT sheetBackgroundColor(@ColorInt int color);

    /**
     * Sets the sheet's animation duration (in milliseconds).
     *
     * @param animationDuration the duration of the sheet's animation (in milliseconds)
     * @return the current instance of the configuration builder (for chaining purposes)
     */
    @NonNull
    BT sheetAnimationDuration(long animationDuration);

    /**
     * Sets the sheet's animation interpolator.
     *
     * @param interpolator the sheet's animation interpolator
     * @return the current instance of the configuration builder (for chaining purposes)
     */
    @NonNull
    BT sheetAnimationInterpolator(@NonNull Interpolator interpolator);

    /**
     * Used to specify if the sheet should be dismissed when
     * an outside touch is detected.
     *
     * @param dismissOnTouchOutside whether to dismiss the sheet on touch outside or not
     * @return the current instance of the configuration builder (for chaining purposes)
     */
    @NonNull
    BT dismissOnTouchOutside(boolean dismissOnTouchOutside);

}