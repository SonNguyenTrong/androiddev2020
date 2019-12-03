package vn.edu.usth.weather;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class WeatherActivity extends FragmentActivity{

    private ViewPager viewPager;
    private Adapter adapter;
    private MediaPlayer music;

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
        adapter = new Adapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(adapter);

        //Add tab layout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        //music player
        copyFileToExternalStorage(R.raw.alliwant, "alliwant.mp3");

        music = MediaPlayer.create(this, R.raw.alliwant);
        music.start();

    }

    private void copyFileToExternalStorage(int resourceId, String resourceName) {
        String pathSDCard = Environment.getExternalStorageDirectory()
                + "/Android/data/vn.edu.usth.weather/" + resourceName;
        try {
            InputStream in = getResources().openRawResource(resourceId);
            FileOutputStream out = new FileOutputStream(pathSDCard);
            byte[] buff = new byte[1024];
            try {
                int read = 0;
                while ((read = in.read(buff)) > 0) {
                    out.write(buff, 0, read);
                }
            } finally {
                in.close();
                out.close() ;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        music.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("StatusWeatherLog","onDestroy() method running...");
    }
}
