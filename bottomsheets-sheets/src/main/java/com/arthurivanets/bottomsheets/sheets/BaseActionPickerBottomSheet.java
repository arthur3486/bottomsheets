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

package com.arthurivanets.bottomsheets.sheets;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.arthurivanets.adapster.model.BaseItem;
import com.arthurivanets.bottomsheets.BaseBottomSheet;
import com.arthurivanets.bottomsheets.sheets.adapters.actionpicker.ActionPickerBottomSheetRecyclerViewAdapter;
import com.arthurivanets.bottomsheets.sheets.adapters.actionpicker.ActionPickerItemResources;
import com.arthurivanets.bottomsheets.sheets.adapters.actionpicker.BaseActionItem;
import com.arthurivanets.bottomsheets.sheets.config.ActionPickerConfig;
import com.arthurivanets.bottomsheets.sheets.listeners.OnItemSelectedListener;
import com.arthurivanets.bottomsheets.sheets.util.Utils;
import com.arthurivanets.bottomsheets.util.Preconditions;

import java.util.List;

import androidx.annotation.CallSuper;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.arthurivanets.bottomsheets.sheets.util.Utils.disableAnimations;

/**
 * <br>
 * A base class to be used for the concrete implementation of the Action Picker Bottom Sheets.
 * <br>
 * Provides the general skeleton for the final {@link com.arthurivanets.bottomsheets.BottomSheet} implementation.
 * <br>
 */
abstract class BaseActionPickerBottomSheet<
    IT extends BaseActionItem,
    VH extends BaseItem.ViewHolder<?>,
    IR extends ActionPickerItemResources
    > extends BaseBottomSheet {

    protected final ActionPickerConfig mConfig;

    private List<IT> mItems;

    private IR mItemResources;

    private boolean mShouldDismissOnItemSelection;

    private OnItemSelectedListener<IT> mOnItemSelectedListener;

    protected BaseActionPickerBottomSheet(@NonNull Activity context,
                                          @NonNull List<IT> items,
                                          @NonNull IR itemResources,
                                          @NonNull ActionPickerConfig config) {
        super(context, config);

        mItems = Preconditions.checkNonNull(items);
        mConfig = Preconditions.checkNonNull(config);
        mItemResources = Preconditions.checkNonNull(itemResources);
        mShouldDismissOnItemSelection = true;

        onSheetContentViewCreated();
    }

    @NonNull
    @Override
    protected final View onCreateSheetContentView(@NonNull Context context) {
        return LayoutInflater.from(context).inflate(
            getContentViewLayoutResourceId(),
            this,
            false
        );
    }

    @CallSuper
    protected void onSheetContentViewCreated() {
        initTitleView();
        initRecyclerView();
    }

    private void initTitleView() {
        final TextView titleTv = findViewById(R.id.title);
        titleTv.setTextColor(mConfig.getTitleTextColor());
        titleTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, mConfig.getTitleTextSize());
        titleTv.setText(Utils.fromHtml(mConfig.getTitle()));
        titleTv.setVisibility(!TextUtils.isEmpty(mConfig.getTitle()) ? View.VISIBLE : View.GONE);
    }

    private void initRecyclerView() {
        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        disableAnimations(recyclerView);
        recyclerView.setLayoutManager(initLayoutManager(getContext()));
        recyclerView.setAdapter(initAdapter(getContext()));
    }

    private RecyclerView.LayoutManager initLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }

    private ActionPickerBottomSheetRecyclerViewAdapter<IT, VH, IR> initAdapter(Context context) {
        final ActionPickerBottomSheetRecyclerViewAdapter<IT, VH, IR> adapter = new ActionPickerBottomSheetRecyclerViewAdapter<>(
            context,
            mItems,
            mItemResources
        );
        adapter.setOnItemClickListener((view, item, position) -> onActionItemClicked(item));

        return adapter;
    }

    private void onActionItemClicked(IT item) {
        if (mShouldDismissOnItemSelection) {
            dismiss();
        }

        reportItemSelected(item);
    }

    private void reportItemSelected(IT item) {
        if (mOnItemSelectedListener != null) {
            mOnItemSelectedListener.onItemSelected(item);
        }
    }

    /**
     * Used to retrieve the appropriate content view layout resource id to be inflated
     * and used by the final {@link com.arthurivanets.bottomsheets.BottomSheet}.
     *
     * @return a valid layout resource id of a content view.
     */
    @LayoutRes
    protected abstract int getContentViewLayoutResourceId();

    /**
     * Registers the action item selection listener, to be invoked when the selectable action item is clicked (selected).
     *
     * @param onItemSelectedListener the listener
     */
    public final void setOnItemSelectedListener(@Nullable OnItemSelectedListener<IT> onItemSelectedListener) {
        mOnItemSelectedListener = onItemSelectedListener;
    }

    /**
     * Specifies the exact behavior of the {@link com.arthurivanets.bottomsheets.BottomSheet} in cases
     * when the action item is selected.
     *
     * @param shouldDismissOnItemSelection whether to dismiss the {@link com.arthurivanets.bottomsheets.BottomSheet} when action item is selected or not
     */
    public final void setDismissOnItemSelection(boolean shouldDismissOnItemSelection) {
        mShouldDismissOnItemSelection = shouldDismissOnItemSelection;
    }

}
