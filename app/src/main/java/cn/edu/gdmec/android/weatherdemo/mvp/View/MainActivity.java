package cn.edu.gdmec.android.weatherdemo.mvp.View;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.TimerTask;

import cn.edu.gdmec.android.weatherdemo.R;
import cn.edu.gdmec.android.weatherdemo.mvp.WeatherBean;
import cn.edu.gdmec.android.weatherdemo.mvp.presenter.WeatherPresenter;

public class MainActivity extends Activity implements IWeatherView, View.OnClickListener {

    private TextView tvWeather;
    private TextView tvWeatherYesterday;
    private ProgressDialog progressDialog;
    private WeatherPresenter presenter;
    private TextView tvAuthor;
    private TextView tvSubtitle;
    private int xuanze;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_search).setOnClickListener(this);
        findViewById(R.id.btn_beijing_search).setOnClickListener(this);
        findViewById(R.id.btn_jinpingmei).setOnClickListener(this);
        tvWeather = (TextView) findViewById(R.id.tv_weather);
        tvWeatherYesterday = (TextView) findViewById(R.id.tv_weather_yesterday);
        tvAuthor = (TextView) findViewById(R.id.tv_author);
        tvSubtitle = (TextView) findViewById(R.id.tv_subtitle);


        presenter = new WeatherPresenter(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_search:
                presenter.loadWeather("广州");
                xuanze = 1;
                break;
            case R.id.btn_beijing_search:
                presenter.loadWeather("北京");
                xuanze = 1;
                break;
            case R.id.btn_jinpingmei:
                presenter.loadBook();
                xuanze = 2;
                break;
        }
    }

    @Override
    public void showProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        progressDialog = ProgressDialog.show(MainActivity.this, "", "正在获取");
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showWeatherData(final WeatherBean weatherBean) {
        runOnUiThread(new TimerTask() {
            @Override
            public void run() {
                if (weatherBean.getStatus() == 304) {
                    Toast.makeText(MainActivity.this, weatherBean.getMessage(),
                            Toast.LENGTH_SHORT).show();
                } else {
                    if (xuanze == 1){
                        tvWeather.setText("城市：" + weatherBean.getCity()
                                + " 日期：" + weatherBean.getDate()
                                + " 温度：" + weatherBean.getData().getWendu());
                        tvWeatherYesterday.setText(
                                "昨日天气：" + weatherBean.getData().getYesterday().getLow() + " "
                                        + weatherBean.getData().getYesterday().getHigh());
                    }else if (xuanze == 2){
                        tvAuthor.setText("作者：" + weatherBean.getBooks().get(0).getAuthor());
                        tvSubtitle.setText("图书标题：" + weatherBean.getBooks().get(0).getSubtitle());

                    }


                }
            }
        });
    }

    @Override
    public void showLoadFailMsg(final Exception e) {
        runOnUiThread(new TimerTask() {
            @Override
            public void run() {
                tvWeather.setText("加载数据失败：" + e.toString());
            }
        });

    }



}
