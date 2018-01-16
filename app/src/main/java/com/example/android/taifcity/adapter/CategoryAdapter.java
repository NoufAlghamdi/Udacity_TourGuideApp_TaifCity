package com.example.android.taifcity.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.taifcity.fragments.HotelsFragment;
import com.example.android.taifcity.fragments.MallsFragment;
import com.example.android.taifcity.fragments.ParksFragment;
import com.example.android.taifcity.fragments.RestaurantsFragment;

public class CategoryAdapter extends FragmentPagerAdapter {

    private Context context;

    public CategoryAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.context = context;
    }

    //Return the Fragments that should be displayed for the given page number.
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new ParksFragment();
        } else if (position == 1) {
            return new HotelsFragment();
        } else if (position == 2) {
            return new RestaurantsFragment();
        } else {
            return new MallsFragment();
        }
    }

    //Return the total number of pages.
    @Override
    public int getCount() {
        return 4;
    }
}