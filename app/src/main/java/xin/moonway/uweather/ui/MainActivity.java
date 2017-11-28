package xin.moonway.uweather.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mobvoi.speech.SpeechClient;
import com.mobvoi.speech.SpeechClientListener;
import com.mobvoi.speech.hotword.HotwordListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import xin.moonway.uweather.R;
import xin.moonway.uweather.constant.Constant;
import xin.moonway.uweather.entity.AllUniversityEntity;
import xin.moonway.uweather.entity.GetCityEntity;
import xin.moonway.uweather.entity.GetCoordinateEntity;
import xin.moonway.uweather.entity.GetWeatherEntity;
import xin.moonway.uweather.ui.base.BaseActivity;

/***
 *
 *
 *
 *  请完成TODO的所有补充
 *
 *  所有的请求内容已经实例化 直接取需要的内容即可
 *
 *  请查看完所有的注释
 *
 *  如出现无法使用（FC、无反应)请确认权限设置
 *
 *  未经过全机型测试 测试机型：小米5（MIUI9） googlePX1 华为荣耀8(EMUI5.0)
 *
 *
 *
 */
public class MainActivity extends BaseActivity {
    Button nihaowenwen, stop;
    AllUniversityEntity allUniversityEntity;
    TextView mTextView;
    private static final String sDeviceOne = "deviceFlag";
    private static final String GET_COORDINATE = "http://api.map.baidu.com/geocoder/v2/?output=json&ak=" + Constant.BAIDU_KEY + "&callback=showLocation&address=";
    private static final String GET_CITY = "http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&output=json&pois=1&ak=" + Constant.BAIDU_KEY + "&location=";
    private static final String GET_WEATHER = "http://www.sojson.com/open/api/weather/json.shtml?city=";

