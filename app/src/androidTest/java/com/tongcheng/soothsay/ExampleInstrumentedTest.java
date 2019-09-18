package com.tongcheng.soothsay;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.tongcheng.soothsay.bean.calculation.BaziPaipanBean;
import com.tongcheng.soothsay.bean.calculation.QfDxBean;
import com.tongcheng.soothsay.bean.calculation.QfDxDetailListBean;
import com.tongcheng.soothsay.bean.calculation.ZGJMDetailBean;
import com.tongcheng.soothsay.bean.dao.GodBean;
import com.tongcheng.soothsay.bean.huangli.CalendarBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.data.huangli.oldAlmanac.IOldAlmanac;
import com.tongcheng.soothsay.data.huangli.oldAlmanac.OldAlmanacImp;
import com.tongcheng.soothsay.helper.GreenDaoHelper;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.OneiromancyApi;
import com.tongcheng.soothsay.http.service.PrayingApi;
import com.tongcheng.soothsay.http.service.BaZiApi;
import com.tongcheng.soothsay.ui.fragment.huangli.OldAlmanacConstant;
import com.tongcheng.soothsay.utils.LogUtil;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();


        IOldAlmanac iData = new OldAlmanacImp();
        String date = "2016-10-26";
        String url = String.format(Locale.CHINA, OldAlmanacConstant.CALENDAR_API,date,OldAlmanacConstant.HUANGLI_KEY);
        iData.getCalendar(url, new Callback<CalendarBean>() {
            @Override
            public void onResponse(Call<CalendarBean> call, Response<CalendarBean> response) {
               LogUtil.i("tag","onResponse  :  ");

                if(response.isSuccessful()){
                    CalendarBean bean = response.body();
                    LogUtil.i("tag","onResponse  :  " +bean.toString() );
                }
            }

            @Override
            public void onFailure(Call<CalendarBean> call, Throwable t) {
                LogUtil.i("tag","Throwable  :  " + t.getMessage());
            }
        });
    }

    @Test
    public void paziTest(){
        String year="1980";
        String month="1";
        String day="1";
        String hour="0";
        String minute="0";
        String sex="1";
        BaZiApi.getInstance().baziPaipan(year,month,day,hour,minute,sex).enqueue(new ApiCallBack<ApiResponseBean<BaziPaipanBean>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                BaziPaipanBean bean = (BaziPaipanBean) data;
                LogUtil.printD("bean" + bean);

            }

            @Override
            public void onError(String errorCode, String message) {
            }
        }));
    }

    @Test
    public void daoTest(){
        List<GodBean> godBeen = GreenDaoHelper.getInstance().getSeeion().getGodBeanDao().loadAll();
        for (GodBean godBean : godBeen) {
            Log.d("ExampleInstrumentedTest", "godBean.getImgSrc():" + godBean.getImgSrc());
        }
    }


    @Test
    public void getQfDxDetailList(){
        PrayingApi.getInstance().qfDxDetailList("57e6e9efb9a83aded05d2e845a117497","Test").enqueue(new ApiCallBack<ApiResponseBean<QfDxDetailListBean>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                QfDxDetailListBean data1 = (QfDxDetailListBean) data;
                System.out.println(data1.getQfTotalTimes());
            }

            @Override
            public void onError(String errorCode, String message) {

            }
        }));
    }


    @Test
    public void getQfDxlList(){
        PrayingApi.getInstance().qfDxList("6f13bbceae088f67e619c08f924496b7").enqueue(new ApiCallBack<ApiResponseBean<List<QfDxBean>>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                System.out.println("正确");
            }

            @Override
            public void onError(String errorCode, String message) {
                System.out.println("错误");

            }
        }));
    }

    @Test
    public  void getZGJM(){
        System.out.println("Test3");
        OneiromancyApi.getInstance().getZGQuery(Constant.BASE_ONEIROMANCY_KEY,"魚",null,"1").enqueue(new Callback<ZGJMDetailBean>() {
            @Override
            public void onResponse(Call<ZGJMDetailBean> call, Response<ZGJMDetailBean> response) {
                System.out.println("Test2");
            }

            @Override
            public void onFailure(Call<ZGJMDetailBean> call, Throwable t) {
                System.out.println("Test1");
            }
        });
//        System.out.println("Test");
    }



}





