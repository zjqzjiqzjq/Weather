package cn.edu.gdmec.android.weatherdemo.mvp.model;

/**
 * Model层接口
 */

public interface IWeatherModel {
    void loadWeather(String url,ILoadListener loadListener);
}
