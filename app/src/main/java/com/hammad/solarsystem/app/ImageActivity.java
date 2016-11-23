package com.hammad.solarsystem.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * An full-screen activity that shows the full image
 */
public class ImageActivity extends Activity {

    // The key for the int which represents the drawable indexed by the
    // array index inside Utility.planetDetailFullImageArray to populate
    // the ImageView inside this view with
    public static final String EXTRA_IMAGE_INDEX = "image_index";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image);

        // Get the planet number and image index from the intent extra
        Intent data = getIntent();
        int planetNumber = -1;
        int imageIndex = -1;
        if(data.hasExtra(Utility.PLANET_NUMBER))
            planetNumber = data.getIntExtra(Utility.PLANET_NUMBER, -1);
        if(data.hasExtra(EXTRA_IMAGE_INDEX))
            imageIndex = data.getIntExtra(EXTRA_IMAGE_INDEX, -1);

        ImageView fullScreenImage = (ImageView) findViewById(R.id.iv_fullImage);
        fullScreenImage.setImageResource(Utility.planetDetailImagesArray[planetNumber][imageIndex]);
    }
}
