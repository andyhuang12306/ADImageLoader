package com.andy.adimageloader;

import android.content.Context;
import android.view.View;

/**
 * Created by andy on 2017/7/12.
 */

public interface BaseImageLoaderStrategy {
    void loadImage(String url, View view, ImageLoaderBuilder builder);
    void loadImage(int resouceId, View view, ImageLoaderBuilder builder);
    void clearMemoryCache(Context context);
    void clearDiskCache(Context context);
}
