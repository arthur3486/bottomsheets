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

package com.arthurivanets.bottomsheets.listeners;

import android.animation.Animator;

/**
 * A compat version of the {@link Animator.AnimatorListener}.
 */
public abstract class AnimatorListenerAdapter implements Animator.AnimatorListener {

    private boolean mIsCancelled;

    protected AnimatorListenerAdapter() {
        mIsCancelled = false;
    }

    @Override
    public final void onAnimationStart(Animator animation, boolean isReverse) {
        mIsCancelled = false;

        onAnimationStarted(animation);
    }

    @Override
    public final void onAnimationStart(Animator animation) {
        mIsCancelled = false;

        onAnimationStarted(animation);
    }

    public void onAnimationStarted(Animator animation) {

    }

    @Override
    public final void onAnimationEnd(Animator animation, boolean isReverse) {
        onAnimationEnded(animation);
    }

    @Override
    public final void onAnimationEnd(Animator animation) {
        onAnimationEnded(animation);
    }

    public void onAnimationEnded(Animator animation) {

    }

    @Override
    public final void onAnimationCancel(Animator animation) {
        mIsCancelled = true;

        onAnimationCancelled(animation);
    }

    public void onAnimationCancelled(Animator animation) {

    }

    @Override
    public final void onAnimationRepeat(Animator animation) {
        mIsCancelled = false;

        onAnimationRepeated(animation);
    }

    public void onAnimationRepeated(Animator animation) {

    }

    public final boolean isCancelled() {
        return mIsCancelled;
    }

}
