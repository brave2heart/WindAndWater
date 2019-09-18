package com.living.adapter.mingli;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.living.bean.mingli.MingLiWebViewUrl;
import com.living.bean.mingli.T;
import com.living.bean.mingli.layout.FourGrid;
import com.living.bean.mingli.layout.HeadItem;
import com.living.bean.xuetang.GongXiu;
import com.living.bean.xuetang.Mv;
import com.living.bean.xuetang.QinSuan;
import com.living.bean.xuetang.ZhuanLan;
import com.living.constant.root.RootType;
import com.living.constant.xuetang.MVType;
import com.living.ui.activity.GongXiuHopeActivity;
import com.living.ui.activity.GongXiuLifeActivity;
import com.living.ui.activity.MVItemActivity;
import com.living.ui.activity.MVListActivity;
import com.living.utils.GotoUtil;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.bean.classroom.ShopHomeBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.ui.activity.calculate.chuantong.TraditionFaceActivity;
import com.tongcheng.soothsay.ui.activity.calculate.chuantong.ziwei.ZiweiInputActivity;
import com.tongcheng.soothsay.ui.activity.calculate.constellation.ConstellationActivity;
import com.tongcheng.soothsay.ui.activity.calculate.divination.DivinationActivity;
import com.tongcheng.soothsay.ui.activity.calculate.divination.GuanYinSignActivity;
import com.tongcheng.soothsay.ui.activity.calculate.divination.OneiromancyActivity;
import com.tongcheng.soothsay.ui.activity.calculate.life.LifeGuideActivity;
import com.tongcheng.soothsay.ui.activity.calculate.life.TestNameActivity;
import com.tongcheng.soothsay.ui.activity.calculate.love.MarriageLoveActivity;
import com.tongcheng.soothsay.ui.activity.calculate.nametest.BornBoyOrGirlActivity;
import com.tongcheng.soothsay.ui.activity.calculate.nametest.NameTestNameActivity;
import com.tongcheng.soothsay.ui.activity.calculate.zhuanyun.BaziPaipan.BaziPaipanInputActivity;
import com.tongcheng.soothsay.ui.activity.calculate.zhuanyun.CliffordActivity;
import com.tongcheng.soothsay.ui.activity.classroom.GoodsDetailsActivity;
import com.tongcheng.soothsay.ui.activity.classroom.MoreMasterActivity;
import com.tongcheng.soothsay.ui.activity.classroom.ShopClassifyInfosActivity;
import com.tongcheng.soothsay.ui.activity.classroom.goodsList.GoodsListActivity;
import com.tongcheng.soothsay.ui.activity.common.WebViewActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by weihao on 2018/1/9.
 */

public class RootFragmentItemRecyclerViewAdapter extends RecyclerView.Adapter<RootFragmentItemRecyclerViewAdapter.ViewHolder> {


    private final Context context;
    private int itemType;
    private List<T> t = new ArrayList<>();
    private String mImageName;


