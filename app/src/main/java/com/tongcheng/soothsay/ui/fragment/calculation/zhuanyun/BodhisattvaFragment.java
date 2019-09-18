package com.tongcheng.soothsay.ui.fragment.calculation.zhuanyun;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.GodRecycleViewAdapter;
import com.tongcheng.soothsay.base.AbsBaseRecycleAdapter;
import com.tongcheng.soothsay.base.BaseRecyclerAdapter;
import com.tongcheng.soothsay.base.BaseTitleFragment;
import com.tongcheng.soothsay.bean.calculation.GodBean;
import com.tongcheng.soothsay.bean.event.EventQianXianBean;
import com.tongcheng.soothsay.ui.activity.calculate.zhuanyun.ImmortalDetailsActivity;
import com.tongcheng.soothsay.utils.EventBusUtil;
import com.tongcheng.soothsay.utils.ResUtil;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 宋家任 on 2016/11/3.
 * 佛教菩萨
 */

public class BodhisattvaFragment extends BaseTitleFragment {
    @BindView(R.id.rv_caluation_taoism)
    RecyclerView mRecycleView;

    private String[] bodhisttva1 = new String[]{};
    private String[] bodhisattva2 = new String[]{};
    private int[] img = {R.drawable.god_1, R.drawable.god_1, R.drawable.god_1, R.drawable.god_1,
            R.drawable.god_1, R.drawable.god_1, R.drawable.god_1, R.drawable.god_1,
            R.drawable.god_1, R.drawable.god_1, R.drawable.god_1, R.drawable.god_1,
            R.drawable.god_1, R.drawable.god_1,
    };

    private List<GodBean> mDatas;
    private GodRecycleViewAdapter mAdapter;
    private ResUtil mResUtil;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_calulation_taoism,
                container, false);
        mResUtil = ResUtil.newInstance();
        return view;
    }

    @Override
    public void initData() {
        mDatas = new ArrayList<>();
        bodhisttva1 = getResources().getStringArray(R.array.pusa_type);
        bodhisattva2 = getResources().getStringArray(R.array.pusa_name);
        for (int i = 0; i < bodhisttva1.length; i++) {
            GodBean bean = new GodBean(bodhisttva1[i], bodhisattva2[i], mResUtil.getGodImageRes(bodhisattva2[i]));
            mDatas.add(bean);
        }
        mAdapter = new GodRecycleViewAdapter(getActivity(), mDatas, R.layout.item_request_immortal);
        mRecycleView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mRecycleView.setAdapter(mAdapter);
        //item点击监听
        mAdapter.setOnItemClickListener(new AbsBaseRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                Intent intent = new Intent(BodhisattvaFragment.this.getActivity(), ImmortalDetailsActivity.class);
                intent.putExtra("img", mResUtil.getGodImageRes(bodhisattva2[position]));
                intent.putExtra("name", bodhisattva2[position]);
                intent.putExtra("type", bodhisttva1[position]);
                intent.putExtra("position", position);
                intent.putExtra("flag", "ps");
                startActivity(intent);
//                BodhisattvaFragment.this.getActivity().finish();
            }
        });

    }

    @Override
    public void onDestroy() {
        EventBusUtil.unregister(this);
        super.onDestroy();
    }

    @Subscribe
    public void QingXianSuccessful(EventQianXianBean bean) {
        if (bean.getEvent()) {
            BodhisattvaFragment.this.getActivity().finish();
        }
    }

}
