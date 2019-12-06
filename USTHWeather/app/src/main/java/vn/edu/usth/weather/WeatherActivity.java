package vn.edu.usth.weather;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherActivity extends FragmentActivity{

    private ViewPager viewPager;
    private Adapter adapter;
    private MediaPlayer music;

    final Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            // This method is executed in main thread
            String content = msg.getData().getString("server_response");
            Toast.makeText(getApplicationContext(), content, Toast.LENGTH_LONG).show();
        }
    };

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

        new GetRequestImage().execute("https://ictlab.usth.edu.vn/wp-content/uploads/logos/usth.png");

        // Add view pager for 2 fragment
        viewPager = findViewById(R.id.ViewPager);
        adapter = new Adapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(adapter);

        //Add tab layout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        //Music player
        copyFileToExternalStorage(R.raw.alliwant, "alliwant.mp3");

        music = MediaPlayer.create(this, R.raw.alliwant);
        music.start();


    }

    private class GetRequestImage extends AsyncTask<String, Void, Bitmap> {
        private ProgressDialog progressDialog;

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream inputStream = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);
                return myBitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (Exception e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            progressDialog.dismiss();
            ImageView imageView = (ImageView) findViewById(R.id.logo);
            imageView.setImageBitmap(bitmap);
        }

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(WeatherActivity.this,
                    "Updating weather...",
                    "Wait for few seconds!");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
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
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.refresh:
//                AsyncTaskRunner runner = new AsyncTaskRunner();
//                runner.execute("5000");
                return true;
            case R.id.settings:
                Intent intent = new Intent(this, PrefActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
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
