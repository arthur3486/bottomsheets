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

package com.arthurivanets.bottomsheets;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;

import com.arthurivanets.bottomsheets.config.BaseConfig;
import com.arthurivanets.bottomsheets.listeners.AnimatorListenerAdapter;
import com.arthurivanets.bottomsheets.util.Preconditions;
import com.arthurivanets.bottomsheets.util.Utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.arthurivanets.bottomsheets.util.Utils.IS_AT_LEAST_LOLLIPOP;

/**
 * <br>
 *      A base container which incorporates the main handling of the bottom sheet related logic.
 * <br>
 * <br>
 *      Is responsible for the management of the bottom sheet related views, as well as the animations
 *      and related stuff.
 * <br>
 */
abstract class BottomSheetContainer extends FrameLayout implements BottomSheet {


    protected final BaseConfig mConfig;

    private float mDimAmount;

    // Dimensions
    private float mSheetCornerRadius;
    private float mMaxSheetWidth;
    private float mStatusBarSize;
    private float mTopGapSize;

    private Point mDisplaySize;

    // Colors
    private int mDimColor;
    private int mSheetBackgroundColor;

    // Animation related
    private long mAnimationDuration;
    private Interpolator mAnimationInterpolator;
    private ValueAnimator mAnimator;

    // UI elements
    private ViewGroup mContentContainer;
    private FrameLayout mBottomSheetView;

    // States
    private State mState;

    private boolean mIsDismissableOnTouchOutside;

    // Callbacks & Listeners
    private Runnable mViewManagementAction;
    private OnDismissListener mOnDismissListener;




    BottomSheetContainer(@NonNull Activity hostActivity, @NonNull BaseConfig config) {
        super(hostActivity);

        mConfig = Preconditions.checkNonNull(config);

        init(hostActivity);
    }




    private void init(Activity hostActivity) {
        initContainer(hostActivity);
        initResources(hostActivity);
        initBottomSheet();
    }




    @SuppressWarnings("NewApi")
    private void initContainer(Activity hostActivity) {
        mContentContainer = hostActivity.findViewById(android.R.id.content);

        // ensuring that the sheet is rendered atop all the other views within the same View Group
        if(IS_AT_LEAST_LOLLIPOP) {
            setElevation(999f);
        }
    }




    private void initResources(Activity hostActivity) {
        mDimAmount = mConfig.getDimAmount();

        initDimensions(hostActivity);
        initColors();
        initAnimations();
        initStates();
    }




    private void initDimensions(Activity hostActivity) {
        mSheetCornerRadius = mConfig.getSheetCornerRadius();
        mStatusBarSize = Utils.getStatusBarSize(getContext());
        mTopGapSize = mConfig.getTopGapSize();
        mMaxSheetWidth = mConfig.getMaxSheetWidth();

        mDisplaySize = new Point();
        hostActivity.getWindowManager().getDefaultDisplay().getSize(mDisplaySize);
    }




    private void initColors() {
        mDimColor = mConfig.getDimColor();
        mSheetBackgroundColor = mConfig.getSheetBackgroundColor();
    }




    private void initAnimations() {
        mAnimationDuration = mConfig.getSheetAnimationDuration();
        mAnimationInterpolator = mConfig.getSheetAnimationInterpolator();
    }




    private void initStates() {
        mState = State.COLLAPSED;
        mIsDismissableOnTouchOutside = mConfig.isDismissableOnTouchOutside();
    }




    private void initBottomSheet() {
        mBottomSheetView = new FrameLayout(getContext());
        mBottomSheetView.setLayoutParams(generateDefaultLayoutParams());
        mBottomSheetView.setBackground(createBottomSheetBackgroundDrawable());

        // Creating the actual sheet content view
        final View createdSheetView = Preconditions.checkNonNull(onCreateSheetContentView(getContext()));

        // adding the created views
        mBottomSheetView.addView(createdSheetView);
        addView(mBottomSheetView);
    }




