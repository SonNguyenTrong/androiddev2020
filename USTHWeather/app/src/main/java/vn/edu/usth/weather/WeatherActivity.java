package vn.edu.usth.weather;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;

public class WeatherActivity extends FragmentActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // load the layout
        setContentView(R.layout.empty);
        Log.i("StatusWeatherLog","onCreate() method running...");

        // Create a new Fragment to be placed in the activity
        ForecastFragment firstFragment = new ForecastFragment();

        // Add the fragment to the 'container' FrameLayout
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, firstFragment).commit();
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
