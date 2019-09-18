package com.tongcheng.soothsay.ui.fragment.calculation.zhuanyun;

import android.app.Activity;
import android.app.Dialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.OfferingsAdapter;
import com.tongcheng.soothsay.base.BasePopupWindow;
import com.tongcheng.soothsay.bean.calculation.Offeringsbean;
import com.tongcheng.soothsay.bean.calculation.QFAddGPBean;
import com.tongcheng.soothsay.bean.calculation.QfDxBean;
import com.tongcheng.soothsay.bean.calculation.QfgpListBean;
import com.tongcheng.soothsay.bean.event.EventJiFenBean;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.PrayingApi;
import com.tongcheng.soothsay.ui.activity.calculate.zhuanyun.CreditsIntroActivity;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;
import com.tongcheng.soothsay.utils.EventBusUtil;
import com.living.utils.GotoUtil;
import com.tongcheng.soothsay.utils.ResUtil;
import com.tongcheng.soothsay.utils.ToastUtil;

import java.util.List;

/**
 * Created by bozhihuatong on 2016/11/28.
 */

public class OfferingsSelectPopoWindow extends BasePopupWindow implements OfferingsAdapter.OfferingsOnClickLister {


    private final int mContentid;
    private View mView;
    private ListView mListView;
    private int mItem_praying_mall;
    private int mType;
    private String sort;
    private String OfferingsCount;
    private List<List<Offeringsbean>> mDatas;
    private OfferingsAdapter mOfferingsAdapter;

    boolean isopening = false;
    private TextView tvIntro;
    private QfDxBean mDxBean;
    private ResUtil mResUtil;

    public OfferingsSelectPopoWindow(Activity activity, QfDxBean dxBean, int resId, boolean outSide, String offeringsType, int itemLayout, int threeOrOne, int contentid) {
        this(activity, resId, outSide, offeringsType, itemLayout, threeOrOne, contentid);
        mDxBean = dxBean;
    }


    public void setSort(String sort) {
        this.sort = sort;
        if (isopening) {
            return;
        }
        if (UserManager.getInstance().isLogin()) {
            getDatas(sort, UserManager.getInstance().getUser().getToken(), null);
        } else {
            getDatas(sort);
        }

    }

    public OfferingsSelectPopoWindow(Activity activity, int resId, boolean outSide, String offeringsType, int itemLayout, int threeOrOne, int contentid) {
        super(activity, resId, outSide);
        sort = offeringsType;
        mItem_praying_mall = itemLayout;
        mType = threeOrOne;
        mContentid = contentid;
        tvIntro.setText(mContentid);
        initData();
    }

    @Override
    public void init(View layoutView) {
        mListView = (ListView) layoutView.findViewById(R.id.listview);
        tvIntro = (TextView) layoutView.findViewById(R.id.tv_popo_offerings_select_intro);


    }

    public void initData() {
        if (isopening) {
            return;
        }
        if (UserManager.getInstance().isLogin()) {
            getDatas(sort, UserManager.getInstance().getUser().getToken(), null);
        } else {
            getDatas(sort);
        }
        isopening = true;
        mOfferingsAdapter = new OfferingsAdapter(getActivity(), mDatas, mItem_praying_mall, mType);
        mOfferingsAdapter.setOfferingsOnClickLister(this);
        mListView.setAdapter(mOfferingsAdapter);

    }


    @Override
    protected int[] loadAnimRes() {
        int[] anmis = {R.anim.dade_bottom_in, R.anim.dade_bottom_out};
        return anmis;
    }

    @Override
    public void initListener() {

    }


