package com.tongcheng.soothsay.ui.activity.classroom;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.classroom.ShopClassifyInfosRecyclerViewAdapter;
import com.tongcheng.soothsay.base.AbsBaseRecycleAdapter;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.base.EndlessRecyclerOnScrollListener;
import com.tongcheng.soothsay.bean.classroom.ShopClassifyInfosBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.AllApi;
import com.tongcheng.soothsay.ui.MainActivity;
import com.tongcheng.soothsay.ui.activity.huangli.weather.SpaceItemDecoration;
import com.tongcheng.soothsay.ui.fragment.classroom.ClassRoomFragment;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;
import com.tongcheng.soothsay.utils.LogUtil;
import com.tongcheng.soothsay.utils.ToastUtil;
import com.tongcheng.soothsay.widget.RecyclerSpace;


import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;


/**
 * Created by 宋家任 on 2016/11/30.
 * 商品分类
 */

public class ShopClassifyInfosActivity extends BaseTitleActivity {

    @BindView(R.id.rv_shop_classsify)
    PullToRefreshGridView rvShopClasssify;
    private ShopClassifyInfosRecyclerViewAdapter recycleAdapter;
//    private List<ShopClassifyInfosBean.StoresBean> list;
    //    String[] flag = new String[]{"0", "15", "30", "45"};
    private int start = 0;//翻一页就 +15

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_shop_classify);
        initData();
        initListener();
    }

    String sortId;

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        sortId = intent.getStringExtra("sortId");
        String sortName = intent.getStringExtra("sortName");

        getBaseHeadView().showTitle("开运商城·" + sortName);
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShopClassifyInfosActivity.this.finish();
            }
        });
        getBaseLoadingView().showLoading();

        getDatas(String.valueOf(start), sortId);

    }

    @Override
    public void initListener() {
        rvShopClasssify.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                start = 0;
                getDatas(String.valueOf(start), sortId);
                refreshView.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                start += 15;
                getDatas(String.valueOf(start), sortId);
                refreshView.onRefreshComplete();
            }
        });
    }

    /**
     * 下载数据
     *
     * @param s
     * @param sortId
     */
    private void getDatas(String s, String sortId) {
//        list = new ArrayList<>();
        AllApi.getInstance().getShopClassifyInfos(s, sortId).enqueue(new ApiCallBack<ApiResponseBean<ShopClassifyInfosBean>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                ShopClassifyInfosBean bean = (ShopClassifyInfosBean) data;
                List<ShopClassifyInfosBean.StoresBean> stores = bean.getStores();
                if (start == 0) {
//                initRecycleView(list);
                    initGridView(stores);
                } else {
                    if (stores.size() == 0) {//没有更多数据

                    } else {
//                        for (int i = 0; i < stores.size(); i++) {
//                            initGridView(list);
//                        }
                        recycleAdapter.changeData(stores);
                    }
                    rvShopClasssify.onRefreshComplete();
                }
                getBaseLoadingView().hideLoading();
            }

            @Override
            public void onError(String errorCode, String message) {
                getBaseLoadingView().hideLoading();
                boolean b = ErrorCodeUtil.shownetWorkAndServerError(ShopClassifyInfosActivity.this, errorCode);
                if (b == false) {
                    ToastUtil.showShort(ShopClassifyInfosActivity.this, message);
                }
            }
        }));
//        return list;
    }

    private void initGridView(final List<ShopClassifyInfosBean.StoresBean> result) {
        recycleAdapter = new ShopClassifyInfosRecyclerViewAdapter(this, result, R.layout.item_shop_content_item_recyclerview);
        rvShopClasssify.setAdapter(recycleAdapter);
        rvShopClasssify.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String merchandiseId = recycleAdapter.getDatas().get(position).getMerchandiseId();//商品id
                Intent intent = new Intent(ShopClassifyInfosActivity.this, GoodsDetailsActivity.class);
                intent.putExtra("goodsid", merchandiseId);
                intent.putExtra("redirectUrl", result.get(position).getRedirectUrl());
                startActivity(intent);
            }
        });
    }


//    private void initRecycleView(final List<ShopClassifyInfosBean.StoresBean> result) {
//        recycleAdapter = new ShopClassifyInfosRecyclerViewAdapter(this, result,
//                R.layout.item_shop_content_item_recyclerview);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
//        rvShopClasssify.setLayoutManager(gridLayoutManager);
//        rvShopClasssify.addItemDecoration(new RecyclerSpace(10, Color.WHITE));
//        rvShopClasssify.setAdapter(recycleAdapter);
//        //item点击监听
//        recycleAdapter.setOnItemClickListener(new AbsBaseRecycleAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClickListener(int posotion) {
//                String merchandiseId = result.get(posotion).getMerchandiseId();//商品id
//                Intent intent = new Intent(ShopClassifyInfosActivity.this, GoodsDetailsActivity.class);
//                intent.putExtra("goodsid", merchandiseId);
//                intent.putExtra("redirectUrl", result.get(posotion).getRedirectUrl());
//                startActivity(intent);
//            }
//        });

//        rvShopClasssify.addOnScrollListener(new EndlessRecyclerOnScrollListener(gridLayoutManager) {
//            @Override
//            public void onLoadMore(int currentPage) {
//                simulateLoadMoreData();
//            }
//        });
//    }

//    private void simulateLoadMoreData() {
//        loadMoreData();
//        recycleAdapter.notifyDataSetChanged();
//    }
//
//    int i = 1;
//
//    private void loadMoreData() {
//
//        List<ShopClassifyInfosBean.StoresBean> datas = getDatas(flag[i], sortId);
//        list.addAll(datas);
//        i++;
//    }


}
