package vn.edu.usth.weather;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;


public class WeatherAndForecastFragment extends Fragment {
    private String title;
    private int page;
    private String url;
    TextView weather_desc;
    ImageView image;

    public WeatherAndForecastFragment() {
        //empty constructor
    };

    protected static WeatherAndForecastFragment newInstance(int page, String title) {
        WeatherAndForecastFragment weatherAndForecastFragment = new WeatherAndForecastFragment();
        Bundle var = new Bundle();
        var.putInt("page", page);
        var.putString("title", title);
        weatherAndForecastFragment.setArguments(var);
        return weatherAndForecastFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            page = getArguments().getInt("page");
            title = getArguments().getString("title");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();

        ForecastFragment forecastFragment = new ForecastFragment();

        fragmentTransaction.replace(R.id.forecastFragment, forecastFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

        fragmentTransaction.commit();
        View view = inflater.inflate(R.layout.weather_and_forecast_fragment, container, false);

//        weather_desc = (TextView) view.findViewById(R.id.weather_desc);
//        center_image = (ImageView) view.findViewById(R.id.imagecenter);



        weather_desc = (TextView) view.findViewById(R.id.weather_desc);
        image = (ImageView) view.findViewById(R.id.imagecenter);

        switch (title) {
            case "Hanoi":
                jsonParse("21.0278,105.8342");
            case "Paris":
                jsonParse("48.8566,2.3522");
            case "Cannes":
                jsonParse("43.6047,1.4442");
        }

        return view;
    }

    private void jsonParse(String latlong){
        String key = getResources().getString(R.string.secret_key);
        String baseURL = getResources().getString(R.string.base_url);
        url = baseURL+ key + latlong;
        JsonObjectRequest request =
                new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    Log.i("USTHWeather", "resposnse" + response);
                                    JSONObject obj = response.getJSONObject("currently");
                                    String desc = obj.getString("summary");
                                    String temp = obj.getString("temperature") + "F";
                                    WeatherFragment frag =(WeatherFragment) getChildFragmentManager().findFragmentById(R.id.frag_weather);
                                    weather_desc.setText(desc + "\n" + temp);
                                    switch (desc) {
                                        case "Clear":
                                            image.setImageDrawable(getResources().getDrawable(R.drawable.clear));
                                            break;
                                        case "Rain":
                                            image.setImageDrawable(getResources().getDrawable(R.drawable.rain));
                                            break;
                                        case "Mostly Cloudy":
                                            image.setImageDrawable(getResources().getDrawable(R.drawable.cloudy));
                                            break;
                                        case "Partly Cloudy":
                                            image.setImageDrawable(getResources().getDrawable(R.drawable.part_cloud));
                                            break;
                                        case "Snow":
                                            image.setImageDrawable(getResources().getDrawable(R.drawable.snow));
                                            break;
                                        default:
                                            break;
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        MySingleTon.getInstance(this.getContext()).addToRequestQue(request);
    }

//
//        StringRequest request = new StringRequest(url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.i("USTHWeather", "Json response " + response);
//                        try {
//                            JSONObject obj =  new JSONObject(response);
//                            JSONObject subobj = obj.getJSONObject("currently");
//                            String desc = subobj.getString("summary");
//                            TextView weatherDesc = (TextView) view.findViewById(R.id.weather_desc);
//                            ImageView imageCenter = findViewById(R.id.imagecenter);
//                            switch (desc){
//                                case "Clear" :
//                                    weatherDesc.setText(desc +"\n" +subobj.getString("temperature"));
//                                    imageCenter.setImageDrawable(getResources().getDrawable(R.drawable.clear));
//                                    break;
//                                case "Rain" :
//                                    weatherDesc.setText(desc +"\n" +subobj.getString("temperature"));
//                                    imageCenter.setImageDrawable(getResources().getDrawable(R.drawable.rain));
//                                    break;
//                                case "Mostly Cloudy" :
//                                    weatherDesc.setText(desc +"\n" +subobj.getString("temperature"));
//                                    imageCenter.setImageDrawable(getResources().getDrawable(R.drawable.cloudy));
//                                    break;
//                                case "Partly Cloudy" :
//                                    weatherDesc.setText(desc +"\n" +subobj.getString("temperature"));
//                                    imageCenter.setImageDrawable(getResources().getDrawable(R.drawable.part_cloud));
//                                    break;
//                                case "Snow" :
//                                    weatherDesc.setText(desc +"\n" +subobj.getString("temperature"));
//                                    imageCenter.setImageDrawable(getResources().getDrawable(R.drawable.snow));
//                                    break;
//                                default:
//                                    break;
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                    }
//                });
//        requestQueue.add(request);



}