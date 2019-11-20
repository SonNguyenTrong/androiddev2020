package vn.edu.usth.weather;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

public class WeatherActivity extends FragmentActivity{

    private ViewPager viewPager;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // load the layout
        setContentView(R.layout.empty);
        Log.i("StatusWeatherLog","onCreate() method running...");

        // Create Weather Fragment
        //WeatherFragment weatherFragment = new WeatherFragment();

        // Create Forecast Fragment to be placed in the activity
        //ForecastFragment firstFragment = new ForecastFragment();

        // Add the Weather fragment to Layout
        //getSupportFragmentManager().beginTransaction().add(R.id.fragment_weather, weatherFragment).commit();

        // Add the Forecast fragment to the 'container' FrameLayout
        //getSupportFragmentManager().beginTransaction().add(R.id.fragment_forecast, firstFragment).commit();

        // Add view pager for 2 fragment
        viewPager = findViewById(R.id.ViewPager);
        adapter = new Adapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
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
