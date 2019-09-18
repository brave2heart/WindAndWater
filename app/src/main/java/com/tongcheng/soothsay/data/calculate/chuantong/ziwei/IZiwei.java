package com.tongcheng.soothsay.data.calculate.chuantong.ziwei;

import com.tongcheng.soothsay.bean.dao.ZiweiUserBean;
import com.tongcheng.soothsay.http.BaseCallBack;

import java.util.HashMap;
import java.util.List;


/**
 * Created by Steven on 16/11/16.
 */

public interface IZiwei {
    void getPaipan(HashMap<String,String> map, BaseCallBack callback);

    List<ZiweiUserBean> getHistory();

    long saveHistory(ZiweiUserBean bean);

    Void delHistory();
}
