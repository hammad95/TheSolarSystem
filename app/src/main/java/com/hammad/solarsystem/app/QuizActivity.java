package com.hammad.solarsystem.app;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    // Needed for the navigation bar
    private String[] mDrawerItems;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Set ToolBar as ActionBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
        setSupportActionBar(toolbar);
        // Set the aciton bar to not show the title
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mDrawerItems = getResources().getStringArray(R.array.drawer_items);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<>(this,
                R.layout.drawer_list_item, mDrawerItems));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                toolbar,               /* Toolbar */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */){
            public void onDrawerClosed(View view) {
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerLayout.addDrawerListener(mDrawerToggle);

        //Show quiz option in the navigation drawer as selected
        selectItem(1);

        // Get the page number and scroll to that page in the quiz view pager
        Intent intent = getIntent();
        int planetNumber = -1;
        if(intent.hasExtra(Utility.PLANET_NUMBER))
            planetNumber = intent.getIntExtra(Utility.PLANET_NUMBER, -1);
        if(planetNumber != -1)
            mViewPager.setCurrentItem(planetNumber);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.about) {
            Intent aboutIntent = new Intent(this, AboutActivity.class);
            startActivity(aboutIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        outState.putInt("OPTION", 2);
//        super.onSaveInstanceState(outState);
//        Log.d("QuizActivity", "onSaveInstanceState() called");
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        Log.d("QuizActivity", "onRestoreInstanceState() called");
//        QuizFragment quizFragment = (QuizFragment)
//                mSectionsPagerAdapter.getItem(mViewPager.getCurrentItem());
//        quizFragment.setSelectedOption(savedInstanceState.getInt("OPTION", -1));
//    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class QuizFragment extends Fragment {

        // The text for the radio buttons representing the options for the MCQs
        TextView tvQ1Op1;
        TextView tvQ1Op2;
        TextView tvQ1Op3;
        TextView tvQ1Op4;
        TextView tvQ2Op1;
        TextView tvQ2Op2;
        TextView tvQ2Op3;
        TextView tvQ2Op4;
        TextView tvQ3Op1;
        TextView tvQ3Op2;
        TextView tvQ3Op3;
        TextView tvQ3Op4;

        RadioGroup q1Options;
        RadioGroup q2Options;
        RadioGroup q3Options;

        public QuizFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static QuizFragment newInstance(int planetNumber) {
            QuizFragment fragment = new QuizFragment();
            Bundle args = new Bundle();
            args.putInt(Utility.PLANET_NUMBER, planetNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_quiz, container, false);

            // Get all the questions and options TextViews
            TextView tvQ1 = (TextView) rootView.findViewById(R.id.question_1);
            TextView tvQ2 = (TextView) rootView.findViewById(R.id.question_2);
            TextView tvQ3 = (TextView) rootView.findViewById(R.id.question_3);

            tvQ1Op1 = (TextView) rootView.findViewById(R.id.q1_option_1);
            tvQ1Op2 = (TextView) rootView.findViewById(R.id.q1_option_2);
            tvQ1Op3 = (TextView) rootView.findViewById(R.id.q1_option_3);
            tvQ1Op4 = (TextView) rootView.findViewById(R.id.q1_option_4);

            tvQ2Op1 = (TextView) rootView.findViewById(R.id.q2_option_1);
            tvQ2Op2 = (TextView) rootView.findViewById(R.id.q2_option_2);
            tvQ2Op3 = (TextView) rootView.findViewById(R.id.q2_option_3);
            tvQ2Op4 = (TextView) rootView.findViewById(R.id.q2_option_4);

            tvQ3Op1 = (TextView) rootView.findViewById(R.id.q3_option_1);
            tvQ3Op2 = (TextView) rootView.findViewById(R.id.q3_option_2);
            tvQ3Op3 = (TextView) rootView.findViewById(R.id.q3_option_3);
            tvQ3Op4 = (TextView) rootView.findViewById(R.id.q3_option_4);

            // Get the radio groups
            q1Options = (RadioGroup) rootView.findViewById(R.id.q1_options);
            q2Options = (RadioGroup) rootView.findViewById(R.id.q2_options);
            q3Options = (RadioGroup) rootView.findViewById(R.id.q3_options);

            // Assign the approprate questions and options to the TextViews based on the planet number
            int planetNumber = getArguments().getInt(Utility.PLANET_NUMBER);

            // Set the title in the quiz fragment based on the planet number
            ((TextView)rootView.findViewById(R.id.tv_quiz_title)).
                    setText(MainActivity.planetNameArray[planetNumber]);

            // Display the questions based on the planet number in the quizQuestionsArray
            tvQ1.setText(Utility.quizQuestionsArray[planetNumber][0]);
            tvQ2.setText(Utility.quizQuestionsArray[planetNumber][1]);
            tvQ3.setText(Utility.quizQuestionsArray[planetNumber][2]);

            // Display the options based on the planet number in the quizAnwersArray
            tvQ1Op1.setText(Utility.quizOptionsArray[planetNumber][0]);
            tvQ1Op2.setText(Utility.quizOptionsArray[planetNumber][1]);
            tvQ1Op3.setText(Utility.quizOptionsArray[planetNumber][2]);
            tvQ1Op4.setText(Utility.quizOptionsArray[planetNumber][3]);

            tvQ2Op1.setText(Utility.quizOptionsArray[planetNumber][4]);
            tvQ2Op2.setText(Utility.quizOptionsArray[planetNumber][5]);
            tvQ2Op3.setText(Utility.quizOptionsArray[planetNumber][6]);
            tvQ2Op4.setText(Utility.quizOptionsArray[planetNumber][7]);

            tvQ3Op1.setText(Utility.quizOptionsArray[planetNumber][8]);
            tvQ3Op2.setText(Utility.quizOptionsArray[planetNumber][9]);
            tvQ3Op3.setText(Utility.quizOptionsArray[planetNumber][10]);
            tvQ3Op4.setText(Utility.quizOptionsArray[planetNumber][11]);

            // Set an onClickListener on the submit button to check answers
            Button submitButton = (Button) rootView.findViewById(R.id.btn_submit);
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswers(); // Call this function to check selected answers
                }
            });

            Button retryButton = (Button) rootView.findViewById(R.id.btn_quiz_retry);
            retryButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRetryPressed();
                }
            });

            return rootView;
        }

        public void checkAnswers() {
            int planetNumber = getArguments().getInt(Utility.PLANET_NUMBER);    // Get the planet number
            int numCorrect = 0; // To count how many correct answers

            // Get the radio groups that include the choices for each of the questions and compare
            // the id of the selected radio button in each group to the values stored inside the
            // quizAnswersArray
            final View view = getView();

            if(null != view) {

                // Check if the correct radio button is selected based on the id of the radio button
                // inside the quizAnswersArray based on the planet number
                if (Utility.quizAnswersArray[planetNumber][0] == q1Options.getCheckedRadioButtonId()) {
                    numCorrect++;
                }
                if (Utility.quizAnswersArray[planetNumber][1] == q2Options.getCheckedRadioButtonId()) {
                    numCorrect++;
                }
                if (Utility.quizAnswersArray[planetNumber][2] == q3Options.getCheckedRadioButtonId()) {
                    numCorrect++;
                }
                ((TextView) view.findViewById(R.id.tv_total_score)).setText(numCorrect + "/3");

                // Hide the submit button
                view.findViewById(R.id.btn_submit).setVisibility(View.INVISIBLE);

                // Make the results layout visible
                View resultsLayout = view.findViewById(R.id.results_layout);
                resultsLayout.setVisibility(View.VISIBLE);

                TextView feedbackText = (TextView) view.findViewById(R.id.tv_score_comments);
                if(numCorrect == 0) {
                    feedbackText.setText(getString(R.string.bad));
                } else if(numCorrect == 1) {
                    feedbackText.setText(getString(R.string.ok));
                } else if(numCorrect == 2) {
                    feedbackText.setText(getString(R.string.good));
                } else if(numCorrect == 3)
                    feedbackText.setText(getString(R.string.great));

                // Measure the dimensions of the results layout
                resultsLayout.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                // Apply a transform animation on the results layout
                int height = resultsLayout.getMeasuredHeight(); // Get the height of the layout
                TranslateAnimation translateAnimation = new TranslateAnimation(
                        Animation.ABSOLUTE,
                        0,
                        Animation.ABSOLUTE,
                        0,
                        Animation.ABSOLUTE,
                        500,
                        Animation.ABSOLUTE,
                        0
                );
                translateAnimation.setDuration(100);                // Set duration
                resultsLayout.startAnimation(translateAnimation);   // start animation
            }
        }

        public void onRetryPressed() {
            final View view = getView();
            if(null != view) {
                final View resultsLayout = view.findViewById(R.id.results_layout);
                final View submitButton = view.findViewById(R.id.btn_submit);
                // Create and apply a transform animation on the results layout
                int resultsLayoutHeight = resultsLayout.getMeasuredHeight(); // Get the height of the results layout
                submitButton.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                final int submitButtonHeight = submitButton.getMeasuredHeight();
                TranslateAnimation translateAnimation = new TranslateAnimation(
                        Animation.ABSOLUTE,
                        0,
                        Animation.ABSOLUTE,
                        0,
                        Animation.ABSOLUTE,
                        0,
                        Animation.ABSOLUTE,
                        resultsLayoutHeight
                );
                translateAnimation.setDuration(100);   // Set duration
                translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // Show the submit button
                        submitButton.setVisibility(View.VISIBLE);
                        // Apply a translate animation on the submit button to
                        // slide it up from the bottom of the screen
                        TranslateAnimation translateAnimation = new TranslateAnimation(
                                Animation.ABSOLUTE,
                                0,
                                Animation.ABSOLUTE,
                                0,
                                Animation.ABSOLUTE,
                                submitButtonHeight,
                                Animation.ABSOLUTE,
                                0
                        );
                        translateAnimation.setDuration(50);
                        submitButton.startAnimation(translateAnimation);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                resultsLayout.startAnimation(translateAnimation);   // start animation

                // Hide the results layout
                resultsLayout.setVisibility(View.GONE);
            }

        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a QuizFragment (defined as a static inner class below).
            return QuizFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            // Show total 9 pages.
            return 9;
        }
    }

    // Item click listener for navigation drawer
    public class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    // Method to select an item from the navigation drawer and
    // update the content accordingly
    private void selectItem(int position) {
        // launch main activity if position selected is 0
        if(position == 0) {
            Intent mainActivityIntent = new Intent(this, MainActivity.class);
            NavUtils.navigateUpFromSameTask(this);
        }

        // set item checked as position
        mDrawerList.setItemChecked(position, true);
        // close the drawer
        mDrawerLayout.closeDrawer(mDrawerList);
    }
}
