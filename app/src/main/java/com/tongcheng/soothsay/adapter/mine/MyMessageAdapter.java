package com.tongcheng.soothsay.adapter.mine;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.AbsBaseAdapter;
import com.tongcheng.soothsay.bean.mine.MyMessage;
import com.tongcheng.soothsay.utils.DateUtil;

import java.util.List;

public class MyMessageAdapter extends AbsBaseAdapter<MyMessage.MyMyssageListBean> {


        public MyMessageAdapter(Context context, List<MyMessage.MyMyssageListBean> datas, int itemLayoutId) {
                super(context, datas, itemLayoutId);
        }

        @Override
        public void bindData(ViewHolder holder, MyMessage.MyMyssageListBean myMessage, int position) {
                holder.setTag(position);
                TextView tv_title = holder.getViewV2(R.id.tv_title);
                TextView tv_time = holder.getViewV2(R.id.tv_time);
                TextView tv_is_answer = holder.getViewV2(R.id.tv_is_answer);

                tv_title.setText(myMessage.getTitle());

//                String formatTime = DateUtil.stampToDate(myMessage.getCreatetime());

                tv_time.setText(myMessage.getReplytime());
                String isAnswer = myMessage.getIsAnswer();
                if ("0".equals(isAnswer)){
                        tv_is_answer.setText("未回答");
                }else {
                        tv_is_answer.setText("已回答");
                }
        }
}