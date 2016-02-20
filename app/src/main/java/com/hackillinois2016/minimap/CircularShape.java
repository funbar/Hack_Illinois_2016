package com.hackillinois2016.minimap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Funbar on 2/20/2016.
 */
public class CircularShape extends ImageView{

    public CircularShape(Context context, AttributeSet as)
    {
        super(context, as);
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        int width = 0;
        //int height = 0;
        Drawable drawable = getDrawable();
        if(getWidth() <= 0 || getHeight() <= 0 )
        {
            return;
        }
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Bitmap bitmapcopy = bitmap.copy(Bitmap.Config.ARGB_8888, true);

        width = getWidth();
        //height = getHeight();

        Bitmap roundMap = getRounded(bitmapcopy, width);
        canvas.drawBitmap(roundMap, 0, 0, null);

    }

    public static Bitmap getRounded(Bitmap bitmap, int radius)
    {
        Bitmap lastBitmap;
        if(bitmap.getWidth() != radius || bitmap.getHeight() != radius) {
            lastBitmap = Bitmap.createScaledBitmap(bitmap, radius, radius, false);
        }
            else
        {
            lastBitmap = bitmap;
            Bitmap lastOutput = Bitmap.createBitmap(lastBitmap.getWidth(), lastBitmap.getHeight(), Bitmap.Config.ARGB_8888);
            // ARGB_8888 = 4 bytes/pixsels and stuffz
            Canvas canvas = new Canvas(lastOutput);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setFilterBitmap(true);
            //paint.setDither(true);

            canvas.drawARGB(0, 0, 0, 0);
            /*
            blueish colour
             */
            paint.setColor(Color.parseColor("#99a0ba"));
            canvas.drawCircle(lastBitmap.getWidth() / 2 + 0.7f, lastBitmap.getHeight() / 2 + 0.7f, lastBitmap.getWidth() / 2 + 0.1f, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            Rect rectangle = new Rect(0, 0, lastBitmap.getWidth(), lastBitmap.getHeight());
            canvas.drawBitmap(lastBitmap, rectangle, rectangle, paint);

            return lastOutput;
        }

    }
}
