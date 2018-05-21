package cn.edu.gdmec.android.weatherdemo.mvp.model;

import cn.edu.gdmec.android.weatherdemo.mvp.WeatherBean;

/**
 * 状态监听接口
 */

public interface ILoadListener {
    void onSuccess(WeatherBean bean);
    void onFailure(Exception e);
}
