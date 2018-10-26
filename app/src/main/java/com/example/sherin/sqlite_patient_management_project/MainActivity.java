package com.example.sherin.sqlite_patient_management_project;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etAdd, etNumber,etAdmit,etIssu;
    private TextView tvShowData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        etName = findViewById(R.id.etName);
        etAdd = findViewById(R.id.etAdd);
        etNumber = findViewById(R.id.etNumber);
        etAdmit = findViewById(R.id.etAdmitDate);

        etIssu = findViewById(R.id.etIssuDate);

        tvShowData = findViewById(R.id.tvViewData);
    }


    public void saveData(View view) {

        String name = etName.getText().toString().trim();
        String add = etAdd.getText().toString().trim();
        String number = etNumber.getText().toString().trim();
        String admit = etAdmit.getText().toString().trim();

        String issu = etIssu.getText().toString().trim();


        if (name.isEmpty()) {

            etName.setError("pls enter name");
            etName.requestFocus();
            return;
        }

        if (add.isEmpty()) {

            etAdd.setError("pls enter add");
            etAdd.requestFocus();
            return;
        }
        if (number.isEmpty()) {

            etNumber.setError("pls enter number");
            etNumber.requestFocus();
            return;
        }
        if (admit.isEmpty()) {

            etAdmit.setError("pls enter admit date");
            etAdmit.requestFocus();
            return;
        }

        if (issu.isEmpty()) {

            etIssu.setError("pls enter issu date ");
            etIssu.requestFocus();
            return;
        }


        // All is okay

        MySqlite mySqlite = new MySqlite(this);

        long checker = mySqlite.insertData(name, add, number,admit,issu);

        if (checker == -1) {
            Toast.makeText(this, "Failed to insert data", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Successfully saved", Toast.LENGTH_SHORT).show();
        }
    }

    public void viewData(View view) {

        Intent intent = new Intent(this, ListActivity.class);

        startActivity(intent);


    }
}
