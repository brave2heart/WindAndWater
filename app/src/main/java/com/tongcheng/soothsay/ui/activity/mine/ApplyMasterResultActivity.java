package com.tongcheng.soothsay.ui.activity.mine;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;
import com.nostra13.universalimageloader.utils.L;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.mine.DSPicBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.helper.ImageHelper;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.AllApi;
import com.tongcheng.soothsay.ui.activity.calculate.zhuanyun.VirtueTransferActivity;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.CusDialogUtil;
import com.tongcheng.soothsay.utils.DialogUtil;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;
import com.tongcheng.soothsay.utils.FileUtil;
import com.tongcheng.soothsay.utils.GsonUtil;
import com.tongcheng.soothsay.utils.HttpUtils;
import com.tongcheng.soothsay.utils.ImageUtils;
import com.tongcheng.soothsay.utils.LogUtil;
import com.tongcheng.soothsay.utils.ToastUtil;
import com.tongcheng.soothsay.widget.BigRoundImageView;

import java.io.File;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by 宋家任 on 2016/12/5.
 * 申请页
 */

public class ApplyMasterResultActivity extends BaseTitleActivity {

    @BindView(R.id.briv_master_touxiang)
    BigRoundImageView brivMasterTouxiang;
    @BindView(R.id.briv_master_shenfenzheng_zheng)
    BigRoundImageView brivMasterShenfenzhengZheng;
    @BindView(R.id.briv_master_shenfenzheng_fan)
    BigRoundImageView brivMasterShenfenzhengFan;

    @BindView(R.id.et_master_name)
    TextInputEditText etMasterName;
    @BindView(R.id.et_master_chenghao)
    TextInputEditText etMasterChenghao;
    @BindView(R.id.et_master_jianjie)
    TextInputEditText etMasterJianjie;
    @BindView(R.id.et_master_shanchang)
    TextInputEditText etMasterShanchang;
    @BindView(R.id.briv_master_zhengshu1)
    BigRoundImageView brivMasterZhengshu1;
    //    @BindView(R.id.briv_master_zhengshu2)
//    BigRoundImageView brivMasterZhengshu2;
//    @BindView(R.id.briv_master_zhengshu3)
//    BigRoundImageView brivMasterZhengshu3;
//    @BindView(R.id.briv_master_zhengshu4)
//    BigRoundImageView brivMasterZhengshu4;
    @BindView(R.id.briv_master_richang1)
    BigRoundImageView brivMasterRichang1;
    @BindView(R.id.briv_master_richang2)
    BigRoundImageView brivMasterRichang2;
    @BindView(R.id.btn_master_submit)
    BootstrapButton btnMasterSubmit;

    public static final int ACTION_TYPE_ALBUM = 0;
    public static final int ACTION_TYPE_PHOTO = 1;
    private Uri origUri;
    private Uri cropUri;
    private String theLarge;
    private File protraitFile;
    private String protraitPath;
    private Bitmap protraitBitmap;
    private final static String FILE_SAVEPATH = Environment
            .getExternalStorageDirectory().getAbsolutePath()
            + "/Wzl/Portrait/";
    private final static int CROP = 200;

