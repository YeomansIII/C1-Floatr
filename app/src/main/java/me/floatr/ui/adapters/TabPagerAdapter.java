package me.floatr.ui.adapters;

/**
 * Created by armando on 7/30/15.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import me.floatr.ui.fragments.OffersFragment;
import me.floatr.ui.fragments.RequestsFragment;


public class TabPagerAdapter extends FragmentStatePagerAdapter {
    private String[] tabs = { "Offers", "Requests"};
    public TabPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                //Fragement for Local Recipe
                return new OffersFragment();
            case 1:
                //Fragment for Remote Recipe
                return new RequestsFragment();
        }
        return null;

    }

    @Override
    public int getCount() {

        return 2; //No of Tabs
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

}