package com.tongcheng.soothsay.other.tarot;

import android.content.Context;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.bean.calculation.TarotBean;
import com.tongcheng.soothsay.popwindow.TarotTypePop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Steven on 16/12/6.
 * 用于随机生成塔罗牌
 */
public class TarotManager {

    private Random mRandom;

    private Context mContext;

    public TarotManager(Context context){
        mContext = context;
        mRandom = new Random();

    }


    /**
     * 根据图片下表 获取牌义
     * @param index
     * @param type
     * @param isZhengwei 是否是逆位
     * @return
     */
    public String getjieYu(int index ,int type ,boolean isZhengwei){
        String jieyu = null;
        switch (type){
            case TarotTypePop.LOVE:
                if(isZhengwei){
                    jieyu = mContext.getResources().getStringArray(R.array.tarot_zw_love)[index];
                }else{
                    jieyu = mContext.getResources().getStringArray(R.array.tarot_nw_love)[index];
                }
                break;

            case TarotTypePop.SHIYE:
                if(isZhengwei){
                    jieyu = mContext.getResources().getStringArray(R.array.tarot_zw_work)[index];
                }else{
                    jieyu = mContext.getResources().getStringArray(R.array.tarot_nw_work)[index];
                }
                break;

            case TarotTypePop.MONEY:
                if(isZhengwei){
                    jieyu = mContext.getResources().getStringArray(R.array.tarot_zw_money)[index];
                }else{
                    jieyu = mContext.getResources().getStringArray(R.array.tarot_nw_money)[index];
                }
                break;

            case TarotTypePop.HEALTH:
                if(isZhengwei){
                    jieyu = mContext.getResources().getStringArray(R.array.tarot_zhengwei)[index];
                }else{
                    jieyu = mContext.getResources().getStringArray(R.array.tarot_nw_jiankang)[index];
                }
                break;
        }

        return jieyu;
    }


    /**
     * 创建塔罗牌
     * @return
     */
    public List<TarotCardView> createTarotCard(){
        List<TarotCardView> cards = new ArrayList<TarotCardView>();

        for(TarotBean bean : createTarot()){
            TarotCardView card = new TarotCardView(mContext);
            card.setCardBean(bean);
            cards.add(card);
        }

        return cards;
    }

    /**
     * 创建塔罗牌bean对象
     * @return
     */
    private List<TarotBean> createTarot(){
        int images [] ;     //塔罗牌的图片id
        String names [] ;   //塔罗牌的名字

        names = mContext.getResources().getStringArray(R.array.tarot_name);

        images = new int[22];
        for (int i = 0; i < 22; i++) {
            int id = mContext.getResources().getIdentifier("xztl_tlzb_" + i,"drawable",mContext.getPackageName());
            images[i] = id;
        }

        List<TarotBean> tarots  = new ArrayList<TarotBean>();
        for(int i : randomArray(22)){
            int imgId = images[i];
            String name = names[i];
            int flag = mRandom.nextInt(100);
            boolean isZhengwei = flag % 2 == 0 ? true : false;

            TarotBean bean = new TarotBean(i,imgId,name,isZhengwei);
            tarots.add(bean);
        }

        return tarots;
    }



    /**
     * 随机生成牌顺序
     * @param n
     * @return
     */
    private List<Integer> randomArray(int n){
        List<Integer> result = new ArrayList<Integer>();
        int temp = mRandom.nextInt(n);
        while(result.size()!= n){
            if(result.size() == 0){
                result.add(temp);

            }else{

                boolean flag = false;
                for(int b : result){
                    if(b == temp){
                        flag = true;
                    }
                }
                if(!flag){
                    result.add(temp);
                }
            }
            temp = mRandom.nextInt(n);

        }

        return result;
    }



}
