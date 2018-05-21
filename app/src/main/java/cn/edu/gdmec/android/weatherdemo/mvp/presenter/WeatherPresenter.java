package cn.edu.gdmec.android.weatherdemo.mvp.presenter;

import cn.edu.gdmec.android.weatherdemo.mvp.View.IWeatherView;
import cn.edu.gdmec.android.weatherdemo.mvp.WeatherBean;
import cn.edu.gdmec.android.weatherdemo.mvp.model.ILoadListener;
import cn.edu.gdmec.android.weatherdemo.mvp.model.IWeatherModel;
import cn.edu.gdmec.android.weatherdemo.mvp.model.WeatherModel;

/**
 * Created by apple on 18/5/15.
 */

public class WeatherPresenter implements IWeatherPresenter,ILoadListener {

    private String url = "https://www.sojson.com/open/api/weather/json.shtml?city=";
    private String url1 = "https://api.douban.com/v2/book/search?q=%E9%87%91%E7%93%B6%E6%A2%85&tag=&start=0&count=1";


    private IWeatherView iWeatherView;
    private IWeatherModel iWeatherModel;

    public WeatherPresenter(IWeatherView iWeatherView){
        this.iWeatherView = iWeatherView;
        this.iWeatherModel = new WeatherModel();
    }

    @Override
    public void loadWeather(String city) {
        iWeatherView.showProgress();
        iWeatherModel.loadWeather(url+city,this);
    }

    @Override
    public void loadBook() {
        iWeatherView.showProgress();
        iWeatherModel.loadWeather(url1,this);
    }


    @Override
    public void onSuccess(WeatherBean bean) {
        iWeatherView.hideProgress();
        iWeatherView.showWeatherData(bean);
    }

    @Override
    public void onFailure(Exception e) {
        iWeatherView.hideProgress();
        iWeatherView.showLoadFailMsg(e);
    }
}
