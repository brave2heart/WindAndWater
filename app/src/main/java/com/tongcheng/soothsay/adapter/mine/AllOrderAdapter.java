package com.tongcheng.soothsay.adapter.mine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.AbTypeBaseAdapter;
import com.tongcheng.soothsay.bean.calculation.WishingOrder;
import com.tongcheng.soothsay.bean.mine.AllOrder;
import com.tongcheng.soothsay.bean.pay.OrderRePayBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.AllApi;
import com.tongcheng.soothsay.http.service.WishingSquareApi;
import com.tongcheng.soothsay.ui.activity.mine.OrderDetailActivity;
import com.tongcheng.soothsay.ui.activity.pay.PayActivity;
import com.tongcheng.soothsay.utils.DateUtil;
import com.tongcheng.soothsay.utils.DialogUtil;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;
import com.living.utils.GotoUtil;
import com.tongcheng.soothsay.utils.LogUtil;
import com.tongcheng.soothsay.utils.ToastUtil;
import com.tongcheng.soothsay.widget.RoundImageView;
import com.wevey.selector.dialog.DialogOnClickListener;
import com.wevey.selector.dialog.MDAlertDialog;



import java.util.HashMap;
import java.util.List;



/**
 * Created by ALing on 2016/12/26
 */

public class AllOrderAdapter extends AbTypeBaseAdapter<AllOrder> {
    private Activity mContext;
    private List<AllOrder> datas;
    private HashMap<String,String> map;
    private MDAlertDialog dialog;
    private int status;
    String transType,orderStatus,orderNo,money,storeName,createTime;

    private RoundImageView iv_goods;
    private TextView
            tv_goods_name,tv_create_time,tv_price,tv_del_order,tv_buy_now,tv_had_buy,tv_deliver_goods,tv_goods_receipt,tv_confirm_goods,tv_detail;

    private String token = UserManager.getInstance().getToken();
    private String mOrderNo,mtransType;
    private AllOrder tag;

    public AllOrderAdapter(Context context, List<AllOrder> datas,int status, int... itemLayoutId) {
        super(context, datas, itemLayoutId);
        this.mContext = (Activity) context;
        this.status = status;
    }


    @Override
    public int getItemType(final int position) {
        if (status == 0){

            return 0;
        }else{

            return 1;
        }
    }

    @Override
    public void bindData(final ViewHolder holder, int type, final AllOrder allOrder) {
        holder.setTag(allOrder);
        //第一种布局
        if(type == 0){
            findView(holder,type,allOrder);
            initData(allOrder);
            setGoodsImg();  //设置图标

            tv_del_order.setVisibility(View.VISIBLE);
            tv_buy_now.setVisibility(View.VISIBLE);
            tv_had_buy.setVisibility(View.GONE);
            tv_deliver_goods.setVisibility(View.GONE);
            tv_goods_receipt.setVisibility(View.GONE);
//          删除订单
            tv_del_order.setTag(allOrder);
            LogUtil.i(" ======= " + allOrder.getOrderNo());
            tv_del_order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tag = (AllOrder) v.getTag();
                    mOrderNo = tag.getOrderNo();
                    mtransType = tag.getTransType();
                    showDeletDialog(mtransType,tag);
                }
            });

//            马上购买
            tv_buy_now.setTag(allOrder);
            tv_buy_now.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tag = (AllOrder) v.getTag();
                    OrderRePayBean rePayBean = new OrderRePayBean();
                    rePayBean.setGoodsname(tag.getStoreName());
                    rePayBean.setGoodsprice(tag.getMoney());
                    rePayBean.setToken(token);
                    rePayBean.setOrderNo(tag.getOrderNo());
                    Intent intent = new Intent(mContext, PayActivity.class);
                    intent.putExtra(Constant.REPAY,rePayBean);
                    mContext.startActivityForResult(intent,4);
//                    ToastUtil.showShort(mContext,"马上购买：position:"+",,,"+tag.getOrderNo());
                    for (int i = 0; i < getDatas().size(); i++) {
                        AllOrder tag = getDatas().get(i);
                        if (tag.equals(allOrder)){
                            getDatas().get(i).setOrderStatus("1");
                            notifyDataSetChanged(datas);
                        }
                    }
                }
            });

            if (transType.equals("SC")) {
                tv_detail.setVisibility(View.VISIBLE);
            }else {
                tv_detail.setVisibility(View.GONE);
            }


