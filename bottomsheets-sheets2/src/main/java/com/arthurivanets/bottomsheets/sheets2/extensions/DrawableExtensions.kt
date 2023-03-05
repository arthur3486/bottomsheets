/*
 * Copyright 2019 Arthur Ivanets, arthur.ivanets.work@gmail.com
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

package com.arthurivanets.bottomsheets.sheets2.extensions

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.os.Build
import androidx.annotation.ColorInt

@SuppressLint("NewApi")
internal fun Drawable.applyColor(@ColorInt color: Int): Drawable {
    val newDrawable = this.mutate()

    return when {
        ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) && (newDrawable is RippleDrawable)) -> newDrawable.also {
            it.setColor(ColorStateList.valueOf(color))
        }
        (newDrawable is GradientDrawable) -> newDrawable.also { it.setColor(color) }
        else -> newDrawable.also {
            it.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN)
        }
    }
}
