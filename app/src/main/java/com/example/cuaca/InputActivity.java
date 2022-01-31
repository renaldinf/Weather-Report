package com.example.cuaca;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class InputActivity extends AppCompatActivity {

    EditText etName;
    Spinner spinProvince, spinCity;
    Button btnInput;

    public void onCreate(Bundle savedInstanceState) {
        try{
            final Bundle bundle = new Bundle();
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_input);

            etName = findViewById(R.id.name);
            btnInput = findViewById(R.id.input);

            spinProvince = Create_Province_Add_Spinner();
            spinCity = Create_City_Add_Spinner(String.valueOf(spinProvince.getSelectedItem()));

            btnInput.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(InputActivity.this, MainActivity.class);
                    bundle.putString("city", String.valueOf(spinCity.getSelectedItem()));
                    i.putExtras(bundle);
                    startActivity(i);
                    finish();
                }
            });
        } catch (Exception e) {
            Log.e("ERROR", e.toString());
            e.printStackTrace();
        }
    }


    // Creates the Province Spinner for the Add section sends the choice made to
    // a method for populating the City Spinner
    public Spinner Create_Province_Add_Spinner() {
        Spinner spinProvince = findViewById(R.id.province);
        String[] Province_Array = getResources().getStringArray(R.array.provinces);
        SpinnerAdapter Province_Adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, Province_Array);
        spinProvince.setAdapter(Province_Adapter);
        spinProvince.setOnItemSelectedListener(new Province_Listener());
        return spinProvince;
    }

    // The listener for the Province Spinner that sends whatever choice made to
    // the method that populates the City spinner.
    public class Province_Listener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> Province_Adapter_View,
                                   View v, int position, long row) {
            String Province_Choice = Province_Adapter_View
                    .getItemAtPosition(position).toString();
            Create_City_Add_Spinner(Province_Choice);
        }

        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub

        }

    }

    // Creates the City Spinner for the Add section
    public Spinner Create_City_Add_Spinner(String Province_Choice) {
        Spinner spinCity = findViewById(R.id.city);
        String[] City_Array = null;
        switch (Province_Choice){
            case "Aceh":
                City_Array = getResources().getStringArray(R.array.Aceh);
                break;
            case "Bali":
                City_Array = getResources().getStringArray(R.array.Bali);
                break;
            case "Banten":
                City_Array = getResources().getStringArray(R.array.Banten);
                break;
            case "Bengkulu":
                City_Array = getResources().getStringArray(R.array.Bengkulu);
                break;
            case "DKI Jakarta":
                City_Array = getResources().getStringArray(R.array.DKI_Jakarta);
                break;
            case "Gorontalo":
                City_Array = getResources().getStringArray(R.array.Gorontalo);
                break;
            case "Jambi":
                City_Array = getResources().getStringArray(R.array.Jambi);
                break;
            case "Jawa Barat":
                City_Array = getResources().getStringArray(R.array.Jawa_Barat);
                break;
        }
        SpinnerAdapter City_Adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,City_Array);
        spinCity.setAdapter(City_Adapter);
        return spinCity;
    }
}
