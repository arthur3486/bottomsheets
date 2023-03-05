/*
 * Copyright 2017 Arthur Ivanets, arthur.ivanets.work@gmail.com
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

package com.arthurivanets.demo.adapters.persons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arthurivanets.adapster.Adapter
import com.arthurivanets.adapster.ktx.setOnItemClickListener
import com.arthurivanets.adapster.listeners.OnItemClickListener
import com.arthurivanets.adapster.markers.ItemResources
import com.arthurivanets.adapster.model.Item
import com.arthurivanets.adapster.model.markers.Trackable
import com.arthurivanets.bottomsheets.sheets.adapters.actionpicker.ActionPickerItemResources
import com.arthurivanets.bottomsheets.sheets.adapters.actionpicker.BaseActionItem
import com.arthurivanets.demo.R
import com.arthurivanets.demo.model.Person

class PersonItem(itemModel: Person) : BaseActionItem<
    Person,
    PersonItemViewHolder,
    ActionPickerItemResources
    >(itemModel), Trackable<Long> {

    override fun init(
        adapter: Adapter<out Item<RecyclerView.ViewHolder, ItemResources>>?,
        parent: ViewGroup,
        inflater: LayoutInflater,
        resources: ActionPickerItemResources?
    ): PersonItemViewHolder {
        return PersonItemViewHolder(
            inflater.inflate(
                layout,
                parent,
                false
            )
        )
    }

    override fun setOnItemClickListener(
        viewHolder: PersonItemViewHolder,
        onItemClickListener: OnItemClickListener<BaseActionItem<Person, PersonItemViewHolder, ActionPickerItemResources>>?
    ) {
        viewHolder.itemView.setOnItemClickListener(this, 0, onItemClickListener)
    }

    override fun getLayout(): Int {
        return R.layout.item_person
    }

    override fun getTrackKey(): Long {
        return itemModel.id
    }

}
