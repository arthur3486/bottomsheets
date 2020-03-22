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
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView
import com.arthurivanets.bottomsheets.sheets2.R

internal class DividerItem : AbstractItem<Unit, DividerItem.ViewHolder>(Unit) {


    override fun create(inflater : LayoutInflater, parent : ViewGroup) : RecyclerView.ViewHolder {
        return ViewHolder(
            inflater.inflate(
                R.layout.item_divider,
                parent,
                false
            )
        )
    }


    override fun performBinding(viewHolder : ViewHolder) {
        // do nothing.
    }


    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun setColor(@ColorInt color : Int) {
            itemView.setBackgroundColor(color)
        }

    }


}