    private String touxiangUrl;
    private String sfzZhengmian;
    private String sfzFanmian;
    private String zhengshu1;
    //    private String zhengshu2;
//    private String zhengshu3;
//    private String zhengshu4;
    private String richang1;
    private String richang2;
    ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_apply_maseter_result);
        initData();
    }

    @Override
    public void initData() {
        getBaseHeadView().showTitle("个人身份信息");
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ApplyMasterResultActivity.this.finish();
            }
        });
        mDialog = new ProgressDialog(this);
    }

    int flag;

    @OnClick({R.id.briv_master_touxiang, R.id.briv_master_shenfenzheng_zheng,
            R.id.briv_master_shenfenzheng_fan, R.id.briv_master_zhengshu1,
            /*R.id.briv_master_zhengshu2, R.id.briv_master_zhengshu3,
            R.id.briv_master_zhengshu4,*/ R.id.briv_master_richang1,
            R.id.briv_master_richang2, R.id.btn_master_submit})
    public void onClick(View view) {
        if (ClickUtil.isFastClick()) return;

        switch (view.getId()) {
            case R.id.briv_master_touxiang:
                flag = 0;
                showPhotoDialog();
                break;
            case R.id.briv_master_shenfenzheng_zheng:
                flag = 1;
                showPhotoDialog();
                break;
            case R.id.briv_master_shenfenzheng_fan:
                flag = 2;
                showPhotoDialog();
                break;
            case R.id.briv_master_zhengshu1:
                flag = 3;
                showPhotoDialog();
                break;
//            case R.id.briv_master_zhengshu2:
//                flag = 4;
//                showPhotoDialog();
//                break;
//            case R.id.briv_master_zhengshu3:
//                flag = 5;
//                showPhotoDialog();
//                break;
//            case R.id.briv_master_zhengshu4:
//                flag = 6;
//                showPhotoDialog();
//                break;
            case R.id.briv_master_richang1:
                flag = 7;
                showPhotoDialog();
                break;
            case R.id.briv_master_richang2:
                flag = 8;
                showPhotoDialog();
                break;
            case R.id.btn_master_submit:
                saveApplyInfo();
                break;
        }
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
                    case 0:
                        Acp.getInstance(ApplyMasterResultActivity.this).
                                request(new AcpOptions.Builder().
                                                setPermissions(Manifest.permission.CAMERA,
                                                        Manifest.permission.WRITE_EXTERNAL_STORAGE).build(),
                                        new AcpListener() {
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

    // 裁剪头像的绝对路径
    private Uri getUploadTempFile(Uri uri) {
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
    }

    /**
     * 拍照后裁剪
     *
     * @param data 原始图片
     */
    private void startActionCrop(Uri data, int aspectX, int aspectY, int outputX, int outputY) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(data, "image/*");
        intent.putExtra("output", this.getUploadTempFile(data));
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", aspectX);// 裁剪框比例
        intent.putExtra("aspectY", aspectY);
        intent.putExtra("outputX", outputX);// 输出图片大小
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);// 去黑边
        intent.putExtra("scaleUpIfNeeded", true);// 去黑边
        startActivityForResult(intent,
                ImageUtils.REQUEST_CODE_GETIMAGE_BYSDCARD);
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode,
                                 final Intent imageReturnIntent) {
        switch (requestCode) {
            case ImageUtils.REQUEST_CODE_GETIMAGE_BYCAMERA:
                switch (flag) {
                    case 0:
                        if (origUri != null)
                            startActionCrop(origUri, 2, 3, 200, 300);// 拍照后裁剪
                        break;
                    case 1:
                        if (origUri != null)
                            startActionCrop(origUri, 3, 2, 300, 200);// 拍照后裁剪
                        break;
                    case 2:
                        if (origUri != null)
                            startActionCrop(origUri, 3, 2, 300, 200);// 拍照后裁剪
                        break;
                    case 3:
                        if (origUri != null)
                            startActionCrop(origUri, 3, 2, 300, 200);// 拍照后裁剪
                        break;
//                    case 4:
//                        startActionCrop(origUri, 3, 2, 300, 200);// 拍照后裁剪
//                        break;
//                    case 5:
//                        startActionCrop(origUri, 3, 2, 300, 200);// 拍照后裁剪
//                        break;
//                    case 6:
//                        startActionCrop(origUri, 3, 2, 300, 200);// 拍照后裁剪
//                        break;
                    case 7:
                        if (origUri != null)
                            startActionCrop(origUri, 3, 2, 300, 200);// 拍照后裁剪
                        break;
                    case 8:
                        if (origUri != null)
                            startActionCrop(origUri, 3, 2, 300, 200);// 拍照后裁剪
                        break;
                }
                break;
            case ImageUtils.REQUEST_CODE_GETIMAGE_BYCROP:
                switch (flag) {
                    case 0:
                        if (imageReturnIntent != null)
                            startActionCrop(imageReturnIntent.getData(), 2, 3, 200, 300);// 选图后裁剪
                        break;
                    case 1:
                        if (imageReturnIntent != null)
                            startActionCrop(imageReturnIntent.getData(), 3, 2, 300, 200);// 选图后裁剪
                        break;
                    case 2:
                        if (imageReturnIntent != null)
                            startActionCrop(imageReturnIntent.getData(), 3, 2, 300, 200);// 选图后裁剪
                        break;
                    case 3:
                        if (imageReturnIntent != null)
                            startActionCrop(imageReturnIntent.getData(), 3, 2, 300, 200);// 选图后裁剪
                        break;
//                    case 4:
//                        startActionCrop(imageReturnIntent.getData(), 3, 2, 300, 200);// 选图后裁剪
//                        break;
//                    case 5:
//                        startActionCrop(imageReturnIntent.getData(), 3, 2, 300, 200);// 选图后裁剪
//                        break;
//                    case 6:
//                        startActionCrop(imageReturnIntent.getData(), 3, 2, 300, 200);// 选图后裁剪
//                        break;
                    case 7:
                        if (imageReturnIntent != null)
                            startActionCrop(imageReturnIntent.getData(), 3, 2, 300, 200);// 选图后裁剪
                        break;
                    case 8:
                        if (imageReturnIntent != null)
                            startActionCrop(imageReturnIntent.getData(), 3, 2, 300, 200);// 选图后裁剪
                        break;
                }
                break;
            case ImageUtils.REQUEST_CODE_GETIMAGE_BYSDCARD:
                switch (flag) {
                    case 0:
                        uploadNewPhoto(200, 300);
                        break;
                    case 1:
                        uploadNewPhoto(300, 200);
                        break;
                    case 2:
                        uploadNewPhoto(300, 200);
                        break;
                    case 3:
                        uploadNewPhoto(300, 200);
                        break;
//                    case 4:
//                        uploadNewPhoto(300, 200);
//                        break;
//                    case 5:
//                        uploadNewPhoto(300, 200);
//                        break;
//                    case 6:
//                        uploadNewPhoto(300, 200);
//                        break;
                    case 7:
                        uploadNewPhoto(300, 200);
                        break;
                    case 8:
                        uploadNewPhoto(300, 200);
                        break;
                }
                break;
        }
    }

    /**
     * 上传新照片
     */
    private void uploadNewPhoto(int outputX, int outputY) {
        // 获取头像缩略图
        if (!TextUtils.isEmpty(protraitPath) && protraitFile.exists()) {
            protraitBitmap = ImageUtils
                    .loadImgThumbnail(protraitPath, outputX, outputY);
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
                    String response = utils.postMultipart(Constant.Url.UPLOADPIC, params, pathList);
                    if (TextUtils.isEmpty(response)) {
                        subscriber.onError(new Throwable("no network"));
                        return;
                    }
                    subscriber.onNext(response);
                }
            })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<String>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(String s) {
                            String response = s;
                            LogUtil.printD("re:" + response);
                            DSPicBean bean = (DSPicBean) GsonUtil.jsonToBean(response, DSPicBean.class);
                            if ("00000".equals(bean.getErrorCode())) {
                                switch (flag) {
                                    case 0:
                                        touxiangUrl = bean.getResult().getFileUrl();
                                        ImageHelper.getInstance().display(touxiangUrl, brivMasterTouxiang);
                                        break;
                                    case 1:
                                        sfzZhengmian = bean.getResult().getFileUrl();
                                        ImageHelper.getInstance().display(sfzZhengmian, brivMasterShenfenzhengZheng);
                                        break;
                                    case 2:
                                        sfzFanmian = bean.getResult().getFileUrl();
                                        ImageHelper.getInstance().display(sfzFanmian, brivMasterShenfenzhengFan);
                                        break;
                                    case 3:
                                        zhengshu1 = bean.getResult().getFileUrl();
                                        ImageHelper.getInstance().display(zhengshu1, brivMasterZhengshu1);
                                        break;
//                                    case 4:
//                                        zhengshu2 = bean.getResult().getFileUrl();
//                                        ImageHelper.getInstance().display(zhengshu2, brivMasterZhengshu2);
//                                        break;
//                                    case 5:
//                                        zhengshu3 = bean.getResult().getFileUrl();
//                                        ImageHelper.getInstance().display(zhengshu3, brivMasterZhengshu3);
//                                        break;
//                                    case 6:
//                                        zhengshu4 = bean.getResult().getFileUrl();
//                                        ImageHelper.getInstance().display(zhengshu4, brivMasterZhengshu4);
//                                        break;
                                    case 7:
                                        richang1 = bean.getResult().getFileUrl();
                                        ImageHelper.getInstance().display(richang1, brivMasterRichang1);
                                        break;
                                    case 8:
                                        richang2 = bean.getResult().getFileUrl();
                                        ImageHelper.getInstance().display(richang2, brivMasterRichang2);
                                        break;
                                }
                            } else {
                                ToastUtil.showShort(ApplyMasterResultActivity.this, bean.getMessage());
                            }
                        }

                    });
        }
    }

    /**
     * 申请信息
     */
    private void saveApplyInfo() {


        String token = UserManager.getInstance().getToken();
        String name = etMasterName.getText().toString();
        String chenghao = etMasterChenghao.getText().toString();
        String shanChng = etMasterShanchang.getText().toString();
        String jianjie = etMasterJianjie.getText().toString();
        if ("".equals(name)) {
            ToastUtil.showShort(this, "请填写姓名");
            return;
        }
        if ("".equals(chenghao)) {
            ToastUtil.showShort(this, "请填写称号");
            return;
        }
        if ("".equals(jianjie)) {
            ToastUtil.showShort(this, "请填写个人简介");
            return;
        }
        if ("".equals(shanChng)) {
            ToastUtil.showShort(this, "请填写擅长项目");
            return;
        }
        if (null == touxiangUrl || "".equals(touxiangUrl)) {
            ToastUtil.showShort(this, "请上传头像");
            return;
        }
        if (null == sfzZhengmian || "null".equals(sfzZhengmian)) {
            ToastUtil.showShort(this, "请上传身份证正面");
            return;
        }
        if (null == sfzFanmian || "null".equals(sfzFanmian)) {
            ToastUtil.showShort(this, "请上传身份证反面");
            return;
        }
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.show();
        mDialog.setMessage("正在提交信息，请等待...");
        AllApi.getInstance().dsApply(token, name, chenghao, shanChng, jianjie, touxiangUrl, sfzZhengmian, sfzFanmian, zhengshu1, richang1, richang2).
                enqueue(new ApiCallBack<ApiResponseBean<Object>>(new BaseCallBack() {
                    @Override
                    public void onSuccess(Object data) {
                        ToastUtil.showShort(ApplyMasterResultActivity.this, "提交成功，等待审核");
                        mDialog.dismiss();
                        ApplyMasterResultActivity.this.finish();
                    }

                    @Override
                    public void onError(String errorCode, String message) {
                        ErrorCodeUtil.showHaveTokenError(ApplyMasterResultActivity.this, errorCode);
                        mDialog.dismiss();
                    }
                }));
    }

}
