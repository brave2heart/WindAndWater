package com.tongcheng.soothsay.ui.fragment.calculation.life.zeri;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.huangli.AlmanacBean;
import com.tongcheng.soothsay.bean.huangli.HousYijiBean;
import com.tongcheng.soothsay.other.BaZi;
import com.tongcheng.soothsay.ui.activity.huangli.almanac.AlmanacConstant;
import com.tongcheng.soothsay.ui.activity.huangli.almanac.AlmanacPresenter;
import com.tongcheng.soothsay.ui.activity.huangli.almanac.HourYijiActivity;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.DateUtil;
import com.tongcheng.soothsay.utils.ToastUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 黄历详情页面
 */
public class ZeriDetailsActivity extends BaseTitleActivity{

    @BindView(R.id.tv_old_date)                 TextView        tvOldDate;
    @BindView(R.id.tv_almanac_animal)           TextView        tvanimal;
    @BindView(R.id.tv_almanac_yi)               TextView        tvYi;
    @BindView(R.id.tv_almanac_ji)               TextView        tvJi;
    @BindView(R.id.tv_almanac_jishen)           TextView        tvJiShen;
    @BindView(R.id.tv_almanac_xiongshen)        TextView        tvXiongShen;
    @BindView(R.id.tv_almanac_chongsha)         TextView        tvChongsha;
    @BindView(R.id.tv_almanac_wuxing)           TextView        tvWuxing;
    @BindView(R.id.tv_almanac_pengzu)           TextView        tvPengzu;




//    private List<HousYijiBean.ResultBean> hourBeans;
   private AlmanacBean.ResultBean mYijiBean;

    private Calendar cal;
//    private AlmanacConstant.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_almanacv2);

        initView();
        initData();
    }


    @Override
    public void initView() {
        mYijiBean= (AlmanacBean.ResultBean) getIntent().getSerializableExtra("bean");
//        dateArr = getIntent().getIntArrayExtra(AlmanacConstant.INTENT_DATE);
//        String year = dateArr[0] + "";
//        String month = getStringDate(dateArr[1]);
//        String day = getStringDate(dateArr[2]);
//        getBaseHeadView().showTitle(year + "年" + month + "月" + day + "日");
        getBaseHeadView().showTitle("老黄历");
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        showAlmanac(mYijiBean);
    }

    @Override
    public void initData() {
//        mPresenter = new AlmanacPresenter(this);
//        String date = dateArr[0] + "-" + dateArr[1] + "-" + dateArr[2];
//        mPresenter.getHoursYiji(date);
    }

//    @OnClick({R.id.img_almanac_last,
//              R.id.img_almanac_next,
//              R.id.rl_almanac_yiji,
//              R.id.ll_almanac_yiji})
//    public void onClick(View v){
//
////        switch (v.getId()){
////            //上一天老黄历
////            case R.id.img_almanac_last:
////                getNextOrLastDay(false);
////                break;
////
////            //下一天老黄历
////            case R.id.img_almanac_next:
////                getNextOrLastDay(true);
////                break;
////
////            case R.id.rl_almanac_yiji:
////            case R.id.ll_almanac_yiji:
////                ArrayList<HousYijiBean.ResultBean> hous = new ArrayList<HousYijiBean.ResultBean>();
////                hous.addAll(hourBeans);
////                Intent intent = new Intent(this,HourYijiActivity.class);
////                intent.putExtra("data",hous);
////                startActivity(intent);
////                break;
////        }
//    }

    private String getStringDate(int temp){
        return temp < 10 ? "0" + temp : "" + temp;
    }


//    //上一天黄历 or 下一天黄历
//    private void getNextOrLastDay (boolean next) {
//        if(onNetwork){
//            return;
//        }
//        onNetwork = true;
//        if(next){
//            ++offsetDay;
//
//        }else{
//            --offsetDay;
//        }
//        String date = DateUtil.getCurTimeAddND(offsetDay,"yyyy-MM-dd");
//        String year = date.split("-")[0];
//        String month = date.split("-")[1];
//        String day = date.split("-")[2];
//        getBaseHeadView().showTitle(year + "年" + month + "月" + day + "日");
//
//        mPresenter.getHoursYiji(date);
//    }


    //显示老黄历

    public void showAlmanac(AlmanacBean.ResultBean bean) {


            try {
                AlmanacBean.ResultBean resultBean = bean;
                String yinli = resultBean.getYinli();
                yinli = yinli.replace("(","");
                yinli = yinli.replace(")","");

                String nongli = yinli.substring(yinli.indexOf("年") + 1,yinli.length());
                tvOldDate.setText(nongli);

                String date = resultBean.getYangli();
                cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                cal.setTime(sdf.parse(date));
                BaZi bazi = new BaZi(cal);
                String lunar = bazi.getMonthAndDayGanZhi(date.split("-")[1],date.split("-")[2]);
                String lunarM = lunar.split(",")[0];
                String lunarD = lunar.split(",")[1];
                tvanimal.setText(yinli.substring(0,yinli.indexOf("年") + 1) + " " + lunarM + "月" + " " +lunarD + "日");

                tvYi.setText(resultBean.getYi());
                tvJi.setText(resultBean.getJi());
                tvJiShen.setText(resultBean.getJishen());
                tvXiongShen.setText(resultBean.getXiongshen());
                tvChongsha.setText(resultBean.getChongsha());
                tvWuxing.setText(resultBean.getWuxing());
                tvPengzu.setText(resultBean.getBaiji());

//                StringBuilder sb = new StringBuilder();
//                hourBeans = bean.getHours();
//                for(int i=0 ; i < hourBeans.size() ; i++){
//                    View view = View.inflate(this,R.layout.item_almanac_yiji,null);
//                    TextView tvHour = (TextView) view.findViewById(R.id.tv_almanac_hour);
//                    TextView tvChong = (TextView) view.findViewById(R.id.tv_almanac_chong);
//
//                    String des = hourBeans.get(i).getDes();
//                    if(!TextUtils.isEmpty(des)){
//                        sb.delete(0,sb.length());
//                        des = des.trim();
//                        sb.append(des.split(" ")[0]);
//                        sb.insert(1,"\n");
//                        tvChong.setText(sb.toString());
//                    }
//
//                    tvHour.setText(hours[i]);
//                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
//                    params.weight = 1;
//                    view.setLayoutParams(params);
//                    layoutYiji.addView(view);
//                }

//                sb.delete(0,sb.length());
//                sb.append(resultBean.getBaiji().replace(" ","\n"));
//                sb.insert(4,"\n");
//                sb.insert(9,"\n");
//                sb.insert(15,"\n");

            } catch (ParseException e) {
                e.printStackTrace();
            }


        }
    }











