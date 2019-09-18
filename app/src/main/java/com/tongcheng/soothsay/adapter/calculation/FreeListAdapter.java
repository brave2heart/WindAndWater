package com.tongcheng.soothsay.adapter.calculation;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.AbsBaseAdapter;

import java.util.List;

/**
 * Created by Steven on 16/12/21.
 */

public class FreeListAdapter extends AbsBaseAdapter<String>{

    public FreeListAdapter(Context context, List<String> datas, int itemLayoutId) {
        super(context, datas, itemLayoutId);
    }

    @Override
    public void bindData(ViewHolder holder, String s, int position) {
        TextView tv = (TextView) holder.getItemView();
        String temp [] = s.split(",");
        SpannableStringBuilder sb = new SpannableStringBuilder(temp[0]+temp[1]);
        ForegroundColorSpan fs = new ForegroundColorSpan(getContext().getResources().getColor(R.color.red_light));
        sb.setSpan(fs,0,temp[0].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(sb);
    }
}
