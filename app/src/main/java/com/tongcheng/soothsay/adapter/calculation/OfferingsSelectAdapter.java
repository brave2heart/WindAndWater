package com.tongcheng.soothsay.adapter.calculation;

import android.app.Activity;

import com.tongcheng.soothsay.bean.calculation.Offeringsbean;

import java.util.List;

/**
 * Created by Gubr on 2016/12/3.
 */

public class OfferingsSelectAdapter extends OfferingsAdapter {
    public OfferingsSelectAdapter(Activity context, List<List<Offeringsbean>> datas, int
            LayoutId, int type) {
        super(context, datas, LayoutId, type);
    }
}
