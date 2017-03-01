package com.example.rachel.mhciproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by jack on 28/02/2017.
 * Activity displaying the three categories:
 *  - Preset workouts
 *  - Custom Workouts
 *  - Most Recent Workout
 */


public class CategoryActivity extends Activity {

    ListView listview;
    Button BackButton;
    TextView ToolbarText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        ToolbarText = (TextView)findViewById(R.id.toolbar_text);
        ToolbarText.setText(R.string.category_list_title);

        listview = (ListView) findViewById(R.id.categories_listview);
        listview.setAdapter(new CategoryListViewAdapter(this, new String[] { "Presets", "Custom", "Most Recent Workout" }));

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

        BackButton = (Button)findViewById(R.id.back_button);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CategoryActivity.this, MainActivity.class);
                startActivityForResult(i, 0);
            }
        });

    }
}

