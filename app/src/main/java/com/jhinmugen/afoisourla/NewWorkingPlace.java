package com.jhinmugen.afoisourla;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;

public class NewWorkingPlace extends AppCompatActivity {
    private Button save;
    private EditText name;
    private EditText adrress;
    private Context context;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_working_place);
        initialize();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler databaseHandler = new DatabaseHandler(context, name.getText().toString(), adrress.getText().toString());
                databaseHandler.saveWorkplace();
                finish();
            }
        });


    }


    private void initialize() {
        context = getApplicationContext();
        save = (Button) findViewById(R.id.saveNewWorkingPlace);
        name = (EditText) findViewById(R.id.nameOfWorkingPlace);
        adrress = (EditText) findViewById(R.id.adrressOfWorkingPlace);
    }
}