//                   查看详情
            tv_detail.setTag(allOrder);
            tv_detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tag = (AllOrder) v.getTag();
//                    Log.e("detail", "查看详情订单号 :"+"position:"+position+"====="+orderNo );
                    map = new HashMap<String, String>();
                    map.put("status",String.valueOf(status));
                    map.put("token",token);
                    map.put("orderNo",tag.getOrderNo());
                    map.put("storeName",tag.getStoreName());
                    map.put("price",tag.getMoney());
                    GotoUtil.GoToActivityWithData(mContext,OrderDetailActivity.class,map);
                }
            });

            //第二种布局
        }else{
            findView(holder,type,allOrder);
            initData(allOrder);
            setGoodsImg();  //设置图标

            tv_del_order.setVisibility(View.GONE);
            tv_buy_now.setVisibility(View.GONE);
            tv_had_buy.setVisibility(View.VISIBLE);
            if ("SC".equals(transType)){
                tv_detail.setVisibility(View.VISIBLE);
                /*tv_deliver_goods.setVisibility(View.VISIBLE);
                tv_goods_receipt.setVisibility(View.VISIBLE);
                tv_confirm_goods.setVisibility(View.GONE);*/
                if (orderStatus.equals("2")){       //待发货
                    tv_deliver_goods.setVisibility(View.VISIBLE);
                    tv_goods_receipt.setVisibility(View.GONE);
                    tv_confirm_goods.setVisibility(View.GONE);
                }else if (orderStatus.equals("3")){     //待收货
                    tv_deliver_goods.setVisibility(View.GONE);
                    tv_goods_receipt.setVisibility(View.VISIBLE);
                    tv_confirm_goods.setVisibility(View.VISIBLE);
                }else if (orderStatus.equals("5")){     //已收货
                    tv_deliver_goods.setVisibility(View.GONE);
                    tv_goods_receipt.setVisibility(View.GONE);
                    tv_confirm_goods.setVisibility(View.GONE);
                    tv_had_buy.setVisibility(View.VISIBLE);
                }else {
                    tv_confirm_goods.setVisibility(View.GONE);

                }

                //                   查看详情
                tv_detail.setTag(allOrder);
                tv_detail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tag = (AllOrder) v.getTag();
//                        ToastUtil.showShort(mContext,"查看详情====="+tag.getOrderNo());
                        map = new HashMap<String, String>();
                        map.put("status",String.valueOf(status));
                        map.put("token",token);
                        map.put("orderNo",tag.getOrderNo());
                        map.put("storeName",tag.getStoreName());
                        map.put("price",tag.getMoney());
                        GotoUtil.GoToActivityWithData(mContext,OrderDetailActivity.class,map);
                    }
                });
