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

package com.arthurivanets.bottomsheets.sheets2.dsl

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.arthurivanets.bottomsheets.BottomSheet
import com.arthurivanets.bottomsheets.sheets2.Action
import com.arthurivanets.bottomsheets.sheets2.ActionPickerBottomSheet
import com.arthurivanets.bottomsheets.sheets2.adapter.DividerItem
import com.arthurivanets.bottomsheets.sheets2.adapter.Item
import com.arthurivanets.bottomsheets.sheets2.adapter.ItemThemeApplier

@ActionPickerConfigBuilderDsl
fun Fragment.customActionPickerBottomSheet(
    builder: CustomActionPickerConfigBuilder.() -> Unit
): BottomSheet {
    return requireActivity().customActionPickerBottomSheet(builder)
}

@ActionPickerConfigBuilderDsl
fun Activity.customActionPickerBottomSheet(
    builder: CustomActionPickerConfigBuilder.() -> Unit
): BottomSheet {
    return ActionPickerBottomSheet(
        context = this,
        pickerConfig = CustomActionPickerConfigBuilder(this.applicationContext)
            .also(builder)
            .build()
    )
}

@ActionPickerConfigBuilderDsl
class CustomActionPickerConfigBuilder internal constructor(
    context: Context
) : AbstractActionPickerConfigBuilder(context) {

    var itemThemeApplier: ItemThemeApplier = { _, _ -> /* Do Nothing */ }

    fun section(builder: CustomActionPickerConfigBuilder.() -> Unit) = apply {
        items += DividerItem()
        builder(this)
    }

    fun item(item: Item<out Action>) = apply {
        items += item
    }

    override fun createItemThemeApplier(): ItemThemeApplier {
        return CustomItemThemeApplier(dividerColor, itemThemeApplier)
    }

}

private class CustomItemThemeApplier(
    private val dividerColor: Int,
    private val itemThemeApplier: ItemThemeApplier
) : ItemThemeApplier {

    override fun invoke(item: Item<*>, viewHolder: RecyclerView.ViewHolder) {
        when (viewHolder) {
            is DividerItem.ViewHolder -> viewHolder.setColor(dividerColor)
            else -> itemThemeApplier(item, viewHolder)
        }
    }

}