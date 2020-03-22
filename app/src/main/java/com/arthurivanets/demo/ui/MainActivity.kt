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
import com.arthurivanets.bottomsheets.BottomSheet
import com.arthurivanets.bottomsheets.ktx.actionPickerConfig
import com.arthurivanets.bottomsheets.ktx.showActionPickerBottomSheet
import com.arthurivanets.bottomsheets.ktx.showCustomActionPickerBottomSheet
import com.arthurivanets.bottomsheets.sheets.listeners.OnItemSelectedListener
import com.arthurivanets.bottomsheets.sheets2.ActionId
import com.arthurivanets.bottomsheets.sheets2.dsl.actionPickerBottomSheet
import com.arthurivanets.bottomsheets.sheets2.dsl.customActionPickerBottomSheet
import com.arthurivanets.demo.R
import com.arthurivanets.demo.adapters.persons.PersonItem
import com.arthurivanets.demo.adapters.persons.PersonItem2
import com.arthurivanets.demo.model.PersonAction
import com.arthurivanets.demo.ui.widget.SimpleCustomBottomSheet
import com.arthurivanets.demo.util.misc.shortToast
import com.arthurivanets.demo.util.providers.ActionsProvider
import com.arthurivanets.demo.util.providers.ConfirmationActionsProvider
import com.arthurivanets.demo.util.providers.PersonProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private var bottomSheet : BottomSheet? = null


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

        actionsBottomSheet2Btn.setOnClickListener {
            showActionsBottomSheet2()
        }

        customActionsBottomSheet2Btn.setOnClickListener {
            showPeopleBottomSheet2()
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
            config = actionPickerConfig {
                title(getString(R.string.confirmation_title_item_deletion))
            },
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


    private fun showActionsBottomSheet2() {
        bottomSheet = actionPickerBottomSheet {
            action(
                id = ActionId(1),
                iconId = R.drawable.ic_outline_local_printshop_24px,
                titleId = R.string.action_print
            )
            action(
                id = ActionId(2),
                iconId = R.drawable.ic_outline_save_24px,
                titleId = R.string.action_save
            )
            action(
                id = ActionId(3),
                iconId = R.drawable.ic_outline_offline_bolt_24px,
                titleId = R.string.action_recharge
            )
            action(
                id = ActionId(4),
                iconId = R.drawable.ic_outline_thumb_up_24px,
                titleId = R.string.action_like
            )
            action(
                id = ActionId(5),
                iconId = R.drawable.ic_outline_thumb_down_24px,
                titleId = R.string.action_dislike
            )
            onItemClickListener = { shortToast("ActionID: ${it.id}") }
        }.also(BottomSheet::show)
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


    private fun showPeopleBottomSheet2() {
        bottomSheet = customActionPickerBottomSheet {
            PersonProvider.getPeople()
                .asSequence()
                .map(::PersonAction)
                .map(::PersonItem2)
                .forEach { item(it) }

            onItemClickListener = { shortToast("Person Clicked: [id: ${it.id}]") }
        }.also(BottomSheet::show)
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