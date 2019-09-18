package com.tongcheng.soothsay.ui.fragment.calculation.life;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleFragment;
import com.tongcheng.soothsay.bean.calculation.QQNumberBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.http.service.AllApi;
import com.tongcheng.soothsay.ui.activity.calculate.life.TestQQActivity;
import com.tongcheng.soothsay.utils.ToastUtil;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by 宋家任 on 2016/11/4.
 * QQ号测凶吉结果页
 */

public class TestQQResultFragment extends BaseTitleFragment {
    TextView tvQQnumber;


    String qqnumber;
    @BindView(R.id.tv_testqq_cs)
    TextView tvqqCs;
    @BindView(R.id.tv_testqq_xg)
    TextView tvqqXg;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_testqq_result, container, false);
        tvQQnumber = (TextView) view.findViewById(R.id.tv_testqq_qqnumber);
        tvqqCs = (TextView) view.findViewById(R.id.tv_testqq_cs);
        tvqqXg = (TextView) view.findViewById(R.id.tv_testqq_xg);

        qqnumber = ((TestQQActivity) getActivity()).getQqnumber();
        if (!"".equals(qqnumber)) {
            tvQQnumber.setText(qqnumber);
            getQQMsg();
        }
        return view;
    }

    /**
     * 根据传过来的qq号测算
     */
    private void getQQMsg() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.Url.BASE_QQMSG_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllApi.Service service = retrofit.create(AllApi.Service.class);
        Call<QQNumberBean> call = service.getQQMsg(Constant.QQTESTKEY, qqnumber);
        call.enqueue(new Callback<QQNumberBean>() {
            @Override
            public void onResponse(Call<QQNumberBean> call, Response<QQNumberBean> response) {
                if (0 == response.body().getError_code()) {
                    QQNumberBean.ResultBean.DataBean databean = response.body().getResult().getData();
                    if (!"".equals(databean.getConclusion()))
                        tvqqCs.setText(databean.getConclusion());
                    if (!"".equals(databean.getAnalysis()))
                        tvqqXg.setText(databean.getAnalysis());

                }
            }

            @Override
            public void onFailure(Call<QQNumberBean> call, Throwable t) {
                ToastUtil.showShort(TestQQResultFragment.this.getActivity(), t.toString());
            }
        });
    }


}
