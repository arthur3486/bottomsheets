/*
 * Copyright 2019 Arthur Ivanets, arthur.ivanets.l@gmail.com
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

@file:JvmName("ConfigUtils")

package com.arthurivanets.bottomsheets.ktx

import android.content.Context
import androidx.fragment.app.Fragment
import com.arthurivanets.bottomsheets.config.BaseConfig
import com.arthurivanets.bottomsheets.config.BaseConfigBuilder
import com.arthurivanets.bottomsheets.sheets.config.ActionPickerConfig
import com.arthurivanets.bottomsheets.sheets.config.ActionPickerConfigBuilder
import com.arthurivanets.bottomsheets.sheets.config.Config

/**
 * Used to build a configuration for the [com.arthurivanets.bottomsheets.BaseBottomSheet].
 *
 * @param builder configuration builder
 */
inline fun Fragment.baseConfig(crossinline builder: BaseConfigBuilder<out BaseConfigBuilder<*, *>, BaseConfig>.() -> Unit = {}): BaseConfig {
    assertAttachedToContext()
    return context!!.baseConfig(builder)
}

/**
 * Used to build a configuration for the [com.arthurivanets.bottomsheets.BaseBottomSheet].
 *
 * @param builder configuration builder
 */
inline fun Context.baseConfig(crossinline builder: BaseConfigBuilder<out BaseConfigBuilder<*, *>, BaseConfig>.() -> Unit = {}): BaseConfig {
    return com.arthurivanets.bottomsheets.config.Config.Builder(this)
        .also { builder(it) }
        .build()
}

/**
 * Used to build a configuration for the Action Picker Bottom Sheet.
 *
 * @param builder configuration builder
 */
inline fun Fragment.actionPickerConfig(crossinline builder: ActionPickerConfigBuilder<out ActionPickerConfigBuilder<*, *>, ActionPickerConfig>.() -> Unit = {}): ActionPickerConfig {
    assertAttachedToContext()
    return context!!.actionPickerConfig(builder)
}

/**
 * Used to build a configuration for the Action Picker Bottom Sheet.
 *
 * @param builder configuration builder
 */
inline fun Context.actionPickerConfig(crossinline builder: ActionPickerConfigBuilder<out ActionPickerConfigBuilder<*, *>, ActionPickerConfig>.() -> Unit = {}): ActionPickerConfig {
    return Config.Builder(this)
        .also { builder(it) }
        .build()
}