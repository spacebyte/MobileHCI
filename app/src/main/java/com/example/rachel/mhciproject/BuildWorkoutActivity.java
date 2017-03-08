package com.example.rachel.mhciproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;

import java.util.ArrayList;
import java.util.HashMap;

import static com.google.android.gms.maps.model.BitmapDescriptorFactory.fromBitmap;

/**
 * Created by jack on 01/03/2017.
 */

public class BuildWorkoutActivity extends FragmentActivity implements OnMapReadyCallback {

    Button BackButton, NextButton;
    TextView ToolbarText;

    String SELECTED_START;
    LatLng[] lat_lngs = {
            new LatLng(55.870304, -4.284041),
            new LatLng(55.869582, -4.284033),
            new LatLng(55.869046, -4.283958),
            new LatLng(55.868546, -4.283454),
            new LatLng(55.868377, -4.282574),
            new LatLng(55.868895, -4.282467),
            new LatLng(55.869623, -4.282231),
            new LatLng(55.870297, -4.281566),
            new LatLng(55.870809, -4.280450),
            new LatLng(55.871291, -4.279999),
            new LatLng(55.871670, -4.281190),
            new LatLng(55.871339, -4.282359),
            new LatLng(55.870803, -4.283185),
    };

    HashMap<String, IconGenerator> icon_map = new HashMap<>();

    HashMap<String, String> WORKOUTS = new HashMap<>();


    ArrayList<Marker> map_markers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_workout);

        IconGenerator push_up_icon = new IconGenerator(this);
        push_up_icon.setTextAppearance(R.style.iconGenText2);
        push_up_icon.setBackground(getResources().getDrawable(R.drawable.push_up_icon));

        IconGenerator sit_up_icon = new IconGenerator(this);
        sit_up_icon.setTextAppearance(R.style.iconGenText2);
        sit_up_icon.setBackground(getResources().getDrawable(R.drawable.sit_up_icon));

        IconGenerator lunge_icon = new IconGenerator(this);
        lunge_icon.setTextAppearance(R.style.iconGenText2);
        lunge_icon.setBackground(getResources().getDrawable(R.drawable.lunge_icon));

        IconGenerator high_knees_icon = new IconGenerator(this);
        high_knees_icon.setTextAppearance(R.style.iconGenText2);
        high_knees_icon.setBackground(getResources().getDrawable(R.drawable.high_knees_icon));

        IconGenerator squat_icon = new IconGenerator(this);
        squat_icon.setTextAppearance(R.style.iconGenText2);
        squat_icon.setBackground(getResources().getDrawable(R.drawable.squat_icon));

        IconGenerator jumping_jack_icon = new IconGenerator(this);
        jumping_jack_icon.setTextAppearance(R.style.iconGenText2);
        jumping_jack_icon.setBackground(getResources().getDrawable(R.drawable.jumping_jack_icon));

        icon_map.put("Push Ups", push_up_icon);
        icon_map.put("Sit Ups", sit_up_icon);
        icon_map.put("Lunges", lunge_icon);
        icon_map.put("High Knees", high_knees_icon);
        icon_map.put("Squats", squat_icon);
        icon_map.put("Jumping Jacks", jumping_jack_icon);

        Intent intent = getIntent();
        SELECTED_START = intent.getExtras().getString("SELECTED_START");

        ToolbarText = (TextView)findViewById(R.id.toolbar_text);
        ToolbarText.setText(R.string.build_workout_title);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        BackButton = (Button)findViewById(R.id.back_button);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i = new Intent(BuildWorkoutActivity.this, CustomWorkoutsActivity.class);
                i.putExtra("SELECTED_START", SELECTED_START);
                i.putExtra("WORKOUTS", WORKOUTS);
                startActivityForResult(i, 0);
            }
        });

        NextButton = (Button)findViewById(R.id.next_button);
        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i = new Intent(BuildWorkoutActivity.this, ReviewActivity.class);
                i.putExtra("SELECTED_START", SELECTED_START);
                i.putExtra("WORKOUTS", WORKOUTS);
                startActivityForResult(i, 0);
            }
        });

        Integer[] pickers = {R.id.situp_number_picker, R.id.pushup_number_picker, R.id.lunges_number_picker, R.id.highknees_number_picker, R.id.squats_number_picker, R.id.jumpingjacks_number_picker};
        String[] picker_names = {"Sit Ups", "Push Ups", "Lunges", "High Knees", "Squats", "Jumping Jacks"};
        for (int n = 0; n < pickers.length; n++) {
            int picker = pickers[n];
            final String picker_name = picker_names[n];
            NumberPicker np = (NumberPicker) findViewById(picker);
            String[] nums = new String[20];
            for(int i=0; i<nums.length; i++)
                nums[i] = Integer.toString(i*5);
            np.setMinValue(1);
            np.setMaxValue(20);
            np.setWrapSelectorWheel(false);
            np.setDisplayedValues(nums);
            np.setValue(1);
            np.setOnValueChangedListener( new NumberPicker.
                    OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int
                        oldVal, int newVal) {
                    int counter = newVal-1;
                    Integer val_1 = 0, val_2 = 0;
                    while (counter > 0) {
                        if (counter % 2 == 1) {
                            val_1++;
                        } else {
                            val_2++;
                        }
                        counter--;
                    }
                    val_1 = val_1*5;
                    val_2 = val_2*5;
                    Marker marker_1 = null, marker_2 = null;
                    String marker_1_name = picker_name + " 1";
                    String marker_2_name = picker_name + " 2";
                    for (Marker marker: map_markers){
                        if ((marker.getTag()).equals(marker_1_name)){
                            marker_1 = marker;
                        } else if ((marker.getTag()).equals(marker_2_name)){
                            marker_2 = marker;
                        }
                    }
                    if (val_1 != 0) {
                        BitmapDescriptor icon_1 = fromBitmap((icon_map.get(picker_name)).makeIcon(val_1.toString()));
                        marker_1.setIcon(icon_1);
                    } else {
                        marker_1.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                    }
                    if (val_2 != 0) {
                        BitmapDescriptor icon_2 = fromBitmap((icon_map.get(picker_name)).makeIcon(val_2.toString()));
                        marker_2.setIcon(icon_2);
                    } else {
                        marker_2.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                    }
                    WORKOUTS.put(marker_1_name, val_1.toString());
                    WORKOUTS.put(marker_2_name, val_2.toString());
                }

            });
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        int marker_id = 1;
        int marker_string = 0;
        String[] marker_names = {"Sit Ups 1", "Push Ups 1", "Lunges 1", "High Knees 1", "Squats 1", "Jumping Jacks 1",
                                "Sit Ups 2", "Push Ups 2", "Lunges 2", "High Knees 2", "Squats 2", "Jumping Jacks 2"};
        for(LatLng marker_lat_lng: lat_lngs){
            String tag = "Marker " + Integer.toString(marker_id);
            Marker marker = googleMap.addMarker(new MarkerOptions().position(marker_lat_lng).anchor(0.5f,0.5f));
            marker.setTag(tag);

            if(tag.equals(SELECTED_START)){
                BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.flags_icon);
                marker.setIcon(icon);
                marker.setTag("START");
            } else {
                String marker_name = marker_names[marker_string];
                marker.setTag(marker_name);
                marker_string++;
            }
            marker_id++;
            map_markers.add(marker);
        }

        LatLng centre = new LatLng(55.869977, -4.282648);
        CameraPosition starting_view = new CameraPosition.Builder()
                .target(centre)
                .zoom(16)
                .bearing(0)
                .tilt(0)
                .build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(starting_view));
    }
}
