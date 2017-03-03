package com.example.rachel.mhciproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by jack on 01/03/2017.
 */

public class ReviewActivity extends FragmentActivity implements OnMapReadyCallback {

    Button BackButton, StartButton;
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

    ArrayList<Marker> map_markers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        Intent intent = getIntent();
        SELECTED_START = intent.getExtras().getString("SELECTED_START");

        ToolbarText = (TextView)findViewById(R.id.toolbar_text);
        ToolbarText.setText(R.string.review_title);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        BackButton = (Button)findViewById(R.id.back_button);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i = new Intent(ReviewActivity.this, CustomWorkoutsActivity.class);
                startActivityForResult(i, 0);
            }
        });

        StartButton = (Button)findViewById(R.id.start_button);
        StartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i = new Intent(ReviewActivity.this, WorkoutProgressActivity.class);
                startActivityForResult(i, 0);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        int marker_id = 1;
        for(LatLng marker_lat_lng: lat_lngs){
            String tag = "Marker " + Integer.toString(marker_id);
            Marker marker = googleMap.addMarker(new MarkerOptions().position(marker_lat_lng));
            marker.setTag(tag);
            map_markers.add(marker);
            marker_id++;
            if(tag.equals(SELECTED_START)){
                marker.setTitle("START");
                marker.showInfoWindow();
            }
        }

        LatLng centre = new LatLng(55.869977, -4.282648);
        CameraPosition starting_view = new CameraPosition.Builder()
                .target(centre)
                .zoom(16)
                .bearing(0)
                .tilt(0)
                .build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(starting_view));
    }
}
