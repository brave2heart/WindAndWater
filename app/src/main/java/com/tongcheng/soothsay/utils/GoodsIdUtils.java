package com.tongcheng.soothsay.utils;

import com.tongcheng.soothsay.bean.dao.GoodsBean;
import com.tongcheng.soothsay.bean.dao.GoodsBeanDao;
import com.tongcheng.soothsay.helper.GreenDaoHelper;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by 宋家任 on 2016/12/23.
 * 购物车商品操作类
 */

public class GoodsIdUtils {
    private static GoodsIdUtils goodsIdUtils;

    private GoodsIdUtils() {
    }

    public static GoodsIdUtils getInstance() {
        if (goodsIdUtils == null) {
            goodsIdUtils = new GoodsIdUtils();
        }
        return goodsIdUtils;
    }

    public List<GoodsBean> getGoodsList() {
        GoodsBeanDao dao = GreenDaoHelper.getInstance().getSeeion().getGoodsBeanDao();
        List<GoodsBean> beans = dao.queryBuilder().list();
        return beans;
    }

    public Void saveGoods(GoodsBean bean) {
        GoodsBeanDao dao = GreenDaoHelper.getInstance().getSeeion().getGoodsBeanDao();
        dao.insert(bean);
        return null;
    }

    public Void deleteGoodsList() {
        List<GoodsBean> goodsList = getGoodsList();
        if (goodsList.size() != 0)
            for (GoodsBean bean : goodsList) {
                deletGoods(bean);
            }
        return null;
    }

    public GoodsBean getGoodsBean(String goodsId) {
        GoodsBeanDao dao = GreenDaoHelper.getInstance().getSeeion().getGoodsBeanDao();
        if (dao.queryBuilder().where(GoodsBeanDao.Properties.GoodsId.eq(goodsId)).unique() == null) {
            return null;
        }
        return dao.queryBuilder().where(GoodsBeanDao.Properties.GoodsId.eq(goodsId)).unique();

    }

    public boolean isHaveGoods(String goodsId) {
        GoodsBean goodsBean = getGoodsBean(goodsId);
        if (goodsBean == null) return false;
        else return true;

    }

    /**
     * 获取商品id
     */
//    public String getGoodsid() {
//        GoodsBean bean = getGoodsBean();
//        if (bean != null) {
//            return bean.getGoodsId();
//        }
//        return null;
//    }
    public Void updataGoods(GoodsBean bean) {
        GoodsBeanDao dao = GreenDaoHelper.getInstance().getSeeion().getGoodsBeanDao();
        dao.update(bean);
        return null;
    }

    public Void deletGoods(GoodsBean bean) {
        GoodsBeanDao dao = GreenDaoHelper.getInstance().getSeeion().getGoodsBeanDao();
        dao.delete(bean);
        return null;
    }
}