    @Override
    protected final LayoutParams generateDefaultLayoutParams() {
        final LayoutParams layoutParams = new LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        );
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL;

        return layoutParams;
    }




    private void addToContainer() {
        mContentContainer.addView(
            this,
            new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        );
    }




    private void removeFromContainer() {
        mContentContainer.removeView(this);
    }




    @Override
    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        cancelStateTransitionAnimation();
    }




    /**
     * <br>
     *      Used to create the content view of the bottom sheet.
     * <br>
     * <br>
     *      (Will be the main content shown to the user)
     * <br>
     *
     * @param context a valid context of the parent view
     * @return the view to be used as a custom content view of bottom sheet
     */
    @NonNull
    protected abstract View onCreateSheetContentView(@NonNull Context context);




    @Override
    public final boolean onTouchEvent(MotionEvent event) {
        if(mIsDismissableOnTouchOutside) {
            dismiss();
        }

        return true;
    }




    @Override
    protected final void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setUiState(mState);
    }




    @Override
    protected final void measureChildWithMargins(View child,
                                                 int parentWidthMeasureSpec,
                                                 int widthUsed,
                                                 int parentHeightMeasureSpec,
                                                 int heightUsed) {
        final int parentWidth = MeasureSpec.getSize(parentWidthMeasureSpec);
        final int parentHeight = MeasureSpec.getSize(parentHeightMeasureSpec);
        final int displayHeight = mDisplaySize.y;
        final int verticalGapSize = (int) ((displayHeight > parentHeight) ? 0 : mStatusBarSize);
        final int maxWidth = (int) Math.min(parentWidth, mMaxSheetWidth);
        final int maxHeight = (int) (parentHeight - verticalGapSize - mTopGapSize);
        int adjustedParentWidthMeasureSpec = parentWidthMeasureSpec;
        int adjustedParentHeightMeasureSpec = parentHeightMeasureSpec;

        // adjusting the max width & height specs to be used by the Bottom Sheet View
        if(child == mBottomSheetView) {
            adjustedParentWidthMeasureSpec = MeasureSpec.makeMeasureSpec(maxWidth, MeasureSpec.getMode(parentWidthMeasureSpec));
            adjustedParentHeightMeasureSpec = MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.getMode(parentHeightMeasureSpec));
        }

        super.measureChildWithMargins(
            child,
            adjustedParentWidthMeasureSpec,
            widthUsed,
            adjustedParentHeightMeasureSpec,
            heightUsed
        );
    }




    @Override
    public final void show() {
        show(true);
    }




    @Override
    public final void show(final boolean animate) {
        if(isAttachedToContainer()) {
            return;
        }

        cancelStateTransitionAnimation();
        addToContainer();
        postViewShowingAction(animate);
    }




    @Override
    public final void dismiss() {
        dismiss(true);
    }




    @Override
    public final void dismiss(boolean animate) {
        if(isAttachedToContainer() && (!animate || !State.COLLAPSING.equals(mState))) {
            cancelStateTransitionAnimation();
            reportOnDismiss();
            postViewDismissingAction(animate);
        }
    }




    private void postViewShowingAction(final boolean animate) {
        postPendingViewManagementAction(new Runnable() {
            @Override
            public void run() {
                if(animate) {
                    if(!State.EXPANDED.equals(mState) && !State.EXPANDING.equals(mState)) {
                        setUiState(State.COLLAPSED);
                        animateStateTransition(State.EXPANDING);
                    }
                } else {
                    setUiState(mState = State.EXPANDED);
                }
            }
        });
    }




    private void postViewDismissingAction(final boolean animate) {
        postPendingViewManagementAction(new Runnable() {
            @Override
            public void run() {
                if(animate) {
                    if(!State.COLLAPSED.equals(mState) && !State.COLLAPSING.equals(mState)) {
                        animateStateTransition(State.COLLAPSING);
                    }
                } else {
                    removeFromContainer();
                    setUiState(mState = State.COLLAPSED);
                }
            }
        });
    }




    private void postPendingViewManagementAction(Runnable action) {
        cancelPendingViewManagementAction();
        post(mViewManagementAction = action);
    }




    private void cancelPendingViewManagementAction() {
        if(mViewManagementAction != null) {
            removeCallbacks(mViewManagementAction);
        }
    }




    private boolean isAttachedToContainer() {
        final int containerChildCount = mContentContainer.getChildCount();

        for(int i = 0; i < containerChildCount; i++) {
            if(mContentContainer.getChildAt(i) == this) {
                return true;
            }
        }

        return false;
    }




    private void animateStateTransition(final State state) {
        // cancelling any currently active transition animation
        cancelStateTransitionAnimation();

        // performing the precalculations
        final boolean isExpanding = State.EXPANDING.equals(state);
        final float startY = getMeasuredHeight();
        final float endY = (getMeasuredHeight() - mBottomSheetView.getMeasuredHeight());
        final float deltaY = (startY - endY);
        final float startValue = ((getMeasuredHeight() - mBottomSheetView.getY()) / deltaY);
        final float endValue = (isExpanding ? 1f : 0f);

        // creating and starting a brand-new one
        mAnimator = ValueAnimator.ofFloat(startValue, endValue);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                final float animatedValue = (Float) valueAnimator.getAnimatedValue();
                final float newY = (startY + (animatedValue * (endY - startY)));

                mBottomSheetView.setY(newY);
                setBackgroundAlpha(animatedValue);
            }
        });
        mAnimator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStarted(Animator animation) {
                mState = (isExpanding ? State.EXPANDING : State.COLLAPSING);
            }

            @Override
            public void onAnimationEnded(Animator animation) {
                if(isExpanding) {
                    mState = State.EXPANDED;
                } else {
                    mState = State.COLLAPSED;

                    removeFromContainer();
                }
            }

        });
        mAnimator.setInterpolator(mAnimationInterpolator);
        mAnimator.setDuration(mAnimationDuration);
        mAnimator.start();
    }




    private void cancelStateTransitionAnimation() {
        if((mAnimator != null) && mAnimator.isRunning()) {
            mAnimator.cancel();
        }
    }




    private void reportOnDismiss() {
        if(mOnDismissListener != null) {
            mOnDismissListener.onDismiss(this);
        }
    }




    private Drawable createBottomSheetBackgroundDrawable() {
        final GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(mSheetBackgroundColor);
        drawable.setCornerRadii(new float[] {
            mSheetCornerRadius,
            mSheetCornerRadius,
            mSheetCornerRadius,
            mSheetCornerRadius,
            0f,
            0f,
            0f,
            0f
        });

        return drawable;
    }




    private void setBackgroundAlpha(float alpha) {
        setBackgroundColor(Color.argb(
            (int) (255 * alpha * mDimAmount),
            Color.red(mDimColor),
            Color.green(mDimColor),
            Color.blue(mDimColor)
        ));
    }




    private void setUiState(State state) {
        setBottomSheetState(state);
        setBackgroundState(state);
    }




    private void setBottomSheetState(State state) {
        if(State.EXPANDED.equals(state)) {
            mBottomSheetView.setY(getMeasuredHeight() - mBottomSheetView.getMeasuredHeight());
        } else if(State.COLLAPSED.equals(state)) {
            mBottomSheetView.setY(getMeasuredHeight());
        }
    }




    private void setBackgroundState(State state) {
        if(State.EXPANDED.equals(state)) {
            setBackgroundAlpha(1f);
        } else if(State.COLLAPSED.equals(state)) {
            setBackgroundAlpha(0f);
        }
    }




    @NonNull
    @Override
    public final State getState() {
        return mState;
    }




    @Override
    public final void setOnDismissListener(@Nullable OnDismissListener onDismissListener) {
        mOnDismissListener = onDismissListener;
    }




}