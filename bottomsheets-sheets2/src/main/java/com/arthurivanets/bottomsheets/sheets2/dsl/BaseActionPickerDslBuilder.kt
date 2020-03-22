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
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.animation.Interpolator
import androidx.annotation.ColorInt
import androidx.annotation.LayoutRes
import com.arthurivanets.bottomsheets.config.BaseConfig
import com.arthurivanets.bottomsheets.config.BaseConfig.DEFAULT_ANIMATION_DURATION
import com.arthurivanets.bottomsheets.config.BaseConfig.DEFAULT_DIM_AMOUNT
import com.arthurivanets.bottomsheets.sheets2.Action
import com.arthurivanets.bottomsheets.sheets2.R
import com.arthurivanets.bottomsheets.sheets2.adapter.Item
import com.arthurivanets.bottomsheets.sheets2.adapter.ItemThemeApplier
import com.arthurivanets.bottomsheets.sheets2.extensions.getCompatColor
import com.arthurivanets.bottomsheets.sheets2.extensions.getDimension


@DslMarker
annotation class ActionPickerConfigBuilderDsl


internal data class ActionPickerConfig(
    private val _dimColor : Int,
    private val _dimAmount : Float,
    private val _topGapSize : Float,
    private val _extraPaddingTop : Float,
    private val _extraPaddingBottom : Float,
    private val _maxSheetWidth : Float,
    private val _sheetBackgroundColor : Int,
    private val _sheetCornerRadius : Float,
    private val _sheetAnimationDuration : Long,
    private val _sheetAnimationInterpolator : Interpolator,
    private val _isDismissableOnTouchOutside : Boolean,
    val isDismissableOnItemClick : Boolean,
    val headerView : AuxiliaryView?,
    val dividerColor : Int,
    val items : List<Item<*>>,
    val itemThemeApplier : ItemThemeApplier?,
    val onItemClickListener : ((Action) -> Unit)?
) : BaseConfig {

    override fun getDimColor() = _dimColor

    override fun getDimAmount() = _dimAmount

    override fun getTopGapSize() = _topGapSize

    override fun getExtraPaddingTop() = _extraPaddingTop

    override fun getExtraPaddingBottom() = _extraPaddingBottom

    override fun getMaxSheetWidth() = _maxSheetWidth

    override fun getSheetBackgroundColor() = _sheetBackgroundColor

    override fun getSheetCornerRadius() = _sheetCornerRadius

    override fun getSheetAnimationDuration() = _sheetAnimationDuration

    override fun getSheetAnimationInterpolator() = _sheetAnimationInterpolator

    override fun isDismissableOnTouchOutside() = _isDismissableOnTouchOutside

}


@ActionPickerConfigBuilderDsl
abstract class AbstractActionPickerConfigBuilder internal constructor(
    protected val context : Context
) {


    private var headerView : AuxiliaryView? = null
    protected val items : MutableList<Item<*>> = mutableListOf()

    @ColorInt
    var dividerColor : Int = context.getCompatColor(R.color.actions_divider_color)

    @ColorInt
    var dimColor : Int = context.getCompatColor(R.color.bottom_sheet_dim_color)

    @ColorInt
    var backgroundColor : Int = context.getCompatColor(R.color.bottom_sheet_background_color)

    var dimAmount : Float = DEFAULT_DIM_AMOUNT
    var topGapSize : Float = 0F
    var extraPaddingTop : Float = 0F
    var extraPaddingBottom : Float = 0F
    var maxWidth : Float = context.getDimension(R.dimen.bottom_sheet_max_sheet_width)
    var cornerRadius : Float = context.getDimension(R.dimen.bottom_sheet_sheet_corner_radius)
    var animationDuration : Long = DEFAULT_ANIMATION_DURATION
    var animationInterpolator : Interpolator = DecelerateInterpolator(1.5F)
    var isDismissableOnTouchOutside : Boolean = true
    var isDismissableOnItemClick : Boolean = true
    var onItemClickListener : ((Action) -> Unit)? = null


    fun headerView(@LayoutRes resId : Int, isSticky : Boolean = true) = apply {
        headerView = AuxiliaryView(
            resourceId = resId,
            isSticky = isSticky
        )
    }


    fun headerView(view : View, isSticky : Boolean = true) = apply {
        headerView = AuxiliaryView(
            view = view,
            isSticky = isSticky
        )
    }


    internal fun build() : ActionPickerConfig {
        return ActionPickerConfig(
            _dimColor = dimColor,
            _dimAmount = dimAmount,
            _topGapSize = topGapSize,
            _extraPaddingTop = extraPaddingTop,
            _extraPaddingBottom = extraPaddingBottom,
            _maxSheetWidth = maxWidth,
            _sheetBackgroundColor = backgroundColor,
            _sheetCornerRadius = cornerRadius,
            _sheetAnimationDuration = animationDuration,
            _sheetAnimationInterpolator = animationInterpolator,
            _isDismissableOnTouchOutside = isDismissableOnTouchOutside,
            isDismissableOnItemClick = isDismissableOnItemClick,
            headerView = headerView,
            dividerColor = dividerColor,
            items = items,
            itemThemeApplier = createItemThemeApplier(),
            onItemClickListener = onItemClickListener
        )
    }


    protected abstract fun createItemThemeApplier() : ItemThemeApplier


}