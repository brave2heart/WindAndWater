package com.living.bean.mingli.layout;

/**
 * Created by weihao on 2018/1/9.
 */

public class RecyclerViewItemData<T> {

    private T mT;  //用来装载不同类型的item数据bean
    private int dataType; //item数据bean的类型

    public RecyclerViewItemData(T t, int dataType) {
        mT = t;
        this.dataType = dataType;
    }

    public T getT() {
        return mT;
    }

    public void setT(T t) {
        mT = t;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }
}
