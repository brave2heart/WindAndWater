package com.tongcheng.soothsay.data.calculate.chuantong.ziwei;

import com.tongcheng.soothsay.bean.calculation.ZiweipaipanBean;
import com.tongcheng.soothsay.bean.dao.ZiweiUserBean;
import com.tongcheng.soothsay.bean.dao.ZiweiUserBeanDao;
import com.tongcheng.soothsay.helper.GreenDaoHelper;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.chuantongApi;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Steven on 16/11/16.
 */

public class ZiweiImp implements IZiwei {

    @Override
    public void getPaipan(HashMap<String,String> map, BaseCallBack callback) {
        chuantongApi.getInstance().getZiweipaipan(map).enqueue(new ApiCallBack<ApiResponseBean<ZiweipaipanBean>>(callback));
    }

    //获取历史记录
    @Override
    public List<ZiweiUserBean> getHistory() {
        ZiweiUserBeanDao dao = GreenDaoHelper.getInstance().getSeeion().getZiweiUserBeanDao();
        return dao.loadAll();
    }

    //插入一条历史记录
    @Override
    public long saveHistory(ZiweiUserBean bean) {
        ZiweiUserBeanDao dao = GreenDaoHelper.getInstance().getSeeion().getZiweiUserBeanDao();
        QueryBuilder<ZiweiUserBean> qb = dao.queryBuilder();
        List<ZiweiUserBean> list = qb.where(ZiweiUserBeanDao.Properties.Date.eq(bean.getDate())
                ,ZiweiUserBeanDao.Properties.Name.eq(bean.getName())
                ,ZiweiUserBeanDao.Properties.Sex.eq(bean.getSex())).list();
        long id = -1;
        if(list.size() == 0){
           id = dao.insert(bean);
        }
        return id ;
    }

    //清空历史记录
    @Override
    public Void delHistory() {
        ZiweiUserBeanDao dao = GreenDaoHelper.getInstance().getSeeion().getZiweiUserBeanDao();
        dao.deleteAll();
        return null;
    }
}
