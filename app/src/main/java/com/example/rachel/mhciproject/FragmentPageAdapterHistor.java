package com.example.rachel.mhciproject;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar;

/**
 * Created by rachel on 07/03/17.
 */

public class FragmentPageAdapterHistor extends FragmentActivity {

    private android.app.Fragment fragment;

            // the content of our main layout with the specified fragment;
            // that's why we declared an id for the main layout.

    public void onTabSelected(TabLayout.Tab tab, android.app.FragmentTransaction ft) {
        ft.replace(R.id.activity_main, fragment);
    }

            // When a tab is unselected, we have to hide it from the user's view.
    public void onTabUnselected(TabLayout.Tab tab, android.app.FragmentTransaction ft) {
        ft.remove(fragment);
    }

            // Nothing special here. Fragments already did the job.
    public void onTabReselected(TabLayout.Tab tab, android.app.FragmentTransaction ft) {

    }
}