package com.tongcheng.soothsay.popwindow;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.ZeriSelectEventAdapter;
import com.tongcheng.soothsay.base.BasePopupWindow;
import com.tongcheng.soothsay.bean.calculation.ZeriBean;

/**
 */

public class ZeriSelectEventPop extends BasePopupWindow implements AdapterView.OnItemClickListener {

private  Handler mHandler=new Handler(){
    @Override
    public void handleMessage(Message msg) {
        if (msg.what==1) {
            ZeriSelectEventPop.this.closePop();
        }
    }


};
    private int curposition;
    private final ZeriSelectEventAdapter mAdapter;
    private ListView mListView;


    public ZeriSelectEventPop(Activity activity, int resId, ZeriSelectEventAdapter adapter) {
        super(activity, resId,true);
        getLayoutView().setClickable(false);
        getLayoutView().setFocusable(false);
        mAdapter = adapter;
        mListView= (ListView) getLayoutView().findViewById(R.id.listview);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void init(View layoutView) {

    }

    @Override
    public void initListener() {
    }

    @Override
    public void showPopOnRootView(Activity activity) {
        super.showPopOnRootView(activity);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mAdapter.getDatas().get(curposition).setCheck(false);
        ZeriBean zeriBean = mAdapter.getDatas().get(position);
        zeriBean.setCheck(true);
        curposition=position;
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(zeriBean);
        }
        mAdapter.notifyDataSetChanged();
        mHandler.sendEmptyMessageDelayed(1,200);
    }


    OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(ZeriBean zeriBean);
    }
}
