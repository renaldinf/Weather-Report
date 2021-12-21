package com.example.cuaca;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class gridAdapter extends BaseAdapter {
    Context context;
    ArrayList<WeatherDaily> dailies;

    public gridAdapter(Context context, ArrayList<WeatherDaily> dailies){
        this.dailies = dailies;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dailies.size();
    }

    @Override
    public Object getItem(int position) {
        return dailies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater Inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = Inflater.inflate(R.layout.row_data, null);

        ImageView imageView = (ImageView)view.findViewById(R.id.iconimage);
        TextView textdata = (TextView)view.findViewById(R.id.textdata);
        TextView texttgl = (TextView)view.findViewById(R.id.texttgl);

        Weather weather = dailies.get(position).weathers.get(0);
        String dateString = DateFormat.format("dd-MM-yyyy", dailies.get(position).getDate()).toString();
        Picasso.get().load(weather.getIconURL()).into(imageView);

        texttgl.setText(dateString);
        textdata.setText(weather.description);
        return view;
    }
}
