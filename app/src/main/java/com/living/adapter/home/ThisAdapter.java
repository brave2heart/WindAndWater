package com.living.adapter.home;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.living.bean.home.ConstellationBean;
import com.living.constant.home.HomeAPI;
import com.living.constant.home.HomeType;

import com.living.presenter.activity.Te;
import com.living.ui.fragment.home.FourForcunt.ThisFragment;
import com.living.utils.ConstellationUtil;
import com.tongcheng.soothsay.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weihao on 2018/1/6.
 */

public class ThisAdapter extends RecyclerView.Adapter<ThisAdapter.ViewHolder> {
    private final ThisFragment thisFragment;
    private String constellationName;
    private List<ConstellationBean.ConstellationIcon> icons = new ArrayList<>();
    private int type;
    private AlertDialog mDialog;
    private ViewHolder mHolder;
    private List<Te> mConsBeanList = new ArrayList<>();


    public ThisAdapter(List<Te> mConsBeanList, int type, String constellationName, List<ConstellationBean.ConstellationIcon> icons, ThisFragment thisFragment) {
        this.thisFragment = thisFragment;
        this.mConsBeanList = mConsBeanList;
        this.type = type;
        this.constellationName = constellationName;
        this.icons = icons;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mConstellation_name;
        private final ImageView mConstellation_image;
        private final TextView mThis_change;
        private final TextView mConstellation_date;
        private final TextView mConstellation_constype;
        private RatingBar mRb_all;
        private RatingBar mRb_health;
        private RatingBar mRb_work;
        private RatingBar mRb_money;
        private RatingBar mRb_love;
        private TextView mTv_summary;
        private TextView mWeek_health;
        private TextView mWeek_work;
        private TextView mWeek_money;
        private TextView mWeek_love;
        private TextView mMonth_all;
        private TextView mMonth_health;
        private TextView mMonth_work;
        private TextView mMonth_love;
        private TextView mYear_stone;
        private TextView mYear_mima;
        private TextView mYear_work;
        private TextView mYear_money;
        private TextView mYear_love;
        private TextView mYear_mima_head;


        public ViewHolder(View itemView) {
            super(itemView);
            mConstellation_name = itemView.findViewById(R.id.homeforcunt_constellation_name);
            mConstellation_image = itemView.findViewById(R.id.homeforcunt_constellation_image);
            mConstellation_date = itemView.findViewById(R.id.homeforcunt_constellation_date);
            mConstellation_constype = itemView.findViewById(R.id.homeforcunt_constellation_constype);
            mThis_change = itemView.findViewById(R.id.homeforcunt_this_change);
            if (type == HomeType.TOMORROW) {
                /**
                 * date : 20180109
                 * name : 双鱼座
                 * datetime : 2018年01月09日
                 * all : 40%
                 * color : 蓝色
                 * health : 70%
                 * love : 40%
                 * money : 40%
                 * number : 6
                 * QFriend : 巨蟹座
                 * summary : 公众事务增多，朋友间聚会.
                 * work : 40%
                 * resultcode : 200
                 * error_code : 0
                 */
                mRb_all = itemView.findViewById(R.id.rb_homeforcunt_all);
                mRb_health = itemView.findViewById(R.id.rb_homeforcunt_health);
                mRb_work = itemView.findViewById(R.id.rb_homeforcunt_work);
                mRb_money = itemView.findViewById(R.id.rb_homeforcunt_money);
                mRb_love = itemView.findViewById(R.id.rb_homeforcunt_love);
                mTv_summary = itemView.findViewById(R.id.homeforcunt_summary);


            } else if (type == HomeType.WEEK) {
                /**
                 * name : 双鱼座
                 * weekth : 2
                 * date : 2017年09月10日-2017年09月16日
                 * health : 健康：注意上呼吸道的疾病。 作者：星言，一片雪
                 * job : 工作：有来自领导的压力，工作很忙碌。 求职:求职的关键在于，带着目的去寻找自己想要的工作。
                 * love : 恋情：一时冲动，会有分手的打算。
                 * money : 财运：财运不错，有意外之财。
                 * work : 工作：有来自领导的压力，工作很忙碌。 求职:求职的关键在于，带着目的去寻找自己想要的工作。
                 * resultcode : 200
                 * error_code : 0
                 */
                mWeek_health = itemView.findViewById(R.id.homeforcunt_week_health_content);
                mWeek_work = itemView.findViewById(R.id.homeforcunt_week_work_content);
                mWeek_money = itemView.findViewById(R.id.homeforcunt_week_money_content);
                mWeek_love = itemView.findViewById(R.id.homeforcunt_week_love_content);
            } else if (type == HomeType.MONTH) {
                /**
                 * date : 2018年01月
                 * name : 摩羯座
                 * month : 1
                 * all : 1月份的摩羯座感觉事情都不是为自己而做，总有客观因素不得不让你去重视一些问题。
                 * <p>
                 * health : 控制好自己的脾气，不要太过暴躁。
                 * <p>
                 * love : 身边桃花其实是很多的，但是摩羯座木讷的性格不一定发现得了，有伴的摩羯座感情稍微有点不稳定，注意不要去太深挖对方的内心深处，才能更好的过下去。
                 * <p>
                 * money : 偏财运较好额，但是花得也多，所以还是得注意一下，不要一不小心花过头了。
                 * <p>
                 * work : 本月摩羯座的工作运还是不错的，如果能对自己的工作更深入一点，会有很不错的回报。
                 * <p>
                 * happyMagic :
                 * resultcode : 200
                 * error_code : 0
                 */
                mMonth_all = itemView.findViewById(R.id.homeforcunt_month_all_content);
                mMonth_health = itemView.findViewById(R.id.homeforcunt_month_health_content);
                mMonth_work = itemView.findViewById(R.id.homeforcunt_month_work_content);
                mMonth_love = itemView.findViewById(R.id.homeforcunt_month_love_content);


            } else if (type == HomeType.YEAR) {

                /**
                 * name : 狮子座
                 * date : 2018年
                 * year : 2018
                 * mima : {"info":"整体较忙绿，调整步伐","text":["狮子座在2018年可能会出现一些被动的变化，需要你去适应，做好自我调节。今年的安全感相对会有所下降，自信心也随之减弱，对于很多事情都存在不确定，显得忐忑不安，有一段时间会显得很迷茫，不知该从何做起。只要调整好自己，才能继续安好地度过一年。会有不少的麻烦琐碎事，人际上也有些不愉快，但是还是能够按照自己的意愿做事，这是最值得开心的。要保持平和谦虚的姿态，减少纠纷，压力变少，自然感到轻松了。"]}
                 * career : ["木星来到狮子座的家宅宫，很多狮子座在住宅房产方面能够获得一些好运。尤其利于从事房产中介、建筑设计、土地规划等方面的狮子座。另外某些狮子座也能够通过房屋买卖租赁获得一些实际利益。夏季是狮子座状态最好，才华尽显的时期。而立秋之后，职场上的进取心弱，工作生活压力大，合作关系也容易出现矛盾。要注意身体健康。家庭关系给到你一些支持，家宅方面的舒畅度提升，不过10月-11月期间，有些房产家务方面的旧事，需要谨慎应对。进入冬季，狮子座的心态愉悦开朗了很多。感情工作相对都顺利些。涉及到借贷，保险、税务等问题需要积极应对。"]
                 * love : ["很多狮子座的情感关系，在2017年遇到冰点，而进入2018年，尤其入夏季后，会出现比较戏剧的状况。有的人会积极投入到某人怀抱，或者被催婚相亲。入秋后，又容易发生彼此的不理解甚至相互斗争。这个期间，尤其是婚姻中的狮子座，很容易与伴侣纠缠一些对错，甚至对抗。很奇怪这个期间狮子座变得时而超自信，时而超自卑，状态不稳定，找茬的节奏。另外，这个期间，狮子座在工作中压力大，身体方面也容易出现病痛，所以，自身及时调节，应该是可以度过危机。11月之后，狮子座的桃花运旺起来，感情方面也能体会到更多被关注。精气神逐渐恢复，感情运势，越来越好。"]
                 * health : ["学业方面，狮子座2018年度的状态，比2017年要好。学习状态积极，只是压力大，需要付出加倍努力，才能获得好的成绩。尤其是10月-11月期间，某些狮子座可能会因为家庭原因，导致学业方面不顺利。健康方面，狮子座在2018年要特别留意，背部，脊柱，牙齿，左耳，心脏，心脑血管，牙齿，皮肤等方面病痛出现。"]
                 * finance : ["首先要提醒狮子座朋友，过去一年当中遭遇到的金钱压力，在本年度有所缓解，并且有可能通过家人的支持，改善生活状态，或者通过房产租赁买卖获得一些收益。或者花钱贷款投资房产等。对于狮子座而言，每个水逆期间都不利于你的财运，具体时间提示一下：03/23-04/15，07/26-08/19，11/17-12/07。这个期间避免签订与金钱相关的合约合同，避免大额消费。另外，到了年底，你的投资意愿更强，可能会发生冒险贷款，集资借贷等行为。"]
                 * luckeyStone : 美国碧玉
                 * future :
                 * resultcode : 200
                 * error_code : 0
                 */
                mYear_stone = itemView.findViewById(R.id.homeforcunt_stone_content);
                mYear_mima_head = itemView.findViewById(R.id.homeforcunt_mima_content_head);
                mYear_mima = itemView.findViewById(R.id.homeforcunt_mima_content);
                mYear_work = itemView.findViewById(R.id.homeforcunt_year_work_content);
                mYear_money = itemView.findViewById(R.id.homeforcunt_year_money_content);
                mYear_love = itemView.findViewById(R.id.homeforcunt_year_love_content);

            }
        }
    }

