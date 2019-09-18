package com.tongcheng.soothsay.ui.fragment.calculation.zhuanyun;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.OfferingsAdapter;
import com.tongcheng.soothsay.base.BaseTitleFragment;
import com.tongcheng.soothsay.bean.calculation.Offeringsbean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.PrayingApi;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;
import com.tongcheng.soothsay.utils.ToastUtil;

import java.util.List;

/**
 * Created by bozhihuatong on 2016/11/28.
 */

public class OfferingsMallFragment extends BaseTitleFragment {


    private View mView;
    private ListView mListView;
    private int mItem_praying_mall;
    private int mType;
    private String sort;
    private String OfferingsCount;
    private List<List<Offeringsbean>> mDatas;
    private OfferingsAdapter mOfferingsAdapter;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        mView = inflater.inflate(R.layout.fragment_offerings_mall, container, false);
//        initData();
//        initListener();
        return mView;

    }

    @Override
    public void initData() {
        sort = getArguments().getString(Constant.SORT);
        mItem_praying_mall = getArguments().getInt(Constant.ITEM_LAYOUT);
        mListView = (ListView) mView.findViewById(R.id.listview);
        mType = getArguments().getInt("type");
        if (UserManager.getInstance().isLogin()) {
            getDatas(sort, UserManager.getInstance().getUser().getToken(), "1");
            String token = UserManager.getInstance().getUser().getToken();
        }else{
            getDatas(sort);
        }

        mOfferingsAdapter = new OfferingsAdapter(getActivity(), mDatas, mItem_praying_mall, mType);
        mListView.setAdapter(mOfferingsAdapter);

    }

    @Override
    public void initListener() {

    }


    public void getDatas(String sort){
        PrayingApi.getInstance().getOfferings(sort).enqueue(new ApiCallBack<ApiResponseBean<List<List<Offeringsbean>>>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                mOfferingsAdapter.addData((List<List<Offeringsbean>>) data);
            }

            @Override
            public void onError(String errorCode, String message) {
                boolean b = ErrorCodeUtil.shownetWorkAndServerError(getActivity(), errorCode);
                if (b == false){
                    ToastUtil.showShort(getActivity(),message);
                }
            }
        }));
    }

    public void getDatas(String sort, String token, String isFee) {
        PrayingApi.getInstance().getOfferings(sort, token, isFee).enqueue(new ApiCallBack<ApiResponseBean<List<List<Offeringsbean>>>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                List<List<Offeringsbean>> data1 = (List<List<Offeringsbean>>) data;
                mOfferingsAdapter.addData((List<List<Offeringsbean>>) data);
            }

            @Override
            public void onError(String errorCode, String message) {
                boolean b = ErrorCodeUtil.shownetWorkAndServerError(getActivity(), errorCode);
                if (b == false){
                    ToastUtil.showShort(getActivity(),message);
                }
            }
        }));

    }
}
