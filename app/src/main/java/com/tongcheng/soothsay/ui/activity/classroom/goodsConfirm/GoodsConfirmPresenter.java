package com.tongcheng.soothsay.ui.activity.classroom.goodsConfirm;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.tongcheng.soothsay.bean.classroom.AddressBean;
import com.tongcheng.soothsay.bean.classroom.ShopCartBean;
import com.tongcheng.soothsay.bean.pay.OrdersRequestBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.data.classroom.GoodsImp;
import com.tongcheng.soothsay.data.classroom.IGoods;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.ui.activity.classroom.AddAddressActivity;
import com.tongcheng.soothsay.ui.activity.classroom.SelectAddressActivity;
import com.tongcheng.soothsay.ui.activity.pay.PayActivity;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;
import com.tongcheng.soothsay.utils.LogUtil;
import com.tongcheng.soothsay.utils.ToastUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Steven on 16/12/26.
 */
public class GoodsConfirmPresenter implements GoodsConfirmConstant.Presenter{

    private final double JIFEN_SCALE = 100;        //积分与人民币的比例

    private double money = 0;           //实际要付款的金额
    private double offsetMoney = 0;     //使用积分可抵扣价格
    private int canUseJifen = 0;         //商品可用积分

    private boolean boxState;           //checkbox状态

    private String ordersName = "开光圣品";
    private String transType = "SC";

    private GoodsConfirmConstant.View mView;
    private IGoods mData;

    public GoodsConfirmPresenter(GoodsConfirmConstant.View mView) {
        this.mView = mView;
        mData = new GoodsImp();
        mView.setPresenter(this);
    }

    @Override
    public void getAddressList(String token) {
        mView.showLoad();
        mData.getAddressList(token,new ApiCallBack<ApiResponseBean<List<AddressBean>>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                mView.showLoadFinish();
                mView.showGoodsList();
                List<AddressBean> beans = (List<AddressBean>) data;

                if(beans != null && beans.size() != 0){
                    mView.showAddress(beans);
                }else{
                    mView.showEmpty();
                }
            }

            @Override
            public void onError(String errorCode, String message) {
                ErrorCodeUtil.handleErr(mView,errorCode,message);
            }
        }));
    }

    /**
     *  计算可以用积分抵消多少价格
     *  @param jifen   用户的积分
     *  @param canUseJifen  可以用的总积分
     *  @param totalPrice   商品总价
     *  @return 实际要支付价格
     */
    @Override
    public void calculateMoney(int jifen, int canUseJifen, double totalPrice) {
        this.canUseJifen = canUseJifen;
        this.money = totalPrice;

        boolean flag = false;
        //积分不足 不可以使用
        if(jifen <= 0){
            flag = false;
        }else{
            //积分要大于可以使用的积分 并且 要能整除 100
            if(jifen < canUseJifen && jifen % JIFEN_SCALE != 0){
                flag = false;
            }else{
                flag = true;
            }
        }

        //可以使用积分
        if(flag){
            //积分抵消的价格
            offsetMoney = canUseJifen / JIFEN_SCALE;
            flag = totalPrice < offsetMoney ? false : true ;
            mView.calculateMoney(flag,canUseJifen,offsetMoney);
            return ;
        }
        mView.calculateMoney(flag,canUseJifen,offsetMoney);
    }


    //使用积分抵扣
    @Override
    public void useJifen(boolean checked){
        boxState = checked;
        if(checked) {
            money -= offsetMoney;
        }else{
            money += offsetMoney;
        }
        mView.useJifen(money);
    }


    //去选择地址
    @Override
    public void gotoAddress(boolean isDefult,List<AddressBean> beans, Activity activity) {
        //没有收货地址
        if(beans == null ){
            Intent addIntent = new Intent(activity,AddAddressActivity.class);
            addIntent.putExtra(GoodsConfirmConstant.INTENT_ISDEFULT,isDefult);
            activity.startActivityForResult(addIntent,101);
            //选择收货地址
        }else{
            Intent selectIntent = new Intent(activity,SelectAddressActivity.class);
            ArrayList<AddressBean> temp = new ArrayList<>();
            temp.addAll(beans);
            selectIntent.putExtra(Constant.INTENT_DATA,temp);
            activity.startActivityForResult(selectIntent,101);

        }
    }


    //立即付款
    @Override
    public void payForNow(Activity activity,AddressBean addr, List<ShopCartBean> goods) {
        String token = UserManager.getInstance().getToken();
        if (TextUtils.isEmpty(token)) {
            mView.showTokenOverdue();
            return ;
        }

        if(addr == null){
            ToastUtil.showShort(activity,"请选择收货地址");
            return;
        }

        String goodsName = this.ordersName;
        String transType = this.transType;
        String totalPrice = money * 100 + "";
        String extra = parseToJson(addr,goods);

        OrdersRequestBean request = new OrdersRequestBean();
        request.setToken(token);
        request.setTotalPrice(totalPrice);
        request.setTransType(transType);
        request.setGoodsName(goodsName);
        request.setExtra(extra);

        Intent intent = new Intent(activity, PayActivity.class);
        intent.putExtra(Constant.INTENT_DATA,request);
        activity.startActivityForResult(intent,202);

    }

    //修改积分
    @Override
    public void changeJifen(Activity activity) {
        String jifen = UserManager.getInstance().getUserJf(activity);
        int temp = (int) (Double.valueOf(jifen) - canUseJifen);
        UserManager.getInstance().setUserJf(activity,temp+"");
    }

    private String parseToJson(AddressBean addr,List<ShopCartBean> goods){

        try {
            JSONObject bigObj = new JSONObject();
            JSONArray jArray = new JSONArray();
            for(ShopCartBean bean : goods){
                JSONObject jObj = new JSONObject();
                jObj.put("merchandiseId",bean.getMerchandiseId());
                jObj.put("amount",bean.getPayCount());

                jArray.put(jObj);
            }

            String useJf = boxState ? "1" : "0";        //0：不使用；1：使用积分
            LogUtil.i("   useJf:  " + useJf);

            bigObj.put("goods",jArray);
            bigObj.put("addressId",addr.getId());
            bigObj.put("jf",canUseJifen);
            bigObj.put("isUserJf",useJf);

            return bigObj.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }




    @Override
    public void start() {}


}
