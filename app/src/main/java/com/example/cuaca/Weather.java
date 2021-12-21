package com.example.cuaca;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Weather {
    int id;
    String main, description, icon;

    public Weather(JSONObject object) {

        try {
            this.id = object.getInt("id");
            this.main = object.getString("main");
            this.description = object.getString("description");
            this.icon = object.getString("icon");
        } catch (JSONException e) {
            Log.e("App", "error mapping model");
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getMain() {
        return main;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public String getIconURL() {
        return String.format("https://openweathermap.org/img/wn/%1$s@2x.png", icon);
    }
}
