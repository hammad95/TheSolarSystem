package com.hammad.solarsystem.app;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    // LOG_TAG for this fragment
    public static final String LOG_TAG = "DetailActivityFragment";

    // The planet number sent as an intent extra to DetailActivity
    int mPlanetNumber;

    // An onClickListener for thumbnail images
    private View.OnClickListener mImageOnClickListener;

    // Youtube player fragment, Youtube player view and YouTubePlayer.OnInitializedListener
    private YouTubePlayerSupportFragment mYouTubePlayerFragment;
    public static YouTubePlayer mYouTubePlayer;
    private YouTubePlayer.OnInitializedListener mPlayerOnInitializedListener;

    // Flag to indicate if the player is in full screen mode
    public static Boolean isPlayerFullScreen = false;

    // Request code for getErrorDialog() method for youtube initialization (useless)
    private final int REQUEST_CODE = 0;

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        // Get the planet number intent extra passed to DetailActivity
        if(getActivity().getIntent().hasExtra(Utility.PLANET_NUMBER))
            mPlanetNumber = getActivity().getIntent().getIntExtra(Utility.PLANET_NUMBER, -1);

        // Set the title TextView
        TextView title = (TextView) rootView.findViewById(R.id.tv_planet_title);
        title.setText(MainActivity.planetNameArray[mPlanetNumber]);

        // Set the details TextView
        TextView details = (TextView) rootView.findViewById(R.id.tv_planet_detail);
        details.setText(MainActivity.planetDetailsArray[mPlanetNumber]);

        // Get a reference to the YouTubeFragment
        if(mYouTubePlayerFragment == null) {
            mYouTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
            // Add the fragment using getChildFragmentManager
            getChildFragmentManager().beginTransaction().
                    replace(R.id.container_youtube_fragment, mYouTubePlayerFragment).
                    commit();
        }

        // Initialize the YouTubePlayer.OnInitializedListener
        mPlayerOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {

                // Get the youTubePlayer passed as argument
                mYouTubePlayer = youTubePlayer;

                // Do not show a fullscreen button
                mYouTubePlayer.setShowFullscreenButton(false);

                // Set flags to auto control orientation and ui
                mYouTubePlayer.setFullscreenControlFlags(
                        YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION);
                mYouTubePlayer.setFullscreenControlFlags(
                        YouTubePlayer.FULLSCREEN_FLAG_CONTROL_SYSTEM_UI);

                // Set an onFullScreenListener to the YouTubePlayer
                mYouTubePlayer.setOnFullscreenListener(new YouTubePlayer.OnFullscreenListener() {
                    @Override
                    public void onFullscreen(boolean isSwitchingToFullScreen) {

                        // If switching to full screen, set isPlayerFullScreen to true
                        // Also hide the root linear layout for a natural feel
                        if(isSwitchingToFullScreen) {
                            isPlayerFullScreen = true;  // set boolean to true
                        }
                        else {
                            isPlayerFullScreen = false; // set boolean to false
                            mYouTubePlayer.pause();     // Pause video
                        }
                    }
                });

                // Add a YouTubePlayer.PlaybackEventListener to listen for play events
                mYouTubePlayer.setPlaybackEventListener(new YouTubePlayer.PlaybackEventListener() {
                    @Override
                    public void onPlaying() {
                        // If user presses the play button, enter fullscreen mode
                        mYouTubePlayer.setFullscreen(true);
                    }

                    @Override
                    public void onPaused() {

                    }

                    @Override
                    public void onStopped() {

                    }

                    @Override
                    public void onBuffering(boolean b) {

                    }

                    @Override
                    public void onSeekTo(int i) {

                    }
                });

                // If YouTubePlayer was not restored, load video
                if(!wasRestored) {
                    // Load the appropriate video based on the PLANET_NUMBER argument passed in the bundle
                    String videoId;
                    videoId = MainActivity.planetVideoArray[mPlanetNumber];  // Get the video id
                    mYouTubePlayer.cueVideo(videoId);                        // Cue the video
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                // If error is user recoverable, show a dialog to the user
                if(youTubeInitializationResult.isUserRecoverableError()) {
                    youTubeInitializationResult.getErrorDialog(getActivity(), REQUEST_CODE);
                }
            }
        };

        ImageView thumbnailImage1 = (ImageView) rootView.findViewById(R.id.iv_thumbnail_1);
        ImageView thumbnailImage2 = (ImageView) rootView.findViewById(R.id.iv_thumbnail_2);
        ImageView thumbnailImage3 = (ImageView) rootView.findViewById(R.id.iv_thumbnail_3);
        ImageView thumbnailImage4 = (ImageView) rootView.findViewById(R.id.iv_thumbnail_4);
        ImageView thumbnailImage5 = (ImageView) rootView.findViewById(R.id.iv_thumbnail_5);

        // Assign the appropriate thumbnail images based on the planet number
        // but first decode the image to resize it as a thumbnail
        new ThumbnailMakerTask(thumbnailImage1, getContext()).
                execute(Utility.planetDetailImagesArray[mPlanetNumber][0]);
        new ThumbnailMakerTask(thumbnailImage2, getContext()).
                execute(Utility.planetDetailImagesArray[mPlanetNumber][1]);
        new ThumbnailMakerTask(thumbnailImage3, getContext()).
                execute(Utility.planetDetailImagesArray[mPlanetNumber][2]);
        new ThumbnailMakerTask(thumbnailImage4, getContext()).
                execute(Utility.planetDetailImagesArray[mPlanetNumber][3]);
        new ThumbnailMakerTask(thumbnailImage5, getContext()).
                execute(Utility.planetDetailImagesArray[mPlanetNumber][4]);

        // Initialize the onClickListener for thumbnail images
        mImageOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // The integer to pass to ImageActivity based on the
                // clicked thumbnail ImageView
                int intentExtraImageIndex = -1;
                switch(v.getId()) {
                    case R.id.iv_thumbnail_1:
                        intentExtraImageIndex = 0;
                        break;
                    case R.id.iv_thumbnail_2:
                        intentExtraImageIndex = 1;
                        break;
                    case R.id.iv_thumbnail_3:
                        intentExtraImageIndex = 2;
                        break;
                    case R.id.iv_thumbnail_4:
                        intentExtraImageIndex = 3;
                        break;
                    case R.id.iv_thumbnail_5:
                        intentExtraImageIndex = 4;
                        break;
                }
                // Create an intent for ImageActivity
                Intent intentImageActivity = new Intent(getContext(), ImageActivity.class);
                // Pass the planet number and the image index to ImageActivity intent
                intentImageActivity.putExtra(Utility.PLANET_NUMBER, mPlanetNumber);
                intentImageActivity.putExtra(ImageActivity.EXTRA_IMAGE_INDEX, intentExtraImageIndex);
                startActivity(intentImageActivity);
            }
        };

        // Assign an onClickListener to each image view
        thumbnailImage1.setOnClickListener(mImageOnClickListener);
        thumbnailImage2.setOnClickListener(mImageOnClickListener);
        thumbnailImage3.setOnClickListener(mImageOnClickListener);
        thumbnailImage4.setOnClickListener(mImageOnClickListener);
        thumbnailImage5.setOnClickListener(mImageOnClickListener);

        return rootView;
    }

    @Override
    public void onResume() {

        // Initialize the YouTubeFragment
        mYouTubePlayerFragment.initialize(BuildConfig.YOUTUBE_API_KEY, mPlayerOnInitializedListener);

        super.onResume();
    }

    @Override
    public void onPause() {

        // Release resources taken up by youtube player
        if(mYouTubePlayer != null)
            mYouTubePlayer.release();

        super.onDestroyView();
    }
}
