package com.jhinmugen.afoisourla;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.jhinmugen.afoisourla.db.EmployeeContact;
import com.jhinmugen.afoisourla.db.EmployeeDbHelper;

import java.util.ArrayList;

public class DatabaseHandler {
    private static final String TAG = "DatabaseHandler";
    private String name;
    private String surname;
    private int code;
    private String startTime;
    private String finishTime;
    private String startTimeWorkingPlace;
    private String finishTimeWorkingPlace;
    private String comments;
    private String workingPlaceName;
    private String workingPlaceAdress;
    private Context context;
    private EmployeeDbHelper dbHelper;
    private ArrayList<String> employeesNames;
    private ArrayList<String> employeesSurnames;
    private ArrayList<String> employeesCodes;
    private ArrayList<String> employeesStartTime;
    private ArrayList<String> employeesFinishTime;
    private ArrayList<String> workingPlaceNames;
    private ArrayList<String> workingPlaceAdrresses;
    private ArrayList<String> workingPlaceStartTimes;
    private ArrayList<String> workingPlacefinishTimes;
    private ArrayList<String> workingPlaceComments;
    private String workManagerName;

    public DatabaseHandler(Context context) {
        this.context = context;

    }
    public DatabaseHandler(Context context, String name){
        this.context = context;
        this.name = name;
    }

    public DatabaseHandler(Context context, String name, String surname, int code) {
        this.context = context;
        this.name = name;
        this.surname = surname;
        this.code = code;
    }

    public DatabaseHandler(Context context, String name, String adrress) {
        this.context = context;
        this.workingPlaceName = name;
        this.workingPlaceAdress = adrress;

    }

    public void saveWorkplace() {
        dbHelper = new EmployeeDbHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        startTimeWorkingPlace = "";
        finishTimeWorkingPlace = "";
        comments = "";
        values.put(EmployeeContact.WorkingSite.COL_WORKING_SITE_NAME, workingPlaceName);
        values.put(EmployeeContact.WorkingSite.COL_WORKING_SITE_DESTINATION, workingPlaceAdress);
        values.put(EmployeeContact.WorkingSite.COL_WORKING_SITE_START, startTimeWorkingPlace);
        values.put(EmployeeContact.WorkingSite.COL_WORKING_SITE_END, finishTimeWorkingPlace);
        values.put(EmployeeContact.WorkingSite.COL_WORKING_SITE_COMMENTS, comments);
        database.insert(EmployeeContact.WorkingSite.TABLE, null, values);
        database.close();
    }

