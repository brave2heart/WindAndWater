package com.living.ui.fragment.home.TouTiao;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.living.constant.home.HomeAPI;
import com.tongcheng.soothsay.R;

/**
 * Created by weihao on 2017/12/18.
 */

public class TouTiaoFragment extends NewsBaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_toutiao, container, false);

        View view1 = initView(view, R.id.recyclerview,HomeAPI.TouTiaoType[0], HomeAPI.TouTiaokey);
        return view1;
    }




}

