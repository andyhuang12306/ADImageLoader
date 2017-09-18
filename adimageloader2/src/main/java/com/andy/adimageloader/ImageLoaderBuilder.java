package com.andy.adimageloader;

import android.graphics.Color;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;

/**
 * Created by andy on 2017/7/12.
 */

public class ImageLoaderBuilder {
    private int     mPlaceHolder;
    private int     mErrorHolder;
    private int mWifiConnected;
    private int     mWifiStrategy;
    private int     mShape;//图片形状：0：方形 1：圆形 2：圆角方形
    private boolean mBordered;//是否带边框
    private int     mBorderColor;
    private float   mBorderWidth;
    private boolean mSkipMemery;
    private DiskCacheStrategy     mDiskCacheStr;
    private int mCrossFade;//淡入淡出动画时间
    private boolean mDontAnimate;
    private RequestListener mListener;
    private int mDisplayAs;  //0：asBitmap  1:asGif

    private ImageLoaderBuilder(Builder builder){
        mPlaceHolder =builder.mPlaceHolder;
        mWifiStrategy=builder.mWifiStrategy;
        mShape=builder.mShape;
        mBordered=builder.mBordered;
        mBorderColor=builder.mBorderColor;
        mBorderWidth=builder.mBorderWidth;
        mSkipMemery=builder.mSkipMemery;
        mDiskCacheStr=builder.mDiskCacheStr;
        mErrorHolder=builder.mErrorHolder;
        mCrossFade=builder.mCrossFade;
        mDontAnimate=builder.mDontAnimate;
        mListener=builder.mListener;
        mDisplayAs=builder.mDisplayAs;
    }

    public int getDisplayAs(){
        return mDisplayAs;
    }

    public RequestListener getListener(){
        return mListener;
    }

    public boolean getDontAnimate(){
        return mDontAnimate;
    }

    public int getCrossFade(){
        return mCrossFade;
    }

    public int getErrorHolder(){
        return mErrorHolder;
    }

    public DiskCacheStrategy getDiskCacheStr(){
        return mDiskCacheStr;
    }

    public boolean getSkipMemerry(){
        return mSkipMemery;
    }

    public int getBorderColor(){
        return mBorderColor;
    }

    public float getBorderWidth(){
        return mBorderWidth;
    }

    public boolean isBordered(){
        return mBordered;
    }

    public int getShape(){
        return mShape;
    }

    public int getPlaceHolder() {
        return mPlaceHolder;
    }


    public int getWifiStrategy() {
        return mWifiStrategy;
    }

    public int getWifiState() {
        return mWifiConnected;
    }

    public void setWifiState(int state) {
        mWifiConnected =state;
    }

    public static class Builder{
        private int mPlaceHolder;
        private int mWifiStrategy;
        private int mShape;
        private boolean mBordered;
        private int mBorderColor;
        private float mBorderWidth;
        private boolean mSkipMemery;
        private DiskCacheStrategy mDiskCacheStr;
        private int mErrorHolder;
        private int mCrossFade;
        private boolean mDontAnimate;
        private RequestListener mListener;
        private int mDisplayAs;

        public Builder(){
            mPlaceHolder= R.mipmap.place_holder_image_load;
            mWifiStrategy=ImageLoaderUtil.LOAD_NORMAL;
            mShape=0;
            mBordered=false;
            mBorderColor= Color.WHITE;
            mBorderWidth= 2f;
            mSkipMemery=true;
            mDiskCacheStr= DiskCacheStrategy.ALL;
            mErrorHolder= R.mipmap.place_holder_image_load;
            mCrossFade=300;
            mDontAnimate=false;
            mListener=null;
            mDisplayAs=0;
        }

        public Builder mDontAnimate(boolean dontAnimate){
            mDontAnimate=dontAnimate;
            return this;
        }

        public Builder mCrossFade(int crossFade){
            mCrossFade=crossFade;
            return this;
        }

        public Builder mDisplayAs(int displayAs){
            mDisplayAs=displayAs;
            return this;
        }

        public Builder mListener(RequestListener listener){
            mListener=listener;
            return this;
        }

        public Builder mErrorHolder(int errorHolder){
            mErrorHolder=errorHolder;
            return this;
        }

        public Builder mDiskCacheStr(DiskCacheStrategy diskCacheStr){
            mDiskCacheStr=diskCacheStr;
            return this;
        }

        public Builder mSkipMemerry(boolean skipMemerry){
            mSkipMemery=skipMemerry;
            return this;
        }

        public Builder mPlaceHolder(int placeHolder){
            mPlaceHolder=placeHolder;
            return this;
        }

        public Builder mWifiStrategy(int wifiStrategy){
            mWifiStrategy=wifiStrategy;
            return this;
        }

        public Builder mShape(int shape){
            mShape=shape;
            return this;
        }

        public Builder mBordered(boolean bordered){
            mBordered=bordered;
            return this;
        }

        public Builder mBorderColor(int borderColor){
            mBorderColor=borderColor;
            return this;
        }

        public Builder mBorderWidth(float borderWidth){
            mBorderWidth=borderWidth;
            return this;
        }

        public ImageLoaderBuilder build(){
            return new ImageLoaderBuilder(this);
        }
    }
}
