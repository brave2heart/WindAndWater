package com.tongcheng.soothsay.ui.activity.mine;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.bigkoo.pickerview.TimePickerView;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.mine.WorkAndHunYinStatusListAdapter;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.event.ChangeNameBean;
import com.tongcheng.soothsay.bean.event.DontLoginBean;
import com.tongcheng.soothsay.bean.event.EventLoginBean;
import com.tongcheng.soothsay.bean.event.LoginRefreshBean;
import com.tongcheng.soothsay.bean.mine.HeadPicBean;
import com.tongcheng.soothsay.bean.mine.MineInfoBean;
import com.tongcheng.soothsay.bean.mine.WorkAndHunYinListBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.helper.ImageHelper;
import com.tongcheng.soothsay.helper.JPushHelper;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.AllApi;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.CusDialogUtil;
import com.tongcheng.soothsay.utils.DateUtil;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;
import com.tongcheng.soothsay.utils.EventBusUtil;
import com.tongcheng.soothsay.utils.FileUtil;
import com.tongcheng.soothsay.utils.GoodsIdUtils;
import com.tongcheng.soothsay.utils.GsonUtil;
import com.tongcheng.soothsay.utils.HttpUtils;
import com.tongcheng.soothsay.utils.ImageUtils;
import com.tongcheng.soothsay.utils.LogUtil;
import com.tongcheng.soothsay.utils.ToastUtil;
import com.tongcheng.soothsay.widget.RoundImageView;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


/**
 * Created by 宋家任 on 2016/11/22.
 * 用户个人信息界面
 */

public class MineInfoActivity extends BaseTitleActivity implements TimePickerView.OnTimeSelectListener {

    @BindView(R.id.iv_mine_info_photo)
    RoundImageView ivMineInfoPhoto;//头像
    @BindView(R.id.rl_mine_info_photo)
    RelativeLayout rlMineInfoPhoto;
    @BindView(R.id.tv_mine_info_bangding)
    TextView tvMineInfoBangding;//手机号
    @BindView(R.id.rl_mine_info_phone)
    RelativeLayout rlMineInfoPhone;
    @BindView(R.id.tv_mine_info_name)
    TextView tvMineInfoName;//昵称
    @BindView(R.id.rl_mine_info_name)
    RelativeLayout rlMineInfoName;
    @BindView(R.id.tv_mine_info_shengri)
    TextView tvMineInfoShengri;//生日
    @BindView(R.id.rl_mine_info_shengri)
    RelativeLayout rlMineInfoShengri;
    @BindView(R.id.tv_mine_info_sex)
    TextView tvMineInfoSex;//性别
    @BindView(R.id.rl_mine_info_sex)
    RelativeLayout rlMineInfoSex;
    @BindView(R.id.tv_mine_info_work)
    TextView tvMineInfoWork;//工作
    @BindView(R.id.rl_mine_info_work)
    RelativeLayout rlMineInfoWork;
    @BindView(R.id.tv_mine_info_hunyin)
    TextView tvMineInfoHunyin;//婚姻状况
    @BindView(R.id.rl_mine_info_hunyin)
    RelativeLayout rlMineInfoHunyin;
    @BindView(R.id.btn_tuichu)
    BootstrapButton btnTuichu;//退出登录

    public static final int ACTION_TYPE_ALBUM = 0;
    public static final int ACTION_TYPE_PHOTO = 1;
    private Uri origUri;
    private Uri cropUri;
    private String theLarge;
    private final static String FILE_SAVEPATH = Environment
            .getExternalStorageDirectory().getAbsolutePath()
            + "/Wzl/Portrait/";
    private File protraitFile;
    private String protraitPath;
    private Bitmap protraitBitmap;
    private final static int CROP = 200;

