package com.arthurivanets.demo.adapters.persons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arthurivanets.bottomsheets.sheets2.adapter.AbstractItem
import com.arthurivanets.bottomsheets.sheets2.adapter.HasListeners
import com.arthurivanets.demo.R
import com.arthurivanets.demo.model.Person
import com.arthurivanets.demo.model.PersonAction
import com.arthurivanets.demo.util.glide.PersonImageTransformator
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

internal class PersonItem2(action : PersonAction) : AbstractItem<PersonAction, PersonItem2.ViewHolder>(action) {


    override fun create(inflater : LayoutInflater, parent : ViewGroup) : RecyclerView.ViewHolder {
        return ViewHolder(
            inflater.inflate(
                R.layout.item_person,
                parent,
                false
            )
        )
    }


    override fun performBinding(viewHolder : ViewHolder) {
        viewHolder.bindData(model.person)
    }


    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), HasListeners {

        private val imageIv = itemView.findViewById<ImageView>(R.id.imageIv)
        private val fullNameTv = itemView.findViewById<TextView>(R.id.fullNameTv)
        private val usernameTv = itemView.findViewById<TextView>(R.id.usernameTv)

        internal fun bindData(person : Person) {
            fullNameTv.text = person.fullName
            usernameTv.text = usernameTv.context.getString(R.string.person_item_username_template, person.username)

            loadImage(person.imageResourceId)
        }

        private fun loadImage(imageResourceId : Int) {
            val imageSize = imageIv.context.resources.getDimensionPixelSize(R.dimen.person_item_image_size)

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


}