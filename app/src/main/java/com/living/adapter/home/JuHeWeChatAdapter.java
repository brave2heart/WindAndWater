package com.living.adapter.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.living.bean.home.WeChatBean;
import com.living.glide.GlideApp;
import com.living.utils.GlideRoundTransformUtils;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.ui.activity.common.WebViewActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weihao on 2018/1/5.
 */

public class JuHeWeChatAdapter extends RecyclerView.Adapter<JuHeWeChatAdapter.ViewHolder> {

    private final Context context;

    private List<WeChatBean.ResultBean.ListBean> listBeans = new ArrayList<>();

    public JuHeWeChatAdapter(Context context, List<WeChatBean.ResultBean.ListBean> listBeans) {
        this.context = context;
        this.listBeans = listBeans;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mWechat_title;
        private final TextView mWechat_source;
        private final ImageView mWechat_image;
        private final View mItemView;

        public ViewHolder(View itemView) {
            super(itemView);

            mItemView = itemView;
            mWechat_title = itemView.findViewById(R.id.home_wechat_title);
            mWechat_source = itemView.findViewById(R.id.home_wechat_source);
            mWechat_image = itemView.findViewById(R.id.home_wechat_image);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item_wechat, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final WeChatBean.ResultBean.ListBean listBean = listBeans.get(position);
        holder.mWechat_title.setText(listBean.getTitle());
        holder.mWechat_source.setText(listBean.getSource());
//        Glide.with(context)
//                .load(listBean.getFirstImg())
//                .into(holder.mWechat_image);

        setImageview(context, listBean.getFirstImg(), R.mipmap.home_top_image_loading1, R.mipmap.home_top_image_loading1, holder.mWechat_image);


        holder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = listBean.getUrl();
                Intent intent = new Intent(v.getContext(), WebViewActivity.class);
                intent.putExtra("web_url", url);
                intent.putExtra("web_title", listBean.getTitle());
                intent.putExtra("web_share", true);
                intent.putExtra("web_image", listBean.getFirstImg());
                v.getContext().startActivity(intent);
            }
        });
    }

    public void setImageview(Context context, String url, int place, int error, ImageView view) {
        GlideApp.with(context)
                .load(url)
                .placeholder(place) //设置占位图
                .error(error) //设置错误图片
//                .crossFade() //设置淡入淡出效果，默认300ms，可以传参
                //.dontAnimate() //不显示动画效果
                .transition(new DrawableTransitionOptions().crossFade(300))
//                .transform(new GlideRoundTransformUtils(context)) //将图片转成圆角
                .into(view);

    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }


}
