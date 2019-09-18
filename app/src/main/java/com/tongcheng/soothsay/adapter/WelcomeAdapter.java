package com.tongcheng.soothsay.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.tongcheng.soothsay.base.AbBasePagerAdapter;

import java.util.List;


public class WelcomeAdapter extends AbBasePagerAdapter<Integer> {
	
	
	
	public WelcomeAdapter(Context context,List<View> views, List<Integer> datas) {
		super(context,views, datas);
	}
	
	@Override
	public void bindData(View view, Integer t) {
		ImageView img=(ImageView) view;
		img.setImageResource(t);
	}
}
