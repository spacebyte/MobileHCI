package com.example.rachel.mhciproject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

/**
 * Created by jack on 28/02/2017.
 */

public class PresetWorkoutsActivity extends Activity{

    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presets_list);
        listview = (ListView) findViewById(R.id.presets_listview);
        listview.setAdapter(new ListViewAdapter(this, new String[] { "Workout 1",
                "Workout 2", "Workout 3" }));
    }
}
