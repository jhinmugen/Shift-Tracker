package com.jhinmugen.afoisourla;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class WorkingPlaceListAdapter extends ArrayAdapter<String> {
    private static final String TAG = "WorkingPlaceListAdapter";
    private Context context;
    private String myText;
    private ArrayList<String> workingPlaceNames;
    private ArrayList<String> workingPlaceAdresses;
    private ArrayList<String> workingPlaceComments;
    private ArrayList<String> workingPlaceStartTimes;
    private ArrayList<String> workingPlaceFinishTimes;
    private int pos;

    public WorkingPlaceListAdapter(@NonNull Context context, ArrayList<String> names, ArrayList<String> adrress, ArrayList<String> comments, ArrayList<String> startTimes, ArrayList<String> finishTimes) {
        super(context, R.layout.working_site_item, names);
        this.context = context;
        this.workingPlaceNames = names;
        this.workingPlaceAdresses = adrress;
        this.workingPlaceComments = comments;
        this.workingPlaceStartTimes = startTimes;
        this.workingPlaceFinishTimes = finishTimes;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder viewHolder;
        final DatabaseHandler databaseHandler = new DatabaseHandler(context);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.working_site_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.workingPlaceName = (TextView) convertView.findViewById(R.id.workingPlaceName);
            viewHolder.workingPlaceDestination = (TextView) convertView.findViewById(R.id.workingPlaceAdress);
            viewHolder.startFinish = (Button) convertView.findViewById(R.id.workingPlaceStartFinish);
            viewHolder.delete = (Button) convertView.findViewById(R.id.buttonDelete);
            viewHolder.send = (Button) convertView.findViewById(R.id.buttonSendMail);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.workingPlaceName.setText(workingPlaceNames.get(position));
        viewHolder.workingPlaceDestination.setText(workingPlaceAdresses.get(position));
        viewHolder.startFinish.setTag(position);
        viewHolder.startFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pos = position;
                if (workingPlaceStartTimes.get(pos).equals("")) {
                    workingPlaceStartTimes.add(pos, Calendar.getInstance().getTime().toString());
                    databaseHandler.updateWorkingPlaceStart(workingPlaceNames.get(pos), Calendar.getInstance().getTime().toString());
                    viewHolder.startFinish.setBackgroundColor(Color.GREEN);
                } else {
                    workingPlaceFinishTimes.add(pos, Calendar.getInstance().getTime().toString());
                    databaseHandler.updateWorkingPlaceFinish(workingPlaceNames.get(pos), Calendar.getInstance().getTime().toString());
                    viewHolder.startFinish.setBackgroundColor(Color.RED);
                }
                if (!workingPlaceFinishTimes.get(pos).equals("")) {
                    myText = insertComments();
                }

            }
        });
        viewHolder.delete.setTag(position);
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View newView = view;
                pos = position;
                new AlertDialog.Builder(context)
                        .setTitle("Διαγραφή Έργου")
                        .setMessage("Είστε σίγουρος πως θέλετε να διαγράψετε το έργο;")
                        .setPositiveButton("Ναι", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                databaseHandler.deleteWorkingPlace(workingPlaceNames.get(pos));
                                if (newView.getTag() != null) {
                                    workingPlaceNames.remove((int) newView.getTag());
                                    workingPlaceAdresses.remove((int) newView.getTag());
                                    workingPlaceComments.remove((int) newView.getTag());
                                    workingPlaceStartTimes.remove((int) newView.getTag());
                                    workingPlaceFinishTimes.remove((int) newView.getTag());
                                    notifyDataSetChanged();
                                }
                            }
                        })
                        .setNegativeButton("'Οχι", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });

        viewHolder.send.setTag(position);
        viewHolder.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pos = position;
                sendEmail(context, pos);
            }
        });


        if (myText != null) {
            databaseHandler.updateWorkingPlaceComments(workingPlaceNames.get(pos), myText);
            workingPlaceComments = databaseHandler.getComments();
        }

        return convertView;
    }

    private String insertComments() {
        android.support.v7.app.AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new android.support.v7.app.AlertDialog.Builder(context, R.style.Theme_AppCompat_Dialog_Alert);
        } else {
            builder = new android.support.v7.app.AlertDialog.Builder(context);
        }
        final EditText input = new EditText(context);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setTitle("Σχόλια σχετικά με το έργο");
        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                myText = input.getText().toString();
            }
        });
        builder.create().show();
        return myText;

    }


    private void sendEmail(Context context, int pos) {
        GPSTracker gpsTracker = new GPSTracker(context);
        gpsTracker.getLocation();
        GeoLocation geoLocation = new GeoLocation(gpsTracker.getLongitude(), gpsTracker.getLatitude(), context);
        gpsTracker.stopUsingGPS();
        geoLocation.calculateAddress();

        String content = workingPlaceNames.get(pos) + " " + workingPlaceAdresses.get(pos) + " " + workingPlaceComments.get(pos) + " " + workingPlaceStartTimes.get(pos) + " " + workingPlaceFinishTimes.get(pos) + " " + geoLocation.getAddress() + " " + geoLocation.getCity() + " " + geoLocation.getKnownName();
        DatabaseHandler workManager = new DatabaseHandler(context);
        SendMail sendMail = new SendMail(context, content, workManager.getWorkManagerName());
        sendMail.send();
    }


    private class ViewHolder {
        private TextView workingPlaceName;
        private TextView workingPlaceDestination;
        private Button startFinish;
        private Button delete;
        private Button send;
    }

}
