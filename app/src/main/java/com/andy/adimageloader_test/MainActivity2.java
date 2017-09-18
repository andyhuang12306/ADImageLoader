package com.andy.adimageloader_test;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.andy.adimageloader.ImageLoaderBuilder;
import com.andy.adimageloader.ImageLoaderUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andy on 2017/9/18.
 */

public class MainActivity2 extends Activity {

    @BindView(R.id.img_one)
    ImageView mImgOne;
    @BindView(R.id.img_two)
    ImageView mImgTwo;
    @BindView(R.id.img_three)
    ImageView mImgThree;

    public static final String IMG_URL="https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1505721309&di=1b9cb70766af141532cd65160d9957c8&src=http://img.mp.itc.cn/upload/20170209/7828d01aaf244737a8af6b950825d58f_th.jpg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        loadImgOne();
        loadImgtwo();
        loadImgthree();
    }

    private void loadImgthree() {
        ImageLoaderBuilder options=new ImageLoaderBuilder.Builder()
                .mBorderColor(Color.RED)
                .mBordered(true)
                .mBorderWidth(2f)
                .mCrossFade(500)
                .mDisplayAs(0)
                .mDontAnimate(false)
                .mShape(2)
                .mSkipMemerry(true)
                .build();
        ImageLoaderUtil.getInstance().loadImage(IMG_URL, mImgThree, options);
    }

    private void loadImgtwo() {
        ImageLoaderBuilder options=new ImageLoaderBuilder.Builder()
                .mBorderColor(Color.BLACK)
                .mBordered(true)
                .mBorderWidth(0.2f)
                .mCrossFade(500)
                .mDisplayAs(0)
                .mDontAnimate(false)
                .mShape(1)
                .mSkipMemerry(true)
                .build();
        ImageLoaderUtil.getInstance().loadImage(IMG_URL, mImgTwo, options);
    }

    private void loadImgOne() {
        ImageLoaderUtil.getInstance().loadImage(IMG_URL, mImgOne);
    }
}
