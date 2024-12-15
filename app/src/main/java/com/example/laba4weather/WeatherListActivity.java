package com.example.laba4weather;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class WeatherListActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_list);

        listView = findViewById(R.id.weatherListView);
        databaseHelper = new DatabaseHelper(this);

        loadWeatherData();
    }

    private void loadWeatherData() {
        Cursor cursor = databaseHelper.getAllWeather();
        String[] from = {DatabaseHelper.COLUMN_CITY, DatabaseHelper.COLUMN_TEMPERATURE};
        int[] to = {android.R.id.text1, android.R.id.text2};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_2,
                cursor,
                from,
                to,
                0
        );

        listView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseHelper.close();
    }
}