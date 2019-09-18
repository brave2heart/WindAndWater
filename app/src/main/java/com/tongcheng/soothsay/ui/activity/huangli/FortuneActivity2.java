package com.tongcheng.soothsay.ui.activity.huangli;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.living.bean.home.ConstellationBean;

import com.living.presenter.activity.HomeFortuneActivityPresenter;
import com.tongcheng.soothsay.R;
import com.living.adapter.home.GeRenFragmentPagerAdapter;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.huangli.FortuneBean;
import com.tongcheng.soothsay.bean.huangli.HFortuneBean;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.helper.ImageHelper;
import com.tongcheng.soothsay.ui.MainActivity;
import com.tongcheng.soothsay.ui.fragment.huangli.OldAlmanacConstant;
import com.tongcheng.soothsay.widget.CircularProgress;
import com.tongcheng.soothsay.widget.RoundImageView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FortuneActivity2 extends BaseTitleActivity {
    //顶部标题栏
    @BindView(R.id.headArea)
    View headView;
    @BindView(R.id.headBackButton)
    ImageButton btnBack;
    @BindView(R.id.headTitle)
    TextView tvTitle;
    //今日运势，明日运势，本周运势，本月运势
    @BindView(R.id.geren_head_image)
    ImageView mGerenHeadImage;
    @BindView(R.id.geren_quanquan)
    LinearLayout mGerenQuanquan;
    @BindView(R.id.geren_four_grade)
    LinearLayout mGerenFourGrade;
    @BindView(R.id.geren_ll_xingyun)
    RelativeLayout mGerenLlXingyun;
    @BindView(R.id.geren_ll_yiji)
    RelativeLayout mGerenLlYiji;

    @BindView(R.id.tablayout)
    XTabLayout mTablayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    @BindView(R.id.tv_fortune_color)
    TextView tvColor;                   //幸运色
    @BindView(R.id.tv_fortune_yi)
    TextView tvYi;                      //宜
    @BindView(R.id.tv_fortune_ji)
    TextView tvJi;                      //忌
    @BindView(R.id.img_fortune_icon)
    RoundImageView imgIcon;             //头像
    @BindView(R.id.img_exmple_icon)
    ImageView imgExmple;                //头像示例小图标
    @BindView(R.id.tv_fortune_birthday)
    TextView tvBirthday;                //生日
    @BindView(R.id.tv_fortune_name)
    TextView tvName;                    //用户名字
    @BindView(R.id.cp_yunshi_score)
    CircularProgress cProgress;
    @BindView(R.id.ap_yunshi_all)
    TextView apAll;
    @BindView(R.id.ap_yunshi_money)
    TextView apMoney;
    @BindView(R.id.ap_yunshi_love)
    TextView apLove;
    @BindView(R.id.ap_yunshi_work)
    TextView apWork;
    @BindView(R.id.tv_home_geren_xingzuo)
    TextView mTvHomeGerenXingzuo;         //星座

    private FortuneBean fortuneBean;

    @Inject
    public HomeFortuneActivityPresenter homeFortuneActivityPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fortune2);
        ButterKnife.bind(this);


        initView();
        initTabLayoutViewPager();
        initData();
        initXingZuoData();

    }

    private void initXingZuoData() {
//
//        DaggerFortuneActivity2Component.builder().fortuneActivity2Module(new FortuneActivity2Module(this)).build().in(this);
//
//        mFortuneActivity2Presenter.getXingZuoDate("处女座",HomeAPI.XingZuoType[0],HomeAPI.XingZuokey);



    }



    private void initTabLayoutViewPager() {
        //将viewPager和Fragmnet绑定
        GeRenFragmentPagerAdapter fragmentPagerAdapter = new GeRenFragmentPagerAdapter(getSupportFragmentManager());
        mViewpager.setAdapter(fragmentPagerAdapter);
        //将TabLayout和ViewPager绑定
        mTablayout.setupWithViewPager(mViewpager);
    }

    @Override
    public void initView() {
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.title_fortune));
        setActionBarTopPadding(headView);
        btnBack.setVisibility(View.VISIBLE);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FortuneActivity2.this, MainActivity.class));
            }
        });


    }

    @Override
    public void initData() {

        fortuneBean = (FortuneBean) getIntent().getSerializableExtra(OldAlmanacConstant.INTENT_DATE);

        String month = fortuneBean.getCurrMon() >= 10 ? fortuneBean.getCurrMon() + "" : "0" + fortuneBean.getCurrMon();
        int imgId = getResources().getIdentifier("fortune_bg_" + month, "drawable", getPackageName());
        // imgBg.setImageResource(imgId); 旧版个人运势背景图

        //tvDate.setText(bean.getDate());  //日期
        // tvOldDate.setText(bean.getOldDate());  农历
        // tvGanzhi.setText(bean.getGanzhi());  干支
        tvColor.setText(fortuneBean.getLuckyColor()); //幸运色
        tvYi.setText(fortuneBean.getYi());
        tvJi.setText(fortuneBean.getJi());
//        tvScore.setText(bean.getScore());
        //tvFortune.setText(bean.getFortune()); 今日运势

        String name = fortuneBean.getUserName();
        String day = fortuneBean.getBirthday();
        if (UserManager.getInstance().isLogin() && !TextUtils.isEmpty(UserManager.getInstance().getUserName(this))) {
            name = UserManager.getInstance().getUserName(this);
            day = UserManager.getInstance().getUserDate(this);
            String imgUrl = UserManager.getInstance().getUserPhotoUrl(this);
            ImageHelper.getInstance().display(imgUrl, imgIcon);
            imgExmple.setVisibility(View.INVISIBLE);

            if (!TextUtils.isEmpty(day)) {
                String temp[] = day.split("\\.");
                day = temp[0] + "年" + temp[1] + "月" + temp[2] + "日";
                tvBirthday.setText(day);
            }

        }
        tvName.setText(name);

    }

    private void showFortune(HFortuneBean bean) {
        String alls = bean.getAll();
        int all = Integer.valueOf(alls.substring(0, alls.indexOf("%")));
        all = all <= 75 ? all + 20 : all;

        String moneys = bean.getMoney();
        int money = Integer.valueOf(moneys.substring(0, moneys.indexOf("%")));
        money = money <= 75 ? money + 20 : money;

        String loves = bean.getLove();
        int love = Integer.valueOf(loves.substring(0, loves.indexOf("%")));
        love = love <= 75 ? love + 20 : love;

        String works = bean.getWork();
        int work = Integer.valueOf(works.substring(0, works.indexOf("%")));
        work = work <= 75 ? work + 20 : work;

//        alls = getEvaluateByScore(all);
//        moneys = getEvaluateByScore(money);
//        loves = getEvaluateByScore(love);
//        works = getEvaluateByScore(work);
//
//        apAll.setText(alls);
//        apMoney.setText(moneys);
//        apLove.setText(loves);
//        apWork.setText(works);

        cProgress.setValue(all);
        apAll.setText(all);
        apMoney.setText(money);
        apLove.setText(love);
        apWork.setText(work);

        fortuneBean.setScore(all + "");
        fortuneBean.setFortune(bean.getSummary());
        fortuneBean.setLuckyColor(bean.getColor());


    }




    public void setData(ConstellationBean constellationBean) {
//
//        cProgress.setValue(getInt(constellationBean.getHealth()));
//        NumAnimUtil.startAnim(apAll, getInt(constellationBean.getAll()));
//        NumAnimUtil.startAnim(apWork, getInt(constellationBean.getWork()));
//        NumAnimUtil.startAnim(apMoney, getInt(constellationBean.getMoney()));
//        NumAnimUtil.startAnim(apLove, getInt(constellationBean.getLove()));
    }
    //获取Int类型的分数
    public int getInt(String str) {
        int mInt = Integer.parseInt(str.replace("%", ""));
        return mInt;
    }

}