package vn.edu.usth.weather;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.content.Context;
import android.os.Bundle;

public class Adapter extends FragmentPagerAdapter {
    private Context _context;
    private final int page_count =3;
    private String titles[] = null;

    public Adapter(FragmentManager fm, Context c){
        super(fm);
        _context = c;
        titles = _context.getResources().getStringArray(R.array.title);
    }

    @Override
    public int getCount() {
        return page_count;
    }

    @Override
    public Fragment getItem(int page) {
        WeatherAndForecastFragment weatherAndForecastFragment = new WeatherAndForecastFragment();
        Bundle bundle = new Bundle();
        switch (page) {
            case 0: return WeatherAndForecastFragment.newInstance(0, titles[0]);
            case 1: return WeatherAndForecastFragment.newInstance(1, titles[1]);
            case 2: return WeatherAndForecastFragment.newInstance(2, titles[2]);
        }
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