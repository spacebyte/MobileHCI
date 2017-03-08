package com.example.rachel.mhciproject;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;

import com.eralp.circleprogressview.CircleProgressView;

public class Dashboard extends Fragment {

    Button ToCategory;
    CircleProgressView PushUpsProgressView, SitUpsProgressView, JumpingJacksProgressView,
            SquatsProgressView, LungesProgressView, HighKneesProgressView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard, container, false);

        LungesProgressView = (CircleProgressView) view.findViewById(R.id.lunges_circle_progress);
        LungesProgressView.setTextEnabled(true);
        LungesProgressView.setInterpolator(new AccelerateDecelerateInterpolator());
        LungesProgressView.setStartAngle(270);
        LungesProgressView.setProgressWithAnimation(65, 2000);

        SitUpsProgressView = (CircleProgressView) view.findViewById(R.id.sit_ups_circle_progress);
        SitUpsProgressView.setTextEnabled(true);
        SitUpsProgressView.setInterpolator(new AccelerateDecelerateInterpolator());
        SitUpsProgressView.setStartAngle(270);
        SitUpsProgressView.setProgressWithAnimation(85, 2000);

        JumpingJacksProgressView = (CircleProgressView) view.findViewById(R.id.jumping_jacks_circle_progress);
        JumpingJacksProgressView.setTextEnabled(true);
        JumpingJacksProgressView.setInterpolator(new AccelerateDecelerateInterpolator());
        JumpingJacksProgressView.setStartAngle(270);
        JumpingJacksProgressView.setProgressWithAnimation(50, 2000);

        PushUpsProgressView = (CircleProgressView) view.findViewById(R.id.push_ups_circle_progress);
        PushUpsProgressView.setTextEnabled(true);
        PushUpsProgressView.setInterpolator(new AccelerateDecelerateInterpolator());
        PushUpsProgressView.setStartAngle(270);
        PushUpsProgressView.setProgressWithAnimation(75, 2000);

        HighKneesProgressView = (CircleProgressView) view.findViewById(R.id.high_knees_circle_progress);
        HighKneesProgressView.setTextEnabled(true);
        HighKneesProgressView.setInterpolator(new AccelerateDecelerateInterpolator());
        HighKneesProgressView.setStartAngle(270);
        HighKneesProgressView.setProgressWithAnimation(60, 2000);

        SquatsProgressView = (CircleProgressView) view.findViewById(R.id.squats_circle_progress);
        SquatsProgressView.setTextEnabled(true);
        SquatsProgressView.setInterpolator(new AccelerateDecelerateInterpolator());
        SquatsProgressView.setStartAngle(270);
        SquatsProgressView.setProgressWithAnimation(100, 2000);

        ToCategory = (Button) view.findViewById(R.id.to_category_button);
        ToCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), CategoryActivity.class);
                startActivityForResult(i, 0);
            }
        });
        return view;
    }
}