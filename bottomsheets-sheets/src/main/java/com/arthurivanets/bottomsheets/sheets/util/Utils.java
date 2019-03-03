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

package com.arthurivanets.bottomsheets.sheets.util;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.widget.TextView;

import com.arthurivanets.bottomsheets.util.Preconditions;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import static com.arthurivanets.bottomsheets.util.Utils.IS_AT_LEAST_NOUGAT;

/**
 * General Module Utils.
 */
public final class Utils {




    /**
     * Disables all the {@link RecyclerView} animations.
     */
    public static void disableAnimations(@NonNull RecyclerView recyclerView) {
        Preconditions.nonNull(recyclerView);

        if(recyclerView.getItemAnimator() != null) {
            recyclerView.setItemAnimator(null);
        }
    }




    /**
     * Sets the {@link TextView}'s left {@link Drawable}.
     */
    public static void setDrawableLeft(@NonNull TextView textView, @Nullable Drawable drawable) {
        Preconditions.nonNull(textView);

        textView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
    }




    /**
     * Retrieves the {@link Drawable} for the corresponding drawable resource id
     * and applies the specified color to it.
     *
     * @param context the context
     * @param drawableId the drawable resource id
     * @param desiredColor the desired color to be applied to the retrieved drawable
     * @return the retrieved and colored drawable or null.
     */
    @Nullable
    public static Drawable getColoredDrawable(@NonNull Context context,
                                              @DrawableRes int drawableId,
                                              @ColorInt int desiredColor) {
        Preconditions.nonNull(context);

        final Drawable drawable = ContextCompat.getDrawable(context, drawableId);

        return ((drawable != null) ? getColoredDrawable(drawable, desiredColor) : null);
    }




    /**
     * Applies the specified color to the specified {@link Drawable}.
     */
    @NonNull
    public static Drawable getColoredDrawable(@NonNull Drawable drawable, @ColorInt int desiredColor) {
        Preconditions.nonNull(drawable);

        drawable.mutate().setColorFilter(
            desiredColor,
            PorterDuff.Mode.SRC_ATOP
        );

        return drawable;
    }




    /**
     * Creates a formatted html text from the specified raw text.
     *
     * @param rawText the text to be formatted
     * @return the formatted html text
     */
    @SuppressWarnings("NewApi")
    public static CharSequence fromHtml(CharSequence rawText) {
        if(!(rawText instanceof String)) {
            return rawText;
        }

        final String text = (String) rawText;

        if(IS_AT_LEAST_NOUGAT) {
            return Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(text);
        }
    }




}