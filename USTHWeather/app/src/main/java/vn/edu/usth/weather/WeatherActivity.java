package vn.edu.usth.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // load the layout
        setContentView(R.layout.filters);
        Log.i("StatusWeatherLog","onCreate() method running...");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("StatusWeatherLog","onStart() method running...");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("StatusWeatherLog","onResume() method running...");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("StatusWeatherLog","onPause() method running...");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("StatusWeatherLog","onStop() method running...");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("StatusWeatherLog","onDestroy() method running...");
    }
}
