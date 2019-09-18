package com.tongcheng.soothsay.ui.fragment.calculation.zhuanyun;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.PrayingChooseFruitAdapter;
import com.tongcheng.soothsay.adapter.calculation.PrayingSelectFlowerAdapter;
import com.tongcheng.soothsay.bean.calculation.QfDxBean;
import com.tongcheng.soothsay.bean.calculation.QfgpListBean;
import com.tongcheng.soothsay.data.calculate.zhuanyun.qifutai.QiFuTaiTempData;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.PrayingApi;
import com.tongcheng.soothsay.ui.activity.calculate.zhuanyun.DxDetailActivity;
import com.tongcheng.soothsay.ui.activity.calculate.zhuanyun.PrayingStationActivity;
import com.tongcheng.soothsay.ui.activity.calculate.zhuanyun.RequestImmortalActivity;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.living.utils.GotoUtil;
import com.tongcheng.soothsay.utils.ResUtil;
import com.tongcheng.soothsay.widget.SelectFlowerPopupWindow;
import com.tongcheng.soothsay.widget.SelectFruitPopupWindow;

import java.util.HashMap;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * //                                  _oo8oo_
 * //                                 o8888888o
 * //                                 88" . "88
 * //                                 (| -_- |)
 * //                                 0\  =  /0
 * //                               ___/'==='\___
 * //                             .' \\|     |// '.
 * //                            / \\|||  :  |||// \
 * //                           / _||||| -:- |||||_ \
 * //                          |   | \\\  -  /// |   |
 * //                          | \_|  ''\---/''  |_/ |
 * //                          \  .-\__  '-'  __/-.  /
 * //                        ___'. .'  /--.--\  '. .'___
 * //                     ."" '<  '.___\_<|>_/___.'  >' "".
 * //                    | | :  `- \`.:`\ _ /`:.`/ -`  : | |
 * //                    \  \ `-.   \_ __\ /__ _/   .-` /  /
 * //                =====`-.____`.___ \_____/ ___.`____.-`=====
 * //                                  `=---=`
 * //
 * //
 * //               ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * //
 * //                          佛祖保佑         永无bug
 * //
 *
 * @description: 命理推算-祈福转运-祈福台的Fragment
 * @author: lijuan
 * @date: 2016-10-31
 * @time: 10:55
 */
