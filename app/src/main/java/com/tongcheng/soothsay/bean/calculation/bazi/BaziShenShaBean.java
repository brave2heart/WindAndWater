package com.tongcheng.soothsay.bean.calculation.bazi;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Gubr on 2016/12/28.
 */

public class BaziShenShaBean {


    /**
     * jieXi : 甲乙生人子午中, 丙丁鸡兔定亨通,戊己两干临四季, 庚辛寅亥禄丰隆,壬癸巳申偏喜美, 值此应当福气钟,更须贵格来相扶, 候封万户到三公.
     太极贵人查法同天乙贵人. 但无胎元, 命宫之说.<<三命通会>>曰:"太极者, 太初也, 始也, 物造于初为太极, 成也, 收也.物有归曰极, 造化始终相保. 乃曰太极贵人也. 甲乙木造乎子, 坎水而生, 后终乎午, 离火焚而死, 丙丁火, 先喜出乎震, 卯也. 后喜藏乎兑, 酉也. 庚辛金得寅, 乃金生乎艮, 见亥乃金庙乎乾. 壬癸水先得申而生, 后得巳而纳."
     太极贵人, 人命逢之, 主聪明好学, 有钻劲, 喜文史哲宗教等科目. 为人正直, 做事有始有终. 如得生旺及有贵格吉星相扶助, 主气宇轩昂, 福寿双全, 富贵人间.
     * shenSha : 太极
     */

    @SerializedName("jieXi")
    private String jieXi;
    @SerializedName("shenSha")
    private String shenSha;

    public String getJieXi() {
        return jieXi;
    }

    public void setJieXi(String jieXi) {
        this.jieXi = jieXi;
    }

    public String getShenSha() {
        return shenSha;
    }

    public void setShenSha(String shenSha) {
        this.shenSha = shenSha;
    }
}
