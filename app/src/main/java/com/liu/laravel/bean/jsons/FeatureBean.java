package com.liu.laravel.bean.jsons;

import com.liu.laravel.bean.BaseModel;

/**
 * created by liuxuhui on 2018/5/2  下午6:45
 */
public class FeatureBean extends BaseModel {

    /**
     * temperature : 15℃~26℃
     * weather : 多云转晴
     * wind : 东北风微风
     * week : 星期日
     * date : 20151011
     */

    private String temperature;
    private String weather;
    private String wind;
    private String week;
    private String date;

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "FeatureBean{" +
                "temperature='" + temperature + '\'' +
                ", weather='" + weather + '\'' +
                ", wind='" + wind + '\'' +
                ", week='" + week + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
