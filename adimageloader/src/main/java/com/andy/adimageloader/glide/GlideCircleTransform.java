package com.andy.adimageloader.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Created by andy on 2017/7/13.
 */

public class GlideCircleTransform extends BitmapTransformation {

    private Paint mBorderPaint;
    private float mBorderWith;

    public GlideCircleTransform(Context context) {
        super(context);
    }

    public GlideCircleTransform(Context context, int borderColor, float borderWith){
        super(context);
        mBorderPaint=new Paint();
        mBorderPaint.setDither(true);
        mBorderPaint.setFilterBitmap(true);
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setColor(borderColor);
        mBorderPaint.setStrokeCap(Paint.Cap.ROUND);
        mBorderPaint.setStrokeWidth(borderWith);
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap source, int outWidth, int outHeight) {
        return circleCrop(pool, source);
    }

    private Bitmap circleCrop(BitmapPool pool, Bitmap source) {
        if (source == null) return null;
        int size = Math.min(source.getWidth(), source.getHeight());
        int x = (source.getWidth() - size)/2;
        int y = (source.getHeight() - size)/2;

        Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);
        Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        float r = size/2f;
        float r1=(size-2*4)/2f;
        canvas.drawCircle(r, r, r1, paint);
        if(null!=mBorderPaint){
            canvas.drawCircle(r,r,r1,mBorderPaint);
        }
        return result;
    }

    @Override
    public String getId() {
        return getClass().getName();
    }
}
