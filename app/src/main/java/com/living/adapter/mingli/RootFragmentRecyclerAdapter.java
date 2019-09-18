package com.living.adapter.mingli;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.living.bean.mingli.T;
import com.living.bean.mingli.layout.Column;
import com.living.bean.mingli.layout.DaShi;
import com.living.bean.mingli.layout.RecyclerViewItemData;
import com.living.bean.xuetang.ZhouYi;
import com.living.constant.root.RootType;
import com.living.constant.xuetang.MVType;
import com.living.ui.activity.MVListActivity;
import com.living.utils.GlideImageLoader;
import com.living.utils.GotoUtil;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.bean.calculation.MingLiBean;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.ui.activity.calculate.freepool.FreePoolActivity;
import com.tongcheng.soothsay.ui.activity.classroom.MoreMasterActivity;
import com.tongcheng.soothsay.ui.activity.classroom.NewsActivity;
import com.tongcheng.soothsay.ui.fragment.classroom.CircleListActivity;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weihao on 2018/1/9.
 */

public class RootFragmentRecyclerAdapter extends RecyclerView.Adapter<RootFragmentRecyclerAdapter.ViewHolder> {


    private final Context context;
    private List<RecyclerViewItemData> mItemDatas = new ArrayList<>();
    private int mDataType;
    private ViewHolder mHolder;

    public RootFragmentRecyclerAdapter(List<RecyclerViewItemData> ItemDatas, Context context) {
        mItemDatas = ItemDatas;

        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView mHeaditem_recycler;
        private ImageView mColumn_red;
        private TextView mColumn_name;
        private TextView mColumn_more;
        private ImageView mColumn_right;
        private ImageView mFangimage;
        private com.youth.banner.Banner mMingli_banner;
        private ImageView mDashi_image;
        private TextView mDashi_name;
        private TextView mDashi_content;
        private TextView mDashi_ability;
        private TextView mDashi_zhouyi;
        private TextView mDashi_gx;
        private TextView mDashi_zhuanye;
        private TextView mDashi_hp;
        private ImageView mDashi_jd;
        private TextView mDashi_jd_num;
        private ImageView mDashi_pl;
        private TextView mDashi_pl_num;
        private ImageView mDashi_cy;
        private TextView mDashi_cy_num;
        private View mDashi_ItemView;
        private View mColumnItemView;
        private ImageView mZhouyi_bazi;
        private ImageView mZhouyi_fengshui;
        private ImageView mZhouyi_shouxiang;
        private ImageView mZhouyi_shengxiao;
        private ImageView mZhouyi_xingzuo;


        public ViewHolder(View itemView) {
            super(itemView);
            if (mDataType == RootType.BANNER) {

                mMingli_banner = itemView.findViewById(R.id.mingli_banner);

            } else if (mDataType == RootType.RECYCLER_ITEM) {

                mHeaditem_recycler = itemView.findViewById(R.id.mingli_headitem_recycler);

            } else if (mDataType == RootType.COLUMN) {
                mColumnItemView = itemView;
                mColumn_red = itemView.findViewById(R.id.column_iv_red);
                mColumn_name = itemView.findViewById(R.id.column_tv_name);
                mColumn_more = itemView.findViewById(R.id.column_tv_more);
                mColumn_right = itemView.findViewById(R.id.column_iv_right);

            } else if (mDataType == RootType.FANGIMAGE) {

                mFangimage = itemView.findViewById(R.id.mingli_fangimage);


            } else if (mDataType == RootType.DASHI) {
                mDashi_ItemView = itemView;
                mDashi_image = itemView.findViewById(R.id.mingli_dashi_image);
                mDashi_name = itemView.findViewById(R.id.mingli_dashi_name);
                mDashi_content = itemView.findViewById(R.id.mingli_dashi_content);
                mDashi_ability = itemView.findViewById(R.id.mingli_dashi_ability);
                mDashi_zhouyi = itemView.findViewById(R.id.mingli_dahsi_zhouyi);
                mDashi_gx = itemView.findViewById(R.id.mingli_dashi_gx);
                mDashi_zhuanye = itemView.findViewById(R.id.mingli_dashi_zhuanye);
                mDashi_hp = itemView.findViewById(R.id.mingli_dashi_hp);
                mDashi_jd = itemView.findViewById(R.id.mingli_dashi_jd);
                mDashi_jd_num = itemView.findViewById(R.id.mingli_dashi_jd_num);
                mDashi_pl = itemView.findViewById(R.id.mingli_dashi_pl);
                mDashi_pl_num = itemView.findViewById(R.id.mingli_dashi_pl_num);
                mDashi_cy = itemView.findViewById(R.id.mingli_dashi_cy);
                mDashi_cy_num = itemView.findViewById(R.id.mingli_dashi_cy_num);


            } else if (mDataType == RootType.ZHOUYI) {
                mZhouyi_bazi = itemView.findViewById(R.id.zhouyi_bazi);
                mZhouyi_fengshui = itemView.findViewById(R.id.zhouyi_fengshui);
                mZhouyi_shouxiang = itemView.findViewById(R.id.zhouyi_shouxiang);
                mZhouyi_shengxiao = itemView.findViewById(R.id.zhouyi_shengxiao);
                mZhouyi_xingzuo = itemView.findViewById(R.id.zhouyi_xingzuo);

            }
        }
    }


