package com.tongcheng.soothsay.dialog;

import android.content.Context;
import android.content.res.AssetManager;

import com.bigkoo.XmlParserHandler;
import com.bigkoo.model.ProvinceModel;
import com.bigkoo.pickerview.OptionsPickerView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by ALing on 2016/12/26 0026.
 */

public class ProvinceCityAreaDialog {
    private List<String> proList = new ArrayList<>();           //省
    private List<List<String>> cityList = new ArrayList<>();    //市
    private List<List<List<String>>> areaList = new ArrayList<>();     //区

    private OptionsPickerView pickerView;
    private OnCitySelectListener listener;

    public ProvinceCityAreaDialog(Context context ){
        pickerView = new OptionsPickerView(context);
        AssetManager asset = context.getAssets();
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
                areaList.add(list.getDisNameList());
            }
        }
        pickerView.setPicker(proList, cityList,areaList, true);
        pickerView.setCyclic(false);
        pickerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                if(listener != null){
                    String province = proList.get(options1);
                    String city = cityList.get(options1).get(option2).replace("市","");
                    String area = areaList.get(options1).get(option2).get(options3);
                    listener.onCitySelect(province,city,area);
                }

            }
        });

    }

    public void show(){
        pickerView.show();
    }

    public interface OnCitySelectListener{
        void onCitySelect(String province,String city,String area);
    }

    public void setOnCitySelectListener(OnCitySelectListener listener){
        this.listener = listener;
    }
}
