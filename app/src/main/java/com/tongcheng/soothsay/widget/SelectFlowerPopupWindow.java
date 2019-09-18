package com.tongcheng.soothsay.widget;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.PrayingSelectFlowerAdapter;
import com.tongcheng.soothsay.base.BasePopupWindow;
import com.tongcheng.soothsay.bean.calculation.FlowerBean;
import com.tongcheng.soothsay.utils.WindowUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:祈福台选择插花、摆果、烧香的弹出窗口
 * @author: linjinyan
 * @date: 2016-11-08
 * @time: 15:34
 */
public class SelectFlowerPopupWindow extends BasePopupWindow {


    private PrayingSelectFlowerAdapter adapter;
    private ListView listView;
    private List<FlowerBean> flowers;
    private TextView tvDescription;

    public SelectFlowerPopupWindow(Activity activity, int resId) {
        super(activity, resId);
    }

    @Override
    public void init(View layoutView) {
        listView = (ListView) layoutView.findViewById(R.id.lv_select);
        tvDescription = (TextView) layoutView.findViewById(R.id.description);
        initData();
        initListView();
    }

    private void initListView() {
        adapter = new PrayingSelectFlowerAdapter(getActivity(), flowers, R.layout.item_praying_select);
        listView.setAdapter(adapter);
        tvDescription.setText(R.string.txt_flower);
    }

    @Override
    public void initListener() {

    }

    public void setPopFlowerListener(PrayingSelectFlowerAdapter.onPopFlowerListener popFlowerListener) {
        adapter.setPopFlowerListener(popFlowerListener);
    }

    private void initData() {
        flowers = new ArrayList<>();
        FlowerBean flower1 = new FlowerBean();
        flower1.setFirst_img(R.drawable.lingji_qifutai_flower1);
        flower1.setFirst_name("name1");
        flower1.setFirst_price("price1");
        flower1.setSecond_img(R.drawable.lingji_qifutai_flower2);
        flower1.setSecond_name("name2");
        flower1.setSecond_price("price2");
        flower1.setThird_img(R.drawable.lingji_qifutai_flower3);
        flower1.setThird_name("name3");
        flower1.setThird_price("price3");
        flowers.add(flower1);
        FlowerBean flower2 = new FlowerBean();
        flower2.setFirst_img(R.drawable.lingji_qifutai_flower4);
        flower2.setFirst_name("name4");
        flower2.setFirst_price("price4");
        flower2.setSecond_img(R.drawable.lingji_qifutai_flower5);
        flower2.setSecond_name("name5");
        flower2.setSecond_price("price5");
        flower2.setThird_img(R.drawable.lingji_qifutai_flower6);
        flower2.setThird_name("name6");
        flower2.setThird_price("price6");
        flowers.add(flower2);
    }

}
