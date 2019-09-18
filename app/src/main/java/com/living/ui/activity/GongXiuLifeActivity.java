package com.living.ui.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.living.adapter.MessageAdapter;
import com.living.bean.xuetang.Data;
import com.living.utils.GotoUtil;
import com.living.utils.ImageWatcherUtil;
import com.living.utils.SpaceItemDecorationUtil;
import com.living.view.MessagePicturesLayout;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.utils.UMShareUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ch.ielse.view.imagewatcher.ImageWatcher;

public class GongXiuLifeActivity extends BaseTitleActivity implements ImageWatcher.OnPictureLongPressListener, MessagePicturesLayout.Callback {

    @BindView(R.id.v_image_watcher)
    ImageWatcher mImageWatcher;
    @BindView(R.id.life_recycler)
    RecyclerView mLifeRecycler;
    @BindView(R.id.headBackButton)
    ImageButton mHeadBackButton;
    @BindView(R.id.headShareButton)
    ImageButton mHeadShareButton;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    private MessageAdapter adapter;
    boolean isTranslucentStatus = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
            isTranslucentStatus = true;
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life);
        ButterKnife.bind(this);
        initData();
    }

    @OnClick({R.id.headBackButton, R.id.headShareButton, R.id.fab})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.headBackButton:
                GongXiuLifeActivity.this.finish();
                break;
            case R.id.headShareButton:
                new UMShareUtils(GongXiuLifeActivity.this).shareDefault(R.mipmap.ic_launcher, "生活感悟", "生活感悟", "www.baidu.com ");
                break;
            case R.id.fab:
//                Toast.makeText(this, "我是发帖按钮", Toast.LENGTH_SHORT).show();
                GotoUtil.GoToActivity(this, SendTweetActivity.class);

            default:
                break;
        }
    }


    @Override
    public void initData() {
        super.initData();
//        getBaseHeadView().showTitle("生活感悟");
//        //分享按钮
//        getBaseHeadView().showHeadRightImageButton(R.drawable.icon_share, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new UMShareUtils(GongXiuLifeActivity.this).shareDefault(R.mipmap.ic_launcher, "生活感悟", "生活感悟", "www.baidu.com ");
//            }
//        });
//        getBaseHeadView().showBackButton(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                GongXiuLifeActivity.this.finish();
//            }
//        });


        //下拉刷新
        mSwipeRefresh.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorPrimary));
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });

        mLifeRecycler.setLayoutManager(new LinearLayoutManager(this));
        mLifeRecycler.addItemDecoration(new SpaceItemDecorationUtil(this).setSpace(14).setSpaceColor(0xFFECECEC));
        mLifeRecycler.setAdapter(adapter = new MessageAdapter(this).setPictureClickCallback(this));
        adapter.set(Data.get(), 1);

        // 一般来讲， ImageWatcher 需要占据全屏的位置
        // 如果是透明状态栏，你需要给ImageWatcher标记 一个偏移值，以修正点击ImageView查看的启动动画的Y轴起点的不正确
        mImageWatcher.setTranslucentStatus(!isTranslucentStatus ? ImageWatcherUtil.calcStatusBarHeight(this) : 0);
        // 配置error图标 如果不介意使用lib自带的图标，并不一定要调用这个API
        mImageWatcher.setErrorImageRes(R.mipmap.error_picture);
        // 长按图片的回调，你可以显示一个框继续提供一些复制，发送等功能
        mImageWatcher.setOnPictureLongPressListener(this);

        mImageWatcher.setLoader(new ImageWatcher.Loader() {
            @Override
            public void load(Context context, String url, final ImageWatcher.LoadCallback lc) {
                Picasso.with(context).load(url).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        lc.onResourceReady(bitmap);
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {
                        lc.onLoadFailed(errorDrawable);
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                        lc.onLoadStarted(placeHolderDrawable);
                    }
                });
            }
        });

        ImageWatcherUtil.fitsSystemWindows(isTranslucentStatus, findViewById(R.id.v_fit));
    }

    private void refreshData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        adapter.notifyDataSetChanged();
                        mSwipeRefresh.setRefreshing(false);
                    }
                });
            }

        }).start();
    }


    @Override
    public void onThumbPictureClick(ImageView i, List<ImageView> imageGroupList, List<String> urlList) {
        mImageWatcher.show(mFab,i, imageGroupList, urlList);
        mFab.setVisibility(View.GONE);

    }

    public void getFabVisibilityGone() {
        mFab.setVisibility(View.GONE);
    }
    public void getFabVisibilityVisible() {
        mFab.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPictureLongPress(ImageView v, String url, int pos) {
        Toast.makeText(v.getContext().getApplicationContext(), "长按了第" + (pos + 1) + "张图片", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        if (!mImageWatcher.handleBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}
