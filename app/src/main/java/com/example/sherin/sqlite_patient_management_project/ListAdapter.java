package com.example.sherin.sqlite_patient_management_project;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ListAdapter extends BaseAdapter {

    private Context context;
    private List<Patient> patientList;

    public ListAdapter(Context context, List<Patient> patientList) {
        this.context = context;
        this.patientList = patientList;
    }

    @Override
    public int getCount() {
        return patientList.size();
    }

    @Override
    public Object getItem(int position) {
        return patientList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInflater.inflate(R.layout.sample_layout, null);
        }

        final TextView name, _add_, number,admit,issu;
        name = convertView.findViewById(R.id.tv_name);
        _add_ = convertView.findViewById(R.id.tv_add);
        number = convertView.findViewById(R.id.tv_number);
        admit = convertView.findViewById(R.id.tv_admit);
        issu = convertView.findViewById(R.id.tv_issu);



        name.setText(patientList.get(position).getId() + " , " + patientList.get(position).getName());
        _add_.setText(patientList.get(position).getAdd());
        number.setText(patientList.get(position).getNumber());
        admit.setText(patientList.get(position).getAdmit());
        issu.setText(patientList.get(position).getIssu());


        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you want to delete");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        MySqlite mySqlite = new MySqlite(context);
                        int res = mySqlite.delete(patientList.get(position).getId());

                        if (res == -1) {
                            // ---not able to delete
                            Toast.makeText(context, "Not deleted", Toast.LENGTH_SHORT).show();
                        } else {
                            // --- delete successfull
                            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();

                            patientList.remove(position);
                            notifyDataSetChanged();
                        }
                    }
                });

                builder.setNegativeButton("No", null);
                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String id = patientList.get(position).getId();
                        String name = patientList.get(position).getName();
                        String _add_ = patientList.get(position).getAdd();
                        String number = patientList.get(position).getNumber();
                        String admit = patientList.get(position).getAdmit();

                        String issu = patientList.get(position).getIssu();



                        Intent intent = new Intent(context, UpdateActivity.class);
                        intent.putExtra("id", id);
                        intent.putExtra("name", name);
                        intent.putExtra("_add_", _add_);
                        intent.putExtra("number", number);
                        intent.putExtra("number", admit);
                        intent.putExtra("number", issu);

                        context.startActivity(intent);
                    }
                });

                builder.show();


                return false;
            }
        });


        return convertView;
    }
}