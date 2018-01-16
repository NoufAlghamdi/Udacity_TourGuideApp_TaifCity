package com.example.android.taifcity.activity;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.taifcity.adapter.CategoryAdapter;
import com.example.android.taifcity.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        CategoryAdapter adapter = new CategoryAdapter(MainActivity.this,
                getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = findViewById(R.id.tabs);

        // Connect the tab layout with the view pager. This will
        //   1. Update the tab layout when the view pager is swiped
        //   2. Update the view pager when a tab is selected
        //   3. Set the tab layout's tab names with the view pager's adapter's titles
        //      by calling onPageTitle()
        tabLayout.setupWithViewPager(viewPager);

        //Set the icon for each tab, and the contentDescription of the icons.
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_main_tab1park_24dp);
        tabLayout.getTabAt(0).setContentDescription(getString(R.string.main_tab1name));
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_main_tab2hotel_24dp);
        tabLayout.getTabAt(1).setContentDescription(getString(R.string.main_tab2name));
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_main_tab3restaurant_24dp);
        tabLayout.getTabAt(2).setContentDescription(getString(R.string.main_tab3name));
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_main_tab4mall_24dp);
        tabLayout.getTabAt(3).setContentDescription(getString(R.string.main_tab4name));

        //Set the color of the tab icons:
        //The color of the selected tab icon is iconTextColor, and the color of the unselected tab icon is unselectedIconColor from the color.xml.
        tabLayout.getTabAt(0).getIcon().setColorFilter(getResources().getColor(R.color.iconTextColor), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(1).getIcon().setColorFilter(getResources().getColor(R.color.unselectedIconColor), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(2).getIcon().setColorFilter(getResources().getColor(R.color.unselectedIconColor), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(3).getIcon().setColorFilter(getResources().getColor(R.color.unselectedIconColor), PorterDuff.Mode.SRC_IN);

        //Set a listener to control the color of the selected and the unselected tab icon color.
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(getResources().getColor(R.color.iconTextColor), PorterDuff.Mode.SRC_IN);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(getResources().getColor(R.color.unselectedIconColor), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}