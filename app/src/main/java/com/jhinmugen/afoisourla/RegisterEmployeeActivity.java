package com.jhinmugen.afoisourla;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterEmployeeActivity extends AppCompatActivity {
    private Button cancel;
    private Button save;
    private EditText name;
    private EditText surname;
    private int code;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_employee);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = getApplicationContext();
        initialize();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CodeGenerator codeGenerator = new CodeGenerator();
                code = codeGenerator.randomGenerator();
                DatabaseHandler databaseHandler = new DatabaseHandler(context, name.getText().toString(), surname.getText().toString(), code);
                databaseHandler.save();
                alertDialog(code);
            }
        });

    }

    private void initialize() {
        cancel = (Button) findViewById(R.id.cancelEmployee);
        save = (Button) findViewById(R.id.saveEmployee);
        name = (EditText) findViewById(R.id.nameToRestiger);
        surname = (EditText) findViewById(R.id.surnameToRegister);
    }

    private void alertDialog(int code) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, R.style.Theme_AppCompat_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Κωδικός Εργαζομένου")
                .setMessage("Ο κωδικός του εργαζομένου είναι:" + code)
                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .show();

    }


}
