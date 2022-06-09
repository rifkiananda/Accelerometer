package com.example.accelerometer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor;
    TextView textX, textY, textZ;

    Button button_database;
    boolean flag = false;
    int interval = 2000;
    Handler handler;
    private final Runnable processSensors = new Runnable() {
        @Override
        public void run() {
            flag = true;
            handler.postDelayed(this, interval);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();

        textX = findViewById(R.id.textX);
        textY = findViewById(R.id.textY);
        textZ = findViewById(R.id.textZ);
        button_database = findViewById(R.id.buttondatabase);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        final Handler handler = new Handler();
        Runnable refresh = new Runnable() {
            @Override
            public void run() {
                String stringX = textX.getText().toString();
                String stringY = textY.getText().toString();
                String stringZ = textZ.getText().toString();

                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
                SensorModel sensorModel = new SensorModel(stringX,stringY,stringZ);
                databaseHelper.addSensor(sensorModel);
                finish();
                startActivity(getIntent());
                handler.postDelayed(this, 60000);
            }
        };
        handler.postDelayed(refresh, 60000);

        button_database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ViewSensorActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (flag){
            String x = String.valueOf(sensorEvent.values[0]);
            String y = String.valueOf(sensorEvent.values[1]);
            String z = String.valueOf(sensorEvent.values[2]);
            textX.setText(x);
            textY.setText(y);
            textZ.setText(z);

            flag = false;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor,
                SensorManager.SENSOR_DELAY_NORMAL);

        handler.post(processSensors);
    }
}