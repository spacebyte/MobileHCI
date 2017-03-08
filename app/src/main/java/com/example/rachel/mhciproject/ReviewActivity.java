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

public class ReviewActivity extends FragmentActivity implements OnMapReadyCallback {

    Button BackButton, StartButton;
    TextView ToolbarText;

    String SELECTED_START = "Marker 1";
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

    HashMap<String, IconGenerator> icon_map = new HashMap<>();

    HashMap<String, String> WORKOUTS = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

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
        WORKOUTS = (HashMap<String, String>) intent.getSerializableExtra("WORKOUTS");

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
        int marker_string = 0;
        String[] marker_names = {"Sit Ups 1", "Push Ups 1", "Lunges 1", "High Knees 1", "Squats 1", "Jumping Jacks 1",
                "Sit Ups 2", "Push Ups 2", "Lunges 2", "High Knees 2", "Squats 2", "Jumping Jacks 2"};
        for(LatLng marker_lat_lng: lat_lngs){
            String tag = "Marker " + Integer.toString(marker_id);
            Marker marker = googleMap.addMarker(new MarkerOptions().position(marker_lat_lng));
            if(tag.equals(SELECTED_START)) {
                BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.flags_icon);
                marker.setIcon(icon);
                marker.setTag("START");
            } else {
                String marker_name = marker_names[marker_string];
                String count_text = WORKOUTS.get(marker_name);
                if (count_text != null) {
                    if (!count_text.equals("0")) {
                        BitmapDescriptor icon = fromBitmap((icon_map.get(marker_name.substring(0, marker_name.length() - 2))).makeIcon(count_text));
                        marker.setIcon(icon);

                    }
                }
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
