package com.hammad.solarsystem.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Hassan on 11/5/2016.
 */
/**
 * A fragment that will contain an image of the sun or a planet.
 */
public class PlanetFragment extends Fragment {
    public final String LOG_TAG = this.getClass().getSimpleName();
    /**
     * The fragment argument representing the number of this
     * fragment.
     */
    private static final String ARG_PLANET_NUM = "planet_number";

    // The image of the planet
    private ImageView planetImage;

    public PlanetFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlanetFragment newInstance(int planetNumber) {
        PlanetFragment fragment = new PlanetFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PLANET_NUM, planetNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        // Get the number of the fragment
        int planetNum = getArguments().getInt(ARG_PLANET_NUM);
        // Get the image based on the planet number
        planetImage = (ImageView) rootView.findViewById(R.id.iv_planet);
        int imageId = MainActivity.planetImageArray[planetNum];
        planetImage.setImageResource(imageId);

        // Get the title and image layout and set an item click listener on it
        planetImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPlanetClicked();
            }
        });

        return rootView;
    }

    // Starts a new activity when a planet is clicked
    public void onPlanetClicked() {
        // Start a detail activity and pass the name of the planet to it
        Intent detailActivityIntent = new Intent(getActivity(), DetailActivity.class);
        detailActivityIntent.putExtra(Utility.PLANET_NUMBER,
                getArguments().getInt(ARG_PLANET_NUM));
        startActivity(detailActivityIntent);
    }
}
