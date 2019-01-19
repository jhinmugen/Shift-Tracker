package com.jhinmugen.afoisourla;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EmployeesActivity extends AppCompatActivity {
    private Button newEmployee;
    private Button listOfEmployees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees);
        initialize();

        newEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerNewEmployee();
            }
        });

        listOfEmployees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEmployees();
            }
        });

    }

    private void initialize(){
        newEmployee = (Button) findViewById(R.id.newEmployees);
        listOfEmployees = (Button) findViewById(R.id.listOfemployees);
    }

    private void registerNewEmployee(){
        Intent intent = new Intent(this,RegisterEmployeeActivity.class);
        startActivity(intent);

    }

    private void showEmployees(){
        Intent intent = new Intent(this, ManageEmployees.class);
        startActivity(intent);
    }

}
