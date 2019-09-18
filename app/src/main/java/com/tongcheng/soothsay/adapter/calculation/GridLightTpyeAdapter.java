package com.tongcheng.soothsay.adapter.calculation;

import android.content.Context;
import android.widget.ImageView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.AbsBaseAdapter;
import com.tongcheng.soothsay.bean.calculation.LightTypeBean;
import com.tongcheng.soothsay.bean.calculation.WishingType;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ALing on 2016/12/5 0005.
 * 许愿点灯的适配器
 */

public class GridLightTpyeAdapter extends AbsBaseAdapter<WishingType> {

    private Context mContext;
    private List<LightTypeBean> list = new ArrayList<>();
    private int[] lightArr;
    private int[] selectLightArr;
    private ImageView iv_lightType;
    private int clickStatus = 0;

    public GridLightTpyeAdapter(Context context, List datas,int[] lightArr ,int[] selectLightArr,int itemLayoutId) {
        super(context, datas, itemLayoutId);
        this.mContext = context;
        this.lightArr = lightArr;
        this.selectLightArr = selectLightArr;
    }


    @Override
    public void bindData(AbsBaseAdapter.ViewHolder holder, WishingType o, final int position) {
        iv_lightType = (ImageView) holder.getView(R.id.iv_light_type);

        if (clickStatus == position){
            iv_lightType.setImageResource(selectLightArr[position]);
        }else {
            iv_lightType.setImageResource(lightArr[position]);
        }

    }

    public void setSelect(int position){
        clickStatus = position;
    }

}
