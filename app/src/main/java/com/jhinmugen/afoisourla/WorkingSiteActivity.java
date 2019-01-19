package com.jhinmugen.afoisourla;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class WorkingSiteActivity extends AppCompatActivity {
    private static final String TAG = "WorkingSiteActivity";
    private ArrayList<String> workingPlaceNames;
    private ArrayList<String> workingPlaceAdrresses;
    private ArrayList<String> workingPlaceStartTimes;
    private ArrayList<String> workingPlacefinishTimes;
    private ArrayList<String> workingPlaceComments;
    private WorkingPlaceListAdapter workingPlaceListAdapter;
    private TextView workingPlaceName;
    private TextView workingPlaceDestination;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working_site);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initialize();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewEmployActivity();
            }
        });
        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        workingPlaceNames = databaseHandler.getWorkingPlaceNames();
        workingPlaceAdrresses = databaseHandler.getWorkingPlaceAdresses();
        workingPlaceStartTimes = databaseHandler.getStartTimeWorkingPlace();
        workingPlacefinishTimes = databaseHandler.getFinishTimeWorkingPlace();
        workingPlaceComments = databaseHandler.getComments();
        updateUI();

    }


    private void startNewEmployActivity() {
        Intent intent = new Intent(this, NewWorkingPlace.class);
        startActivity(intent);
    }

    private void initialize() {
        listView = (ListView) findViewById(R.id.workingSiteList);
        workingPlaceName = (TextView) findViewById(R.id.workingPlaceName);
        workingPlaceDestination = (TextView) findViewById(R.id.workingPlaceAdress);

    }

    private void updateUI() {
        workingPlaceListAdapter = new WorkingPlaceListAdapter(this, workingPlaceNames, workingPlaceAdrresses, workingPlaceComments, workingPlaceStartTimes, workingPlacefinishTimes);
        listView.setAdapter(workingPlaceListAdapter);
        workingPlaceListAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        workingPlaceNames = databaseHandler.getWorkingPlaceNames();
        workingPlaceAdrresses = databaseHandler.getWorkingPlaceAdresses();
        workingPlaceStartTimes = databaseHandler.getStartTimeWorkingPlace();
        workingPlacefinishTimes = databaseHandler.getFinishTimeWorkingPlace();
        workingPlaceComments = databaseHandler.getComments();
        updateUI();

    }
}