    @Override
    public int getItemViewType(int position) {
        mDataType = mItemDatas.get(position).getDataType();
        return mDataType;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        String lay = "fragment_mingli_";
        int layoutId = parent.getContext().getApplicationContext().getResources().getIdentifier(lay + (RootType.rootLayoutId[mDataType]), "layout", parent.getContext().getApplicationContext().getPackageName());
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int dataType = mItemDatas.get(position).getDataType();
        if (dataType == RootType.BANNER) {

            List<String> stringList = (List<String>) mItemDatas.get(position).getT();
//            List<Integer> integerList = (List<Integer>) mItemDatas.get(position).getT();
////            List<String> mAdsURL = new ArrayList<>();  //图片
//            List<String> mAdsTitle = new ArrayList<>(); //标题
//            List<MingLiBean.AdsBean> adsBeans = (List<MingLiBean.AdsBean>) mItemDatas.get(position).getT();
//            for (int i = 0; i < adsBeans.size(); i++) {
//                String url = adsBeans.get(i).getUrl();
//                mAdsURL.add(url);
//                String title = adsBeans.get(i).getTitle();
//                mAdsTitle.add(title);
//            }


            holder.mMingli_banner.setImageLoader(new GlideImageLoader());
            holder.mMingli_banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);//设置圆形指示器与标题
            holder.mMingli_banner.setIndicatorGravity(BannerConfig.RIGHT);//设置指示器位置
            holder.mMingli_banner.setBannerAnimation(Transformer.ForegroundToBackground);
            holder.mMingli_banner.setDelayTime(2000);//设置轮播时间
            holder.mMingli_banner.setImages(stringList);//设置图片源
//            holder.mMingli_banner.setBannerTitles("");//设置标题源
            holder.mMingli_banner.start();
//            mingLiFragment.setBannerData(holder.mMingli_banner, integerList);

            //出现嵌套使用RecyclerView时
        } else if (dataType == RootType.RECYCLER_ITEM) {
            List<RecyclerViewItemData> itemDatas = (List<RecyclerViewItemData>) mItemDatas.get(position).getT();
            List<T> t = (List<T>) itemDatas.get(0).getT();

            int itemdataType = itemDatas.get(0).getDataType();
            if (itemdataType == RootType.GRID_FOUR) {
                //网格布局，4列
                setGridLayout(holder.mHeaditem_recycler, 4, t, itemdataType, context);
            } else if (itemdataType == RootType.GRID_TWO) {
                //网格布局，2列
                setGridLayout(holder.mHeaditem_recycler, 2, t, itemdataType, context);
            } else if (itemdataType == RootType.ZHUANLANITEM) {

                //设置水平布局
                setLinearLayoutHorizontal(holder.mHeaditem_recycler, t, itemdataType, context);


            } else if (itemdataType == RootType.GONGXIUITEM) {

                //设置垂直布局
                setLinearLayoutVertical(holder.mHeaditem_recycler, t, itemdataType, context);


            } else if (itemdataType == RootType.QINSUANITEM) {

                //设置水平布局
                setLinearLayoutHorizontal(holder.mHeaditem_recycler, t, itemdataType, context);


            } else if (itemdataType == RootType.COLUMN_RECYCLER) {
                //设置垂直布局
                setLinearLayoutVertical(holder.mHeaditem_recycler, t, itemdataType, context);
            }


        } else if (dataType == RootType.COLUMN) {

            final Column column = (Column) mItemDatas.get(position).getT();
            holder.mColumn_red.setImageResource(column.getImage());
            holder.mColumn_name.setText(column.getColumnName());
            holder.mColumn_more.setText(column.getMoreName());
            holder.mColumn_right.setImageResource(column.getRightImage());
            //点击事件
            holder.mColumnItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //防止暴力点击
                    if (ClickUtil.isFastClick()) {
                        return;
                    }
                    Intent intent = new Intent(context, MVListActivity.class);
                    if ("放生池".equals(column.getColumnName())) {
                        GotoUtil.GoToActivity((Activity) context, FreePoolActivity.class);
                    } else if ("大师推荐".equals(column.getColumnName())) {
                        GotoUtil.GoToActivity((Activity) context, MoreMasterActivity.class);
                    } else if ("专栏推荐".equals(column.getColumnName())) {
                        Intent titleType = intent.putExtra("titleType", MVType.ZHUANLAN);
                        context.startActivity(titleType);
                    } else if ("课程推荐".equals(column.getColumnName())) {
                        Intent titleType = intent.putExtra("titleType", MVType.KECHENG);
                        context.startActivity(titleType);
                    } else if ("共修圈子".equals(column.getColumnName())) {
                        GotoUtil.GoToActivity((Activity) context, CircleListActivity.class);
                    } else if ("周易知识".equals(column.getColumnName())) {
                        GotoUtil.GoToActivity((Activity) context, NewsActivity.class);
                    }
                }
            });
        } else if (dataType == RootType.FANGIMAGE) {
            int imageId = (int) mItemDatas.get(position).getT();
            holder.mFangimage.setImageResource(imageId);
            holder.mFangimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //放生池
                    GotoUtil.GoToActivity((Activity) context, FreePoolActivity.class);
                }
            });
        } else if (dataType == RootType.DASHI) {
            DaShi daShi = (DaShi) mItemDatas.get(position).getT();
            List<MingLiBean.DsBean> dsBeans = daShi.getDs();
            final MingLiBean.DsBean dsBean = dsBeans.get(0);

            /**
             * chengHao : 李大仙
             * id : 3
             * jianJie : 这是一个风水大师
             * name : 李大师
             * shanChang : 擅长算命，风水
             * touXiang : http://120.76.219.201:8080/file/getLocalFile.do?fileType=cacheImages&fileName=37621483430097944.png
             * url : http://120.76.219.201:8080/html/dashixiangqing.html?dsId=3&token=
             */

            Glide.with(context).load(dsBean.getTouXiang()).into(holder.mDashi_image);

            holder.mDashi_name.setText(dsBean.getName());
            holder.mDashi_content.setText(dsBean.getJianJie());
            holder.mDashi_ability.setText(dsBean.getShanChang());
            holder.mDashi_zhouyi.setText(daShi.getDashiZhouYi());
            holder.mDashi_gx.setText(daShi.getDashiGX());
            holder.mDashi_zhuanye.setText(daShi.getDashiZhuanYe());
            holder.mDashi_hp.setText(daShi.getDashiHP());
            holder.mDashi_jd.setImageResource(daShi.getDashiJDImage());
            holder.mDashi_jd_num.setText(daShi.getDashiJDNum());
            holder.mDashi_pl.setImageResource(daShi.getDashiPLImage());
            holder.mDashi_pl_num.setText(daShi.getDashiPLNum());
            holder.mDashi_cy.setImageResource(daShi.getDashiCYImage());
            holder.mDashi_cy_num.setText(daShi.getDashiCYNum());

            holder.mDashi_ItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = dsBean.getUrl();
                    if (UserManager.getInstance().isLogin()) {
                        url = url + UserManager.getInstance().getToken();
                    }
                    GotoUtil.GoToWebViewActivity(context, dsBean.getName(), url);
                }
            });
        } else if (dataType == RootType.ZHOUYI) {
            ZhouYi zhouYi = (ZhouYi) mItemDatas.get(position).getT();
            holder.mZhouyi_bazi.setImageResource(zhouYi.getZhouYiImage1()); //八字
            holder.mZhouyi_bazi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gotoNewsActivity(5);
                }
            });
            holder.mZhouyi_fengshui.setImageResource(zhouYi.getZhouYiImage2());
            holder.mZhouyi_fengshui.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gotoNewsActivity(2);
                }
            });
            holder.mZhouyi_shouxiang.setImageResource(zhouYi.getZhouYiImage3());
            holder.mZhouyi_shouxiang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gotoNewsActivity(4);
                }
            });
            holder.mZhouyi_shengxiao.setImageResource(zhouYi.getZhouYiImage4());
            holder.mZhouyi_shengxiao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gotoNewsActivity(1);
                }
            });
            holder.mZhouyi_xingzuo.setImageResource(zhouYi.getZhouYiImage5());
            holder.mZhouyi_xingzuo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gotoNewsActivity(3);
                }
            });


        }


    }


    public void gotoNewsActivity(int newsid) {
        Intent intent = new Intent(context, NewsActivity.class);
        intent.putExtra("NewsID", newsid);
        context.startActivity(intent);
    }


    private void setLinearLayoutVertical(RecyclerView headitem_recycler, List<T> t, int itemdataType, Context context) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);  //设置垂直布局
        headitem_recycler.setLayoutManager(layoutManager);
        headitem_recycler.setAdapter(new RootFragmentItemRecyclerViewAdapter(t, itemdataType, context));

    }

    private void setLinearLayoutHorizontal(RecyclerView headitem_recycler, List<T> t, int itemdataType, Context context) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);  //设置水平布局滚动
        headitem_recycler.setLayoutManager(layoutManager);
        headitem_recycler.setAdapter(new RootFragmentItemRecyclerViewAdapter(t, itemdataType, context));

    }

    private void setGridLayout(RecyclerView headitem_recycler, int spanCount, List<T> t, int itemdataType, Context context) {
        headitem_recycler.setLayoutManager(new GridLayoutManager(context, spanCount));
        headitem_recycler.setAdapter(new RootFragmentItemRecyclerViewAdapter(t, itemdataType, context));
    }

    @Override
    public int getItemCount() {
        return mItemDatas.size();
    }


}
