package com.tongcheng.soothsay.ui.activity.huangli;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.huangli.FortuneBean;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.helper.ImageHelper;
import com.tongcheng.soothsay.ui.fragment.huangli.OldAlmanacConstant;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.WindowUtil;
import com.tongcheng.soothsay.widget.HeadZoomScrollView;
import com.tongcheng.soothsay.widget.RoundImageView;

import butterknife.BindView;
import butterknife.OnClick;

//个人运势
public class FortuneActivity extends BaseTitleActivity implements View.OnClickListener {


    @BindView(R.id.headArea)                    View                    headView;
    @BindView(R.id.headBackButton)              ImageButton             btnBack;
    @BindView(R.id.headTitle)                   TextView                tvTitle;
    @BindView(R.id.scrollView_yunshi)           HeadZoomScrollView      scrollView;
    @BindView(R.id.tv_fortune_date)             TextView                tvDate;
    @BindView(R.id.tv_fortune_old)              TextView                tvOldDate;
    @BindView(R.id.tv_fortune_ganzhi)           TextView                tvGanzhi;
    @BindView(R.id.tv_fortune_color)            TextView                tvColor;
    @BindView(R.id.tv_fortune_yi)               TextView                tvYi;
    @BindView(R.id.tv_fortune_ji)               TextView                tvJi;
    @BindView(R.id.tv_fortune_fortune)          TextView                tvFortune;
    @BindView(R.id.tv_fortune_score)            TextView                tvScore;
    @BindView(R.id.img_fortune_icon)            RoundImageView          imgIcon;
    @BindView(R.id.img_exmple_icon)             ImageView               imgExmple;
    @BindView(R.id.fortune_bg)                  ImageView               imgBg;
    @BindView(R.id.tv_fortune_name)             TextView                tvName;
    @BindView(R.id.tv_fortune_birthday)         TextView                tvBirthday;
    @BindView(R.id.rl_fortune_content)          RelativeLayout          rlContent;


    private FortuneBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_fortune);

        initView();
        initListener();
        initData();
    }

    @Override
    public void initView() {
        headView.setBackgroundColor(Color.argb(0,255,199,115));
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.title_fortune));
        btnBack.setVisibility(View.VISIBLE);
        setActionBarTopPadding(headView);

        int height = WindowUtil.getScreenH(this);
        imgBg.getLayoutParams().height = height / 3 * 2;
    }


    @Override
    public void initListener() {
        scrollView.setOnScrollListener(scrollListener);

    }

    @Override
    public void initData() {
        bean = (FortuneBean) getIntent().getSerializableExtra(OldAlmanacConstant.INTENT_DATE);
        String month = bean.getCurrMon() >= 10 ? bean.getCurrMon()+"" : "0" + bean.getCurrMon();
        int imgId = getResources().getIdentifier("fortune_bg_" + month,"drawable",getPackageName());
        imgBg.setImageResource(imgId);


        tvDate.setText(bean.getDate());
        tvOldDate.setText(bean.getOldDate());
        tvGanzhi.setText(bean.getGanzhi());
        tvColor.setText(bean.getLuckyColor());
        tvYi.setText(bean.getYi());
        tvJi.setText(bean.getJi());
        tvScore.setText(bean.getScore());
        tvFortune.setText(bean.getFortune());

        String name = bean.getUserName();
        String day = bean.getBirthday();
        if(UserManager.getInstance().isLogin() &&  !TextUtils.isEmpty(UserManager.getInstance().getUserName(this))){
            name = UserManager.getInstance().getUserName(this);
            day = UserManager.getInstance().getUserDate(this);
            String imgUrl = UserManager.getInstance().getUserPhotoUrl(this);
            ImageHelper.getInstance().display(imgUrl,imgIcon);
            imgExmple.setVisibility(View.INVISIBLE);

            if (!TextUtils.isEmpty(day)) {
                String temp [] = day.split("\\.");
                day = temp[0] + "年" + temp[1] + "月" + temp[2] + "日";
                tvBirthday.setText(day);
            }

        }
        tvName.setText(name);

    }

    @OnClick({R.id.headBackButton})
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.headBackButton:
                supportFinishAfterTransition();
                break;
        }
    }


    private HeadZoomScrollView.OnScrollListener scrollListener = new HeadZoomScrollView.OnScrollListener() {
        @Override
        public void onScroll(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
            if (scrollY >= 0 && scrollY <= 255) {
                headView.setBackgroundColor(Color.argb(scrollY,255,199,115));
            }
        }
    };

}
