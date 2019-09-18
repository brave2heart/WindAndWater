package com.tongcheng.soothsay.ui.activity.calculate.zhuanyun;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.DxDetailAdapter;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.event.ChangeWishBean;
import com.tongcheng.soothsay.bean.calculation.QfDetailBean;
import com.tongcheng.soothsay.bean.calculation.QfDxDetailListBean;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.PrayingApi;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.DateUtil;
import com.tongcheng.soothsay.widget.WishChangePopupWindow;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.umeng.socialize.utils.ContextUtil.getContext;

/**
 * 大仙供奉详情列表
 */
public class DxDetailActivity extends BaseTitleActivity implements View.OnClickListener {


    @BindView(R.id.rc_dxdetail)
    RecyclerView mRcDxdetail;
    private DxDetailAdapter mDetailAdapter;
    private List<QfDetailBean> mDatas;
    private String mDxname;
    private WishChangePopupWindow mWishChangePopupWindow;
    private TextView mQiFuWishContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_dxdetail);
        initData();
    }


    @Override
    public void initData() {
        EventBus.getDefault().register(this);
        getBaseHeadView().showTitle("我的心愿");
        mDatas= new ArrayList<QfDetailBean>();
        mDetailAdapter = new DxDetailAdapter(this, mDatas, R.layout.qifu_item_wish_progress);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRcDxdetail.setLayoutManager(linearLayoutManager);
        mRcDxdetail.setAdapter(mDetailAdapter);

        mDxname = getIntent().getStringExtra("DxDetail");
        getDxList(mDxname);

    }
    @Subscribe()
    public void showEventBus(ChangeWishBean wishBean){

        mQiFuWishContent.setText(wishBean.Wish);
        mDetailAdapter.notifyDataSetChanged();
    }










    private  void getDxList(String dxname){
        PrayingApi.getInstance().qfDxDetailList(UserManager.getInstance().getUser().getToken(),dxname).enqueue(new ApiCallBack<ApiResponseBean<QfDxDetailListBean>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                QfDxDetailListBean dxDetailListBean =  (QfDxDetailListBean) data;
                mDetailAdapter.setDatas(dxDetailListBean.getQfDetail());
                View view = LayoutInflater.from(DxDetailActivity.this).inflate(R.layout.qifu_activity_my_wish_head,null,false);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels);
                layoutParams.width= FrameLayout.LayoutParams.MATCH_PARENT;
                layoutParams.height= FrameLayout.LayoutParams.WRAP_CONTENT;
                view.setLayoutParams(layoutParams);
                mQiFuWishContent = (TextView) view.findViewById(R.id.qifu_wish_content);
                TextView cxz=(TextView)view.findViewById(R.id.qifu_with_cxz);
                TextView date=(TextView)view.findViewById(R.id.qifu_wish_date);
                TextView totalTimes=(TextView)view.findViewById(R.id.qifu_wish_totaltimes);
                View changeview = view.findViewById(R.id.qifu_xinyuan_change);
                changeview.setTag(dxDetailListBean);
                changeview.setOnClickListener(DxDetailActivity.this);
                mQiFuWishContent.setText(dxDetailListBean.getQfContent());
                cxz.setText(dxDetailListBean.getQfCxz());
                date.setText(DateUtil.formatTime(Long.valueOf(dxDetailListBean.getQfDate()),"yyyy年MM月dd日"));
                totalTimes.setText(dxDetailListBean.getQfTotalTimes()+"天");
                mDetailAdapter.addHeaderView(view);
//                TextView textView = new TextView(DxDetailActivity.this);
//                textView.setGravity(Gravity.CENTER);
//                textView.setLayoutParams(layoutParams);
//                textView.setText("没有更多数据");
//                mDetailAdapter.addFooterView(textView);

            }

            @Override
            public void onError(String errorCode, String message) {
                Toast.makeText(DxDetailActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        }));
    }


    @Override
    public void onClick(View v) {
        if (ClickUtil.isFastClick()) return;

        if (R.id.qifu_xinyuan_change==v.getId()) {
           showWIshChangeWindow((QfDxDetailListBean)v.getTag());
        }
    }



    private void showWIshChangeWindow(final QfDxDetailListBean qfDxDetailListBean) {
        final Dialog dialog = new Dialog(this, R.style.Dialog);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_wish_change, null);
        TextView cancel = (TextView) view.findViewById(R.id.tv_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
       TextView submit=(TextView)view.findViewById(R.id.tv_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ClickUtil.isFastClick()) return;



//                mIsFromChange = getIntent().getBooleanExtra("isFromChange",false);
//                if (mIsFromChange){
//                    mWish = getIntent().getStringExtra("Wish");
//                    mDxName = getIntent().getStringExtra("dxName");
//                跳到一个新的activity

                Intent intent = new Intent(DxDetailActivity.this, MyWishActivity.class);
                intent.putExtra("isFromChange",true);
                intent.putExtra("Wish",qfDxDetailListBean.getQfContent());
                intent.putExtra("dxName",qfDxDetailListBean.getQfDx());
                startActivity(intent);
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.setWindowAnimations(R.style.normalDialogAnim);
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
