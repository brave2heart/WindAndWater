package com.tongcheng.soothsay.bean.huangli;

import java.util.List;

/**
 * Created by Steven on 16/11/3.
 */

public class TodayWeatherBean {


    /**
     * reason : 查询成功
     * result : {"data":{"realtime":{"city_code":"101210701","city_name":"温州","date":"2014-10-15","time":"09:00:00","week":3,"moon":"九月廿二","dataUptime":1413337811,"weather":{"temperature":"19","humidity":"54","info":"雾","img":"18"},"wind":{"direct":"北风","power":"1级","offset":null,"windspeed":null}},"life":{"date":"2014-10-15","info":{"chuanyi":["较舒适","建议着薄外套或牛仔衫裤等服装。年老体弱者宜着夹克衫、薄毛衣等。昼夜温差较大，注意适当增减衣服。"],"ganmao":["较易发","昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。"],"kongtiao":["较少开启","您将感到很舒适，一般不需要开启空调。"],"wuran":["良","气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。"],"xiche":["较适宜","较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"],"yundong":["较适宜","天气较好，但风力较大，推荐您进行室内运动，若在户外运动请注意防风。"],"ziwaixian":["中等","属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"]}},"date":null,"isForeign":0}}
     * error_code : 0
     */

    private String reason;
    /**
     * data : {"realtime":{"city_code":"101210701","city_name":"温州","date":"2014-10-15","time":"09:00:00","week":3,"moon":"九月廿二","dataUptime":1413337811,"weather":{"temperature":"19","humidity":"54","info":"雾","img":"18"},"wind":{"direct":"北风","power":"1级","offset":null,"windspeed":null}},"life":{"date":"2014-10-15","info":{"chuanyi":["较舒适","建议着薄外套或牛仔衫裤等服装。年老体弱者宜着夹克衫、薄毛衣等。昼夜温差较大，注意适当增减衣服。"],"ganmao":["较易发","昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。"],"kongtiao":["较少开启","您将感到很舒适，一般不需要开启空调。"],"wuran":["良","气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。"],"xiche":["较适宜","较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"],"yundong":["较适宜","天气较好，但风力较大，推荐您进行室内运动，若在户外运动请注意防风。"],"ziwaixian":["中等","属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"]}},"date":null,"isForeign":0}
     */

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
        /**
         * realtime : {"city_code":"101210701","city_name":"温州","date":"2014-10-15","time":"09:00:00","week":3,"moon":"九月廿二","dataUptime":1413337811,"weather":{"temperature":"19","humidity":"54","info":"雾","img":"18"},"wind":{"direct":"北风","power":"1级","offset":null,"windspeed":null}}
         * life : {"date":"2014-10-15","info":{"chuanyi":["较舒适","建议着薄外套或牛仔衫裤等服装。年老体弱者宜着夹克衫、薄毛衣等。昼夜温差较大，注意适当增减衣服。"],"ganmao":["较易发","昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。"],"kongtiao":["较少开启","您将感到很舒适，一般不需要开启空调。"],"wuran":["良","气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。"],"xiche":["较适宜","较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"],"yundong":["较适宜","天气较好，但风力较大，推荐您进行室内运动，若在户外运动请注意防风。"],"ziwaixian":["中等","属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"]}}
         * date : null
         * isForeign : 0
         */

        private DataBean data;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * city_code : 101210701
             * city_name : 温州
             * date : 2014-10-15
             * time : 09:00:00
             * week : 3
             * moon : 九月廿二
             * dataUptime : 1413337811
             * weather : {"temperature":"19","humidity":"54","info":"雾","img":"18"}
             * wind : {"direct":"北风","power":"1级","offset":null,"windspeed":null}
             */

            private RealtimeBean realtime;
            /**
             * date : 2014-10-15
             * info : {"chuanyi":["较舒适","建议着薄外套或牛仔衫裤等服装。年老体弱者宜着夹克衫、薄毛衣等。昼夜温差较大，注意适当增减衣服。"],"ganmao":["较易发","昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。"],"kongtiao":["较少开启","您将感到很舒适，一般不需要开启空调。"],"wuran":["良","气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。"],"xiche":["较适宜","较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"],"yundong":["较适宜","天气较好，但风力较大，推荐您进行室内运动，若在户外运动请注意防风。"],"ziwaixian":["中等","属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"]}
             */

            private LifeBean life;


            private List<FutureWeatherBean> weather;
            private Object date;
            private int isForeign;

            public List<FutureWeatherBean> getFutureWeather() {
                return weather;
            }

            public void setFutureWeather(List<FutureWeatherBean> futureWeather) {
                this.weather = futureWeather;
            }

            public RealtimeBean getRealtime() {
                return realtime;
            }

            public void setRealtime(RealtimeBean realtime) {
                this.realtime = realtime;
            }

