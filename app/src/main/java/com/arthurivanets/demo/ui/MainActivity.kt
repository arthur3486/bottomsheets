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

package com.arthurivanets.demo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arthurivanets.bottomsheets.BaseBottomSheet
import com.arthurivanets.bottomsheets.BottomSheet
import com.arthurivanets.bottomsheets.ktx.showActionPickerBottomSheet
import com.arthurivanets.bottomsheets.ktx.showCustomActionPickerBottomSheet
import com.arthurivanets.bottomsheets.sheets.config.Config
import com.arthurivanets.bottomsheets.sheets.listeners.OnItemSelectedListener
import com.arthurivanets.demo.R
import com.arthurivanets.demo.adapters.persons.PersonItem
import com.arthurivanets.demo.ui.widget.SimpleCustomBottomSheet
import com.arthurivanets.demo.util.misc.shortToast
import com.arthurivanets.demo.util.providers.ActionsProvider
import com.arthurivanets.demo.util.providers.ConfirmationActionsProvider
import com.arthurivanets.demo.util.providers.PersonProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private var bottomSheet : BaseBottomSheet? = null


    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }


    private fun init() {
        customBottomSheetBtn.setOnClickListener {
            showCustomBottomSheet()
        }

        confirmationBottomSheetBtn.setOnClickListener {
            showDeletionConfirmationBottomSheet()
        }

        actionsBottomSheetBtn.setOnClickListener {
            showActionsBottomSheet()
        }

        customActionsBottomSheetBtn.setOnClickListener {
            showPeopleBottomSheet()
        }
    }


    private fun showCustomBottomSheet() {
        dismissBottomSheet()

        bottomSheet = SimpleCustomBottomSheet(this).also(BottomSheet::show)
    }


    private fun showDeletionConfirmationBottomSheet() {
        dismissBottomSheet()

        bottomSheet = showActionPickerBottomSheet(
            options = ConfirmationActionsProvider.getGeneralDeletionConfirmationActionOptions(this),
            config = Config.Builder(this)
                .title(getString(R.string.confirmation_title_item_deletion))
                .build(),
            onItemSelectedListener = OnItemSelectedListener {
                shortToast(it.title.toString())
            }
        )
    }


    private fun showActionsBottomSheet() {
        dismissBottomSheet()

        bottomSheet = showActionPickerBottomSheet(
            options = ActionsProvider.getActionOptions(this),
            onItemSelectedListener = OnItemSelectedListener {
                shortToast(it.title.toString())
            }
        )
    }


    private fun showPeopleBottomSheet() {
        dismissBottomSheet()

        bottomSheet = showCustomActionPickerBottomSheet(
            items = PersonProvider.getPeople().map(::PersonItem),
            onItemSelectedListener = OnItemSelectedListener {
                shortToast(it.itemModel.fullName)
            }
        )
    }


    private fun dismissBottomSheet(animate : Boolean = true) {
        bottomSheet?.dismiss(animate)
        bottomSheet = null
    }


    private fun isBottomSheetExpanded() : Boolean {
        return (bottomSheet?.state == BottomSheet.State.EXPANDED)
    }


    override fun onBackPressed() {
        if(isBottomSheetExpanded()) {
            dismissBottomSheet()
            return
        }

        super.onBackPressed()
    }


}