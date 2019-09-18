package com.tongcheng.soothsay.adapter.calculation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseRecyclerAdapter;
import com.tongcheng.soothsay.bean.calculation.WishingSquare;
import com.tongcheng.soothsay.bean.mine.AllOrder;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.WishingSquareApi;
import com.tongcheng.soothsay.ui.activity.calculate.life.MyWishingActivity;
import com.tongcheng.soothsay.ui.activity.mine.LoginActivity;
import com.tongcheng.soothsay.ui.fragment.calculation.life.wishingsquare.WishingSquareConstant;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;
import com.tongcheng.soothsay.utils.LogUtil;
import com.tongcheng.soothsay.utils.ToastUtil;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ALing on 2016/12/8 0008.
 * 许愿广场适配器
 */

public class WishingSquareAdapter extends BaseRecyclerAdapter<WishingSquare>{
    private Activity mContext;
    private String token;
    private TextView tv_wishing_count;
    private Button btn_clifford;
    private int index;
    List<WishingSquare> list;
    private HashMap<String,String> map;
    public WishingSquareAdapter(Activity context, List<WishingSquare> list, int itemLayoutId) {
        super(context, list, itemLayoutId);
        this.mContext = context;
        this.list = list;
    }

    @Override
    public void bindData(ViewHolder holder, final int position, final WishingSquare wishingSquare) {
        index = position;
        ImageView iv_lightType = (ImageView) holder.getView(R.id.iv_light_type);
        TextView tv_lightType = (TextView) holder.getView(R.id.tv_light_type);
        TextView tv_name = (TextView) holder.getView(R.id.tv_name);
        TextView tv_date = (TextView) holder.getView(R.id.tv_date);
        TextView tv_wishing_content = (TextView) holder.getView(R.id.tv_wishing_content);
        tv_wishing_count = (TextView) holder.getView(R.id.tv_wishing_count);
        btn_clifford = (Button) holder.getView(R.id.btn_clifford);

        //通过灯的名字找到索引
        final String[] lightType = getContext().getResources().getStringArray(R.array.light_typeArr);
        final int index = WishingSquareConstant.findIndex(lightType, wishingSquare.getXydName());
        iv_lightType.setImageResource(WishingSquareConstant.xyddArr[index]);


        if (wishingSquare != null){
//            iv_lightType.setImageResource(lightImgArr[position]);
            tv_lightType.setText(wishingSquare.getXydName());
            tv_name.setText("信女："+wishingSquare.getUsername());
            tv_date.setText(wishingSquare.getBornDate());
            tv_wishing_content.setText("\t\t\t"+wishingSquare.getContent());
            tv_wishing_count.setText(wishingSquare.getBlessingCount());

            btn_clifford.setTag(wishingSquare);
            btn_clifford.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ClickUtil.isFastClick(v)) return;

                    if (!UserManager.getInstance().isLogin()){
//                        getContext().startActivity(new Intent(getContext(),LoginActivity.class));
                        login();
                    }else {
                        final WishingSquare tag = (WishingSquare) v.getTag();
                        token = UserManager.getInstance().getToken();
                        map = new HashMap<String, String>();
                        map.put("token",token);
                        map.put("orderNo",tag.getOrderNo());

                        WishingSquareApi.getInstance().getClifford(map).enqueue(new ApiCallBack<ApiResponseBean<WishingSquare>>(new BaseCallBack() {
                            @Override
                            public void onSuccess(Object data) {

                                list.get(position).setBlessingCount((Integer.valueOf(tag.getBlessingCount())+1)+"");
                                if (tag.getBlessingCount().equals(list.get(position).getBlessingCount())){
                                    ToastUtil.showShort(mContext,"好人一生平安");
                                    notifyDataSetChanged();
                                }

                            }

                            @Override
                            public void onError(String errorCode, String message) {
                                if (ApiCallBack.NET_ERROR.equals(errorCode)){
                                    boolean b = ErrorCodeUtil.shownetWorkAndServerError(mContext, errorCode);
                                    if (b == false){
                                        ToastUtil.showShort(mContext,message);
                                    }
                                }
                                if ("10077".equals(errorCode)){
                                    ToastUtil.showShort(getContext(),message);
                                }else {
                                    boolean b = ErrorCodeUtil.showHaveTokenError(mContext, message);
                                    if (b == false){
                                        ToastUtil.showShort(getContext(),message);
                                    }
                                }
                            }
                        }));
                    }
                }
            });
        }
    }

    private void login() {
        Intent intent = new Intent(mContext, LoginActivity.class);
        mContext.startActivityForResult(intent, 1);
    }
}
