package vn.edu.usth.weather;

import android.os.Bundle;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ForecastFragment extends Fragment {
    public ForecastFragment() {
        //empty constructor
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // View v = new View(getActivity());
        // v.setBackgroundColor(0x0000FF00);
        View view = inflater.inflate(R.layout.forecast_fragment, container, false);
        LinearLayout fragment_container = view.findViewById(R.id
                .fragment_container);
        for (int i = 0; i < 7; i++ ) {
            LinearLayout.LayoutParams parent = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 0 ,1);
            LinearLayout.LayoutParams child1 = new LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.MATCH_PARENT,3);
            LinearLayout.LayoutParams child2 = new LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.MATCH_PARENT,2);
            LinearLayout.LayoutParams child3 = new LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.MATCH_PARENT,5);
            TextView textview = new TextView(getContext());
            textview.setText(getResources().getStringArray(R.array.days)[i]);
            textview.setGravity(Gravity.CENTER);
            textview.setLayoutParams(child1);

            ImageView imageview = new ImageView(getContext());
            imageview.setBackgroundResource(R.drawable.wea_ther1);
            imageview.setLayoutParams(child2);
            imageview.setPadding(10,10,10,10);

            TextView desc = new TextView(getContext());
            desc.setText(R.string.description);
            desc.setGravity(Gravity.CENTER);
            desc.setLayoutParams(child3);

            LinearLayout layout = new LinearLayout(getContext());
            layout.setOrientation(LinearLayout.HORIZONTAL);
            layout.setWeightSum(10);
            layout.setLayoutParams(parent);
            layout.addView(textview);
            layout.addView(imageview);
            layout.addView(desc);

            fragment_container.addView(layout);
        }

        return view;
    }
}