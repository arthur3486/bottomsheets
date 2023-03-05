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

import com.arthurivanets.adapster.model.BaseItem;
import com.arthurivanets.adapster.util.Preconditions;
import com.arthurivanets.bottomsheets.sheets.adapters.actionpicker.ActionPickerItemResources;
import com.arthurivanets.bottomsheets.sheets.adapters.actionpicker.BaseActionItem;
import com.arthurivanets.bottomsheets.sheets.config.ActionPickerConfig;
import com.arthurivanets.bottomsheets.sheets.config.Config;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * A concrete implementation of the {@link com.arthurivanets.bottomsheets.BottomSheet} which provides
 * the necessary underlying functionality to create a Custom {@link com.arthurivanets.bottomsheets.BottomSheet}-based
 * Action Picker, Action Items of which can be customized to the fullest extent.
 */
@SuppressLint("ViewConstructor")
public final class CustomActionPickerBottomSheet<IT extends BaseActionItem> extends BaseActionPickerBottomSheet<IT, BaseItem.ViewHolder<?>, ActionPickerItemResources> {

    /**
     * Creates a new instance of the {@link CustomActionPickerBottomSheet} for the specified custom items.
     *
     * @param fragment the host fragment
     * @param items    the custom action items
     * @return the created {@link CustomActionPickerBottomSheet}
     */
    @NonNull
    public static <IT extends BaseActionItem> CustomActionPickerBottomSheet<IT> init(@NonNull Fragment fragment,
                                                                                     @NonNull List<IT> items) {
        Preconditions.nonNull(fragment);
        Preconditions.nonNull(fragment.getActivity());
        Preconditions.nonNull(items);

        return init(fragment.getActivity(), items);
    }

    /**
     * Creates a new instance of the {@link CustomActionPickerBottomSheet} for the specified custom items.
     *
     * @param activity the host activity
     * @param items    the custom action items
     * @return the created {@link CustomActionPickerBottomSheet}
     */
    @NonNull
    public static <IT extends BaseActionItem> CustomActionPickerBottomSheet<IT> init(@NonNull Activity activity,
                                                                                     @NonNull List<IT> items) {
        Preconditions.nonNull(activity);
        Preconditions.nonNull(items);

        return init(
            activity,
            items,
            new Config.Builder(activity).build()
        );
    }

    /**
     * Creates a new instance of the {@link CustomActionPickerBottomSheet} for the specified custom items.
     *
     * @param fragment the host fragment
     * @param items    the custom action items
     * @param config   the sheet configuration
     * @return the created {@link CustomActionPickerBottomSheet}
     */
    @NonNull
    public static <IT extends BaseActionItem> CustomActionPickerBottomSheet<IT> init(@NonNull Fragment fragment,
                                                                                     @NonNull List<IT> items,
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
     * Creates a new instance of the {@link CustomActionPickerBottomSheet} for the specified custom items.
     *
     * @param activity the host activity
     * @param items    the custom action items
     * @param config   the sheet configuration
     * @return the created {@link CustomActionPickerBottomSheet}
     */
    @NonNull
    public static <IT extends BaseActionItem> CustomActionPickerBottomSheet<IT> init(@NonNull Activity activity,
                                                                                     @NonNull List<IT> items,
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
     * Creates a new instance of the {@link CustomActionPickerBottomSheet} for the specified custom items.
     *
     * @param fragment      the host fragment
     * @param items         the custom action items
     * @param itemResources the custom action item resources
     * @param config        the sheet configuration
     * @return the created {@link CustomActionPickerBottomSheet}
     */
    @NonNull
    public static <IT extends BaseActionItem> CustomActionPickerBottomSheet<IT> init(@NonNull Fragment fragment,
                                                                                     @NonNull List<IT> items,
                                                                                     @NonNull ActionPickerItemResources itemResources,
                                                                                     @NonNull ActionPickerConfig config) {
        Preconditions.nonNull(fragment);
        Preconditions.nonNull(fragment.getActivity());
        Preconditions.nonNull(items);
        Preconditions.nonNull(itemResources);
        Preconditions.nonNull(config);

        return new CustomActionPickerBottomSheet<>(
            fragment.getActivity(),
            items,
            itemResources,
            config
        );
    }

    /**
     * Creates a new instance of the {@link CustomActionPickerBottomSheet} for the specified custom items.
     *
     * @param activity      the host activity
     * @param items         the custom action items
     * @param itemResources the custom action item resources
     * @param config        the sheet configuration
     * @return the created {@link CustomActionPickerBottomSheet}
     */
    @NonNull
    public static <IT extends BaseActionItem> CustomActionPickerBottomSheet<IT> init(@NonNull Activity activity,
                                                                                     @NonNull List<IT> items,
                                                                                     @NonNull ActionPickerItemResources itemResources,
                                                                                     @NonNull ActionPickerConfig config) {
        Preconditions.nonNull(activity);
        Preconditions.nonNull(items);
        Preconditions.nonNull(itemResources);
        Preconditions.nonNull(config);

        return new CustomActionPickerBottomSheet<>(
            activity,
            items,
            itemResources,
            config
        );
    }

    protected CustomActionPickerBottomSheet(@NonNull Activity context,
                                            @NonNull List<IT> items,
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