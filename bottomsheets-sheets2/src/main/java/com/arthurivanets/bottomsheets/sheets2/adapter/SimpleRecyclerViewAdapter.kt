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

package com.arthurivanets.bottomsheets.sheets2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlin.reflect.KClass

private inline class ViewType(val type: Int)

internal class SimpleRecyclerViewAdapter<IT : Item<*>>(
    context: Context,
    items: List<IT> = emptyList()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    private val viewHolderFactories = HashMap<ViewType, ViewHolderFactory>()

    var items = items
        set(value) {
            field = value
            value.extractViewHolderFactories()
            notifyDataSetChanged()
        }

    var itemThemer: ItemThemeApplier? = null
    var listenerBinder: ((IT, RecyclerView.ViewHolder) -> Unit)? = null

    init {
        items.extractViewHolderFactories()
    }

    private fun List<IT>.extractViewHolderFactories() {
        viewHolderFactories.clear()

        for (item in this) {
            val viewType = item::class.toViewType()

            if (viewHolderFactories[viewType] == null) {
                viewHolderFactories[viewType] = item
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder = viewHolderFactories[ViewType(viewType)]?.create(inflater, parent)
        return (viewHolder ?: throw RuntimeException("The ViewHolder factory was not found."))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(this[position]) {
            bind(holder)

            if (holder is HasListeners) {
                listenerBinder?.invoke(this, holder)
            }

            itemThemer?.invoke(this, holder)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return this[position]::class.toViewType().type
    }

    operator fun get(position: Int): IT {
        return items[position]
    }

    private fun KClass<out Item<*>>.toViewType(): ViewType {
        return ViewType(this.qualifiedName.hashCode())
    }

}
