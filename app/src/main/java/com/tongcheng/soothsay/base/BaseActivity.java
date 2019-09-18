package com.tongcheng.soothsay.base;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

import com.tongcheng.soothsay.utils.StatusbarUtils;

public class BaseActivity extends AppCompatActivity {


    public final String TAG = this.getClass().getSimpleName();
    private FragmentManager mManager;
    private FragmentTransaction mTransaction;
    private String curFragmentName = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //在BaseActivity,设置沉浸状态栏,达到全局设置的目的
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);

        }



        mManager = getSupportFragmentManager();
        //禁止旋转屏幕
        if (!canRotation()) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        Log.d("BaseActivity", getClass().getSimpleName());


    }

    public void initView() {
    }

    public void initListener() {


    }

    public void initData() {
    }

    /**
     * 是否可以旋转屏幕
     */
    public boolean canRotation() {
        return false;
    }


    /**
     * 禁止点击菜单键
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private Fragment mContent;


    public Fragment getCurrentFragment() {
        return mContent;
    }

    /**
     * 替换fragment的方法
     *
     * @param fragment
     * @param simpleName
     * @param id
     */
    public void switchFragment(Fragment fragment, String simpleName, int id) {
        mTransaction = mManager.beginTransaction();
        curFragmentName = simpleName;
        if (fragment == null)
            return;

        if (mContent == null) {
            mTransaction.add(id, fragment, simpleName);
        } else {

            if (fragment.isAdded()) {
                mTransaction.hide(mContent).show(fragment);
            } else {
                mTransaction.hide(mContent).add(id, fragment, simpleName);
            }
        }
        mContent = fragment;
        mTransaction.commitAllowingStateLoss();
    }

    /**
     * 替换fragment方法  默认用fragmentr的simpleName做标签
     *
     * @param fragment
     * @param id
     */
    public void switchFragment(Fragment fragment, int id) {
        String simpleName = fragment.getClass().getSimpleName();
        switchFragment(fragment, simpleName, id);
    }

    @Override
    public void finish() {
        super.finish();
    }

}
