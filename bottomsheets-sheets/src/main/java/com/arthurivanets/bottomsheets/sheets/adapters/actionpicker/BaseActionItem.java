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

package com.arthurivanets.bottomsheets.sheets.adapters.actionpicker;

import com.arthurivanets.adapster.listeners.OnItemClickListener;
import com.arthurivanets.adapster.model.BaseItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * A base class to be used for the custom concrete implementations of the {@link com.arthurivanets.bottomsheets.BottomSheet} action items.
 */
public abstract class BaseActionItem<IM, VH extends BaseItem.ViewHolder<IM>, IR extends ActionPickerItemResources> extends BaseItem<IM, VH, IR> {




    public BaseActionItem(IM itemModel) {
        super(itemModel);
    }




    /**
     * Registers the Action Item Click Listener.
     *
     * @param viewHolder the item view holder
     * @param onItemClickListener the actual listener
     */
    public abstract void setOnItemClickListener(@NonNull VH viewHolder, @Nullable OnItemClickListener<BaseActionItem<IM, VH, IR>> onItemClickListener);




}
