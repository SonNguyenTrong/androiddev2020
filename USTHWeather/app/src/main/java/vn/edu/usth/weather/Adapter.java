package vn.edu.usth.weather;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.os.Bundle;

public class Adapter extends FragmentPagerAdapter {
    private final int page_count =3;
    private String titles[] = new String[]{"Page 1", "Page n", "Page infinite"};

    public Adapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public int getCount() {
        return page_count;
    }

    @Override
    public Fragment getItem(int page) {
        WeatherAndForecastFragment weatherAndForecastFragment = new WeatherAndForecastFragment();
        Bundle bundle = new Bundle();
        page=page+1;
        bundle.putString("page", Integer.toString(page));
        weatherAndForecastFragment.setArguments(bundle);
        return weatherAndForecastFragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int page) {
        return titles[page];
    }
}