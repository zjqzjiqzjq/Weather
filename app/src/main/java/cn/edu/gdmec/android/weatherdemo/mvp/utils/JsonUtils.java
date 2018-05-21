package cn.edu.gdmec.android.weatherdemo.mvp.utils;

import com.google.gson.Gson;

import cn.edu.gdmec.android.weatherdemo.mvp.WeatherBean;

/**
 * 解析Json
 */

public class JsonUtils {

    public static WeatherBean getWeather(String res){
        Gson gson = new Gson();
        WeatherBean weatherBean = gson.fromJson(res,WeatherBean.class);
        return weatherBean;
    }


}
