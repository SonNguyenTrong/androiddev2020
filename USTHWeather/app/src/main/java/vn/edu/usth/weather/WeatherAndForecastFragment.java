package vn.edu.usth.weather;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class WeatherAndForecastFragment extends Fragment {
    private String title;
    private int page;

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

        WeatherFragment weatherFragment = new WeatherFragment();
        weatherFragment.setArguments(this.getArguments());
        ForecastFragment forecastFragment = new ForecastFragment();

        fragmentTransaction.replace(R.id.weatherFragment, weatherFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

        fragmentTransaction.replace(R.id.forecastFragment, forecastFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

        fragmentTransaction.commit();

        return inflater.inflate(R.layout.weather_and_forecast_fragment, container, false);
    }
}