package com.wolff.wtask.localdb;

/**
 * Created by wolff on 24.06.2017.
 */

public class DbSchema {
    public static final String DATABASE_NAME = "wtask.db";

    public static final String CREATE_TASK_TABLE = "CREATE TABLE "+Table_Task.TABLE_NAME+" ("+
            BaseColumns.ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            BaseColumns.NAME+" TEXT, "+
            BaseColumns.DESCRIBE+" TEXT, "+
            BaseColumns.DATE_CREATION+" TEXT, "+
            BaseColumns.CATEGORY+" INTEGER, "+
            BaseColumns.ISFOGET+" INTEGER, "+
            BaseColumns.DATE_FINISH+" TEXT) ";


    //==================================================================================================
    public static final class BaseColumns{
        public static final String ID = "_id";
        public static final String NAME = "_name";
        public static final String DESCRIBE = "_describe";
        public static final String DATE_CREATION = "_dateCreation";
        public static final String DATE_FINISH = "_dateFinish";
        public static final String CATEGORY = "_category";
        public static final String ISFOGET = "_isFoget";
    }
    public static final class Table_Task {
        public static final String TABLE_NAME = "table_task";
    }
}