    private TimePickerView timeView;
    private String time = "1970.08.08.08";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_mine_info);
        initData();
    }

    @Override
    public void initData() {
        initBaseHeadView();
        getBaseLoadingView().showLoading();
        getMineInfo();
    }

    private void initBaseHeadView() {
        getBaseHeadView().showTitle("我的资料");
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new ChangeNameBean());
                UserManager.getInstance().setUserName(MineInfoActivity.this, UserManager.getInstance().getUserName(MineInfoActivity.this));
                MineInfoActivity.this.finish();
            }
        });
    }

    /**
     * 获取用户信息
     */
    private void getMineInfo() {
        String token = UserManager.getInstance().getUser().getToken();
        if (!"".equals(token))
            AllApi.getInstance().MineInfo(token)
                    .enqueue(new ApiCallBack<ApiResponseBean<MineInfoBean>>(new BaseCallBack() {
                        @Override
                        public void onSuccess(Object data) {
                            getBaseLoadingView().hideLoading();
                            MineInfoBean bean = (MineInfoBean) data;
                            if (!"".equals(bean.getHeadPic()))
                                ImageHelper.getInstance().display(bean.getHeadPic(), ivMineInfoPhoto);  //头像
                            UserManager.getInstance().setUserPhotoUrl(MineInfoActivity.this, bean.getHeadPic());
                            UserManager.getInstance().setUserJf(MineInfoActivity.this, bean.getJf());
                            tvMineInfoBangding.setText(bean.getPhone()); //手机号
                            tvMineInfoName.setText(bean.getName());//昵称
                            UserManager.getInstance().setUserName(MineInfoActivity.this, bean.getName());
                            if (!"".equals(bean.getBornTime())) {
                                String s = DateUtil.stampToDate(bean.getBornTime());
                                tvMineInfoShengri.setText(s);//出生日期
                                UserManager.getInstance().setUserDate(MineInfoActivity.this, s);
                                LogUtil.printD("my:" + s);
                            }
                            if (!"0".equals(bean.getSex())) {
                                if ("1".equals(bean.getSex()))
                                    tvMineInfoSex.setText("男");//性别
                                else if ("2".equals(bean.getSex()))
                                    tvMineInfoSex.setText("女");
                            }

                            if (!"0".equals(bean.getWorkStatus())) {
                                if ("1".equals(bean.getWorkStatus())) {
                                    tvMineInfoWork.setText("待业");//工作状况
                                } else if ("2".equals(bean.getWorkStatus())) {
                                    tvMineInfoWork.setText("学生");
                                } else if ("3".equals(bean.getWorkStatus())) {
                                    tvMineInfoWork.setText("个体经营");
                                } else if ("4".equals(bean.getWorkStatus())) {
                                    tvMineInfoWork.setText("公务员");
                                } else if ("5".equals(bean.getWorkStatus())) {
                                    tvMineInfoWork.setText("白领");
                                } else if ("6".equals(bean.getWorkStatus())) {
                                    tvMineInfoWork.setText("公司高管");
                                } else if ("7".equals(bean.getWorkStatus())) {
                                    tvMineInfoWork.setText("退休");
                                }
                            }

                            if (!"0".equals(bean.getMarriageStatus()))
                                if ("1".equals(bean.getMarriageStatus())) {
                                    tvMineInfoHunyin.setText("已婚");//婚姻状况
                                } else if ("2".equals(bean.getMarriageStatus())) {
                                    tvMineInfoHunyin.setText("单身");//婚姻状况
                                } else if ("3".equals(bean.getMarriageStatus())) {
                                    tvMineInfoHunyin.setText("恋爱中");//婚姻状况
                                }


                        }

                        @Override
                        public void onError(String errorCode, String message) {
                            ErrorCodeUtil.showEmptyView(MineInfoActivity.this, errorCode, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (ClickUtil.isFastClick()) return;
                                    initData();
                                    getBaseEmptyView().hideEmptyView();
                                }
                            }, 1688);
                            getBaseLoadingView().hideLoading();
                        }
                    }));

        else {//基本上不会触发这个方法，但是程序健壮性考虑
            ToastUtil.showShort(this, "链接出错");
            getBaseLoadingView().hideLoading();
            return;
        }
    }


    @OnClick({R.id.rl_mine_info_photo, R.id.rl_mine_info_phone, R.id.rl_mine_info_name,
            R.id.rl_mine_info_shengri, R.id.rl_mine_info_sex, R.id.rl_mine_info_work,
            R.id.rl_mine_info_hunyin, R.id.btn_tuichu})
    public void onClick(View view) {
        if (ClickUtil.isFastClick()) return;

        switch (view.getId()) {
            case R.id.rl_mine_info_photo://修改头像
                showPhotoDialog();
                break;
            case R.id.rl_mine_info_phone://绑定手机号
                break;
            case R.id.rl_mine_info_name://修改姓名
                Intent intent1 = new Intent(this, ChangNameActivity.class);
                startActivityForResult(intent1, Constant.RequestCode.CHANGE_NAME);
                break;
            case R.id.rl_mine_info_shengri://设置生日
                showTimePicker();
                break;
            case R.id.rl_mine_info_sex://性别
                showSexDialog();
                break;
            case R.id.rl_mine_info_work://工作状态
                showWorkStatusDialog();
                break;
            case R.id.rl_mine_info_hunyin://婚姻状况
                showHunyinDialog();
                break;
            case R.id.btn_tuichu://退出登录
                new CusDialogUtil(this).showCusDefaultDialog("退出登录", "是否确认退出登录？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (UserManager.getInstance().isLogin()) {
                            dialog.dismiss();
                        } else {
                            dialog.dismiss();
                            MineInfoActivity.this.finish();
                            EventBusUtil.post(new DontLoginBean());
                        }
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (UserManager.getInstance().isLogin()) {
                            UserManager.getInstance().deleteUser();
                            UserManager.getInstance().setUserPhotoUrl(MineInfoActivity.this, "");
                            UserManager.getInstance().setUserName(MineInfoActivity.this, "");
                            UserManager.getInstance().setUserDate(MineInfoActivity.this, "");
                            GoodsIdUtils.getInstance().deleteGoodsList();
                            //退出登录收不到推送
                            JPushHelper.getInstance().setJPushAlias(MineInfoActivity.this, null);

                            EventBusUtil.post(new EventLoginBean(EventLoginBean.OUT_LOGIN));
                            Intent intent = new Intent(MineInfoActivity.this, LoginActivity.class);
                            startActivityForResult(intent, Constant.RequestCode.EXIT_LOGIN);
                            MineInfoActivity.this.finish();
                        } else {
                            EventBusUtil.post(new EventLoginBean(EventLoginBean.OUT_LOGIN));
                            Intent intent = new Intent(MineInfoActivity.this, LoginActivity.class);
                            startActivityForResult(intent, Constant.RequestCode.EXIT_LOGIN);
                            MineInfoActivity.this.finish();
                        }
                    }
                });
                break;
        }
    }


    String[] work = {"待业", "学生", "个体经营", "公务员", "白领", "公司高管", "退休"};

    /**
     * 工作状态
     */
    private void showWorkStatusDialog() {

        List<WorkAndHunYinListBean> datas = new ArrayList<>();
        for (int i = 0; i < work.length; i++) {
            WorkAndHunYinListBean bean = new WorkAndHunYinListBean(work[i]);
            datas.add(bean);
        }
        WorkAndHunYinStatusListAdapter adapter = new WorkAndHunYinStatusListAdapter(this, datas, R.layout.item_dialog_mine);
        final ActionSheetDialog dialog = new ActionSheetDialog(this, adapter, null);
        dialog.isTitleShow(false).show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        changeWork("1", 0);
                        break;
                    case 1:
                        changeWork("2", 1);
                        break;
                    case 2:
                        changeWork("3", 2);
                        break;
                    case 3:
                        changeWork("4", 3);
                        break;
                    case 4:
                        changeWork("5", 4);
                        break;
                    case 5:
                        changeWork("6", 5);
                        break;
                    case 6:
                        changeWork("7", 6);
                        break;

                }
                dialog.dismiss();
            }
        });

    }

    private void changeWork(String item, final int position) {
        AllApi.getInstance().changeWork(item, UserManager.getInstance().getUser().getToken())
                .enqueue(new ApiCallBack<ApiResponseBean<Object>>(new BaseCallBack() {
                    @Override
                    public void onSuccess(Object data) {
                        tvMineInfoWork.setText(work[position]);
                    }

                    @Override
                    public void onError(String errorCode, String message) {
//                        ErrorCodeUtil.showHaveTokenError(MineInfoActivity.this, errorCode);
                        boolean b = ErrorCodeUtil.showHaveTokenError(MineInfoActivity.this, errorCode);
                        if (b == false) {
                            ToastUtil.showShort(MineInfoActivity.this, message);
                        }
                    }
                }));

    }

    //显示时间控件
    private void showTimePicker() {
        if (timeView == null) {
            timeView = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
            timeView.setCyclic(false);
            timeView.setTime(new Date());
            timeView.setOnTimeSelectListener(this);
        }
        timeView.show();
    }

    /**
     * 性别弹窗
     */
    private void showSexDialog() {
        final String[] stringItems = {"男", "女"};
        final ActionSheetDialog dialog = new ActionSheetDialog(this, stringItems, null);
        dialog.isTitleShow(false).show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        AllApi.getInstance().changeSex("1", UserManager.getInstance().getUser().getToken())
                                .enqueue(new ApiCallBack<ApiResponseBean<Object>>(new BaseCallBack() {
                                    @Override
                                    public void onSuccess(Object data) {
                                        tvMineInfoSex.setText("男");
                                    }

                                    @Override
                                    public void onError(String errorCode, String message) {
                                        ToastUtil.showShort(MineInfoActivity.this, "修改错误:" + message);
                                    }
                                }));
                        break;
                    case 1:
                        AllApi.getInstance().changeSex("2", UserManager.getInstance().getUser().getToken())
                                .enqueue(new ApiCallBack<ApiResponseBean<Object>>(new BaseCallBack() {
                                    @Override
                                    public void onSuccess(Object data) {
                                        tvMineInfoSex.setText("女");
                                    }

                                    @Override
                                    public void onError(String errorCode, String message) {
                                        ToastUtil.showShort(MineInfoActivity.this, "修改错误:" + message);
                                    }
                                }));
                        break;
                }
                dialog.dismiss();
            }
        });
    }

    /**
     * 婚姻状态
     */
    private void showHunyinDialog() {
        final String[] stringItems = {"已婚", "单身", "恋爱中"};
        final ActionSheetDialog dialog = new ActionSheetDialog(this, stringItems, null);
        dialog.isTitleShow(false).show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        changeHunyin("1", stringItems[0]);
                        break;
                    case 1:
                        changeHunyin("2", stringItems[1]);
                        break;
                    case 2:
                        changeHunyin("3", stringItems[2]);
                        break;
                }
                dialog.dismiss();
            }
        });
    }

    private void changeHunyin(String item, final String hunyin) {
        AllApi.getInstance().changeHunyin(item, UserManager.getInstance().getUser().getToken())
                .enqueue(new ApiCallBack<ApiResponseBean<Object>>(new BaseCallBack() {
                    @Override
                    public void onSuccess(Object data) {
                        tvMineInfoHunyin.setText(hunyin);
                    }

                    @Override
                    public void onError(String errorCode, String message) {
                        ToastUtil.showShort(MineInfoActivity.this, "修改错误:" + message);
                    }
                }));
    }

    /**
     * 展示相片选择对话框
     */
    public void showPhotoDialog() {
        final String[] stringItems = {"相机", "相册"};
        final ActionSheetDialog dialog = new ActionSheetDialog(this, stringItems, null);
        dialog.isTitleShow(false).show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0://获取权限
                        Acp.getInstance(MineInfoActivity.this).request(new AcpOptions.Builder().setPermissions(
                                Manifest.permission.CAMERA,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                .build(), new AcpListener() {
                            @Override
                            public void onGranted() {
                                goToSelectPicture(ACTION_TYPE_PHOTO);
                            }

                            @Override
                            public void onDenied(List<String> permissions) {

                            }
                        });
                        break;
                    case 1:
                        goToSelectPicture(ACTION_TYPE_ALBUM);
                        break;
                }
                dialog.dismiss();
            }
        });
    }

    /**
     * 选择图片
     *
     * @param position
     */
    private void goToSelectPicture(int position) {
        switch (position) {
            case ACTION_TYPE_ALBUM:
                startImagePick();
                break;
            case ACTION_TYPE_PHOTO:
                startTakePhoto();
                break;
            default:
                break;
        }
    }

    /**
     * 选择图片裁剪
     */
    private void startImagePick() {
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "选择图片"),
                    ImageUtils.REQUEST_CODE_GETIMAGE_BYCROP);
        } else {
            intent = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "选择图片"),
                    ImageUtils.REQUEST_CODE_GETIMAGE_BYCROP);
        }
    }

    private void startTakePhoto() {
        Intent intent;
        // 判断是否挂载了SD卡
        String savePath = "";
        String storageState = Environment.getExternalStorageState();
        if (storageState.equals(Environment.MEDIA_MOUNTED)) {
            savePath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + "/smds/Camera/";
            File savedir = new File(savePath);
            if (!savedir.exists()) {
                savedir.mkdirs();
            }
        }

        // 没有挂载SD卡，无法保存文件
        if (TextUtils.isEmpty(savePath)) {
            ToastUtil.showShort(this, "无法保存照片，请检查SD卡是否挂载");
            return;
        }
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date());
        String fileName = "smds_" + timeStamp + ".jpg";// 照片命名
        File out = new File(savePath, fileName);
        ContentValues contentValues = new ContentValues(1);
        contentValues.put(MediaStore.Images.Media.DATA, out.getAbsolutePath());
        Uri uri = this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        origUri = uri;
        theLarge = savePath + fileName;// 该照片的绝对路径
        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent,
                ImageUtils.REQUEST_CODE_GETIMAGE_BYCAMERA);
    }


    /**
     * 裁剪头像的绝对路径
     *
     * @param uri
     * @return
     */
    private Uri getUploadTempFile(Uri uri) {
        if (uri != null) {
            String storageState = Environment.getExternalStorageState();
            if (storageState.equals(Environment.MEDIA_MOUNTED)) {
                File savedir = new File(FILE_SAVEPATH);
                if (!savedir.exists()) {
                    savedir.mkdirs();
                }
            } else {
                ToastUtil.showShort(this, "无法保存上传的头像，请检查SD卡是否挂载");
                return null;
            }
            String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss")
                    .format(new Date());
            String thePath = ImageUtils.getAbsolutePathFromNoStandardUri(uri);

            // 如果是标准Uri
            if (TextUtils.isEmpty(thePath)) {
                thePath = ImageUtils.getAbsoluteImagePath(this, uri);
            }
            String ext = FileUtil.getFileFormat(thePath);
            ext = TextUtils.isEmpty(ext) ? "jpg" : ext;
            // 照片命名
            String cropFileName = "osc_crop_" + timeStamp + "." + ext;
            // 裁剪头像的绝对路径
            protraitPath = FILE_SAVEPATH + cropFileName;
            protraitFile = new File(protraitPath);

            cropUri = Uri.fromFile(protraitFile);
            return this.cropUri;
        } else {
            //用户手机内存不足时
            ToastUtil.showLong(MineInfoActivity.this, "异常错误请重试..");
            return null;
        }

    }

    /**
     * 拍照后裁剪
     *
     * @param data 原始图片
     */
    private void startActionCrop(Uri data) {
        if (data != null) {
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(data, "image/*");
            intent.putExtra("output", this.getUploadTempFile(data));
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 1);// 裁剪框比例
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", CROP);// 输出图片大小
            intent.putExtra("outputY", CROP);
            intent.putExtra("scale", true);// 去黑边
            intent.putExtra("scaleUpIfNeeded", true);// 去黑边
            startActivityForResult(intent,
                    ImageUtils.REQUEST_CODE_GETIMAGE_BYSDCARD);
        } else ToastUtil.showLong(this, "异常错误请重试..");
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode,
                                 final Intent imageReturnIntent) {
        if (requestCode == 1688 && resultCode == Constant.ResultCode.LOGIN) {
            initData();
        }
        if (requestCode == 98 && resultCode == 99) {//绑定手机号

        }

        if (requestCode == Constant.RequestCode.CHANGE_NAME && resultCode == 1000) {//设置用户名
            String name = imageReturnIntent.getStringExtra("name");
            tvMineInfoName.setText(name);
        }
        if (requestCode == Constant.RequestCode.EXIT_LOGIN && resultCode == 9999) {//重新登录之后
            getMineInfo();
        }
        if (resultCode != Activity.RESULT_OK)
            return;

        switch (requestCode) {
            case ImageUtils.REQUEST_CODE_GETIMAGE_BYCAMERA:
                startActionCrop(origUri);// 拍照后裁剪
                break;
            case ImageUtils.REQUEST_CODE_GETIMAGE_BYCROP:
                startActionCrop(imageReturnIntent.getData());// 选图后裁剪
                break;
            case ImageUtils.REQUEST_CODE_GETIMAGE_BYSDCARD:
                uploadNewPhoto();
                break;
        }
    }

    /**
     * 上传新照片
     */
    private void uploadNewPhoto() {
        // 获取头像缩略图
        if (!TextUtils.isEmpty(protraitPath) && protraitFile.exists()) {
            protraitBitmap = ImageUtils
                    .loadImgThumbnail(protraitPath, 150, 150);
        } else {
            ToastUtil.showShort(this, "图像不存在，上传失败");
        }
        if (protraitBitmap != null) {
            //封装请求参数
            final List<String[]> params = new ArrayList<String[]>();
            String[] content = {"token", UserManager.getInstance().getUser().getToken()};
            params.add(content);


            //封装图片参数
            final List<String[]> pathList = new ArrayList<String[]>();
            final String filePath = protraitFile.getPath();
            String[] files = {"file", filePath};
            pathList.add(files);
            Observable.create(new Observable.OnSubscribe<String>() {

                @Override
                public void call(Subscriber<? super String> subscriber) {
                    HttpUtils utils = new HttpUtils();
                    String response = utils.postMultipart(Constant.Url.UPLOADHEADPIC, params, pathList);
                    subscriber.onNext(response);
                }
            })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Action1<String>() {
                        @Override
                        public void call(String response) {
                            HeadPicBean bean = (HeadPicBean) GsonUtil.jsonToBean(response, HeadPicBean.class);
                            if ("00000".equals(bean.getErrorCode())) {
                                ImageHelper.getInstance().display(bean.getResult().getHeadPic(), ivMineInfoPhoto);
                                UserManager.getInstance().setUserPhotoUrl(MineInfoActivity.this, bean.getResult().getHeadPic());
                                //修改成功
                                EventBus.getDefault().post(new EventLoginBean(EventLoginBean.LOGIN));
                            } else {
                                ToastUtil.showShort(MineInfoActivity.this, bean.getMessage());
                            }


                        }
                    });


//            AllApi.getInstance().headPic(UserManager.getInstance().getUser().getToken(), protraitFile)
//                    .enqueue(new ApiCallBack<ApiResponseBean<HeadPicBean>>(new BaseCallBack() {
//                        @Override
//                        public void onSuccess(Object data) {
//                            ToastUtil.showShort(MineInfoActivity.this, "上传成功");
//                            HeadPicBean bean = (HeadPicBean) data;
//                            String picUrl = bean.getHeadPic();
//                            SpUtil.putString(MineInfoActivity.this, "pic", picUrl);
//                            ImageHelper.getInstance().display(picUrl, ivMineInfoPhoto);
//                        }
//
//                        @Override
//                        public void onError(String errorCode, String message) {
//                            LogUtil.printD("errorcode:" + errorCode);
//                            ToastUtil.showShort(MineInfoActivity.this, message);
//                        }
//                    }));
        }
    }

    /**
     * 时间选择器监听
     *
     * @param date
     */
    @Override
    public void onTimeSelect(Date date) {
        final long time = date.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        this.time = sdf.format(date);
        final String temp[] = this.time.split("\\.");
        AllApi.getInstance().changeDate(time, UserManager.getInstance().getUser().getToken())
                .enqueue(new ApiCallBack<ApiResponseBean<Object>>(new BaseCallBack() {
                    @Override
                    public void onSuccess(Object data) {
                        tvMineInfoShengri.setText(temp[0] + "." + temp[1] + "." + temp[2]);
                        UserManager.getInstance().setUserDate(MineInfoActivity.this, MineInfoActivity.this.time);
                        LogUtil.printD("time:" + MineInfoActivity.this.time);
//                        UserManager.getInstance().setUserDate(MineInfoActivity.this, temp[0] + "年" + temp[1] + "月" + temp[2] + "日");
                    }

                    @Override
                    public void onError(String errorCode, String message) {
//                        ErrorCodeUtil.showHaveTokenError(MineInfoActivity.this, errorCode);
                        boolean b = ErrorCodeUtil.showHaveTokenError(MineInfoActivity.this, errorCode);
                        if (b == false) {
                            ToastUtil.showShort(MineInfoActivity.this, message);
                        }
                    }
                }));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            EventBus.getDefault().post(new ChangeNameBean());
            UserManager.getInstance().setUserName(this, UserManager.getInstance().getUserName(this));
            this.finish();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }
}
