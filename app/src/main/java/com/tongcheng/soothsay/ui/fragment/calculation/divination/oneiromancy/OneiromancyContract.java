package com.tongcheng.soothsay.ui.fragment.calculation.divination.oneiromancy;

import com.tongcheng.soothsay.BasePresenter;
import com.tongcheng.soothsay.BaseUiView;
import com.tongcheng.soothsay.BaseView;
import com.tongcheng.soothsay.bean.calculation.ZGJMDetailBean;

import java.util.List;

/**
 * Created by Gubr on 2016/12/7.
 */

public interface OneiromancyContract {
    interface InputView extends BaseUiView<Presenter> {
        String TAG="INPUTVIEW";
      void  showResult(List<ZGJMDetailBean.ResultBean> datas);

        void showEmptyToast();

        void showMustHanZi();

        void showDarkBack();

        void showLightBack();
    }

    interface DetailView extends BaseView<Presenter>{
        String TAG="DETAILVIEW";


        void showDetail(ZGJMDetailBean.ResultBean data);

        void removeSelf();
    }



    interface Presenter extends BasePresenter{

        void getDetail(int position);

        void showInput();

        void query(String str);

        void subscribe();

        void unsubscribe();


        void removeDetailView();
    }
}
