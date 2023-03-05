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

package com.arthurivanets.bottomsheets.sheets2

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.arthurivanets.bottomsheets.BaseBottomSheet
import com.arthurivanets.bottomsheets.sheets2.adapter.Item
import com.arthurivanets.bottomsheets.sheets2.adapter.SimpleRecyclerViewAdapter
import com.arthurivanets.bottomsheets.sheets2.dsl.ActionPickerConfig
import com.arthurivanets.bottomsheets.sheets2.extensions.layoutInflater
import kotlinx.android.synthetic.main.view_action_picker_bottom_sheet_content.view.*

@SuppressLint("ViewConstructor")
internal class ActionPickerBottomSheet internal constructor(
    context: Activity,
    private val pickerConfig: ActionPickerConfig
) : BaseBottomSheet(context, pickerConfig) {


    init {
        initHeader()
        initRecyclerView()
    }

    override fun onCreateSheetContentView(context: Context): View {
        return context.layoutInflater.inflate(
            R.layout.view_action_picker_bottom_sheet_content,
            this,
            false
        )
    }

    private fun initHeader() {
        pickerConfig.headerView?.let { headerView ->
            headerContainer.addView(
                headerView.getView(context, headerContainer),
                LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT
                )
            )
        }
    }

    private fun initRecyclerView() = with(recyclerView) {
        layoutManager = LinearLayoutManager(context)
        adapter = initItemAdapter()
    }

    private fun initItemAdapter(): SimpleRecyclerViewAdapter<Item<*>> {
        return SimpleRecyclerViewAdapter(
            context = context,
            items = pickerConfig.items
        ).apply {
            itemThemer = pickerConfig.itemThemeApplier
            listenerBinder = { item, holder ->
                when (item.model) {
                    is Action -> holder.itemView.setOnClickListener {
                        handleActionClick(item.model as Action)
                    }
                }
            }
        }
    }

    private fun handleActionClick(action: Action) {
        if (pickerConfig.isDismissableOnItemClick) {
            dismiss()
        }

        pickerConfig.onItemClickListener?.invoke(action)
    }

}