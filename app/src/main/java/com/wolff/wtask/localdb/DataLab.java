package com.wolff.wtask.localdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.wolff.wtask.model.WTask;
import com.wolff.wtask.utilites.DateUtils;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by wolff on 24.06.2017.
 */

public class DataLab {    private static DataLab sDataLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    private DataLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new DbHelper(mContext).getWritableDatabase();
    }
    public static DataLab get(Context context){
        if(sDataLab==null){
            sDataLab = new DataLab(context);
        }
        return sDataLab;
    }
    //==============================================================================================
    private DbCursorWrapper queryWTask(int category){
        String selection;
        String[] selectionArgs;
        String[] columns = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;
        selection = DbSchema.BaseColumns.CATEGORY+" = ?";
        selectionArgs = new String[]{String.valueOf(category)};

        Cursor cursor = mDatabase.query(DbSchema.Table_Task.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                groupBy,
                having,
                orderBy);
        return new DbCursorWrapper(cursor);
    }
    public ArrayList<WTask> getWTaskList(int category){
        DbCursorWrapper cursorWrapper = queryWTask(category+1);
        ArrayList<WTask> taskList = new ArrayList<>();
        cursorWrapper.moveToFirst();
        while (!cursorWrapper.isAfterLast()) {
            taskList.add(cursorWrapper.getWCTask());
            cursorWrapper.moveToNext();
        }
        cursorWrapper.close();
        return taskList;
    }
    private static ContentValues getContentValues_WTask(WTask item){
        DateUtils dateUtils = new DateUtils();
        ContentValues values = new ContentValues();
        values.put(DbSchema.BaseColumns.NAME,item.getName());
        values.put(DbSchema.BaseColumns.DESCRIBE,item.getDescribe());
        values.put(DbSchema.BaseColumns.CATEGORY, item.getCategory());
        if(item.getDateCreation().toString().isEmpty()) {
            values.put(DbSchema.BaseColumns.DATE_CREATION,dateUtils.dateToString(new Date(),DateUtils.DATE_FORMAT_SAVE));
        }else {
            values.put(DbSchema.BaseColumns.DATE_CREATION,dateUtils.dateToString(item.getDateCreation(),DateUtils.DATE_FORMAT_SAVE));
        }
        return values;
    }
/*    public WOperation fingCreditById(double idCredit, ArrayList<WOperation> creditList){

        for (WOperation item:creditList) {
            if(item.getId()==idCredit){
                return item;
            }
        }
        return null;
    }
    */
    public void task_add(WTask item){
        ContentValues values = getContentValues_WTask(item);
        String table = DbSchema.Table_Task.TABLE_NAME;
        mDatabase.insert(table,null,values);
        Log.e("add task","Success");
    }
    public void task_update(WTask item){
        ContentValues values = getContentValues_WTask(item);
        String table = DbSchema.Table_Task.TABLE_NAME;
        mDatabase.update(
                table,
                values,
                DbSchema.BaseColumns.ID+" = ?",
                new String[]{String.valueOf(item.getId())}
        );
        Log.e("update task"," Success");
    }
    public void task_delete(WTask item){
        String table = DbSchema.Table_Task.TABLE_NAME;
        mDatabase.delete(
                table,
                DbSchema.BaseColumns.ID+" =?",
                new String[]{String.valueOf(item.getId())}
        );
        Log.e("delete task","Success");
    }


}
