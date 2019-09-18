package com.tongcheng.soothsay.data.calculate.life.zeri;

import android.content.Context;
import android.util.Log;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.bean.calculation.ZeriBean;
import com.tongcheng.soothsay.bean.huangli.AlmanacBean;
import com.tongcheng.soothsay.http.service.HuangliApi;
import com.tongcheng.soothsay.ui.activity.huangli.almanac.AlmanacConstant;
import com.tongcheng.soothsay.utils.DateUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by bozhihuatong on 2016/12/23.
 */

public class ZeriSourceImp implements IZeriSource {

    private static final String TAG = "ZeriSourceImp";
    private final Context mContext;
    private final List<String> mYijiList;
    private final List<String> mYijiContent;
    List<ZeriBean> yiDates;
    List<ZeriBean> jiDates;
    private boolean isYi = true;
    private Date mDate;
    private int resultCount = 0;
    private ArrayList<AlmanacBean.ResultBean> mResultBeen;
    private String checkTitle;


    public ZeriSourceImp(Context context) {
        mContext = context;
        String[] stringArray = mContext.getResources().getStringArray(R.array.zeri_yiji_list);
        mYijiList = Arrays.asList(stringArray);
        String[] stringArray1 = mContext.getResources().getStringArray(R.array.zeri_yiji_analysis);
        mYijiContent = Arrays.asList(stringArray1);
    }

    @Override
    public List<ZeriBean> getSelectEventData() {

        if (isYi) {
            if (yiDates == null) {
                yiDates = new ArrayList<>();
                String[] stringArray = mContext.getResources().getStringArray(R.array.zeri_yi_table);
                for (String s : stringArray) {
                    int i = mYijiList.indexOf(s);
                    yiDates.add(new ZeriBean(s, mYijiContent.get(i), false));
                }
            }
            return yiDates;
        } else {
            if (jiDates == null) {
                jiDates = new ArrayList<>();
                String[] stringArray = mContext.getResources().getStringArray(R.array.zeri_ji_table);
                for (String s : stringArray) {
                    int i = mYijiList.indexOf(s);
                    jiDates.add(new ZeriBean(s, mYijiContent.get(i), false));
                }
            }
            return jiDates;
        }

    }

    @Override
    public void setCheckTitle(String checkTitle) {
        this.checkTitle = checkTitle;
    }

    @Override
    public void saveEventType(boolean flag) {
        isYi = flag;
    }


    @Override
    public void saveSelectTime(Date date) {
        mDate = date;
    }

    @Override
    public String getTimeBtnText() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String time = sdf.format(mDate);
        return new String("公历 " + time);
    }

    @Override
    public void getResultSource(final OnResultListener onResultListener) {
        resultCount = 0;
        mResultBeen = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Date netDate = DateUtil.getNetDate(mDate, i);
            String date = DateUtil.formatDate(netDate, "yyyy-MM-dd");
            String url = String.format(Locale.CHINA, AlmanacConstant.ALMANAC_API, AlmanacConstant.ALMANAC_KEY, date);
            HuangliApi.getInstance().getAlmanac(url).enqueue(new Callback<AlmanacBean>() {
                @Override
                public void onResponse(Call<AlmanacBean> call, Response<AlmanacBean> response) {
                    AlmanacBean.ResultBean result = response.body().getResult();
                    if (isYi) {
                        String[] yiArray = result.getYiArray();
                        addData(yiArray, result, onResultListener);
                    } else {
                        String[] jiArray = result.getJiArray();
                        addData(jiArray, result, onResultListener);
                    }
                }

                @Override
                public void onFailure(Call<AlmanacBean> call, Throwable t) {
                    synchronized (this){
                        ++resultCount;
                    }
                    onResultListener.onError();
                }
            });
        }

    }


    @Override
    public AlmanacBean.ResultBean getDatas(int position) {
        if (mResultBeen.size() > position) {
            return mResultBeen.get(position);
        } else {
            return null;
        }
    }

    @Override
    public String getSelectEventBtnText() {
        return checkTitle;
    }

    private void addData(String[] yiArray, AlmanacBean.ResultBean result, OnResultListener onResultListener) {
        synchronized (ZeriSourceImp.this) {

//            万事皆吉
//            <item>诸事不利</item>
//            <item></item>
//            <item>万事皆凶</item>
            for (String s : yiArray) {
                if (s.equals(checkTitle) || (isYi ? s.equals("万事皆吉") : (s.equals("诸事不利") || s.equals("万事皆凶")))) {
                    mResultBeen.add(result);
                    break;
                }
            }
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

            ++resultCount;
            if (resultCount == 7) {
                Collections.sort(mResultBeen, new Comparator<AlmanacBean.ResultBean>() {
                    @Override
                    public int compare(AlmanacBean.ResultBean o1, AlmanacBean.ResultBean o2) {
                        return o1.getIntYangli() - o2.getIntYangli();
                    }
                });
                onResultListener.getResultDatas(mResultBeen);
            }
        }

    }


    @Override
    public String getCheckTitle() {
        return checkTitle;
    }
}



