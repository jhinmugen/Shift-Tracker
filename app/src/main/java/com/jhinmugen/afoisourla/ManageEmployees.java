package com.jhinmugen.afoisourla;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Struct;
import java.util.ArrayList;

public class ManageEmployees extends AppCompatActivity {
    private static final String TAG = "ManageEmployees";
    private TextView name;
    private TextView surname;
    private ArrayList<String> employeesNames;
    private ArrayList<String> employeesSurnames;
    private ArrayList<String> employeesCode;
    private ArrayList<String> employeesStartTimes;
    private ArrayList<String> getEmployeesFinishTimes;
    private CustomListAdapter customListAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_employees);
        initialize();
        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        employeesNames = databaseHandler.getNames();
        employeesSurnames = databaseHandler.getSurname();
        employeesCode = databaseHandler.getCode();
        employeesStartTimes = databaseHandler.getStartTimes();
        getEmployeesFinishTimes = databaseHandler.getFinishTimes();
        updateUI();


    }

    public void initialize() {
        name = (TextView) findViewById(R.id.workingEmployeeName);
        surname = (TextView) findViewById(R.id.workingEmployeeSurname);
        listView = (ListView) findViewById(R.id.employeeList);

    }

    public void updateUI() {
        customListAdapter = new CustomListAdapter(this, employeesNames, employeesSurnames, employeesCode, employeesStartTimes, getEmployeesFinishTimes);
        listView.setAdapter(customListAdapter);
        customListAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        updateUI();
    }
}
