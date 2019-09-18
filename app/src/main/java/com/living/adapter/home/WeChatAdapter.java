package com.living.adapter.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.living.bean.home.JWeChatBean;
import com.living.glide.GlideApp;
import com.living.utils.CheckURLUtil;
import com.living.utils.GlideRoundTransformUtils;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.ui.activity.common.WebViewActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weihao on 2018/1/3.
 */

public class WeChatAdapter extends RecyclerView.Adapter<WeChatAdapter.ViewHolder> {
    private int channekid;
    private List<JWeChatBean.ResultBean.ListBean> listBeans = new ArrayList<>();
    private Context context;


    public WeChatAdapter(int channelid, List<JWeChatBean.ResultBean.ListBean> listBeans, Context context) {
        this.listBeans = listBeans;
        this.context = context;
        this.channekid = channelid;
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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        JWeChatBean.ResultBean.ListBean listBean = listBeans.get(position);
        holder.mWechat_title.setText(listBean.getTitle());
        holder.mWechat_source.setText(listBean.getWeixinname());
//        Glide.with(context)
//                .load(listBean.getPic())
//                .into(holder.mWechat_image);
        setImageview(context, listBean.getPic(), R.mipmap.home_top_image_loading1, R.mipmap.home_top_image_loading1, holder.mWechat_image);

        holder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = holder.getAdapterPosition();
                JWeChatBean.ResultBean.ListBean bean = listBeans.get(itemPosition);
                String url = bean.getUrl();
                        /* mUrl = intent.getStringExtra("web_url");
                   mWebTitle = intent.getStringExtra("web_title");
                   isShare = intent.getBooleanExtra("web_share",false);*/
                Intent intent = new Intent(v.getContext(), WebViewActivity.class);
                intent.putExtra("web_url", url);
                //HomeFragmentPagerAdapter.mTitles[channekid]
                intent.putExtra("web_title", bean.getTitle());
                intent.putExtra("web_share", true);
                intent.putExtra("web_image", bean.getPic());
                v.getContext().startActivity(intent);
//              Toast.makeText(context, url, Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void setImageview(Context context, String url, int place, int error, ImageView view) {
        GlideApp.with(context)
                .load(url)
                .placeholder(place) //设置占位图
                .error(error) //设置错误图片
//                .crossFade() //设置淡入淡出效果，默认300ms，可以传参
                .transition(new DrawableTransitionOptions().crossFade(300))
                //.dontAnimate() //不显示动画效果
//                .transform(new GlideRoundTransformUtils(context)) //将图片转成圆角
                .into(view);
    }


    @Override
    public int getItemCount() {
        return listBeans.size();
    }

}
