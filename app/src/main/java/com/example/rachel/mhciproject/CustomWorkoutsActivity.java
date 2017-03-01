package com.example.rachel.mhciproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by jack on 28/02/2017.
 */

public class CustomWorkoutsActivity extends Activity{

    ListView listview;
    Button BackButton, CreateNewWorkoutButton;
    TextView ToolbarText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customs_list);

        ToolbarText = (TextView)findViewById(R.id.toolbar_text);
        ToolbarText.setText(R.string.custom_workouts_title);

        listview = (ListView) findViewById(R.id.customs_listview);
        listview.setAdapter(new WorkoutListViewAdapter(this, new String[] { "Workout 1", "Workout 2", "Workout 3" }));

        BackButton = (Button)findViewById(R.id.back_button);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CustomWorkoutsActivity.this, CategoryActivity.class);
                startActivityForResult(i, 0);
            }
        });

        CreateNewWorkoutButton = (Button)findViewById(R.id.create_workout_button);
        CreateNewWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CustomWorkoutsActivity.this, CreateCustomWorkoutActivity.class);
                startActivityForResult(i, 0);
            }
        });
    }
}
