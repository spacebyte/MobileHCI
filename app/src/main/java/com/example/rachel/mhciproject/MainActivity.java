package com.example.rachel.mhciproject;

import android.app.ActionBar;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.eralp.circleprogressview.CircleProgressView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import static com.example.rachel.mhciproject.R.layout.history;



import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends FragmentActivity {

    Button ToCategory;
    CircleProgressView PushUpsProgressView, SitUpsProgressView, JumpingJacksProgressView,
            SquatsProgressView, LungesProgressView, HighKneesProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Dashboard"));
        tabLayout.addTab(tabLayout.newTab().setText("History"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final FragmentPageAdapterHistor adapter = new FragmentPageAdapterHistor
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
/*
        GraphView graph = (GraphView) findViewById(R.id.graph);
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6),
                new DataPoint(5, 1),
                new DataPoint(6, 3)


        });
        graph.addSeries(series);
        series.setSpacing(50);
        series.setDataWidth(0.5);
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"Mon", "Tue", "Wed", "Thur", "Fri","Sat", "Sun"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
*/


    }
}


/*public class MainActivity extends FragmentActivity {

    Button ToCategory;
    CircleProgressView PushUpsProgressView, SitUpsProgressView, JumpingJacksProgressView,
            SquatsProgressView, LungesProgressView, HighKneesProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setTabMode(TabLayout.MODE_FIXED);
        tabs.addTab(tabs.newTab().setText("Dashboard"));
        tabs.addTab(tabs.newTab().setText("History"));

        final ActionBar actionBar = getActionBar();

        // Specify that tabs should be displayed in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create a tab listener that is called when the user changes tabs.
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                // show the given tab
            }

            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
                // hide the given tab
            }

            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
                // probably ignore this event
            }
        };

        // Add 3 tabs, specifying the tab's text and TabListener
        for (int i = 0; i < 3; i++) {
            actionBar.addTab(
                    actionBar.newTab()
                            .setText("Tab " + (i + 1))
                            .setTabListener(tabListener));
        }




        /*GraphView graph = (GraphView) findViewById(R.id.graph);
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6),
                new DataPoint(5, 1),
                new DataPoint(6, 3)


        });
        graph.addSeries(series);
        series.setSpacing(50);
        series.setDataWidth(0.5);
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"Mon", "Tue", "Wed", "Thur", "Fri","Sat", "Sun"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        LungesProgressView = (CircleProgressView) findViewById(R.id.lunges_circle_progress);
        LungesProgressView.setTextEnabled(true);
        LungesProgressView.setInterpolator(new AccelerateDecelerateInterpolator());
        LungesProgressView.setStartAngle(270);
        LungesProgressView.setProgressWithAnimation(65, 2000);

        SitUpsProgressView = (CircleProgressView) findViewById(R.id.sit_ups_circle_progress);
        SitUpsProgressView.setTextEnabled(true);
        SitUpsProgressView.setInterpolator(new AccelerateDecelerateInterpolator());
        SitUpsProgressView.setStartAngle(270);
        SitUpsProgressView.setProgressWithAnimation(85, 2000);

        JumpingJacksProgressView = (CircleProgressView) findViewById(R.id.jumping_jacks_circle_progress);
        JumpingJacksProgressView.setTextEnabled(true);
        JumpingJacksProgressView.setInterpolator(new AccelerateDecelerateInterpolator());
        JumpingJacksProgressView.setStartAngle(270);
        JumpingJacksProgressView.setProgressWithAnimation(50, 2000);

        PushUpsProgressView = (CircleProgressView) findViewById(R.id.push_ups_circle_progress);
        PushUpsProgressView.setTextEnabled(true);
        PushUpsProgressView.setInterpolator(new AccelerateDecelerateInterpolator());
        PushUpsProgressView.setStartAngle(270);
        PushUpsProgressView.setProgressWithAnimation(75, 2000);

        HighKneesProgressView = (CircleProgressView) findViewById(R.id.high_knees_circle_progress);
        HighKneesProgressView.setTextEnabled(true);
        HighKneesProgressView.setInterpolator(new AccelerateDecelerateInterpolator());
        HighKneesProgressView.setStartAngle(270);
        HighKneesProgressView.setProgressWithAnimation(60, 2000);

        SquatsProgressView = (CircleProgressView) findViewById(R.id.squats_circle_progress);
        SquatsProgressView.setTextEnabled(true);
        SquatsProgressView.setInterpolator(new AccelerateDecelerateInterpolator());
        SquatsProgressView.setStartAngle(270);
        SquatsProgressView.setProgressWithAnimation(100, 2000);

        ToCategory = (Button)findViewById(R.id.to_category_button);
        ToCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CategoryActivity.class);
                startActivityForResult(i, 0);
            }
        });



    }
}*/
