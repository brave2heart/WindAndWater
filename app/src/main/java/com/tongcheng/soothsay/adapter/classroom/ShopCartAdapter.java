package com.tongcheng.soothsay.adapter.classroom;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseRecyclerAdapter;
import com.tongcheng.soothsay.bean.classroom.ShopCartBean;
import com.tongcheng.soothsay.bean.dao.GoodsBean;
import com.tongcheng.soothsay.bean.event.ChangePricebean;
import com.tongcheng.soothsay.helper.ImageHelper;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.EventBusUtil;
import com.tongcheng.soothsay.utils.GoodsIdUtils;

import java.util.List;

/**
 * Created by 宋家任 on 2016/12/26.
 * 购物车适配器
 */

public class ShopCartAdapter extends BaseRecyclerAdapter<ShopCartBean> {
    private OnDelItemListener del;

    public ShopCartAdapter(Context context, List<ShopCartBean> list, int itemLayoutId) {
        super(context, list, itemLayoutId);
    }

//    double price;//单价
//    double totalPrice;//总价

    @Override
    public void bindData(final ViewHolder holder, final int position, final ShopCartBean shopCartBean) {
        final GoodsBean goodsBean = GoodsIdUtils.getInstance().getGoodsBean(shopCartBean.getMerchandiseId());
        //图片
        ImageView iv = (ImageView) holder.getView(R.id.iv_cart_goods);
//        ImageHelper.getInstance().display(shopCartBean.getFacePic(), iv);
        ImageHelper.getInstance().display(shopCartBean.getFacePic(), R.drawable.placeholder_sc_340, iv);
        //标题
        TextView tvName = (TextView) holder.getView(R.id.tv_goods_name);
        tvName.setText(shopCartBean.getName());
        //描述
        TextView tvattr = (TextView) holder.getView(R.id.tv_goods_attr);
        tvattr.setText(shopCartBean.getYinyu());
        //单价
        TextView tvPrice = (TextView) holder.getView(R.id.tv_goods_price);
        tvPrice.setText("¥ " + shopCartBean.getPrice());


        //是否选中
        final CheckBox cb = (CheckBox) holder.getView(R.id.cb_goods);
        cb.setTag(shopCartBean);
        cb.setChecked(shopCartBean.isCheck());
        if (goodsBean != null && !"".equals(goodsBean.getAmount())) {
            shopCartBean.setPayCount(Integer.valueOf(goodsBean.getAmount()));
            shopCartBean.setTotalPrice(Double.valueOf(shopCartBean.getPrice()) * Integer.valueOf(shopCartBean.getShopCartCount()));
        }

        //选中监听
        cb.setOnClickListener(new View.OnClickListener() {
            double totalPrice = 0.0d;
            double price = 0.0d;
            int num;

            @Override
            public void onClick(View v) {
                //                    price = Double.valueOf(shopCartBean.getPrice());
//                    num = Integer.valueOf(shopCartBean.getShopCartCount());
//                    totalPrice = price * num;
//                    shopCartBean.setTotalPrice(totalPrice);
//                    EventBusUtil.post(new ChangePricebean());
                if (shopCartBean.isCheck()) {
                    shopCartBean.setCheck(false);
                    EventBusUtil.post(new ChangePricebean(-1));
                } else {
                    shopCartBean.setCheck(true);
                    EventBusUtil.post(new ChangePricebean());
                }
//                onChecked.checkGroup(position, !shopCartBean.isCheck());
            }
        });

        //删除条目
        ImageView ivDel = (ImageView) holder.getView(R.id.iv_cart_del);
        ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                del.onDel(holder, position, goodsBean);
                if (cb.isChecked())//勾选了才更新价格
                    EventBusUtil.post(new ChangePricebean());
            }
        });

        double price = Double.valueOf(shopCartBean.getPrice());
        int num = Integer.valueOf(shopCartBean.getShopCartCount());
        double totalPrice = price * num;
        shopCartBean.setTotalPrice(totalPrice);

        //数量
        final TextView tvNum = (TextView) holder.getView(R.id.tv_cart_shop_num);
//        tvNum.setText(goodsBean.getAmount());
        tvNum.setText(shopCartBean.getShopCartCount());
        tvNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //减
        TextView tvReduce = (TextView) holder.getView(R.id.tv_reduce);
        tvReduce.setTag(shopCartBean);
        tvReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopCartBean bean = (ShopCartBean) v.getTag();
                int num = Integer.valueOf(bean.getShopCartCount());
                double price = Double.valueOf(shopCartBean.getPrice());
                double totalPrice = price * num;
                if (num > 1) {//大于1才能减
                    num--;
                    goodsBean.setAmount(String.valueOf(num));
                    GoodsIdUtils.getInstance().updataGoods(goodsBean);
                    shopCartBean.setShopCartCount(String.valueOf(num));
                    tvNum.setText(shopCartBean.getShopCartCount());
                    num = Integer.valueOf(shopCartBean.getShopCartCount());
                    bean.setPayCount(num);
                    totalPrice = price * num;
                    shopCartBean.setTotalPrice(totalPrice);
                    if (cb.isChecked())//勾选了才发请求
                        EventBusUtil.post(new ChangePricebean());
                }
            }
        });
        TextView tvAdd = (TextView) holder.getView(R.id.tv_add);
        tvAdd.setTag(shopCartBean);
        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (cb.isChecked()) {
                ShopCartBean bean = (ShopCartBean) v.getTag();
                int num = Integer.valueOf(bean.getShopCartCount());
                double price = Double.valueOf(shopCartBean.getPrice());
                double totalPrice = price * num;
                num++;
                goodsBean.setAmount(String.valueOf(num));
                GoodsIdUtils.getInstance().updataGoods(goodsBean);
                shopCartBean.setShopCartCount(String.valueOf(num));
                tvNum.setText(shopCartBean.getShopCartCount());
                num = Integer.valueOf(shopCartBean.getShopCartCount());
                bean.setPayCount(num);
                totalPrice = price * num;
                shopCartBean.setTotalPrice(totalPrice);
                if (cb.isChecked())
                    EventBusUtil.post(new ChangePricebean());
//                }
            }
        });
    }

    public void setOnDelItemListener(OnDelItemListener itemDel) {
        this.del = itemDel;
    }


    //删除条目监听
    public interface OnDelItemListener {
        void onDel(ViewHolder holder, int position, GoodsBean bean);
    }


}
