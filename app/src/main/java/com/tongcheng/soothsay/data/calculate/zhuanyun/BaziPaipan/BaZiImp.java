package com.tongcheng.soothsay.data.calculate.zhuanyun.BaziPaipan;

import com.tongcheng.soothsay.bean.calculation.BaziPaipanBean;
import com.tongcheng.soothsay.bean.calculation.bazi.BaziShiyeBean;
import com.tongcheng.soothsay.bean.calculation.bazi.BaziSzshishenBean;
import com.tongcheng.soothsay.bean.calculation.bazi.BaziXinggeBean;
import com.tongcheng.soothsay.bean.calculation.bazi.BaziYinyuanBean;
import com.tongcheng.soothsay.bean.dao.BaziUserBean;
import com.tongcheng.soothsay.bean.dao.BaziUserBeanDao;
import com.tongcheng.soothsay.helper.GreenDaoHelper;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.BaZiApi;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.HashMap;
import java.util.List;


/**
 *八字数据
 */
public class BaZiImp implements IBaZi {

    @Override
    public void getPaipan(HashMap<String, String> map, BaseCallBack callback) {
        BaZiApi.getInstance().baziPaipan(map).enqueue(new ApiCallBack<ApiResponseBean<BaziPaipanBean>>(callback));
    }

    @Override
    public void getYinYuan(HashMap<String, String> map, BaseCallBack callback) {
        BaZiApi.getInstance().baziYinyuan(map).enqueue(new ApiCallBack<ApiResponseBean<BaziYinyuanBean>>(callback));
    }

    @Override
    public void getShiYe(HashMap<String, String> map, BaseCallBack callback) {
        BaZiApi.getInstance().baziShiye(map).enqueue(new ApiCallBack<ApiResponseBean<BaziShiyeBean>>(callback));
    }

    @Override
    public void getXingGe(HashMap<String, String> map, BaseCallBack callback) {
        BaZiApi.getInstance().baziXingge(map).enqueue(new ApiCallBack<ApiResponseBean<BaziXinggeBean>>(callback));
    }

    @Override
    public void getXTpapan(HashMap<String, String> map, BaseCallBack callback) {
        BaZiApi.getInstance().baziSzShishen(map).enqueue(new ApiCallBack<ApiResponseBean<List<BaziSzshishenBean>>>(callback));
    }


    //获取历史记录
    @Override
    public List<BaziUserBean> getHistory() {
        BaziUserBeanDao dao = GreenDaoHelper.getInstance().getSeeion().getBaziUserBeanDao();
        if (dao != null) {
            return dao.loadAll();
        }
        return null;

    }

    //插入一条历史记录
    @Override
    public long saveHistory(BaziUserBean bean) {
        BaziUserBeanDao dao = GreenDaoHelper.getInstance().getSeeion().getBaziUserBeanDao();
        QueryBuilder<BaziUserBean> qb = dao.queryBuilder();
        List<BaziUserBean> list = qb.where(BaziUserBeanDao.Properties.Date.eq(bean.getDate())
                , BaziUserBeanDao.Properties.Name.eq(bean.getName())
                , BaziUserBeanDao.Properties.Sex.eq(bean.getSex())).list();
        long id = -1;
        if (list.size() == 0) {
            id = dao.insert(bean);
        }
        return id;
    }

    //清空历史记录
    @Override
    public Void delHistory() {
        BaziUserBeanDao dao = GreenDaoHelper.getInstance().getSeeion().getBaziUserBeanDao();
        dao.deleteAll();
        return null;
    }
}
