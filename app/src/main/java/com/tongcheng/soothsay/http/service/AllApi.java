package com.tongcheng.soothsay.http.service;

import com.tongcheng.soothsay.bean.calculation.LingFuBean;
import com.tongcheng.soothsay.bean.calculation.MingLiBean;
import com.tongcheng.soothsay.bean.calculation.NameTestBean;
import com.tongcheng.soothsay.bean.calculation.NewsBean;
import com.tongcheng.soothsay.bean.calculation.NewsTypeBean;
import com.tongcheng.soothsay.bean.calculation.NumberTestBean;
import com.tongcheng.soothsay.bean.calculation.OneLingFuBean;
import com.tongcheng.soothsay.bean.calculation.QQNumberBean;
import com.tongcheng.soothsay.bean.mine.AllOrder;
import com.tongcheng.soothsay.bean.classroom.ClassRoomBean;
import com.tongcheng.soothsay.bean.classroom.MoreMasterBean;
import com.tongcheng.soothsay.bean.classroom.ShopClassifyInfosBean;
import com.tongcheng.soothsay.bean.classroom.ShopHomeBean;
import com.tongcheng.soothsay.bean.mine.DSApplyBean;
import com.tongcheng.soothsay.bean.mine.JiFenBean;
import com.tongcheng.soothsay.bean.mine.LoginBean;
import com.tongcheng.soothsay.bean.mine.MineInfoBean;
import com.tongcheng.soothsay.bean.mine.MyMessage;
import com.tongcheng.soothsay.bean.mine.OrderDetail;
import com.tongcheng.soothsay.bean.mine.RegisterBean;
import com.tongcheng.soothsay.bean.mine.RyTokenBean;
import com.tongcheng.soothsay.bean.mine.SignInBean;
import com.tongcheng.soothsay.bean.splash.SplashBean;
import com.tongcheng.soothsay.http.ApiBuild;
import com.tongcheng.soothsay.http.ApiResponseBean;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


/**
 * Created by 宋家任 on 2016/11/6.
 * 请求api
 */

public class AllApi {
    private static AllApi.Service instance;
    //
    public interface Service {

        /**
         * qq测算
         *
         * @param key
         * @param qq
         * @return
         */
        @GET("qq?")
        Call<QQNumberBean> getQQMsg(@Query("key") String key,
                                    @Query("qq") String qq);

        @GET("query?")
        Call<QQNumberBean> getZGQuery(@Query("key") String key,
                                      @Query("q") String q,
                                      @Query("cid") String cid,
                                      @Query("full") String full);

        /**
         * 手机  QQ  身份证都是这用个测试
         * 数字测试
         *
         * @param number
         * @return
         */
        @POST("publish/number/testnumber.do")
        @FormUrlEncoded
        Call<ApiResponseBean<NumberTestBean>> getNumberMsg(@Field("number") String number);

        /**
         * 姓名测算
         *
         * @param name 姓名
         * @return
         */
        @POST("publish/name/testname.do")
        @FormUrlEncoded
        Call<ApiResponseBean<NameTestBean>> getNameMsg(@Field("name") String name);

        /**
         * 手机号发送验证码
         *
         * @param phone 手机号
         * @return
         */
        @POST("/publish/member/getCheckCode.do")
        @FormUrlEncoded
        Call<ApiResponseBean<Object>> registerYzm(@Field("phone") String phone,
                                                  @Field("type") String type);


        /**
         * 手机号注册
         *
         * @param msourceKey 唯一标识
         * @param phone      手机号
         * @param checkCode  验证码
         * @param password   密码
         * @param timestamp  时间戳
         * @param type       注册类型，默认为0。0：手机直接注册；1：第三方登录注册
         * @param thirdType  第三方登录类型，当type==1时必填。1：QQ登录；2：微信；3：新浪
         * @param uniqueKey  第三方登录唯一标识码，当type==1时必填
         * @param nickname   昵称，当type==1才有
         * @param headPic    头像地址，当type==1才有
         * @param sex        性别，当type==1才有 1男 2女
         * @return
         */
        @POST("/publish/member/bindingPhone.do")
        @FormUrlEncoded
        Call<ApiResponseBean<RegisterBean>> register(@Field("msourceKey") String msourceKey,
                                                     @Field("phone") String phone,
                                                     @Field("checkCode") String checkCode,
                                                     @Field("password") String password,
                                                     @Field("timestamp") String timestamp,
                                                     @Field("type") String type,
                                                     @Field("thirdType") String thirdType,
                                                     @Field("uniqueKey") String uniqueKey,
                                                     @Field("nickname") String nickname,
                                                     @Field("headPic") String headPic,
                                                     @Field("sex") String sex);

        @POST("/publish/news/getNewsList.do")
        @FormUrlEncoded
        Call<ApiResponseBean<List<NewsBean>>> getNewsList(@Field("typeId") String typeId,
                                                          @Field("start") String start);

        @POST("/publish/news/getNewsType.do")
        Call<ApiResponseBean<List<NewsTypeBean>>> getNewsType();


