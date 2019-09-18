package com.tongcheng.soothsay.widget;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.PrayingChooseFruitAdapter;
import com.tongcheng.soothsay.base.BasePopupWindow;
import com.tongcheng.soothsay.bean.calculation.FruitBean;
import com.tongcheng.soothsay.utils.WindowUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:祈福台选择摆果的弹出窗口
 * @author: linjinyan
 * @date: 2016-11-09
 * @time: 11:34
 */
public class SelectFruitPopupWindow extends BasePopupWindow {


    private PrayingChooseFruitAdapter adapter;
    private ListView listView;
    private List<FruitBean> fruits;
    private TextView tvDescription;

    public SelectFruitPopupWindow(Activity activity, int resId) {
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
        adapter = new PrayingChooseFruitAdapter(getActivity(), fruits, R.layout.item_praying_select);
        listView.setAdapter(adapter);
        tvDescription.setText(R.string.txt_fruit);
    }

    @Override
    public void initListener() {

    }

    public void setPopFruitListener(PrayingChooseFruitAdapter.onPopFruitListener popListener) {
        adapter.setPopFruitListener(popListener);
    }

    private void initData() {
        fruits = new ArrayList<>();
        FruitBean fruit1 = new FruitBean();
        fruit1.setFirst_img(R.drawable.gp_49);
        fruit1.setFirst_name("name1");
        fruit1.setFirst_price("price1");
        fruit1.setSecond_img(R.drawable.gp_51);
        fruit1.setSecond_name("name2");
        fruit1.setSecond_price("price2");
        fruit1.setThird_img(R.drawable.gp_53);
        fruit1.setThird_name("name3");
        fruit1.setThird_price("price3");
        fruits.add(fruit1);
        FruitBean fruit2 = new FruitBean();
        fruit2.setFirst_img(R.drawable.gp_55);
        fruit2.setFirst_name("name4");
        fruit2.setFirst_price("price4");
        fruit2.setSecond_img(R.drawable.gp_57);
        fruit2.setSecond_name("name5");
        fruit2.setSecond_price("price5");
        fruit2.setThird_img(R.drawable.gp_59);
        fruit2.setThird_name("name6");
        fruit2.setThird_price("price6");
        fruits.add(fruit2);
    }
}