    public void workManagerName(){
        dbHelper = new EmployeeDbHelper(context);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EmployeeContact.WorkkManager.COL_WORKING_MANAGER, name);
        sqLiteDatabase.insert(EmployeeContact.WorkkManager.TABLE, null,contentValues);
        sqLiteDatabase.close();
    }

    public void save() {
        dbHelper = new EmployeeDbHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        startTime = "0";
        finishTime = "0";
        values.put(EmployeeContact.EmployeeEntry.COL_EMPLOYEE_NAME, name);
        values.put(EmployeeContact.EmployeeEntry.COL_EMPLOYEE_SURNAME, surname);
        values.put(EmployeeContact.EmployeeEntry.COL_EMPLOYEE_CODE, String.valueOf(code));
        values.put(EmployeeContact.EmployeeEntry.COL_EMPLOYEE_START, startTime);
        values.put(EmployeeContact.EmployeeEntry.COL_EMPLOYEE_END, finishTime);
        database.insert(EmployeeContact.EmployeeEntry.TABLE, null, values);
        database.close();

    }

    public void updateWorkingPlaceComments(String name, String comments){
        dbHelper = new EmployeeDbHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EmployeeContact.WorkingSite.COL_WORKING_SITE_COMMENTS, comments);
        database.update(EmployeeContact.WorkingSite.TABLE, values, EmployeeContact.WorkingSite.COL_WORKING_SITE_NAME + " = ?", new String[]{name});
        database.close();
    }

    public void  updateWorkingPlaceStart(String name, String time){
        dbHelper = new EmployeeDbHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EmployeeContact.WorkingSite.COL_WORKING_SITE_START, time);
        database.update(EmployeeContact.WorkingSite.TABLE, values, EmployeeContact.WorkingSite.COL_WORKING_SITE_NAME + " = ?", new String[]{name});
        database.close();
    }

    public void updateWorkingPlaceFinish(String name, String time){
        dbHelper = new EmployeeDbHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EmployeeContact.WorkingSite.COL_WORKING_SITE_END, time);
        database.update(EmployeeContact.WorkingSite.TABLE, values, EmployeeContact.WorkingSite.COL_WORKING_SITE_NAME + " = ?", new String[]{name});
        database.close();
    }


    public void updateAll(String code, String startTime, String finishTime) {
        dbHelper = new EmployeeDbHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EmployeeContact.EmployeeEntry.COL_EMPLOYEE_START, startTime);
        values.put(EmployeeContact.EmployeeEntry.COL_EMPLOYEE_END, finishTime);
        database.update(EmployeeContact.EmployeeEntry.TABLE, values, EmployeeContact.EmployeeEntry.COL_EMPLOYEE_CODE + " = ?", new String[]{code});
        database.close();
    }

    public void updateStartPerCode(String code, String time) {
        dbHelper = new EmployeeDbHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EmployeeContact.EmployeeEntry.COL_EMPLOYEE_START, time);
        database.update(EmployeeContact.EmployeeEntry.TABLE, values, EmployeeContact.EmployeeEntry.COL_EMPLOYEE_CODE + " = ?", new String[]{code});
        database.close();
    }

    public void updateFinishPerCode(String code, String time) {
        dbHelper = new EmployeeDbHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EmployeeContact.EmployeeEntry.COL_EMPLOYEE_END, time);
        database.update(EmployeeContact.EmployeeEntry.TABLE, values, EmployeeContact.EmployeeEntry.COL_EMPLOYEE_CODE + " = ?", new String[]{code});
        database.close();
    }

    public void deleteEmployee(String name, String surname) {
        dbHelper = new EmployeeDbHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete(EmployeeContact.EmployeeEntry.TABLE,
                EmployeeContact.EmployeeEntry.COL_EMPLOYEE_NAME + " = ?" + " AND " + EmployeeContact.EmployeeEntry.COL_EMPLOYEE_SURNAME + " = ?", new String[]{name, surname});
        database.close();
    }

    public void deleteWorkingPlace(String name){
        dbHelper = new EmployeeDbHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete(EmployeeContact.WorkingSite.TABLE, EmployeeContact.WorkingSite.COL_WORKING_SITE_NAME + " = ?", new String[]{name});
        database.close();
    }

    public ArrayList<String> getNames() {
        employeesNames = new ArrayList<>();
        dbHelper = new EmployeeDbHelper(context);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(EmployeeContact.EmployeeEntry.TABLE, new String[]{EmployeeContact.EmployeeEntry._ID,
                EmployeeContact.EmployeeEntry.COL_EMPLOYEE_NAME,
                EmployeeContact.EmployeeEntry.COL_EMPLOYEE_SURNAME,
                EmployeeContact.EmployeeEntry.COL_EMPLOYEE_CODE,
                EmployeeContact.EmployeeEntry.COL_EMPLOYEE_START,
                EmployeeContact.EmployeeEntry.COL_EMPLOYEE_END}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(EmployeeContact.EmployeeEntry.COL_EMPLOYEE_NAME);
            employeesNames.add(cursor.getString(idx));
        }
        cursor.close();
        database.close();
        return employeesNames;
    }

    public ArrayList<String> getSurname() {
        employeesSurnames = new ArrayList<>();
        dbHelper = new EmployeeDbHelper(context);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(EmployeeContact.EmployeeEntry.TABLE, new String[]{EmployeeContact.EmployeeEntry._ID,
                EmployeeContact.EmployeeEntry.COL_EMPLOYEE_NAME,
                EmployeeContact.EmployeeEntry.COL_EMPLOYEE_SURNAME,
                EmployeeContact.EmployeeEntry.COL_EMPLOYEE_CODE,
                EmployeeContact.EmployeeEntry.COL_EMPLOYEE_START,
                EmployeeContact.EmployeeEntry.COL_EMPLOYEE_END}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(EmployeeContact.EmployeeEntry.COL_EMPLOYEE_SURNAME);
            employeesSurnames.add(cursor.getString(idx));
        }
        cursor.close();
        database.close();
        return employeesSurnames;
    }

    //TODO:Testing purposes with code! to be deleted
    public ArrayList<String> getCode() {
        employeesCodes = new ArrayList<>();
        dbHelper = new EmployeeDbHelper(context);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(EmployeeContact.EmployeeEntry.TABLE, new String[]{EmployeeContact.EmployeeEntry._ID,
                EmployeeContact.EmployeeEntry.COL_EMPLOYEE_NAME,
                EmployeeContact.EmployeeEntry.COL_EMPLOYEE_SURNAME,
                EmployeeContact.EmployeeEntry.COL_EMPLOYEE_CODE,
                EmployeeContact.EmployeeEntry.COL_EMPLOYEE_START,
                EmployeeContact.EmployeeEntry.COL_EMPLOYEE_END}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(EmployeeContact.EmployeeEntry.COL_EMPLOYEE_CODE);
            employeesCodes.add(cursor.getString(idx));
        }
        cursor.close();
        database.close();
        return employeesCodes;
    }

    public ArrayList<String> getStartTimes() {
        employeesStartTime = new ArrayList<>();
        dbHelper = new EmployeeDbHelper(context);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(EmployeeContact.EmployeeEntry.TABLE, new String[]{EmployeeContact.EmployeeEntry._ID,
                EmployeeContact.EmployeeEntry.COL_EMPLOYEE_NAME,
                EmployeeContact.EmployeeEntry.COL_EMPLOYEE_SURNAME,
                EmployeeContact.EmployeeEntry.COL_EMPLOYEE_CODE,
                EmployeeContact.EmployeeEntry.COL_EMPLOYEE_START,
                EmployeeContact.EmployeeEntry.COL_EMPLOYEE_END}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(EmployeeContact.EmployeeEntry.COL_EMPLOYEE_START);
            employeesStartTime.add(cursor.getString(idx));
        }
        cursor.close();
        database.close();
        return employeesStartTime;
    }

    public ArrayList<String> getFinishTimes() {
        employeesFinishTime = new ArrayList<>();
        dbHelper = new EmployeeDbHelper(context);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(EmployeeContact.EmployeeEntry.TABLE, new String[]{EmployeeContact.EmployeeEntry._ID,
                EmployeeContact.EmployeeEntry.COL_EMPLOYEE_NAME,
                EmployeeContact.EmployeeEntry.COL_EMPLOYEE_SURNAME,
                EmployeeContact.EmployeeEntry.COL_EMPLOYEE_CODE,
                EmployeeContact.EmployeeEntry.COL_EMPLOYEE_START,
                EmployeeContact.EmployeeEntry.COL_EMPLOYEE_END}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(EmployeeContact.EmployeeEntry.COL_EMPLOYEE_END);
            employeesFinishTime.add(cursor.getString(idx));
        }
        cursor.close();
        database.close();
        return employeesFinishTime;
    }


    public ArrayList<String> getStartTimeWorkingPlace() {
        workingPlaceStartTimes = new ArrayList<>();
        dbHelper = new EmployeeDbHelper(context);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(EmployeeContact.WorkingSite.TABLE, new String[]{EmployeeContact.WorkingSite._ID,
                EmployeeContact.WorkingSite.COL_WORKING_SITE_NAME,
                EmployeeContact.WorkingSite.COL_WORKING_SITE_DESTINATION,
                EmployeeContact.WorkingSite.COL_WORKING_SITE_START,
                EmployeeContact.WorkingSite.COL_WORKING_SITE_END,
                EmployeeContact.WorkingSite.COL_WORKING_SITE_COMMENTS}, null,null,null,null, null);
        while(cursor.moveToNext()){
            int idx = cursor.getColumnIndex(EmployeeContact.WorkingSite.COL_WORKING_SITE_START);
            workingPlaceStartTimes.add(cursor.getString(idx));
        }
        cursor.close();
        database.close();
        return workingPlaceStartTimes;
    }

    public ArrayList<String> getFinishTimeWorkingPlace() {
        workingPlacefinishTimes = new ArrayList<>();
        dbHelper = new EmployeeDbHelper(context);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(EmployeeContact.WorkingSite.TABLE, new String[]{EmployeeContact.WorkingSite._ID,
                EmployeeContact.WorkingSite.COL_WORKING_SITE_NAME,
                EmployeeContact.WorkingSite.COL_WORKING_SITE_DESTINATION,
                EmployeeContact.WorkingSite.COL_WORKING_SITE_START,
                EmployeeContact.WorkingSite.COL_WORKING_SITE_END,
                EmployeeContact.WorkingSite.COL_WORKING_SITE_COMMENTS}, null,null,null,null, null);
        while(cursor.moveToNext()){
            int idx = cursor.getColumnIndex(EmployeeContact.WorkingSite.COL_WORKING_SITE_END);
            workingPlacefinishTimes.add(cursor.getString(idx));
        }
        cursor.close();
        database.close();
        return workingPlacefinishTimes;
    }

    public ArrayList<String> getComments() {
        workingPlaceComments = new ArrayList<>();
        dbHelper = new EmployeeDbHelper(context);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(EmployeeContact.WorkingSite.TABLE, new String[]{EmployeeContact.WorkingSite._ID,
                EmployeeContact.WorkingSite.COL_WORKING_SITE_NAME,
                EmployeeContact.WorkingSite.COL_WORKING_SITE_DESTINATION,
                EmployeeContact.WorkingSite.COL_WORKING_SITE_START,
                EmployeeContact.WorkingSite.COL_WORKING_SITE_END,
                EmployeeContact.WorkingSite.COL_WORKING_SITE_COMMENTS}, null,null,null,null, null);
        while(cursor.moveToNext()){
            int idx = cursor.getColumnIndex(EmployeeContact.WorkingSite.COL_WORKING_SITE_COMMENTS);
            workingPlaceComments.add(cursor.getString(idx));
        }
        cursor.close();
        database.close();
        return workingPlaceComments;
    }

    public ArrayList<String> getWorkingPlaceNames() {
        workingPlaceNames = new ArrayList<>();
        dbHelper = new EmployeeDbHelper(context);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(EmployeeContact.WorkingSite.TABLE, new String[]{EmployeeContact.WorkingSite._ID,
                EmployeeContact.WorkingSite.COL_WORKING_SITE_NAME,
                EmployeeContact.WorkingSite.COL_WORKING_SITE_DESTINATION,
                EmployeeContact.WorkingSite.COL_WORKING_SITE_START,
                EmployeeContact.WorkingSite.COL_WORKING_SITE_END,
                EmployeeContact.WorkingSite.COL_WORKING_SITE_COMMENTS}, null,null,null,null, null);
        while(cursor.moveToNext()){
            int idx = cursor.getColumnIndex(EmployeeContact.WorkingSite.COL_WORKING_SITE_NAME);
            workingPlaceNames.add(cursor.getString(idx));
        }
        cursor.close();
        database.close();
        return workingPlaceNames;
    }

    public ArrayList<String> getWorkingPlaceAdresses() {
        workingPlaceAdrresses = new ArrayList<>();
        dbHelper = new EmployeeDbHelper(context);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(EmployeeContact.WorkingSite.TABLE, new String[]{EmployeeContact.WorkingSite._ID,
                EmployeeContact.WorkingSite.COL_WORKING_SITE_NAME,
                EmployeeContact.WorkingSite.COL_WORKING_SITE_DESTINATION,
                EmployeeContact.WorkingSite.COL_WORKING_SITE_START,
                EmployeeContact.WorkingSite.COL_WORKING_SITE_END,
                EmployeeContact.WorkingSite.COL_WORKING_SITE_COMMENTS}, null,null,null,null, null);
        while(cursor.moveToNext()){
            int idx = cursor.getColumnIndex(EmployeeContact.WorkingSite.COL_WORKING_SITE_DESTINATION);
            workingPlaceAdrresses.add(cursor.getString(idx));
        }
        cursor.close();
        database.close();
        return workingPlaceAdrresses;
    }

    public String getWorkManagerName(){
        dbHelper = new EmployeeDbHelper(context);
        SQLiteDatabase database =  dbHelper.getReadableDatabase();
        Cursor cursor = database.query(EmployeeContact.WorkkManager.TABLE, new String[]{EmployeeContact.WorkkManager._ID,
                EmployeeContact.WorkkManager.COL_WORKING_MANAGER},null,null,null,null,null);
        while (cursor.moveToNext()){
            int idx = cursor.getColumnIndex(EmployeeContact.WorkkManager.COL_WORKING_MANAGER);
            workManagerName = cursor.getString(idx);
        }
        cursor.close();
        database.close();
        return workManagerName;
    }
}
