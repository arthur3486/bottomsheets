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

import com.arthurivanets.demo.R
import com.arthurivanets.demo.model.Person

object PersonProvider {

    fun getPeople(): List<Person> {
        return listOf(
            Person(
                id = 1L,
                username = "danicajohns",
                fullName = "Danica Johns",
                imageResourceId = R.drawable.ic_woman_1
            ),
            Person(
                id = 2L,
                username = "harryaguilar",
                fullName = "Harry Aguilar",
                imageResourceId = R.drawable.ic_man_2
            ),
            Person(
                id = 3L,
                username = "houstonroth",
                fullName = "Houston Roth",
                imageResourceId = R.drawable.ic_man_3
            ),
            Person(
                id = 4L,
                username = "george41",
                fullName = "George Evans",
                imageResourceId = R.drawable.ic_man_4
            )
        )
    }

}