//          确认收货
                tv_confirm_goods.setTag(allOrder);
                tv_confirm_goods.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AllOrder tag = (AllOrder) v.getTag();
                        Log.e("No", "onClick: NO:"+tag.getOrderNo() );
                        mOrderNo = tag.getOrderNo();
                        map = new HashMap<String, String>();
                        map.put("token",token);
                        map.put("orderNo",mOrderNo);
                        confirmOrder(map,allOrder,tv_deliver_goods,tv_confirm_goods,tv_had_buy);   //确认收货成功，按钮变成 已购买

                    }
                });
            }else {
                tv_detail.setVisibility(View.GONE);
                tv_deliver_goods.setVisibility(View.GONE);
                tv_goods_receipt.setVisibility(View.GONE);
                tv_confirm_goods.setVisibility(View.GONE);
                tv_had_buy.setVisibility(View.VISIBLE);
            }
        }
    }

    public void findView(ViewHolder holder,int type, AllOrder allOrder){
        iv_goods = (RoundImageView) holder.getView(R.id.iv_goods_img);  //商品图片
        tv_goods_name = (TextView) holder.getView(R.id.tv_goods_name); //商品名称
        tv_create_time = (TextView) holder.getView(R.id.tv_create_time); //创建时间
        tv_price = (TextView) holder.getView(R.id.tv_goods_price); //商品价格

        tv_del_order = (TextView) holder.getView(R.id.tv_del_order); //删除订单
        tv_buy_now = (TextView) holder.getView(R.id.tv_buy_now); //马上购买
        tv_had_buy = (TextView) holder.getView(R.id.tv_had_buy);//已经购买
        tv_deliver_goods = (TextView) holder.getView(R.id.tv_deliver_goods); //待发货
        tv_goods_receipt = (TextView) holder.getView(R.id.tv_goods_receipt); //待收货
        tv_confirm_goods = (TextView) holder.getView(R.id.tv_confirm_goods); //确认收货

        tv_detail = (TextView) holder.getView(R.id.tv_detail); //查看详情

    }
    public void initData(AllOrder allOrder){
        transType = allOrder.getTransType();  //商品类型，虚拟商品，还是商城 "LF":"灵符""SC":"商城""XYD":"许愿灯""FSC":"放生池""JFDH":"积分兑换"
        orderStatus = allOrder.getOrderStatus();  //订单状态：当transType= SC时数据才有效。
        orderNo = allOrder.getOrderNo(); //订单号
        money = allOrder.getMoney();
        storeName = allOrder.getStoreName();
        createTime = allOrder.getCreateTime();
        String formatTime = DateUtil.formatTime(Long.valueOf(createTime), "yyyy/MM/dd HH:mm");

        tv_goods_name.setText(storeName);
        tv_create_time.setText(formatTime);
        tv_price.setText("价格："+money);
    }

    private void setGoodsImg(){
        if (transType != null){
            switch (transType){
                case "LF":
                    iv_goods.setImageResource(R.drawable.sc_icon_fu);
                    break;
                case "SC":
                    iv_goods.setImageResource(R.drawable.sc_icon_sc);
                    break;
                case "XYD":
                    iv_goods.setImageResource(R.drawable.sc_icon_xyd);
                    break;
                case "FSC":
                    iv_goods.setImageResource(R.drawable.sc_icon_fsc);
                    break;
                case "JFDH":
                    iv_goods.setImageResource(R.drawable.sc_icon_jf);
                    break;
            }
        }
    }



    //显示删除对话框
    public void showDeletDialog(final String transType, final AllOrder tag){
        dialog = DialogUtil.createDialog(getContext(), "确认删除","","取消", "删除",
                new DialogOnClickListener() {

                    @Override
                    public void clickLeftButton(View view) {
                        dialog.dismiss();
                        dialog = null;
                    }

                    @Override
                    public void clickRightButton(View view) {
                        map = new HashMap<>();
                        map.put("token", token);
                        map.put("transType",tag.getTransType());
                        map.put("orderNo",tag.getOrderNo());
                        Log.e("tag", "clickRightButton: "+  UserManager.getInstance().getToken()+"==="+tag.getOrderNo());
//                      删除订单
                        deleteOrder(map,tag);
                        dialog.dismiss();
                        dialog = null;
                    }
                });
        dialog.show();
    }
    //      删除订单
    private void deleteOrder(final HashMap<String, String> map, final AllOrder tag) {
        WishingSquareApi.getInstance().getDeleteOrder(map).enqueue(new ApiCallBack<ApiResponseBean<WishingOrder>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                getDatas().remove(tag);
                notifyDataSetChanged();
                ToastUtil.showShort(mContext,"删除成功");
            }

            @Override
            public void onError(String errorCode, String message) {
                boolean b = ErrorCodeUtil.showHaveTokenError(mContext, errorCode);
                if (b == false){
                    ToastUtil.showShort(getContext(),message);
                }
            }
        }));
    }
    //              确认收货
    private void confirmOrder(final HashMap<String, String> map, final AllOrder tag, final TextView tv_deliver_goods, final TextView tv_confirm_goods, final
    TextView tv_had_buy) {
        AllApi.getInstance().getConfirmOrder(map).enqueue(new ApiCallBack<ApiResponseBean<Object>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                for (int i = 0; i < getDatas().size(); i++) {
                    AllOrder temp = getDatas().get(i);
                    if (temp.equals(tag)){
                        getDatas().get(i).setOrderStatus("5");
                        tv_had_buy.setVisibility(View.VISIBLE);
                        tv_deliver_goods.setVisibility(View.GONE);
                        tv_confirm_goods.setVisibility(View.GONE);
                        notifyDataSetChanged();
                    }
                }

                ToastUtil.showShort(mContext,"确认成功");

            }

            @Override
            public void onError(String errorCode, String message) {
                boolean b = ErrorCodeUtil.showHaveTokenError(mContext, errorCode);
                if (b == false){
                    ToastUtil.showShort(getContext(),message);
                }
            }
        }));
    }
}