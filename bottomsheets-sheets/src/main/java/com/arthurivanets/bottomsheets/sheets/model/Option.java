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

package com.arthurivanets.bottomsheets.sheets.model;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.arthurivanets.bottomsheets.util.Preconditions;

import java.io.Serializable;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * A standard option info holder for the {@link com.arthurivanets.bottomsheets.sheets.ActionPickerBottomSheet}.
 */
public final class Option implements Parcelable {


    private int iconId;

    private int iconColor;
    private int titleColor;
    private int descriptionColor;

    private long id;

    private CharSequence title;
    private CharSequence description;

    private Object tag;




    public Option() {
        this.iconId = 0;
        this.iconColor = Color.BLACK;
        this.titleColor = Color.BLACK;
        this.descriptionColor = Color.BLACK;
        this.id = -1L;
        this.title = "";
        this.description = "";
        this.tag = null;
    }




    private Option(Parcel in) {
        this.iconId = in.readInt();
        this.iconColor = in.readInt();
        this.titleColor = in.readInt();
        this.descriptionColor = in.readInt();
        this.id = in.readLong();
        this.title = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
        this.description = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
        this.tag = in.readSerializable();
    }




    @NonNull
    public final Option setId(long id) {
        this.id = id;
        return this;
    }




    public final long getId() {
        return this.id;
    }




    @NonNull
    public final Option setIconId(@DrawableRes int iconId) {
        this.iconId = iconId;
        return this;
    }




    @DrawableRes
    public final int getIconId() {
        return this.iconId;
    }




    public final boolean isValidIconIdSet() {
        return (this.iconId != 0);
    }




    @NonNull
    public final Option setIconColor(@ColorInt int iconColor) {
        this.iconColor = iconColor;
        return this;
    }




    @ColorInt
    public final int getIconColor() {
        return this.iconColor;
    }




    @NonNull
    public final Option setTitleColor(@ColorInt int titleColor) {
        this.titleColor = titleColor;
        return this;
    }




    @ColorInt
    public final int getTitleColor() {
        return this.titleColor;
    }




    @NonNull
    public final Option setDescriptionColor(@ColorInt int descriptionColor) {
        this.descriptionColor = descriptionColor;
        return this;
    }




    @ColorInt
    public final int getDescriptionColor() {
        return this.descriptionColor;
    }




    @NonNull
    public final Option setTitle(@NonNull CharSequence title) {
        this.title = Preconditions.checkNonNull(title);
        return this;
    }




    @NonNull
    public final CharSequence getTitle() {
        return this.title;
    }




    @NonNull
    public final Option setDescription(@NonNull CharSequence description) {
        this.description = Preconditions.checkNonNull(description);
        return this;
    }




    @NonNull
    public final CharSequence getDescription() {
        return this.description;
    }




    @NonNull
    public final Option setTag(@Nullable Object tag) {
        this.tag = tag;
        return this;
    }




    @Nullable
    public final Object getTag() {
        return this.tag;
    }




    @SuppressWarnings("unchecked")
    @Nullable
    public final <T> T getTagAs() {
        return ((T) this.tag);
    }




    public final boolean isTagSet() {
        return (this.tag != null);
    }




    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 17;
        result = ((result * prime) + this.iconId);
        result = ((result * prime) + this.iconColor);
        result = ((result * prime) + this.titleColor);
        result = ((result * prime) + this.descriptionColor);
        result = ((result * prime) + Long.valueOf(this.id).hashCode());
        result = ((result * prime) + ((this.title != null) ? this.title.hashCode() : 0));
        result = ((result * prime) + ((this.description != null) ? this.description.hashCode() : 0));
        result = ((result * prime) + ((this.tag != null) ? this.tag.hashCode() : 0));

        return result;
    }




    @Override
    public final boolean equals(@Nullable Object obj) {
        return ((obj instanceof Option) && (obj.hashCode() == hashCode()));
    }




    @Override
    public final int describeContents() {
        return 0;
    }




    @Override
    public final void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.iconId);
        dest.writeInt(this.iconColor);
        dest.writeInt(this.titleColor);
        dest.writeInt(this.descriptionColor);
        dest.writeLong(this.id);
        TextUtils.writeToParcel(this.title, dest, flags);
        TextUtils.writeToParcel(this.description, dest, flags);

        if(this.tag instanceof Serializable) {
            dest.writeSerializable((Serializable) this.tag);
        }
    }




    public static final Creator<Option> CREATOR = new ClassLoaderCreator<Option>() {

        @Override
        public Option createFromParcel(Parcel source, ClassLoader loader) {
            return new Option(source);
        }

        @Override
        public Option createFromParcel(Parcel source) {
            return new Option(source);
        }

        @Override
        public Option[] newArray(int size) {
            return new Option[size];
        }

    };




}