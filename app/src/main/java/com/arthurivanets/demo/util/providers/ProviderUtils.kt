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

@file:JvmName("ProviderUtils")

package com.arthurivanets.demo.util.providers

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.arthurivanets.bottomsheets.sheets.model.Option
import com.arthurivanets.demo.R


/**
 *
 */
fun Context.createOption(id : Long,
                         @DrawableRes iconId : Int,
                         title : CharSequence) : Option {
    return Option()
        .setId(id)
        .setIconId(iconId)
        .setIconColor(ContextCompat.getColor(this, R.color.colorSecondaryTextGray))
        .setTitle(title)
        .setTitleColor(ContextCompat.getColor(this, R.color.colorPrimaryTextBlack))
}