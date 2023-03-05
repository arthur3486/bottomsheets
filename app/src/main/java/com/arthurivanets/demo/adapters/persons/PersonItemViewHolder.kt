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

package com.arthurivanets.demo.adapters.persons

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.arthurivanets.adapster.model.BaseItem
import com.arthurivanets.demo.R
import com.arthurivanets.demo.model.Person
import com.arthurivanets.demo.util.glide.PersonImageTransformator
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class PersonItemViewHolder(itemView: View) : BaseItem.ViewHolder<Person>(itemView) {

    val imageIv = itemView.findViewById<ImageView>(R.id.imageIv)
    val fullNameTv = itemView.findViewById<TextView>(R.id.fullNameTv)
    val usernameTv = itemView.findViewById<TextView>(R.id.usernameTv)

    override fun bindData(data: Person?) {
        super.bindData(data)

        data?.let(::handleData)
    }

    private fun handleData(data: Person) {
        fullNameTv.text = data.fullName
        usernameTv.text =
            usernameTv.context.getString(R.string.person_item_username_template, data.username)

        loadImage(data.imageResourceId)
    }

    private fun loadImage(imageResourceId: Int) {
        val imageSize =
            imageIv.context.resources.getDimensionPixelSize(R.dimen.person_item_image_size)

        Glide.with(imageIv)
            .load(imageResourceId)
            .override(imageSize, imageSize)
            .apply(
                RequestOptions().apply {
                    transform(PersonImageTransformator())
                }
            )
            .into(imageIv)
    }

}