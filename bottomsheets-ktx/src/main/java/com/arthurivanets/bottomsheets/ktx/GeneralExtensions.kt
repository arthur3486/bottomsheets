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

@file:JvmName("GeneralExtensions")

package com.arthurivanets.bottomsheets.ktx

import androidx.fragment.app.Fragment

/**
 * Verifies if the current [Fragment] is attached to a parent [android.app.Activity].
 *
 * @throws IllegalStateException if the [Fragment] is not attached to a parent [android.app.Activity]
 */
fun Fragment.assertAttachedToActivity() {
    if (activity == null) {
        throw IllegalStateException("The Fragment is not attached to the Activity.")
    }
}

/**
 * Verifies if the current [Fragment] is attached to a [android.content.Context].
 *
 * @throws IllegalStateException if the [Fragment] is not attached to a [android.content.Context]
 */
fun Fragment.assertAttachedToContext() {
    if (context == null) {
        throw IllegalStateException("The Fragment is not attached to the Context.")
    }
}
