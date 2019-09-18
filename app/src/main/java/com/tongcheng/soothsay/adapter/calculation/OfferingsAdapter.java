package com.tongcheng.soothsay.adapter.calculation;

import android.app.Activity;
import android.app.Dialog;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.bean.calculation.Offeringsbean;
import com.tongcheng.soothsay.bean.event.EventJiFenBean;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.PrayingApi;
import com.tongcheng.soothsay.ui.activity.calculate.zhuanyun.CreditsIntroActivity;
import com.tongcheng.soothsay.ui.activity.mine.LoginActivity;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;
import com.tongcheng.soothsay.utils.EventBusUtil;
import com.living.utils.GotoUtil;
import com.tongcheng.soothsay.utils.ResUtil;
import com.tongcheng.soothsay.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

// TODO: 2017/1/12 下个版本要把这个优化一下
/**
 *
 * Created by bozhihuatong on 2016/11/28.
 * 供品 适合器
 */

public class OfferingsAdapter extends BaseAdapter implements View.OnClickListener {

    private final ResUtil mResUtil;
    private  List<List<Offeringsbean>> mDatas=new ArrayList<>();
    private final LayoutInflater mInflater;
    /**
     * 三个一个类型  一个一个类型
     */
    private final int mType;
    private final int mLayoutId;
    private final Activity mContext;
    private ArrayList<Offeringsbean> mOfferingsbeen;
//    private ArrayList<Offeringsbean> mOfferingsbeen;

