package com.example.cuaca;

import android.text.format.DateFormat;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class WeatherDaily {
    int dt;
    ArrayList<Weather> weathers;

    public WeatherDaily(JSONObject object){
        try {
            this.dt = object.getInt("dt");

            ArrayList<Weather> weathers = new ArrayList<Weather>();
            JSONArray array = object.getJSONArray("weather");
            for (int i = 0; i < array.length(); i++) {
                JSONObject item = array.getJSONObject(i);
                Weather weather = new Weather(item);
                weathers.add(weather);
            }
            this.weathers = weathers;
        }catch (JSONException e) {
            Log.e("App", "Error mapping model");
        }
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public int getDt() {
        return dt;
    }

    public void setWeathers(ArrayList<Weather> weathers) {
        this.weathers = weathers;
    }

    public ArrayList<Weather> getWeathers() {
        return weathers;
    }

    public Date getDate() {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(Long.valueOf(dt) * 1000);
        return cal.getTime();
    }
}
