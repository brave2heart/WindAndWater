package com.tongcheng.soothsay.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.AbsBaseAdapter;
import com.tongcheng.soothsay.utils.WindowUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lijuan
 * @description: 自定义popupwindow
 * @date: 2016-10-27
 * @time: 09:15
 */
public class PullDownPopupwindow extends PopupWindow {
    private Context mContext;
    private String[] titles;
    private int[] imgs;
    private List<String> list;
    //下拉框的宽度和高度。
    private int windowWidth;
    private int windowHeight;
    private PopupWindowItemClickListener popupWindowItemClickListener;

    //默认背景颜色为白色
    private int setBackGroundColor = Color.parseColor("#ffffff");

    //设置监听事件
    public void setPopupWindowItemClickListener(PopupWindowItemClickListener popupWindowItemClickListener) {
        this.popupWindowItemClickListener = popupWindowItemClickListener;
    }

    //设置窗体的宽度
    public void setWindowWidth(int windowWidth) {
        this.windowWidth = windowWidth;
    }

    //设置窗体的高度
    public void setWindowHeight(int windowHeight) {
        this.windowHeight = windowHeight;
    }

    //设置背景颜色
    public void setSetBackGroundColor(int setBackGroundColor) {
        this.setBackGroundColor = setBackGroundColor;
    }

    public PullDownPopupwindow(Context context, String[] titles) {
        this(context, titles, null);
    }

    public PullDownPopupwindow(Context context, String[] titles, int[] imgs) {
        super(context);
        this.mContext = context;
        this.titles = titles;
        this.imgs = imgs;

        //初始化下拉框的宽度和高度。
        windowWidth = WindowUtil.dip2px(mContext, 110);
        windowHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
    }

    /**
     * 初始化视图
     *
     * @param mContext
     */
    public void initView(Context mContext) {
        ListView mListView = (ListView) setPopupWindowLayout();
        list = new ArrayList<String>();
        for (int i = 0; i < titles.length; i++) {
            list.add(String.valueOf(i));
        }

        AbsBaseAdapter<String> adapter = new AbsBaseAdapter<String>(mContext, list, R.layout.popupwindow) {
            @Override
            public void bindData(AbsBaseAdapter.ViewHolder holder, String s, int position) {
                ((TextView) holder.getView(R.id.txt_title)).setText(titles[position]);
                ImageView img = (ImageView) holder.getView(R.id.img);
                if (imgs == null) {
                    img.setVisibility(View.GONE);
                } else {
                    img.setBackgroundResource(imgs[position]);
                }
            }
        };

        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new ItemClickListener());

        //设置View视图
        setContentView(mListView);

        //设置弹窗的宽度和高度
        setWidth(windowWidth);
        setHeight(windowHeight);
        //设置弹出窗体可点击
        setFocusable(true);
        setOutsideTouchable(true);
        //设置透明背景
        ColorDrawable dw = new ColorDrawable(0000000000);
        setBackgroundDrawable(dw);
    }

    /**
     * 获取适配器视图
     *
     * @return
     */
    public View getAdapterView() {
        //设置一个线性布局容器
        RelativeLayout relaContainer = new RelativeLayout(mContext);
        ViewGroup.LayoutParams vl = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        relaContainer.setLayoutParams(vl);
        //设置图片
        ImageView img = new ImageView(mContext);
        RelativeLayout.LayoutParams lpImg = new RelativeLayout.LayoutParams(WindowUtil.dip2px(mContext, 50), WindowUtil.dip2px(mContext, 50));
        img.setLayoutParams(lpImg);
        img.setId(Integer.valueOf(1));
        img.setImageResource(R.mipmap.ic_launcher);
        relaContainer.addView(img);
        //设置文本
        TextView textView = new TextView(mContext);
        RelativeLayout.LayoutParams lpTxt = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        textView.setText("测试");
        textView.setId(Integer.valueOf(2));
        textView.setLayoutParams(lpTxt);
        relaContainer.addView(textView);

        return relaContainer;
    }

    /**
     * 设置布局视图
     *
     * @return
     */
    public View setPopupWindowLayout() {
        //首页设置一个布局视图
        ListView mListView = new ListView(mContext);
        //隐藏滚动条
        mListView.setVerticalScrollBarEnabled(false);
        return mListView;
    }

    /**
     * 点击事件
     */
    public class ItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            if (popupWindowItemClickListener != null)
                popupWindowItemClickListener.onItemClcikListener(adapterView, view, i, l);
            //取消此下拉框
            dismiss();
        }
    }

    /**
     * 用户自定点击事件回调接口
     */
    public interface PopupWindowItemClickListener {
        void onItemClcikListener(AdapterView<?> adapterView, View view, int i, long l);
    }

    /**
     * 显示视图
     *
     * @param parent
     * @param x
     * @param y
     */
    public void showPopupWindow(View parent, int x, int y) {
        if (!isShowing()) {
            showAsDropDown(parent, x, y);
        } else {
            dismiss();
        }
    }
}
