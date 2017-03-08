package com.example.rachel.mhciproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;

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
                        String SELECTED_START = "Marker 4";
                        String ORIGIN = "CATEGORIES";
                        HashMap<String, String> WORKOUTS = new HashMap<>();
                        WORKOUTS.put("Sit Ups 1", "10");
                        WORKOUTS.put("Sit Ups 2", "5");
                        WORKOUTS.put("Push Ups 1", "0");
                        WORKOUTS.put("Push Ups 2", "0");
                        WORKOUTS.put("Lunges 1", "5");
                        WORKOUTS.put("Lunges 2", "0");
                        WORKOUTS.put("High Knees 1", "0");
                        WORKOUTS.put("High Knees 2", "0");
                        WORKOUTS.put("Squats 1", "10");
                        WORKOUTS.put("Squats 2", "10");
                        WORKOUTS.put("Jumping Jacks 1", "5");
                        WORKOUTS.put("Jumping Jacks 2", "5");

                        i = new Intent(CategoryActivity.this, ReviewActivity.class);
                        i.putExtra("SELECTED_START", SELECTED_START);
                        i.putExtra("WORKOUTS", WORKOUTS);
                        i.putExtra("ORIGIN", ORIGIN);
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

