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

package com.arthurivanets.bottomsheets;

import android.app.Activity;

import com.arthurivanets.bottomsheets.config.BaseConfig;

import androidx.annotation.NonNull;

/**
 * A base public {@link BottomSheet} implementation to be used by the all the concrete {@link BottomSheet} implementations.
 */
public abstract class BaseBottomSheet extends BottomSheetContainer {

    /**
     * The main constructor used for the initialization of the {@link BottomSheet}.
     *
     * @param hostActivity the activity the content view of which is to be used as a holder of the bottom sheet
     */
    public BaseBottomSheet(@NonNull Activity hostActivity, @NonNull BaseConfig config) {
        super(hostActivity, config);
    }

}