    public OfferingsAdapter(Activity context, List<List<Offeringsbean>> datas, int LayoutId, int type) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mType = type;
        mLayoutId = LayoutId;
        mResUtil = ResUtil.newInstance();

    }

    @Override
    public int getCount() {


        if (mType==1){
            return mOfferingsbeen==null?0:mOfferingsbeen.size();
        }else{
            return mDatas.size();
        }
    }


    public void addData(final List<List<Offeringsbean>> datas){


        if (mType==1){
            mOfferingsbeen = new ArrayList<>();
            for (List<Offeringsbean> data : datas) {
                for (Offeringsbean offeringsbean : data) {
                   mOfferingsbeen.add(offeringsbean);
                }
            }
        }else{
            mDatas=datas;
        }
        mContext.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        });
    }
    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {




        if (mType == 3) {
            convertView = getView3(position, convertView, parent);
        } else if (mType == 1) {
            convertView = getview1(position, convertView, parent);
        }
        return convertView;

    }

    private View getview1(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(mLayoutId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.mImage1 = (ImageView) convertView.findViewById(R.id.iv_offerings1_img);
            viewHolder.name1 = (TextView) convertView.findViewById(R.id.tv_offerings1_name);
            viewHolder.priceTitle1 = (TextView) convertView.findViewById(R.id.tv_offerings1_price);

            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        Offeringsbean offeringsbean = mOfferingsbeen.get(position);
        viewHolder.mImage1.setImageResource(getImageRes(offeringsbean));
        viewHolder.mImage1.setTag(offeringsbean);
        viewHolder.name1.setText(offeringsbean.getNameTitle());
        viewHolder.priceTitle1.setText(offeringsbean.getPriceTitle());
        viewHolder.mImage1.setOnClickListener(this);
        return convertView;
    }

    @NonNull
    private View getView3(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(mLayoutId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.mImage1 = (ImageView) convertView.findViewById(R.id.iv_offerings1_img);
            viewHolder.mImage2 = (ImageView) convertView.findViewById(R.id.iv_offerings2_img);
            viewHolder.mImage3 = (ImageView) convertView.findViewById(R.id.iv_offerings3_img);
            viewHolder.name1 = (TextView) convertView.findViewById(R.id.tv_offerings1_name);
            viewHolder.name2 = (TextView) convertView.findViewById(R.id.tv_offerings2_name);
            viewHolder.name3 = (TextView) convertView.findViewById(R.id.tv_offerings3_name);
            viewHolder.priceTitle1 = (TextView) convertView.findViewById(R.id.tv_offerings1_price);
            viewHolder.priceTitle2 = (TextView) convertView.findViewById(R.id.tv_offerings2_price);
            viewHolder.priceTitle3 = (TextView) convertView.findViewById(R.id.tv_offerings3_price);

            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        viewsetVisibility(viewHolder);
        if (mDatas.get(position).size()>=1) {
            Offeringsbean offeringsbean = mDatas.get(position).get(0);
            viewHolder.mImage1.setImageResource(getImageRes(offeringsbean));
            viewHolder.mImage1.setTag(offeringsbean);
            viewHolder.name1.setText(offeringsbean.getNameTitle());
            viewHolder.priceTitle1.setText(offeringsbean.getPriceTitle());
        }

        if (mDatas.get(position).size()>=2) {
            Offeringsbean offeringsbean = mDatas.get(position).get(1);

            viewHolder.mImage2.setImageResource(getImageRes(offeringsbean));
            viewHolder.mImage2.setTag(offeringsbean);
            viewHolder.name2.setText(offeringsbean.getNameTitle());
            viewHolder.priceTitle2.setText(offeringsbean.getPriceTitle());
        } else {
            viewHolder.mImage2.setVisibility(View.GONE);
            viewHolder.name2.setVisibility(View.GONE);
            viewHolder.priceTitle2.setVisibility(View.GONE);
        }

        if (mDatas.get(position).size()>=3) {
            Offeringsbean offeringsbean = mDatas.get(position).get(2);

            viewHolder.mImage3.setImageResource(getImageRes(offeringsbean));
            viewHolder.mImage3.setTag(offeringsbean);
            viewHolder.name3.setText(offeringsbean.getNameTitle());
            viewHolder.priceTitle3.setText(offeringsbean.getPriceTitle());
        } else {
            viewHolder.mImage3.setVisibility(View.GONE);
            viewHolder.name3.setVisibility(View.GONE);
            viewHolder.priceTitle3.setVisibility(View.GONE);
        }

        viewHolder.mImage1.setOnClickListener(this);
        viewHolder.mImage2.setOnClickListener(this);
        viewHolder.mImage3.setOnClickListener(this);
        return convertView;
    }

    private void viewsetVisibility(ViewHolder viewHolder) {
        viewHolder.mImage1.setVisibility(View.VISIBLE);
        viewHolder.mImage2.setVisibility(View.VISIBLE);
        viewHolder.mImage3.setVisibility(View.VISIBLE);
        viewHolder.name1.setVisibility(View.VISIBLE);
        viewHolder.name2.setVisibility(View.VISIBLE);
        viewHolder.name3.setVisibility(View.VISIBLE);
        viewHolder.priceTitle1.setVisibility(View.VISIBLE);
        viewHolder.priceTitle2.setVisibility(View.VISIBLE);
        viewHolder.priceTitle3.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {

        Offeringsbean offeringsbean = (Offeringsbean) v.getTag();

        if (o != null) {
            o.onClickOfferings(offeringsbean);
        }else{
            showIntroDialog(offeringsbean);
        }



    }


    private static class ViewHolder {
        ImageView mImage1, mImage2, mImage3;
        TextView name1, name2, name3, priceTitle1, priceTitle2, priceTitle3;
    }

    private void showIntroDialog(final Offeringsbean offeringsbean) {
        final Dialog dialog = new Dialog(mContext, R.style.Dialog);
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.dialog_offerings_intro, null);
        TextView intro_name = (TextView) inflate.findViewById(R.id.tv_offerings_intro_name);
        intro_name.setText(offeringsbean.getName());
        TextView intro_content = (TextView) inflate.findViewById(R.id.tv_offerings_intro_content);
        intro_content.setText(mResUtil.getGpContentByName(offeringsbean.getName()));
        BootstrapButton intro_submit = (BootstrapButton) inflate.findViewById(R.id.btn_offerings_intro_submit);
        intro_submit.setText("兑换("+offeringsbean.getIntJiFen()+"积分)");
        intro_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ClickUtil.isFastClick()) return;


//               这里不管有没有物品 都是买东西的 应该判断的是积分
                if (offeringsbean.getIntJiFen()!=0){

                        String userJf = UserManager.getInstance().getUserJf(mContext);
                        Integer uerjifen = Integer.valueOf(userJf);
                        if (offeringsbean.getIntJiFen()>uerjifen){
                            showGoPayDialog();
                            return;
                        }
                }
                if (UserManager.getInstance().isLogin()) {
                  // 买东西
                    PrayingApi.getInstance().qfBuy(UserManager.getInstance().getUser().getToken(), offeringsbean.getName(), offeringsbean.getAmount(), offeringsbean.getJf()).enqueue(new ApiCallBack<ApiResponseBean<Void>>(new BaseCallBack() {
                        @Override
                        public void onSuccess(Object data) {
                           //购买成功
                            offeringsbean.setBuyCountByint(offeringsbean.getAmountInt());
                            UserManager.getInstance().changeUserJF(mContext,-offeringsbean.getIntJiFen());
                            EventBusUtil.post(new EventJiFenBean(UserManager.getInstance().getUserJf(mContext)));
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }

                        @Override
                        public void onError(String errorCode, String message) {
                            boolean b = ErrorCodeUtil.shownetWorkAndServerError(mContext, errorCode);
                            if (b == false){
                                ToastUtil.showShort(mContext,message);
                            }
                            dialog.dismiss();
                        }
                    }));
//                    这里去换成物品
                }else{
                    dialog.dismiss();
                    GotoUtil.GoToActivity(mContext, LoginActivity.class);
                }

            }
        });

        dialog.setContentView(inflate);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        dialog.show();
    }

    private void showGoPayDialog() {
       final   Dialog dialog = new Dialog(mContext, R.style.Dialog);
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.dialog_offerings_gopay, null);
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
        Toast.makeText(mContext, "去买积分", Toast.LENGTH_SHORT).show();
    GotoUtil.GoToActivity(mContext, CreditsIntroActivity.class);
    }


    private int getImageRes(Offeringsbean offeringsbean){
//       在这里拿到图片
        return mResUtil.getGpImageByName(offeringsbean.getName());

    }


    private String getGpContent(Offeringsbean offeringsbean){
        String storeId = offeringsbean.getStoreId();
        int strId = mContext.getResources().getIdentifier("gp_content_" + storeId, "string", mContext.getPackageName());
        String content="";
        if (strId!=0)
         content = mContext.getResources().getString(strId);
        return content;
    }




   public  interface OfferingsOnClickLister{
       public void  onClickOfferings( Offeringsbean offeringsbean);
    }


    OfferingsOnClickLister o;

    public void setOfferingsOnClickLister(OfferingsOnClickLister o) {
        this.o = o;
    }
}
