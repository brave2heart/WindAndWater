package com.living.constant.root;

/**
 * Created by weihao on 2018/1/9.
 */


public class RootType {
    //Fragment类型
    public static final int MINGLI = 0;  //命理
    public static final int CLASSROOM = 1;  //学堂
    public static final int SHOP = 2;  //商城
    public static final int ME = 3;  //我的

    //命理布局数据type
    public static final int BANNER = 0;  //轮播图类型
    public static final int RECYCLER_ITEM = 1;  // item的RecyclerView
    public static final int COLUMN = 2;  //栏目
    public static final int FANGIMAGE = 3;  //放生池图片
    public static final int DASHI = 4;  //大师推荐数据bean
    public static final int ZHOUYI = 5;  //大师推荐数据bean




    //子条目,head和热门测算数据的type
    public static final int GRID_FOUR = 0;  // 网格布局4列
    public static final int GRID_TWO = 1;   // 网格布局2列
    public static final int ZHUANLANITEM = 2;  //学堂专栏推荐布局子条目类型type
    public static final int GONGXIUITEM = 3;  //学堂共修圈子布局子条目类型type
    public static final int QINSUANITEM = 4;  //学堂大师亲算布局子条目类型type
    public static final int MVITEM = 5;  //MV数据bean
    public static final int SHOPITEM = 6;  //MV数据bean
    public static final int COLUMN_RECYCLER = 7;  //大师推荐数据bean



    public static String[] rootLayoutId = new String[]{"banner", "headitem", "column", "fangshengimage", "dashi","zhouyi"};
    public static String[] rootItemLayoutId = new String[]{"headitem", "remenitem", "zhuanlan","gongxiu","qinsuan","mvitem","shop","column_recycler"};


}
