package com.example.rachel.mhciproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.List;

/**
 * Created by jack on 28/02/2017.
 */

public class CreateCustomWorkoutActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    Button BackButton, NextButton;
    TextView ToolbarText;
    String SELECTED_START;
    String workout_name;

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

    List<Marker> map_markers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_custom_workout);

        ToolbarText = (TextView)findViewById(R.id.toolbar_text);
        ToolbarText.setText(R.string.create_custom_workout_title);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        BackButton = (Button)findViewById(R.id.back_button);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i = new Intent(CreateCustomWorkoutActivity.this, CustomWorkoutsActivity.class);
                startActivityForResult(i, 0);
            }
        });

        NextButton = (Button)findViewById(R.id.next_button);
        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i = new Intent(CreateCustomWorkoutActivity.this, BuildWorkoutActivity.class);
                if(SELECTED_START != null) {
                    i.putExtra("SELECTED_START", SELECTED_START);
                    startActivityForResult(i, 0);
                } else {
                    Toast.makeText(CreateCustomWorkoutActivity.this, "Select a starting point", Toast.LENGTH_SHORT).show();
                }
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter workout name");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                workout_name = input.getText().toString();
            }
        });

        builder.show();

        NumberPicker np = (NumberPicker) findViewById(R.id.lap_picker);
        String[] nums = new String[20];
        for(int i=0; i<nums.length; i++)
            nums[i] = Integer.toString(i+1);
        np.setMinValue(1);
        np.setMaxValue(20);
        np.setWrapSelectorWheel(false);
        np.setDisplayedValues(nums);
        np.setValue(1);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        int marker_id = 1;
        for(LatLng marker_lat_lng: lat_lngs){
            System.out.println(marker_id);
            String tag = "Marker " + Integer.toString(marker_id);
            Marker marker = googleMap.addMarker(new MarkerOptions().position(marker_lat_lng));
            marker.setTag(tag);
            map_markers.add(marker);
            marker_id++;
        }

        LatLng centre = new LatLng(55.869977, -4.282648);
        CameraPosition starting_view = new CameraPosition.Builder()
                .target(centre)
                .zoom(16)
                .bearing(0)
                .tilt(0)
                .build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(starting_view));
        googleMap.setOnMarkerClickListener(this);
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        for(Marker _marker: map_markers) {
            _marker.setTitle("");
            _marker.hideInfoWindow();
            _marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        }
        marker.setTitle("START");
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.flags_icon);
        marker.setIcon(icon);
        SELECTED_START = (String) marker.getTag();
        return true;
    }
}
