package com.example.sherin.sqlite_patient_management_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private ListView listView;
    private List<Patient> patientList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        initView();

        loadData();


    }


    private void loadData() {

        MySqlite mySqlite = new MySqlite(this);
        Cursor cursor = mySqlite.getData();

        if (cursor.getCount() > 0) {
            // ---- we receaved data
            while (cursor.moveToNext()) {

                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String _add_ = cursor.getString(2);
                String number = cursor.getString(3);
                String admit = cursor.getString(4);
                String issu = cursor.getString(5);


                Patient patient = new Patient(id, name, _add_, number,admit,issu);

                patientList.add(patient);

            }


            ListAdapter adapter = new ListAdapter(this, patientList);
            listView.setAdapter(adapter);

        } else {
            // --- no data found

        }
    }

    private void initView() {

        listView =  findViewById(R.id.lvId);
    }
}