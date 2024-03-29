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

package com.arthurivanets.bottomsheets.sheets.config;

import android.content.Context;
import android.content.res.Resources;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

import com.arthurivanets.bottomsheets.sheets.R;
import com.arthurivanets.bottomsheets.util.Preconditions;

import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.math.MathUtils;

/**
 * A concrete implementation of the {@link ActionPickerConfig} used by the
 * {@link com.arthurivanets.bottomsheets.sheets.BaseActionPickerBottomSheet}-based bottom sheets.
 */
public final class Config implements ActionPickerConfig {

    private final float dimAmount;
    private final float sheetCornerRadius;
    private final float maxSheetWidth;
    private final float topGapSize;
    private final float titleTextSize;
    private final float extraPaddingTop;
    private final float extraPaddingBottom;

    private final int dimColor;
    private final int sheetBackgroundColor;
    private final int titleTextColor;

    private final CharSequence title;

    private final long animationDuration;
    private final Interpolator animationInterpolator;

    private final boolean isDismissableOnTouchOutside;

    private Config(Builder builder) {
        this.dimAmount = builder.dimAmount;
        this.sheetCornerRadius = builder.sheetCornerRadius;
        this.maxSheetWidth = builder.maxSheetWidth;
        this.topGapSize = builder.topGapSize;
        this.extraPaddingTop = builder.extraPaddingTop;
        this.extraPaddingBottom = builder.extraPaddingBottom;
        this.titleTextSize = builder.titleTextSize;
        this.dimColor = builder.dimColor;
        this.sheetBackgroundColor = builder.sheetBackgroundColor;
        this.titleTextColor = builder.titleTextColor;
        this.title = builder.title;
        this.animationDuration = builder.animationDuration;
        this.animationInterpolator = builder.animationInterpolator;
        this.isDismissableOnTouchOutside = builder.isDismissableOnTouchOutside;
    }

    @NonNull
    @Override
    public final CharSequence getTitle() {
        return this.title;
    }

    @Override
    public final int getTitleTextColor() {
        return this.titleTextColor;
    }

    @Override
    public final float getTitleTextSize() {
        return this.titleTextSize;
    }

    @Override
    public final int getDimColor() {
        return this.dimColor;
    }

    @Override
    public final float getDimAmount() {
        return this.dimAmount;
    }

    @Override
    public final float getTopGapSize() {
        return this.topGapSize;
    }

    @Override
    public final float getExtraPaddingTop() {
        return this.extraPaddingTop;
    }

    @Override
    public final float getExtraPaddingBottom() {
        return this.extraPaddingBottom;
    }

    @Override
    public final float getMaxSheetWidth() {
        return this.maxSheetWidth;
    }

    @Override
    public final int getSheetBackgroundColor() {
        return this.sheetBackgroundColor;
    }

    @Override
    public final float getSheetCornerRadius() {
        return this.sheetCornerRadius;
    }

    @Override
    public final long getSheetAnimationDuration() {
        return this.animationDuration;
    }

    @NonNull
    @Override
    public final Interpolator getSheetAnimationInterpolator() {
        return this.animationInterpolator;
    }

    @Override
    public final boolean isDismissableOnTouchOutside() {
        return this.isDismissableOnTouchOutside;
    }

    public static final class Builder implements ActionPickerConfigBuilder<Builder, ActionPickerConfig> {

        private float dimAmount;
        private float sheetCornerRadius;
        private float maxSheetWidth;
        private float topGapSize;
        private float titleTextSize;
        private float extraPaddingTop;
        private float extraPaddingBottom;

        private int dimColor;
        private int sheetBackgroundColor;
        private int titleTextColor;

        private CharSequence title;

        private long animationDuration;
        private Interpolator animationInterpolator;

        private boolean isDismissableOnTouchOutside;

        public Builder(@NonNull Context context) {
            Preconditions.nonNull(context);

            final Resources resources = context.getResources();

            this.dimAmount = DEFAULT_DIM_AMOUNT;
            this.sheetCornerRadius = resources.getDimension(R.dimen.bottom_sheet_sheet_corner_radius);
            this.topGapSize = 0;
            this.extraPaddingTop = 0;
            this.extraPaddingBottom = 0;
            this.titleTextSize = resources.getDimensionPixelSize(R.dimen.action_picker_bottom_sheet_title_text_size);
            this.maxSheetWidth = resources.getDimension(R.dimen.bottom_sheet_max_sheet_width);
            this.dimColor = ContextCompat.getColor(context, R.color.bottom_sheet_dim_color);
            this.sheetBackgroundColor = ContextCompat.getColor(context, R.color.bottom_sheet_background_color);
            this.titleTextColor = ContextCompat.getColor(context, R.color.action_picker_bottom_sheet_title_color);
            this.title = "";
            this.animationDuration = DEFAULT_ANIMATION_DURATION;
            this.animationInterpolator = new DecelerateInterpolator(1.5f);
            this.isDismissableOnTouchOutside = true;
        }

        @Override
        public final Builder title(@NonNull CharSequence title) {
            this.title = Preconditions.checkNonNull(title);
            return this;
        }

        @Override
        public final Builder titleTextColor(@ColorInt int textColor) {
            this.titleTextColor = textColor;
            return this;
        }

        @Override
        public final Builder titleTextSize(float textSize) {
            this.titleTextSize = textSize;
            return this;
        }

        @NonNull
        public final Builder dimAmount(@FloatRange(from = 0.0f, to = 1.0f) float dimAmount) {
            this.dimAmount = MathUtils.clamp(dimAmount, MIN_DIM_AMOUNT, MAX_DIM_AMOUNT);
            return this;
        }

        @NonNull
        public final Builder sheetCornerRadius(float cornerRadius) {
            this.sheetCornerRadius = cornerRadius;
            return this;
        }

        @NonNull
        public final Builder topGapSize(float topGapSize) {
            this.topGapSize = topGapSize;
            return this;
        }

        @NonNull
        @Override
        public final Builder extraPaddingTop(float extraPaddingTop) {
            this.extraPaddingTop = extraPaddingTop;
            return this;
        }

        @NonNull
        @Override
        public final Builder extraPaddingBottom(float extraPaddingBottom) {
            this.extraPaddingBottom = extraPaddingBottom;
            return this;
        }

        @NonNull
        public final Builder maxSheetWidth(float maxWidth) {
            this.maxSheetWidth = maxWidth;
            return this;
        }

        @NonNull
        public final Builder dimColor(@ColorInt int dimColor) {
            this.dimColor = dimColor;
            return this;
        }

        @NonNull
        public final Builder sheetBackgroundColor(@ColorInt int color) {
            this.sheetBackgroundColor = color;
            return this;
        }

        @NonNull
        public final Builder sheetAnimationDuration(long animationDuration) {
            this.animationDuration = animationDuration;
            return this;
        }

        @NonNull
        public final Builder sheetAnimationInterpolator(@NonNull Interpolator interpolator) {
            this.animationInterpolator = Preconditions.checkNonNull(interpolator);
            return this;
        }

        @NonNull
        public final Builder dismissOnTouchOutside(boolean dismissOnTouchOutside) {
            this.isDismissableOnTouchOutside = dismissOnTouchOutside;
            return this;
        }

        @NonNull
        @Override
        public final ActionPickerConfig build() {
            return new Config(this);
        }

    }

}
