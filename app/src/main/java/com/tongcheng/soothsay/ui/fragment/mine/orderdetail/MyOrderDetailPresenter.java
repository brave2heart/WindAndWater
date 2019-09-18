package com.tongcheng.soothsay.ui.fragment.mine.orderdetail;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.tongcheng.soothsay.bean.classroom.AddressBean;
import com.tongcheng.soothsay.bean.mine.OrderDetail;
import com.tongcheng.soothsay.bean.pay.OrdersRequestBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.data.classroom.GoodsImp;
import com.tongcheng.soothsay.data.classroom.IGoods;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.ui.activity.pay.PayActivity;

/**
 * Created by Administrator on 2016/12/30.
 */

public class MyOrderDetailPresenter implements MyOrderConstant.Presenter{
    private final double JIFEN_SCALE = 100;        //积分与人民币的比例

    private double money = 0;           //实际要付款的金额
    private double offsetMoney = 0;     //使用积分可抵扣价格
    private int canUseJifen = 0;         //商品可用积分

    private boolean boxState;           //checkbox状态

    private String ordersName = "开光圣品";
    private String transType = "SC";

    private MyOrderConstant.View mView;
    private IGoods mData;

    public MyOrderDetailPresenter(MyOrderConstant.View mView) {
        this.mView = mView;
        mData = new GoodsImp();
        mView.setPresenter(this);
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

    //修改积分
    @Override
    public void changeJifen(Activity activity) {
        String jifen = UserManager.getInstance().getUserJf(activity);
        int temp = (int) (Double.valueOf(jifen) - canUseJifen);
        UserManager.getInstance().setUserJf(activity,temp+"");
    }

    /*private String parseToJson(AddressBean addr,OrderDetail orderDetail){

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
    }*/




    @Override
    public void start() {}
}
