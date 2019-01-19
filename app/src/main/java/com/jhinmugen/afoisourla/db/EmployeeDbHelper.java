package com.jhinmugen.afoisourla.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EmployeeDbHelper extends SQLiteOpenHelper {

    public EmployeeDbHelper(Context context) {
        super(context, EmployeeContact.DB_NAME, null, EmployeeContact.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + EmployeeContact.EmployeeEntry.TABLE + "( " +
                EmployeeContact.EmployeeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EmployeeContact.EmployeeEntry.COL_EMPLOYEE_NAME + " TEXT NOT NULL, " +
                EmployeeContact.EmployeeEntry.COL_EMPLOYEE_SURNAME + " TEXT NOT NULL, " +
                EmployeeContact.EmployeeEntry.COL_EMPLOYEE_CODE + " TEXT NOT NULL, " +
                EmployeeContact.EmployeeEntry.COL_EMPLOYEE_START + " TEXT NOT NULL, " +
                EmployeeContact.EmployeeEntry.COL_EMPLOYEE_END + " TEXT NOT NULL);";
        sqLiteDatabase.execSQL(createTable);

        String secondTable = "CREATE TABLE " + EmployeeContact.WorkingSite.TABLE + "( " +
                EmployeeContact.WorkingSite._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EmployeeContact.WorkingSite.COL_WORKING_SITE_NAME + " TEXT NOT NULL, " +
                EmployeeContact.WorkingSite.COL_WORKING_SITE_DESTINATION + " TEXT NOT NULL, " +
                EmployeeContact.WorkingSite.COL_WORKING_SITE_COMMENTS + " TEXT NOT NULL, " +
                EmployeeContact.WorkingSite.COL_WORKING_SITE_START + " TEXT NOT NULL, " +
                EmployeeContact.WorkingSite.COL_WORKING_SITE_END + " TEXT NOT NULL);";
        sqLiteDatabase.execSQL(secondTable);

        String thirdTable = "CREATE TABLE " + EmployeeContact.WorkkManager.TABLE + "( " +
                EmployeeContact.WorkkManager._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EmployeeContact.WorkkManager.COL_WORKING_MANAGER + " TEXT NOT NULL);";
        sqLiteDatabase.execSQL(thirdTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EmployeeContact.EmployeeEntry.TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EmployeeContact.WorkingSite.TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EmployeeContact.WorkkManager.TABLE);
        onCreate(sqLiteDatabase);
    }
}
