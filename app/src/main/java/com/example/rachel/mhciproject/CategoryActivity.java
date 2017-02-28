package com.example.rachel.mhciproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by jack on 28/02/2017.
 */


public class CategoryActivity extends Activity {
    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        listview = (ListView) findViewById(R.id.categories_listview);
        listview.setAdapter(new ListViewAdapter(this, new String[] { "Presets", "Custom", "Most Recent Workout" }));

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,  int position, long id) {

                Intent i;

                switch( position )
                {
                    case 0:
                        i = new Intent(CategoryActivity.this, PresetWorkoutsActivity.class);
                        startActivityForResult(i, 0);
                        break;
                    case 1:
                        i = new Intent(CategoryActivity.this, CustomWorkoutsActivity.class);
                        startActivityForResult(i, 0);
                        break;
                    case 2:
                        i = new Intent(CategoryActivity.this, MostRecentWorkoutActivity.class);
                        startActivityForResult(i, 0);
                        break;
                }
            }
        });
    }
}

