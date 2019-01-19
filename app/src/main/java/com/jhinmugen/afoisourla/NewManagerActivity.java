package com.jhinmugen.afoisourla;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewManagerActivity extends AppCompatActivity {
    private Button saveButton;
    private Button exit;
    private EditText workManagerName;
    private String name = "";
    private boolean saved = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_manager);
        initialize();


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = workManagerName.getText().toString();
                if (name.equals("")){
                    new AlertDialog.Builder(NewManagerActivity.this)
                            .setTitle("Αποθήκευση Ονόματος")
                            .setMessage("Παρακαλώ εισάγεται το ονοματεπώνυμο σας")
                            .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            }).show();

                }else{
                    DatabaseHandler databaseHandler = new DatabaseHandler(getApplicationContext(), name);
                    databaseHandler.workManagerName();
                    saved = true;
                }

            }
        });


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler databaseHandler2 = new DatabaseHandler(NewManagerActivity.this);
                if (saved == false){
                    new AlertDialog.Builder(NewManagerActivity.this)
                            .setTitle("Επιστροφή στο Menu")
                            .setMessage("Παρακαλώ εισάγεται το ονοματεπώνυμο σας")
                            .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            }).show();
                }else{
                    finish();
                }

            }
        });
    }

    public void initialize() {
        saveButton = (Button) findViewById(R.id.saveManagerNameButton);
        workManagerName = (EditText) findViewById(R.id.managerName);
        exit = (Button) findViewById(R.id.exit);
    }
}
