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

@file:JvmName("BottomSheetsUtils")

package com.arthurivanets.bottomsheets.ktx

import android.app.Activity
import androidx.fragment.app.Fragment
import com.arthurivanets.bottomsheets.BottomSheet
import com.arthurivanets.bottomsheets.sheets.ActionPickerBottomSheet
import com.arthurivanets.bottomsheets.sheets.CustomActionPickerBottomSheet
import com.arthurivanets.bottomsheets.sheets.adapters.actionpicker.ActionItem
import com.arthurivanets.bottomsheets.sheets.adapters.actionpicker.BaseActionItem
import com.arthurivanets.bottomsheets.sheets.config.ActionPickerConfig
import com.arthurivanets.bottomsheets.sheets.listeners.OnItemSelectedListener
import com.arthurivanets.bottomsheets.sheets.model.Option


/**
 * Initializes and shows the [Option] Action Picker Bottom Sheet.
 *
 * @param options bottom sheet action options
 * @param config bottom sheet configuration (optional)
 * @param onItemSelectedListener item selection listener
 * @param onDismissListener bottom sheet dismissal listener
 */
@JvmOverloads
fun Fragment.showActionPickerBottomSheet(options : List<Option>,
                                         config : ActionPickerConfig = actionPickerConfig(),
                                         onItemSelectedListener : OnItemSelectedListener<Option>,
                                         onDismissListener : BottomSheet.OnDismissListener? = null) : ActionPickerBottomSheet {
    assertAttachedToActivity()

    return activity!!.showActionPickerBottomSheet(
        options = options,
        config = config,
        onItemSelectedListener = onItemSelectedListener,
        onDismissListener = onDismissListener
    )
}


/**
 * Initializes and shows the [Option] Action Picker Bottom Sheet.
 *
 * @param options bottom sheet action options
 * @param config bottom sheet configuration (optional)
 * @param onItemSelectedListener item selection listener
 * @param onDismissListener bottom sheet dismissal listener
 */
@JvmOverloads
fun Activity.showActionPickerBottomSheet(options : List<Option>,
                                         config : ActionPickerConfig = actionPickerConfig(),
                                         onItemSelectedListener : OnItemSelectedListener<Option>,
                                         onDismissListener : BottomSheet.OnDismissListener? = null) : ActionPickerBottomSheet {
    return ActionPickerBottomSheet.init(
        this,
        options.map(::ActionItem),
        config
    ).apply {
        setOnItemSelectedListener { onItemSelectedListener.onItemSelected(it.itemModel) }
        onDismissListener?.let(::setOnDismissListener)
        show()
    }
}


/**
 * Initializes and shows the Custom Action Picker Bottom Sheet.
 * Your custom action items of type [T] will be utilized.
 *
 * @param items your custom bottom sheet action items
 * @param config bottom sheet configuration (optional)
 * @param onItemSelectedListener item selection listener
 * @param onDismissListener bottom sheet dismissal listener
 */
@JvmOverloads
fun <T : BaseActionItem<*, *, *>> Fragment.showCustomActionPickerBottomSheet(items : List<T>,
                                                                             config : ActionPickerConfig = actionPickerConfig(),
                                                                             onItemSelectedListener : OnItemSelectedListener<T>,
                                                                             onDismissListener : BottomSheet.OnDismissListener? = null) : CustomActionPickerBottomSheet<T> {
    assertAttachedToActivity()

    return activity!!.showCustomActionPickerBottomSheet(
        items = items,
        config = config,
        onItemSelectedListener = onItemSelectedListener,
        onDismissListener = onDismissListener
    )
}


/**
 * Initializes and shows the Custom Action Picker Bottom Sheet.
 * Your custom action items of type [T] will be utilized.
 *
 * @param items your custom bottom sheet action items
 * @param config bottom sheet configuration (optional)
 * @param onItemSelectedListener item selection listener
 * @param onDismissListener bottom sheet dismissal listener
 */
@JvmOverloads
fun <T : BaseActionItem<*, *, *>> Activity.showCustomActionPickerBottomSheet(items : List<T>,
                                                                             config : ActionPickerConfig = actionPickerConfig(),
                                                                             onItemSelectedListener : OnItemSelectedListener<T>,
                                                                             onDismissListener : BottomSheet.OnDismissListener? = null) : CustomActionPickerBottomSheet<T> {
    return CustomActionPickerBottomSheet.init(
        this,
        items,
        config
    ).apply {
        setOnItemSelectedListener(onItemSelectedListener)
        onDismissListener?.let(::setOnDismissListener)
        show()
    }
}