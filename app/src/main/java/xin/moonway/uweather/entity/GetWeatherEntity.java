package xin.moonway.uweather.entity;

import java.util.List;

/**
 *
 * Created by 辽科大_Moonway on 2017/11/21.
 * <p>
 * https://github.com/moonway0127
 */
public class GetWeatherEntity {


    /**
     * date : 20171121
     * message : Success !
     * status : 200
     * city : 鞍山市
     * count : 3
     * data : {"shidu":"42%","pm25":28,"pm10":46,"quality":"优","wendu":"-3","ganmao":"各类人群可自由活动","yesterday":{"date":"20日星期一","sunrise":"06:40","high":"高温 4.0℃","low":"低温 -3.0℃","sunset":"16:26","aqi":38,"fx":"南风","fl":"3-4级","type":"晴","notice":"晴空万里，去沐浴阳光吧"},"forecast":[{"date":"21日星期二","sunrise":"06:41","high":"高温 10.0℃","low":"低温 -6.0℃","sunset":"16:25","aqi":68,"fx":"南风","fl":"3-4级","type":"多云","notice":"悠悠的云里有淡淡的诗"},{"date":"22日星期三","sunrise":"06:42","high":"高温 0.0℃","low":"低温 -7.0℃","sunset":"16:25","aqi":81,"fx":"北风","fl":"3-4级","type":"晴","notice":"天气干燥，请适当增加室内湿度"},{"date":"23日星期四","sunrise":"06:44","high":"高温 4.0℃","low":"低温 -7.0℃","sunset":"16:24","aqi":100,"fx":"北风","fl":"3-4级","type":"晴","notice":"晴空万里，去沐浴阳光吧"},{"date":"24日星期五","sunrise":"06:45","high":"高温 1.0℃","low":"低温 -6.0℃","sunset":"16:24","aqi":97,"fx":"北风","fl":"3-4级","type":"晴","notice":"晴空万里，去沐浴阳光吧"},{"date":"25日星期六","sunrise":"06:46","high":"高温 5.0℃","low":"低温 -7.0℃","sunset":"16:23","aqi":107,"fx":"南风","fl":"3-4级","type":"多云","notice":"绵绵的云朵，形状千变万化"}]}
     */

    private String date;
    private String message;
    private int status;
    private String city;
    private int count;
    private DataBean data;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * shidu : 42%
         * pm25 : 28.0
         * pm10 : 46.0
         * quality : 优
         * wendu : -3
         * ganmao : 各类人群可自由活动
         * yesterday : {"date":"20日星期一","sunrise":"06:40","high":"高温 4.0℃","low":"低温 -3.0℃","sunset":"16:26","aqi":38,"fx":"南风","fl":"3-4级","type":"晴","notice":"晴空万里，去沐浴阳光吧"}
         * forecast : [{"date":"21日星期二","sunrise":"06:41","high":"高温 10.0℃","low":"低温 -6.0℃","sunset":"16:25","aqi":68,"fx":"南风","fl":"3-4级","type":"多云","notice":"悠悠的云里有淡淡的诗"},{"date":"22日星期三","sunrise":"06:42","high":"高温 0.0℃","low":"低温 -7.0℃","sunset":"16:25","aqi":81,"fx":"北风","fl":"3-4级","type":"晴","notice":"天气干燥，请适当增加室内湿度"},{"date":"23日星期四","sunrise":"06:44","high":"高温 4.0℃","low":"低温 -7.0℃","sunset":"16:24","aqi":100,"fx":"北风","fl":"3-4级","type":"晴","notice":"晴空万里，去沐浴阳光吧"},{"date":"24日星期五","sunrise":"06:45","high":"高温 1.0℃","low":"低温 -6.0℃","sunset":"16:24","aqi":97,"fx":"北风","fl":"3-4级","type":"晴","notice":"晴空万里，去沐浴阳光吧"},{"date":"25日星期六","sunrise":"06:46","high":"高温 5.0℃","low":"低温 -7.0℃","sunset":"16:23","aqi":107,"fx":"南风","fl":"3-4级","type":"多云","notice":"绵绵的云朵，形状千变万化"}]
         */

        private String shidu;
        private double pm25;
        private double pm10;
        private String quality;
        private String wendu;
        private String ganmao;
        private YesterdayBean yesterday;
        private List<ForecastBean> forecast;

        public String getShidu() {
            return shidu;
        }

        public void setShidu(String shidu) {
            this.shidu = shidu;
        }

        public double getPm25() {
            return pm25;
        }

        public void setPm25(double pm25) {
            this.pm25 = pm25;
        }

        public double getPm10() {
            return pm10;
        }

        public void setPm10(double pm10) {
            this.pm10 = pm10;
        }

        public String getQuality() {
            return quality;
        }

        public void setQuality(String quality) {
            this.quality = quality;
        }

        public String getWendu() {
            return wendu;
        }

        public void setWendu(String wendu) {
            this.wendu = wendu;
        }

        public String getGanmao() {
            return ganmao;
        }

        public void setGanmao(String ganmao) {
            this.ganmao = ganmao;
        }

        public YesterdayBean getYesterday() {
            return yesterday;
        }

        public void setYesterday(YesterdayBean yesterday) {
            this.yesterday = yesterday;
        }

        public List<ForecastBean> getForecast() {
            return forecast;
        }

        public void setForecast(List<ForecastBean> forecast) {
            this.forecast = forecast;
        }

        public static class YesterdayBean {
            /**
             * date : 20日星期一
             * sunrise : 06:40
             * high : 高温 4.0℃
             * low : 低温 -3.0℃
             * sunset : 16:26
             * aqi : 38.0
             * fx : 南风
             * fl : 3-4级
             * type : 晴
             * notice : 晴空万里，去沐浴阳光吧
             */

            private String date;
            private String sunrise;
            private String high;
            private String low;
            private String sunset;
            private double aqi;
            private String fx;
            private String fl;
            private String type;
            private String notice;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getSunrise() {
                return sunrise;
            }

            public void setSunrise(String sunrise) {
                this.sunrise = sunrise;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getSunset() {
                return sunset;
            }

            public void setSunset(String sunset) {
                this.sunset = sunset;
            }

            public double getAqi() {
                return aqi;
            }

            public void setAqi(double aqi) {
                this.aqi = aqi;
            }

            public String getFx() {
                return fx;
            }

            public void setFx(String fx) {
                this.fx = fx;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }
        }

        public static class ForecastBean {
            /**
             * date : 21日星期二
             * sunrise : 06:41
             * high : 高温 10.0℃
             * low : 低温 -6.0℃
             * sunset : 16:25
             * aqi : 68.0
             * fx : 南风
             * fl : 3-4级
             * type : 多云
             * notice : 悠悠的云里有淡淡的诗
             */

            private String date;
            private String sunrise;
            private String high;
            private String low;
            private String sunset;
            private double aqi;
            private String fx;
            private String fl;
            private String type;
            private String notice;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getSunrise() {
                return sunrise;
            }

            public void setSunrise(String sunrise) {
                this.sunrise = sunrise;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getSunset() {
                return sunset;
            }

            public void setSunset(String sunset) {
                this.sunset = sunset;
            }

            public double getAqi() {
                return aqi;
            }

            public void setAqi(double aqi) {
                this.aqi = aqi;
            }

            public String getFx() {
                return fx;
            }

            public void setFx(String fx) {
                this.fx = fx;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }
        }
    }
}
