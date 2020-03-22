/*
 * Copyright 2019 Arthur Ivanets, arthur.ivanets.l@gmail.com
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

package com.arthurivanets.bottomsheets.sheets2

import com.arthurivanets.bottomsheets.sheets2.dsl.Icon
import com.arthurivanets.bottomsheets.sheets2.dsl.Text


inline class ActionId(val id : Long)


/**
 * A base interface that must be implemented by each Action Item Model of
 * the Action Picker Bottom Sheet, in order to ensure the correct handling of the action item click events.
 */
interface Action {

    /**
     * The id of the action.
     */
    val id : Long

}


internal data class SimpleAction(
    override val id : Long,
    internal val icon : Icon? = null,
    internal val title : Text
) : Action