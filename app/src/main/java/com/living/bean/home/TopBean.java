package com.living.bean.home;

import java.util.List;

/**
 * Created by weihao on 2017/12/25.
 */

public class TopBean {

    @Override
    public String toString() {
        return "TopBean{" +
                "reason='" + reason + '\'' +
                ", result=" + result +
                ", error_code=" + error_code +
                '}';
    }

    /**
     * reason : 成功的返回
     * result : {"stat":"1","data":[{"uniquekey":"13c6ce71350fd3ef925aa70adcdc651f","title":"演技才会让人气演员发光","date":"2017-12-25 08:43","category":"头条","author_name":"新华网","url":"http://mini.eastday.com/mobile/171225084306459.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20171225/20171225084306_7a5b2fc5a7fabc41ff8943f8eebb7290_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://09.imgmini.eastday.com/mobile/20171225/20171225084306_7a5b2fc5a7fabc41ff8943f8eebb7290_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://09.imgmini.eastday.com/mobile/20171225/20171225084306_7a5b2fc5a7fabc41ff8943f8eebb7290_1_mwpm_03200403.jpg"},{"uniquekey":"8cbb7b97c073bccf29d27d587f544b11","title":"郎朗上《吐槽大会2》一展歌喉\u201c吓坏\u201d网友","date":"2017-12-25 08:43","category":"头条","author_name":"新华网","url":"http://mini.eastday.com/mobile/171225084305480.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20171225/20171225084305_a0e987ba3dd3478b9b315cf97db48c78_1_mwpm_03200403.jpg"},{"uniquekey":"b63297d658dd572da61feec9ca6be3c3","title":"平安夜越南节日气氛浓厚，各地圣诞夜市人潮汹涌","date":"2017-12-25 08:17","category":"头条","author_name":"越南越美","url":"http://mini.eastday.com/mobile/171225081725146.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20171225/20171225081725_3e9094fbe58afa8fa1d0c29ed09eeaf5_8_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01.imgmini.eastday.com/mobile/20171225/20171225081725_3e9094fbe58afa8fa1d0c29ed09eeaf5_7_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01.imgmini.eastday.com/mobile/20171225/20171225081725_3e9094fbe58afa8fa1d0c29ed09eeaf5_3_mwpm_03200403.jpg"},{"uniquekey":"c8301b3fc7195fa8a6ff4a2e9945407a","title":"黄轩：《妖猫传》里白居易像个孩子","date":"2017-12-25 08:11","category":"头条","author_name":"新华网","url":"http://mini.eastday.com/mobile/171225081115970.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20171225/20171225081115_a2962ee334f3432bedbc6de79d23b907_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://06.imgmini.eastday.com/mobile/20171225/20171225081115_a2962ee334f3432bedbc6de79d23b907_1_mwpm_03200403.jpg"},{"uniquekey":"d3c0ffd2e922e72404b2a99fb1910d75","title":"上海这项排名又列全国第一 怪不得魔都各大医院一直挤破头","date":"2017-12-25 08:09","category":"头条","author_name":"上海社区","url":"http://mini.eastday.com/mobile/171225080954432.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20171225/20171225080954_fbe24ca05f300c0dc7a75a5ea12a4142_11_mwpm_03200403.jpg","thumbnail_pic_s02":"http://03.imgmini.eastday.com/mobile/20171225/20171225080954_29892a291f1fd6fe97dafe64c82cbff3_9_mwpm_03200403.jpg","thumbnail_pic_s03":"http://03.imgmini.eastday.com/mobile/20171225/20171225080954_ced6d566c4b0dbae0210d328829043f7_1_mwpm_03200403.jpg"},{"uniquekey":"1eba85a5069448d6465e8279b0d9c9a5","title":"俄美女总统候选人参加活动 笑容满面颜值高","date":"2017-12-25 07:57","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/171225075745724.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20171225/20171225075745_7d4e6c69678b26471be80c78d6c4cc48_4_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00.imgmini.eastday.com/mobile/20171225/20171225075745_7d4e6c69678b26471be80c78d6c4cc48_5_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00.imgmini.eastday.com/mobile/20171225/20171225075745_7d4e6c69678b26471be80c78d6c4cc48_6_mwpm_03200403.jpg"},{"uniquekey":"fe092ddb6d4f238783685199d8e4d0f7","title":"郑建邦获任民革中央常务副主席","date":"2017-12-25 07:51","category":"头条","author_name":"澎湃新闻网","url":"http://mini.eastday.com/mobile/171225075105448.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20171225/20171225075105_64e69e3337e9bcc0ba9dc7bacd9565f0_1_mwpm_03200403.jpg"},{"uniquekey":"fa5ea92c8064103d6716482ff1ebd43a","title":"菲律宾大火零存活率 杜特尔特视察落泪","date":"2017-12-25 07:47","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/171225074736660.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20171225/20171225074736_ae6fdf16312e04638b4fe44aa48ff5ad_1_mwpm_03200403.jpg"},{"uniquekey":"60a163792eb237b59601bac16c1f12e1","title":"黎巴嫩一司机涉嫌强奸英国女外交官 并将其杀害","date":"2017-12-25 07:47","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/171225074736542.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20171225/20171225074736_3d78c06bbd18c571c67ae25d1abdd939_1_mwpm_03200403.jpg"},{"uniquekey":"103ffd25abe837f014050ed05cb1857b","title":"做好战争准备！美防长刚刚放狠话，这回似乎真要动武？","date":"2017-12-25 07:40","category":"头条","author_name":"第一军情","url":"http://mini.eastday.com/mobile/171225074059149.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20171225/20171225_fb1ad2ac32269e23131a9adb87921a78_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01.imgmini.eastday.com/mobile/20171225/20171225_f198c1b027637311d2dade2ee9bb7753_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01.imgmini.eastday.com/mobile/20171225/20171225_01483e0e307a2429d2bb7b17c0fcb4f6_cover_mwpm_03200403.jpg"},{"uniquekey":"03ea27e19f207482e65c7134fc671668","title":"这个地方全面禁烟，私藏烟草蹲大牢!网友表示:国内这样烟早戒了","date":"2017-12-25 07:37","category":"头条","author_name":"飞行的狗狗","url":"http://mini.eastday.com/mobile/171225073721788.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20171225/20171225073721_f2d2d2bfbd5485193e3360c11fd83b01_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01.imgmini.eastday.com/mobile/20171225/20171225073721_f2d2d2bfbd5485193e3360c11fd83b01_6_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01.imgmini.eastday.com/mobile/20171225/20171225073721_f2d2d2bfbd5485193e3360c11fd83b01_5_mwpm_03200403.jpg"},{"uniquekey":"d92643e08d8ada8263b969e52ca555c5","title":"美国最新表态曝光：这两句话里竟暗藏动手信号！半岛局势不容乐观","date":"2017-12-25 07:34","category":"头条","author_name":"大国军情","url":"http://mini.eastday.com/mobile/171225073416798.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20171225/20171225_2cf93b7a9d978cdcaaecad42a4d9d3cc_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://06.imgmini.eastday.com/mobile/20171225/20171225_8ced19167b9731c18d2a9376e4d9bf79_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://06.imgmini.eastday.com/mobile/20171225/20171225_1213e7d93377f1c1d145911608bc6a17_cover_mwpm_03200403.jpg"},{"uniquekey":"2c9a38d1c7001d267e3741e62d4f0cc9","title":"最新爆料：日本终于要迈出最危险一步！这次连遮羞布都不要了","date":"2017-12-25 07:31","category":"头条","author_name":"大国军情","url":"http://mini.eastday.com/mobile/171225073145253.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20171225/20171225_bd3c5bb3b6e3ea00930b3a687515de81_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://09.imgmini.eastday.com/mobile/20171225/20171225_2d89d20f23631479d8f837465248d5e0_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://09.imgmini.eastday.com/mobile/20171225/20171225_d7adbba7e84b4990f22e57b6f1ba100d_cover_mwpm_03200403.jpg"},{"uniquekey":"de0d7cafba0304fa16ee3562ccff5350","title":"半岛局势又升级：一个军事强国高调插足！最新表态却让特朗普发懵","date":"2017-12-25 07:28","category":"头条","author_name":"大国军情","url":"http://mini.eastday.com/mobile/171225072805669.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20171225/20171225_d0f8721796c77cf8c1d622855ab358a4_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01.imgmini.eastday.com/mobile/20171225/20171225_b3dfadadb9edf25b484a384afc7ae616_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01.imgmini.eastday.com/mobile/20171225/20171225_7a00929218648c171c27b991f6322a3d_cover_mwpm_03200403.jpg"},{"uniquekey":"8995cbdc3b65309f4d65ee479a5c24cf","title":"中国嫦娥四号将上月球种土豆!日本慌了!宣布要登上月球!","date":"2017-12-25 07:26","category":"头条","author_name":"中国气象爱好者","url":"http://mini.eastday.com/mobile/171225072639105.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20171225/20171225072639_12382846780b78d97243ca14f5dee19a_5_mwpm_03200403.jpg","thumbnail_pic_s02":"http://09.imgmini.eastday.com/mobile/20171225/20171225072639_12382846780b78d97243ca14f5dee19a_4_mwpm_03200403.jpg","thumbnail_pic_s03":"http://09.imgmini.eastday.com/mobile/20171225/20171225072639_12382846780b78d97243ca14f5dee19a_3_mwpm_03200403.jpg"},{"uniquekey":"e863e332f3050d1335dd06ca8a95c795","title":"林郑月娥上任后第一次休假 一家四口坐高铁去桂林","date":"2017-12-25 07:22","category":"头条","author_name":"观察者网","url":"http://mini.eastday.com/mobile/171225072242828.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20171225/20171225072242_d1e208528ed4152ca35ff03836226263_1_mwpm_03200403.jpg"},{"uniquekey":"1af8740bb2e9c8892c866ce7f745d3ff","title":"美日又背后搞事：竟同时对中国这个邻居下手！对华极不利！","date":"2017-12-25 07:20","category":"头条","author_name":"大国军情","url":"http://mini.eastday.com/mobile/171225072012915.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20171225/20171225_43cf8a295f38ea65ccaa9c4c7dcce418_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://06.imgmini.eastday.com/mobile/20171225/20171225_9f0d2d0e6c52262d108ef1cc9478ac3a_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://06.imgmini.eastday.com/mobile/20171225/20171225_568adc47c027ba1ebe1c2cd5723b7271_cover_mwpm_03200403.jpg"},{"uniquekey":"02e27cdc2a8a5f442e3bfc57f15f757c","title":"全球10个皇室国家隆重奢华的婚礼，凯特王妃结婚花了3亿","date":"2017-12-25 07:18","category":"头条","author_name":"嗅怪猎奇","url":"http://mini.eastday.com/mobile/171225071843635.html","thumbnail_pic_s":"http://08.imgmini.eastday.com/mobile/20171225/20171225_4e95cb4fe86c5efc5ecfd7ffed0a4ddf_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://08.imgmini.eastday.com/mobile/20171225/20171225_b8a51bff33c58391466696604d4e24d7_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://08.imgmini.eastday.com/mobile/20171225/20171225_487881e76185c8618052a3fdd71630af_cover_mwpm_03200403.jpg"},{"uniquekey":"4a2ea9fa9c36b568a678accd559f956f","title":"伤亡惨重！巴铁发起新一轮反击，用炮火给印度送份圣诞大礼","date":"2017-12-25 07:02","category":"头条","author_name":"大国军情","url":"http://mini.eastday.com/mobile/171225070211208.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20171225/20171225_f0b1da7934716424570bf22716c9a483_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://02.imgmini.eastday.com/mobile/20171225/20171225_602b8e98cb83d1bde912b705a9e1d159_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://02.imgmini.eastday.com/mobile/20171225/20171225_eb89466611be301581711f08adc9ef0e_cover_mwpm_03200403.jpg"},{"uniquekey":"7b429de8832f28231012d03f6c3e2c60","title":"杜海涛直呼刘德华\u201c华仔\u201d!吴君如当场愣住了，网友:不知羞耻!","date":"2017-12-25 06:54","category":"头条","author_name":"小白电影报告","url":"http://mini.eastday.com/mobile/171225065418230.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20171225/20171225065418_b4b1648c899d50cc2450c4477e4b9f2e_5_mwpm_03200403.jpg","thumbnail_pic_s02":"http://07.imgmini.eastday.com/mobile/20171225/20171225065418_b4b1648c899d50cc2450c4477e4b9f2e_4_mwpm_03200403.jpg","thumbnail_pic_s03":"http://07.imgmini.eastday.com/mobile/20171225/20171225065418_b4b1648c899d50cc2450c4477e4b9f2e_3_mwpm_03200403.jpg"},{"uniquekey":"e9187cfc6bd88df84dae7cb7ee6c989d","title":"明天后菩萨庇护，事事顺心的三大生肖!","date":"2017-12-25 06:54","category":"头条","author_name":"糗事笑不停","url":"http://mini.eastday.com/mobile/171225065415507.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20171225/20171225065415_ab0bbc07a3a9e76b49b6f0cab179051a_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://03.imgmini.eastday.com/mobile/20171225/20171225065415_ab0bbc07a3a9e76b49b6f0cab179051a_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://03.imgmini.eastday.com/mobile/20171225/20171225065415_ab0bbc07a3a9e76b49b6f0cab179051a_1_mwpm_03200403.jpg"},{"uniquekey":"eeb72c9d3be3940b459da9e1c14a6d09","title":"十九大没怎么谈城镇化？其实，重要信息已发出，还提到一件\u201c国之大事\u201d","date":"2017-12-25 06:50","category":"头条","author_name":"解放网","url":"http://mini.eastday.com/mobile/171225065002277.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20171225/20171225065002_17f6ef2bdabd1c7f7e1af4543cdb1310_1_mwpm_03200403.jpg"},{"uniquekey":"76afaee62711624d407895c3573b0634","title":"拿到营业执照才能申请许可？上海探索证照联办，只需\u201c见一面\u201d","date":"2017-12-25 06:39","category":"头条","author_name":"解放网","url":"http://mini.eastday.com/mobile/171225063940822.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20171225/20171225063940_531edfd555016f283c6c276cc28191a7_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://05.imgmini.eastday.com/mobile/20171225/20171225063940_531edfd555016f283c6c276cc28191a7_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://05.imgmini.eastday.com/mobile/20171225/20171225063940_531edfd555016f283c6c276cc28191a7_1_mwpm_03200403.jpg"},{"uniquekey":"ed338d06119077a42d5512dfaa5fb176","title":"商界大佬又新增一位\u201c段子手\u201d：当了全球老大不过如此","date":"2017-12-25 06:35","category":"头条","author_name":"新闻早餐","url":"http://mini.eastday.com/mobile/171225063549948.html","thumbnail_pic_s":"http://08.imgmini.eastday.com/mobile/20171225/20171225_babb97841eaac696456d1672a8e07814_mwpm_03200403.jpg","thumbnail_pic_s02":"http://08.imgmini.eastday.com/mobile/20171225/20171225_56a3f7acd10648cdcd66752a127e15de_mwpm_03200403.jpg","thumbnail_pic_s03":"http://08.imgmini.eastday.com/mobile/20171225/20171225_9a4ca5d253d755caa35228f6f146e87b_mwpm_03200403.jpg"},{"uniquekey":"419e3a550b84a633842132e367cac1b1","title":"八大民主党派一个月完成换届","date":"2017-12-25 06:15","category":"头条","author_name":"中青在线","url":"http://mini.eastday.com/mobile/171225061536124.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20171225/20171225061536_6c11089f48acd60316f139add485e04e_1_mwpm_03200403.jpg"},{"uniquekey":"6be7e225d537c1cc4f8032e03b0fc052","title":"男子下班回家睡觉发现天花板有异样，上去查看后吓得马上去报警！","date":"2017-12-25 06:11","category":"头条","author_name":"圈内的事","url":"http://mini.eastday.com/mobile/171225061124799.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20171225/20171225061124_68eb56c0c9eeb2260294487a0ca84f15_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://09.imgmini.eastday.com/mobile/20171225/20171225061124_68eb56c0c9eeb2260294487a0ca84f15_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://09.imgmini.eastday.com/mobile/20171225/20171225061124_68eb56c0c9eeb2260294487a0ca84f15_2_mwpm_03200403.jpg"},{"uniquekey":"441b47a3d5fd697f27e5320ace4ad8e4","title":"乡下结婚当天耍床的种种奇葩事，让人哭笑不得，谁见过那种场面？","date":"2017-12-25 06:11","category":"头条","author_name":"沙漠雨","url":"http://mini.eastday.com/mobile/171225061123112.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20171225/20171225061123_77fc1f4c19bdd131e4e900eca6c465c0_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://07.imgmini.eastday.com/mobile/20171225/20171225061123_77fc1f4c19bdd131e4e900eca6c465c0_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://07.imgmini.eastday.com/mobile/20171225/20171225061123_77fc1f4c19bdd131e4e900eca6c465c0_3_mwpm_03200403.jpg"},{"uniquekey":"2be218ffd8738b0c4004075e39cc5d19","title":"为什么代人受过叫背锅，不叫背盘背盆背碗呢？","date":"2017-12-25 06:07","category":"头条","author_name":"多彩贵州网","url":"http://mini.eastday.com/mobile/171225060752437.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20171225/20171225060752_21715bb4f5236e4e25dfaa9aaeb41f4f_6_mwpm_03200403.jpg","thumbnail_pic_s02":"http://03.imgmini.eastday.com/mobile/20171225/20171225060752_21715bb4f5236e4e25dfaa9aaeb41f4f_8_mwpm_03200403.jpg","thumbnail_pic_s03":"http://03.imgmini.eastday.com/mobile/20171225/20171225060752_21715bb4f5236e4e25dfaa9aaeb41f4f_7_mwpm_03200403.jpg"},{"uniquekey":"f71b5a736bc14f5f532a54e2b329f13f","title":"俄总统新闻秘书：普京将在选举前发表国情咨文","date":"2017-12-25 06:05","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/171225060547992.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20171225/20171225060547_1c057b279cc1cf4326db72cdfcfc5ef6_1_mwpm_03200403.jpg"},{"uniquekey":"a937d1ef89627c0d1d53cb4b2b3b4fd2","title":"臀部核心根基基础力量加强训练:8个动作激活臀部提升训练质量","date":"2017-12-25 05:50","category":"头条","author_name":"91健身","url":"http://mini.eastday.com/mobile/171225055004377.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20171225/20171225055004_d553d256079de74275542eca30efa20f_11_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00.imgmini.eastday.com/mobile/20171225/20171225055004_d553d256079de74275542eca30efa20f_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00.imgmini.eastday.com/mobile/20171225/20171225055004_d553d256079de74275542eca30efa20f_6_mwpm_03200403.jpg"}]}
     * error_code : 0
     */

