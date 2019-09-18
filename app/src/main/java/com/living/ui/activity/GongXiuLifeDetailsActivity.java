package com.living.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.living.adapter.MessageAdapter;
import com.living.bean.xuetang.Data;
import com.living.bean.xuetang.LikeImage;
import com.living.bean.xuetang.PLData;
import com.living.utils.ImageWatcherUtil;
import com.living.utils.KeyboardUtil;
import com.living.utils.SpaceItemDecorationUtil;
import com.living.view.MessagePicturesLayout;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.tongcheng.soothsay.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ch.ielse.view.imagewatcher.ImageWatcher;

public class GongXiuLifeDetailsActivity extends AppCompatActivity implements ImageWatcher.OnPictureLongPressListener, MessagePicturesLayout.Callback {


    @BindView(R.id.life_recycler)
    RecyclerView mLifeRecycler;
    @BindView(R.id.v_image_watcher)
    ImageWatcher mImageWatcher;
    @BindView(R.id.dianzan_recycler)
    RecyclerView mDianzanRecycler;
    @BindView(R.id.gongxiu_dianzan_icon)
    ImageView mGongxiuDianzanIcon;
    @BindView(R.id.gongxiu_dianzan_num)
    TextView mGongxiuDianzanNum;
    @BindView(R.id.life_details_right)
    ImageView mLifeDetailsRight;
    @BindView(R.id.pl_recycler)
    RecyclerView mPlRecycler;
    @BindView(R.id.life_details_root)
    RelativeLayout mLifeDetailsRoot;
    @BindView(R.id.input_comment_container)
    LinearLayout mInputCommentContainer;
    @BindView(R.id.input_comment)
    EditText mInputComment;
    @BindView(R.id.btn_publish_comment)
    TextView mBtnPublishComment;
    private boolean isTranslucentStatus = false;
    private List<Data> mDataList = new ArrayList<>();
    private List<LikeImage> mLikeImages = new ArrayList<>();
    private List<PLData> mPLDatas = new ArrayList<>();


    private MessageAdapter adapter;
    private DianZanRecyclerAdapter mZanRecyclerAdapter;
    private boolean isAdd = true;
    private PLRecyclerAdapter mPlRecyclerAdapter;

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
//        SoftKeyBroadManager mManager = new SoftKeyBroadManager(mLifeDetailsRoot);
////添加软键盘的监听，然后和上面一样的操作即可.
//        mSoftKeyBroadManager.addSoftKeyboardStateListener();
////注意销毁时，得移除监听
//        mSoftKeyBroadManager.removeSoftKeyboardStateListener();
        KeyboardUtil.controlKeyboardLayout(mLifeDetailsRoot, mInputCommentContainer);
        initData();

    }

    private void initData() {
        Intent intent = getIntent();
        Data mData = (Data) intent.getSerializableExtra("Data");
        mDataList.add(mData);
        //点赞
        LikeImage likeImage1 = new LikeImage(R.drawable.geren);
        LikeImage likeImage2 = new LikeImage(R.drawable.geren2);
        LikeImage likeImage3 = new LikeImage(R.drawable.geren3);
        LikeImage likeImage4 = new LikeImage(R.drawable.geren4);
        LikeImage likeImage5 = new LikeImage(R.drawable.geren_bg);
        LikeImage likeImage6 = new LikeImage(R.drawable.weather_bg1);
        LikeImage likeImage7 = new LikeImage(R.drawable.weather_bg2);
        LikeImage likeImage8 = new LikeImage(R.drawable.geren3);
        LikeImage likeImage9 = new LikeImage(R.drawable.geren4);
        LikeImage likeImage10 = new LikeImage(R.drawable.geren_bg);
        mLikeImages.add(likeImage1);
        mLikeImages.add(likeImage2);
        mLikeImages.add(likeImage3);
        mLikeImages.add(likeImage4);
        mLikeImages.add(likeImage5);
        mLikeImages.add(likeImage6);
        mLikeImages.add(likeImage7);
        mLikeImages.add(likeImage8);
        mLikeImages.add(likeImage9);


        for (int i = 0; i < 20; i++) {


            //评论
            PLData plData1 = new PLData(
                    R.drawable.weather_bg,
                    "毛利小五郎",
                    "济南市",
                    "2个小时前",
                    "就是因为我老是不会十五字不知道该说什么，所以我攒下来以后自己用。"
            );

            PLData plData2 = new PLData(
                    R.drawable.geren4,
                    "开心就好",
                    "浙江萧山区",
                    "3个小时前",
                    "这篇帖子构思新颖，题材独具匠心，段落清晰，情节诡异，跌宕起伏，主线分明，引人入胜，平淡中显示出不凡的文学功底，可谓是字字珠玑，句句经典，是我辈应当学习之典范。"
            );

            mPLDatas.add(plData1);
            mPLDatas.add(plData2);
        }


        mLifeRecycler.setLayoutManager(new LinearLayoutManager(this));
        mLifeRecycler.addItemDecoration(new SpaceItemDecorationUtil(this).setSpace(14).setSpaceColor(0xFFECECEC));
        mLifeRecycler.setAdapter(adapter = new MessageAdapter(this).setPictureClickCallback(this));
        adapter.set(mDataList, 2);

        //点赞
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mDianzanRecycler.setLayoutManager(layoutManager);
        mZanRecyclerAdapter = new DianZanRecyclerAdapter(mLikeImages);
        mDianzanRecycler.setAdapter(mZanRecyclerAdapter);

        //评论
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        mPlRecycler.setLayoutManager(manager);
        //设置显示动画,避免更新数据时出现的画面抖动。
        mPlRecycler.setItemAnimator(new DefaultItemAnimator());
        mPlRecyclerAdapter = new PLRecyclerAdapter(mPLDatas);
        mPlRecycler.setAdapter(mPlRecyclerAdapter);


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
        mImageWatcher.show(null, i, imageGroupList, urlList);
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

    @OnClick({R.id.gongxiu_dianzan_icon, R.id.gongxiu_dianzan_num, R.id.btn_publish_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.gongxiu_dianzan_icon:
            case R.id.gongxiu_dianzan_num:
                if (isAdd) {
                    mLikeImages.add(0, new LikeImage(R.mipmap.ic_launcher));
                    mGongxiuDianzanNum.setText("88");
                    mGongxiuDianzanIcon.setImageResource(R.mipmap.gongxiu_dianzan_red);
                    isAdd = false;
                } else {
                    mLikeImages.remove(0);
                    mGongxiuDianzanNum.setText("87");
                    mGongxiuDianzanIcon.setImageResource(R.mipmap.gongxiu_dianzan_icon);
                    isAdd = true;
                }

                int size = mLikeImages.size();
                if (size > 0) {
                    mZanRecyclerAdapter.notifyDataSetChanged();
                }
                break;
            //发表
            case R.id.btn_publish_comment:
                String plComment = mInputComment.getText().toString().trim();
                mPLDatas.add(0, new PLData(
                        R.mipmap.ic_launcher,
                        "风生水公司",
                        "广西南宁市",
                        "46分钟前",
                        plComment
                ));
                int plSize = mPLDatas.size();
                if (plSize > 0) {
                    mPlRecyclerAdapter.notifyDataSetChanged();
                }
                //发送完成后，清空输入框的内容。
                mInputComment.setText("");
                break;

            default:
                break;
        }
    }


}
