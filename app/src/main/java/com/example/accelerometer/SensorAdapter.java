package com.example.accelerometer;

import static com.example.accelerometer.R.*;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SensorAdapter extends RecyclerView.Adapter<SensorAdapter.ViewHolder> {

    List<SensorModel> sensor;
    Context context;
    DatabaseHelper databaseHelper;

    public SensorAdapter(List<SensorModel> sensor, Context context) {
        this.sensor = sensor;
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.sensor_item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final SensorModel sensorModel = sensor.get(position);
        holder.textViewID.setText(Integer.toString(sensorModel.getId()));
        holder.textViewX.setText(sensorModel.getX());
        holder.textViewY.setText(sensorModel.getY());
        holder.textViewZ.setText(sensorModel.getZ());
    }

    @Override
    public int getItemCount() {
        return sensor.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewID;
        TextView textViewX;
        TextView textViewY;
        TextView textViewZ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewID = itemView.findViewById(id.textID);
            textViewX = itemView.findViewById(id.textX);
            textViewY = itemView.findViewById(id.textY);
            textViewZ = itemView.findViewById(id.textZ);
        }
    }
}
