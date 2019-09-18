package com.tongcheng.soothsay.ui.activity.calculate.zhuanyun;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.calculation.WishTreeBean;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.WishTreeAPi;
import com.tongcheng.soothsay.popwindow.WishTreeInputPop;
import com.tongcheng.soothsay.popwindow.WishTreePop;
import com.tongcheng.soothsay.ui.activity.mine.LoginActivity;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.DateUtil;
import com.living.utils.GotoUtil;
import com.tongcheng.soothsay.utils.SpUtil;
import com.tongcheng.soothsay.widget.WishTreeView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 许愿树
 */
public class WishTreeActivity extends BaseTitleActivity {


    @BindView(R.id.headArea)
    RelativeLayout headArea;
    @BindView(R.id.headBackButton)
    ImageButton headBackButton;
    @BindView(R.id.wishTreeView)
    WishTreeView treeView;
    @BindView(R.id.btn_free_shuoming)
    ImageView mBtnFreeShuoming;


    int count;
    @BindView(R.id.tv_seenumber)
    TextView mTvSeenumber;

    private WishTreePop mPop;
    private WishTreeInputPop mInputPop;
    private String mDate;
    private String mUserName="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_tree);
        ButterKnife.bind(this);

        initView();
        initListener();
        initData();


    }

    @Override
    public void initView() {
        headBackButton.setVisibility(View.VISIBLE);
        setActionBarTopPadding(headArea);

    }

    @Override
    public void initListener() {
        treeView.setOnWishTreeListener(new WishTreeView.OnWishTreeListener() {
            @Override
            public void onWishtTreeClick(View v, WishTreeBean bean) {
                if (count == 0) {
                    Toast.makeText(WishTreeActivity.this, "今天次数已经看完啦", Toast.LENGTH_SHORT).show();
                    return;
                }
                showPop(bean);
                count--;
                mTvSeenumber.setText("今天还可\n查看" + count + "条");

            }
        });


    }

    @OnClick({R.id.headBackButton, R.id.btn_free_shuoming})
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.headBackButton:
                finish();
                break;
            case R.id.btn_free_shuoming:
                if (ClickUtil.isFastClick()) {
                    return;
                }
                showoperate();
                break;
        }
    }

    @Override
    public void initData() {
        if (!UserManager.getInstance().isLogin()) {
            mUserName = UserManager.getInstance().getUserName(this);
        }
        String wishSeeCount = SpUtil.getString(this, "WishSeeCount" + mUserName);
        if (wishSeeCount.isEmpty()) {
            count = 20;
            mDate = DateUtil.getCurTime("yyyy.MM.dd");
        } else {
            try {
                JSONObject jsonObject = new JSONObject(wishSeeCount);
                String date = jsonObject.getString("date");
                int count = jsonObject.getInt("count");
                String curTime = DateUtil.getCurTime("yyyy.MM.dd");
                if (date.equals(curTime)) {
                    this.count = count;//如果是同一天 就把里面的数字拿出来
                } else {
                    this.count = 20;//日期不同数做废
                }
                mDate = curTime;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        mTvSeenumber.setText("今天还可\n查看" + count + "条");

        WishTreeAPi.getInstance().getXyTreeList(mUserName).enqueue(new ApiCallBack<ApiResponseBean<List<WishTreeBean>>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                List<WishTreeBean> data1 = (List<WishTreeBean>) data;
                treeView.addAllView(data1);
            }

            @Override
            public void onError(String errorCode, String message) {
                Toast.makeText(WishTreeActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        }));

    }


    private void showPop(WishTreeBean bean) {
        if (mPop == null) {
            mPop = new WishTreePop(this, R.layout.dialog_wish_tree);
        }
        mPop.setData(bean);
        mPop.showPopOnRootView(this);
    }


    private void showPopInputView() {
        if (mInputPop == null) {
            mInputPop = new WishTreeInputPop(this, R.layout.dialog_wish_tree_input);
        }
        mInputPop.showPopOnRootView(this);

    }


    private void reAddView() {
        if (!UserManager.getInstance().isLogin()) {
            GotoUtil.GoToActivity(this, LoginActivity.class);
            return;
        }
        WishTreeAPi.getInstance().getXyTreeList(UserManager.getInstance().getToken()).enqueue(new ApiCallBack<ApiResponseBean<List<WishTreeBean>>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                List<WishTreeBean> data1 = (List<WishTreeBean>) data;
                treeView.removeAllView();
                treeView.addAllView(data1);
            }

            @Override
            public void onError(String errorCode, String message) {
                Toast.makeText(WishTreeActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        }));


    }


    public void showoperate() {
        final String[] stringItems = {"许愿", "其它许愿"};
        final ActionSheetDialog dialog = new ActionSheetDialog(this, stringItems, null);
        dialog.isTitleShow(false).show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        showPopInputView();
                        break;
                    case 1:
                        reAddView();
                        break;
                }
                dialog.dismiss();
            }
        });
    }


    @Override
    protected void onStop() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("date", mDate);
            jsonObject.put("count", count);
            String s = jsonObject.toString();
            SpUtil.putString(this, "WishSeeCount" + mUserName, s);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        super.onStop();
    }
}
