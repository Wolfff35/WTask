package com.wolff.wtask.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wolff.wtask.R;
import com.wolff.wtask.model.WTask;

import java.util.ArrayList;

/**
 * Created by wolff on 30.06.2017.
 */

public class Adapter_category_list  extends BaseAdapter {
    Context mContext;
    LayoutInflater mInflater;
    ArrayList<String> mCategoryList = new ArrayList<>();

    public Adapter_category_list(Context context, String[] taskList){
        mContext=context;
        //mTaskList=taskList;
        for(int i=0;i<taskList.length;i++){
            Log.e("i"," = "+i);
            mCategoryList.add(taskList[i]);
        }
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return mCategoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return mCategoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view==null){
            view=mInflater.inflate(R.layout.category_list_item_adapter,parent,false);
        }
        // task = (WTask)getItem(position);
        TextView tvTaskName = (TextView) view.findViewById(R.id.tvTaskName);

        tvTaskName.setText((String)getItem(position));
        return view;
    }}