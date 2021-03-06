package com.example.accelerometer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "sensor.db";
    private static final String TABLE_NAME = "sensor_table";
    public static final String ID = "id";
    public static final String KOOR_X = "x";
    public static final String KOOR_Y = "y";
    public static final String KOOR_Z = "z";
    private SQLiteDatabase sqLiteDatabase;

    private static final String CREATE_TABLE = "create table " + TABLE_NAME +"("+ID+
            " INTEGER PRIMARY KEY AUTOINCREMENT," + KOOR_X + " TEXT," + KOOR_Y + " TEXT," + KOOR_Z +
            " TEXT);";

    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addSensor(SensorModel sensorModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.KOOR_X, sensorModel.getX());
        contentValues.put(DatabaseHelper.KOOR_Y, sensorModel.getY());
        contentValues.put(DatabaseHelper.KOOR_Z, sensorModel.getZ());
        sqLiteDatabase  = this.getWritableDatabase();
        sqLiteDatabase.insert(DatabaseHelper.TABLE_NAME,null,contentValues);
    }

    public List<SensorModel> getSensorList() {
        String sql = "select * from " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<SensorModel> storeSensor = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String x = cursor.getString(1);
                String y = cursor.getString(2);
                String z = cursor.getString(3);
                storeSensor.add(new SensorModel(id,x,y,z));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeSensor;
    }
}
