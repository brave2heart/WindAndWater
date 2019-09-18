package com.tongcheng.soothsay.popwindow;

import android.app.Activity;
import android.app.Dialog;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.OfferingsAdapter;
import com.tongcheng.soothsay.adapter.calculation.ViewPagerAdapter;
import com.tongcheng.soothsay.base.BasePopupWindow;
import com.tongcheng.soothsay.bean.calculation.Offeringsbean;
import com.tongcheng.soothsay.bean.calculation.QfgpListBean;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.PrayingApi;
import com.tongcheng.soothsay.utils.ResUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bozhihuatong on 2016/11/28.
 */

public class QiFuTaiUseIntroPopoWindow extends BasePopupWindow {


    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;




    private LinearLayout mLLIntro;



    public QiFuTaiUseIntroPopoWindow(Activity activity, int resId, boolean outSide) {
        super(activity, resId, outSide);
    }

    @Override
    public void init(View layoutView) {
        mViewPager = (ViewPager) layoutView.findViewById(R.id.viewpager);
        mLLIntro = (LinearLayout) layoutView.findViewById(R.id.indicator);
    }


    @Override
    protected int[] loadAnimRes() {
        int[] anmis = {R.anim.dade_bottom_in, R.anim.dade_bottom_out};
        return anmis;
    }

    @Override
    public void initListener() {
        int childCount = mLLIntro.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (i == 0) {
                ((ImageView) mLLIntro.getChildAt(i)).setImageResource(R.drawable.shape_circle_yellow);
            }else{
                ((ImageView) mLLIntro.getChildAt(i)).setImageResource(R.drawable.shape_circle_grey);
            }
        }
        mViewPagerAdapter = new ViewPagerAdapter(getViews());
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int childCount = mLLIntro.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    if (i == position) {
                        ((ImageView) mLLIntro.getChildAt(i)).setImageResource(R.drawable.shape_circle_yellow);
                    }else{
                        ((ImageView) mLLIntro.getChildAt(i)).setImageResource(R.drawable.shape_circle_grey);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    public List<View> getViews() {
        ArrayList<View> views = new ArrayList<>();
//        views.add(getItemView(R.layout.qifu_qifutai_use_intro_item, R.drawable.lingji_qifutai_first_guide1, R.string.qifu_guide_text1));
//        views.add(getItemView(R.layout.qifu_qifutai_use_intro_item, R.drawable.lingji_qifutai_first_guide2, R.string.qifu_guide_text2));
//        views.add(getItemView(R.layout.qifu_qifutai_use_intro_item, R.drawable.lingji_qifutai_first_guide3, R.string.qifu_guide_text3));
//        views.add(getItemView(R.layout.qifu_qifutai_use_intro_item, R.drawable.lingji_qifutai_first_guide4, R.string.qifu_guide_text4));
        views.add(getItemView(R.layout.qifu_qifutai_use_intro_item, R.drawable.launch_bg1, R.string.qifu_guide_text1));
        views.add(getItemView(R.layout.qifu_qifutai_use_intro_item, R.drawable.launch_bg1, R.string.qifu_guide_text2));
        views.add(getItemView(R.layout.qifu_qifutai_use_intro_item, R.drawable.launch_bg1, R.string.qifu_guide_text3));
        views.add(getItemView(R.layout.qifu_qifutai_use_intro_item, R.drawable.launch_bg1, R.string.qifu_guide_text4));
        return views;
    }

    private View getItemView(int qifu_qifutai_use_intro_item, int iageviewResId, int textViewResId) {
        View inflate = View.inflate(getActivity(), qifu_qifutai_use_intro_item, null);
        TextView textview = (TextView) inflate.findViewById(R.id.textView);
        textview.setText(textViewResId);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.imageView);
        imageView.setImageResource(iageviewResId);
        return inflate;
    }
}



