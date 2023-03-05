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

package com.arthurivanets.bottomsheets.sheets2.dsl

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.arthurivanets.bottomsheets.BottomSheet
import com.arthurivanets.bottomsheets.sheets2.ActionId
import com.arthurivanets.bottomsheets.sheets2.ActionPickerBottomSheet
import com.arthurivanets.bottomsheets.sheets2.R
import com.arthurivanets.bottomsheets.sheets2.SimpleAction
import com.arthurivanets.bottomsheets.sheets2.adapter.DividerItem
import com.arthurivanets.bottomsheets.sheets2.adapter.Item
import com.arthurivanets.bottomsheets.sheets2.adapter.ItemThemeApplier
import com.arthurivanets.bottomsheets.sheets2.adapter.SimpleActionItem
import com.arthurivanets.bottomsheets.sheets2.extensions.getCompatColor

@ActionPickerConfigBuilderDsl
fun Fragment.actionPickerBottomSheet(
    builder: ActionPickerConfigBuilder.() -> Unit
): BottomSheet {
    return requireActivity().actionPickerBottomSheet(builder)
}

@ActionPickerConfigBuilderDsl
fun Activity.actionPickerBottomSheet(
    builder: ActionPickerConfigBuilder.() -> Unit
): BottomSheet {
    return ActionPickerBottomSheet(
        context = this,
        pickerConfig = ActionPickerConfigBuilder(this.applicationContext)
            .also(builder)
            .build()
    )
}

@ActionPickerConfigBuilderDsl
class ActionPickerConfigBuilder internal constructor(
    context: Context
) : AbstractActionPickerConfigBuilder(context) {

    private var itemTheme: ItemTheme = ItemTheme.Builder(context).build()

    @ItemThemeBuilderDsl
    fun itemTheme(builder: ItemTheme.Builder.() -> Unit) = apply {
        itemTheme = ItemTheme.Builder(context)
            .also(builder)
            .build()
    }

    fun section(builder: ActionPickerConfigBuilder.() -> Unit) = apply {
        items += DividerItem()
        builder(this)
    }

    fun action(
        id: ActionId,
        @StringRes titleId: Int,
        vararg args: Any
    ) = apply {
        items += SimpleActionItem(
            SimpleAction(
                id = id.id,
                title = Text(
                    resourceId = titleId,
                    args = args
                )
            )
        )
    }

    fun action(
        id: ActionId,
        @DrawableRes iconId: Int,
        @StringRes titleId: Int,
        vararg args: Any
    ) = apply {
        items += SimpleActionItem(
            SimpleAction(
                id = id.id,
                icon = Icon(resourceId = iconId),
                title = Text(
                    resourceId = titleId,
                    args = args
                )
            )
        )
    }

    fun action(
        id: ActionId,
        icon: Drawable,
        @StringRes titleId: Int,
        vararg args: Any
    ) = apply {
        items += SimpleActionItem(
            SimpleAction(
                id = id.id,
                icon = Icon(drawable = icon),
                title = Text(
                    resourceId = titleId,
                    args = args
                )
            )
        )
    }

    fun action(
        id: ActionId,
        @DrawableRes iconId: Int,
        title: CharSequence
    ) = apply {
        items += SimpleActionItem(
            SimpleAction(
                id = id.id,
                icon = Icon(resourceId = iconId),
                title = Text(text = title)
            )
        )
    }

    @JvmOverloads
    fun action(
        id: ActionId,
        icon: Drawable? = null,
        title: CharSequence
    ) = apply {
        items += SimpleActionItem(
            SimpleAction(
                id = id.id,
                icon = icon?.let { Icon(drawable = it) },
                title = Text(text = title)
            )
        )
    }

    override fun createItemThemeApplier(): ItemThemeApplier {
        return DefaultItemThemeApplier(dividerColor, itemTheme)
    }

}

@DslMarker
annotation class ItemThemeBuilderDsl

data class ItemTheme internal constructor(
    internal val iconColor: Int,
    internal val textColor: Int,
    internal val overlayColor: Int
) {

    @ItemThemeBuilderDsl
    class Builder internal constructor(context: Context) {

        @ColorInt
        var iconColor: Int = context.getCompatColor(R.color.action_item_icon)

        @ColorInt
        var textColor: Int = context.getCompatColor(R.color.action_item_text)

        @ColorInt
        var overlayColor: Int = context.getCompatColor(R.color.action_item_overlay)

        internal fun build(): ItemTheme {
            return ItemTheme(
                iconColor = iconColor,
                textColor = textColor,
                overlayColor = overlayColor
            )
        }

    }

}

private class DefaultItemThemeApplier(
    private val dividerColor: Int,
    private val theme: ItemTheme
) : ItemThemeApplier {

    override fun invoke(item: Item<*>, viewHolder: RecyclerView.ViewHolder) {
        when (viewHolder) {
            is DividerItem.ViewHolder -> viewHolder.setColor(dividerColor)
            is SimpleActionItem.ViewHolder -> viewHolder.setTheme(theme)
        }
    }

}
