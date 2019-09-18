package com.tongcheng.soothsay.data;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class BaseDataImp {
    private List<Call> mCallList;


    public void onDestroy() {
        if (mCallList != null && mCallList.size() > 0) {
            for (int i = 0; i < mCallList.size(); i++) {
                mCallList.get(i).cancel();
            }
        }
    }

    public void addApiCall(Call call) {
        if (mCallList == null) {
            mCallList = new ArrayList<>();
        }
        mCallList.add(call);
    }
}
