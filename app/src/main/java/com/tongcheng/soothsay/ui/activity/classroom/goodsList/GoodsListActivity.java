package com.tongcheng.soothsay.ui.activity.classroom.goodsList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.classroom.GoodsListAdapter;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.classroom.GoodsListBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.ui.activity.classroom.GoodsDetailsActivity;
import com.tongcheng.soothsay.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;

/**
 * 开运商城
 */
public class GoodsListActivity extends BaseTitleActivity implements GoodsListContract.View {

    @BindView(R.id.lv_goods_list)
    PullToRefreshGridView listView;

    private String type;  //查询类型.  1:新品推荐，2：热销圣品

    private GoodsListContract.Presenter mPresenter;

    private GoodsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);

        initView();
        initListener();
        initData();

    }


    @Override
    public void initView() {
        String title = getIntent().getStringExtra(Constant.INTENT_DATA);
        getBaseHeadView().showTitle(title);

        type = "新品推荐".equals(title) ? "1" : "2";
    }

    @Override
    public void initListener() {
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                mPresenter.getGoodsList(type, true);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                mPresenter.loadMore(type);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GoodsListBean.StoresBean bean = adapter.getDatas().get(position);
                String goodsId = bean.getMerchandiseId();
                String url = bean.getRedirectUrl();
                Intent intent = new Intent(GoodsListActivity.this, GoodsDetailsActivity.class);
                intent.putExtra("goodsid", goodsId);
                intent.putExtra("redirectUrl", url);
                startActivity(intent);
            }
        });
    }


    @Override
    public void initData() {
        new GoodsListPresenter(this);
        mPresenter.getGoodsList(type, false);
    }


    @Override
    public void showGoodsList(List<GoodsListBean.StoresBean> beans) {
        adapter = new GoodsListAdapter(this, beans, R.layout.item_grid_goods);
        listView.setAdapter(adapter);
    }

    @Override
    public void refreshFinish(List<GoodsListBean.StoresBean> beans) {
        adapter.notifyChangeData(beans);
    }

    @Override
    public void loadMoreFinish(List<GoodsListBean.StoresBean> beans) {
        adapter.changeData(beans);
    }

    @Override
    public void showNetError() {
        if (adapter == null) {
            getBaseEmptyView().showNetWorkView(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.getGoodsList(type, false);
                }
            });

        } else {
            ToastUtil.showShort(this, getString(R.string.showNeterr));
        }
    }

    @Override
    public void showError(String info) {
        ToastUtil.showShort(this, info);
    }

    @Override
    public void showEmpty() {
        if (adapter == null) {
            getBaseEmptyView().showEmptyContent();
        }
    }

    @Override
    public void showTitle(String title) {

    }

    @Override
    public void showLoad() {
        if (adapter == null) {
            getBaseLoadingView().showLoading();
        }
    }

    @Override
    public void showLoadFinish() {
        getBaseLoadingView().hideLoading();
        listView.onRefreshComplete();
    }

    @Override
    public void showReLoad() {

    }

    @Override
    public void setPresenter(GoodsListContract.Presenter presenter) {
        mPresenter = presenter;
    }


}