        /**
         * 获取积分商品列表
         *
         * @return
         */
        @POST("/publish/jf/getJfstoreList.do")
        Call<ApiResponseBean<List<JiFenBean>>> getJfstoreList();

        /**
         * 忘记密码修改密码
         *
         * @param phone
         * @param checkCode
         * @param password
         * @return
         */
        @POST("/publish/member/changePwd.do")
        @FormUrlEncoded
        Call<ApiResponseBean<Object>> forgetpsd(@Field("phone") String phone,
                                                @Field("checkCode") String checkCode,
                                                @Field("password") String password,
                                                @Field("timestamp") String timestamp);

        /**
         * 登录
         *
         * @param phone     手机号
         * @param password  密码
         * @param timestamp 时间戳
         * @return
         */
        @POST("/publish/member/login.do")
        @FormUrlEncoded
        Call<ApiResponseBean<LoginBean>> login(@Field("phone") String phone,
                                               @Field("password") String password,
                                               @Field("timestamp") String timestamp);


        /**
         * 签到
         *
         * @param token
         * @return 10002    缺少参数，token
         * 10099	签到获取积分失败
         * 10100	今天已经签到了
         * 99999	服务器系统错误
         */
        @POST("/publish/member/signIn.do")
        @FormUrlEncoded
        Call<ApiResponseBean<SignInBean>> signIn(@Field("token") String token);


        /**
         * 第三方登录
         *
         * @param thirdType 1：QQ登录；2：微信；3：新浪
         * @param uniqueKey
         * @return
         */
        @POST("/publish/member/thirdPartyLogin.do")
        @FormUrlEncoded
        Call<ApiResponseBean<LoginBean>> thirdPartylogin(@Field("thirdType") String thirdType,
                                                         @Field("uniqueKey") String uniqueKey);

        /**
         * 获取用户个人信息
         *
         * @param token
         * @return
         */
        @POST("/publish/member/getUserMember.do")
        @FormUrlEncoded
        Call<ApiResponseBean<MineInfoBean>> MineInfo(@Field("token") String token);

        /**
         * 上传新头像 表单提交出错，不用这个方法
         *
         * @param token
         * @param fileId
         * @return
         */
//        @Multipart()
//        @POST("/publish/member/uploadHeadPic.do")
//        Call<ApiResponseBean<HeadPicBean>> headPic(@Part("token") String token,
//                                                   @Part("file")  File fileId);

        /**
         * 修改名字
         *
         * @param name
         * @param token
         * @return
         */
        @POST("/publish/member/saveUserMember.do")
        @FormUrlEncoded
        Call<ApiResponseBean<Object>> changeNane(@Field("name") String name,
                                                 @Field("token") String token);

        /**
         * 修改生日
         *
         * @param bornTime
         * @param token
         * @return
         */
        @POST("/publish/member/saveUserMember.do")
        @FormUrlEncoded
        Call<ApiResponseBean<Object>> changeDate(@Field("bornTime") long bornTime,
                                                 @Field("token") String token);

        /**
         * 修改性别
         *
         * @param sex
         * @param token
         * @return
         */
        @POST("/publish/member/saveUserMember.do")
        @FormUrlEncoded
        Call<ApiResponseBean<Object>> changeSex(@Field("sex") String sex,
                                                @Field("token") String token);

        /**
         * 修改工作状态
         *
         * @param workStatus
         * @param token
         * @return
         */
        @POST("/publish/member/saveUserMember.do")
        @FormUrlEncoded
        Call<ApiResponseBean<Object>> changeWork(@Field("workStatus") String workStatus,
                                                 @Field("token") String token);

        /**
         * 修改婚姻状态
         *
         * @param marriageStatus
         * @param token
         * @return
         */
        @POST("/publish/member/saveUserMember.do")
        @FormUrlEncoded
        Call<ApiResponseBean<Object>> changeHunyin(@Field("marriageStatus") String marriageStatus,
                                                   @Field("token") String token);

        /**
         * 大师学堂首页
         *
         * @return
         */
        @POST("/publish/lecture/listInfo.do")
        @FormUrlEncoded
        Call<ApiResponseBean<ClassRoomBean>> getClassRoomDatas(@Field("") String str);

        /**
         * 命里推算轮播图及大师亲算
         *
         * @return
         */
        @POST("/publish/lecture/getMLListInfo.do")
        @FormUrlEncoded
        Call<ApiResponseBean<MingLiBean>> getMingLiDatas(@Field("") String str);

        @POST("/publish/circle/getCircleTypeList.do")
        Call<ApiResponseBean<List<ClassRoomBean.CirclesBean>>> getCircleTypeList();

        /**
         * 商城首页
         *
         * @return
         */
        @POST("/publish/store/topMerchandiseList.do")
        @FormUrlEncoded
        Call<ApiResponseBean<ShopHomeBean>> getShopHome(@Field("") String str);


        /**
         * 获取商品信息数据
         *
         * @param start
         * @param categoryId
         * @return
         */
        @POST("/publish/store/merchandiseList.do")
        @FormUrlEncoded
        Call<ApiResponseBean<ShopClassifyInfosBean>> getShopClassifyInfos(@Field("start") String start,
                                                                          @Field("categoryId") String categoryId);

