package xin.moonway.uweather.dao;

/**
 *
 * Created by 辽科大_Moonway on 2017/9/16.
 *
 * https://github.com/moonway0127
 */
public class ConfigDao {

    private static ConfigDao sInstance;
    // 创建
    public static ConfigDao getInstance() {
        if (sInstance == null) {
            synchronized (ConfigDao.class) {
                if (sInstance == null) {
                    sInstance = new ConfigDao();
                }
            }
        }
        return sInstance;
    }
}
