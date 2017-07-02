package com.wolff.wtask.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wolff.wtask.R;
import com.wolff.wtask.model.WTask;

import java.util.ArrayList;

/**
 * Created by wolff on 27.06.2017.
 */

public class Adapter_task_list extends BaseAdapter {
    Context mContext;
    LayoutInflater mInflater;
    ArrayList<WTask> mTaskList;

    public Adapter_task_list(Context context, ArrayList<WTask> taskList){
        mContext=context;
        mTaskList=taskList;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        //Log.e("ADAPTER","count = "+mCurrencyList.size());
        return mTaskList.size();
    }

    @Override
    public Object getItem(int position) {
        return mTaskList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view==null){
            view=mInflater.inflate(R.layout.task_list_item_adapter,parent,false);
        }
        WTask task = (WTask)getItem(position);
        TextView tvTaskName = (TextView) view.findViewById(R.id.tvTaskName);

        tvTaskName.setText(task.getName());
        return view;
    }}
