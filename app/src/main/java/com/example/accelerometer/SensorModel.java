package com.example.accelerometer;

public class SensorModel {
    Integer id;
    String x, y, z;

    public SensorModel(String x, String y, String z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public SensorModel(Integer id, String x, String y, String z) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getZ() {
        return z;
    }

    public void setZ(String z) {
        this.z = z;
    }
}
