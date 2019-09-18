package com.tongcheng.soothsay.constant;

/**
 * Created by 宋家任 on 2016/9/23.
 * 常量
 */

public final class Constant {

    public static final String INTENT_DATA = "data";
    public static final String REPAY = "repay";

    //是否首次登陆标志
    public static final String FIRST_OPEN = "first_open";

    public static final boolean DEBUG = true;
    public static final String BASE_ONEIROMANCY_KEY = "df2e222ad83984f205c87cbc1cc76dd3";

    //qq测算key
    public static final String QQTESTKEY = "253fbe4224e6bf8cf495dd28b74a999b";

    //融云客服相关
    public static final String KEFU_KEY = "qf3d5gbjqfwxh";
    public static final String KEFU_ID = "KEFU148306034196532";
    public static final String TEST_TOKEN = "SlyqoF1d1rOyobwpuiUOSHs0qJgF6x2WjsonYUEvomcnsRkTo1Sug72W5aF3BfntZ3gEJZJYF1SD+YAVocJ6ByWk+KtVfBxs";     //测试token


    public static final String ONLY_WIFI = "only_wifi";

    //    积分值
    public static final String CREDITS_COUNT = "credits_count";


    /**
     * 祈福台 供品 类型
     */
    public static final String SORT = "sort";

    /**
     * 祈福台 供品对应的 布局
     */
    public static final String ITEM_LAYOUT = "ItemLayout";

    public interface Url {
        //base url
        String BASE_URL = "http://smds.fssvip.com:8080/";
        //周公解梦
        String BASE_ONEIROMANCY_URL = "http://v.juhe.cn/dream/";
        //qq测算url
        String BASE_QQMSG_URL = "http://japi.juhe.cn/qqevaluate/";
        //头像上传url http://120.76.219.201:8080/publish/member/uploadHeadPic.do
        String UPLOADHEADPIC = BASE_URL + "publish/member/uploadHeadPic.do";
        //大师头像上传url
        String UPLOADPIC = BASE_URL + "publish/ds/uploadPic.do";
        //应用分享链接
        String SHARE_APP = BASE_URL + "html/share.html";
        //关于风生水
        String ABOUT_FSS = BASE_URL + "html/viewArticle.html?type=2";
        //加入我们
        String JOIN_OUS = BASE_URL + "html/viewArticle.html?type=1";
        //风生水协议
        String AGREEMENT = BASE_URL + "html/agreement.html";
    }


    /**
     * 错误码
     */
    public interface ErrorCode {
        //token过期
        String TOKEN_ERROR_10001 = "10001";
        //服务器系统错误
        String SERVER_ERROR_99999 = "99999";
        //"查不到用户","10007"可以认为token无效
        String RESPONSE_ERROR_LOST_10007 = "10007";

        //        查询无信息
        String RESPONSE_ERROR_LOST_10004 = "10004";

        //服务器繁忙（服务器卡顿）
        String SERVER_BUSY = "500";


        //登录注册时的错误码
        //手机号已注册
        String RESPONSE_ERROR_USER__10023 = "10023";
    }

    /**
     * 请求码
     */
    public interface RequestCode {
        //退出登录请求码
        int EXIT_LOGIN = 9000;
        //注册时请求码
        int REGISTER = 9001;
        //登录
        int LOGIN = 9002;
        //改名
        int CHANGE_NAME = 9003;
        //        忘记密码
        int FORGET = 9004;
        //qq登录
        int QQLOGIN = 9005;
        //微信登录
        int WEIXINLOGIN = 9006;
        //大德符运登录
        int DADE_LOGIN = 9007;
        //        我的许愿灯登录
        int MY_WISH = 9008;
        //灵符支付
        int LF_PAY = 9009;
        //        意见反馈
        int FEED_BACK = 9010;

    }

    public interface ResultCode {
        int LOGIN = 9999;
        int ADD_ADDRESS = 101;         //添加新的收货地址
        int SELECT_ADDRESS = 102;      //选择新的收货地址
        int UPDATA_ADDRESS = 103;      //修改地址
    }


}
