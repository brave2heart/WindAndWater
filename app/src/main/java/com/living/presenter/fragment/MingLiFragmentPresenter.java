package com.living.presenter.fragment;

import android.content.res.TypedArray;

import com.living.bean.mingli.T;
import com.living.bean.mingli.layout.Column;
import com.living.bean.mingli.layout.DaShi;
import com.living.bean.mingli.layout.FourGrid;
import com.living.bean.mingli.layout.HeadItem;
import com.living.bean.mingli.layout.RecyclerViewItemData;
import com.living.constant.root.RootType;
import com.living.ui.fragment.MingLiFragment;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.bean.calculation.MingLiBean;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.AllApi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weihao on 2018/1/24.
 */

public class MingLiFragmentPresenter {
    private final MingLiFragment mingLiFragment;
    private List<RecyclerViewItemData> mRecyclerItemDatas = new ArrayList();
    private MingLiBean mMingLiBean;

    public MingLiFragmentPresenter(MingLiFragment liFragment) {
        mingLiFragment = liFragment;
    }

    //命理fragment数据
    public void getMingLiData() {
        //初始化广告条
//        initBanner(R.drawable.mingli_lunbotu1, R.drawable.mingli_lunbotu1, R.drawable.mingli_lunbotu1);
        String url1 = "http://img.zcool.cn/community/01407458bf699ca801219c77fb42db.jpg@1280w_1l_2o_100sh.jpg";

        String url2 = "http://img4.imgtn.bdimg.com/it/u=4267287614,405687641&fm=214&gp=0.jpg";
        String url3 = "http://pic.58pic.com/58pic/17/07/46/46q58PICkUB_1024.jpg";

        initBanner(url1, url2, url3);

        //初始化headitem数据
        initHead(R.array.mingliheadName, R.array.mingliheadImage);
        //放生池
        mRecyclerItemDatas.add(new RecyclerViewItemData(new Column(R.drawable.shape_red_rectangles, "放生池", "点击查看", R.drawable.tv_right), RootType.COLUMN));
        //放生池图片
        mRecyclerItemDatas.add(new RecyclerViewItemData(R.drawable.mingli_fashengchi, RootType.FANGIMAGE));
        //热门测算
        mRecyclerItemDatas.add(new RecyclerViewItemData(new Column(R.drawable.shape_red_rectangles, "热门测算", "", 0), RootType.COLUMN));
        //热门测算数据
        initFourGrid(R.array.minglihotName, R.array.minglihotContent, R.array.minglihotImage);
        //推荐测算
        mRecyclerItemDatas.add(new RecyclerViewItemData(new Column(R.drawable.shape_red_rectangles, "推荐测算", "", 0), RootType.COLUMN));
        //推荐测算数据
        initFourGrid(R.array.minglirecommendName, R.array.minglirecommendContent, R.array.minglirecommendImage);
        //大师推荐
        mRecyclerItemDatas.add(new RecyclerViewItemData(new Column(R.drawable.shape_red_rectangles, "大师推荐", "更多", R.drawable.tv_right), RootType.COLUMN));
        //大师推荐数据
        getListInfo();

        mingLiFragment.setMingLiData(mRecyclerItemDatas);

    }

    private void getListInfo() {
        AllApi.getInstance().getMingLiDatas("").enqueue(new ApiCallBack<ApiResponseBean<MingLiBean>>(new BaseCallBack<MingLiBean>() {
            @Override
            public void onSuccess(MingLiBean data) {

                mMingLiBean = data;
                List<MingLiBean.DsBean> mMingLiBeanDs = mMingLiBean.getDs();

                DaShi daShi = new DaShi(
                        mMingLiBeanDs,
                        "周易",
                        "国学",
                        "专业",
                        "好评",
                        R.mipmap.jieda,
                        "1901",
                        R.mipmap.pinglun,
                        "859",
                        R.mipmap.canyu,
                        "11639"
                );

                mRecyclerItemDatas.add(new RecyclerViewItemData(daShi, RootType.DASHI));

            }

            @Override
            public void onError(String errorCode, String message) {
            }
        }));
    }


    //轮播图
    private void initBanner(String url1, String url2, String url3) {
//
        List<String> strUrl = new ArrayList<>();
        strUrl.add(url1);
        strUrl.add(url2);
        strUrl.add(url3);
//      strUrl.add("https://www.baidu.com/img/bd_logo1.png");


//                        http://fssvip.b0.upaiyun.com/fss/81201504166465658.jpg
//                        http://fssvip.b0.upaiyun.com/fss/45531504167604026.jpg
//                        http://fssvip.b0.upaiyun.com/fss/30551504167961086.jpg
//        List<Integer> integerList = new ArrayList<>();
//        integerList.add(imageId1);
//        integerList.add(imageId2);
//        integerList.add(imageId3);
        RecyclerViewItemData banners = new RecyclerViewItemData(strUrl, RootType.BANNER);
        mRecyclerItemDatas.add(banners);
    }

    //顶部网格布局，8个item
    private void initHead(int nameId, int imageId) {
        List<T> mHeadItems = new ArrayList<>();
        List<RecyclerViewItemData> headItemDatas = new ArrayList<>();

        String[] headName = mingLiFragment.getActivity().getResources().getStringArray(nameId);
        int[] headImage = new int[headName.length];
        TypedArray imgs = mingLiFragment.getActivity().getResources().obtainTypedArray(imageId);
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

    //网格布局，4个item
    private void initFourGrid(int nameId, int contentId, int imageId) {

        String[] names = mingLiFragment.getActivity().getResources().getStringArray(nameId);
        String[] contents = mingLiFragment.getActivity().getResources().getStringArray(contentId);
        int[] images = new int[names.length];
        TypedArray imgs = mingLiFragment.getActivity().getResources().obtainTypedArray(imageId);
        for (int i = 0; i < imgs.length(); i++) {
            int resourceId = imgs.getResourceId(i, -1);
            images[i] = resourceId;
        }

        //((热门测算数据，标记1),标记1);  相当于重复嵌套RecyclerViewItemData类来标记2次来区分到子类的数据判断

        List<T> interiorDatas = new ArrayList<>();
        List<RecyclerViewItemData> listRecyItemDatas = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            T baZi = new FourGrid(names[i], contents[i], images[i]);
            interiorDatas.add(baZi);
        }
//        内层数据type remenitem
        RecyclerViewItemData recyItemData = new RecyclerViewItemData(interiorDatas, RootType.GRID_TWO);
        listRecyItemDatas.add(recyItemData);

//        外层数据type  head
        RecyclerViewItemData recyclerViewItemDatas = new RecyclerViewItemData(listRecyItemDatas, RootType.RECYCLER_ITEM);
        mRecyclerItemDatas.add(recyclerViewItemDatas);


    }

}
