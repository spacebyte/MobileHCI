package com.example.rachel.mhciproject;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jack on 01/03/2017.
 */

public class WorkoutProgressActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private BluetoothAdapter bleDev = null;
    private BluetoothLeScanner scanner = null;
    private Button toggleScan = null;
    private TextView progressText = null;
    private ImageView activityImage = null;
    private ImageView nextActivityImage = null;
    private TextView upNext = null;
    private Button btnDoneWorkout = null;
    private int workoutCount = 0;
    private ArrayList<String> beaconNames = new ArrayList<>(Arrays.asList("Kontakt 0k30","beaconToy"));

    private Handler mHandler = new Handler();
    private long startTime;
    private long elapsedTime;
    private final int REFRESH_RATE = 100;
    private String hours,minutes,seconds,milliseconds;
    private long secs,mins,hrs,msecs;
    private boolean stopped = false;

    private MediaPlayer mp;

    // request ID for enabling Bluetooth
    private static final int REQUEST_ENABLE_BT = 1000;

    private boolean isScanning = false;
    private int scanMode = ScanSettings.SCAN_MODE_BALANCED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_progress);

        progressText = (TextView) findViewById(R.id.progressText);
        activityImage = (ImageView) findViewById(R.id.activityImage);
        nextActivityImage = (ImageView) findViewById(R.id.nextActivityImage);
        upNext = (TextView) findViewById(R.id.upNext);
        btnDoneWorkout = (Button) findViewById(R.id.btnDoneWorkout);
        toggleScan = (Button) findViewById(R.id.btnToggleScan);

        ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.BLUETOOTH,
                                Manifest.permission.BLUETOOTH_ADMIN,
                                Manifest.permission.ACCESS_COARSE_LOCATION},
                        1);

        toggleScan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                toggleScan();
            }

        });

        btnDoneWorkout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                setToRunning();
            }

        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_ENABLE_BT) {
            if(resultCode != RESULT_OK) {
                Toast.makeText(this, "Bluetooth not enabled!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Bluetooth enabled successfully", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void toggleScan() {
        if(!isScanning)
            startWorkout();
        else
            pauseWorkout();
    }

    private void pauseWorkout() {
        mHandler.removeCallbacks(startTimer);
        stopped = true;

        if(scanner != null && isScanning) {
            Toast.makeText(this, "Stopping BLE scan...", Toast.LENGTH_SHORT).show();
            isScanning = false;
            Log.i(TAG, "Scan stopped");
            scanner.stopScan(bleScanCallback);
        }

        toggleScan.setText("Resume Workout");
        activityImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.pause));
        progressText.setTextColor(Color.rgb(211,211,211));
    }

    private Runnable startTimer = new Runnable() {
        public void run() {
            elapsedTime = System.currentTimeMillis() - startTime;
            updateTimer(elapsedTime);
            mHandler.postDelayed(this,REFRESH_RATE);
        }
    };

    private void startWorkout() {
        if(stopped){
            startTime = System.currentTimeMillis() - elapsedTime;
        }
        else{
            startTime = System.currentTimeMillis();
        }
        mHandler.removeCallbacks(startTimer);
        mHandler.postDelayed(startTimer, 0);

        ((TextView)findViewById(R.id.timer)).setVisibility(View.VISIBLE);

        toggleScan.setText("Pause Workout");
        activityImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.running));
        activityImage.setVisibility(View.VISIBLE);
        progressText.setTextColor(Color.rgb(107,130,253));
        progressText.setVisibility(View.VISIBLE);
        upNext.setVisibility(View.VISIBLE);
        nextActivityImage.setVisibility(View.VISIBLE);

        if(scanner == null) {
            scanner = bleDev.getBluetoothLeScanner();
            if(scanner == null) {
                // probably tried to start a scan without granting Bluetooth permission
                Toast.makeText(this, "Failed to start scan (BT permission granted?)", Toast.LENGTH_LONG).show();
                Log.w(TAG, "Failed to get BLE scanner instance");
                return;
            }
        }

        Toast.makeText(this, "Starting BLE scan...", Toast.LENGTH_SHORT).show();

        scanner.startScan(bleScanCallback);
        isScanning = true;
    }

    private void setToRunning(){
        btnDoneWorkout.setVisibility(View.INVISIBLE);
        activityImage.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.running));
        progressText.setText("Running");
        stopped = false;
        ((TextView)findViewById(R.id.timer)).setText("00:00:00");
        mHandler.removeCallbacks(startTimer);
        mHandler.postDelayed(startTimer, 0);
        if(workoutCount==1) {
            nextActivityImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.squat));
            upNext.setText("Up Next: Jumping Squats");
        }
        if(workoutCount==2){
            nextActivityImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.finish));
            upNext.setText("Last Leg!");
        }
    }

    private void incrementWorkoutCount(){
        this.workoutCount++;
    }

    private void nextWorkout(){
        switch(workoutCount){
            case 0:
                activityImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.push_up));
                progressText.setText("Flat Pushup");

                ((TextView) findViewById(R.id.timer)).setText("10");

                mp = MediaPlayer.create(this, R.raw.workout_one);
                mp.start();
                break;
            case 1:
                activityImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.squat));
                progressText.setText("Jumping Squats");


                ((TextView) findViewById(R.id.timer)).setText("25");

                mp = MediaPlayer.create(this, R.raw.workout_two);
                mp.start();
                break;
            default:
                setToRunning();
        }
        mHandler.removeCallbacks(startTimer);
        stopped = true;
        btnDoneWorkout.setVisibility(View.VISIBLE);
        nextActivityImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.running));
        upNext.setText("Up Next: Running");
    }

    // class implementing BleScanner callbacks
    private ScanCallback bleScanCallback = new ScanCallback() {

        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);

            final BluetoothDevice dev = result.getDevice();

            if(dev != null && isScanning) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (dev.getName() != null && dev.getName().equals(beaconNames.get(workoutCount))) {
                            nextWorkout();
                            incrementWorkoutCount();
                        }
                    }
                });
            }
        }

        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            super.onBatchScanResults(results);
            Log.d(TAG, "BatchScanResult(" + results.size() + " results)");
        }

        @Override
        public void onScanFailed(int errorCode) {
            super.onScanFailed(errorCode);
            Log.w(TAG, "ScanFailed(" + errorCode + ")");
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // retrieve the BluetoothManager instance and check if Bluetooth is enabled. If not the
                    // user will be prompted to enable it and the response will be checked in onActivityResult
                    final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
                    bleDev = bluetoothManager.getAdapter();
                    if (bleDev == null || !bleDev.isEnabled()) {
                        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                    }
                } else {
                    break;
                }
                return;
            }
        }
    }

    private void updateTimer (float time){
        secs = (long)(time/1000);
        mins = (long)((time/1000)/60);
        hrs = (long)(((time/1000)/60)/60);

		/* Convert the seconds to String
		 * and format to ensure it has
		 * a leading zero when required
		 */
        secs = secs % 60;
        seconds=String.valueOf(secs);
        if(secs == 0){
            seconds = "00";
        }
        if(secs <10 && secs > 0){
            seconds = "0"+seconds;
        }

		/* Convert the minutes to String and format the String */

        mins = mins % 60;
        minutes=String.valueOf(mins);
        if(mins == 0){
            minutes = "00";
        }
        if(mins <10 && mins > 0){
            minutes = "0"+minutes;
        }

        /* Convert the hours to String and format the String */

        hours=String.valueOf(hrs);
        if(hrs == 0){
            hours = "00";
        }
        if(hrs <10 && hrs > 0){
            hours = "0"+hours;
        }

    	/* Although we are not using milliseconds on the timer in this example
    	 * I included the code in the event that you wanted to include it on your own
    	 */
        milliseconds = String.valueOf((long)time);
        if(milliseconds.length()==2){
            milliseconds = "0"+milliseconds;
        }
        if(milliseconds.length()<=1){
            milliseconds = "00";
        }
        milliseconds = milliseconds.substring(milliseconds.length()-3, milliseconds.length()-2);

		/* Setting the timer text to the elapsed time */
        ((TextView)findViewById(R.id.timer)).setText(hours + ":" + minutes + ":" + seconds);
    }
}
