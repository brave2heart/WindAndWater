package com.tongcheng.soothsay.ui.fragment.mine;


import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.living.ui.activity.ShoppingHomeActivity;
import com.living.ui.fragment.ShoppingHomeFragment;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleFragment;
import com.tongcheng.soothsay.bean.event.ChangeNameBean;
import com.tongcheng.soothsay.bean.event.DontLoginBean;
import com.tongcheng.soothsay.bean.event.EventLoginBean;
import com.tongcheng.soothsay.bean.mine.DSApplyBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.helper.ImageHelper;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.AllApi;
import com.tongcheng.soothsay.ui.activity.calculate.zhuanyun.CreditsIntroActivity;
import com.tongcheng.soothsay.ui.activity.classroom.ShopCartActivity;


import com.tongcheng.soothsay.ui.activity.mine.ApplyMasterActivity;
import com.tongcheng.soothsay.ui.activity.mine.ApplyMasterStatusActivity;
import com.tongcheng.soothsay.ui.activity.mine.LoginActivity;
import com.tongcheng.soothsay.ui.activity.mine.MineInfoActivity;
import com.tongcheng.soothsay.ui.activity.mine.MineOrderActivity;
import com.tongcheng.soothsay.ui.activity.mine.MyMessageActivity;
import com.tongcheng.soothsay.ui.activity.mine.SettingActivity;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.CusDialogUtil;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;
import com.living.utils.GotoUtil;
import com.tongcheng.soothsay.utils.SystemTools;
import com.tongcheng.soothsay.utils.ToastUtil;
import com.tongcheng.soothsay.widget.RoundImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.CSCustomServiceInfo;


/**
 * Created by 宋家任 on 2016/11/21.
 * 我的界面
 */
public class MineFragment extends BaseTitleFragment {

