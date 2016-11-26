package com.hammad.solarsystem.app;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private ViewPager.OnPageChangeListener mOnPageChangeListener;

    // Needed for the navigation bar
    private String[] mDrawerItems;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    // Holds planet names
    public static String[] planetNameArray;

    // Holds planet details text
    public static String[] planetDetailsArray;

    // Holds image references
    public static int[] planetImageArray;

    // Holds YouTube video IDs
    public static String[] planetVideoArray;

    // Planet title and number textViews inside mainactivity
    private TextView planetTitle;
    private TextView planetNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Set ToolBar as ActionBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
        setSupportActionBar(toolbar);

        planetTitle = (TextView) findViewById(R.id.tv_planet_title);
        planetNumber = (TextView) findViewById(R.id.tv_planet_number);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // Initialize the on page change listener callback for the view pager
        mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // Update the planet name and number based on page number
                setPlanetTitleAndNumber(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };

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

        // Initialize the planet name array to hold
        // planet names based on the planet number
        planetNameArray = new String[9];
        planetNameArray[0] = "The Sun";
        planetNameArray[1] = "Mercury";
        planetNameArray[2] = "Venus";
        planetNameArray[3] = "Earth";
        planetNameArray[4] = "Mars";
        planetNameArray[5] = "Jupiter";
        planetNameArray[6] = "Saturn";
        planetNameArray[7] = "Uranus";
        planetNameArray[8] = "Neptune";

        // Initialize the planet image array to hold references
        // to planet images based on the planet number
        planetImageArray = new int[9];
        planetImageArray[0] = R.drawable.sun;
        planetImageArray[1] = R.drawable.mercury;
        planetImageArray[2] = R.drawable.venus;
        planetImageArray[3] = R.drawable.earth;
        planetImageArray[4] = R.drawable.mars;
        planetImageArray[5] = R.drawable.jupiter;
        planetImageArray[6] = R.drawable.saturn;
        planetImageArray[7] = R.drawable.uranus;
        planetImageArray[8] = R.drawable.neptune;

        // Initialize the planet video array to hold IDs
        // of YouTube videos of the planets based on the planet number
        planetVideoArray = new String[9];
        planetVideoArray[0] = "biPLgH5GU3o";
        planetVideoArray[1] = "p4KKFj8dZIw";
        planetVideoArray[2] = "9MVRMzmwubM";
        planetVideoArray[3] = "Q3YYwIsMHzw";
        planetVideoArray[4] = "7I5JTAj2u94";
        planetVideoArray[5] = "ITPizr7Pqgg";
        planetVideoArray[6] = "p32xzRSBXuk";
        planetVideoArray[7] = "vLM30GXhSsI";
        planetVideoArray[8] = "UqVuDhfh9W8";

        // Initialize the planet details array to hold text details
        // of the planets based on the planet number
        planetDetailsArray = new String[9];
        planetDetailsArray[0] = "The Sun is the star at the center of our solar system. It is a massive ball of gases. It is 93 million miles from our home planet, and is 332,946 times more massive than Earth, with a radius of 432,168.6 miles. The composition of the Sun is 91% hydrogen and 8.9% helium. The temperature at the core of the sun is about 27 million degrees Fahrenheit.";
        planetDetailsArray[1] = "Mercury is the planet closest to the Sun in our solar system. Its orbit takes it as close as 29 million miles and as far as 43 million miles from the sun. Mercury's surface temperatures can reach up to 800 degrees Fahrenheit and at night, drop to -290 degrees Fahrenheit. A solar day on mercury is equal to 175.97 Earth days. Mercury does not have an atmosphere; instead it has a thin exosphere which is resulted from the solar wind from the Sun which bombards Mercury's surface along with other striking micrometeoroids.";
        planetDetailsArray[2] = "Venus is the second planet from the sun and the planet closest to the Earth. It has a very thick atmosphere which traps heat in a greenhouse effect, making it the hottest planet in our solar system. Venus has a radius of 3760.4 miles, roughly the same as Earth. A day on Venus is equal to 243 Earth days. Its distance on average from the Sun is about 67 million miles. It takes Venus 225 Earth days to complete an orbit around the sun. Venus has an atmosphere that consists mainly of carbon dioxide, which traps sun's heat, resulting in surface temperatures on Venus higher than 880 degrees Fahrenheit.";
        planetDetailsArray[3] = "Earth is the fifth largest planet in our solar system. It has a radius of 3959 miles. Its distance from the sun is 93 million miles, which is equal to one astronomical unit. Sunlight takes about 8 minutes to get to Earth. Earth is composed of four layers: an inner core at the center of the planet, covered by the outer core, mantle and crust. The inner core is solid, made up of iron and nickel metals. The outer core is also made up of iron and nickel but is liquid. The mantle is a mixture of hot and molten rock, and the crust, the outermost layer, is divided into huge plates that float on the mantle.";
        planetDetailsArray[4] = "Mars is a rocky planet about half the size of Earth, with a radius of 2106.1 miles. The surface temperature on Mars ranges from -225 degrees Fahrenheit to 70 degrees Fahrenheit. Due to the tilt of its rotational axis like Earth, Mars also experiences seasons. Mars's thin atmosphere and cold temperatures do not allow liquid water to exist on its surface. However, in 2008, NASA's spacecraft Phoenix landed on Mars and found signs of the possibility of inhabiting the planet, including the occasional presence of liquid water and potentially favorable soil chemistry.";
        planetDetailsArray[5] = "Jupiter is the largest planet in our solar system. Its radius is 43,440.7 miles making it 11 times wider than Earth. It is 484 million miles from the sun which means that it takes sunlight 43 minutes to travel from the Sun to Jupiter. One Jupiter day takes about 10 Earth hours, making it the shortest day in the solar system. Its atmosphere is mostly hydrogen and helium which has caused it to earn its reputation as a gas giant. Jupiter is said to have the same composition as a star, but it just did not grow massive enough to ignite.";
        planetDetailsArray[6] = "Saturn is the second largest planet in our solar system after Jupiter. It has a radius of 36,183.7 miles. Like Jupiter, its composition is mainly hydrogen and helium. Saturn has a volume 755 times greater than the Earth. The center of the planet is a dense core of rock, ice, water and other solidified compounds due to the intense pressure and heat. The center layer is covered by liquid metallic hydrogen inside a layer of liquid nitrogen. Saturn has the most spectacular rings in our solar system. They are believed to be composed of pieces of comets, asteroids, or shattered moons. The rings of Saturn have a thickness of about one kilometer and span up to 175000 miles.";
        planetDetailsArray[7] = "Uranus is a very cold and windy planet, an ice giant. Its radius is 15,759.2 miles, 4 times wider than the Earth. A solar day on Uranus is 17 hours and 14 minutes. It is, on average, 1.8 billion miles from the Sun. From this distance, sunlight takes 2 hours and 40 minutes to travel from the Sun to Uranus. Most of Uranus's mass is made up of water, methane and ammonia. Temperature near the core can go up to 9000 degrees Fahrenheit. The atmosphere of Uranus is mostly hydrogen and helium, with a small amount of methane, water and ammonia. The methane in Uranus's atmosphere is what gives the planet its blue-green color.";
        planetDetailsArray[8] = "The second ice giant, Neptune is the last planet in our solar system. With a distance of nearly 2.8 billion miles from the Sun, it completes an orbit around the Sun every 165 years. Its radius is 15,299.4 miles, which is 3.8647 times that of the Earth. Neptune's atmosphere, like other large planets in the solar system, is mainly hydrogen and helium, with traces of methane, water, and ammonia. Like Uranus, the methane in Neptune's atmosphere gives it its blue color.";
    }

    @Override
    protected void onResume() {
        super.onResume();

        // To be able to the set the right info when the activity is first created
        // or such as when device orientation changes
        setPlanetTitleAndNumber(mViewPager.getCurrentItem());

        // Add a page change listener to this view pager to be able to change the title and
        // number of the planet when the page is changed
        mViewPager.addOnPageChangeListener(mOnPageChangeListener);

        //Show discover option in the navigation drawer as selected
        selectItem(0);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Remove the page change listener for this view pager
        mViewPager.removeOnPageChangeListener(mOnPageChangeListener);
    }

    // Update the planet name and number based on page number
    // and the image navigation icons white or black
    public void setPlanetTitleAndNumber(int planetNum) {
        ImageView leftIcon = (ImageView) findViewById(R.id.iv_icon_left);
        ImageView rightIcon = (ImageView) findViewById(R.id.iv_icon_right);
        if(planetNum != 0)
            leftIcon.setColorFilter(Color.WHITE);
        else
            leftIcon.setColorFilter(Color.BLACK);
        if (planetNum != 8)
            rightIcon.setColorFilter(Color.WHITE);
        else
            rightIcon.setColorFilter(Color.BLACK);

        planetTitle.setText(planetNameArray[planetNum]);
        planetNumber.setText(planetNum+1 + "/9");
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        // Total number of pages to show
        public final int NUM_PAGES = 9; // For 8 planets and the sun

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        // getItem is called to instantiate the fragment for the given page.
        @Override
        public Fragment getItem(int position) {
            // Return a PlanetFragment (defined as a static inner class below).
            return PlanetFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            // Show NUM_PAGES total pages.
            return NUM_PAGES;
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
        // launch quiz activity if position selected is 1
        if(position == 1) {
            Intent quizActivityIntent = new Intent(this, QuizActivity.class);
            // Send the page number in with the intent
            quizActivityIntent.putExtra(Utility.PLANET_NUMBER, mViewPager.getCurrentItem());
            startActivity(quizActivityIntent);
        }

        // set item checked as position
        mDrawerList.setItemChecked(position, true);
        // close the drawer
        mDrawerLayout.closeDrawer(mDrawerList);
    }
}
