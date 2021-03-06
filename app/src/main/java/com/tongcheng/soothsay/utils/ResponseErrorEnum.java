package com.tongcheng.soothsay.utils;

/**
 * 接口响应错误信息
 *
 */
public enum ResponseErrorEnum {
	/**
	 * "没有登录或登录过期","10000"
	 */
	RESPONSE_ERROR_LOGIN_NO("没有登录或登录过期","10000"),
	/**
	 * "token过期","10001"
	 */
	RESPONSE_ERROR_10001("token过期","10001"),
	/**
	 * "缺少参数，token","10002"
	 */
	RESPONSE_ERROR_LOST_10002("缺少参数，token","10002"),
	/**
	 * "已经注册","10003"
	 */
	RESPONSE_ERROR_MEMBER_LOST_10003("已经注册","10003"),
	/**
	 * "查询无信息","10004"
	 */
	RESPONSE_ERROR_LOST_10004("查询无信息","10004"),
	/**
	 * "缺少参数","10005"
	 */
	RESPONSE_ERROR_LOST_10005("缺少参数","10005"),
	/**
	 * "查不到用户","10007"
	 */
	RESPONSE_ERROR_LOST_10007("查不到用户","10007"),
	/**
	 * "缺少参数，起始数量","10008"
	 */
	RESPONSE_ERROR_LOST_10008("缺少参数，起始数量","10008"),
	/**
	 * "缺少参数，密码为空","10009"
	 */
	RESPONSE_ERROR_MEMBER_LOST_10009("缺少参数，密码为空","10009"),
	/**
	 * "用户名或密码错误","10010"
	 */
	RESPONSE_ERROR_MEMBER_10010("用户名或密码错误","10010"),
	
	
	//紫微斗数
	/**
	 * "缺少参数，出生时间为空","10011"
	 */
	RESPONSE_ERROR_ZIWEI_LOST_10011("缺少参数，出生时间为空","10011"),
	/**
	 * "缺少参数，性别为空","10012"
	 */
	RESPONSE_ERROR_ZIWEI_LOST_10012("缺少参数，性别为空","10012"),
	/**
	 * "缺少参数，姓名为空","10013"
	 */
	RESPONSE_ERROR_ZIWEI_LOST_10013("缺少参数，姓名为空","10013"),
	/**
	 * "缺少参数，命宫位置索引为空","10014"
	 */
	RESPONSE_ERROR_ZIWEI_LOST_10014("缺少参数，命宫位置索引为空","10014"),
	
	//用户
	/**
	 * "缺少参数，手机唯一标识为空","10021"
	 */
	RESPONSE_ERROR_USER_LOST_10021("缺少参数，手机唯一标识为空","10021"),
	/**
	 * "缺少参数，手机号为空","10022"
	 */
	RESPONSE_ERROR_USER_LOST_10022("缺少参数，手机号为空","10022"),
	/**
	 * "手机号码已经注册","10023"
	 */
	RESPONSE_ERROR_USER__10023("手机号码已经注册","10023"),
	/**
	 * "缺少参数，手机验证码为空","10024"
	 */
	RESPONSE_ERROR_USER_LOST_10024("缺少参数，手机验证码为空","10024"),
	/**
	 * "验证码错误","10025"
	 */
	RESPONSE_ERROR_USER_10025("验证码错误","10025"),
	/**
	 * "缺少参数，登录编码为空","10026"
	 */
	RESPONSE_ERROR_USER_10026("缺少参数，登录编码为空","10026"),
	/**
	 * "缺少参数，登录密码为空","10027"
	 */
	RESPONSE_ERROR_USER_10027("缺少参数，登录密码为空","10027"),
	
