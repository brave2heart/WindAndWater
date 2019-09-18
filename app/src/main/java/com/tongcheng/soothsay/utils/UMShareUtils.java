package com.tongcheng.soothsay.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.widget.Toast;

import com.tongcheng.soothsay.ui.MainActivity;
import com.tongcheng.soothsay.ui.activity.mine.SettingActivity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.shareboard.ShareBoardConfig;


/**
 * Created by 宋家任 on 2016/12/2.
 * 友盟分享工具类
 * 2017/1/3
 * 一期无微博分享
 */

public class UMShareUtils {
    private Activity mActivity;
    private ShareAction mShareAction;

    public UMShareUtils(Activity mActivity) {
        this.mActivity = mActivity;
    }

    /**
     * 图文分享(图片从网络获取)
     *
     * @param ImageUrl 图片url
     * @param title    标题
     * @param text     内容
     * @param url      点击跳转的url
     */
    public void shareDefault(String ImageUrl, String title, String text, String url) {
        UMImage image = new UMImage(mActivity, ImageUrl);//网络图片
        ShareBoardConfig config = new ShareBoardConfig();
        config.setShareboardPostion(ShareBoardConfig.SHAREBOARD_POSITION_BOTTOM);
        config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE);
        mShareAction = new ShareAction(mActivity).setDisplayList(
                SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE,
                /*SHARE_MEDIA.SINA,*/ SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE)
                .withMedia(image)
                .withTitle(title)
                .withText(text)
                .withTargetUrl(url)
                .setCallback(umShareListener);
        mShareAction.open(config);
    }

    /**
     * 图文分享(图片从从资源文件获取)
     *
     * @param resid 图片id
     * @param title 标题
     * @param text  内容
     * @param url   点击跳转的url
     */
    public void shareDefault(int resid, String title, String text, String url) {
        UMImage image = new UMImage(mActivity, resid);//本地图片
        ShareBoardConfig config = new ShareBoardConfig();
        config.setShareboardPostion(ShareBoardConfig.SHAREBOARD_POSITION_BOTTOM);
        config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE);
        mShareAction = new ShareAction(mActivity).setDisplayList(
                SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE,
                /*SHARE_MEDIA.SINA,*/ SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE)
                .withMedia(image)
                .withTitle(title)
                .withText(text)
                .withTargetUrl(url)
                .setCallback(umShareListener);
        mShareAction.open(config);
    }

    /**
     * 分享图片
     *
     * @param bitmap
     */
    public void shareImg(Bitmap bitmap) {
        UMImage image = new UMImage(mActivity, bitmap);
        ShareBoardConfig config = new ShareBoardConfig();
        config.setShareboardPostion(ShareBoardConfig.SHAREBOARD_POSITION_BOTTOM);
        config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE);
        mShareAction = new ShareAction(mActivity).setDisplayList(
                SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE,
                /*SHARE_MEDIA.SINA,*/ SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE)
                .withMedia(image)
                .setCallback(umShareListener);
        mShareAction.open(config);
    }

    /**
     * 分享图片
     *
     * @param resid 资源id
     */
    public void shareImg(int resid) {
        UMImage image = new UMImage(mActivity, resid);
        ShareBoardConfig config = new ShareBoardConfig();
        config.setShareboardPostion(ShareBoardConfig.SHAREBOARD_POSITION_BOTTOM);
        config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE);
        mShareAction = new ShareAction(mActivity).setDisplayList(
                SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE,
                /*SHARE_MEDIA.SINA,*/  SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE)
                .withMedia(image)
                .setCallback(umShareListener);
        mShareAction.open(config);
    }

    /**
     * 分享图片
     *
     * @param imgurl 图片链接
     */
    public void shareImg(String imgurl) {
        UMImage image = new UMImage(mActivity, imgurl);
        ShareBoardConfig config = new ShareBoardConfig();
        config.setShareboardPostion(ShareBoardConfig.SHAREBOARD_POSITION_BOTTOM);
        config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE);
        mShareAction = new ShareAction(mActivity).setDisplayList(
                SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE,
                /*SHARE_MEDIA.SINA,*/ SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE)
                .withMedia(image)
                .setCallback(umShareListener);
        mShareAction.open(config);
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            if (platform.name().equals("WEIXIN_FAVORITE")) {
                ToastUtil.showShort(mActivity, "收藏成功");
            } else {
                if (platform != SHARE_MEDIA.MORE && platform != SHARE_MEDIA.SMS
                        && platform != SHARE_MEDIA.EMAIL
                        && platform != SHARE_MEDIA.FLICKR
                        && platform != SHARE_MEDIA.FOURSQUARE
                        && platform != SHARE_MEDIA.TUMBLR
                        && platform != SHARE_MEDIA.POCKET
                        && platform != SHARE_MEDIA.PINTEREST
                        && platform != SHARE_MEDIA.LINKEDIN
                        && platform != SHARE_MEDIA.INSTAGRAM
                        && platform != SHARE_MEDIA.GOOGLEPLUS
                        && platform != SHARE_MEDIA.YNOTE
                        && platform != SHARE_MEDIA.EVERNOTE) {
                    Toast.makeText(mActivity," 分享成功", Toast.LENGTH_SHORT).show();
                }

            }

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            if (platform != SHARE_MEDIA.MORE && platform != SHARE_MEDIA.SMS
                    && platform != SHARE_MEDIA.EMAIL
                    && platform != SHARE_MEDIA.FLICKR
                    && platform != SHARE_MEDIA.FOURSQUARE
                    && platform != SHARE_MEDIA.TUMBLR
                    && platform != SHARE_MEDIA.POCKET
                    && platform != SHARE_MEDIA.PINTEREST
                    && platform != SHARE_MEDIA.LINKEDIN
                    && platform != SHARE_MEDIA.INSTAGRAM
                    && platform != SHARE_MEDIA.GOOGLEPLUS
                    && platform != SHARE_MEDIA.YNOTE
                    && platform != SHARE_MEDIA.EVERNOTE) {
                if (t != null) {
                    ToastUtil.showShort(mActivity, "分享错误:" + t.getMessage());
                }
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            ToastUtil.showShort(mActivity,  "分享取消");
        }
    };


}
