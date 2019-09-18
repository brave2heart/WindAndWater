package com.tongcheng.soothsay.http;


import android.text.TextUtils;
import android.util.Log;

import com.tongcheng.soothsay.MyApplicationLike;
import com.tongcheng.soothsay.utils.GsonUtil;
import com.tongcheng.soothsay.utils.NetworkUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCallBack<T> implements Callback<T> {

    public static final String NET_ERROR = "400";
    public static final String RESOLVE_ERROR = "401";
    public static final String SERVER_ERROR = "500";

    BaseCallBack baseCallBack;

    public ApiCallBack(BaseCallBack baseCallBack) {
        this.baseCallBack = baseCallBack;
    }




    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            ApiResponseBean apiResponseBean = (ApiResponseBean) response.body();
//            System.out.println("apiResponseBean" + GsonUtil.objectToJson(apiResponseBean));


            if (!"00000".equals(apiResponseBean.getErrorCode())) {
                baseCallBack.onError(apiResponseBean.getErrorCode(), apiResponseBean.getMessage());
                return;
            }
            baseCallBack.onSuccess(apiResponseBean.getResult());
        } else {
            ApiResponseBean apiResponseBean = null;
            try {
                String s = new String(response.errorBody().bytes());
                if (TextUtils.isEmpty(s)) {
                    baseCallBack.onError(NET_ERROR,"网络错误");
                    return;
                }
                apiResponseBean = (ApiResponseBean) GsonUtil.jsonToBean(s, ApiResponseBean.class);
            } catch (Exception e) {
                e.printStackTrace();

                baseCallBack.onError(RESOLVE_ERROR, "解析错误");
                Log.i("tag", "onResponse  error  :  " + NET_ERROR + "     msg : " + e.getMessage());
            }
            if (apiResponseBean == null) {
                baseCallBack.onError(SERVER_ERROR, "apiResponseBean 空");
                Log.i("tag", "onResponse  error  :  " + SERVER_ERROR);
            } else {
                baseCallBack.onError(apiResponseBean.getErrorCode(), apiResponseBean.getMessage());
            }

        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        t.printStackTrace();
        //网络连接错误
        if(NetworkUtil.isConnected(MyApplicationLike.getInstance())){
            baseCallBack.onError(SERVER_ERROR, "服务器繁忙");
        //服务器错误
        }else{
            baseCallBack.onError(NET_ERROR,"暂时没有网络，请检查手机网络");
        }
    }
}
