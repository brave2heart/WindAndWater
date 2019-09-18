package com.tongcheng.soothsay.data.classroom.news;

import com.tongcheng.soothsay.bean.calculation.NewsBean;
import com.tongcheng.soothsay.http.BaseCallBack;

import java.util.List;

/**
 * Created by Gubr on 2016/12/27.
 */

public interface INews {

    void getNewsList( String typeid, String start, BaseCallBack<List<NewsBean>> callback);
}
