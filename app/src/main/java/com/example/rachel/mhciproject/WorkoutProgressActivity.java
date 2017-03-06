package com.example.rachel.mhciproject;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;
import android.graphics.Color;
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
    private TextView nextActivityText = null;
    private TextView upNext = null;

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
        nextActivityText = (TextView) findViewById(R.id.nextActivityText);
        upNext = (TextView) findViewById(R.id.upNext);

        // set up a handler for taps on the start/stop scanning button
        toggleScan = (Button) findViewById(R.id.btnToggleScan);
        toggleScan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                toggleScan();
            }

        });

        // retrieve the BluetoothManager instance and check if Bluetooth is enabled. If not the
        // user will be prompted to enable it and the response will be checked in onActivityResult
        final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        bleDev = bluetoothManager.getAdapter();
        if (bleDev == null || !bleDev.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
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

    private void startWorkout() {
        toggleScan.setText("Pause Workout");
        activityImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.running));
        activityImage.setVisibility(View.VISIBLE);
        progressText.setTextColor(Color.rgb(127,127,127));
        progressText.setVisibility(View.VISIBLE);
        upNext.setVisibility(View.VISIBLE);
        nextActivityImage.setVisibility(View.VISIBLE);
        nextActivityText.setVisibility(View.VISIBLE);

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

    // class implementing BleScanner callbacks
    private ScanCallback bleScanCallback = new ScanCallback() {

        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);
            progressText.setText("test1");

            final BluetoothDevice dev = result.getDevice();

            if(dev != null && isScanning) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressText.setText("something");
                    }
                });
            }
        }

        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            progressText.setText("batch...");
            super.onBatchScanResults(results);
            Log.d(TAG, "BatchScanResult(" + results.size() + " results)");
        }

        @Override
        public void onScanFailed(int errorCode) {
            super.onScanFailed(errorCode);
            progressText.setText("failed...");
            Log.w(TAG, "ScanFailed(" + errorCode + ")");
        }
    };

}
