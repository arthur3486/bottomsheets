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

package com.arthurivanets.bottomsheets.sheets2.dsl

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import com.arthurivanets.bottomsheets.sheets2.extensions.getCompatDrawable
import com.arthurivanets.bottomsheets.sheets2.extensions.layoutInflater


internal class Icon internal constructor(
    private val drawable : Drawable? = null,
    private val resourceId : Int = 0
) {


    fun getIcon(context : Context) : Drawable {
        return when {
            (drawable != null) -> drawable
            (resourceId != 0) -> (context.getCompatDrawable(resourceId) ?: throw IllegalArgumentException("Not Drawable Resource found for ResourceId($resourceId)"))
            else -> throw IllegalStateException("The Icon is not set.")
        }
    }


}


internal class Text internal constructor(
    private val text : CharSequence? = null,
    private val resourceId : Int = 0,
    private val args : Array<out Any>? = null
) {


    fun getText(context : Context) : CharSequence {
        return when {
            (text != null) -> text
            (resourceId != 0) -> (args?.let { context.getString(resourceId, *it) } ?: context.getString(resourceId))
            else -> ""
        }
    }


}


internal data class AuxiliaryView(
    private val view : View? = null,
    private val resourceId : Int = 0,
    internal val isSticky : Boolean = false
) {


    internal fun getView(context : Context, parent : ViewGroup) : View {
        return when {
            (view != null) -> view
            (resourceId != 0) -> context.layoutInflater.inflate(resourceId, parent, false)
            else -> throw IllegalStateException("The View has not been set.")
        }
    }


}