package com.tongcheng.soothsay.bean.calculation;

import com.tongcheng.soothsay.http.service.AllApi;

import java.io.Serializable;

/**
 * Created by ALing on 2016/12/5 0005.
 */

public class ZodiacPairing implements Serializable{
    private String boyIndex;
    private String girlIndex;
    private String animalDetail;

    public String getBoyIndex() {
        return boyIndex;
    }

    public void setBoyIndex(String boyIndex) {
        this.boyIndex = boyIndex;
    }

    public String getGirlIndex() {
        return girlIndex;
    }

    public void setGirlIndex(String girlIndex) {
        this.girlIndex = girlIndex;
    }

    public String getAnimalDetail() {
        return animalDetail;
    }

    public void setAnimalDetail(String animalDetail) {
        this.animalDetail = animalDetail;
    }
}