    @Override
    public ThisAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        String lay = "fragment_homeforcunt_";
        int layoutId = parent.getContext().getApplicationContext().getResources().getIdentifier(lay + (HomeAPI.ConstellationType[type]), "layout", parent.getContext().getApplicationContext().getPackageName());
        ViewHolder holder = getHolder(parent, layoutId);
        return holder;
    }

    private ViewHolder getHolder(final ViewGroup parent, int layout) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        mHolder = new ViewHolder(view);
        mHolder.mConstellation_name.setText(constellationName);
        mHolder.mConstellation_image.setImageResource(ConstellationUtil.getConstellationIcon(constellationName));
        mHolder.mThis_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog = new AlertDialog
                        .Builder(parent.getContext())
                        .setView(getChoiceView(parent))
                        .create();

                mDialog.show();
            }
        });
        return mHolder;
    }

    private View getChoiceView(ViewGroup parent) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment2, null, false);
        RecyclerView today_recycler = view.findViewById(R.id.homeforcunt_change_recycler);
        today_recycler.setLayoutManager(new GridLayoutManager(parent.getContext(), 4));
        today_recycler.setAdapter(new ThisChangeAdapder());
        return view;

    }

    @Override
    public void onBindViewHolder(ThisAdapter.ViewHolder holder, int position) {
        if (type == HomeType.TOMORROW) {
            Te t = mConsBeanList.get(position);
            ConstellationBean.TomorrowConstellationBean bean = (ConstellationBean.TomorrowConstellationBean) t;
            mHolder.mRb_all.setRating(ConstellationUtil.getConsRating(bean.getAll()));
            mHolder.mRb_health.setRating(ConstellationUtil.getConsRating(bean.getHealth()));
            mHolder.mRb_work.setRating(ConstellationUtil.getConsRating(bean.getWork()));
            mHolder.mRb_money.setRating(ConstellationUtil.getConsRating(bean.getMoney()));
            mHolder.mRb_love.setRating(ConstellationUtil.getConsRating(bean.getLove()));
            mHolder.mTv_summary.setText(bean.getSummary());
        } else if (type == HomeType.WEEK) {
            Te t = mConsBeanList.get(position);
            ConstellationBean.WeekConstellationBean bean = (ConstellationBean.WeekConstellationBean) t;

            String health = bean.getHealth();
            if (health == null) {
                mHolder.mWeek_health.setText("");
            } else {
                String sHealth = health.replace("健康：", "");
                mHolder.mWeek_health.setText(sHealth);
            }

            String work = bean.getWork();
            if (work == null) {
                mHolder.mWeek_work.setText("");
            } else {
                String sWork = work.replace("工作：", "");
                mHolder.mWeek_work.setText(sWork);
            }

            String money = bean.getMoney();
            if (money == null) {
                mHolder.mWeek_money.setText("");
            } else {
                String sMoney = money.replace("财运：", "");
                mHolder.mWeek_money.setText(sMoney);
            }
            String love = bean.getLove();
            if (love == null) {
                mHolder.mWeek_love.setText("");
            } else {
                String sHealth = love.replace("恋爱：", "");
                mHolder.mWeek_love.setText(sHealth);
            }
//            String work = bean.getWork();
//            String money = bean.getMoney();
//            String love = bean.getLove();
//            String sHealth = health.replace("健康：", "");
//            String sWork = work.replace("工作：", "");
//            String sMoney = money.replace("财运：", "");
//            String sLove = love.replace("恋爱：", "");
//
//            mHolder.mWeek_health.setText(sHealth);
//            mHolder.mWeek_work.setText(sWork);
//            mHolder.mWeek_money.setText(sMoney);
//            mHolder.mWeek_love.setText(sLove);

        } else if (type == HomeType.MONTH) {
            Te t = mConsBeanList.get(position);
            ConstellationBean.MonthConstellationBean bean = (ConstellationBean.MonthConstellationBean) t;
            mHolder.mMonth_all.setText(bean.getAll());
            mHolder.mMonth_health.setText(bean.getHealth());
            mHolder.mMonth_work.setText(bean.getWork());
            mHolder.mMonth_love.setText(bean.getLove());

        } else if (type == HomeType.YEAR) {
            Te t = mConsBeanList.get(position);
            ConstellationBean.YearConstellationBean bean = (ConstellationBean.YearConstellationBean) t;
            mHolder.mYear_stone.setText(bean.getLuckeyStone());
            mHolder.mYear_mima_head.setText("[" + bean.getMima().getInfo() + "]");
            mHolder.mYear_mima.setText(bean.getMima().getText().get(0));
            mHolder.mYear_work.setText(bean.getCareer().get(0));
            mHolder.mYear_money.setText(bean.getFinance().get(0));
            mHolder.mYear_love.setText(bean.getLove().get(0));

        }

    }


    @Override
    public int getItemCount() {
        return mConsBeanList.size();
    }


    class ThisChangeAdapder extends RecyclerView.Adapter<ThisChangeAdapder.ViewHolder> {


        public class ViewHolder extends RecyclerView.ViewHolder {

            private final ImageView mIcon;
            private final TextView mName;
            private final View mItemView;

            public ViewHolder(View itemView) {
                super(itemView);
                mItemView = itemView;
                mIcon = itemView.findViewById(R.id.homeforcunt_today_icon);
                mName = itemView.findViewById(R.id.homeforcunt_today_name);
            }
        }

        @Override
        public ThisChangeAdapder.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_this_constellation_item, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final ConstellationBean.ConstellationIcon icon = icons.get(position);
            holder.mIcon.setImageResource(icon.getConIcon());
            holder.mName.setText(icon.getConName());

            holder.mItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mHolder.mConstellation_image.setImageResource(icon.getConIcon());
                    mHolder.mConstellation_name.setText(icon.getConName());
//                    mHolder.mConstellation_date.setText(ConstellationUtil.getConsTimeSlot(icon.getConName()));
//                    mHolder.mConstellation_constype.setText(ConstellationUtil.getConsType(icon.getConName()));
                    thisFragment.getConstellationNameData(icon.getConName());
                    mDialog.dismiss();
                }
            });


        }

        @Override
        public int getItemCount() {
            return 12;
        }


    }

}
