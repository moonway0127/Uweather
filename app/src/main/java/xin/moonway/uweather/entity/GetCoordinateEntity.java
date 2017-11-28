package xin.moonway.uweather.entity;

/**
 *
 * Created by 辽科大_Moonway on 2017/11/21.
 * <p>
 * https://github.com/moonway0127
 */
public class GetCoordinateEntity {

    /**
     * status : 0
     * result : {"location":{"lng":116.30815063007148,"lat":40.056890127931275},"precise":1,"confidence":80,"level":"道路"}
     */

    private int status;
    private ResultBean result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * location : {"lng":116.30815063007148,"lat":40.056890127931275}
         * precise : 1
         * confidence : 80
         * level : 道路
         */

        private LocationBean location;
        private int precise;
        private int confidence;
        private String level;

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public int getPrecise() {
            return precise;
        }

        public void setPrecise(int precise) {
            this.precise = precise;
        }

        public int getConfidence() {
            return confidence;
        }

        public void setConfidence(int confidence) {
            this.confidence = confidence;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public static class LocationBean {
            /**
             * lng : 116.30815063007148
             * lat : 40.056890127931275
             */

            private double lng;
            private double lat;

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }
        }
    }
}
