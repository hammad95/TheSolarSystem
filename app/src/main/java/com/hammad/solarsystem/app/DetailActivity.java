package com.hammad.solarsystem.app;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Set ActionBar
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_detail_activity));
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);  // Add up navigation button
            actionBar.setElevation(0f);                 // Remove elevation from toolbar
        }
    }

    @Override
    public void onBackPressed() {
        // If player is in full screen, exit full screen
        if(DetailActivityFragment.isPlayerFullScreen)
            DetailActivityFragment.mYouTubePlayer.setFullscreen(false);
        else    // Otherwise, use default behavior of back
            super.onBackPressed();
    }
}
