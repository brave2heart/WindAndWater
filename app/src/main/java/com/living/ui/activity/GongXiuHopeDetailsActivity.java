package com.living.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.living.adapter.MessageAdapter;
import com.living.bean.xuetang.Data;
import com.living.utils.ImageWatcherUtil;
import com.living.utils.SpaceItemDecorationUtil;
import com.living.view.MessagePicturesLayout;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.tongcheng.soothsay.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ch.ielse.view.imagewatcher.ImageWatcher;

public class GongXiuHopeDetailsActivity extends AppCompatActivity implements ImageWatcher.OnPictureLongPressListener, MessagePicturesLayout.Callback {


    @BindView(R.id.life_recycler)
    RecyclerView mLifeRecycler;
    @BindView(R.id.v_image_watcher)
    ImageWatcher mImageWatcher;
    private boolean isTranslucentStatus = false;
    private List<Data> mDataList = new ArrayList<>();


    private MessageAdapter adapter;

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
        setContentView(R.layout.activity_gong_xiu_life_details);
        ButterKnife.bind(this);
        initData();

    }

    private void initData() {
        Intent intent = getIntent();
        Data mData = (Data) intent.getSerializableExtra("Data");
        mDataList.add(mData);

        mLifeRecycler.setLayoutManager(new LinearLayoutManager(this));
        mLifeRecycler.addItemDecoration(new SpaceItemDecorationUtil(this).setSpace(14).setSpaceColor(0xFFECECEC));
        mLifeRecycler.setAdapter(adapter = new MessageAdapter(this).setPictureClickCallback(this));
        adapter.set(mDataList, 2);

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


    @Override
    public void onThumbPictureClick(ImageView i, List<ImageView> imageGroupList, List<String> urlList) {
        mImageWatcher.show(null,i, imageGroupList, urlList);
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
