package com.example.sherin.sqlite_patient_management_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    private EditText etName, etAdd, etNumber,etAdmit,etIssu;

    private String id, name, add, number,admit,issu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            id = bundle.getString("id");
            name = bundle.getString("name");
            add = bundle.getString("add");
            number = bundle.getString("number");
            admit = bundle.getString("admit");

            issu = bundle.getString("issu");

        }

        initView();
    }

    private void initView() {
        etName = findViewById(R.id._name);
        etAdd = findViewById(R.id._add);
        etNumber = findViewById(R.id._number);
        etAdmit = findViewById(R.id._admit);

        etIssu = findViewById(R.id._issu);


        etName.setText(name);
        etAdd.setText(add);
        etNumber.setText(number);
        etAdmit.setText(admit);
        etIssu.setText(issu);
    }

    public void updateBtn(View view) {


        MySqlite mySqlite = new MySqlite(this);

        String newName = etName.getText().toString().trim();
        String newAdd = etAdd.getText().toString().trim();
        String newNumber = etNumber.getText().toString().trim();
        String newAdmin = etAdmit.getText().toString().trim();
        String newIssu = etIssu.getText().toString().trim();


        int res = mySqlite.updateData(id, newName, newAdd, newNumber,newAdmin,newIssu);

        if (res == -1) {
            Toast.makeText(this, "Not updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Update Successfull", Toast.LENGTH_SHORT).show();
        }
    }
}