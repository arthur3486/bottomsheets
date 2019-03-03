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

package com.arthurivanets.demo.util.misc;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

import com.arthurivanets.bottomsheets.util.Preconditions;

import androidx.annotation.NonNull;

public final class BitmapUtils {




    @NonNull
    public static Bitmap getCircularBitmap(@NonNull Bitmap bitmap) {
        Preconditions.nonNull(bitmap);

        final Bitmap croppedCircularImg = Bitmap.createBitmap(
            bitmap.getWidth(),
            bitmap.getHeight(),
            Bitmap.Config.ARGB_8888
        );

        final Canvas canvas = new Canvas(croppedCircularImg);
        final Paint paint = new Paint();
        final Rect rectangle = new Rect(
            0,
            0,
            bitmap.getWidth(),
            bitmap.getHeight()
        );

        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setFilterBitmap(true);

        canvas.drawARGB(0, 0, 0, 0);

        paint.setColor(0xff424242);

        canvas.drawOval(new RectF(rectangle), paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        canvas.drawBitmap(bitmap, rectangle, rectangle, paint);

        return croppedCircularImg;
    }




}