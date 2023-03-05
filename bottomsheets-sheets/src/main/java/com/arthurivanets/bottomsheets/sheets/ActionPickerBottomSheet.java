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

package com.arthurivanets.bottomsheets.sheets;

import android.annotation.SuppressLint;
import android.app.Activity;

import com.arthurivanets.adapster.util.Preconditions;
import com.arthurivanets.bottomsheets.sheets.adapters.actionpicker.ActionItem;
import com.arthurivanets.bottomsheets.sheets.adapters.actionpicker.ActionItemViewHolder;
import com.arthurivanets.bottomsheets.sheets.adapters.actionpicker.ActionPickerItemResources;
import com.arthurivanets.bottomsheets.sheets.config.ActionPickerConfig;
import com.arthurivanets.bottomsheets.sheets.config.Config;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * A concrete implementation of the {@link com.arthurivanets.bottomsheets.BottomSheet} which provides
 * the necessary underlying functionality to create a convenient {@link com.arthurivanets.bottomsheets.BottomSheet}-based
 * Action Picker (uses {@link ActionItem}s to represent the picker options).
 */
@SuppressLint("ViewConstructor")
public final class ActionPickerBottomSheet extends BaseActionPickerBottomSheet<ActionItem, ActionItemViewHolder, ActionPickerItemResources> {

    /**
     * Creates a new instance of the {@link ActionPickerBottomSheet} for the specified items.
     *
     * @param fragment the host fragment
     * @param items    the action items
     * @return the created {@link ActionPickerBottomSheet}
     */
    @NonNull
    public static ActionPickerBottomSheet init(@NonNull Fragment fragment,
                                               @NonNull List<ActionItem> items) {
        Preconditions.nonNull(fragment);
        Preconditions.nonNull(fragment.getActivity());
        Preconditions.nonNull(items);

        return init(fragment.getActivity(), items);
    }

    /**
     * Creates a new instance of the {@link ActionPickerBottomSheet} for the specified items.
     *
     * @param activity the host activity
     * @param items    the action items
     * @return the created {@link ActionPickerBottomSheet}
     */
    @NonNull
    public static ActionPickerBottomSheet init(@NonNull Activity activity,
                                               @NonNull List<ActionItem> items) {
        Preconditions.nonNull(activity);
        Preconditions.nonNull(items);

        return init(
            activity,
            items,
            new Config.Builder(activity).build()
        );
    }

    /**
     * Creates a new instance of the {@link ActionPickerBottomSheet} for the specified items.
     *
     * @param fragment the host fragment
     * @param items    the action items
     * @param config   the sheet configuration
     * @return the created {@link ActionPickerBottomSheet}
     */
    @NonNull
    public static ActionPickerBottomSheet init(@NonNull Fragment fragment,
                                               @NonNull List<ActionItem> items,
                                               @NonNull ActionPickerConfig config) {
        Preconditions.nonNull(fragment);
        Preconditions.nonNull(fragment.getActivity());
        Preconditions.nonNull(items);
        Preconditions.nonNull(config);

        return init(
            fragment.getActivity(),
            items,
            new ActionPickerItemResources(),
            config
        );
    }

    /**
     * Creates a new instance of the {@link ActionPickerBottomSheet} for the specified items.
     *
     * @param activity the host activity
     * @param items    the action items
     * @param config   the sheet configuration
     * @return the created {@link ActionPickerBottomSheet}
     */
    @NonNull
    public static ActionPickerBottomSheet init(@NonNull Activity activity,
                                               @NonNull List<ActionItem> items,
                                               @NonNull ActionPickerConfig config) {
        Preconditions.nonNull(activity);
        Preconditions.nonNull(items);
        Preconditions.nonNull(config);

        return init(
            activity,
            items,
            new ActionPickerItemResources(),
            config
        );
    }

    /**
     * Creates a new instance of the {@link ActionPickerBottomSheet} for the specified items.
     *
     * @param fragment      the host fragment
     * @param items         the action items
     * @param itemResources the action item resources
     * @param config        the sheet configuration
     * @return the created {@link ActionPickerBottomSheet}
     */
    @NonNull
    public static ActionPickerBottomSheet init(@NonNull Fragment fragment,
                                               @NonNull List<ActionItem> items,
                                               @NonNull ActionPickerItemResources itemResources,
                                               @NonNull ActionPickerConfig config) {
        Preconditions.nonNull(fragment);
        Preconditions.nonNull(fragment.getActivity());
        Preconditions.nonNull(items);
        Preconditions.nonNull(itemResources);
        Preconditions.nonNull(config);

        return new ActionPickerBottomSheet(
            fragment.getActivity(),
            items,
            itemResources,
            config
        );
    }

    /**
     * Creates a new instance of the {@link ActionPickerBottomSheet} for the specified items.
     *
     * @param activity      the host activity
     * @param items         the action items
     * @param itemResources the action item resources
     * @param config        the sheet configuration
     * @return the created {@link ActionPickerBottomSheet}
     */
    @NonNull
    public static ActionPickerBottomSheet init(@NonNull Activity activity,
                                               @NonNull List<ActionItem> items,
                                               @NonNull ActionPickerItemResources itemResources,
                                               @NonNull ActionPickerConfig config) {
        Preconditions.nonNull(activity);
        Preconditions.nonNull(items);
        Preconditions.nonNull(itemResources);
        Preconditions.nonNull(config);

        return new ActionPickerBottomSheet(
            activity,
            items,
            itemResources,
            config
        );
    }

    protected ActionPickerBottomSheet(@NonNull Activity context,
                                      @NonNull List<ActionItem> items,
                                      @NonNull ActionPickerItemResources itemResources,
                                      @NonNull ActionPickerConfig config) {
        super(
            context,
            items,
            itemResources,
            config
        );
    }

    @Override
    protected final int getContentViewLayoutResourceId() {
        return R.layout.bottom_sheet_base_action_picker;
    }

}
