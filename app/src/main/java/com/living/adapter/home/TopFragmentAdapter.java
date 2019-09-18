package com.living.adapter.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.living.bean.home.TopBean;
import com.living.glide.GlideApp;
import com.living.utils.GlideRoundTransformUtils;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.ui.activity.common.WebViewActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weihao on 2017/12/18.
 */

public class TopFragmentAdapter extends RecyclerView.Adapter<TopFragmentAdapter.ViewHolder> {


    private final Context context;
    private List<TopBean.ResultBean.DataBean> data = new ArrayList<>();
    private TopBean.ResultBean.DataBean mDataBean;
    private ViewHolder mHolder;


    public TopFragmentAdapter(Context context, List<TopBean.ResultBean.DataBean> dates) {
        this.context = context;
        data = dates;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private final TextView mTop_title;
        private final TextView mTop_name;
        private final ImageView mTop_image1;
        private final ImageView mTop_image2;
        private final ImageView mTop_image3;
        private final TextView mTop_time;
        private final View mItemView;
        private final LinearLayout mLl_3image;
        private final LinearLayout mLl_1image;
        private final ImageView mRight_iamger;

        public ViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;

            mTop_title = itemView.findViewById(R.id.home_top_title);
            mTop_time = itemView.findViewById(R.id.home_toutiao_time);
            mTop_name = itemView.findViewById(R.id.home_top_author_name);
            mTop_image1 = itemView.findViewById(R.id.home_top_imageview1);
            mTop_image2 = itemView.findViewById(R.id.home_top_imageview2);
            mTop_image3 = itemView.findViewById(R.id.home_top_imageview3);

            mLl_3image = itemView.findViewById(R.id.home_toutiao_ll); //3张图片的linearLayout
            mLl_1image = itemView.findViewById(R.id.home_toutiao_ll_one_image); //1张图片时

            mRight_iamger = itemView.findViewById(R.id.home_news_right_image);  //右边1张图片


        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_home_toutiao_item, parent, false);
        mHolder = new ViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        mDataBean = data.get(position);
        holder.mTop_title.setText(mDataBean.getTitle());
        holder.mTop_name.setText(mDataBean.getAuthor_name());
        if (mDataBean.getThumbnail_pic_s() != null) { // 第1张
            setImageview(context, mDataBean.getThumbnail_pic_s(), R.mipmap.home_top_image_loading1, R.mipmap.home_top_image_loading1, holder.mTop_image1);
        } else {
            holder.mTop_image1.setVisibility(View.INVISIBLE);
        }
        if (mDataBean.getThumbnail_pic_s02() != null) { //第2张
            setImageview(context, mDataBean.getThumbnail_pic_s02(), R.mipmap.home_top_image_loading1, R.mipmap.home_top_image_loading1, holder.mTop_image2);
        } else {
            if (mDataBean.getThumbnail_pic_s03() != null) {
                //如果第2张为空，把3赋值到2，并隐藏3
                setImageview(context, mDataBean.getThumbnail_pic_s03(), R.mipmap.home_top_image_loading1, R.mipmap.home_top_image_loading1, holder.mTop_image2);
                holder.mTop_image3.setVisibility(View.INVISIBLE);
            } else {
                holder.mTop_image2.setVisibility(View.INVISIBLE);
            }
        }
        if (mDataBean.getThumbnail_pic_s03() != null) {  //第3张
            setImageview(context, mDataBean.getThumbnail_pic_s03(), R.mipmap.home_top_image_loading1, R.mipmap.home_top_image_loading1, holder.mTop_image3);
        } else {
            holder.mTop_image3.setVisibility(View.INVISIBLE);
        }

        if (mDataBean.getThumbnail_pic_s() != null && mDataBean.getThumbnail_pic_s02() == null & mDataBean.getThumbnail_pic_s03() == null) {
            holder.mLl_3image.setVisibility(View.GONE);
            holder.mLl_1image.setVisibility(View.VISIBLE);
            setImageview(context, mDataBean.getThumbnail_pic_s(), R.mipmap.home_top_image_loading1, R.mipmap.home_top_image_loading1, holder.mRight_iamger);

        }

        holder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = holder.getAdapterPosition();
                TopBean.ResultBean.DataBean dataBean = data.get(itemPosition);
                String url = dataBean.getUrl();

                /* mUrl = intent.getStringExtra("web_url");
                   mWebTitle = intent.getStringExtra("web_title");
                   isShare = intent.getBooleanExtra("web_share",false);*/
                Intent intent = new Intent(v.getContext(), WebViewActivity.class);
                intent.putExtra("web_url", url);
                intent.putExtra("web_title", "新闻头条");
                intent.putExtra("web_share", true);
                v.getContext().startActivity(intent);
//                Toast.makeText(context, url, Toast.LENGTH_SHORT).show();

            }
        });


        //3个小时前
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
        return data.size();
    }


}
