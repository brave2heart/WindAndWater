package com.tongcheng.soothsay.ui.activity.calculate.zhuanyun;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.FragmentAdapter;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.calculation.QfDxBean;
import com.tongcheng.soothsay.bean.event.EventJiFenBean;
import com.tongcheng.soothsay.bean.event.EventQianXianBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.data.calculate.zhuanyun.qifutai.QiFuTaiTempData;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.PrayingApi;
import com.tongcheng.soothsay.popwindow.PrayingIntroPop;
import com.tongcheng.soothsay.popwindow.QiFuTaiUseIntroPopoWindow;
import com.tongcheng.soothsay.service.MusicService;
import com.tongcheng.soothsay.ui.fragment.calculation.zhuanyun.PrayingStationFragment;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.CusDialogUtil;
import com.tongcheng.soothsay.utils.EventBusUtil;
import com.tongcheng.soothsay.utils.SpUtil;
import com.tongcheng.soothsay.widget.CustomDialog;
import com.tongcheng.soothsay.widget.PullDownPopupwindow;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

//import com.tongcheng.soothsay.widget.CusDialogUtil;

/**
 * @description: 命理推算-祈福转运-祈福台
 * @author: lijuan
 * @date: 2016-10-31
 * @time: 09:58
 */
public class PrayingStationActivity extends BaseTitleActivity implements View.OnClickListener {


    private static final String TAG = "PrayingStationActivity";
    private ViewPager mViewPager;
    private FragmentAdapter adapter;
    private LinearLayout mLlDot;
    private ImageView mIvToLeft, mIvToRight, mIvGodLeft, mIvGodRight, mIvBack, mIvMore;
    private Button mBtnMaster, mBtnCourse, mBtnShoppingMall;
    private TextView mTvJifen, mTvHeart;
    private CheckBox mCb;
    //    private String[] titles = {"积分说明", "积分操作", "分享"};
    private String[] titles = {"祈福说明"};


    /**
     * ViewPage选项卡页面集合
     */
    private List<PrayingStationFragment> mFragments;
    /**
     * 总的页数
     */
    private int pageCount;
    /**
     * 当前显示的是第几页
     */
    private int curIndex = 0;
    private List<String> mTitles;

