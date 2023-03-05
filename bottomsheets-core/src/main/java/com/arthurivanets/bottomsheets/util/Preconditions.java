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

package com.arthurivanets.bottomsheets.util;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import androidx.annotation.NonNull;

/**
 * A set of the general preconditions and checks.
 */
public final class Preconditions {

    public static void isTrue(boolean condition) {
        isTrue("Condition", condition);
    }

    public static void isTrue(@NonNull String info, boolean condition) {
        nonNull(info);

        if (!condition) {
            throw new IllegalStateException(String.format(
                Locale.US,
                "%s - the condition is not met. The Condition must be positive.",
                info
            ));
        }
    }

    public static <T> T checkNonNull(T object) {
        nonNull(object);
        return object;
    }

    public static void nonNull(Object object) {
        if (object == null) {
            throw new NullPointerException("The argument must be non-null!");
        }
    }

    public static void nonEmpty(String string) {
        if (TextUtils.isEmpty(string)) {
            throw new IllegalArgumentException("You must specify a valid raw text.");
        }
    }

    public static void nonEmpty(Collection<?> collection) {
        nonNull(collection);

        if (collection.isEmpty()) {
            throw new IllegalArgumentException("You must specify a collection that contains at least one element.");
        }
    }

    public static void withinBoundsExclusive(int index, ArrayList<?> dataset) {
        nonNull(dataset);

        if ((index < 0) || (index >= dataset.size())) {
            throw new IndexOutOfBoundsException("The Index must lie within the bounds of the specified dataset (0 <= index < dataset.size).");
        }
    }

    public static void withinBoundsInclusive(int index, ArrayList<?> dataset) {
        nonNull(dataset);

        if ((index < 0) || (index > dataset.size())) {
            throw new IndexOutOfBoundsException("The Index must lie within the bounds of the specified dataset (0 <= index <= dataset.size).");
        }
    }

}
