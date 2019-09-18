package com.tongcheng.soothsay.data.classroom.news;

import com.tongcheng.soothsay.bean.calculation.NewsBean;
import com.tongcheng.soothsay.bean.calculation.NewsTypeBean;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.AllApi;

import java.util.List;

/**
 * Created by Gubr on 2016/12/27.
 */

public class NewImp implements INews {

    @Override
    public void getNewsList( String typeid, String start ,BaseCallBack<List<NewsBean>> callback) {
        AllApi.getInstance().getNewsList(typeid,start).enqueue(new ApiCallBack<ApiResponseBean<List<NewsBean>>>(callback));
    }



}