    private String reason;
    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {

        @Override
        public String toString() {
            return "ResultBean{" +
                    "stat='" + stat + '\'' +
                    ", data=" + data +
                    '}';
        }

        /**
         * stat : 1
         * data : [{"uniquekey":"13c6ce71350fd3ef925aa70adcdc651f","title":"演技才会让人气演员发光","date":"2017-12-25 08:43","category":"头条","author_name":"新华网","url":"http://mini.eastday.com/mobile/171225084306459.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20171225/20171225084306_7a5b2fc5a7fabc41ff8943f8eebb7290_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://09.imgmini.eastday.com/mobile/20171225/20171225084306_7a5b2fc5a7fabc41ff8943f8eebb7290_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://09.imgmini.eastday.com/mobile/20171225/20171225084306_7a5b2fc5a7fabc41ff8943f8eebb7290_1_mwpm_03200403.jpg"},{"uniquekey":"8cbb7b97c073bccf29d27d587f544b11","title":"郎朗上《吐槽大会2》一展歌喉\u201c吓坏\u201d网友","date":"2017-12-25 08:43","category":"头条","author_name":"新华网","url":"http://mini.eastday.com/mobile/171225084305480.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20171225/20171225084305_a0e987ba3dd3478b9b315cf97db48c78_1_mwpm_03200403.jpg"},{"uniquekey":"b63297d658dd572da61feec9ca6be3c3","title":"平安夜越南节日气氛浓厚，各地圣诞夜市人潮汹涌","date":"2017-12-25 08:17","category":"头条","author_name":"越南越美","url":"http://mini.eastday.com/mobile/171225081725146.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20171225/20171225081725_3e9094fbe58afa8fa1d0c29ed09eeaf5_8_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01.imgmini.eastday.com/mobile/20171225/20171225081725_3e9094fbe58afa8fa1d0c29ed09eeaf5_7_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01.imgmini.eastday.com/mobile/20171225/20171225081725_3e9094fbe58afa8fa1d0c29ed09eeaf5_3_mwpm_03200403.jpg"},{"uniquekey":"c8301b3fc7195fa8a6ff4a2e9945407a","title":"黄轩：《妖猫传》里白居易像个孩子","date":"2017-12-25 08:11","category":"头条","author_name":"新华网","url":"http://mini.eastday.com/mobile/171225081115970.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20171225/20171225081115_a2962ee334f3432bedbc6de79d23b907_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://06.imgmini.eastday.com/mobile/20171225/20171225081115_a2962ee334f3432bedbc6de79d23b907_1_mwpm_03200403.jpg"},{"uniquekey":"d3c0ffd2e922e72404b2a99fb1910d75","title":"上海这项排名又列全国第一 怪不得魔都各大医院一直挤破头","date":"2017-12-25 08:09","category":"头条","author_name":"上海社区","url":"http://mini.eastday.com/mobile/171225080954432.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20171225/20171225080954_fbe24ca05f300c0dc7a75a5ea12a4142_11_mwpm_03200403.jpg","thumbnail_pic_s02":"http://03.imgmini.eastday.com/mobile/20171225/20171225080954_29892a291f1fd6fe97dafe64c82cbff3_9_mwpm_03200403.jpg","thumbnail_pic_s03":"http://03.imgmini.eastday.com/mobile/20171225/20171225080954_ced6d566c4b0dbae0210d328829043f7_1_mwpm_03200403.jpg"},{"uniquekey":"1eba85a5069448d6465e8279b0d9c9a5","title":"俄美女总统候选人参加活动 笑容满面颜值高","date":"2017-12-25 07:57","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/171225075745724.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20171225/20171225075745_7d4e6c69678b26471be80c78d6c4cc48_4_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00.imgmini.eastday.com/mobile/20171225/20171225075745_7d4e6c69678b26471be80c78d6c4cc48_5_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00.imgmini.eastday.com/mobile/20171225/20171225075745_7d4e6c69678b26471be80c78d6c4cc48_6_mwpm_03200403.jpg"},{"uniquekey":"fe092ddb6d4f238783685199d8e4d0f7","title":"郑建邦获任民革中央常务副主席","date":"2017-12-25 07:51","category":"头条","author_name":"澎湃新闻网","url":"http://mini.eastday.com/mobile/171225075105448.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20171225/20171225075105_64e69e3337e9bcc0ba9dc7bacd9565f0_1_mwpm_03200403.jpg"},{"uniquekey":"fa5ea92c8064103d6716482ff1ebd43a","title":"菲律宾大火零存活率 杜特尔特视察落泪","date":"2017-12-25 07:47","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/171225074736660.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20171225/20171225074736_ae6fdf16312e04638b4fe44aa48ff5ad_1_mwpm_03200403.jpg"},{"uniquekey":"60a163792eb237b59601bac16c1f12e1","title":"黎巴嫩一司机涉嫌强奸英国女外交官 并将其杀害","date":"2017-12-25 07:47","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/171225074736542.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20171225/20171225074736_3d78c06bbd18c571c67ae25d1abdd939_1_mwpm_03200403.jpg"},{"uniquekey":"103ffd25abe837f014050ed05cb1857b","title":"做好战争准备！美防长刚刚放狠话，这回似乎真要动武？","date":"2017-12-25 07:40","category":"头条","author_name":"第一军情","url":"http://mini.eastday.com/mobile/171225074059149.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20171225/20171225_fb1ad2ac32269e23131a9adb87921a78_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01.imgmini.eastday.com/mobile/20171225/20171225_f198c1b027637311d2dade2ee9bb7753_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01.imgmini.eastday.com/mobile/20171225/20171225_01483e0e307a2429d2bb7b17c0fcb4f6_cover_mwpm_03200403.jpg"},{"uniquekey":"03ea27e19f207482e65c7134fc671668","title":"这个地方全面禁烟，私藏烟草蹲大牢!网友表示:国内这样烟早戒了","date":"2017-12-25 07:37","category":"头条","author_name":"飞行的狗狗","url":"http://mini.eastday.com/mobile/171225073721788.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20171225/20171225073721_f2d2d2bfbd5485193e3360c11fd83b01_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01.imgmini.eastday.com/mobile/20171225/20171225073721_f2d2d2bfbd5485193e3360c11fd83b01_6_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01.imgmini.eastday.com/mobile/20171225/20171225073721_f2d2d2bfbd5485193e3360c11fd83b01_5_mwpm_03200403.jpg"},{"uniquekey":"d92643e08d8ada8263b969e52ca555c5","title":"美国最新表态曝光：这两句话里竟暗藏动手信号！半岛局势不容乐观","date":"2017-12-25 07:34","category":"头条","author_name":"大国军情","url":"http://mini.eastday.com/mobile/171225073416798.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20171225/20171225_2cf93b7a9d978cdcaaecad42a4d9d3cc_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://06.imgmini.eastday.com/mobile/20171225/20171225_8ced19167b9731c18d2a9376e4d9bf79_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://06.imgmini.eastday.com/mobile/20171225/20171225_1213e7d93377f1c1d145911608bc6a17_cover_mwpm_03200403.jpg"},{"uniquekey":"2c9a38d1c7001d267e3741e62d4f0cc9","title":"最新爆料：日本终于要迈出最危险一步！这次连遮羞布都不要了","date":"2017-12-25 07:31","category":"头条","author_name":"大国军情","url":"http://mini.eastday.com/mobile/171225073145253.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20171225/20171225_bd3c5bb3b6e3ea00930b3a687515de81_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://09.imgmini.eastday.com/mobile/20171225/20171225_2d89d20f23631479d8f837465248d5e0_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://09.imgmini.eastday.com/mobile/20171225/20171225_d7adbba7e84b4990f22e57b6f1ba100d_cover_mwpm_03200403.jpg"},{"uniquekey":"de0d7cafba0304fa16ee3562ccff5350","title":"半岛局势又升级：一个军事强国高调插足！最新表态却让特朗普发懵","date":"2017-12-25 07:28","category":"头条","author_name":"大国军情","url":"http://mini.eastday.com/mobile/171225072805669.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20171225/20171225_d0f8721796c77cf8c1d622855ab358a4_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01.imgmini.eastday.com/mobile/20171225/20171225_b3dfadadb9edf25b484a384afc7ae616_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01.imgmini.eastday.com/mobile/20171225/20171225_7a00929218648c171c27b991f6322a3d_cover_mwpm_03200403.jpg"},{"uniquekey":"8995cbdc3b65309f4d65ee479a5c24cf","title":"中国嫦娥四号将上月球种土豆!日本慌了!宣布要登上月球!","date":"2017-12-25 07:26","category":"头条","author_name":"中国气象爱好者","url":"http://mini.eastday.com/mobile/171225072639105.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20171225/20171225072639_12382846780b78d97243ca14f5dee19a_5_mwpm_03200403.jpg","thumbnail_pic_s02":"http://09.imgmini.eastday.com/mobile/20171225/20171225072639_12382846780b78d97243ca14f5dee19a_4_mwpm_03200403.jpg","thumbnail_pic_s03":"http://09.imgmini.eastday.com/mobile/20171225/20171225072639_12382846780b78d97243ca14f5dee19a_3_mwpm_03200403.jpg"},{"uniquekey":"e863e332f3050d1335dd06ca8a95c795","title":"林郑月娥上任后第一次休假 一家四口坐高铁去桂林","date":"2017-12-25 07:22","category":"头条","author_name":"观察者网","url":"http://mini.eastday.com/mobile/171225072242828.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20171225/20171225072242_d1e208528ed4152ca35ff03836226263_1_mwpm_03200403.jpg"},{"uniquekey":"1af8740bb2e9c8892c866ce7f745d3ff","title":"美日又背后搞事：竟同时对中国这个邻居下手！对华极不利！","date":"2017-12-25 07:20","category":"头条","author_name":"大国军情","url":"http://mini.eastday.com/mobile/171225072012915.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20171225/20171225_43cf8a295f38ea65ccaa9c4c7dcce418_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://06.imgmini.eastday.com/mobile/20171225/20171225_9f0d2d0e6c52262d108ef1cc9478ac3a_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://06.imgmini.eastday.com/mobile/20171225/20171225_568adc47c027ba1ebe1c2cd5723b7271_cover_mwpm_03200403.jpg"},{"uniquekey":"02e27cdc2a8a5f442e3bfc57f15f757c","title":"全球10个皇室国家隆重奢华的婚礼，凯特王妃结婚花了3亿","date":"2017-12-25 07:18","category":"头条","author_name":"嗅怪猎奇","url":"http://mini.eastday.com/mobile/171225071843635.html","thumbnail_pic_s":"http://08.imgmini.eastday.com/mobile/20171225/20171225_4e95cb4fe86c5efc5ecfd7ffed0a4ddf_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://08.imgmini.eastday.com/mobile/20171225/20171225_b8a51bff33c58391466696604d4e24d7_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://08.imgmini.eastday.com/mobile/20171225/20171225_487881e76185c8618052a3fdd71630af_cover_mwpm_03200403.jpg"},{"uniquekey":"4a2ea9fa9c36b568a678accd559f956f","title":"伤亡惨重！巴铁发起新一轮反击，用炮火给印度送份圣诞大礼","date":"2017-12-25 07:02","category":"头条","author_name":"大国军情","url":"http://mini.eastday.com/mobile/171225070211208.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20171225/20171225_f0b1da7934716424570bf22716c9a483_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://02.imgmini.eastday.com/mobile/20171225/20171225_602b8e98cb83d1bde912b705a9e1d159_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://02.imgmini.eastday.com/mobile/20171225/20171225_eb89466611be301581711f08adc9ef0e_cover_mwpm_03200403.jpg"},{"uniquekey":"7b429de8832f28231012d03f6c3e2c60","title":"杜海涛直呼刘德华\u201c华仔\u201d!吴君如当场愣住了，网友:不知羞耻!","date":"2017-12-25 06:54","category":"头条","author_name":"小白电影报告","url":"http://mini.eastday.com/mobile/171225065418230.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20171225/20171225065418_b4b1648c899d50cc2450c4477e4b9f2e_5_mwpm_03200403.jpg","thumbnail_pic_s02":"http://07.imgmini.eastday.com/mobile/20171225/20171225065418_b4b1648c899d50cc2450c4477e4b9f2e_4_mwpm_03200403.jpg","thumbnail_pic_s03":"http://07.imgmini.eastday.com/mobile/20171225/20171225065418_b4b1648c899d50cc2450c4477e4b9f2e_3_mwpm_03200403.jpg"},{"uniquekey":"e9187cfc6bd88df84dae7cb7ee6c989d","title":"明天后菩萨庇护，事事顺心的三大生肖!","date":"2017-12-25 06:54","category":"头条","author_name":"糗事笑不停","url":"http://mini.eastday.com/mobile/171225065415507.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20171225/20171225065415_ab0bbc07a3a9e76b49b6f0cab179051a_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://03.imgmini.eastday.com/mobile/20171225/20171225065415_ab0bbc07a3a9e76b49b6f0cab179051a_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://03.imgmini.eastday.com/mobile/20171225/20171225065415_ab0bbc07a3a9e76b49b6f0cab179051a_1_mwpm_03200403.jpg"},{"uniquekey":"eeb72c9d3be3940b459da9e1c14a6d09","title":"十九大没怎么谈城镇化？其实，重要信息已发出，还提到一件\u201c国之大事\u201d","date":"2017-12-25 06:50","category":"头条","author_name":"解放网","url":"http://mini.eastday.com/mobile/171225065002277.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20171225/20171225065002_17f6ef2bdabd1c7f7e1af4543cdb1310_1_mwpm_03200403.jpg"},{"uniquekey":"76afaee62711624d407895c3573b0634","title":"拿到营业执照才能申请许可？上海探索证照联办，只需\u201c见一面\u201d","date":"2017-12-25 06:39","category":"头条","author_name":"解放网","url":"http://mini.eastday.com/mobile/171225063940822.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20171225/20171225063940_531edfd555016f283c6c276cc28191a7_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://05.imgmini.eastday.com/mobile/20171225/20171225063940_531edfd555016f283c6c276cc28191a7_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://05.imgmini.eastday.com/mobile/20171225/20171225063940_531edfd555016f283c6c276cc28191a7_1_mwpm_03200403.jpg"},{"uniquekey":"ed338d06119077a42d5512dfaa5fb176","title":"商界大佬又新增一位\u201c段子手\u201d：当了全球老大不过如此","date":"2017-12-25 06:35","category":"头条","author_name":"新闻早餐","url":"http://mini.eastday.com/mobile/171225063549948.html","thumbnail_pic_s":"http://08.imgmini.eastday.com/mobile/20171225/20171225_babb97841eaac696456d1672a8e07814_mwpm_03200403.jpg","thumbnail_pic_s02":"http://08.imgmini.eastday.com/mobile/20171225/20171225_56a3f7acd10648cdcd66752a127e15de_mwpm_03200403.jpg","thumbnail_pic_s03":"http://08.imgmini.eastday.com/mobile/20171225/20171225_9a4ca5d253d755caa35228f6f146e87b_mwpm_03200403.jpg"},{"uniquekey":"419e3a550b84a633842132e367cac1b1","title":"八大民主党派一个月完成换届","date":"2017-12-25 06:15","category":"头条","author_name":"中青在线","url":"http://mini.eastday.com/mobile/171225061536124.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20171225/20171225061536_6c11089f48acd60316f139add485e04e_1_mwpm_03200403.jpg"},{"uniquekey":"6be7e225d537c1cc4f8032e03b0fc052","title":"男子下班回家睡觉发现天花板有异样，上去查看后吓得马上去报警！","date":"2017-12-25 06:11","category":"头条","author_name":"圈内的事","url":"http://mini.eastday.com/mobile/171225061124799.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20171225/20171225061124_68eb56c0c9eeb2260294487a0ca84f15_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://09.imgmini.eastday.com/mobile/20171225/20171225061124_68eb56c0c9eeb2260294487a0ca84f15_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://09.imgmini.eastday.com/mobile/20171225/20171225061124_68eb56c0c9eeb2260294487a0ca84f15_2_mwpm_03200403.jpg"},{"uniquekey":"441b47a3d5fd697f27e5320ace4ad8e4","title":"乡下结婚当天耍床的种种奇葩事，让人哭笑不得，谁见过那种场面？","date":"2017-12-25 06:11","category":"头条","author_name":"沙漠雨","url":"http://mini.eastday.com/mobile/171225061123112.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20171225/20171225061123_77fc1f4c19bdd131e4e900eca6c465c0_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://07.imgmini.eastday.com/mobile/20171225/20171225061123_77fc1f4c19bdd131e4e900eca6c465c0_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://07.imgmini.eastday.com/mobile/20171225/20171225061123_77fc1f4c19bdd131e4e900eca6c465c0_3_mwpm_03200403.jpg"},{"uniquekey":"2be218ffd8738b0c4004075e39cc5d19","title":"为什么代人受过叫背锅，不叫背盘背盆背碗呢？","date":"2017-12-25 06:07","category":"头条","author_name":"多彩贵州网","url":"http://mini.eastday.com/mobile/171225060752437.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20171225/20171225060752_21715bb4f5236e4e25dfaa9aaeb41f4f_6_mwpm_03200403.jpg","thumbnail_pic_s02":"http://03.imgmini.eastday.com/mobile/20171225/20171225060752_21715bb4f5236e4e25dfaa9aaeb41f4f_8_mwpm_03200403.jpg","thumbnail_pic_s03":"http://03.imgmini.eastday.com/mobile/20171225/20171225060752_21715bb4f5236e4e25dfaa9aaeb41f4f_7_mwpm_03200403.jpg"},{"uniquekey":"f71b5a736bc14f5f532a54e2b329f13f","title":"俄总统新闻秘书：普京将在选举前发表国情咨文","date":"2017-12-25 06:05","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/171225060547992.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20171225/20171225060547_1c057b279cc1cf4326db72cdfcfc5ef6_1_mwpm_03200403.jpg"},{"uniquekey":"a937d1ef89627c0d1d53cb4b2b3b4fd2","title":"臀部核心根基基础力量加强训练:8个动作激活臀部提升训练质量","date":"2017-12-25 05:50","category":"头条","author_name":"91健身","url":"http://mini.eastday.com/mobile/171225055004377.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20171225/20171225055004_d553d256079de74275542eca30efa20f_11_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00.imgmini.eastday.com/mobile/20171225/20171225055004_d553d256079de74275542eca30efa20f_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00.imgmini.eastday.com/mobile/20171225/20171225055004_d553d256079de74275542eca30efa20f_6_mwpm_03200403.jpg"}]
         */

        private String stat;
        private List<DataBean> data;

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {

            @Override
            public String toString() {
                return "DataBean{" +
                        "uniquekey='" + uniquekey + '\'' +
                        ", title='" + title + '\'' +
                        ", date='" + date + '\'' +
                        ", category='" + category + '\'' +
                        ", author_name='" + author_name + '\'' +
                        ", url='" + url + '\'' +
                        ", thumbnail_pic_s='" + thumbnail_pic_s + '\'' +
                        ", thumbnail_pic_s02='" + thumbnail_pic_s02 + '\'' +
                        ", thumbnail_pic_s03='" + thumbnail_pic_s03 + '\'' +
                        '}';
            }

            /**
             * uniquekey : 13c6ce71350fd3ef925aa70adcdc651f
             * title : 演技才会让人气演员发光
             * date : 2017-12-25 08:43
             * category : 头条
             * author_name : 新华网
             * url : http://mini.eastday.com/mobile/171225084306459.html
             * thumbnail_pic_s : http://09.imgmini.eastday.com/mobile/20171225/20171225084306_7a5b2fc5a7fabc41ff8943f8eebb7290_2_mwpm_03200403.jpg
             * thumbnail_pic_s02 : http://09.imgmini.eastday.com/mobile/20171225/20171225084306_7a5b2fc5a7fabc41ff8943f8eebb7290_3_mwpm_03200403.jpg
             * thumbnail_pic_s03 : http://09.imgmini.eastday.com/mobile/20171225/20171225084306_7a5b2fc5a7fabc41ff8943f8eebb7290_1_mwpm_03200403.jpg
             */

            private String uniquekey;
            private String title;
            private String date;
            private String category;
            private String author_name;
            private String url;
            private String thumbnail_pic_s;
            private String thumbnail_pic_s02;
            private String thumbnail_pic_s03;

            public String getUniquekey() {
                return uniquekey;
            }

            public void setUniquekey(String uniquekey) {
                this.uniquekey = uniquekey;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getAuthor_name() {
                return author_name;
            }

            public void setAuthor_name(String author_name) {
                this.author_name = author_name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getThumbnail_pic_s() {
                return thumbnail_pic_s;
            }

            public void setThumbnail_pic_s(String thumbnail_pic_s) {
                this.thumbnail_pic_s = thumbnail_pic_s;
            }

            public String getThumbnail_pic_s02() {
                return thumbnail_pic_s02;
            }

            public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
                this.thumbnail_pic_s02 = thumbnail_pic_s02;
            }

            public String getThumbnail_pic_s03() {
                return thumbnail_pic_s03;
            }

            public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
                this.thumbnail_pic_s03 = thumbnail_pic_s03;
            }
        }
    }
}