            public LifeBean getLife() {
                return life;
            }

            public void setLife(LifeBean life) {
                this.life = life;
            }

            public Object getDate() {
                return date;
            }

            public void setDate(Object date) {
                this.date = date;
            }

            public int getIsForeign() {
                return isForeign;
            }

            public void setIsForeign(int isForeign) {
                this.isForeign = isForeign;
            }

            public static class RealtimeBean {
                private String city_code;
                private String city_name;
                private String date;
                private String time;
                private int week;
                private String moon;
                private int dataUptime;
                /**
                 * temperature : 19
                 * humidity : 54
                 * info : 雾
                 * img : 18
                 */

                private WeatherBean weather;
                /**
                 * direct : 北风
                 * power : 1级
                 * offset : null
                 * windspeed : null
                 */

                private WindBean wind;

                public String getCity_code() {
                    return city_code;
                }

                public void setCity_code(String city_code) {
                    this.city_code = city_code;
                }

                public String getCity_name() {
                    return city_name;
                }

                public void setCity_name(String city_name) {
                    this.city_name = city_name;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public int getWeek() {
                    return week;
                }

                public void setWeek(int week) {
                    this.week = week;
                }

                public String getMoon() {
                    return moon;
                }

                public void setMoon(String moon) {
                    this.moon = moon;
                }

                public int getDataUptime() {
                    return dataUptime;
                }

                public void setDataUptime(int dataUptime) {
                    this.dataUptime = dataUptime;
                }

                public WeatherBean getWeather() {
                    return weather;
                }

                public void setWeather(WeatherBean weather) {
                    this.weather = weather;
                }

                public WindBean getWind() {
                    return wind;
                }

                public void setWind(WindBean wind) {
                    this.wind = wind;
                }

                public static class WeatherBean {
                    private String temperature;
                    private String humidity;
                    private String info;
                    private String img;

                    public String getTemperature() {
                        return temperature;
                    }

                    public void setTemperature(String temperature) {
                        this.temperature = temperature;
                    }

                    public String getHumidity() {
                        return humidity;
                    }

                    public void setHumidity(String humidity) {
                        this.humidity = humidity;
                    }

                    public String getInfo() {
                        return info;
                    }

                    public void setInfo(String info) {
                        this.info = info;
                    }

                    public String getImg() {
                        return img;
                    }

                    public void setImg(String img) {
                        this.img = img;
                    }
                }

                public static class WindBean {
                    private String direct;
                    private String power;
                    private Object offset;
                    private Object windspeed;

                    public String getDirect() {
                        return direct;
                    }

                    public void setDirect(String direct) {
                        this.direct = direct;
                    }

                    public String getPower() {
                        return power;
                    }

                    public void setPower(String power) {
                        this.power = power;
                    }

                    public Object getOffset() {
                        return offset;
                    }

                    public void setOffset(Object offset) {
                        this.offset = offset;
                    }

                    public Object getWindspeed() {
                        return windspeed;
                    }

                    public void setWindspeed(Object windspeed) {
                        this.windspeed = windspeed;
                    }
                }
            }

            public static class LifeBean {
                private String date;
                private InfoBean info;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public InfoBean getInfo() {
                    return info;
                }

                public void setInfo(InfoBean info) {
                    this.info = info;
                }

                public static class InfoBean {
                    private List<String> chuanyi;
                    private List<String> ganmao;
                    private List<String> kongtiao;
                    private List<String> wuran;
                    private List<String> xiche;
                    private List<String> yundong;
                    private List<String> ziwaixian;

                    public List<String> getChuanyi() {
                        return chuanyi;
                    }

                    public void setChuanyi(List<String> chuanyi) {
                        this.chuanyi = chuanyi;
                    }

                    public List<String> getGanmao() {
                        return ganmao;
                    }

                    public void setGanmao(List<String> ganmao) {
                        this.ganmao = ganmao;
                    }

                    public List<String> getKongtiao() {
                        return kongtiao;
                    }

                    public void setKongtiao(List<String> kongtiao) {
                        this.kongtiao = kongtiao;
                    }

                    public List<String> getWuran() {
                        return wuran;
                    }

                    public void setWuran(List<String> wuran) {
                        this.wuran = wuran;
                    }

                    public List<String> getXiche() {
                        return xiche;
                    }

                    public void setXiche(List<String> xiche) {
                        this.xiche = xiche;
                    }

                    public List<String> getYundong() {
                        return yundong;
                    }

                    public void setYundong(List<String> yundong) {
                        this.yundong = yundong;
                    }

                    public List<String> getZiwaixian() {
                        return ziwaixian;
                    }

                    public void setZiwaixian(List<String> ziwaixian) {
                        this.ziwaixian = ziwaixian;
                    }
                }
            }
        }
    }
}
