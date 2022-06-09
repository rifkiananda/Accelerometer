package com.example.accelerometer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class ViewSensorActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sensor);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        List<SensorModel> sensorModel = databaseHelper.getSensorList();

        if (sensorModel.size() > 0){
            SensorAdapter sensorAdapter = new SensorAdapter(sensorModel,ViewSensorActivity.this);
            recyclerView.setAdapter(sensorAdapter);
        }else{
            
        }
    }
}