package com.tongcheng.soothsay.ui.fragment.classroom.news;

import android.util.Log;
import android.util.SparseArray;

import com.tongcheng.soothsay.bean.calculation.NewsBean;
import com.tongcheng.soothsay.data.classroom.news.INews;
import com.tongcheng.soothsay.data.classroom.news.NewImp;
import com.tongcheng.soothsay.http.BaseCallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gubr on 2016/12/27.
 */

public class NewsPresenter implements NewsContract.Presenter {

    private SparseArray<NewsContract.NewsView> views;
    private SparseArray<Integer> newscount;
    private INews source;


    public NewsPresenter() {
        views = new SparseArray<>();
        newscount = new SparseArray<>();
        source = new NewImp();
    }


    @Override
    public void start() {

    }

    @Override
    public void addview(NewsContract.NewsView view, int id) {
        views.put(id, view);
    }

    @Override
    public void headRefresh(final int newsid, final NewsBean newsBean) {
        source.getNewsList(String.valueOf(newsid), "0", new BaseCallBack<List<NewsBean>>() {
            @Override
            public void onSuccess(List<NewsBean> data) {
                ArrayList<NewsBean> datas = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    if (!data.get(i).getId().equals( newsBean.getId())) {
                        datas.add(data.get(i));
                    }else{
                        break;
                    }
                }
                if (datas.size() == 0)
                   showNoMoreNews(newsid);
                NewsPresenter.this.headRefresh(datas, newsid);
            }

            @Override
            public void onError(String errorCode, String message) {
              showLoadFinish(newsid);
              showError(newsid,message);

            }
        });
    }

    /**
     * 进来  第一次加载
     *
     * @param newsId
     */
    @Override
    public void showNewsList(final int newsId) {
        loadmroeNews(newsId, 0);
    }

    @Override
    public void loadmroeNews(int newsid) {
        Integer integer = newscount.get(newsid);
        int count = 0;
        if (integer != null) {
            count = integer;
        }
        loadmroeNews(newsid, count);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        views.clear();
        newscount.clear();
//        source = null;
    }

    private void loadmroeNews(final int newsid, int count) {
        if (newscount.get(newsid, 0) == 0) {
            getNewsView(newsid).showLoad();
        }
        Log.d("NewsPresenter", "count:" + count);
        source.getNewsList(String.valueOf(newsid), String.valueOf(count), new BaseCallBack<List<NewsBean>>() {
            @Override
            public void onSuccess(List<NewsBean> data) {
                newscount.put(newsid, newscount.get(newsid, 0) + data.size());
                if (data.size() == 0) {
                  showLoadFinish(newsid);
                    showNoMoreNews(newsid);
                    return;
                }
              addNews(newsid,data);
              showLoadFinish(newsid);
            }

            @Override
            public void onError(String errorCode, String message) {

                showLoadFinish(newsid);
               showError(newsid,message);
            }
        });
    }


    private void showLoadFinish(int newsid) {
        NewsContract.NewsView newsView = views.get(newsid);
        if (newsView != null) {
            newsView.showLoadFinish();
        }
    }

    private void showError(int newsid, String info) {
        NewsContract.NewsView newsView = views.get(newsid);
        if (newsView != null) {
            newsView.showError(info);
        }
    }

    private void headRefresh(ArrayList<NewsBean> datas, int newsid) {
        NewsContract.NewsView newsView = views.get(newsid);
        if (newsView != null) {
           newsView.headRefresh(datas);
        }
    }

    private void addNews(int newsid, List<NewsBean> data) {
        NewsContract.NewsView newsView = views.get(newsid);
        if (newsView != null) {
            newsView.addNews(data);
        }
    }
    private void showNoMoreNews(int newsid) {
        NewsContract.NewsView newsView = views.get(newsid);
        if (newsView != null) {
            newsView.showNoMoreNews();
        }
    }


    private NewsContract.NewsView getNewsView(int newsid) {
        return views.get(newsid);
    }
}
