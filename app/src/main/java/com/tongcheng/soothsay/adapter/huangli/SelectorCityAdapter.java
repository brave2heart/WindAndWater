package com.tongcheng.soothsay.adapter.huangli;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseRecyclerAdapter;
import com.tongcheng.soothsay.bean.dao.CityBean;
import com.tongcheng.soothsay.bean.dao.CityBeanDao;
import com.tongcheng.soothsay.helper.GreenDaoHelper;
import com.tongcheng.soothsay.utils.DialogUtil;
import com.wevey.selector.dialog.DialogOnClickListener;
import com.wevey.selector.dialog.MDAlertDialog;

import java.util.List;

/**
 * Created by Steven on 16/11/22.
 */

public class SelectorCityAdapter extends BaseRecyclerAdapter<CityBean> {

    private MDAlertDialog dialog;

    public SelectorCityAdapter(Context context, List<CityBean> list, int itemLayoutId) {
        super(context, list, itemLayoutId);
    }

    @Override
    public void bindData(final ViewHolder holder, int position, CityBean cityBean) {
        ImageView img = (ImageView) holder.getView(R.id.img_select_city_add);
        TextView tv = (TextView) holder.getView(R.id.tv_select_city);
        if(TextUtils.isEmpty(cityBean.getCity())){
            img.setVisibility(View.VISIBLE);
            tv.setVisibility(View.INVISIBLE);

        }else{
            tv.setText(cityBean.getCity());
            tv.setVisibility(View.VISIBLE);
            img.setVisibility(View.INVISIBLE);

        }

        holder.getItemView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
               dialog = DialogUtil.createDialog(getContext(), "", "删除城市", "", "确定", new DialogOnClickListener() {
                    @Override
                    public void clickLeftButton(View view) {
                        dialog.dismiss();
                    }

                    @Override
                    public void clickRightButton(View view) {
                        int pos = (int) v.getTag();
                        CityBeanDao dao = GreenDaoHelper.getInstance().getSeeion().getCityBeanDao();
                        dao.delete(getList().get(pos));
                        removeData(holder,pos);
                        dialog.dismiss();
                    }
                });
                dialog.show();
                return true;
            }
        });
    }
}