	/**
	 * "缺少参数，合婚类型为空","10028"
	 */
	RESPONSE_ERROR_USER_10028("缺少参数，合婚类型为空","10028"),
	/**
	 * "缺少参数，积分类型为空","10029"
	 */
	RESPONSE_ERROR_USER_10029("缺少参数，积分类型为空","10029"),
	/**
	 * "缺少参数，广告类型为空","10030"
	 */
	RESPONSE_ERROR_USER_10030("缺少参数，广告类型为空","10030"),
	/**

	 * "缺少参数，广告ID为空","10031"
	 */
	RESPONSE_ERROR_USER_10031("缺少参数，广告ID为空","10031"),
	/**
	 * "缺少参数，大师信息为空","10032"
	 */
	RESPONSE_ERROR_USER_10032("缺少参数，大师信息为空","10032"),
	/**
	 * "缺少参数，大师信息不完整","10033"
	 */
	RESPONSE_ERROR_DS_10033("缺少参数，大师信息不完整","10033"),
	
	//商城
	/**
	 * "缺少参数，新闻类型为空","10040"
	 */
	RESPONSE_ERROR_NEWS_LOST_10040("缺少参数，新闻类型为空","10040"),
	
	/**
	 * 缺少参数，商品ID为空","10041"
	 */
	RESPONSE_ERROR_STORE_LOST_10041("缺少参数，商品ID为空","10041"),
	/**
	 * "缺少参数，商品数量为空","10042"
	 */
	RESPONSE_ERROR_STORE_LOST_10042("缺少参数，商品数量为空","10042"),
	/**
	 * "缺少参数，订单交易场景类型为空","10044"
	 */
	RESPONSE_ERROR_STORE_LOST_10044("缺少参数，订单交易场景类型为空","10044"),
	/**
	 * "缺少参数，订单支付方式为空","10045"
	 */
	RESPONSE_ERROR_STORE_LOST_10045("缺少参数，订单支付方式为空","10045"),
	/**
	 * "缺少参数，订单金额为空","10046"
	 */
	RESPONSE_ERROR_STORE_LOST_10046("缺少参数，订单金额为空","10046"),
	/**
	 * "缺少参数，订单商品ID为空","10047"
	 */
	RESPONSE_ERROR_STORE_LOST_10047("缺少参数，订单商品ID为空","10047"),
	/**
	 * "缺少参数，订单商品数量为空","10048"
	 */
	RESPONSE_ERROR_STORE_LOST_10048("缺少参数，订单商品数量为空","10048"),
	/**
	 * "缺少参数，订单商品商品和数量不匹配","10049"
	 */
	RESPONSE_ERROR_STORE_LOST_10049("缺少参数，订单商品商品和数量不匹配","10049"),
	/**
	 * "个人积分不足","10050"
	 */
	RESPONSE_ERROR_STORE_10050("个人积分不足","10050"),
	/**
	 * "订单金额不匹配","10051"
	 */
	RESPONSE_ERROR_STORE_10051("订单金额不匹配","10051"),
	/**
	 * "缺少参数，订单商品名称为空","10052"
	 */
	RESPONSE_ERROR_STORE_LOST_10052("缺少参数，订单商品名称为空","10052"),
	/**
	 * "缺少参数，订单商品额外参数为空","10053"
	 */
	RESPONSE_ERROR_STORE_LOST_10053("缺少参数，订单商品额外参数为空","10053"),
	/**
	 * "缺少参数，订单商品许愿灯名称为空","10054"
	 */
	RESPONSE_ERROR_STORE_LOST_10054("缺少参数，订单许愿灯名称为空","10054"),
	/**
	 * "缺少参数，订单许愿灯状态为空","10055"
	 */
	RESPONSE_ERROR_STORE_LOST_10055("缺少参数，订单许愿灯状态为空","10055"),
	/**
	 * "缺少参数，订单许愿灯性别为空","10056"
	 */
	RESPONSE_ERROR_STORE_LOST_10056("缺少参数，订单许愿灯性别为空","10056"),
	/**
	 * "缺少参数，订单许愿灯性别为空","10056"
	 */
	RESPONSE_ERROR_STORE_LOST_10057("缺少参数，订单许愿灯出生日期为空","10057"),
	/**
	 * "缺少参数，订单许愿灯用户名字为空","10058"
	 */
	RESPONSE_ERROR_STORE_LOST_10058("缺少参数，订单许愿灯用户名字为空","10058"),
	/**
	 * "缺少参数，订单许愿灯内容为空","10059"
	 */
	RESPONSE_ERROR_STORE_LOST_10059("缺少参数，订单许愿灯内容为空","10059"),
	/**
	 * "缺少参数，订单许愿灯点灯期限类型为空","10060"
	 */
	RESPONSE_ERROR_STORE_LOST_10060("缺少参数，订单许愿灯点灯期限类型为空","10060"),
	/**
	 * "缺少参数，订单许愿是否公开为空","10061"
	 */
	RESPONSE_ERROR_STORE_LOST_10061("缺少参数，订单许愿是否公开为空","10061"),
	/**
	 * "查不到许愿灯信息","10062"
	 */
	RESPONSE_ERROR_STORE_10062("查不到许愿灯信息","10062"),
	/**
	 * "查不到此姓名信息","10063"
	 */
	RESPONSE_ERROR_STORE_10063("查不到此姓名信息","10063"),
	/**
	 * "缺少参数，许愿灯类型为空","10064"
	 */
	RESPONSE_ERROR_STORE_LOST_10064("缺少参数，许愿灯类型为空","10064"),
	/**
	 * "缺少参数，订单编号为空","10065"
	 */
	RESPONSE_ERROR_STORE_LOST_10065("缺少参数，订单编号为空","10065"),
	/**
	 * "缺少参数，文件ID为空","10066"
	 */
	RESPONSE_ERROR_MEMBER_LOST_10066("缺少参数，文件ID为空","10066"),
	/**
	 * "缺少参数，上传的文件为空","10067"
	 */
	RESPONSE_ERROR_MEMBER_LOST_10067("缺少参数，上传的文件为空","10067"),
	
	
	//灵符商城
	/**
	 * 缺少参数，商品分类为空","10043"
	 */
	RESPONSE_ERROR_STORE_LOST_10043("缺少参数，商品分类为空","10043"),

