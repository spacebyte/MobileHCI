package com.example.rachel.mhciproject;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class FragmentPageAdapterHistor extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public FragmentPageAdapterHistor(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Dashboard tab1 = new Dashboard();
                return tab1;
            case 1:
                History tab2 = new History();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}