        /**
         * 大师列表
         *
         * @return
         */
        @POST("/publish/ds/getDsList.do")
        @FormUrlEncoded
        Call<ApiResponseBean<List<MoreMasterBean>>> getmoreMaster(@Field("") String str);

        /**
         * 大师申请
         *
         * @param token
         * @param name
         * @param chengHao
         * @param shanChang
         * @param jianJie
         * @param touXiang
         * @param sPicZM
         * @param sPicBM
         * @param zjPic
         * @param usPic1
         * @param usPic2
         * @return
         */
        @POST("/publish/ds/dsApply.do")
        @FormUrlEncoded
        Call<ApiResponseBean<Object>> dsApply(@Field("token") String token,
                                              @Field("name") String name,
                                              @Field("chengHao") String chengHao,
                                              @Field("shanChang") String shanChang,
                                              @Field("jianJie") String jianJie,
                                              @Field("touXiang") String touXiang,
                                              @Field("sPicZM") String sPicZM,
                                              @Field("sPicBM") String sPicBM,
                                              @Field("zjPic") String zjPic,
                                              @Field("usPic1") String usPic1,
                                              @Field("usPic2") String usPic2);

        /**
         * 获取大师申请信息
         *
         * @param token
         * @return
         */
        @POST("/publish/ds/getApplyInfo.do")
        @FormUrlEncoded
        Call<ApiResponseBean<DSApplyBean>> getdsapplyinfo(@Field("token") String token);

        /**
         * 获取闪屏页广告
         *
         * @return
         */
        @POST("/publish/homeAd/getAd.do")
        @FormUrlEncoded
        Call<ApiResponseBean<SplashBean>> getSplash(@Field("") String str);

        /**
         * 获取灵符列表
         *
         * @param token
         * @param sort  灵符类型。
         *              1：金钱财运，
         *              2：平安护身，
         *              3：文昌官运，
         *              4：桃花姻缘，
         *              5：避祸镇煞，
         *              6：送子添丁，
         *              7：太岁符，
         *              8：安家镇宅，
         *              9：护身符
         * @return
         */
        @POST("/publish/lfstore/getlfstore.do")
        @FormUrlEncoded
        Call<ApiResponseBean<List<List<LingFuBean>>>> getLingfu(@Field("token") String token,
                                                                @Field("sort") String sort);

        /**
         * 获取单个灵符的状态
         *
         * @param token
         * @param lfName 符名
         * @return
         */
        @POST("/publish/lfstore/getLfBuyList.do")
        @FormUrlEncoded
        Call<ApiResponseBean<List<OneLingFuBean>>> getoneLingFuInfo(@Field("token") String token,
                                                                    @Field("lfName") String lfName);

        /**
         * 填写用符人信息
         *
         * @param token
         * @param orderNo  订单编号
         * @param username
         * @param bazi
         * @param address
         * @return
         */
        @POST("/publish/lfstore/saveLfInfo.do")
        @FormUrlEncoded
        Call<ApiResponseBean<Object>> getUserLingFuMsg(@Field("token") String token,
                                                       @Field("orderNo") String orderNo,
                                                       @Field("username") String username,
                                                       @Field("bazi") String bazi,
                                                       @Field("address") String address);

        /**
         * 用户反馈
         *
         * @param token
         * @return
         */
        @POST("/publish/userFeedback/saveFeedback.do")
        @FormUrlEncoded
        Call<ApiResponseBean<Object>> getFeedBack(@Field("token") String token, @Field("content") String content);

        /**
         * 获取全部订单
         */
        @POST("/publish/store/getAllOrderList.do")
        @FormUrlEncoded
        Call<ApiResponseBean<List<AllOrder>>> getAllOrder(@FieldMap HashMap<String, String> map);

        /**
         * 订单 确认收货
         *
         * @param map
         * @return
         */

        @POST("/publish/store/confirmOrder.do")
        @FormUrlEncoded
        Call<ApiResponseBean<Object>> getConfirmOrder(@FieldMap HashMap<String, String> map);

        /**
         * 获取订单详情
         *
         * @param map
         * @return
         */
        @POST("/publish/store/getOrderDetail.do")
        @FormUrlEncoded
        Call<ApiResponseBean<OrderDetail>> getOrderDetail(@FieldMap HashMap<String, String> map);


        /**
         * 获取融云客服token接口
         */
        @POST("publish/customService/getCustomToken.do")
        @FormUrlEncoded
        Call<ApiResponseBean<RyTokenBean>> getImToken(@Field("token") String token);

        /**
         * 我的消息
         *
         * @param map
         * @return
         */
        @POST("/publish/myMessage/messageList.do")
        @FormUrlEncoded
        Call<ApiResponseBean<MyMessage>> getMyMessageList(@FieldMap HashMap<String, String> map);

    }

    public static AllApi.Service getInstance() {
        if (instance == null) {
            instance = ApiBuild.getRetrofit().create(AllApi.Service.class);
        }
        return instance;
    }
}
