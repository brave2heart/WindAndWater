package com.living.constant.home;

import com.living.utils.TimeUtil;

/**
 * Created by weihao on 2017/12/25.
 */

public class HomeAPI {

    //聚合：新闻头条

    /* 	类型,,top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐)
              tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)*/
    public static final String TouTiaoBaseUrl = "http://v.juhe.cn/";
    public static final String TouTiaokey = "e54bbaf544509245e3c726d05bd8ef64";
    public static final String[] TouTiaoType = new String[]{"top", "shehui", "guonei", "guoji", "yule", "tiyu", "junshi", "keji", "caijing", "shishang"};


    //聚合：星座运势

//    today,tomorrow,week,nextweek,month,year

    public static final String ConstellationBaseUrl = "http://web.juhe.cn:8080/";
    public static final String Constellationkey = "56521a14b05ea90f0989adc973c41c97";
    public static final String[] ConstellationType = new String[]{"today", "tomorrow", "week", "month", "year"};

    /*聚合——微信精选*/
    public static final String WeChatBaseUrl = "http://v.juhe.cn/";
    public static final String WeChatkey = "bc825580457c03c5da919f58f851ebb6";
    public static final int WeChatPno = 1;
    public static final int WeChatPs = 50;

    /*极速数据——微信精选*/
    //    http://api.jisuapi.com/weixinarticle/get?channelid=1&start=0&num=10&appkey=846dc0d6176c6b95
    public static final String JWeChatBaseUrl = "http://api.jisuapi.com/";
    public static final int JWeChatChannelid = 1;
    public static final int JWeChatStart = 0;
    public static final int JWeChatNum = 15;
    public static final String JWeChatkey = "846dc0d6176c6b95";


}
