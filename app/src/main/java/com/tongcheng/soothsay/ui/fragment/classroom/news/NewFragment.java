package com.tongcheng.soothsay.ui.fragment.classroom.news;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.classroom.NewsListAdapter;
import com.tongcheng.soothsay.base.AbTypeBaseAdapter;
import com.tongcheng.soothsay.base.BaseLazyFragment;
import com.tongcheng.soothsay.bean.calculation.NewsBean;
import com.tongcheng.soothsay.ui.activity.classroom.NewsActivity;
import com.tongcheng.soothsay.ui.activity.common.WebViewActivity;
import com.tongcheng.soothsay.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Gubr on 2016/12/27.
 */

public class NewFragment extends BaseLazyFragment implements NewsContract.NewsView {
    @BindView(R.id.lv_record)
    PullToRefreshListView mLvRecord;

    private int start = 0;
    private NewsListAdapter mAdapter;
    List<NewsBean> datas = new ArrayList<>();


    private NewsContract.Presenter mPresenter;
    private int mNewId;


    @Override
    public void initListener() {
        mLvRecord.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (mAdapter.getDatas() != null && mAdapter.getDatas().size() > 0) {
                    mPresenter.headRefresh(mNewId, mAdapter.getDatas().get(0));
                } else {
                    mPresenter.loadmroeNews(mNewId);
                }
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                mPresenter.loadmroeNews(mNewId);
            }
        });
//        mLvRecord.getRefreshableView().addFooterView(View.inflate(getContext(),R.layout.base_loading,null));
        mLvRecord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == mAdapter.getCount() && view.getTag() == null) return;
                NewsBean bean = (NewsBean) ((AbTypeBaseAdapter.ViewHolder) view.getTag()).getTag();
//                GotoUtil.GoToWebViewActivity(getActivity(), bean.getTitle(), bean.getRedirectUrl());
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("web_title", bean.getTitle());
                intent.putExtra("web_url", bean.getRedirectUrl());
                intent.putExtra("web_share",true);
                getActivity().startActivity(intent);
            }
        });
//        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.base_empty_error, mLvRecord, false);
//        View viewById = inflate.findViewById(R.id.base_reload);
//        viewById.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mPresenter.loadmroeNews(mNewId);
//            }
//        });
//        mLvRecord.setEmptyView(inflate);

    }

    @Override
    public void initData() {
        mNewId = getArguments().getInt("newsId");
        if (mPresenter == null) {
            mPresenter = ((NewsActivity) getActivity()).getPresenter();
            mPresenter.addview(this, mNewId);
        }
        mAdapter = new NewsListAdapter(getContext(), datas, R.layout.item_news_type_zero, R.layout.item_news_type_one);
        mLvRecord.setAdapter(mAdapter);
    }


    @Override
    public void addNews(List<NewsBean> data) {
        start += data.size();
        mAdapter.changeDatas(data);
    }


    @Override
    public void showNetError() {
        ToastUtil.showShort(getContext(), getString(R.string.showNeterr));
    }

    @Override
    public void showError(String info) {
        ToastUtil.showShort(getContext(), info);
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showTitle(String title) {

    }

    @Override
    public void showLoad() {
        getBaseLoadingView().showLoading();
    }

    @Override
    public void showLoadFinish() {
        mLvRecord.onRefreshComplete();
        getBaseLoadingView().hideLoading();
    }

    @Override
    public void showReLoad() {

    }

    @Override
    public void setPresenter(NewsContract.Presenter presenter) {
        mPresenter.addview(this, mNewId);
    }

    @Override
    public void setPresenter(NewsContract.Presenter presenter, int newId) {
        setPresenter(presenter);
        mPresenter.addview(this, newId);
    }

    @Override
    public void showNoMoreNews() {
        ToastUtil.showShort(getActivity(), "没有更多信息啦");
    }

    @Override
    public void headRefresh(List<NewsBean> data) {
        start += data.size();
        mAdapter.changeAddDatasFrist(data);
        mLvRecord.onRefreshComplete();
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    protected void lazyLoad() {
        if (start == 0) {//懒加载  但是每一次重新进到这个fragment 都会重新刷新 所以需要  给加一个判断
            mPresenter.showNewsList(mNewId);
        }
    }
}