public class PrayingStationFragment extends Fragment implements View.OnClickListener,
        PrayingSelectFlowerAdapter.onPopFlowerListener,
        PrayingChooseFruitAdapter.onPopFruitListener, OfferingsSelectPopoWindow.OfferingsOnClickLister /**, SelectXiangPopupWindow
 .onPopXiangListener, SelectDengPopupWindow.onPopDengListener */
{
    private static ResUtil sResUtil;
    @BindView(R.id.iv_shentai)
    ImageView mIvShentai;
    @BindView(R.id.iv_god2)
    ImageView mIvGod;
    @BindView(R.id.iv_table)
    ImageView mIvTable;
    @BindView(R.id.iv_left_vase)
    ImageView mIvLeftVase;
    @BindView(R.id.iv_right_vase)
    ImageView mIvRightVase;
    @BindView(R.id.iv_left_lamp)
    ImageView mIvLeftLamp;
    @BindView(R.id.iv_right_lamp)
    ImageView mIvRightLamp;
    @BindView(R.id.iv_plate)
    ImageView mIvPlate;
    @BindView(R.id.iv_deng)
    ImageView mIvDeng;
    @BindView(R.id.iv_watercup)
    ImageView mIvWatercup;
    @BindView(R.id.iv_belt)
    ImageView mIvBelt;
    @BindView(R.id.iv_xiang)
    ImageView mIvXiang;
    @BindView(R.id.iv_bg)
    ImageView mIvBg;


    @BindView(R.id.iv_right_lamp_huo)
    ImageView mIvRightLampHuo;
    @BindView(R.id.iv_deng_huo)
    ImageView mIvDengHuo;
    @BindView(R.id.iv_left_lamp_huo)
    ImageView mIvLeftLampHuo;
    @BindView(R.id.iv_guangquan2)
    ImageView mIvGuangquan;


    private QfgpListBean mFlower, mOil, mCup, mTable, mScreen, mFruit, mIncense;

    //判断是否插花
    private boolean hasFlower;
    //是否摆果
    private boolean hasFruit;

    private boolean hasIncense;


    private int firstPraying;

    private SelectFlowerPopupWindow selectFlowerPopupWindow;
    private SelectFruitPopupWindow selectFruitPopupWindow;
    //    private SelectXiangPopupWindow selectXiangPopupWindow;
//    private SelectDengPopupWindow selectDengPopupWindow;
    private View selectView;
    private QfDxBean mGod;
    private View mRootView;
    private QiFuTaiTempData mQiFuTaiTempData;
    private AnimationDrawable leftVaseAnim;
    private AnimationDrawable rightvaseAnim;
    private AnimationDrawable plateAnim;
    private AnimationDrawable xiangAnim;

    private AnimationDrawable mIvRightLampHuoAnim;
    private AnimationDrawable mIvDengHuoAnim;
    private AnimationDrawable mIvLeftLampHuoAnim;
    private OfferingsSelectPopoWindow mOfferingsSelectPopoWindow;
    private OfferingsSelectPopoWindow mShowSelectScreenPopoWindow;
    private OfferingsSelectPopoWindow mshowSelectTablePopoWindow1;

    public static PrayingStationFragment newInstance(int index) {
        Bundle bundle = new Bundle();
        bundle.putInt("index", 'A' + index);
        PrayingStationFragment fragment = new PrayingStationFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    public static PrayingStationFragment newInstance(Bundle bundle) {
        PrayingStationFragment fragment = new PrayingStationFragment();
        fragment.setArguments(bundle);
        sResUtil = ResUtil.newInstance();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        if (mRootView == null) {
            mQiFuTaiTempData = QiFuTaiTempData.newInstaince();
            mRootView = inflater.inflate(R.layout.fragment_praying_station, null);
            ButterKnife.bind(this, mRootView);
            mGod = (QfDxBean) getArguments().getSerializable("god");
            mIvGod.setImageResource(mGod.getImgSrc(getContext()));
            showThreeGod();
            initGuangQuan(mIvGuangquan);
            initGp();

        }
        return mRootView;
    }

    private void initGuangQuan(ImageView ivGuangquan) {
        int intQfTotalTimes = mGod.getIntQfTotalTimes();
        if (intQfTotalTimes == -1) return;
        int i = intQfTotalTimes / 3;
        int guangQuanResId = 0;
        switch (i) {
            case 0:
                break;
            case 1:
                guangQuanResId = R.drawable.lingji_qifu_guangquan1;
                break;
            case 2:
                guangQuanResId = R.drawable.lingji_qifu_guangquan2;
                break;
            case 3:
                guangQuanResId = R.drawable.lingji_qifu_guangquan3;
                break;
            case 4:
                guangQuanResId = R.drawable.lingji_qifu_guangquan4;
                break;
            case 5:
            default:
                guangQuanResId = R.drawable.lingji_qifu_guangquan5;
                break;
        }
        ivGuangquan.setImageResource(guangQuanResId);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.5f, 1f);
        alphaAnimation.setDuration(3000);
        alphaAnimation.setRepeatCount(Animation.INFINITE);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        ivGuangquan.startAnimation(alphaAnimation);
    }

    /**
     * 初始化 有效的贡品 并设置到界面上。
     */
    private void initGp() {
        for (QfgpListBean qfgpListBean : mGod.getGpList()) {
            setOfferings(qfgpListBean);
        }
        showAima();
    }

    private void setOfferings(QfgpListBean qfgpListBean) {
        switch (qfgpListBean.sort) {
            case 1://花
                mFlower = qfgpListBean;
                setGPImaget(mIvRightVase, mFlower);
                setGPImaget(mIvLeftVase, mFlower);
                hasFlower = true;
                break;
            case 2://果
                mFruit = qfgpListBean;
                setGPImaget(mIvPlate, mFruit);
                hasFruit = true;
                break;
            case 3://香
                mIncense = qfgpListBean;
//                在这里设置蜡烛
//                灯跟蜡烛要一起亮所以在这里一起设置
                setLempHuo();
                setDengAnima();
                setGPImaget(mIvXiang, mIncense);
                hasIncense = true;
                break;
            case 4://油
                mOil = qfgpListBean;
//                这里设置灯
//                setDengAnima();
                break;
            case 5://水杯
                mCup = qfgpListBean;
                setGPImaget(mIvWatercup, mCup);
                break;
            case 6://桌
                mTable = qfgpListBean;
                setGPImaget(mIvTable, mTable);
                break;
            case 7://屏风
                mScreen = qfgpListBean;
                setGPImaget(mIvBg, mScreen);
                break;
            default:
                Log.d("PrayingStationFragment", "商品类型出错  暂时没有");
        }
    }

    private void setLempHuo() {
        if (mIvLeftLampHuoAnim == null) {
            mIvLeftLampHuoAnim = (AnimationDrawable) mIvLeftLampHuo.getDrawable();
        }
        if (mIvRightLampHuoAnim == null) {
            mIvRightLampHuoAnim = (AnimationDrawable) mIvRightLampHuo.getDrawable();
        }
        mIvRightLampHuo.setVisibility(View.VISIBLE);
        mIvRightLampHuoAnim.start();
        mIvLeftLampHuo.setVisibility(View.VISIBLE);
        mIvLeftLampHuoAnim.start();
    }

    /**
     * 设置供品图片
     *
     * @param imageView
     * @param qfgpListBean
     */
    private void setGPImaget(ImageView imageView, QfgpListBean qfgpListBean) {
        int gpImageByName = sResUtil.getGpImageByName(qfgpListBean.gpName);
        imageView.setImageResource(gpImageByName);

    }

    private void setDengAnima() {
        if (mIvDengHuoAnim == null) {
            mIvDengHuoAnim = (AnimationDrawable) mIvDengHuo.getDrawable();
        }
        mIvDengHuo.setVisibility(View.VISIBLE);
        mIvDengHuoAnim.start();
    }


    //  这里只有在没有花放果的时候 提示需要先放果。
    @OnClick({R.id.iv_shentai, R.id.iv_god2, R.id.iv_table, R.id.iv_left_vase, R.id.iv_right_vase,
            R.id.iv_left_lamp, R.id.iv_right_lamp, R.id.iv_plate, R.id.iv_deng, R.id.iv_watercup,
            R.id.iv_belt, R.id.iv_xiang, R.id.iv_bg})
    public void onClick(View view) {
        if (ClickUtil.isFastClick()) return;

        if (view.getId() == R.id.iv_god2) {
            showGod();
            return;
//            ToastUtil.showLong(getActivity(), "神明");
        }
        if (mGod.getImgSrc(getContext()) == R.drawable.qifu_qingxian) {
//            Toast.makeText(getActivity(), "需要先请仙", Toast.LENGTH_SHORT).show();
//            这里显示一个dialog
            showPrayingGodDialg();
            return;
        }
        switch (view.getId()) {
            case R.id.iv_shentai:
//                ToastUtil.showLong(getActivity(), "台柱");
                break;
            case R.id.iv_table:
                showSelectTable();
                break;
            case R.id.iv_left_vase:
                showSelectFlowerView();
                break;
            case R.id.iv_right_vase:
                showSelectFlowerView();
                break;
//            case R.id.iv_left_lamp:
//                ToastUtil.showLong(getActivity(), "灯");
//                break;
//            case R.id.iv_right_lamp:
//                ToastUtil.showLong(getActivity(), "灯");
//                break;
            case R.id.iv_plate:
                showSelectFruitView();
                break;
            case R.id.iv_deng:
                showSelectOilView();
//                showDengView();
                break;
            case R.id.iv_watercup:
                showSelectCupView();
                break;
            case R.id.iv_belt:
//                祈福历程
                GoToPrayingIntro();
                break;
            case R.id.iv_xiang:
                showIncenseView();
                break;
            case R.id.iv_bg:
                showSelectScreenView();
            default:
                break;
        }
    }

    private void showPrayingGodDialg() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("提示")
                .setMessage("你还没有请仙，请仙选择你要供奉的大仙进行祈福。诚心祈福，福有攸归。")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getActivity(), RequestImmortalActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        }).show();
    }

    private void showPrayingNextGodDialg() {
        mGod.setQfCxz(String.valueOf(Integer.valueOf(mGod.getQfCxz())+5));
        mGod.setQfTotalTimes(String.valueOf(mGod.getIntQfTotalTimes()+1));
        ((PrayingStationActivity) getActivity()).chagneGodBean(mGod);
        final Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        String QfTotalTimes = String.format(Locale.CANADA, "恭喜，你已累计祈拜地藏菩萨<font color=\"#FF0000\">%s</font>天。坚持祈拜，原望将更容易实现。", mGod.getQfTotalTimes());
        View view = View.inflate(getActivity(), R.layout.dialog_praying_god_finish, null);
        ((TextView) view.findViewById(R.id.tv_dialog_praying_god_finish_count)).setText(Html.fromHtml(QfTotalTimes));
        LayoutInflater.from(getActivity()).inflate(R.layout.dialog_praying_god_finish,(ViewGroup) mRootView,false);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(layoutParams);
        ((Button) view.findViewById(R.id.btn_dialog_praying_god_finish_next)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((PrayingStationActivity) getActivity()).getNextGod();
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.setWindowAnimations(R.style.normalDialogAnim);
        dialog.show();
    }


    private void GoToPrayingIntro() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("DxDetail", mGod.getQfDx());
        GotoUtil.GoToActivityWithData(getActivity(), DxDetailActivity.class, hashMap);
    }

    /**
     * 这里要加个判读  如果当前的godbean是空的话 就让去请仙
     */
    private void showGod() {
        if (mGod.getImgSrc(getContext()) == R.drawable.qifu_qingxian) {
//            这里跳去请仙
            GotoUtil.GoToActivity(getActivity(), RequestImmortalActivity.class);
        } else {
            showGodIntro(mGod);
        }
    }

    //插花选择窗口
    private void showSelectFlowerView() {
        baseShowSelectView("1", R.string.txt_flower);
    }


    //摆果选择窗口
    private void showSelectFruitView() {
        if (showOrderView(2)) {
            baseShowSelectView("2", R.string.txt_fruit);
        }
    }

    private void showInnerSelectFruitView() {
        baseShowSelectView("2", R.string.txt_fruit);
    }

    /**
     * 香
     */
    private void showIncenseView() {
        if (showOrderView(3)) {
            baseShowSelectView("3", R.string.txt_xiang);
        }
    }


    /**
     * 灯
     */
    private void showSelectOilView() {
        baseShowSelectView("4", R.string.txt_deng);
    }

    /**
     * 杯子
     */
    private void showSelectCupView() {
        baseShowSelectView("5", R.string.txt_water);
    }


    private void baseShowSelectView(String offeringsId, int offeringContentId) {

//        先获取数据  再显示数据 不要到内部

        if (mOfferingsSelectPopoWindow == null) {
            mOfferingsSelectPopoWindow = new OfferingsSelectPopoWindow
                    (getActivity(), mGod, R.layout.popo_offerings_select_, true, offeringsId, R.layout.item_praying_mall, 3, offeringContentId);
            mOfferingsSelectPopoWindow.setOfferingsOnClickLister(this);
        } else {
            mOfferingsSelectPopoWindow.setSort(offeringsId);
        }
//        popoWindow.showPopOnRootView(getActivity());
    }

    private void showSelectScreenView() {
        if (mShowSelectScreenPopoWindow == null) {
            mShowSelectScreenPopoWindow = new OfferingsSelectPopoWindow
                    (getActivity(), mGod, R.layout.popo_offerings_select_, true, "7", R.layout.item_praying_mall_single, 1, R.string.txt_screen);
            mShowSelectScreenPopoWindow.setOfferingsOnClickLister(this);
        } else {
            mShowSelectScreenPopoWindow.setSort("7");
        }

    }

    private void showSelectTable() {
        if (mshowSelectTablePopoWindow1 == null) {
            mshowSelectTablePopoWindow1 = new OfferingsSelectPopoWindow
                    (getActivity(), mGod, R.layout.popo_offerings_select_, true, "6", R.layout.item_praying_mall_single, 1, R.string.txt_table);
            mshowSelectTablePopoWindow1.setOfferingsOnClickLister(this);
        } else {
            mShowSelectScreenPopoWindow.setSort("6");
        }

    }

    private void showGodIntro(QfDxBean dxBean) {
        if (dxBean == null) {
            return;
        }
        final Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        View view = View.inflate(getActivity(), R.layout.dialog_praying_god_intro, null);
        TextView date = (TextView) view.findViewById(R.id.tv_dialong_praying_god_intor_date);
        TextView content = (TextView) view.findViewById(R.id.tv_dialong_praying_god_intro_content);
        TextView tvReturn = (TextView) view.findViewById(R.id.tv_dialong_praying_god_intro_return);
        Button cancel = (Button) view.findViewById(R.id.btn_dialong_praying_god_intro_cancel);
        ImageView godImage = (ImageView) view.findViewById(R.id.iv_dialong_praying_god_intro_god);
        date.setText("已累记祈福" + dxBean.getQfTotalTimes() + "天");
        godImage.setImageResource(getGodImageByGodName(dxBean.getQfDx()));
        content.setText(getGodContentByGodName(dxBean.getQfDx()));
        tvReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showIsConfirmReturnGod();
                dialog.dismiss();
            }
        });
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

    private void showIsConfirmReturnGod() {
        final Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_confirm_returngod, null);
        TextView tvContent = (TextView) inflate.findViewById(R.id.tv_content);
        String content = "一旦送仙回天将意味着您不再向此仙祈愿供奉，祈福历程和数据将会清零。请问您是否确认要送" + mGod.getQfDx() + "回天？";

        tvContent.setText(content);
        BootstrapButton gopay_submit = (BootstrapButton) inflate.findViewById(R.id.btn_offerings_gopay_submit);
        BootstrapButton gopay_cancel = (BootstrapButton) inflate.findViewById(R.id.btn_offerings_gopay_cancel);

        gopay_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoReturnGod(dialog);
            }
        });
        gopay_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.setContentView(inflate);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        dialog.show();
    }


    private void showPrayingFinishDialog() {

        final Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        View view = View.inflate(getActivity(), R.layout.dialog_qifu_peace, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(layoutParams);
        ((Button) view.findViewById(R.id.qifu_peace_finish_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPrayingNextGodDialg();
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.setWindowAnimations(R.style.normalDialogAnim);
        dialog.show();
    }

    private void gotoReturnGod(final Dialog dialog) {
//        送仙
        PrayingApi.getInstance().deleteDx(UserManager.getInstance().getUser().getToken(), mGod.getQfDx()).enqueue(new ApiCallBack<ApiResponseBean<Void>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                mQiFuTaiTempData.remove(mGod.getQfDx());
                ((PrayingStationActivity) getActivity()).refresh(mGod);
                dialog.dismiss();
            }

            @Override
            public void onError(String errorCode, String message) {
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

            }
        }));
    }


    //    显示要放的正确顺序  花  果 香 顺序不能错
    private boolean showOrderView(int selectId) {
//        这里面要检查 如果没有花  要提示去插花  果

        boolean flags = false;
        String btnName = "";
        if (!hasFlower) {
            btnName = "供奉鲜花";
        } else if (!hasFruit && selectId == 3) {
            btnName = "供奉水果";
        } else {
            return true;
        }
        final Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        View view = View.inflate(getActivity(), R.layout.dialog_qifu_gongfeng, null);
        Button btnPraying = (Button) view.findViewById(R.id.qifu_gongfeng_dailog_btn);
        btnPraying.setText(btnName);
        btnPraying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!hasFlower) {
                    showSelectFlowerView();
                } else if (!hasFruit) {
                    showSelectFruitView();
                }
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.setWindowAnimations(R.style.normalDialogAnim);
        dialog.show();
        return flags;
    }

    private String getGodContentByGodName(String qfDx) {
        return sResUtil.getGodContentByName(qfDx);
    }


    private int getGpImagetByGpId(int id) {
        int identifier = getContext().getResources().getIdentifier("gp_" + id, "drawable",
                getContext().getPackageName());
        return identifier;
    }

    /**
     * 返回神仙的图片
     *
     * @param godName
     * @return
     */
    private int getGodImageByGodName(String godName) {
        return sResUtil.getGodImageRes(godName);
    }


    //提示顺序
    public void tips() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("正确的供奉顺序：");
        if (!hasFlower) {
            builder.setPositiveButton("供奉鲜花", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //弹出鲜花
                    showSelectFlowerView();
                }
            });
        } else {
            builder.setPositiveButton("供奉水果", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //弹出水果
                    showSelectFruitView();
                }
            });
        }
        builder.show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onPopFlowerClick(int i) {
        mIvLeftVase.setImageResource(i);
        mIvRightVase.setImageResource(i);
        hasFlower = true;
        selectFlowerPopupWindow.closePop();
    }

    @Override
    public void onPopFruitClick(int i) {
        mIvPlate.setImageResource(i);
        hasFruit = true;
        selectFruitPopupWindow.closePop();
    }

    public QfDxBean getGod() {
        return mGod;
    }

    @Override
    public void onAddOfferingsSuccessful(QfgpListBean offeringsbean) {
        setOfferings(offeringsbean);
        if (offeringsbean.sort == 3) {
            showPrayingFinishDialog();
        }
        showAima();
    }


    /**
     * 判断今天是不是已经供奉完成
     *
     * @return
     */
    private void isFirstPrayingFinishShow() {
        if (firstPraying == 1 && hasFlower && hasFruit && hasIncense) {
            firstPraying++;
            if (firstPraying == 1) {
                showFInishView();
            }
        }
    }


    private void showFInishView() {
        final Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        View view = View.inflate(getActivity(), R.layout.dialog_qifu_peace, null);
        Button btnPraying = (Button) view.findViewById(R.id.qifu_peace_finish_btn);
        btnPraying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                showPrayingProgress();
            }
        });
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.setWindowAnimations(R.style.normalDialogAnim);
        dialog.show();

    }


    /**
     * 显示进度dialog
     */
    private void showPrayingProgress() {
        final Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        View view = View.inflate(getActivity(), R.layout.lingji_qifutai_qifu_day_dialog, null);
        Button btnPraying = (Button) view.findViewById(R.id.lingji_qifutai_qifuday_dialog_button1);
        btnPraying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               这里跳到另一个大仙
                PrayingStationActivity activity = (PrayingStationActivity) getActivity();
                dialog.dismiss();
                activity.getNextGod();
            }
        });
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.setWindowAnimations(R.style.normalDialogAnim);
        dialog.show();
    }


    private void showAima() {
        if (mGod.getImgSrc(getContext()) == R.drawable.qifu_qingxian) {
            return;
        } else {
            stopAnima(leftVaseAnim);
            stopAnima(rightvaseAnim);
            stopAnima(xiangAnim);
            stopAnima(plateAnim);
            if (!hasFlower) {
                mIvLeftVase.setImageResource(R.drawable.qifutai_vase);
                leftVaseAnim = (AnimationDrawable) mIvLeftVase.getDrawable();
                leftVaseAnim.start();
                mIvRightVase.setImageResource(R.drawable.qifutai_vase);
                rightvaseAnim = (AnimationDrawable) mIvRightVase.getDrawable();
                rightvaseAnim.start();
            } else if (!hasFruit) {
                mIvPlate.setImageResource(R.drawable.qifutai_fruit);
                plateAnim = (AnimationDrawable) mIvPlate.getDrawable();
                plateAnim.start();
            } else if (!hasIncense) {
                mIvXiang.setImageResource(R.drawable.qifutai_xiang);
                xiangAnim = (AnimationDrawable) mIvXiang.getDrawable();
                xiangAnim.start();
            }
        }

    }

    private void stopAnima(AnimationDrawable Anim) {
        if (Anim != null) {
            Anim.stop();
        }
    }


    //    这里设置是否要显示三个大仙  福禄寿
    private void showThreeGod() {
        String qfDx = mGod.getQfDx();
        if (qfDx == null) {
            return;
        }
        if (!qfDx.isEmpty() && (qfDx.equals("南极仙翁") || qfDx.equals("福神") || qfDx.equals("禄星"))) {
//            禄星
            setOtherGod(R.id.item_god_1, R.drawable.god_12);
//           寿星
            setOtherGod(R.id.item_god_3, R.drawable.god_5);
//           福星
            mIvGod.setImageResource(R.drawable.god_9);
            mQiFuTaiTempData.addGod("南极仙翁");
            mQiFuTaiTempData.addGod("福神");
            mQiFuTaiTempData.addGod("禄星");
        }
    }

    private void setOtherGod(int resId, int godResId) {
        View viewById = mRootView.findViewById(resId);
        viewById.setVisibility(View.VISIBLE);
        ImageView guangquan = (ImageView) viewById.findViewById(R.id.iv_guangquan);
        initGuangQuan(guangquan);
        ImageView god = (ImageView) viewById.findViewById(R.id.iv_god);
        god.setImageResource(godResId);
    }


}