	/**
	 * 缺少参数，测试数字为空,"10068"
	 */
	RESPONSE_ERROR_STORE_LOST_10068("缺少参数，测试数字为空","10068"),
	/**
	 * "缺少参数，灵符名称为空","10069"
	 */
	RESPONSE_ERROR_STORE_LOST_10069("缺少参数，灵符名称为空","10069"),
	/**
	 * "缺少参数，灵符购买类型为空","10070"
	 */
	RESPONSE_ERROR_STORE_LOST_10070("缺少参数，灵符购买类型为空","10070"),
	/**
	 * "缺少参数，订单编号为空","10071"
	 */
	RESPONSE_ERROR_STORE_LOST_10071("缺少参数，订单编号为空","10071"),
	/**
	 * "缺少参数，订单积兑换ID为空","10072"
	 */
	RESPONSE_ERROR_STORE_LOST_10072("缺少参数，订单积兑换ID为空","10072"),
	/**
	 * "查不到积分信息","10073"
	 */
	RESPONSE_ERROR_STORE_10073("查不到积分信息","10073"),
	/**
	 * "查不到灵符信息","10074"
	 */
	RESPONSE_ERROR_STORE_10074("查不到灵符信息","10074"),
	/**
	 * "缺少参数，第三方类型","10075"
	 */
	RESPONSE_ERROR_MEMBER_10075("缺少参数，第三方登录类型","10075"),
	/**
	 * "缺少参数，第三方唯一标识","10076"
	 */
	RESPONSE_ERROR_MEMBER_10076("缺少参数，第三方唯一标识","10076"),
	/**
	 * "缺少参数，第三方唯一标识","10076"
	 */
	RESPONSE_ERROR_XYD_10077("已经为他祈福过了","10077"),
	/**
	 * "缺少参数，祈福大仙名称为空","10078"
	 */
	RESPONSE_ERROR_QF_LOST_10078("缺少参数，祈福大仙名称为空","10078"),
	/**
	 * "缺少参数，祈福大仙的内容为空","10079"
	 */
	RESPONSE_ERROR_QF_LOST_10079("缺少参数，祈福大仙的内容为空","10079"),
	/**
	 * "已供奉大仙","10080"
	 */
	RESPONSE_ERROR_QF_LOST_10080("已供奉大仙","10080"),
	/**
	 * "缺少参数，祈福供品为空","10081"
	 */
	RESPONSE_ERROR_QF_LOST_10081("缺少参数，祈福供品为空","10081"),
	/**
	 * "未供奉大仙","10082"
	 */
	RESPONSE_ERROR_QF_10082("未供奉大仙","10082"),
	/**
	 * "今天已祈福过了","10083"
	 */
	RESPONSE_ERROR_QF_10083("今天已祈福过此类供品了","10083"),
	/**
	 * "购买的供品不足，请购买","10084"
	 */
	RESPONSE_ERROR_QF_10084("购买的供品不足，请购买","10084"),
	/**
	 * "缺少参数，供品数量为空","10085"
	 */
	RESPONSE_ERROR_QF_10085("缺少参数，供品数量为空","10085"),
	/**
	 * "缺少参数，供品购买积分为空","10086"
	 */
	RESPONSE_ERROR_QF_10086("缺少参数，供品购买积分为空","10086"),
	/**
	 * "祈福供品不存在","10087"
	 */
	RESPONSE_ERROR_QF_10087("祈福供品不存在","10087"),
	/**
	 * "缺少参数，血型为空","10088"
	 */
	RESPONSE_ERROR_MATCH_10088("缺少参数，血型为空","10088"),
	/**
	 * "祈福台供品积分不匹配","10089"
	 */
	RESPONSE_ERROR_QF_10089("祈福台供品积分不匹配","10089"),
	/**
	 * "祈福台供品数量不匹配","10090"
	 */
	RESPONSE_ERROR_QF_10090("祈福台供品数量不匹配","10090"),
	/**
	 * "缺少参数，放生商品为空","10091"
	 */
	RESPONSE_ERROR_FS_10091("缺少参数，放生商品为空","10091"),
	/**
	 * "缺少参数，生肖为空","10092"
	 */
	RESPONSE_ERROR_MATCH_10092("缺少参数，生肖为空","10092"),
	/**
	 * "缺少参数，日期或月份为空","10093"
	 */
	RESPONSE_ERROR_MATCH_10093("缺少参数，日期或月份为空","10093"),
	/**
	 * "缺少参数，灵符名称为空","10094"
	 */
	RESPONSE_ERROR_LF_10094("缺少参数，灵符名称为空","10094"),
	/**
	 * "缺少参数，许愿树信息为空","10095"
	 */
	RESPONSE_ERROR_XY_10095("缺少参数，许愿树信息为空","10095"),
	/**
	 * "缺少参数，许愿者名字为空","10096"
	 */
	RESPONSE_ERROR_XY_10096("缺少参数，许愿者名字为空","10096"),
	/**
	 * "缺少参数，许愿树内容为空","10097"
	 */
	RESPONSE_ERROR_XY_10097("缺少参数，许愿树内容为空","10097"),
	/**
	 * "缺少参数，许愿树id为空","10098"
	 */
	RESPONSE_ERROR_XY_10098("缺少参数，许愿树id为空","10098"),
	/**
	 * "签到获取积分失败","10099"
	 */
	RESPONSE_ERROR_JF_10099("签到获取积分失败","10099"),
	/**
	 * "今天已经签到了","10100"
	 */
	RESPONSE_ERROR_JF_10100("今天已经签到了","10100"),
	
	
	
	/**
	 * "调用成功","00000"
	 */
	RESPONSE_SUCCESS("调用成功","00000"),
	/**
	 * "系统错误","99999"
	 */
	RESPONSE_ERROR_SYSTEM("系统错误","99999");
	
	
    //错误信息
    private String errMsg;
    //错误编码
    private String errCode;

    // 构造方法
    private ResponseErrorEnum(String errMsg, String errCode) {
        this.errMsg = errMsg;
        this.errCode = errCode;
    }
    
    public static String getErrMsg(int errCode) {
        for (ResponseErrorEnum re : ResponseErrorEnum.values()) {
            if (re.getErrCode().equals(errCode)) {
                return re.errMsg;
            }
        }
        return null;
    }
    
	public String getErrMsg() {
		return errMsg;
	}

	public String getErrCode() {
		return errCode;
	}

	// 覆盖方法
    @Override
    public String toString() {
        return this.errCode + "_" + this.errMsg;
    }
}
