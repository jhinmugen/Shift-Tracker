package com.jhinmugen.afoisourla.db;

import android.provider.BaseColumns;

public class EmployeeContact {
    public static final String DB_NAME = "com.jhinmugen.afoisourla.db";
    public static final int DB_VERSION = 3;

    public class EmployeeEntry implements BaseColumns {
        public static final String TABLE = "Employee";
        public static final String COL_EMPLOYEE_NAME = "Name";
        public static final String COL_EMPLOYEE_SURNAME = "Surname";
        public static final String COL_EMPLOYEE_CODE = "Code";
        public static final String COL_EMPLOYEE_START = "StartingTime";
        public static final String COL_EMPLOYEE_END = "EndTime";
    }

    public class WorkingSite implements BaseColumns {
        public static final String TABLE = "WorkingSite";
        public static final String COL_WORKING_SITE_NAME = "Name";
        public static final String COL_WORKING_SITE_DESTINATION = "Destination";
        public static final String COL_WORKING_SITE_START = "StartingTime";
        public static final String COL_WORKING_SITE_END = "FinishTime";
        public static final String COL_WORKING_SITE_COMMENTS = "Comments";
    }

    public class WorkkManager implements BaseColumns {
        public static final String TABLE = "WorkingManager";
        public static final String COL_WORKING_MANAGER = "Name";
    }
}
