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

package com.arthurivanets.demo.ui.widget

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.arthurivanets.bottomsheets.BaseBottomSheet
import com.arthurivanets.bottomsheets.config.BaseConfig
import com.arthurivanets.bottomsheets.config.Config
import com.arthurivanets.demo.R

class SimpleCustomBottomSheet(
    hostActivity: Activity,
    config: BaseConfig = Config.Builder(hostActivity).build()
) : BaseBottomSheet(hostActivity, config) {

    override fun onCreateSheetContentView(context: Context): View {
        return LayoutInflater.from(context).inflate(
            R.layout.view_simple_custom_bottom_sheet,
            this,
            false
        )
    }

}
