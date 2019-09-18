package com.living.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.content.ContentValues.TAG;

/**
 * Created by weihao on 2018/1/5.
 */

public class CheckURLUtil {


    public static boolean checkNetWorkStatus(Context context) {
        boolean result;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();
        if (netinfo != null && netinfo.isConnected()) {
            result = true;
            Log.i(TAG, "The net was connected");
        } else {
            result = false;
            Log.i(TAG, "The net was bad!");
        }
        return result;
    }

    public static boolean checkURL(String url) {
        boolean value = false;
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            int code = conn.getResponseCode();
            System.out.println(">>>>>>>>>>>>>>>> " + code + " <<<<<<<<<<<<<<<<<<");
            if (code != 200) {
                value = false;
            } else {
                value = true;
            }
        } catch (MalformedURLException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        return value;
    }


}
