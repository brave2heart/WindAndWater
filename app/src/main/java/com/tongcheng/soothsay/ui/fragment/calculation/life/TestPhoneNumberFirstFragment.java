package com.tongcheng.soothsay.ui.fragment.calculation.life;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleFragment;
import com.tongcheng.soothsay.bean.calculation.NumberTestBean;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.AllApi;
import com.tongcheng.soothsay.utils.CheckPhoneUtil;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.LogUtil;
import com.tongcheng.soothsay.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by bozhihuatong on 2016/11/23.
 */

public class TestPhoneNumberFirstFragment extends BaseTitleFragment {
    @BindView(R.id.tv_testnumber_introduce)
    TextView tvTestphonenumber;
    @BindView(R.id.et_testnumber)
    BootstrapEditText etNumber;
    @BindView(R.id.btn_testnumber)
    Button btnNumber;
    @BindView(R.id.tv_res_title)
    TextView textView4;
    @BindView(R.id.tv_cesuan_response)
    TextView tvResponse;


    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.activity_testnumber, container, false);
        return view;
    }

    @OnClick(R.id.btn_testnumber)
    public void onClick() {
        if (ClickUtil.isFastClick()) {
            return;
        }
        String phonenumber = etNumber.getText().toString();
        if (TextUtils.isEmpty(phonenumber)) {
            ToastUtil.showShort(getActivity(),"手机号码不能为空");
        }else if (CheckPhoneUtil.isPhoneNum(phonenumber)){
//            去请求数据
            getPhoneNumberMesg(phonenumber);
        }else{
            ToastUtil.showShort(getActivity(),"手机号码格式不正确");
        }

    }

    private void getPhoneNumberMesg(String phonenumber) {
        AllApi.getInstance().getNumberMsg(phonenumber).enqueue(new ApiCallBack<ApiResponseBean<NumberTestBean>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                NumberTestBean bean=(NumberTestBean)data;
                tvResponse.setText(bean.getResult());
            }

            @Override
            public void onError(String errorCode, String message) {
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            }
        }));
    }

//    private void getPhoneNumberMesg(String phoneNumber) {
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Constant.BASE_QQMSG_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        AllApi.Service service = retrofit.create(AllApi.Service.class);
////        这里请求的是相同的结果  可以用同一个bean
//        Call<QQNumberBean> call = service.getQQMsg(Constant.QQTESTKEY, "15812691920");
//        call.enqueue(new Callback<QQNumberBean>() {
//            @Override
//            public void onResponse(Call<QQNumberBean> call, Response<QQNumberBean> response) {
//                Log.d("TestPhoneNumberFirstFra", "response.body().getError_code():" + response.body().getError_code());
//                if (0 == response.body().getError_code()) {
//                    QQNumberBean.ResultBean.DataBean databean = response.body().getResult().getData();
//                    if (!"".equals(databean.getConclusion())) {
//                        Toast.makeText(getActivity(), "没有进到这里吗", Toast.LENGTH_SHORT).show();
//
//                        tvResponse.setText(databean.getConclusion() + "\n" + databean.getAnalysis());
//                    }
////                    if (!"".equals(databean.getAnalysis()))
////                        tvqqXg.setText(databean.getAnalysis());
//                    Toast.makeText(getActivity(), databean.getAnalysis() + " " + databean.getConclusion(), Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<QQNumberBean> call, Throwable t) {
//             tvResponse.setText("错误");
//            }
//        });
//    }
}
