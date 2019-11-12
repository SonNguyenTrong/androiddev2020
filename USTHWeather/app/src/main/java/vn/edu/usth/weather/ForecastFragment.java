package vn.edu.usth.weather;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ForecastFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // View v = new View(getActivity());
        // v.setBackgroundColor(0x0000FF00);
        View view = inflater.inflate(R.layout.empty, container, false);
        LinearLayout fragment_container = (LinearLayout) view.findViewById(R.id
                .fragment_container);
        fragment_container.setOrientation(LinearLayout.VERTICAL);
        TextView textview = new TextView(getContext());
        textview.setText("Thursday");
        fragment_container.addView(textview);
        ImageView imageview = new ImageView(getContext());
        imageview.setMaxHeight(250);
        imageview.setMaxWidth(250);
        imageview.setBackgroundResource(R.drawable.wea_ther1);
        fragment_container.addView(imageview);
        return view;
    }
}