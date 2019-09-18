package com.tongcheng.soothsay.ui.fragment.calculation.zhuanyun;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleFragment;
import com.tongcheng.soothsay.bean.event.EventJiFenBean;
import com.tongcheng.soothsay.bean.event.EventNetWorkChangeBean;
import com.tongcheng.soothsay.bean.mine.JiFenBean;
import com.tongcheng.soothsay.bean.pay.OrdersRequestBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.AllApi;
import com.tongcheng.soothsay.ui.activity.calculate.zhuanyun.CreditsIntroActivity;
import com.tongcheng.soothsay.ui.activity.mine.LoginActivity;
import com.tongcheng.soothsay.ui.activity.pay.PayActivity;
import com.tongcheng.soothsay.ui.fragment.huangli.OldAlmanacFragment;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;
import com.tongcheng.soothsay.utils.EventBusUtil;
import com.tongcheng.soothsay.utils.SystemTools;
import com.tongcheng.soothsay.utils.ToastUtil;
import com.tongcheng.soothsay.widget.MultiRadioGroup;

import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bozhihuatong on 2016/11/30.
 */

public class CreditsObtainFragment extends BaseTitleFragment implements MultiRadioGroup.OnCheckedChangeListener {
    @BindView(R.id.rg_credits_mall)
    MultiRadioGroup mRgCreditsMall;
    @BindView(R.id.tv_credits_count)
    TextView mTvCreditsCount;
    int id=0;
    private final String transType="JFDH";


    private SparseArray<JiFenBean> mHashMap = new SparseArray<>();
    private List<JiFenBean> mData1;


    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_credits_obtain, container, false);
        return view;
    }


    @Override
    public void initData() {
        if (!SystemTools.checkNet(getActivity())) {
//            ToastUtil.showShort(getActivity(),"没有网络  请连网");
            getBaseEmptyView().showNetWorkView(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initData();
                }
            });
            return;
        }
        AllApi.getInstance().getJfstoreList().enqueue(new ApiCallBack<ApiResponseBean<List<JiFenBean>>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                mRgCreditsMall.setVisibility(View.VISIBLE);
                getBaseEmptyView().hideEmptyView();
                mData1 = (List<JiFenBean>) data;
                JSONArray jsonArray = new JSONArray(mData1);
                String s = jsonArray.toString();
                if (mData1==null)return;
                for (int i = 0; i < mData1.size(); i++) {
                    JiFenBean jiFenBean = mData1.get(i);
                    setRadioButton(i, jiFenBean);
                }

            }

            @Override
            public void onError(String errorCode, String message) {
                boolean b = ErrorCodeUtil.shownetWorkAndServerError(getActivity(), errorCode);
                if (b == false){
                    ToastUtil.showShort(getActivity(),message);
                }
            }
        }));
        mRgCreditsMall.setOnCheckedChangeListener(this);
        String userJf = UserManager.getInstance().getUserJf(getActivity());
        mTvCreditsCount.setText("积分" + userJf);
    }

    @Override
    public void onCheckedChanged(MultiRadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_credits_mall_1:
                id = 1;
                break;
            case R.id.rb_credits_mall_2:
                id = 2;
                break;
            case R.id.rb_credits_mall_3:
                id = 3;
                break;
            case R.id.rb_credits_mall_4:
                id = 4;
                break;
            case R.id.rb_credits_mall_5:
                id = 5;
                break;
            case R.id.rb_credits_mall_6:
                id = 6;
                break;
        }
    }


    @OnClick(R.id.rb_credits_mall_submit)
    public void onClick() {
        if (ClickUtil.isFastClick()) {
            return;
        }
        if (!SystemTools.checkNet(getActivity())){
            ToastUtil.showShort(getActivity(),"没有网络  请连网");
            return;
        }
        if (mData1 == null) {
            initData();
            return;
        }

        String token = UserManager.getInstance().getToken();
        if(TextUtils.isEmpty(token)){
            login();
            return;
        }
        payNow();
    }

    private void payNow() {
        if (id==0){
            Toast.makeText(getActivity(), "请选选择要购买的积分", Toast.LENGTH_SHORT).show();
        }else{
            if (mData1==null||mData1.size()<id) {
                return;
            }
            JiFenBean jiFenBean = mData1.get(id - 1);
            double money=Double.valueOf(jiFenBean.getMoney()+"0");



            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("jfStoreId",id);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            OrdersRequestBean requestBean = new OrdersRequestBean();
            requestBean.setToken(UserManager.getInstance().getToken());
            requestBean.setTotalPrice("1");
            requestBean.setTransType(transType);
            requestBean.setExtra(jsonObject.toString());
            requestBean.setGoodsName("积分充值");
//            修改在activity里保存将要购买的积分  payactivity成功回调后会修改积分值
            ( (CreditsIntroActivity) getActivity()).setBuyJifen(Integer.valueOf(jiFenBean.getJf()));
            Intent intent = new Intent(getActivity(), PayActivity.class);
            intent.putExtra(Constant.INTENT_DATA,requestBean);
            startActivityForResult(intent,2);
        }
    }





    private void login(){
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivityForResult(intent,1);
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // TODO: inflate a fragment view
//        View rootView = super.onCreateView(inflater, container, savedInstanceState);
//        ButterKnife.bind(this, rootView);
//        return rootView;
//    }

    public void setRadioButton(int radioButton, JiFenBean jiFenBean) {
        if (!isAdded()) {
             return;
        }
        int id = getResources().getIdentifier("rb_credits_mall_" + (radioButton + 1), "id", getActivity().getPackageName());
        if (id != 0) {
            RadioButton button = (RadioButton) mRgCreditsMall.findViewById(id);
            button.setText(jiFenBean.getBottonStr());
        }
    }


    @Subscribe
    public void setJifen (EventJiFenBean bean){
        String jifen= bean.getJiFen();
        mTvCreditsCount.setText("积分" + jifen);
    }


    @Subscribe void netchange(EventNetWorkChangeBean bean){
        if (bean.getEvent()== EventNetWorkChangeBean.NETWORK_CONNECTED&&(mData1==null||mData1.size()==0)) {
            initData();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBusUtil.register(this);
    }


    @Override
    public void onDestroy() {
        EventBusUtil.unregister(this);
        super.onDestroy();

    }
}
