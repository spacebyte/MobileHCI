package com.example.rachel.mhciproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.Toast;

import com.eralp.circleprogressview.CircleProgressView;
import com.eralp.circleprogressview.ProgressAnimationListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class MainActivity extends AppCompatActivity {

    private Button ToCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GraphView graph = (GraphView) findViewById(R.id.graph);
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6),
                new DataPoint(5, 1),
                new DataPoint(6, 3)


        });
        graph.addSeries(series);
        series.setSpacing(50);
        series.setDataWidth(0.5);
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"Mon", "Tue", "Wed", "Thur", "Fri","Sat", "Sun"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        CircleProgressView mCircleProgressView = (CircleProgressView) findViewById(R.id.circle_progress_view);
        mCircleProgressView.setTextEnabled(true);
        mCircleProgressView.setInterpolator(new AccelerateDecelerateInterpolator());
        mCircleProgressView.setStartAngle(45);
        mCircleProgressView.setProgressWithAnimation(65, 2000);



        CircleProgressView nCircleProgressView = (CircleProgressView) findViewById(R.id.circle_progress_view2);
        nCircleProgressView.setTextEnabled(true);
        nCircleProgressView.setInterpolator(new AccelerateDecelerateInterpolator());
        nCircleProgressView.setStartAngle(45);
        nCircleProgressView.setProgressWithAnimation(85, 2000);

        CircleProgressView oCircleProgressView = (CircleProgressView) findViewById(R.id.circle_progress_view1);
        oCircleProgressView.setTextEnabled(true);
        oCircleProgressView.setInterpolator(new AccelerateDecelerateInterpolator());
        oCircleProgressView.setStartAngle(45);
        oCircleProgressView.setProgressWithAnimation(50, 2000);

        CircleProgressView pCircleProgressView = (CircleProgressView) findViewById(R.id.circle_progress_view3);
        pCircleProgressView.setTextEnabled(true);
        pCircleProgressView.setInterpolator(new AccelerateDecelerateInterpolator());
        pCircleProgressView.setStartAngle(45);
        pCircleProgressView.setProgressWithAnimation(75, 2000);

        CircleProgressView qCircleProgressView = (CircleProgressView) findViewById(R.id.circle_progress_view4);
        qCircleProgressView.setTextEnabled(true);
        qCircleProgressView.setInterpolator(new AccelerateDecelerateInterpolator());
        qCircleProgressView.setStartAngle(45);
        qCircleProgressView.setProgressWithAnimation(60, 2000);

        CircleProgressView rCircleProgressView = (CircleProgressView) findViewById(R.id.circle_progress_view5);
        rCircleProgressView.setTextEnabled(true);
        rCircleProgressView.setInterpolator(new AccelerateDecelerateInterpolator());
        rCircleProgressView.setStartAngle(45);
        rCircleProgressView.setProgressWithAnimation(100, 2000);

        ToCategory = (Button)findViewById(R.id.to_category_button);
        ToCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CategoryActivity.class);
                startActivityForResult(i, 0);
            }
        });



    }
}
