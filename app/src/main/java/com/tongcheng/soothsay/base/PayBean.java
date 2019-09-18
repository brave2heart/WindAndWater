package com.tongcheng.soothsay.base;


/**
 * 灵符支付bean
 */
public class PayBean {
   public boolean isPaySucc;//是否支付成功 true 成功 false失败

    public PayBean(boolean isPaySucc) {
        this.isPaySucc = isPaySucc;
    }
}
