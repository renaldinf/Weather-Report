package com.example.cuaca;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<WeatherDaily> dailies;
    WeatherDaily currentDaily;

    GridView grid;
    TextView tvLocation, tvDescription;
    ImageView ivCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grid = (GridView) findViewById(R.id.datagrid);
        tvLocation = (TextView) findViewById(R.id.lokasi);
        ivCurrent = (ImageView) findViewById(R.id.todayweather);
        tvDescription = (TextView) findViewById(R.id.description);

        String url = "https://api.openweathermap.org/data/2.5/onecall?lat=-6.9020805&lon=107.6157571&exclude=current,minutely,hourly,alerts&appid=cc30933ca3e53f64426e121993e2d6ba&lang=id&units=metric";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        WeatherResponse resp = new WeatherResponse(response);

                        dailies = resp.getDaily();
                        if (dailies.size() > 0) {
                            currentDaily = dailies.get(0);
                            configCurrentWeather();
                        }
                        grid.setAdapter(new gridAdapter(getApplicationContext(), dailies));

                        for (WeatherDaily daily : dailies) {
                            for (Weather weather : daily.weathers) {
                                Log.i("Fetch", weather.getMain());
                            }
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);
    }

    private void configCurrentWeather() {
        if (currentDaily != null) {
            Weather weather = currentDaily.weathers.get(0);

            String dateString = DateFormat.format("EEEE, dd MMMM yyyy", currentDaily.getDate()).toString();
            tvLocation.setText("Kota Bandung \n" + dateString);
            Picasso.get().load(weather.getIconURL()).into(ivCurrent);

            tvDescription.setText(weather.description);
        }
    }
}
