package com.example.sherin.sqlite_patient_management_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySqlite extends SQLiteOpenHelper {

    private static final String NAME = "mydb.db";
    private static final int VERSION = 1;
    private static final String TABLE_NAME = "student_info";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_ADD = "_add_";
    private static final String COL_NUMBER = "number";
    private static final String COL_ADMIT = "admit";
    private static final String COL_ISSU = "issu";



    public MySqlite(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = " CREATE TABLE " + TABLE_NAME + " ( " + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" +
                " ,  " + COL_NAME + " TEXT , " + COL_ADD + " TEXT , " + COL_NUMBER + " TEXT,"+COL_ADMIT +" TEXT" +
                " ,"+ COL_ISSU +" TEXT )";


        try {
            db.execSQL(sql);
           // onCreate(db);
            Log.i("db_info", "Table is created");
        } catch (Exception e) {

            Log.i("db_info", "table not created : " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // -- drop table
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME ;

        try {
            db.execSQL(sql);
            onCreate(db);
            Log.i("db_info", "Upgrade is exucuted successfully");
        } catch (Exception e) {
            Log.i("db_info", "Upgrade is not called : " + e.getMessage());
        }

        // -- when we need alter
        /*String sql1 = "ALTER TABLE " + TABLE_NAME + " ADD COLUMN NEW_COLUMN TEXT";
        db.execSQL(sql1);*/
    }


    // ---- for save into the local database
    public long insertData(String name, String _add_, String number,String admit,String issu) {

        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COL_NAME, name);
        values.put(COL_ADD, _add_);
        values.put(COL_NUMBER, number);
        values.put(COL_ADMIT, admit);
        values.put(COL_ISSU, issu);


        return database.insert(TABLE_NAME, null, values);
    }

    // --- fot getting the data from local database
    public Cursor getData() {

        SQLiteDatabase database = getReadableDatabase();

        String sql = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = database.rawQuery(sql, null);

        return cursor;
    }

    public int delete(String id) {

        SQLiteDatabase database = getWritableDatabase();

        return database.delete(TABLE_NAME, COL_ID + " = ? ", new String[]{id});
      //  return database.delete(TABLE_NAME, COL_NUMBER + " = ? ", new String[]{});


    }

    public int updateData(String id, String name, String _add_, String number,String admit,String issu){

        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_ADD, _add_);
        values.put(COL_NUMBER, number);
        values.put(COL_ADMIT, admit);

        values.put(COL_ISSU, issu);


        return database.update(TABLE_NAME, values, COL_ID + " = ? ", new String[]{id});
    }

}