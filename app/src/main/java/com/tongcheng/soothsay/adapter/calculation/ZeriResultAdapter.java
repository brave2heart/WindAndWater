package com.tongcheng.soothsay.adapter.calculation;

import android.content.Context;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.AbsBaseAdapter;
import com.tongcheng.soothsay.bean.huangli.AlmanacBean;
import com.tongcheng.soothsay.utils.DateUtil;

import java.util.List;

/**
 * Created by bozhihuatong on 2016/12/23.
 * 择日适配器
 */

public class ZeriResultAdapter extends AbsBaseAdapter<AlmanacBean.ResultBean> {


    public ZeriResultAdapter(Context context, List<AlmanacBean.ResultBean> datas, int itemLayoutId) {
        super(context, datas, itemLayoutId);
    }

    @Override
    public void bindData(ViewHolder holder, AlmanacBean.ResultBean resultBean, int position) {

        /**
         * id : 2436
         * yangli : 2016-11-11
         * yinli : 丙申(猴)年十月十二
         * wuxing : 山下火 开执位
         * chongsha : 冲兔(辛卯)煞东
         * baiji : 丁不剃头头必生疮 酉不宴客醉坐颠狂
         * jishen : 母仓 时阴 生气 圣心 除神 鸣犬
         * yi : 嫁娶 冠笄 祭祀 祈福 求嗣 斋醮  开光 出行 解除 动土 开市 交易 立券 挂匾 拆卸 破土
         * xiongshen : 灾煞 天火 五离 朱雀
         * ji : 伐木 上梁 修造 入殓 理发 会亲友 入宅 安门 安葬 作灶
         */

        String week = DateUtil.getweekdayBystr(resultBean.getYangli() + " ");
        TextView textView=holder.getViewV2(R.id.zeri_yiji_time_result_textView_item);
        textView.setText(resultBean.getYangli()+" "+resultBean.getYinli()+" "+week);
        textView.setTag(resultBean);
    }
}