    private int img;
    private String name;
    private String wish;
    private List<QfDxBean> mDxBeen;
    private QiFuTaiTempData mQiFuTaiTempData;
    private CustomDialog mCustomDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_praying_station);
        initViews();
        initDatas();
        initListener();
        mQiFuTaiTempData = QiFuTaiTempData.newInstaince();
        EventBusUtil.register(this);
        Boolean isPlayMusic = SpUtil.getBoolean(this, "isPlayMusic", true);
        if (!isPlayMusic && UserManager.getInstance().isLogin()) {
            Intent intent = new Intent(this, MusicService.class);
            startService(intent);
        }

    }

    public void getNextGod() {
//        这里面有一个细节  如果按viewpager的size来判读  有可能  前面没有请仙的会跳过。
        int count = mViewPager.getAdapter().getCount();
        int i = mViewPager.getCurrentItem() + 1;
        if (i < count) {
            mViewPager.setCurrentItem(i);
        } else {
//            这里有两种
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTvJifen.setText(UserManager.getInstance().getUserJf(this));
        //判断是否从从请仙入口进入
//        if (getIntent().getStringExtra("type") != null) {
//            if (getIntent().getStringExtra("type").equals(TYPE_WISH)) {
//                curIndex = pageCount - 2;
//                notifyPageChange(curIndex);
//            }
//        }

//        if (mQiFuTaiTempData.isQingXianSuccessful()) {
//            addGod();
//        }
    }

    @Subscribe
    public void QingXianSuccessful(EventQianXianBean bean) {
        if (bean.getEvent()) {
            PrayingStationActivity.this.finish();
        }
    }

    private void initDatas() {
        mFragments = new ArrayList<>();
        mTitles = new ArrayList<>();
        mTvJifen.setText(UserManager.getInstance().getUserJf(this));
        mLlDot.removeAllViews();
        addGod();

    }

    private void showPager(int index) {
        //设置圆点
        setOvalLayout(index);
        setIsVisible(index);
    }

    private void addGod() {
        addEmpty();
        if (!UserManager.getInstance().isLogin()) {
            mCustomDialog = new CusDialogUtil(this).showLoginDialg(R.string.txt_qifutai_gotologin, 1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finish();
                }
            });

//            new CusDialogUtil(this).showLoginDialg(R.string.txt_qifutai_gotologin, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                    GotoUtil.GoToActivityForResult(PrayingStationActivity.this, LoginActivity.class,1);
//
//                }
//            }, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    GotoUtil.GoToActivityForResult(PrayingStationActivity.this, LoginActivity.class,1);
//                    dialog.dismiss();
//                }
//            });
            return;
        }
        String token = UserManager.getInstance().getUser().getToken();
        PrayingApi.getInstance().qfDxList(token).enqueue(new ApiCallBack<ApiResponseBean<List<QfDxBean>>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                mFragments.clear();
                mDxBeen = (List<QfDxBean>) data;
                if (mDxBeen.size() != 0) {

                    String qfCxz = mDxBeen.get(0).getQfCxz();
                    mTvHeart.setText(qfCxz);
                }
                for (QfDxBean qfDxBean : mDxBeen) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("god", qfDxBean);
                    mFragments.add(PrayingStationFragment.newInstance(bundle));
                    mTitles.add(qfDxBean.getQfDx());
                    String qfname = "" + qfDxBean.getQfDx();
                    mQiFuTaiTempData.addGod(qfDxBean.getQfDx());


                    if (!qfDxBean.getQfDx().isEmpty() && (qfDxBean.getQfDx().equals("南极仙翁") || qfDxBean.getQfDx().equals("福神") || qfDxBean.getQfDx().equals("禄星"))) {
                        mQiFuTaiTempData.addGod("南极仙翁");
                        mQiFuTaiTempData.addGod("福神");
                        mQiFuTaiTempData.addGod("禄星");
                    }
                }
                addEmpty();
                if (adapter == null) {
                    adapter = new FragmentAdapter(getSupportFragmentManager(), mFragments, mTitles);
                    mViewPager.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } else {
                    adapter.notifyDataChange(mFragments, mTitles);
                }
                if (mQiFuTaiTempData.isQingXianSuccessful()) {
                    curIndex = mFragments.size() - 2;
                    mQiFuTaiTempData.setisQingXianSuccessful(false, null);
                    showPager(curIndex);
                    notifyPageChange(curIndex);
                    showQianXiangSuccessFulView();
                } else {
                    showPager(curIndex);
                }
            }

            @Override
            public void onError(String errorCode, String message) {
                if (Constant.ErrorCode.RESPONSE_ERROR_LOST_10004.equals(errorCode)) {
                    new CusDialogUtil(PrayingStationActivity.this).showLoginDialg(R.string.txt_qifutai_gotologinv2, 3);
                }
                mFragments.clear();
                addEmpty();

                adapter = new FragmentAdapter(getSupportFragmentManager(), mFragments, mTitles);
                //给ViewPager设置适配器
                mViewPager.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                showPager(curIndex);
//                getBaseEmptyView().showEmptyView();
            }
        }));

    }

    // TODO: 2017/1/3 这里可以添加请仙成功后播放完成的动画
    private void showQianXiangSuccessFulView() {

    }

    private void addEmpty() {
        Bundle bundle = new Bundle();
        QfDxBean qfDxBean = new QfDxBean(null, null, "0", "0", "", null, null, null);
        bundle.putSerializable("god", qfDxBean);
        mFragments.add(PrayingStationFragment.newInstance(bundle));
        mTitles.add("wish");
        pageCount = mFragments.size();
    }

    private boolean hasData() {
        return name != null;
    }

    /**
     * 初始化控件
     */
    private void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.vp_praying);
        mLlDot = (LinearLayout) findViewById(R.id.ll_dot);
        mIvToLeft = (ImageView) findViewById(R.id.iv_to_left);
        mIvToRight = (ImageView) findViewById(R.id.iv_to_right);
        mIvGodLeft = (ImageView) findViewById(R.id.iv_god_left);
        mIvGodRight = (ImageView) findViewById(R.id.iv_god_right);
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mIvMore = (ImageView) findViewById(R.id.iv_more);

        mBtnMaster = (Button) findViewById(R.id.btn_master);
        mBtnCourse = (Button) findViewById(R.id.btn_course);
        mBtnShoppingMall = (Button) findViewById(R.id.btn_shopping_mall);

        mTvJifen = (TextView) findViewById(R.id.tv_jifen);
        mTvHeart = (TextView) findViewById(R.id.tv_heart);

        mCb = (CheckBox) findViewById(R.id.cb);
        mCb.setChecked(SpUtil.getBoolean(this, "isPlayMusic"));
        mCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SpUtil.putBoolean(PrayingStationActivity.this, "isPlayMusic", mCb.isChecked());
                if (mCb.isChecked()) {
                    stopService(new Intent(PrayingStationActivity.this, MusicService.class));
                } else {
                    startService(new Intent(PrayingStationActivity.this, MusicService.class));

//                    EventBus.getDefault().post(new MusicEventBean(MusicEventBean.PLAY_KEY));
                }
            }
        });
        mIvGodLeft.setOnClickListener(this);
        mIvGodRight.setOnClickListener(this);
        mIvToLeft.setOnClickListener(this);
        mIvToRight.setOnClickListener(this);
        mIvBack.setOnClickListener(this);
        mIvMore.setOnClickListener(this);
        mBtnMaster.setOnClickListener(this);
        mBtnCourse.setOnClickListener(this);
        mBtnShoppingMall.setOnClickListener(this);
        mTvJifen.setOnClickListener(this);
    }

    /**
     * 设置圆点
     */
    public void setOvalLayout(int index) {
        mLlDot.removeAllViews();
        for (int i = 0; i < pageCount; i++) {
            mLlDot.addView(LayoutInflater.from(this).inflate(R.layout.dot, null));
        }
        // 默认显示第一页
        mLlDot.getChildAt(index).findViewById(R.id.v_dot)
                .setBackgroundResource(R.drawable.dot_selected);

    }

    @Override
    public void initListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // 取消圆点选中
                mLlDot.getChildAt(curIndex)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_normal);
                // 圆点选中
                mLlDot.getChildAt(position)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_selected);
                setIsVisible(position);
                curIndex = position;
                if (mDxBeen != null && mDxBeen.size() > position) {
                    mTvHeart.setText(mDxBeen.get(position).getQfCxz());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void notifyPageChange(int index) {
        Log.d(TAG, "index:" + index);
        mLlDot.getChildAt(0)
                .findViewById(R.id.v_dot)
                .setBackgroundResource(R.drawable.dot_normal);
        // 圆点选中
        mLlDot.getChildAt(index)
                .findViewById(R.id.v_dot)
                .setBackgroundResource(R.drawable.dot_selected);
        setIsVisible(index);
        mViewPager.setCurrentItem(index);
//        mViewPager.setCurrentItem(index,false);

    }

    /**
     * 设置左右两边的按钮图片以及是否可见
     *
     * @param curIndex 当前显示的是第几页
     */
    private void setIsVisible(int curIndex) {
        if (curIndex == 0) {
            mIvToLeft.setVisibility(View.INVISIBLE);
            mIvGodLeft.setVisibility(View.INVISIBLE);
            mIvToRight.setVisibility(View.VISIBLE);
            mIvGodRight.setVisibility(View.VISIBLE);
            if (mFragments.size() > 1)
                mIvGodRight.setImageResource(((QfDxBean) mFragments.get(curIndex + 1).getArguments().getSerializable("god")).getImgSrc(this));
            if (curIndex == pageCount - 1) {
                mIvToRight.setVisibility(View.INVISIBLE);
            }
        } else if (curIndex == pageCount - 1) {
            mIvToLeft.setVisibility(View.VISIBLE);
            mIvGodLeft.setVisibility(View.VISIBLE);
            mIvGodLeft.setImageResource(((QfDxBean) mFragments.get(curIndex - 1).getArguments().getSerializable("god")).getImgSrc(this));
            mIvToRight.setVisibility(View.INVISIBLE);
            mIvGodRight.setVisibility(View.INVISIBLE);
        } else {
            mIvToLeft.setVisibility(View.VISIBLE);
            mIvToRight.setVisibility(View.VISIBLE);
            mIvGodLeft.setVisibility(View.VISIBLE);
            mIvGodRight.setVisibility(View.VISIBLE);
            mIvGodRight.setImageResource(((QfDxBean) mFragments.get(curIndex + 1).getArguments().getSerializable("god")).getImgSrc(this));
            mIvGodLeft.setImageResource(((QfDxBean) mFragments.get(curIndex - 1).getArguments().getSerializable("god")).getImgSrc(this));
        }
    }


    @Override
    public void onClick(View v) {
        if (ClickUtil.isFastClick()) return;

        switch (v.getId()) {

            case R.id.iv_to_left:
            case R.id.iv_god_left:
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
                break;
            case R.id.iv_to_right:
            case R.id.iv_god_right:
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                break;
            case R.id.iv_back:
                finish();
            case R.id.iv_more:
//                PullDownPopupwindow pullDownPopupwindow = new PullDownPopupwindow(PrayingStationActivity.this, titles);
//                pullDownPopupwindow.setPopupWindowItemClickListener(new PopupwindowItemClickListener());
//                pullDownPopupwindow.initView(PrayingStationActivity.this);
//                pullDownPopupwindow.showPopupWindow(mIvMore, 20, 20);
                showPrayingIntroDialog();
                break;
            case R.id.btn_master://请仙
//                GotoUtil.GoToActivity(PrayingStationActivity.this, RequestImmortalActivity.class);
                Intent intent = new Intent(this, RequestImmortalActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_course://祈福历程
                Intent intent2 = new Intent(this, PrayingCourseActivity.class);
                startActivity(intent2);


                break;
            case R.id.btn_shopping_mall://祈福商城
                Intent intent3 = new Intent(this, PrayingMallActivity.class);
                startActivity(intent3);

                break;
            case R.id.tv_jifen:
                startActivity(new Intent(this,CreditsIntroActivity.class));
                break;
        }
    }


    /**
     * 暂时不用 不删除
     */
    public class PopupwindowItemClickListener implements PullDownPopupwindow.PopupWindowItemClickListener {

        @Override
        public void onItemClcikListener(AdapterView<?> adapterView, View view, int i, long l) {
//            ToastUtil.showLong(PrayingStationActivity.this, titles[i]);

            switch (i) {
                case 0:
//                    积分说明F
//                    showJiFenIntroDialog();

                    showPrayingIntroDialog();
                    break;
                case 1:
                    showJiFenUseDialog();
                    break;
                case 2:
                    showFenXianDialog();
                    break;
            }
        }
    }

    private void showPrayingIntroDialog() {
        PrayingIntroPop prayingIntroPop = new PrayingIntroPop(this, R.layout.pop_praying_intro, true);
        prayingIntroPop.showPopOnRootView(this);
    }

    private void showFenXianDialog() {

    }

    private void showJiFenUseDialog() {
        QiFuTaiUseIntroPopoWindow qiFuTaiUseIntroPopoWindow = new QiFuTaiUseIntroPopoWindow(this, R.layout.qifu_qifutai_use_intro_dialog, true);
        qiFuTaiUseIntroPopoWindow.showPopOnRootView(this);
    }

    private void showJiFenIntroDialog() {
        final Dialog dialog = new Dialog(this, R.style.Dialog);
        View view = View.inflate(this, R.layout.dialog_jifen_intro, null);
        Button cancel = (Button) view.findViewById(R.id.btn_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.setWindowAnimations(R.style.normalDialogAnim);
        dialog.show();
    }


    public void refresh(QfDxBean god) {
        Log.d(TAG, "god:" + god);
        int i = mDxBeen.indexOf(god);
        mDxBeen.remove(god);
        if (i!=-1) {

//            curIndex=mViewPager.getCurrentItem();
//            notifyPageChange(curIndex);
//            Log.d(TAG, "god:" + i);
            adapter.removeItem(i);
            curIndex=mViewPager.getCurrentItem();
            pageCount=adapter.getCount();
           setOvalLayout(curIndex);
        }
//        GotoUtil.GoToActivity(this, PrayingStationActivity.class);
//        finish();
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        if (requestCode == 1) {
            if (UserManager.getInstance().isLogin()) {
                mCustomDialog.dismiss();
                addGod();
            } else {
                finish();
            }
        }
        super.startActivityForResult(intent, requestCode, options);
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(PrayingStationActivity.this, MusicService.class));
        EventBusUtil.unregister(this);
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Log.d(TAG, "requestCode:" + requestCode);
//        Log.d(TAG, "resultCode:" + resultCode);
//        if (requestCode==3){
//            initDatas();
//        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Subscribe
    public void setJifen(EventJiFenBean bean) {
        mTvJifen.setText(UserManager.getInstance().getUserJf(this));
    }

    public void chagneGodBean(QfDxBean god) {
        int i = mDxBeen.indexOf(god);
        if (i>-1) {
            mDxBeen.set(i,god);
        }
        if (i==mViewPager.getCurrentItem()){
            mTvHeart.setText(god.getQfCxz());
        }
    }
}
