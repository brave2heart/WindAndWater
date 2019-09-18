package com.tongcheng.soothsay.data.calculate.zhuanyun.BaziPaipan;

import com.tongcheng.soothsay.bean.dao.BaziUserBean;
import com.tongcheng.soothsay.http.BaseCallBack;

import java.util.HashMap;
import java.util.List;



public interface IBaZi {
    void getPaipan(HashMap<String, String> map, BaseCallBack callback);

    void getYinYuan(HashMap<String, String> map, BaseCallBack callback);

    void getShiYe(HashMap<String, String> map, BaseCallBack callback);

    void getXingGe(HashMap<String, String> map, BaseCallBack callback);

    void getXTpapan(HashMap<String, String> map, BaseCallBack callback);

    List<BaziUserBean> getHistory();

    long saveHistory(BaziUserBean bean);

    Void delHistory();
}
