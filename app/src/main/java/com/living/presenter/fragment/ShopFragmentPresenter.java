package com.living.presenter.fragment;

import android.content.res.TypedArray;
import android.view.View;

import com.google.gson.GsonBuilder;
import com.living.bean.mingli.T;
import com.living.bean.mingli.layout.HeadItem;
import com.living.bean.mingli.layout.RecyclerViewItemData;
import com.living.constant.root.RootType;
import com.living.http.RetrofitService;
import com.living.ui.fragment.ShopFragment;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.bean.classroom.ShopHomeBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.http.ApiResponseBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by weihao on 2018/1/24.
 */

public class ShopFragmentPresenter {

    private ShopFragment mShopFragment;
    private List<RecyclerViewItemData> mRecyclerItemDatas = new ArrayList();

    public ShopFragmentPresenter(ShopFragment shopFragment) {
        mShopFragment = shopFragment;
    }

    public void getShopData() {
        String url1 = "http://fssvip.b0.upaiyun.com/fss/81201504166465658.jpg";
        String url2 = "http://fssvip.b0.upaiyun.com/fss/45531504167604026.jpg";
        String url3 = "http://fssvip.b0.upaiyun.com/fss/30551504167961086.jpg";
        initBanner(url1, url2, url3);
        initHead(R.array.ShopheadName, R.array.Shopheadimage);
        getShopItemData("");
        mShopFragment.setShopData(mRecyclerItemDatas);

    }
    private void getShopItemData(String str) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.Url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //支持Rx
                .build();
        final RetrofitService service = retrofit.create(RetrofitService.class);
        Observable<ApiResponseBean<ShopHomeBean>> observable = service.getShopHome(str);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ApiResponseBean<ShopHomeBean>>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        mShopFragment.getBaseEmptyView().showNetWorkView(R.drawable.nonetwork, R.string.showNeterr, R.string.touch_again, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getShopItemData("");

                            }
                        });
                    }
                    @Override
                    public void onNext(ApiResponseBean<ShopHomeBean> shopbean) {
                        mShopFragment.getBaseLoadingView().hideLoading();
                        List<RecyclerViewItemData> viewItemData = new ArrayList<>();
                        List<ShopHomeBean.StoreInfosBean> storeInfos = shopbean.getResult().getStoreInfos();
                        RecyclerViewItemData itemData = new RecyclerViewItemData(storeInfos, RootType.COLUMN_RECYCLER);
                        viewItemData.add(itemData);
                        RecyclerViewItemData itemDatas = new RecyclerViewItemData(viewItemData, RootType.RECYCLER_ITEM);
                        mRecyclerItemDatas.add(itemDatas);
                    }
                });

    }
    //轮播图
    private void initBanner(String url1, String url2, String url3) {
        List<String> strUrl = new ArrayList<>();
        strUrl.add(url1);
        strUrl.add(url2);
        strUrl.add(url3);
        RecyclerViewItemData banners = new RecyclerViewItemData(strUrl, RootType.BANNER);
        mRecyclerItemDatas.add(banners);
    }
    //顶部网格布局，8个item
    private void initHead(int nameId, int imageId) {
        List<T> mHeadItems = new ArrayList<>();
        List<RecyclerViewItemData> headItemDatas = new ArrayList<>();

        String[] headName = mShopFragment.getActivity().getResources().getStringArray(nameId);
        int[] headImage = new int[headName.length];
        TypedArray imgs = mShopFragment.getActivity().getResources().obtainTypedArray(imageId);
        for (int i = 0; i < imgs.length(); i++) {
            int resourceId = imgs.getResourceId(i, -1);
            headImage[i] = resourceId;
        }
        for (int i = 0; i < headName.length; i++) {
            T headItem = new HeadItem(headImage[i], headName[i]);
            mHeadItems.add(headItem);
        }
        RecyclerViewItemData headItems = new RecyclerViewItemData(mHeadItems, RootType.GRID_FOUR);
        headItemDatas.add(headItems);
        RecyclerViewItemData headDatas = new RecyclerViewItemData(headItemDatas, RootType.RECYCLER_ITEM);
        mRecyclerItemDatas.add(headDatas);
    }
}