package com.tongcheng.soothsay.ui.fragment.classroom.news;

import com.tongcheng.soothsay.BasePresenter;
import com.tongcheng.soothsay.BaseUiView;
import com.tongcheng.soothsay.bean.calculation.NewsBean;

import java.util.List;

/**
 * Created by Gubr on 2016/12/27.
 */

public interface  NewsContract {


    //这里应该是每一个Fragment对应一个newsview
    interface NewsView extends BaseUiView<Presenter>{


        void addNews(List<NewsBean> data);

        void setPresenter(Presenter presenter, int newId);

        void showNoMoreNews();

        void headRefresh(List<NewsBean> data);
    }


    interface Presenter extends BasePresenter{

        void addview(NewsView view, int id);

        void headRefresh(int newsid ,NewsBean newsBean);

        void showNewsList(int newsId);

        void loadmroeNews(int newsid);

        void subscribe();

        void unsubscribe();
    }

}
