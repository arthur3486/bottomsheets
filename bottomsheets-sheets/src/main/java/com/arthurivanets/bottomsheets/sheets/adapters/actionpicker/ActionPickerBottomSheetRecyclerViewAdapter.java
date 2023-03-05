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

import android.content.Context;

import com.arthurivanets.adapster.listeners.OnItemClickListener;
import com.arthurivanets.adapster.markers.ItemResources;
import com.arthurivanets.adapster.model.BaseItem;
import com.arthurivanets.adapster.recyclerview.TrackableRecyclerViewAdapter;
import com.arthurivanets.bottomsheets.util.Preconditions;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class ActionPickerBottomSheetRecyclerViewAdapter<
    IT extends BaseActionItem,
    VH extends BaseItem.ViewHolder<?>,
    IR extends ActionPickerItemResources
    > extends TrackableRecyclerViewAdapter<Long, IT, VH> {

    private final ItemResources mResources;

    private OnItemClickListener<IT> mOnItemClickListener;

    public ActionPickerBottomSheetRecyclerViewAdapter(@NonNull Context context,
                                                      @NonNull List<IT> items,
                                                      @NonNull IR resources) {
        super(context, items);

        mResources = Preconditions.checkNonNull(resources);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected final void assignListeners(@NonNull VH holder,
                                         int position,
                                         @NonNull IT item) {
        super.assignListeners(holder, position, item);

        item.setOnItemClickListener(holder, mOnItemClickListener);
    }

    public final void setOnItemClickListener(@Nullable OnItemClickListener<IT> onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public final ItemResources getResources() {
        return mResources;
    }

}
