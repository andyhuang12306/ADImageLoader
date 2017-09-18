package com.andy.adimageloader.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetWorkUtils {

    public static boolean isNetWorkConnected(Context context){
        if(null!=context){
            ConnectivityManager service = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = service.getActiveNetworkInfo();
            if(null!=networkInfo){
                return networkInfo.isAvailable();
            }
        }
        return false;
    }

    public static boolean isWifiConnected(Context context){
        if(null!=context){
            ConnectivityManager service = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifiInfo = service.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if(null!=wifiInfo){
                return wifiInfo.isAvailable();
            }
        }
        return false;
    }

    public static boolean isMobileConnected(Context context){
        ConnectivityManager service = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = service.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if(null!=wifiInfo){
            return wifiInfo.isAvailable();
        }
        return false;
    }

    public static int getNetType(Context context){
        int netType=-1;
        ConnectivityManager service = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = service.getActiveNetworkInfo();
        if(null!=networkInfo){
            int type = networkInfo.getType();
            if(ConnectivityManager.TYPE_MOBILE==type){
                if(null!=networkInfo.getExtraInfo()&&networkInfo.getExtraInfo().toLowerCase().equals("cmnet")){
                    netType=3;
                }else {
                    netType=2;
                }
            }else if(ConnectivityManager.TYPE_WIFI==type){
                netType=1;
            }
        }
        return netType;
    }
}