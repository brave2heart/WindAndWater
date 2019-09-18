package com.tongcheng.soothsay.ui.fragment.calculation.divination.oneiromancy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.OneiromancyDetalisAdapter;
import com.tongcheng.soothsay.adapter.calculation.OneiromancyTitleAdapter;
import com.tongcheng.soothsay.bean.calculation.ZGJMDetailBean;
import com.tongcheng.soothsay.utils.ClickUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Gubr on 2016/12/7.
 * 周公解梦 结果展示界面
 */

public class OneiromancyFragmentDetails extends Fragment implements OneiromancyContract.DetailView {


    private OneiromancyContract.Presenter mPresenter;



    private ListView mlistview;
    private TextView mTitle1;
    private TextView mTitle2;
    private OneiromancyDetalisAdapter mOneiromancyDetalisAdapter;
    private ImageView mback;


    public static OneiromancyFragmentDetails newInstance() {
        return new OneiromancyFragmentDetails();
    }





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_oneiromancy_detail, container, false);

        initView(root);
        initData();
        initListener();
        return root;
    }

    private void initView(View root) {
        mback = (ImageView) root.findViewById(R.id.iv_back);
        mTitle1 = (TextView) root.findViewById(R.id.tv_title1);
        mTitle2 = (TextView) root.findViewById(R.id.tv_title2);
        mlistview = (ListView) root.findViewById(R.id.listview);



    }

    private void initData() {
    }

    private void initListener() {

        mback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ClickUtil.isFastClick()) {
                    return;
                }
                mPresenter.showInput();
            }
        });
//        mTvSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                mPresenter.
//            }
//        });

    }



    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
        mPresenter.unsubscribe();
    }







    @Override
    public void setPresenter(OneiromancyContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Subscribe
    @Override
    public void showDetail(ZGJMDetailBean.ResultBean data) {
        if (data==null) {
            return;
        }
        mTitle1.setText(data.getTitle());
        mTitle2.setText(data.getTitle());

        if (mOneiromancyDetalisAdapter==null) {
            mOneiromancyDetalisAdapter = new OneiromancyDetalisAdapter(getActivity(), data.getList(), R.layout.item_oneiromancy_detail);
            mlistview.setAdapter(mOneiromancyDetalisAdapter);
        }else{
            mOneiromancyDetalisAdapter.notifyChangeData(data.getList());
        }
//        这里显示结果  不用listview
    }

    @Override
    public void removeSelf() {
            mPresenter.removeDetailView();
    }
}
