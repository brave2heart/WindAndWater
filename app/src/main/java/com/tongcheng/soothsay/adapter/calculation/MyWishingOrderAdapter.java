package com.tongcheng.soothsay.adapter.calculation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseRecyclerAdapter;
import com.tongcheng.soothsay.bean.calculation.WishingOrder;
import com.tongcheng.soothsay.bean.pay.OrderRePayBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.WishingSquareApi;
import com.tongcheng.soothsay.ui.activity.pay.PayActivity;
import com.tongcheng.soothsay.ui.fragment.calculation.life.wishingsquare.WishingSquareConstant;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.DialogUtil;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;
import com.tongcheng.soothsay.utils.ToastUtil;
import com.wevey.selector.dialog.DialogOnClickListener;
import com.wevey.selector.dialog.MDAlertDialog;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ALing on 2016/12/6 0006.
 * 我的许愿灯列表适配器
 */

public class MyWishingOrderAdapter extends BaseRecyclerAdapter<WishingOrder> {
    private Activity mContext;
    private MDAlertDialog dialog;
    private int mIndex;
    private WishingOrder tag,orderBean;
    private List<WishingOrder> mDatas;
    public MyWishingOrderAdapter(Context context, List<WishingOrder> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
        this.mContext = (Activity) context;
        this.mDatas = mDatas;
    }

