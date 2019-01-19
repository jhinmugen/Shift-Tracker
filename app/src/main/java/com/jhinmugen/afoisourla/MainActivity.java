package com.jhinmugen.afoisourla;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final String PREFS_NAME = "MyPresFile";
    private static final String TAG = "MainActivity";
    private Button employees;
    private String myText = "";
    private String workManagerName = "";
    private Button workplace;
    private Button report;
    private String emailContent;
    private ArrayList<String> codes;
    private ArrayList<String> name;
    private ArrayList<String> surname;
    private ArrayList<String> startTime;
    private ArrayList<String> finishTime;
    public static final int INITIAL_REQUEST = 1337;
    public static final int LOCATION_REQUEST = INITIAL_REQUEST + 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DatabaseHandler databaseHandler = new DatabaseHandler(this);
        checkIfFirstLaunched();
        initialize();
        askPermissions();


        employees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startEmployeesActivity();
            }
        });

        workplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startWorkingSiteActivity();
            }
        });

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailContent = mailContent(databaseHandler);
                SendMail sendMail = new SendMail(getApplicationContext(), emailContent, databaseHandler.getWorkManagerName());
                sendMail.send();
                codes = databaseHandler.getCode();
                for (String code : codes) {
                    databaseHandler.updateAll(code, "0", "0");
                }

            }
        });
    }

    private void checkIfFirstLaunched() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        if (settings.getBoolean("my_first_time", true)) {
            Intent intent = new Intent(this, NewManagerActivity.class);
            startActivity(intent);

            settings.edit().putBoolean("my_first_time", false).commit();
        }
    }

    private void initialize() {
        employees = (Button) findViewById(R.id.employee);
        workplace = (Button) findViewById(R.id.workplace);
        report = (Button) findViewById(R.id.report);
    }

    private void startEmployeesActivity() {
        Intent intent = new Intent(this, EmployeesActivity.class);
        startActivity(intent);
    }

    private void startWorkingSiteActivity() {
        Intent intent = new Intent(this, WorkingSiteActivity.class);
        startActivity(intent);
    }

    private void askPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST);

        }
    }

    private String mailContent(DatabaseHandler databaseHandler) {
        String content = "";
        name = databaseHandler.getNames();
        surname = databaseHandler.getSurname();
        startTime = databaseHandler.getStartTimes();
        finishTime = databaseHandler.getFinishTimes();
        for (int counter = 0; counter < name.size(); counter++) {
            content = content + name.get(counter) + " " + surname.get(counter) + " " + startTime.get(counter) + " " + finishTime.get(counter) + "\n" + "==========================" + "\n";
        }
        return content;
    }

}
