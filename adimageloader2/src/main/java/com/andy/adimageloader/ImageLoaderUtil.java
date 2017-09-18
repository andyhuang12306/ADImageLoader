package com.andy.adimageloader;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.andy.adimageloader.glide.GlideImageLoaderStrategy;
import com.andy.adimageloader.util.NetWorkUtils;


/**
 * Created by andy on 2017/7/12.
 */

public class ImageLoaderUtil {

    private        BaseImageLoaderStrategy mImageLoadStrategy;
    private static ImageLoaderUtil         mInstance;

    public static final int LOAD_ONLY_WIFI=0;//只在wifi环境下加载网络图片
    public static final int LOAD_NORMAL=1;//不管网络环境是否为wifi都加载网络图片

    public static final int IMAGE_SHAPE_RETANGE=0;//方形
    public static final int IMAGE_SHAPE_CIRCLE=1;//圆形
    public static final int IMAGE_SHAPE_ROUND=2;//圆角方形

    public static final int DISPLAY_AS_BITMAP=0;//加载位图
    public static final int DISPLAY_AS_GIF=1;//加载GIF

    private ImageLoaderUtil(){
        mImageLoadStrategy = new GlideImageLoaderStrategy();
    }

    public static ImageLoaderUtil getInstance(){
        if(null==mInstance){
            synchronized (ImageLoaderUtil.class){
                if(null==mInstance){
                    mInstance=new ImageLoaderUtil();
                    return mInstance;
                }
            }
        }
        return mInstance;
    }

    public void loadImage(@NonNull String url, @NonNull View view){
        if(null==url||"".equals(url)||null==view){
            return;
        }
        ImageLoaderBuilder builder=new ImageLoaderBuilder.Builder().build();
        loadImage(url, view, builder);
    }

    public void loadImage(@NonNull String url, @NonNull View view, @NonNull ImageLoaderBuilder builder){
        if(null==url||"".equals(url)||null==view){
            return;
        }
        if(null==builder){
            loadImage(url, view);
        }
        int wifiState = NetWorkUtils.getNetType(view.getContext());
        builder.setWifiState(wifiState);
        mImageLoadStrategy.loadImage(url, view, builder);
    }

    public void loadImage(@NonNull int resouceId, @NonNull View view){
        if(0==resouceId||null==view){
            return;
        }
        ImageLoaderBuilder builder=new ImageLoaderBuilder.Builder().build();
        loadImage(resouceId, view, builder);
    }

    public void loadImage(@NonNull int resourceId, @NonNull View view, @NonNull ImageLoaderBuilder builder){
        if(0==resourceId||null==view){
            return;
        }
        if(null==builder){
            loadImage(resourceId, view);
        }
        int wifiState = NetWorkUtils.getNetType(view.getContext());
        builder.setWifiState(wifiState);
        mImageLoadStrategy.loadImage(resourceId, view, builder);
    }

    public void clearMemoryCache(@NonNull Context context){
        if(null==context){
            return;
        }
        mImageLoadStrategy.clearMemoryCache(context);
    }

    public void clearDiskCache(@NonNull Context context){
        if(null==context){
            return;
        }
        mImageLoadStrategy.clearDiskCache(context);
    }

    public void setImageLoadStrategy(@NonNull BaseImageLoaderStrategy strategy){
        if(null==strategy){
            return;
        }
        mImageLoadStrategy=strategy;
    }
}