    public void getDatas(String sort) {
        isopening = true;
        PrayingApi.getInstance().getOfferings(sort).enqueue(new ApiCallBack<ApiResponseBean<List<List<Offeringsbean>>>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                mOfferingsAdapter.addData((List<List<Offeringsbean>>) data);
                isopening = false;
            }

            @Override
            public void onError(String errorCode, String message) {
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            }
        }));
    }

    public void getDatas(String sort, String token, String isFee) {
        isopening = true;

//        这里可以去内存拿 到时候也可以去网络拿数据。
        PrayingApi.getInstance().getOfferings(sort, token, isFee).enqueue(new ApiCallBack<ApiResponseBean<List<List<Offeringsbean>>>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                List<List<Offeringsbean>> data1 = (List<List<Offeringsbean>>) data;
                mOfferingsAdapter.addData((List<List<Offeringsbean>>) data);
                OfferingsSelectPopoWindow.this.showPopOnRootView(getActivity());
                isopening = false;
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

    @Override
    public void onDismiss() {
        super.onDismiss();
    }

    @Override
    public void onClickOfferings(Offeringsbean offeringsbean) {
//        这里如果有东西 就使用 如果没有就提示购买。

//        Integer count = Integer.valueOf(offeringsbean.getBuyCount());
//        if (count>0) {
////            这里要再查一下当前的god有没有花。
//        }
        showIntroDialog(offeringsbean);

    }


    /**
     * 显示  花的介绍
     *
     * @param offeringsbean
     */
    private void showIntroDialog(final Offeringsbean offeringsbean) {
        final Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_offerings_intro, null);
        TextView intro_name = (TextView) inflate.findViewById(R.id.tv_offerings_intro_name);
        intro_name.setText(offeringsbean.getName());
        TextView intro_content = (TextView) inflate.findViewById(R.id.tv_offerings_intro_content);
        intro_content.setText(getGpContent(offeringsbean));
        BootstrapButton intro_submit = (BootstrapButton) inflate.findViewById(R.id.btn_offerings_intro_submit);
        if (offeringsbean.getIntBuyCount() == 0 && offeringsbean.getIntJiFen() > 0) {
            intro_submit.setText(offeringsbean.getPriceTitle());
        } else {
            intro_submit.setText("确定");
        }
        intro_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ClickUtil.isFastClick()) return;
                String userJf = UserManager.getInstance().getUserJf(getActivity());
                Integer integer = Integer.valueOf(userJf);

                if (offeringsbean.getIntBuyCount() == 00 && offeringsbean.getIntJiFen() > integer) {
//                    这里提示要去买积分
                    showGoPayDialog();
                } else if (offeringsbean.getIntBuyCount() == 0 && offeringsbean.getIntJiFen() > 0) {
                    PrayingApi.getInstance().qfBuy(UserManager.getInstance().getUser().getToken(), offeringsbean.getName(), offeringsbean.getAmount(), offeringsbean.getJf()).enqueue(new ApiCallBack<ApiResponseBean<Void>>(new BaseCallBack() {
                        @Override
                        public void onSuccess(Object data) {
                            offeringsbean.setBuyCountByint(offeringsbean.getAmountInt());
                            UserManager.getInstance().changeUserJF(getActivity(), -offeringsbean.getIntJiFen());
                            EventBusUtil.post(new EventJiFenBean(UserManager.getInstance().getUserJf(getActivity())));
                            ToastUtil.showShort(getActivity(),"兑换成功");
                            mOfferingsAdapter.notifyDataSetChanged();
                            dialog.dismiss();
                        }

                        @Override
                        public void onError(String errorCode, String message) {
                            boolean b = ErrorCodeUtil.shownetWorkAndServerError(getActivity(), errorCode);
                            if (b == false){
                                ToastUtil.showShort(getActivity(),message);
                            }
                            dialog.dismiss();
                        }
                    }));

                } else {
                    PrayingApi.getInstance().qfAddGp(UserManager.getInstance().getUser().getToken(), mDxBean.getQfDx(), offeringsbean.getName()).enqueue(new ApiCallBack<ApiResponseBean<QFAddGPBean>>(new BaseCallBack<QFAddGPBean>() {
                        @Override
                        public void onSuccess(QFAddGPBean data) {
//                这时要设置对应的类型上。
                            UserManager.getInstance().changeUserJF(getActivity(),Integer.valueOf(data.getJf()));
                            QfgpListBean qfgpListBean = new QfgpListBean();
                            qfgpListBean.sort = Integer.valueOf(sort);
                            qfgpListBean.gpName = offeringsbean.getName();
                            qfgpListBean.gpId = Integer.valueOf(offeringsbean.getStoreId());
                            qfgpListBean.qfDate = String.valueOf(System.currentTimeMillis());
                            if (o != null)
                                o.onAddOfferingsSuccessful(qfgpListBean);
                            OfferingsSelectPopoWindow.this.closePop();
                            dialog.dismiss();
                        }





                        @Override
                        public void onError(String errorCode, String message) {
                            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }));
                }
            }
        });

        dialog.setContentView(inflate);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        dialog.show();
    }



    private void showGoPayDialog() {
        final   Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_offerings_gopay, null);
        BootstrapButton gopay_submit = (BootstrapButton) inflate.findViewById(R.id.btn_offerings_gopay_submit);
        BootstrapButton gopay_cancel = (BootstrapButton) inflate.findViewById(R.id.btn_offerings_gopay_cancel);

        gopay_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ClickUtil.isFastClick()) return;

                gotoPayCreditsActivity();
            }
        });
        gopay_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        dialog.setContentView(inflate);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        dialog.show();
    }

    private void gotoPayCreditsActivity() {
        Toast.makeText(getActivity(), "去买积分", Toast.LENGTH_SHORT).show();
        GotoUtil.GoToActivity(getActivity(), CreditsIntroActivity.class);
    }

    private String getGpContent(Offeringsbean offeringsbean) {


        mResUtil = ResUtil.newInstance();
//        String storeId = offeringsbean.getStoreId();
//        int strId = getActivity().getResources().getIdentifier("gp_content_牡丹花", "string", getActivity().getPackageName());
//        String content="";
//        Log.d(TAG, "getGpContent: "+strId+" "+offeringsbean.getName());
//        if (strId!=0)
//            content = getActivity().getResources().getString(strId);
//        return content;

        String gpContentByName = mResUtil.getGpContentByName(offeringsbean.getName());
        return gpContentByName;
    }

    public interface OfferingsOnClickLister {
        void onAddOfferingsSuccessful(QfgpListBean offeringsbean);
    }

    private OfferingsOnClickLister o;

    public void setOfferingsOnClickLister(OfferingsOnClickLister o) {
        this.o = o;
    }
}
