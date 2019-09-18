package com.tongcheng.soothsay.ui.activity.calculate.chuantong.ziwei;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.ZiweiInputAdapter;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.calculation.ZiweipaipanBean;
import com.tongcheng.soothsay.bean.dao.ZiweiUserBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.ToastUtil;
import com.tongcheng.soothsay.utils.WindowUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 紫微输入用户信息
 */
public class ZiweiInputActivity extends BaseTitleActivity implements ZiweiInputConstant.View, TimePickerView.OnTimeSelectListener {

    @BindView(R.id.edit_ziwei_input)        EditText        editName;
    @BindView(R.id.rg_ziwei_input)          RadioGroup      rgSex;
    @BindView(R.id.rg_ziwei_input_man)      RadioButton     rbMan;
    @BindView(R.id.rg_ziwei_input_woman)    RadioButton     rbWoman;
    @BindView(R.id.tv_ziwei_input_date)     TextView        tvDate;
    @BindView(R.id.tv_ziwei_input_fenxi)    TextView        tvFenxi;
    @BindView(R.id.tv_ziwei_input_del)      TextView        tvDel;
    @BindView(R.id.lv_ziwei_input)          ListView        listView;
    @BindView(R.id.activity_ziwei_input)    View            layoutView;
    @BindView(R.id.til_ziwei_input)         TextInputLayout til;

    private String sex = "男";
    private String name;
    private String time ="1900.08.08.08";
    private int icon = R.drawable.ctmx_ziwei_touxiang_man;
    private ZiweiUserBean inputBean;

    private TimePickerView timeView;

    private ZiweiInputAdapter adapter;

    private ZiweiInputConstant.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ziwei_input);

        initView();
        initListener();
        initData();
    }

    @Override
    public void initView() {
        getBaseHeadView().showTitle(getResources().getString(R.string.title_ziwei_input));
    }

    @Override
    public void initListener() {
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        rgSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                WindowUtil.closeInputMethod(ZiweiInputActivity.this);
                if(checkedId == R.id.rg_ziwei_input_man){
                    sex = "男";
                    icon = R.drawable.ctmx_ziwei_touxiang_man;
                }
                if(checkedId == R.id.rg_ziwei_input_woman){
                    sex = "女";
                    icon = R.drawable.ctmx_ziwei_touxiang_woman;
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ZiweiUserBean bean = adapter.getDatas().get(position);
                time = bean.getDate();
                name = bean.getName();
                sex = bean.getSex();
                String temp [] = time.split("\\.");
                tvDate.setText("阳历： "+temp[0] + "年" + temp[1] + "月" + temp[2] + "日" + temp[3] + "时");
                editName.setText(name);
                if("男".equals(sex)){
                    rbMan.setChecked(true);
                }else{
                    rbWoman.setChecked(true);
                }
                tvFenxi.performClick();
            }
        });
    }

    @Override
    public void initData() {
        mPresenter = new ZiweiInputPresenter(this);
        mPresenter.getHistory();
    }


    @OnClick({R.id.activity_ziwei_input,
              R.id.tv_ziwei_input_fenxi,
              R.id.tv_ziwei_input_date,
              R.id.tv_ziwei_input_del})
    public void onClick(View v){

        WindowUtil.closeInputMethod(this);
        switch (v.getId()){
            //时间
            case R.id.tv_ziwei_input_date:
                showTimePicker();
                break;

            //分析
            case R.id.tv_ziwei_input_fenxi:
                lookAnalyse();
                break;

            //清空历史记录
            case R.id.tv_ziwei_input_del:
                delHistory();
                break;
        }
    }

    //清空历史记录
    private void delHistory() {
        mPresenter.delHistory();
    }

    //查看分析
    private void lookAnalyse() {
        name = editName.getText().toString();
        if(TextUtils.isEmpty(name)){
            til.setError("名字不能为空");
//            editName.setError("名字不能为空");
            return;
        }else{
            til.setError("");
        }
        inputBean = new ZiweiUserBean();
        inputBean.setName(name);
        inputBean.setDate(time);
        inputBean.setIcon(icon);
        inputBean.setSex(sex);
        mPresenter.saveHistory(inputBean);

        String temp [] = time.split("\\.");
        String sex = this.sex.equals("男") ? "1" : "2";
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("year",temp[0]);
        map.put("month",temp[1]);
        map.put("day",temp[2]);
        map.put("hour",temp[3]);
        map.put("name",name);
        map.put("sex",sex);
        mPresenter.getPaipan(map);
    }

    //显示时间控件
    private void showTimePicker(){
        if(timeView == null){
            timeView = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY_HOUR);
            timeView.setCyclic(false);
            timeView.setTime(new Date());
            timeView.setOnTimeSelectListener(this);
        }
        timeView.show();
    }

    //显示历史记录
    @Override
    public void showHistory(List<ZiweiUserBean> beans) {
        if(adapter == null){
            adapter = new ZiweiInputAdapter(this,beans,R.layout.item_ziwei_input);
            listView.setAdapter(adapter);

        }else if(beans == null){
            adapter.notifyChangeData(new ArrayList<ZiweiUserBean>());

        }else{
            adapter.changeData(beans);
        }
    }


    //显示紫微排盘
    @Override
    public void showPaipan(final ZiweipaipanBean bean) {
        Intent intent = new Intent(ZiweiInputActivity.this,ZiweiPPActivity.class);
        intent.putExtra(Constant.INTENT_DATA,bean);
        intent.putExtra(ZiweiInputConstant.INTENT_INPUT,inputBean);
        startActivity(intent);

    }


    //时间选择回调
    @Override
    public void onTimeSelect(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH");
        time = sdf.format(date);
        String temp [] = time.split("\\.");
        tvDate.setText("阳历： " + temp[0] + "年" + temp[1] + "月" + temp[2] + "日" + temp[3] + "时");
    }


    @Override
    public void showNetError() {}

    @Override
    public void showError(String info) {
        ToastUtil.showShort(this,info);
    }

    @Override
    public void showEmpty() {}

    @Override
    public void showTitle(String title) {}

    @Override
    public void showLoad() {
        getBaseLoadingView().showLoading(true);
    }

    @Override
    public void showLoadFinish() {
        getBaseLoadingView().hideLoading();
    }

    @Override
    public void showReLoad() {}

    @Override
    public void setPresenter(ZiweiInputConstant.Presenter presenter) {
        mPresenter = presenter;
    }



}
