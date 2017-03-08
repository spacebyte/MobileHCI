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
 */

public class PresetWorkoutsActivity extends Activity{

    ListView listview;
    Button BackButton;
    TextView ToolbarText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presets_list);

        ToolbarText = (TextView)findViewById(R.id.toolbar_text);
        ToolbarText.setText(R.string.preset_workouts_title);

        listview = (ListView) findViewById(R.id.presets_listview);
        listview.setAdapter(new WorkoutListViewAdapter(this, new String[] { "Workout 1", "Workout 2", "Workout 3" }));

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,  int position, long id) {
                String SELECTED_START = "Marker 4";
                String ORIGIN = "PRESETS";
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

                Intent i = new Intent(PresetWorkoutsActivity.this, ReviewActivity.class);
                i.putExtra("SELECTED_START", SELECTED_START);
                i.putExtra("WORKOUTS", WORKOUTS);
                i.putExtra("ORIGIN", ORIGIN);
                startActivityForResult(i, 0);
            }
        });

        BackButton = (Button)findViewById(R.id.back_button);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PresetWorkoutsActivity.this, CategoryActivity.class);
                startActivityForResult(i, 0);
            }
        });
    }
}
