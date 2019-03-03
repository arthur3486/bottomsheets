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

package com.arthurivanets.bottomsheets.sheets.listeners;

/**
 * Listener used to observe the {@link com.arthurivanets.bottomsheets.BottomSheet}
 * item selection state change events.
 *
 * @param <T> the {@link com.arthurivanets.bottomsheets.BottomSheet} item type
 */
public interface OnItemSelectedListener<T> {

    /**
     * Called when the {@link com.arthurivanets.bottomsheets.BottomSheet} item gets selected.
     *
     * @param item the selected item
     */
    void onItemSelected(T item);

}