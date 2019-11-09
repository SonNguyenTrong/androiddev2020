package vn.edu.usth.weather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public class ForecastFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = new View(getActivity());
        v = inflater.inflate(R.layout.empty, container, false);
        v.setBackgroundColor(0x0000FF00);
        return v;
    }
}