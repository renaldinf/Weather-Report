package com.example.cuaca;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WeatherResponse {
    double lat, lon;
    String timezone;
    int timezone_offset;
    ArrayList<WeatherDaily> daily;

    public WeatherResponse(JSONObject object){
        try{
            this.lat = object.getDouble("lat");
            this.lon = object.getDouble("lon");
            this.timezone = object.getString("timezone");
            this.timezone_offset = object.getInt("timezone_offset");

            ArrayList<WeatherDaily> dailies = new ArrayList<WeatherDaily>();
            JSONArray array = object.getJSONArray("daily");
            for (int i = 0; i < array.length(); i++) {
                JSONObject item = array.getJSONObject(i);
                WeatherDaily daily = new WeatherDaily(item);
                dailies.add(daily);
            }
            this.daily = dailies;

        }catch (JSONException e){
            Log.e("App", "Error mapping model");
        }
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLat() {
        return lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLon() {
        return lon;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone_offset(int timezone_offset) {
        this.timezone_offset = timezone_offset;
    }

    public int getTimezone_offset() {
        return timezone_offset;
    }

    public void setDaily(ArrayList<WeatherDaily> daily) {
        this.daily = daily;
    }

    public ArrayList<WeatherDaily> getDaily() {
        return daily;
    }
}
