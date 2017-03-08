package com.example.rachel.mhciproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by jack on 28/02/2017.
 */

public class CustomWorkoutsActivity extends Activity{

    Button BackButton, CreateNewWorkoutButton;
    TextView ToolbarText;

    String SELECTED_START, ORIGIN;
    HashMap<String, String> WORKOUTS = new HashMap<>();


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customs_list);

        SELECTED_START = "Marker 4";
        ORIGIN = "CUSTOMS";
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

        ToolbarText = (TextView)findViewById(R.id.toolbar_text);
        ToolbarText.setText(R.string.custom_workouts_title);

        mRecyclerView = (RecyclerView) findViewById(R.id.customs_listview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new WorkoutListViewAdapter(new String[] { "Workout 1", "Workout 2", "Workout 3","Workout 1", "Workout 2", "Workout 3","Workout 1", "Workout 2", "Workout 3","Workout 1", "Workout 2", "Workout 3","Workout 1", "Workout 2", "Workout 3","Workout 1", "Workout 2", "Workout 3","Workout 1", "Workout 2", "Workout 3","Workout 1", "Workout 2", "Workout 3","Workout 1", "Workout 2", "Workout 3", });
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, mRecyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent i = new Intent(CustomWorkoutsActivity.this, ReviewActivity.class);
                        i.putExtra("SELECTED_START", SELECTED_START);
                        i.putExtra("WORKOUTS", WORKOUTS);
                        i.putExtra("ORIGIN", ORIGIN);
                        startActivityForResult(i, 0);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(CustomWorkoutsActivity.this);
                        builder.setTitle("WORKOUT DETAILS");
                        String message = "MESSAGE";
                        builder.setView(R.layout.workout_preview);
                        builder.setNegativeButton("BACK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.setPositiveButton("START", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent i = new Intent(CustomWorkoutsActivity.this, ReviewActivity.class);
                                i.putExtra("SELECTED_START", SELECTED_START);
                                i.putExtra("WORKOUTS", WORKOUTS);
                                i.putExtra("ORIGIN", ORIGIN);
                                startActivityForResult(i, 0);
                            }
                        });
                        builder.setCancelable(true);

                        builder.show();
                    }
                })
        );

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
