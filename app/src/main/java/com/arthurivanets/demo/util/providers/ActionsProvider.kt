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

package com.arthurivanets.demo.util.providers

import android.content.Context
import com.arthurivanets.bottomsheets.sheets.model.Option
import com.arthurivanets.demo.R

object ActionsProvider {

    object Id {

        const val PRINT = 1L
        const val SAVE = 2L
        const val RECHARGE = 3L
        const val LIKE = 4L
        const val DISLIKE = 5L

    }

    fun getActionOptions(context: Context): List<Option> {
        return ArrayList<Option>().apply {
            // Printing Option
            add(
                context.createOption(
                    Id.PRINT,
                    R.drawable.ic_outline_local_printshop_24px,
                    context.getString(R.string.action_print)
                )
            )

            // Saving Option
            add(
                context.createOption(
                    Id.SAVE,
                    R.drawable.ic_outline_save_24px,
                    context.getString(R.string.action_save)
                )
            )

            // Recharging Option
            add(
                context.createOption(
                    Id.RECHARGE,
                    R.drawable.ic_outline_offline_bolt_24px,
                    context.getString(R.string.action_recharge)
                )
            )

            // Liking Option
            add(
                context.createOption(
                    Id.LIKE,
                    R.drawable.ic_outline_thumb_up_24px,
                    context.getString(R.string.action_like)
                )
            )

            // Disliking Option
            add(
                context.createOption(
                    Id.DISLIKE,
                    R.drawable.ic_outline_thumb_down_24px,
                    context.getString(R.string.action_dislike)
                )
            )
        }
    }

}
