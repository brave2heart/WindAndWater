package com.tongcheng.soothsay.ui.fragment.calculation.divination.oneiromancy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ta.utdid2.android.utils.SystemUtils;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.OneiromancyTitleAdapter;
import com.tongcheng.soothsay.bean.calculation.ZGJMDetailBean;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.SystemTools;
import com.tongcheng.soothsay.utils.WindowUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by Gubr on 2016/12/7.
 * 周公解梦 输入界面
 */

public class OneiromancyFragmentInput extends Fragment implements OneiromancyContract.InputView {


    private OneiromancyContract.Presenter mPresenter;


    private ListView mListView;
    private EditText etInput;
    private OneiromancyTitleAdapter mOneiromancyTitleAdapter;
    private TextView mTvSubmit;


    public static OneiromancyFragmentInput newInstance() {
        return new OneiromancyFragmentInput();
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_oneiromancy_input, container, false);
        initView(root);
        initData();
        initListener();
        return root;
    }

    private void initView(View root) {
        etInput = (EditText) root.findViewById(R.id.et_input);
        mTvSubmit = (TextView) root.findViewById(R.id.tv_submit);
        mListView = (ListView) root.findViewById(R.id.listview);
    }

    private void initData() {
    }

    private void initListener() {
//        etInput.is
        mTvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ClickUtil.isFastClick()) return;

//                mPresenter.
//                Toast.makeText(getActivity(), "点击", Toast.LENGTH_SHORT).show();
        mPresenter.query(etInput.getText().toString());

                WindowUtil.closeInputMethod(getActivity());
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                这里就可以去显示了。
                mPresenter.getDetail(position);
            }
        });
    }



    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }


    @Override
    public void showResult(List<ZGJMDetailBean.ResultBean> datas) {
        if (mOneiromancyTitleAdapter==null){
            mOneiromancyTitleAdapter = new OneiromancyTitleAdapter(getActivity(), datas, R.layout.item_oneiromancy_title);
            mListView.setAdapter(mOneiromancyTitleAdapter);
        }else{
            mOneiromancyTitleAdapter.notifyChangeData(datas);
        }
    }

    @Override
    public void showEmptyToast() {
        Toast.makeText(getActivity(), "没有查找", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMustHanZi() {
        Toast.makeText(getActivity(), "必须是汉字", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDarkBack() {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha=0.6f;
        getActivity().getWindow().setAttributes(lp);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    @Override
    public void showLightBack() {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 1.0f;
        getActivity().getWindow().setAttributes(lp);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    @Override
    public void setPresenter(OneiromancyContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showNetError() {

    }

    @Override
    public void showError(String info) {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showTitle(String title) {

    }

    @Override
    public void showLoad() {

    }

    @Override
    public void showLoadFinish() {

    }

    @Override
    public void showReLoad() {

    }
}