    public RootFragmentItemRecyclerViewAdapter(List<T> t, int itemType, Context context) {
        this.t = t;
        this.itemType = itemType;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mHeaditem_image;
        private TextView mHeaditem_name;
        private TextView mRemen_name;
        private TextView mRemen_content;
        private ImageView mRemen_image;
        private View mHeadItemView;
        private View mRemenItemView;
        private ImageView mZhuanlan_image;
        private TextView mZhuanlan_title;
        private TextView mZhuanlan_price_left;
        private TextView mZhuanlan_price_right;
        private ImageView mGx_head_image;
        private TextView mGx_title;
        private TextView mGx_label_text;
        private ImageView mGx_dian1;
        private TextView mGx_zhuan1;
        private ImageView mGx_dian2;
        private TextView mGx_zhuan2;
        private ImageView mGx_huati_image;
        private TextView mGx_huati_num;
        private ImageView mGx_pl_image;
        private TextView mGx_pl_num;
        private ImageView mGx_right_image;
        private TextView mZhuanlan_name;
        private TextView mZhuanlan_duration;
        private TextView mZhuanlan_label;
        private LinearLayout mZhuanglan_ll;
        private ImageView mQinsuan_image;
        private TextView mQinsuan_name;
        private TextView mQinsuan_zhouyi;
        private TextView mQinsuan_gx;
        private ImageView mQinsuan_jd;
        private TextView mQinsuan_jd_num;
        private ImageView mQinsuan_pl;
        private TextView mQinsuan_pl_num;
        private ImageView mQinsuan_cy;
        private TextView mQinsuan_cy_num;
        private ImageView mMv_image;
        private TextView mMv_title;
        private TextView mMv_name;
        private TextView mMv_duration;
        private TextView mMv_label;
        private TextView mMv_number;
        private TextView mMv_left_price;
        private TextView mMv_right_price;
        private View mMv_ItemView;
        private View mZhuanlan_itemView;
        private View mGx_itemView;
        private ImageView mShop_iamge;
        private TextView mShop_title;
        private TextView mShop_content;
        private TextView mShop_newprice;
        private TextView mShop_oldprice;
        private View mShop_ItemView;
        private ImageView mColumn_red;
        private TextView mColumn_name;
        private TextView mColumn_more;
        private ImageView mColumn_right;
        private RecyclerView mColumn_recycler;

        public ViewHolder(android.view.View itemView) {
            super(itemView);
            if (itemType == RootType.GRID_FOUR) {
                mHeadItemView = itemView;
                mHeaditem_image = itemView.findViewById(R.id.headitem_image);
                mHeaditem_name = itemView.findViewById(R.id.headitem_name);
            } else if (itemType == RootType.GRID_TWO) {
                mRemenItemView = itemView;
                mRemen_name = itemView.findViewById(R.id.mingli_remen_bazi);
                mRemen_content = itemView.findViewById(R.id.mingli_remen_bazi_content);
                mRemen_image = itemView.findViewById(R.id.mingli_remen_bazi_image);

            } else if (itemType == RootType.ZHUANLANITEM) {
                mZhuanlan_itemView = itemView;
                mZhuanlan_image = itemView.findViewById(R.id.xuetang_zhuanlan_iamge);
                mZhuanlan_title = itemView.findViewById(R.id.xuetang_zhuanlan_title);
                mZhuanlan_name = itemView.findViewById(R.id.xuetang_zhuanlan_name);
                mZhuanlan_duration = itemView.findViewById(R.id.xuetang_zhuanlan_duration);
                mZhuanlan_label = itemView.findViewById(R.id.xuetang_zhuanlan_label);
                mZhuanlan_price_left = itemView.findViewById(R.id.xuetang_zhuanlan_price_left);
                mZhuanlan_price_right = itemView.findViewById(R.id.xuetang_zhuanlan_price_right);
                mZhuanglan_ll = itemView.findViewById(R.id.xuetang_zhuanlan_ll);


            } else if (itemType == RootType.GONGXIUITEM) {
                mGx_itemView = itemView;
                mGx_head_image = itemView.findViewById(R.id.gongxou_iv_head_iamge);
                mGx_title = itemView.findViewById(R.id.gongxiu_tv_title);
                mGx_label_text = itemView.findViewById(R.id.gongxiu_labeltext);
                mGx_dian1 = itemView.findViewById(R.id.gongxiu_yuandian1);
                mGx_zhuan1 = itemView.findViewById(R.id.gongxiu_zhuantie1);
                mGx_dian2 = itemView.findViewById(R.id.gongxiu_yuandian2);
                mGx_zhuan2 = itemView.findViewById(R.id.gongxiu_zhuantie2);
                mGx_huati_image = itemView.findViewById(R.id.gongxiu_huati_image);
                mGx_huati_num = itemView.findViewById(R.id.gongxiu_huati_num);
                mGx_pl_image = itemView.findViewById(R.id.gongxiu_pl_image);
                mGx_pl_num = itemView.findViewById(R.id.gongxiu_pl_num);
                mGx_right_image = itemView.findViewById(R.id.gongxiu_right_iamge);

            } else if (itemType == RootType.QINSUANITEM) {
                mQinsuan_image = itemView.findViewById(R.id.qinsuan_image);
                mQinsuan_name = itemView.findViewById(R.id.qinsuan_name);
                mQinsuan_zhouyi = itemView.findViewById(R.id.qinsuan_zhouyi);
                mQinsuan_gx = itemView.findViewById(R.id.qinsuan_gx);
                mQinsuan_jd = itemView.findViewById(R.id.qinsuan_jd);
                mQinsuan_jd_num = itemView.findViewById(R.id.qinsuan_jd_num);
                mQinsuan_pl = itemView.findViewById(R.id.qinsuan_pl);
                mQinsuan_pl_num = itemView.findViewById(R.id.qinsuan_pl_num);
                mQinsuan_cy = itemView.findViewById(R.id.qinsuan_cy);
                mQinsuan_cy_num = itemView.findViewById(R.id.qinsuan_cy_num);

            } else if (itemType == RootType.MVITEM) {
                mMv_ItemView = itemView;
                mMv_image = itemView.findViewById(R.id.mv_iamge);
                mMv_title = itemView.findViewById(R.id.mv_title);
                mMv_name = itemView.findViewById(R.id.mv_name);
                mMv_duration = itemView.findViewById(R.id.mv_duration);
                mMv_label = itemView.findViewById(R.id.mv_label);
                mMv_number = itemView.findViewById(R.id.mv_number);
                mMv_left_price = itemView.findViewById(R.id.mv_left_price);
                mMv_right_price = itemView.findViewById(R.id.mv_right_price);

            } else if (itemType == RootType.SHOPITEM) {
                mShop_ItemView = itemView;
                mShop_iamge = itemView.findViewById(R.id.shop_item_image);
                mShop_title = itemView.findViewById(R.id.shop_item_title);
                mShop_content = itemView.findViewById(R.id.shop_item_content);
                mShop_newprice = itemView.findViewById(R.id.shop_item_newprice);
                mShop_oldprice = itemView.findViewById(R.id.shop_item_oldprice);


            } else if (itemType == RootType.COLUMN_RECYCLER) {
                mColumn_red = itemView.findViewById(R.id.column_iv_red);
                mColumn_name = itemView.findViewById(R.id.column_tv_name);
                mColumn_more = itemView.findViewById(R.id.column_tv_more);
                mColumn_right = itemView.findViewById(R.id.column_iv_right);
                mColumn_recycler = itemView.findViewById(R.id.column_recycler);

            }

        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        String lay = "fragment_mingli_item_";
        int layoutId = parent.getContext().getApplicationContext().getResources().getIdentifier(lay + (RootType.rootItemLayoutId[itemType]), "layout", parent.getContext().getApplicationContext().getPackageName());
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (itemType == RootType.GRID_FOUR) {

            HeadItem headItem = (HeadItem) t.get(position);
            holder.mHeaditem_image.setImageResource(headItem.getImage());
            mImageName = headItem.getImageName();
            holder.mHeaditem_name.setText(mImageName);
            HeadItem headItemType = (HeadItem) t.get(0);
            if ("八字测算".equals(headItemType.getImageName())) {
                holder.mHeadItemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (position) {
                            case 0://八字测算
                                GotoUtil.GoToActivity((Activity) context, TraditionFaceActivity.class);
                                break;
                            case 1: //合婚恋爱
                                GotoUtil.GoToActivity((Activity) context, MarriageLoveActivity.class);
                                break;
                            case 2://起名测名
                                GotoUtil.GoToActivity((Activity) context, NameTestNameActivity.class);
                                break;
                            case 3://星座塔罗
                                GotoUtil.GoToActivity((Activity) context, ConstellationActivity.class);
                                break;
                            case 4:// 大师精品
                                GotoUtil.GoToActivity((Activity) context, MoreMasterActivity.class);
                                break;
                            case 5://求签问事
                                GotoUtil.GoToActivity((Activity) context, DivinationActivity.class);
                                break;
                            case 6://祈福转运
                                HashMap<String, String> map = new HashMap<>();
                                map.put("name", "祈福转运");
                                GotoUtil.GoToActivityWithData((Activity) context, CliffordActivity.class, map);
                                break;
                            case 7://生活指南
                                GotoUtil.GoToActivity((Activity) context, LifeGuideActivity.class);
                                break;
                            default:
                                break;
                        }
                    }
                });
            } else if ("精选推荐".equals(headItemType.getImageName())) {
                holder.mHeadItemView.setOnClickListener(new View.OnClickListener() {
                    Intent intent = new Intent(context, MVListActivity.class);

                    @Override
                    public void onClick(View v) {
                        switch (position) {
                            case 0://精选推荐

                                Intent titleType1 = intent.putExtra("titleType", MVType.JINGXUAN);
                                context.startActivity(titleType1);
                                break;
                            case 1: //热门好课

                                Intent titleType2 = intent.putExtra("titleType", MVType.REMEN);
                                context.startActivity(titleType2);
                                break;
                            case 2://限时特价
                                Intent titleType3 = intent.putExtra("titleType", MVType.TEJIA);
                                context.startActivity(titleType3);
                                break;
                            case 3://好评爆款
                                Intent titleType4 = intent.putExtra("titleType", MVType.HAOPING);
                                context.startActivity(titleType4);
                                break;

                            default:
                                break;

                        }
                    }
                });
            } else if ("新品推荐".equals(headItemType.getImageName())) {
                holder.mHeadItemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (position) {
                            //新品推荐  or 热销圣品
                            case 0:
                            case 1:

                                Intent intent1 = new Intent(context.getApplicationContext(), GoodsListActivity.class);
                                intent1.putExtra(Constant.INTENT_DATA, mImageName);
                                context.startActivity(intent1);
                                break;

                            //其他的都是跳转到webview
//                    default:
//                        if (posotion>1) {
//                            String s = title[posotion];
//                            int i = posotion - 1;
//                            String url = "http://120.76.219.201:8080/html/sale.html?sortId=" + i;
//                            GotoUtil.GoToWebViewActivity(ShoppingHomeActivity.this, s, url);
//                        }
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:

                                String temp = Constant.Url.BASE_URL + "html/sale.html?sortId=%d";
                                String url = String.format(Locale.CHINA, temp, position);
                                Intent intent2 = new Intent(context.getApplicationContext(), WebViewActivity.class);
                                intent2.putExtra("web_url", url);
                                intent2.putExtra("web_title", mImageName);
                                context.startActivity(intent2);
                                break;
                            default:
                                break;
                        }
                    }
                });
            }


        } else if (itemType == RootType.GRID_TWO) {

            FourGrid baZi = (FourGrid) t.get(position);
            FourGrid baZiType = (FourGrid) t.get(0);
            String nameType = baZiType.getName();
            holder.mRemen_name.setText(baZi.getName());
            holder.mRemen_content.setText(baZi.getContent());
            holder.mRemen_image.setImageResource(baZi.getImage());
            if ("八字精批".equals(nameType)) {
                holder.mRemenItemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (position) {
                            case 0://八字排盘
//                                GotoUtil.GoToActivity((Activity) context, BaziPaipanInputActivity.class);
                                GotoUtil.GoToMingLiWebViewActivity((Activity) context, MingLiWebViewUrl.webViewTitles[0], MingLiWebViewUrl.BAZI_PAIPAN);
                                break;
                            case 1://八字合婚
//                                GotoUtil.GoToActivity((Activity) context, MarriageLoveActivity.class);
                                GotoUtil.GoToMingLiWebViewActivity((Activity) context, MingLiWebViewUrl.webViewTitles[1], MingLiWebViewUrl.BAZI_HEHUN);
                                break;
                            case 2: //在线起名
//                                GotoUtil.GoToActivity((Activity) context, BornBoyOrGirlActivity.class);
                                GotoUtil.GoToMingLiWebViewActivity((Activity) context, MingLiWebViewUrl.webViewTitles[2], MingLiWebViewUrl.ZAIXIAN_QIMING);
                                break;
                            case 3://姓名分析
//                                GotoUtil.GoToActivity((Activity) context, TestNameActivity.class);
                                GotoUtil.GoToMingLiWebViewActivity((Activity) context, MingLiWebViewUrl.webViewTitles[3], MingLiWebViewUrl.XINGMING_FENXI);
                                break;
                            default:
                                break;
                        }
                    }
                });
                /*
                *  2018运势：http://shengtai.polms.cn/index.php/Bzcs/Index/index/t/435/p/1
        周公解梦：http://shengtai.polms.cn/index.php/Zgjm1/Index/index/t/435/p/1
        观音灵签:http://shengtai.polms.cn/index.php/Chouqian/Index/index/t/435/p/2
        紫薇排盘：http://shengtai.polms.cn/index.php/Zwpp/Index/index/t/435/p/2
                * */
            } else if ("2018运势".equals(nameType)) {
                holder.mRemenItemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (position) {
                            case 0://2018运势
//                                GotoUtil.GoToActivity((Activity) context, BaziPaipanInputActivity.class);
                                GotoUtil.GoToMingLiWebViewActivity((Activity) context, MingLiWebViewUrl.webViewTitles[4], MingLiWebViewUrl.YUNSHI);
                                break;
                            case 1://周公解梦
                                GotoUtil.GoToMingLiWebViewActivity((Activity) context, MingLiWebViewUrl.webViewTitles[5], MingLiWebViewUrl.ZHOUGONG_JIEMENG);
                                break;
                            case 2: //观音灵签
                                GotoUtil.GoToMingLiWebViewActivity((Activity) context, MingLiWebViewUrl.webViewTitles[6], MingLiWebViewUrl.GUANYIN_LINGQIAN);
                                break;
                            case 3://紫薇斗数
                                GotoUtil.GoToMingLiWebViewActivity((Activity) context, MingLiWebViewUrl.webViewTitles[7], MingLiWebViewUrl.ZIWEI_PAIPAN);
                                break;
                            default:
                                break;
                        }
                    }
                });
            }


        } else if (itemType == RootType.ZHUANLANITEM) {
            ZhuanLan zhuanLan = (ZhuanLan) t.get(position);
            holder.mZhuanlan_image.setImageResource(zhuanLan.getImage());
            holder.mZhuanlan_title.setText(zhuanLan.getTitle());
            if ("".equals(zhuanLan.getName())) {
                holder.mZhuanglan_ll.setVisibility(View.GONE);
            } else {
                holder.mZhuanlan_name.setText(zhuanLan.getName());
                holder.mZhuanlan_duration.setText(zhuanLan.getDuration());
                holder.mZhuanlan_label.setText(zhuanLan.getLabel());
            }

            if ("免费".equals(zhuanLan.getLeftPrice())) {
                holder.mZhuanlan_price_left.setTextColor(context.getResources().getColor(R.color.goods_confirm_green));
                holder.mZhuanlan_price_left.setText(zhuanLan.getLeftPrice());
            } else {
                holder.mZhuanlan_price_left.setTextColor(context.getResources().getColor(R.color.red_light));
                holder.mZhuanlan_price_left.setText(zhuanLan.getLeftPrice());
            }

            //添加价格中间横线
            holder.mZhuanlan_price_right.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            //添加抗锯齿
            holder.mZhuanlan_price_right.getPaint().setAntiAlias(true);
            holder.mZhuanlan_price_right.setText(zhuanLan.getRightPrice());

            holder.mZhuanlan_itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    GotoUtil.GoToActivity((Activity) context, MVItemActivity.class);

                }
            });

        } else if (itemType == RootType.GONGXIUITEM) {
            final GongXiu gongXiu = (GongXiu) t.get(position);
            holder.mGx_head_image.setImageResource(gongXiu.getHeadimage());
            holder.mGx_title.setText(gongXiu.getTitle());
            holder.mGx_label_text.setText(gongXiu.getLabeltext());
            holder.mGx_dian1.setImageResource(gongXiu.getSmallDot1());
            holder.mGx_zhuan1.setText(gongXiu.getContent1());
            holder.mGx_dian2.setImageResource(gongXiu.getSmallDot2());
            holder.mGx_zhuan2.setText(gongXiu.getContent2());
            holder.mGx_huati_image.setImageResource(gongXiu.getHuatiImage());
            holder.mGx_huati_num.setText(gongXiu.getHuatiContent());
            holder.mGx_pl_image.setImageResource(gongXiu.getPlImage());
            holder.mGx_pl_num.setText(gongXiu.getPlContent());
            holder.mGx_right_image.setImageResource(gongXiu.getRightImage());

            holder.mGx_itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    ClassRoomBean.CirclesBean tag1 = (ClassRoomBean.CirclesBean) holder.mGx_itemView.getTag();
//                    if (tag1 != null) {
//                        String url = tag1.getRedirectUrl();
//                        if (UserManager.getInstance().isLogin()) {
//                            url = url + UserManager.getInstance().getToken();
//                        }
//                        GotoUtil.GoToWebViewActivity(context, tag1.getName(), url);
//                    }
//                    Toast.makeText(context, "yyyyyyyyy", Toast.LENGTH_SHORT).show();
                    if (gongXiu.getType() == 1) {
                        context.startActivity(new Intent(v.getContext(), GongXiuLifeActivity.class));
                    } else if (gongXiu.getType() == 2) {
                        context.startActivity(new Intent(v.getContext(), GongXiuHopeActivity.class));
                    }

                }
            });


        } else if (itemType == RootType.QINSUANITEM) {
            QinSuan qinSuan = (QinSuan) t.get(position);
            holder.mQinsuan_image.setImageResource(qinSuan.getImage());
            holder.mQinsuan_name.setText(qinSuan.getName());
            holder.mQinsuan_zhouyi.setText(qinSuan.getLabelZY());
            holder.mQinsuan_gx.setText(qinSuan.getLabelGX());
            holder.mQinsuan_jd.setImageResource(qinSuan.getJdImage());
            holder.mQinsuan_jd_num.setText(qinSuan.getJdNum());
            holder.mQinsuan_pl.setImageResource(qinSuan.getPlImage());
            holder.mQinsuan_pl_num.setText(qinSuan.getPlNum());
            holder.mQinsuan_cy.setImageResource(qinSuan.getCyImage());
            holder.mQinsuan_cy_num.setText(qinSuan.getCyNum());
        } else if (itemType == RootType.MVITEM) {

            Mv mv = (Mv) t.get(position);
            holder.mMv_image.setImageResource(mv.getMvImage());


            holder.mMv_title.setText(mv.getTitle());
            holder.mMv_name.setText(mv.getName());
            holder.mMv_duration.setText(mv.getDuration());
            if ("专栏".equals(mv.getLabel())) {

                holder.mMv_label.setTextColor(context.getResources().getColor(R.color.white));
                holder.mMv_label.setBackgroundColor(context.getResources().getColor(R.color.red_light));
                holder.mMv_label.setText(mv.getLabel());
            } else {
                holder.mMv_label.setTextColor(context.getResources().getColor(R.color.white));
                holder.mMv_label.setBackgroundColor(context.getResources().getColor(R.color.goods_confirm_green));
                holder.mMv_label.setText(mv.getLabel());
            }

            holder.mMv_number.setText(mv.getNumber());

            //添加价格中间横线
            holder.mMv_left_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            //添加抗锯齿
            holder.mMv_left_price.getPaint().setAntiAlias(true);
            holder.mMv_left_price.setText(mv.getLeftPrice());

            holder.mMv_right_price.setText(mv.getRightPrice());

            //子条目点击
            holder.mMv_ItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    GotoUtil.GoToActivity((Activity) context, MVItemActivity.class);

                }
            });
        } else if (itemType == RootType.SHOPITEM) {

            final ShopHomeBean.StoreInfosBean.StoresBean storesBean = (ShopHomeBean.StoreInfosBean.StoresBean) t.get(position);
            Glide.with(context).load(storesBean.getFacePic()).into(holder.mShop_iamge);
            holder.mShop_title.setText(storesBean.getName());
            holder.mShop_content.setText(storesBean.getYinyu());
            holder.mShop_newprice.setText("￥" + storesBean.getPrice());

            holder.mShop_ItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String merchandiseId = storesBean.getMerchandiseId();//商品id
                    Intent intent = new Intent(context, GoodsDetailsActivity.class);
                    intent.putExtra("goodsid", merchandiseId);
                    intent.putExtra("redirectUrl", storesBean.getRedirectUrl());
                    context.startActivity(intent);
                }
            });

        } else if (itemType == RootType.COLUMN_RECYCLER) {
            final ShopHomeBean.StoreInfosBean storeInfosBean = (ShopHomeBean.StoreInfosBean) t.get(position);

            holder.mColumn_red.setImageResource(R.drawable.shape_red_rectangles);
            holder.mColumn_name.setText(storeInfosBean.getSortName());
            holder.mColumn_more.setText("更多");
            holder.mColumn_right.setImageResource(R.drawable.tv_right);
            holder.mColumn_recycler.setLayoutManager(new GridLayoutManager(context, 2));
            List<ShopHomeBean.StoreInfosBean.StoresBean> stores = storeInfosBean.getStores();
            holder.mColumn_recycler.setAdapter(new ShopItemRecyclerViewAdapter(stores, context));
//            holder.mColumn_recycler.setAdapter(new RootFragmentItemRecyclerViewAdapter(t,RootType.SHOPITEM,context));
            holder.mColumn_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ShopClassifyInfosActivity.class);
                    intent.putExtra("sortId", storeInfosBean.getSortId());
                    intent.putExtra("sortName", storeInfosBean.getSortName());
                    context.startActivity(intent);
                }
            });
            holder.mColumn_right.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ShopClassifyInfosActivity.class);
                    intent.putExtra("sortId", storeInfosBean.getSortId());
                    intent.putExtra("sortName", storeInfosBean.getSortName());
                    context.startActivity(intent);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return t.size();
    }
}
