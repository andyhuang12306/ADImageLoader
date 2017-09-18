package com.andy.adimageloader.glide;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.andy.adimageloader.BaseImageLoaderStrategy;
import com.andy.adimageloader.ImageLoaderBuilder;
import com.andy.adimageloader.ImageLoaderUtil;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.stream.StreamModelLoader;


/**
 * Created by andy on 2017/7/12.
 */

public class GlideImageLoaderStrategy implements BaseImageLoaderStrategy {

    private static final String RESOURCEID = "resouce_id:";

    @Override
    public void loadImage(String url, View view, ImageLoaderBuilder model) {
        if(!(view instanceof ImageView)){
            return;
        }
        if(url.startsWith(RESOURCEID)){
            loadNet(url, view, model);
        }else {
            int wifiStrategy = model.getWifiStrategy();
            if(wifiStrategy== ImageLoaderUtil.LOAD_ONLY_WIFI){
                int wifiConnceted = model.getWifiState();
                if(wifiConnceted==1){
                    loadNet(url, view, model);
                }else {
                    loadCache(url, view, model);
                }
            }else {
                loadNet(url, view, model);
            }
        }
    }

    private void loadNet(String url, View view, final ImageLoaderBuilder model){
        DrawableTypeRequest request = Glide.with(view.getContext()).load(url.startsWith(RESOURCEID)?Integer.parseInt(url.substring(11)):url);
        if(model.getDisplayAs()==ImageLoaderUtil.DISPLAY_AS_BITMAP){
            request.asBitmap();
        }else if(model.getDisplayAs()==ImageLoaderUtil.DISPLAY_AS_GIF){
            request.asGif();
        }

        if(model.getDontAnimate()){
            request.dontAnimate();
        }

        if(model.getShape()==ImageLoaderUtil.IMAGE_SHAPE_CIRCLE){
            request.transform(model.isBordered()?new GlideCircleTransform(view.getContext(), model.getBorderColor(), model.getBorderWidth()):new GlideCircleTransform(view.getContext()));
        }else if(model.getShape()==ImageLoaderUtil.IMAGE_SHAPE_ROUND){
            request.transform(new GlideRoundTransform(view.getContext()));
        }
        request.placeholder(model.getPlaceHolder())
                .skipMemoryCache(model.getSkipMemerry())
                .diskCacheStrategy(model.getDiskCacheStr())
                .crossFade(model.getCrossFade())
                .error(model.getErrorHolder())
                .listener(model.getListener())
                .into((ImageView) view);
    }

    private void loadCache(String url, View view, ImageLoaderBuilder model){
        DrawableTypeRequest<String> request = Glide.with(view.getContext())
                .using(new StreamModelLoader<String>() {
                    @Override
                    public DataFetcher getResourceFetcher(final String model, int width, int height) {
                        return new DataFetcher() {
                            @Override
                            public Object loadData(Priority priority) throws Exception {
                                return null;
                            }

                            @Override
                            public void cleanup() {

                            }

                            @Override
                            public String getId() {
                                return model;
                            }

                            @Override
                            public void cancel() {

                            }
                        };
                    }
                }).load(url);
        if(model.getDisplayAs()==ImageLoaderUtil.DISPLAY_AS_BITMAP){
            request.asBitmap();
        }else if(model.getDisplayAs()==ImageLoaderUtil.DISPLAY_AS_GIF){
            request.asGif();
        }

        if(model.getDontAnimate()){
            request.dontAnimate();
        }
        if(model.getShape()== ImageLoaderUtil.IMAGE_SHAPE_CIRCLE){
            request.transform(model.isBordered()?new GlideCircleTransform(view.getContext(), model.getBorderColor(), model.getBorderWidth()):new GlideCircleTransform(view.getContext()));
        }else if(model.getShape()==ImageLoaderUtil.IMAGE_SHAPE_ROUND){
            request.transform(new GlideRoundTransform(view.getContext()));
        }
        request.placeholder(model.getPlaceHolder())
                .error(model.getErrorHolder())
                .skipMemoryCache(model.getSkipMemerry())
                .diskCacheStrategy(model.getDiskCacheStr())
                .crossFade(model.getCrossFade())
                .listener(model.getListener())
                .into((ImageView) view);
    }

    @Override
    public void loadImage(int resouceId, View view, ImageLoaderBuilder builder) {
        loadNet(RESOURCEID+String.valueOf(resouceId), view, builder);
    }


    @Override
    public void clearMemoryCache(Context context) {
        Glide.get(context).clearMemory();
    }

    @Override
    public void clearDiskCache(Context context) {
        Glide.get(context).clearDiskCache();
    }
}
