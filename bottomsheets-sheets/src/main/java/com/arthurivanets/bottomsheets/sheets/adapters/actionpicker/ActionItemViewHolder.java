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

import android.view.View;
import android.widget.TextView;

import com.arthurivanets.adapster.model.BaseItem;
import com.arthurivanets.bottomsheets.sheets.R;
import com.arthurivanets.bottomsheets.sheets.model.Option;
import com.arthurivanets.bottomsheets.sheets.util.Utils;

public final class ActionItemViewHolder extends BaseItem.ViewHolder<Option> {


    final TextView titleTv;




    public ActionItemViewHolder(View itemView) {
        super(itemView);

        this.titleTv = itemView.findViewById(R.id.title);
    }




    @Override
    public final void bindData(Option option) {
        super.bindData(option);

        // icon-related
        if(option.isValidIconIdSet()) {
            Utils.setDrawableLeft(
                this.titleTv,
                Utils.getColoredDrawable(
                    this.titleTv.getContext(),
                    option.getIconId(),
                    option.getIconColor()
                )
            );
        }

        // title-related
        this.titleTv.setTextColor(option.getTitleColor());
        this.titleTv.setText(Utils.fromHtml(option.getTitle()));
    }




}