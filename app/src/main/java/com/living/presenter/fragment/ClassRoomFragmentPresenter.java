package com.living.presenter.fragment;

import android.content.res.TypedArray;

import com.living.bean.mingli.T;
import com.living.bean.mingli.layout.Column;
import com.living.bean.mingli.layout.FourGrid;
import com.living.bean.mingli.layout.HeadItem;
import com.living.bean.mingli.layout.RecyclerViewItemData;
import com.living.bean.xuetang.GongXiu;
import com.living.bean.xuetang.QinSuan;
import com.living.bean.xuetang.ZhouYi;
import com.living.bean.xuetang.ZhuanLan;
import com.living.constant.root.RootType;
import com.living.ui.fragment.ClassRoomFragment;
import com.tongcheng.soothsay.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weihao on 2018/1/24.
 */

public class ClassRoomFragmentPresenter {

    private ClassRoomFragment mClassRoomFragment;
    private List<RecyclerViewItemData> mRecyclerItemDatas = new ArrayList();

    public ClassRoomFragmentPresenter(ClassRoomFragment classRoomFragment) {
        mClassRoomFragment = classRoomFragment;
    }

    //学堂fragment数据
    public void getClassRoomData() {
        //初始化所有数据
        //初始化广告条
//        initBanner(R.drawable.xuetang_lunbotu1, R.drawable.xuetang_lunbotu1, R.drawable.xuetang_lunbotu1);
        String url1 = "http://img.zcool.cn/community/01680c57676fda0000018c1bbf5330.jpg@900w_1l_2o_100sh.jpg";
        String url2 = "http://pic.58pic.com/58pic/17/68/74/16958PICbWZ_1024.jpg";
        String url3 = "http://g.hiphotos.baidu.com/baike/pic/item/0b46f21fbe096b6310a20bde0c338744eaf8ac81.jpg";
        initBanner(url1, url2, url3);

        //初始化4个 headitem数据
        initHead(R.array.ClassRoomheadName, R.array.ClassRoomheadimage);
        //专栏推荐
        mRecyclerItemDatas.add(new RecyclerViewItemData(new Column(R.drawable.shape_red_rectangles, "专栏推荐", "更多", R.drawable.tv_right), RootType.COLUMN));
        //专栏推荐数据
        initZhuanLan();

        //课程推荐
        mRecyclerItemDatas.add(new RecyclerViewItemData(new Column(R.drawable.shape_red_rectangles, "课程推荐", "更多", R.drawable.tv_right), RootType.COLUMN));
        //课程推荐数据
        initKeCheng();
        //周易知识
        mRecyclerItemDatas.add(new RecyclerViewItemData(new Column(R.drawable.shape_red_rectangles, "周易知识", "更多", R.drawable.tv_right), RootType.COLUMN));
        //周易知识数据
        initZhouYi();
        //放生池图片
//        mRecyclerItemDatas.add(new RecyclerViewItemData(R.drawable.mingli_fashengchi, .FANGIMAGE));
//        initReMenData();
        //共修圈子
        mRecyclerItemDatas.add(new RecyclerViewItemData(new Column(R.drawable.shape_red_rectangles, "共修圈子", "更多", R.drawable.tv_right), RootType.COLUMN));
        //共修圈子数据
        initGX();
        //风水推荐
        mRecyclerItemDatas.add(new RecyclerViewItemData(new Column(R.drawable.shape_red_rectangles, "风水推荐", "更多", R.drawable.tv_right), RootType.COLUMN));
        //风水推荐数据
        initFourGrid(R.array.classroomfengshuiName, R.array.classroomfengshuiContent, R.array.classroomfengshuiImage);
        //大师亲算
        mRecyclerItemDatas.add(new RecyclerViewItemData(new Column(R.drawable.shape_red_rectangles, "大师亲算", "更多", R.drawable.tv_right), RootType.COLUMN));
        //大师亲算数据
        initQinShuan();


        mClassRoomFragment.setClassRoomData(mRecyclerItemDatas);
    }

    //轮播图
    private void initBanner(String url1, String url2, String url3) {
//
        List<String> strUrl = new ArrayList<>();
        strUrl.add(url1);
        strUrl.add(url2);
        strUrl.add(url3);
//        strUrl.add("https://www.baidu.com/img/bd_logo1.png");


//                        http://fssvip.b0.upaiyun.com/fss/81201504166465658.jpg
//                        http://fssvip.b0.upaiyun.com/fss/45531504167604026.jpg
//                        http://fssvip.b0.upaiyun.com/fss/30551504167961086.jpg
//        List<Integer> integerList = new ArrayList<>();
//        integerList.add(imageId1);
//        integerList.add(imageId2);
//        integerList.add(imageId3);
        RecyclerViewItemData banners = new RecyclerViewItemData(strUrl, RootType.BANNER);
        mRecyclerItemDatas.add(banners);
    }

