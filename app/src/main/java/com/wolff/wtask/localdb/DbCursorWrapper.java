package com.wolff.wtask.localdb;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.wolff.wtask.model.WTask;
import com.wolff.wtask.utilites.DateUtils;

/**
 * Created by wolff on 24.06.2017.
 */

 public class DbCursorWrapper extends CursorWrapper {

        public DbCursorWrapper(Cursor cursor) {
            super(cursor);
        }
        public WTask getWCTask(){
            int s_id = getInt(getColumnIndex(DbSchema.BaseColumns.ID));
            String s_name = getString(getColumnIndex(DbSchema.BaseColumns.NAME));
            String s_describe = getString(getColumnIndex(DbSchema.BaseColumns.DESCRIBE));
            int s_isFoget = getInt(getColumnIndex(DbSchema.BaseColumns.ISFOGET));
            String s_dateCreation = getString(getColumnIndex(DbSchema.BaseColumns.DATE_CREATION));
            String s_dateFinish = getString(getColumnIndex(DbSchema.BaseColumns.DATE_FINISH));
            int s_category = getInt(getColumnIndex(DbSchema.BaseColumns.CATEGORY));

            WTask wtask = new WTask();
            wtask.setId(s_id);
            wtask.setName(s_name);
            wtask.setDescribe(s_describe);
            DateUtils dateUtils = new DateUtils();
            wtask.setDateCreation(dateUtils.dateFromString(s_dateCreation,DateUtils.DATE_FORMAT_SAVE));
            wtask.setDateFinish(dateUtils.dateFromString(s_dateFinish,DateUtils.DATE_FORMAT_SAVE));
            wtask.setIsForget(s_isFoget==1);
            wtask.setCategory(s_category);
            return wtask;
        }
 }