    @Override
    public void bindData(final ViewHolder holder, final int position, final WishingOrder wishingOrder) {
        mIndex = position;
        orderBean = mDatas.get(position);
        ImageView iv_lightType = (ImageView) holder.getView(R.id.iv_light_type); //灯图片
        ImageView iv_had_light = (ImageView) holder.getView(R.id.iv_had_light);  //已点亮图片
        ImageView iv_delete = (ImageView) holder.getView(R.id.iv_delete);  //删除图片
        ((TextView) holder.getView(R.id.tv_light_type)).setText(wishingOrder.getXydName()); //许愿灯名称
        TextView tv_order = (TextView) holder.getView(R.id.tv_order_number);
        tv_order.setText("订单号："+wishingOrder.getOrderNo());
        tv_order.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        ((TextView) holder.getView(R.id.tv_name)).setText("点灯者姓名："+wishingOrder.getUsername());
        TextView tv_sex = (TextView) holder.getView(R.id.tv_sex);
        if (wishingOrder.getSex().equals("1")){
            tv_sex.setText("性别：男");
        }else {
            tv_sex.setText("性别：女");
        }
        ((TextView) holder.getView(R.id.tv_date)).setText("出生日期："+wishingOrder.getBornDate());
        TextView tv_deadline = (TextView) holder.getView(R.id.tv_deadline);
        if (wishingOrder.getExpiryType().equals("0")){
            tv_deadline.setText("点灯期限：90天");
        }else {
            tv_deadline.setText("点灯期限：180天");
        }
        ((TextView) holder.getView(R.id.tv_my_wishing)).setText("我的心愿："+wishingOrder.getContent());
        ((TextView) holder.getView(R.id.tv_light_count)).setText(""+wishingOrder.getBlessingCount());


        String[] lightType = mContext.getResources().getStringArray(R.array.light_typeArr);
        final int index = WishingSquareConstant.findIndex(lightType, wishingOrder.getXydName());
        iv_lightType.setImageResource(WishingSquareConstant.xyddArr[index]);

        final Button btn_light = (Button) holder.getView(R.id.btn_light);   //点亮按钮
        RelativeLayout rl_count = (RelativeLayout) holder.getView(R.id.rl_count);
        String status = wishingOrder.getStatus();//订单状态
        if (status.equals("0")){ //未支付
            Log.e("tag", "bindData: 订单状态"+status );
            btn_light.setVisibility(View.VISIBLE);
            iv_delete.setVisibility(View.VISIBLE);
            iv_had_light.setVisibility(View.GONE);
            rl_count.setVisibility(View.GONE);
            btn_light.setTag(wishingOrder);
            btn_light.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (ClickUtil.isFastClick()) return;
                    tag = (WishingOrder) v.getTag();
                    rePayOrder(tag);
                    tag.setStatus("1");

//                    paySuccess(tag,wishingOrder,position);


//                    跳转到许愿点灯页面
                    /*HashMap<String,String> map = new HashMap<String, String>();
                    map.put("xydName",wishingOrder.getXydName());
                    map.put("userName",wishingOrder.getUsername());
                    map.put("sex",wishingOrder.getSex());
                    map.put("bornDate",wishingOrder.getBornDate());
                    map.put("content",wishingOrder.getContent());
                    map.put("expiryType",wishingOrder.getExpiryType());
                    map.put("orderNo",wishingOrder.getOrderNo());
                    map.put("price",wishingOrder.getMoney());
                    map.put("from","MyWishingOrderAdapter");
                    map.put("index",String.valueOf(index));
                    GotoUtil.GoToActivityWithData(mContext, MakeAWishActivity.class,map);*/
                    /*for (int i = 0; i < getList().size(); i++) {
                        WishingOrder tag = getList().get(i);
                        if (tag.equals(wishingOrder)){
                            getList().get(i).setStatus("1");
                            notifyDataSetChanged();
                        }
                    }*/
                }

            });

        }else{
            btn_light.setVisibility(View.GONE);
            iv_delete.setVisibility(View.GONE);
            iv_had_light.setVisibility(View.VISIBLE);
            rl_count.setVisibility(View.VISIBLE);

        }

        //            删除订单
        iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ClickUtil.isFastClick()) return;

                showDeletDialog(holder,wishingOrder);
            }
        });
    }
    public void paySuccess() {
        List<WishingOrder> list = getList();
        for (int i = 0;i < list.size();i++){
            WishingOrder wishingOrder = list.get(i);
            if (tag.getStatus().equals(wishingOrder.getStatus())){
                notifyDataSetChanged();
            }
        }
        /*if (tag.getStatus().equals(wishingOrder.getStatus())){
            tag.setStatus("1");
            notifyDataSetChanged();
        }*/
    }
    private void rePayOrder(WishingOrder wishingOrder) {
        OrderRePayBean rePayBean = new OrderRePayBean();
        rePayBean.setGoodsname(wishingOrder.getXydName());
        rePayBean.setGoodsprice(wishingOrder.getMoney());
        rePayBean.setToken(UserManager.getInstance().getToken());
        rePayBean.setOrderNo(wishingOrder.getOrderNo());
        Intent intent = new Intent(mContext, PayActivity.class);
        intent.putExtra(Constant.REPAY,rePayBean);
        mContext.startActivityForResult(intent,4);

    }

    //显示删除对话框
    public void showDeletDialog(final ViewHolder holder, final WishingOrder wishingOrder){
        dialog = DialogUtil.createDialog(getContext(), "确认删除","删除后可在许愿点灯继续点灯","取消", "删除",
                new DialogOnClickListener() {

                    @Override
                    public void clickLeftButton(View view) {
                        dialog.dismiss();
                        dialog = null;
                    }

                    @Override
                    public void clickRightButton(View view) {
                        HashMap<String,String> map = new HashMap<>();
                        map.put("token", UserManager.getInstance().getToken());
                        map.put("transType","XYD");
                        map.put("orderNo",wishingOrder.getOrderNo());
                        Log.e("tag", "clickRightButton: "+  UserManager.getInstance().getToken()+"==="+wishingOrder.getOrderNo());
//                      删除订单
                        deleteOrder(holder,map,mIndex);
                        dialog.dismiss();
                        dialog = null;
                    }
                });
        dialog.show();
    }

    private void deleteOrder(final ViewHolder holder, final HashMap<String,String> map, final int index) {
        WishingSquareApi.getInstance().getDeleteOrder(map).enqueue(new ApiCallBack<ApiResponseBean<WishingOrder>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                    removeData(holder,index);
                    ToastUtil.showShort(mContext,"删除成功");
            }

            @Override
            public void onError(String errorCode, String message) {
                ErrorCodeUtil.showHaveTokenError(mContext, errorCode);
            }
        }));
    }
}