    @BindView(R.id.iv_mine_photo)
    RoundImageView ivUserPhoto;
    @BindView(R.id.tv_mine_name)
    TextView tvName;
    @BindView(R.id.rl_mine_head)
    RelativeLayout rlHead;
    @BindView(R.id.rl_mine_sqds)
    RelativeLayout rlSqds;
    @BindView(R.id.rl_mine_dingdan)
    RelativeLayout rlDingdan;
    @BindView(R.id.rl_mine_jifen)
    RelativeLayout rlJifen;
    @BindView(R.id.rl_mine_xiaoxi)
    RelativeLayout rlXiaoxi;
    @BindView(R.id.rl_mine_kaiyun)
    RelativeLayout rlKaiyun;
    @BindView(R.id.rl_mine_kefu)
    RelativeLayout rlKefu;
    @BindView(R.id.rl_mine_setting)
    RelativeLayout rlSetting;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_mine2, container, false);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        return view;
    }


    @Override
    public void initData() {
        super.initData();
//        getBaseHeadView().showTitle("我的");
        showTopview();
    }


    /**
     * 头部view显示
     */
    private boolean showTopview() {
        if (UserManager.getInstance().isLogin()) {
            tvName.setText(UserManager.getInstance().getUserName(getActivity()));
            String img = UserManager.getInstance().getUserPhotoUrl(getActivity());
            if (!"".equals(img)) {
                ImageHelper.getInstance().display(img, ivUserPhoto);
            }
            return true;
        } else {
            tvName.setText("立即登录享更多体验");
            return false;
        }
    }


    @OnClick({R.id.rl_mine_head, R.id.iv_mine_photo, R.id.tv_mine_name, R.id.tv_me_head_setting, R.id.iv_me_head_card, R.id.iv_me_head_message, R.id.ll_me_head_shop, R.id.rl_mine_sqds, R.id.rl_mine_dingdan,
            R.id.rl_mine_jifen, R.id.rl_mine_xiaoxi, R.id.rl_mine_kaiyun,
            R.id.rl_mine_cart, R.id.rl_mine_kefu, R.id.rl_mine_setting})
    public void onClick(View view) {
        if (ClickUtil.isFastClick()) {
            return;
        }
        switch (view.getId()) {
            //   case R.id.rl_mine_head://头像
            case R.id.iv_mine_photo:
            case R.id.tv_mine_name:
                if (UserManager.getInstance().isLogin()) {//登录跳转个人详情页
                    GotoUtil.GoToActivity(getActivity(), MineInfoActivity.class);
                } else {//没登录跳转登录页
                    GotoUtil.GoToActivityForResult(getActivity(), LoginActivity.class, Constant.RequestCode.LOGIN);
                }
                break;
            case R.id.rl_mine_sqds://申请大师
                if (UserManager.getInstance().isLogin()) {
                    getApplyStatus();
                } else {
                    ToastUtil.showShort(getActivity(), "请先登录");
                }
                break;
            case R.id.rl_mine_dingdan://我的订单
                if (UserManager.getInstance().isLogin()) {
                    GotoUtil.GoToActivity(getActivity(), MineOrderActivity.class);
                } else {
                    GotoUtil.GoToActivityForResult(getActivity(), LoginActivity.class, Constant.RequestCode.LOGIN);
                }
                break;
            case R.id.rl_mine_jifen://积分活动
                if (UserManager.getInstance().isLogin()) {
                    GotoUtil.GoToActivity(getActivity(), CreditsIntroActivity.class);
                } else {
                    new CusDialogUtil(this.getActivity()).showDefaultLoginDialog(R.string.login_now);
                }
                break;
            case R.id.iv_me_head_message:
            case R.id.rl_mine_xiaoxi://我的消息
                if (UserManager.getInstance().isLogin()) {
                    GotoUtil.GoToActivity(getActivity(), MyMessageActivity.class);
                } else {
                    GotoUtil.GoToActivity(getActivity(), LoginActivity.class);
                }
                break;
            case R.id.ll_me_head_shop:
            case R.id.rl_mine_kaiyun://开运商城
                GotoUtil.GoToActivity(MineFragment.this.getActivity(), ShoppingHomeActivity.class);
                break;
            case R.id.iv_me_head_card:
            case R.id.rl_mine_cart://购物车
                GotoUtil.GoToActivity(MineFragment.this.getActivity(), ShopCartActivity.class);
                break;
            case R.id.rl_mine_kefu://联系客服
                if (UserManager.getInstance().isLogin()) {
                    final String qqUrl = "mqqwpa://im/chat?chat_type=wpa&uin=1120575939&version=1";
                    boolean qqClientAvailable = SystemTools.isQQClientAvailable(getActivity());
                    if (qqClientAvailable) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(qqUrl)));
                    } else {
                        ToastUtil.showShort(getContext(), "请先安装QQ客户端");
                    }
                } else {
                    new CusDialogUtil(this.getActivity()).showDefaultLoginDialog(R.string.login_now);
                }
                gotoKefu();
                break;
            case R.id.tv_me_head_setting:
            case R.id.rl_mine_setting://设置
                GotoUtil.GoToActivity(this.getActivity(), SettingActivity.class);
                break;
            default:
                break;
        }
    }

    /**
     * 联系客服
     */
    private void gotoKefu() {
        if (UserManager.getInstance().isLogin(MineFragment.this.getActivity()) && RongIM.getInstance() != null) {
            //首先需要构造使用客服者的用户信息
            CSCustomServiceInfo.Builder csBuilder = new CSCustomServiceInfo.Builder();
            CSCustomServiceInfo csInfo = csBuilder.nickName("融云").build();

            /**
             * 启动客户服聊天界面。
             *
             * @param context           应用上下文。
             * @param customerServiceId 要与之聊天的客服 Id。
             * @param title             聊天的标题，如果传入空值，则默认显示与之聊天的客服名称。
             * @param customServiceInfo 当前使用客服者的用户信息。{@link io.rong.imlib.model.CSCustomServiceInfo}
             */
            RongIM.getInstance().startCustomerServiceChat(MineFragment.this.getActivity(), Constant.KEFU_ID, "在线客服", csInfo);
        }

    }

    /**
     * 获取大师申请状态
     */
    private void getApplyStatus() {
        AllApi.getInstance().getdsapplyinfo(UserManager.getInstance().getToken()).
                enqueue(new ApiCallBack<ApiResponseBean<DSApplyBean>>(new BaseCallBack() {
                    @Override
                    public void onSuccess(Object data) {
                        DSApplyBean bean = (DSApplyBean) data;
                        if ("0".equals(bean.getAduit())) {//申请
                            Intent intent = new Intent(MineFragment.this.getActivity(), ApplyMasterStatusActivity.class);
                            intent.putExtra("status", "0");
                            startActivity(intent);
                            return;
                        }
                        if ("1".equals(bean.getAduit())) {//审核通过
                            Intent intent = new Intent(MineFragment.this.getActivity(), ApplyMasterStatusActivity.class);
                            intent.putExtra("status", "1");
                            startActivity(intent);
                            return;
                        }
                        if ("2".equals(bean.getAduit())) {//审核不通过
                            Intent intent = new Intent(MineFragment.this.getActivity(), ApplyMasterStatusActivity.class);
                            intent.putExtra("status", "2");
                            intent.putExtra("reason", bean.getReason());
                            startActivity(intent);
                            return;
                        } else {
                            GotoUtil.GoToActivity(getActivity(), ApplyMasterActivity.class);
                        }
                    }

                    @Override
                    public void onError(String errorCode, String message) {
//                        ErrorCodeUtil.showHaveTokenError(MineFragment.this.getActivity(), errorCode);
                        boolean b = ErrorCodeUtil.showHaveTokenError(MineFragment.this.getActivity(), errorCode);
                        if (b == false) {
                            ToastUtil.showShort(MineFragment.this.getActivity(), message);
                        }
                    }
                }));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constant.RequestCode.LOGIN && resultCode == Constant.ResultCode.LOGIN) {
            showTopview();
        }

    }

    //刷新头像
    @Subscribe
    public void onEvent(EventLoginBean bean) {
        String img = UserManager.getInstance().getUserPhotoUrl(getActivity());
        tvName.setText(UserManager.getInstance().getUserName(getActivity()));
        if (!"".equals(img)) {
            ImageHelper.getInstance().display(img, ivUserPhoto);
        }
    }

//    //刷新头像
//    @Subscribe
//    public void onEvent(LoginRefreshBean bean) {
//        String img = UserManager.getInstance().getUserPhotoUrl(getActivity());
//        tvName.setText(UserManager.getInstance().getUserName(getActivity()));
//        ImageHelper.getInstance().display(img, ivUserPhoto);
//    }

    //刷新数据
    @Subscribe
    public void onEvent(DontLoginBean bean) {
        ivUserPhoto.setImageResource(R.drawable.default_head_icon);
        tvName.setText("立即登录享更多体验");
    }

    @Subscribe
    public void onEvent(ChangeNameBean bean) {
        String img = UserManager.getInstance().getUserPhotoUrl(getActivity());
        if (!"".equals(img)) {
            ImageHelper.getInstance().display(img, ivUserPhoto);
        }
        tvName.setText(UserManager.getInstance().getUserName(getActivity()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