    //顶部网格布局，8个item
    private void initHead(int nameId, int imageId) {
        List<T> mHeadItems = new ArrayList<>();
        List<RecyclerViewItemData> headItemDatas = new ArrayList<>();

        String[] headName = mClassRoomFragment.getActivity().getResources().getStringArray(nameId);
        int[] headImage = new int[headName.length];
        TypedArray imgs = mClassRoomFragment.getActivity().getResources().obtainTypedArray(imageId);
        for (int i = 0; i < imgs.length(); i++) {
            int resourceId = imgs.getResourceId(i, -1);
            headImage[i] = resourceId;
        }

        for (int i = 0; i < headName.length; i++) {
            T headItem = new HeadItem(headImage[i], headName[i]);
            mHeadItems.add(headItem);
        }
        RecyclerViewItemData headItems = new RecyclerViewItemData(mHeadItems, RootType.GRID_FOUR);
        headItemDatas.add(headItems);
        RecyclerViewItemData headDatas = new RecyclerViewItemData(headItemDatas, RootType.RECYCLER_ITEM);
        mRecyclerItemDatas.add(headDatas);

    }

    //网格布局，4个item
    private void initFourGrid(int nameId, int contentId, int imageId) {

        String[] names = mClassRoomFragment.getActivity().getResources().getStringArray(nameId);
        String[] contents = mClassRoomFragment.getActivity().getResources().getStringArray(contentId);
        int[] images = new int[names.length];
        TypedArray imgs = mClassRoomFragment.getActivity().getResources().obtainTypedArray(imageId);
        for (int i = 0; i < imgs.length(); i++) {
            int resourceId = imgs.getResourceId(i, -1);
            images[i] = resourceId;
        }

        //((热门测算数据，标记1),标记1);  相当于重复嵌套RecyclerViewItemData类来标记2次来区分到子类的数据判断

        List<T> interiorDatas = new ArrayList<>();
        List<RecyclerViewItemData> listRecyItemDatas = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            T baZi = new FourGrid(names[i], contents[i], images[i]);
            interiorDatas.add(baZi);
        }
//        内层数据type remenitem
        RecyclerViewItemData recyItemData = new RecyclerViewItemData(interiorDatas, RootType.GRID_TWO);
        listRecyItemDatas.add(recyItemData);

//        外层数据type  head
        RecyclerViewItemData recyclerViewItemDatas = new RecyclerViewItemData(listRecyItemDatas, RootType.RECYCLER_ITEM);
        mRecyclerItemDatas.add(recyclerViewItemDatas);


    }

    private void initZhuanLan() {
        List<ZhuanLan> mZhuanlan = new ArrayList<>();
        List<RecyclerViewItemData> viewItemData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ZhuanLan zhuanLan1 = new ZhuanLan(R.drawable.xuetang_kecheng_kanxiang,
                    "2018生肖属相全年运势大揭秘",
                    "",
                    "",
                    "",
                    "￥99.0",
                    "￥129"
            );
            mZhuanlan.add(zhuanLan1);
            ZhuanLan zhuanLan2 = new ZhuanLan(R.drawable.xuetang_item_wudao,
                    "物道：用精致装点美好生活（已完结）",
                    "",
                    "",
                    "",
                    "￥99.0",
                    ""
            );
            mZhuanlan.add(zhuanLan2);
            ZhuanLan zhuanLan3 = new ZhuanLan(R.drawable.xuetang_kecheng_fengshui,
                    "教孩子大声说不之不要随便欺负我",
                    "",
                    "",
                    "",
                    "免费",
                    ""
            );
            mZhuanlan.add(zhuanLan3);

        }

        RecyclerViewItemData itemData = new RecyclerViewItemData(mZhuanlan, RootType.ZHUANLANITEM);
        viewItemData.add(itemData);
        RecyclerViewItemData mZhuanLanItems = new RecyclerViewItemData(viewItemData, RootType.RECYCLER_ITEM);

        mRecyclerItemDatas.add(mZhuanLanItems);


    }

    private void initKeCheng() {
        List<ZhuanLan> mKeCheng = new ArrayList<>();
        List<RecyclerViewItemData> viewItemData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ZhuanLan kecheng1 = new ZhuanLan(R.drawable.xuetang_kecheng_shengxiao,
                    "2018生肖属相全年运势大揭秘",
                    "智慧先生",
                    "时长10:30",
                    "推荐",
                    "￥99.0",
                    "￥129"
            );
            mKeCheng.add(kecheng1);
            ZhuanLan kecheng2 = new ZhuanLan(R.drawable.xuetang_zhuanlan_duwu,
                    "物道：用精致装点美好生活（已完结）",
                    "智慧先生",
                    "时长10:30",
                    "推荐",
                    "￥99.0",
                    ""
            );
            mKeCheng.add(kecheng2);
            ZhuanLan kecheng3 = new ZhuanLan(R.drawable.xuetang_kecheng_miaomi,
                    "教孩子大声说不之不要随便欺负我",
                    "智慧先生",
                    "时长10:30",
                    "推荐",
                    "免费",
                    ""
            );
            mKeCheng.add(kecheng3);

        }

        RecyclerViewItemData itemData = new RecyclerViewItemData(mKeCheng, RootType.ZHUANLANITEM);
        viewItemData.add(itemData);
        RecyclerViewItemData mZhuanLanItems = new RecyclerViewItemData(viewItemData, RootType.RECYCLER_ITEM);

        mRecyclerItemDatas.add(mZhuanLanItems);


    }

    private void initZhouYi() {
        int[] mZhouYi = new int[5];
        TypedArray imags = mClassRoomFragment.getActivity().getResources().obtainTypedArray(R.array.zhouyiImage);
        for (int i = 0; i < imags.length(); i++) {
            int resourceId = imags.getResourceId(i, -1);
            mZhouYi[i] = resourceId;
        }

        ZhouYi zhouYi = new ZhouYi(
                mZhouYi[0],
                mZhouYi[1],
                mZhouYi[2],
                mZhouYi[3],
                mZhouYi[4]);

        RecyclerViewItemData itemDatas = new RecyclerViewItemData(zhouYi, RootType.ZHOUYI);
        mRecyclerItemDatas.add(itemDatas);

    }

    private void initGX() {

        /*
        *     private int headimage;
    private String title;
    private int labelImage;
    private int smallDot1;
    private String content1;
    private int smallDot2;
    private String content2;
    private int huatiImage;
    private String huatiContent;
    private int plImage;
    private String plContent;
        * */
        List<GongXiu> mGX = new ArrayList<>();
        List<RecyclerViewItemData> viewItemData = new ArrayList<>();


        GongXiu gongXiu1 = new GongXiu(
                R.drawable.xuetang_gongxiu_shan,
                "生活感悟",
                "人气",
                R.drawable.shape_xiaoyuandian,
                "转贴：借什么也别借钱转贴：借什么也别借钱转贴：借什么也别借钱转贴：借什么也别借钱转贴：借什么也别借钱",
                R.drawable.shape_xiaoyuandian,
                "不评价别人的生话，是一个人最基本的素养，转贴：借什么也别借钱转贴：借什么也别借钱转贴：借什么也别借钱转贴：借什么也别借钱转贴：借什么也别借钱",
                R.mipmap.pinglun,
                "98个话题",
                R.mipmap.canyu,
                "1764人参与",
                R.drawable.xuetang_gongxiu_image2, 1
        );
        mGX.add(gongXiu1);


        GongXiu gongXiu2 = new GongXiu(
                R.drawable.xuetang_qifu,
                "祈福祝愿",
                "人气",
                R.drawable.shape_xiaoyuandian,
                "希望爸爸妈妈身体健康，长命百岁，借什么也别借钱转贴：借什么也别借钱转贴：借什么也别借钱转贴：借什么也别借钱",
                R.drawable.shape_xiaoyuandian,
                "亲爱的父亲，老天爷一定会保佑您，不评价别人的生话，是一个人最基本的素养，转贴：借什么也别借钱",
                R.mipmap.pinglun,
                "98个话题",
                R.mipmap.canyu,
                "1764人参与",
                R.drawable.xuetang_gongxiu_imag1, 2
        );
        mGX.add(gongXiu2);


        RecyclerViewItemData itemData = new RecyclerViewItemData(mGX, RootType.GONGXIUITEM);
        viewItemData.add(itemData);
        RecyclerViewItemData itemDatas = new RecyclerViewItemData(viewItemData, RootType.RECYCLER_ITEM);
        mRecyclerItemDatas.add(itemDatas);

    }

    private void initQinShuan() {

        List<QinSuan> mqinSuan = new ArrayList<>();
        List<RecyclerViewItemData> viewItemData = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            QinSuan qinSuan = new QinSuan(
                    R.drawable.mingli_dashi_lifazhang,
                    "李法章",
                    "国学",
                    "专业",
                    R.mipmap.jieda,
                    "1452解答",
                    R.mipmap.pinglun,
                    "523评论",
                    R.mipmap.canyu,
                    "1392粉丝"
            );
            mqinSuan.add(qinSuan);
        }

        RecyclerViewItemData itemData = new RecyclerViewItemData(mqinSuan, RootType.QINSUANITEM);
        viewItemData.add(itemData);
        RecyclerViewItemData itemDatas = new RecyclerViewItemData(viewItemData, RootType.RECYCLER_ITEM);
        mRecyclerItemDatas.add(itemDatas);
    }


}
