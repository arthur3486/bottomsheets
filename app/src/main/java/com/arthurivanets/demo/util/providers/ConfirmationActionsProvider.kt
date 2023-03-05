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

package com.arthurivanets.demo.util.providers

import android.content.Context
import com.arthurivanets.bottomsheets.sheets.model.Option
import com.arthurivanets.demo.R

object ConfirmationActionsProvider {

    object Id {

        const val CONFIRM = 1L
        const val CANCEL = 2L
        const val OTHER = 3L

    }

    fun getGeneralDeletionConfirmationActionOptions(context: Context): List<Option> {
        return ArrayList<Option>().apply {
            // Confirmation Option
            add(
                context.createOption(
                    Id.CONFIRM,
                    R.drawable.ic_outline_delete_outline_24px,
                    context.getString(R.string.action_delete)
                )
            )

            // Cancellation Option
            add(
                context.createOption(
                    Id.CANCEL,
                    R.drawable.ic_outline_cancel_24px,
                    context.getString(R.string.action_cancel)
                )
            )
        }
    }

}