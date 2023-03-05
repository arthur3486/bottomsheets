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

package com.arthurivanets.bottomsheets.sheets.adapters.actionpicker;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.arthurivanets.adapster.Adapter;
import com.arthurivanets.adapster.listeners.ItemClickListener;
import com.arthurivanets.adapster.listeners.OnItemClickListener;
import com.arthurivanets.adapster.model.Item;
import com.arthurivanets.adapster.model.markers.Trackable;
import com.arthurivanets.bottomsheets.sheets.R;
import com.arthurivanets.bottomsheets.sheets.model.Option;
import com.arthurivanets.bottomsheets.util.Preconditions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * An item used to represent a single option in the {@link com.arthurivanets.bottomsheets.sheets.ActionPickerBottomSheet}.
 */
public final class ActionItem extends BaseActionItem<Option, ActionItemViewHolder, ActionPickerItemResources> implements Trackable<Long> {

    public ActionItem(Option itemModel) {
        super(itemModel);
    }

    @Override
    public final ActionItemViewHolder init(@Nullable Adapter<? extends Item> adapter,
                                           @NonNull ViewGroup parent,
                                           @NonNull LayoutInflater inflater,
                                           @Nullable ActionPickerItemResources resources) {
        return new ActionItemViewHolder(inflater.inflate(
            getLayout(),
            parent,
            false
        ));
    }

    @Override
    public final void setOnItemClickListener(@NonNull ActionItemViewHolder viewHolder, @Nullable OnItemClickListener<BaseActionItem<Option, ActionItemViewHolder, ActionPickerItemResources>> onItemClickListener) {
        Preconditions.nonNull(viewHolder);

        viewHolder.itemView.setOnClickListener(new ItemClickListener<>(this, 0, onItemClickListener));
    }

    @Override
    public final int getLayout() {
        return R.layout.item_bottom_sheet_action;
    }

    @Override
    public final Long getTrackKey() {
        return getItemModel().getId();
    }

}
