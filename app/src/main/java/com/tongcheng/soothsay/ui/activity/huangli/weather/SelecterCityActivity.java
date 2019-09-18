package com.tongcheng.soothsay.ui.activity.huangli.weather;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bigkoo.XmlParserHandler;
import com.bigkoo.model.ProvinceModel;
import com.bigkoo.pickerview.OptionsPickerView;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.huangli.SelectorCityAdapter;
import com.tongcheng.soothsay.base.AbsBaseRecycleAdapter;
import com.tongcheng.soothsay.base.BaseRecyclerAdapter;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.dao.CityBean;
import com.tongcheng.soothsay.bean.dao.CityBeanDao;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.helper.GreenDaoHelper;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.WindowUtil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import butterknife.BindView;

/**
 * 城市选择
 */
public class SelecterCityActivity extends BaseTitleActivity {

    @BindView(R.id.rv_selecter_city)        RecyclerView reView;

    private String city;

    private LinkedList<CityBean> citys;
    private List<String> proList = new ArrayList<>();           //省
    private List<List<String>> cityList = new ArrayList<>();    //市

    private OptionsPickerView pickerView;

    private SelectorCityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_selecter_city);

        initView();
        initListener();
        initData();
    }


    @Override
    public void initView() {
        city = getIntent().getStringExtra(Constant.INTENT_DATA);
        getBaseHeadView().showTitle(city);
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });


        citys = new LinkedList<CityBean>();
        List<CityBean> temps = getCityByDb();
        if(temps.size() == 0){
            citys.add(0,new CityBean(""));
            citys.add(0,new CityBean(city));
            CityBeanDao dao = GreenDaoHelper.getInstance().getSeeion().getCityBeanDao();
            dao.insert(new CityBean(city));
        }else{
            citys.addAll(temps);
            citys.addLast(new CityBean(""));
        }

        adapter = new SelectorCityAdapter(this,citys,R.layout.item_grid_weather_city);
        reView.addItemDecoration(new SpaceItemDecoration(WindowUtil.dip2px(this,5)));
        reView.setItemAnimator(new DefaultItemAnimator());
        reView.setLayoutManager(new GridLayoutManager(this,4));
        reView.setAdapter(adapter);

    }

    @Override
    public void initListener() {

        adapter.setOnReItemOnClickListener(new BaseRecyclerAdapter.OnReItemOnClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                if(position == adapter.getList().size()-1){
                    showSelectPop();
                }else{
                    city = adapter.getList().get(position).getCity();
                    Intent intent = new Intent();
                    intent.putExtra(Constant.INTENT_DATA,city);
                    setResult(1,intent);
                    finish();
                }
            }
        });


    }

    private void initPicker(){
        pickerView = new OptionsPickerView(this);
        AssetManager asset = getAssets();
        InputStream input = null;
        List<ProvinceModel> provinceList = null;
        try {
            input = asset.open("province_data.xml");

            // 创建一个解析xml的工厂对象
            SAXParserFactory spf = SAXParserFactory.newInstance();
            // 解析xml
            SAXParser parser = spf.newSAXParser();
            XmlParserHandler handler = new XmlParserHandler();
            parser.parse(input, handler);
            input.close();
            // 获取解析出来的数据
            provinceList = handler.getDataList();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        if (provinceList != null && provinceList.size() > 0) {
            for (ProvinceModel list : provinceList) {
                proList.add(list.getPickerViewText());
                cityList.add(list.getCityNameList());
            }
        }
        pickerView.setPicker(proList, cityList, true);
        pickerView.setCyclic(false);
        pickerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                city = cityList.get(options1).get(option2).replace("市","");
                insertCity(new CityBean(city));
            }
        });
    }

    private void showSelectPop(){
        if(pickerView == null){
            initPicker();
        }
        pickerView.show();
    }

    private List<CityBean> getCityByDb(){
        CityBeanDao dao = GreenDaoHelper.getInstance().getSeeion().getCityBeanDao();
        return dao.queryBuilder().list();
    }

    private void insertCity(CityBean bean){
        for(CityBean temp : adapter.getList()){
            if(temp.getCity().equals(bean.getCity())){
                return;
            }
        }

        CityBeanDao dao = GreenDaoHelper.getInstance().getSeeion().getCityBeanDao();
        dao.insert(bean);
        adapter.addData(adapter.getList().size()-1,bean);

    }


}
