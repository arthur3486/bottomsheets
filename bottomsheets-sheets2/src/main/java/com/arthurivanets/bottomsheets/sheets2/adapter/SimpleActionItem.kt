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

package com.arthurivanets.bottomsheets.sheets2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arthurivanets.bottomsheets.sheets2.R
import com.arthurivanets.bottomsheets.sheets2.SimpleAction
import com.arthurivanets.bottomsheets.sheets2.dsl.ItemTheme
import com.arthurivanets.bottomsheets.sheets2.extensions.applyColor
import com.arthurivanets.bottomsheets.sheets2.extensions.colorCompoundDrawables
import com.arthurivanets.bottomsheets.sheets2.extensions.updateCompoundDrawables

internal class SimpleActionItem(action: SimpleAction) :
    AbstractItem<SimpleAction, SimpleActionItem.ViewHolder>(action) {

    override fun create(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        return ViewHolder(
            inflater.inflate(
                R.layout.item_simple_action,
                parent,
                false
            ) as TextView
        )
    }

    override fun performBinding(viewHolder: ViewHolder) = with(viewHolder.actionView) {
        updateCompoundDrawables(start = model.icon?.getIcon(context))
        text = model.title.getText(context)
    }

    class ViewHolder(internal val actionView: TextView) : RecyclerView.ViewHolder(actionView),
        HasListeners {

        fun setTheme(theme: ItemTheme) = with(actionView) {
            colorCompoundDrawables(theme.iconColor)
            setTextColor(theme.textColor)
            background = background?.applyColor(theme.overlayColor)
        }

    }

}