    @Override
    public void onCreateMyView() {
        setContentView(R.layout.activity_main);
//        nihaowenwen = findViewById(R.id.nihaowenwen);
//        stop = findViewById(R.id.stop);
        mTextView = findViewById(R.id.mText);
        init(this);

        linstener();
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {
        new Thread(getAllUniversity).start();

    }


    @Override
    protected void initOnClick() {

//        (findViewById(R.id.nihaowenwen))
//                .setOnClickListener(
//                        new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                startOnebox(sDeviceOne);
//                            }
//                        });
//        (findViewById(R.id.stop))
//                .setOnClickListener(
//                        new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                stop(sDeviceOne);
//                            }
//                        });

    }

    @Override
    public void onMyClick(View view) {

    }


    /***
     * 初始化语音引擎
     * @param context
     */
    public void init(Context context) {
        SpeechClient.getInstance().init(context, Constant.MOBVOI_KEY);
        SpeechClient.getInstance().setClientListener(sDeviceOne, new SpeechClientListenerImpl());

    }

    /***
     * 热词唤醒
     *
     */
    public void linstener() {
        // TODO 热词设置 （未实现）
//        SpeechClient.getInstance().setHotwordContent("你好");
        /***
         * 热词回调
         */
        SpeechClient.getInstance().addHotwordListener(new HotwordListener() {
            @Override
            public void onHotwordDetected() {
                Log.d("你好帅比", "1");
                SpeechClient.getInstance().stopHotword();
                startOnebox(sDeviceOne);
            }
        });
        SpeechClient.getInstance().startHotword();
    }


    /**
     * 读取全国高校名单
     * R.raw.universitylist.txt
     */
    Runnable getAllUniversity = new Runnable() {
        @Override
        public void run() {
            try {
                //Return an AssetManager instance for your application's package
                InputStream is = getResources().openRawResource(R.raw.universitylist);
                int size = is.available();
                // Read the entire asset into a local byte buffer.
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                // Convert the buffer into a string.
                String text = new String(buffer, "utf-8");
                Gson gson = new Gson();
                allUniversityEntity = gson.fromJson(text, AllUniversityEntity.class);

                Log.d("text", allUniversityEntity.getData().get(1).getSchool().get(2).getName());
            } catch (IOException e) {
                // Should never happen!
//            throw new RuntimeException(e);
                e.printStackTrace();
            }


        }
    };

    /***
     * 获取经纬度
     * @param address 地标名
     */
    private void getCoordinate(String address) {
        URL myURL = null;
        URLConnection httpsConn = null;

        try {
            myURL = new URL(GET_COORDINATE + address);
        } catch (MalformedURLException e) {
        }
        try {
            httpsConn = (URLConnection) myURL.openConnection();
            if (httpsConn != null) {
                InputStreamReader insr = new InputStreamReader(
                        httpsConn.getInputStream(), "UTF-8");
                BufferedReader br = new BufferedReader(insr);
                String data = null;
                if ((data = br.readLine()) != null) {
                    Log.d("data", data.toString());
                    //时间充足可以做正则表达式获取需求内容
                    if (data.substring(0, 26).equals("showLocation&&showLocation")) {
                        String json = data.substring(27, data.length() - 1);
                        Log.d("data true", json.toString());
                        Gson gson = new Gson();
                        GetCoordinateEntity coordinateEntity = gson.fromJson(json, GetCoordinateEntity.class);
                        if (coordinateEntity.getStatus() == Constant.BAIDU_FLAG_SUCCESS) {
                            Log.d("Lng", coordinateEntity.getResult().getLocation().getLng() + "");
                            getCity(coordinateEntity.getResult().getLocation().getLat() + "", coordinateEntity.getResult().getLocation().getLng() + "");
                        } else {
                            //TODO 请求后返回内容错误或查询不到此处 （这里添加查询错误提示）
                            //TODO 关闭loading动画
                        }

                    } else {
                        //TODO 请求后返回内容错误或查询不到此处 （这里添加查询错误提示）
                        //TODO 关闭loading动画
                        Log.d("data false", data.substring(0, 26));
                    }

                }
                insr.close();
            }
        } catch (IOException e) {
//TODO 关闭loading动画
        }
    }

    /***
     * 通过经纬度判断城市
     * @param lat 纬度
     * @param lng 经度
     */
    private void getCity(String lat, String lng) {
        URL myURL = null;
        URLConnection httpsConn = null;

        try {
            myURL = new URL(GET_CITY + lat + "," + lng);
        } catch (MalformedURLException e) {
        }
        try {
            httpsConn = (URLConnection) myURL.openConnection();
            if (httpsConn != null) {
                InputStreamReader insr = new InputStreamReader(
                        httpsConn.getInputStream(), "UTF-8");
                BufferedReader br = new BufferedReader(insr);
                String data = null;
                if ((data = br.readLine()) != null) {
//                    renderReverse&&renderReverse
                    Log.d("citydata", data.toString());
                    //时间充裕使用正则表达式
                    if (data.substring(0, 28).equals("renderReverse&&renderReverse")) {
                        String json = data.substring(29, data.length() - 1);
                        Log.d("city data true", json.toString());
                        Gson gson = new Gson();
                        GetCityEntity getCityEntity = gson.fromJson(json, GetCityEntity.class);
                        if (getCityEntity.getStatus() == Constant.BAIDU_FLAG_SUCCESS) {
                            Log.d("city ", getCityEntity.getResult().getAddressComponent().getCity() + getCityEntity.getResult().getAddressComponent().getDistrict());
                            getWeather(getCityEntity.getResult().getAddressComponent().getCity());
                        } else {
                            //TODO 请求后返回内容错误或查询不到此处 （这里添加查询错误提示）
                            //TODO 关闭loading动画
                        }
                    } else {
                        //TODO 请求后返回内容错误或查询不到此处 （这里添加查询错误提示）
                        //TODO 关闭loading动画
                        Log.d("data false", data.substring(0, 28));
                    }
                }
                insr.close();
            }
        } catch (IOException e) {
            //TODO 关闭loading动画
        }

    }

    /***
     * 获取城市天气
     * @param city 城市名
     */
    private void getWeather(String city) {
        URL myURL = null;
        URLConnection httpsConn = null;

        try {
            myURL = new URL(GET_WEATHER + city);
        } catch (MalformedURLException e) {
        }
        try {
            httpsConn = (URLConnection) myURL.openConnection();
            if (httpsConn != null) {
                InputStreamReader insr = new InputStreamReader(
                        httpsConn.getInputStream(), "UTF-8");
                BufferedReader br = new BufferedReader(insr);
                String data = null;
                if ((data = br.readLine()) != null) {
                    Log.d("weatherdata", data.toString());

                    Message message = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("data", data);
                    message.setData(bundle);
                    handler.sendMessage(message);
                }
                insr.close();
            }
        } catch (IOException e) {
            //TODO 关闭loading动画
        }
    }

    /***
     * 异步刷新UI
     */
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String data = msg.getData().getString("data");
            Gson gson = new Gson();
            GetWeatherEntity getCityEntity = gson.fromJson(data, GetWeatherEntity.class);
            if (getCityEntity.getStatus() == Constant.WEATHER_FLAG_SUCCESS) {
                //TODO 请求成功 在此处对获得
                //TODO 关闭loading动画
                mTextView.setText(data);

            } else {
                //TODO 请求失败 在此处添加获取失败提示
                //TODO 关闭loading动画
            }


        }
    };


    /***
     * 启动语音引擎
     * @param deviceName 设备名
     */
    public void startOnebox(String deviceName) {

        SpeechClient.getInstance().startOneboxRecognizer(deviceName);
    }

    /***
     * 停止语音引擎
     * @param deviceName 设备名
     */
    public void stopOnbox(String deviceName) {
        runOnUiThread(
                new Runnable() {
                    public void run() {
                        mTextView.setText("stopped");
                    }
                });
        SpeechClient.getInstance().stopRecognizer(deviceName);
    }


    /***
     * 启动语音识别回调
     *
     */
    private class SpeechClientListenerImpl implements SpeechClientListener {
        /**
         * 开始提供录音数据给语音识别引擎时回调。
         */
        @Override
        public void onStartRecord() {
            runOnUiThread(
                    new Runnable() {
                        @Override
                        public void run() {
                            mTextView.setText("start recording ...");
                        }
                    });
        }

        /**
         * 服务器端检测到静音（说话人停止说话）后回调。
         */

        @Override
        public void onRemoteSilenceDetected() {
            runOnUiThread(
                    new Runnable() {
                        public void run() {
                            mTextView.setText("remote silence ...");
                        }
                    });
        }

        /**
         * 输入语音数据实时的音量回调。
         *
         * @param volume 根据语音的能量算出，单位为db, 范围为[0, 60]。
         */

        @Override
        public void onVolume(final double volume) {
        }


        /**
         * 语音识别部分结果返回，比如“今天天气怎么样”，会按顺序返回“今天”，“今天天气”。
         *
         * @param result 识别到的部分结果字符串。
         */
        @Override
        public void onPartialTranscription(final String result) {
            runOnUiThread(
                    new Runnable() {
                        @Override
                        public void run() {
                            mTextView.setText(result);
                        }
                    });
        }


        /**
         * 语音识别最终结果返回，比如“今天天气怎么样”，会按顺序返回“今天”，“今天天气”，“今天天气怎么样”，
         * 最后一个就是Final Transcription。
         *
         * @param result 最终认为用户说的完整字符串。
         */
        @Override
        public void onFinalTranscription(final String result) {
            //TODO 启动loading动画
            runOnUiThread(
                    new Runnable() {
                        @Override
                        public void run() {
                            mTextView.setText(result);
                        }
                    });
            int i, j = 0;
            boolean containsFlag = false;
            for (i = 0; i < allUniversityEntity.getData().size(); i++) {

                for (j = 0; j < allUniversityEntity.getData().get(i).getSchool().size(); j++) {
                    if (result.contains(allUniversityEntity.getData().get(i).getSchool().get(j).getName().toString())) {
                        containsFlag = true;
                    }
                    if (containsFlag) {
                        break;
                    }
                }
                if (containsFlag) {
                    break;
                }
            }

            if (containsFlag && result.contains("天气")) {
                //TODO 匹配成功执行
                final int finalI = i;
                final int finalJ = j;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getCoordinate(allUniversityEntity.getData().get(finalI).getSchool().get(finalJ).getName().toString());
                    }
                }).start();
            } else {
                //TODO 匹配失败 在此处添加反馈
                //TODO 关闭loading动画
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.setText("没有找到此校");
                    }
                });

            }

            SpeechClient.getInstance().startHotword();
        }

        /**
         * 语音搜索结果返回, 为JSON格式字符串。
         *
         * @param result 语音搜索结果json。
         */
        @Override
        public void onResult(final String result) {
//            runOnUiThread(
//                    new Runnable() {
//                        @Override
//                        public void run() {
//                            mTextView.setText(result);
//                        }
//                    });
        }

        /**
         * 当执行出错时会回调。
         *
         * @param errorCode 错误码，See {@link }。
         */
        @Override
        public void onError(final int errorCode) {
            runOnUiThread(
                    new Runnable() {
                        @Override
                        public void run() {
                            mTextView.setText("return error " + errorCode);
                        }
                    });
//TODO 关闭loading动画
            SpeechClient.getInstance().startHotword();
        }

        /**
         * 在检测到本地语音之后，又检测到本地静音时回调。
         */
        @Override
        public void onLocalSilenceDetected() {
            runOnUiThread(
                    new Runnable() {
                        public void run() {
                            mTextView.setText("local silence ...");
                        }
                    });
        }

        /**
         * 一段时间未检测到本地语音时回调。
         */
        @Override
        public void onNoSpeechDetected() {
            runOnUiThread(
                    new Runnable() {
                        @Override
                        public void run() {
                            mTextView.setText("local no speech ...");
                        }
                    });
        }

        /**
         * 检测到本地语音时回调。
         */
        @Override
        public void onSpeechDetected() {
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTextView.setText("Welcome to use");
            }
        });
        SpeechClient.getInstance().startHotword();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SpeechClient.getInstance().stopHotword();
        stopOnbox(sDeviceOne);
    }
}
