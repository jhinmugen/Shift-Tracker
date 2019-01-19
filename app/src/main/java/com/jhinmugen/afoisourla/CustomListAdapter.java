package com.jhinmugen.afoisourla;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CustomListAdapter extends ArrayAdapter<String> {
    private static final String TAG = "CustomListAdapter";
    private String myText;
    private int pos;
    private Context context;
    private String password;
    private ArrayList<String> names;
    private ArrayList<String> surnames;
    private ArrayList<String> codes;
    private ArrayList<String> startTimes;
    private ArrayList<String> finishTimes;

    public CustomListAdapter(Context context, ArrayList<String> names, ArrayList<String> surnames, ArrayList<String> code, ArrayList<String> startTimes, ArrayList<String> finishTimes) {
        super(context, R.layout.empoyee_item,names);
        this.context = context;
        this.names = names;
        this.surnames = surnames;
        this.codes = code;
        this.startTimes = startTimes;
        this.finishTimes = finishTimes;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder mHolder;
        final DatabaseHandler databaseHandler = new DatabaseHandler(context);
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.empoyee_item, parent,false);
            mHolder = new ViewHolder();
            mHolder.employeeName = (TextView) convertView.findViewById(R.id.workingEmployeeName);
            mHolder.employeeSurname = (TextView) convertView.findViewById(R.id.workingEmployeeSurname);
            mHolder.startTime = (TextView) convertView.findViewById(R.id.startTime);
            mHolder.finishTime = (TextView) convertView.findViewById(R.id.finishTime);
            mHolder.code = (TextView) convertView.findViewById(R.id.code);
            mHolder.startFinish = (Button) convertView.findViewById(R.id.startFinishButton);
            mHolder.delete = (Button) convertView.findViewById(R.id.deleteButton);
            convertView.setTag(mHolder);
        }else{
            mHolder = (ViewHolder) convertView.getTag();
        }
        mHolder.employeeName.setText(names.get(position));
        mHolder.employeeSurname.setText(surnames.get(position));
        mHolder.code.setText(codes.get(position));
        mHolder.startTime.setText(startTimes.get(position));
        mHolder.finishTime.setText(finishTimes.get(position));
        mHolder.delete.setTag(position);
        mHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View  newView = view;
                pos = position;
                new AlertDialog.Builder(context)
                        .setTitle("Διαγραφή Εργαζομένου")
                        .setMessage("Είστε σίγουρος πως θέλετε να διαγράψετε τον εργαζόμενο")
                        .setPositiveButton("Ναι", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                databaseHandler.deleteEmployee(names.get(pos),surnames.get(pos));
                                if (newView.getTag() != null){
                                    names.remove((int) newView.getTag());
                                    surnames.remove((int) newView.getTag());
                                    codes.remove((int) newView.getTag());
                                    startTimes.remove((int) newView.getTag());
                                    finishTimes.remove((int) newView.getTag());
                                    notifyDataSetChanged();
                                }
                            }
                        })
                        .setNegativeButton("'Οχι", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();

                notifyDataSetChanged();
            }
        });
        mHolder.startFinish.setTag(position);
        mHolder.startFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pos = position;
                myText = passwordInput();
            }
        });
        if (codes.get(pos).equals(myText)){
            if (startTimes.get(pos).equals("0")){
                startTimes.set(pos, getTime().toString());
                databaseHandler.updateStartPerCode(codes.get(pos),getTime().toString());
                myText = "0";
            }else if (finishTimes.get(pos).equals("0")){
                finishTimes.set(pos, getTime().toString());
                databaseHandler.updateFinishPerCode(codes.get(pos),getTime().toString());
                myText = "0";
            }

        }
        mHolder.startTime.setText(startTimes.get(position));
        notifyDataSetChanged();



        return convertView;
    }

    private String  passwordInput(){
        android.support.v7.app.AlertDialog.Builder builder;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            builder = new android.support.v7.app.AlertDialog.Builder(context, R.style.Theme_AppCompat_Dialog_Alert);
        }else{
            builder = new android.support.v7.app.AlertDialog.Builder(context);
        }
        final EditText input = new EditText(context);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);
        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                myText = input.getText().toString();
            }
        });
        builder.create().show();
        return myText;

    }

    private Date getTime(){
        return Calendar.getInstance().getTime();
    }

    private class ViewHolder{
        private TextView employeeName;
        private TextView employeeSurname;
        private TextView startTime;
        private TextView finishTime;
        private TextView code;
        private Button startFinish;
        private Button delete;
    }

}
