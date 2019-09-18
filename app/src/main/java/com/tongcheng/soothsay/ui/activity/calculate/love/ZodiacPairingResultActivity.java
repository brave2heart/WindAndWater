package com.tongcheng.soothsay.ui.activity.calculate.love;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.calculation.ZodiacPairing;
import com.tongcheng.soothsay.utils.ClickUtil;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/12/11 0011.
 * 生肖配对结果页
 */

public class ZodiacPairingResultActivity extends BaseTitleActivity {
    @BindView(R.id.iv_boy_zodiac)
    ImageView ivBoyZodiac;
    @BindView(R.id.iv_girl_zodiac)
    ImageView ivGirlZodiac;
    @BindView(R.id.tv_animal)
    TextView tvAnimal;
    @BindView(R.id.tv_animal_detail)
    TextView tvAnimalDetail;
    private ZodiacPairing bean;
    private String boyIndex, girlIndex;
    private String animalDetail;
    private String[] ChineseZodiacArr;
    private String mBoyZodiac;
    private String mGirlZodiac;
    private int[] imgZoadiac = new int[]{R.drawable.hyal_sxpd_sx_0, R.drawable.hyal_sxpd_sx_1, R.drawable.hyal_sxpd_sx_2, R.drawable.hyal_sxpd_sx_3,
            R.drawable.hyal_sxpd_sx_4, R.drawable.hyal_sxpd_sx_5, R.drawable.hyal_sxpd_sx_6, R.drawable.hyal_sxpd_sx_7, R.drawable.hyal_sxpd_sx_8,
            R.drawable.hyal_sxpd_sx_9, R.drawable.hyal_sxpd_sx_10, R.drawable.hyal_sxpd_sx_11};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_zodiacpairing_result);
        initListener();
        initData();
        setData();
    }

    @Override
    public void initListener() {
        super.initListener();
        getBaseHeadView().showTitle(getResources().getString(R.string.title_zodiac_pairing));
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        ChineseZodiacArr = getResources().getStringArray(R.array.zodiac_arr);
        bean = (ZodiacPairing) getIntent().getSerializableExtra("bean");
        boyIndex = bean.getBoyIndex();
        girlIndex = bean.getGirlIndex();
        Log.e(TAG, "initData index: " + boyIndex + ">>>>>" + girlIndex);
        animalDetail = bean.getAnimalDetail();
    }

    private void setData() {
        ivBoyZodiac.setImageResource(imgZoadiac[Integer.valueOf(boyIndex)]);
        ivGirlZodiac.setImageResource(imgZoadiac[Integer.valueOf(girlIndex)]);
        mBoyZodiac = ChineseZodiacArr[Integer.valueOf(boyIndex)];
        mGirlZodiac = ChineseZodiacArr[Integer.valueOf(girlIndex)];
        tvAnimal.setText(mBoyZodiac + "先生+" + mGirlZodiac + "太太");
        tvAnimalDetail.setText("\t\t\t"+animalDetail);
    }
}
