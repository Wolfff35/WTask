package com.wolff.wtask.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.wolff.wtask.R;
import com.wolff.wtask.adapters.Adapter_category_list;
import com.wolff.wtask.adapters.Adapter_task_list;
import com.wolff.wtask.localdb.DataLab;

import java.util.ArrayList;

/**
 * Created by wolff on 30.06.2017.
 */

 public class Category_list_dialog extends DialogFragment {
        private String[] mCategoryList;
        public static final String TAG_CATEGORY_ID = "cat_id";
        public static final String TYPE_CATEGORY = "type_category";
        private Context mContext;
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            //return super.onCreateDialog(savedInstanceState);
            DataLab dataLab = DataLab.get(mContext);
            Log.e("DIALOG","TYPE_CATEGORY = "+getArguments().getInt(TYPE_CATEGORY));
            //mCategoryList = dataLab.getWCategoryList(getArguments().getInt(TYPE_CATEGORY));
            mCategoryList = getResources().getStringArray(R.array.WCategorys);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_list,null);
            ListView lvItemList = (ListView)view.findViewById(R.id.lvItemList);
            Adapter_category_list adapter = new Adapter_category_list(mContext, mCategoryList);
            lvItemList.setAdapter(adapter);
            lvItemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.e("DIALOG","Selected item cat "+mCategoryList[position]);
                    Intent intent = new Intent();
                    intent.putExtra(TAG_CATEGORY_ID,position);
                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK,intent);
                    dismiss();
                }
            });
            builder.setView(view);
            return builder.create();
        }

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            mContext = context;
        }
    }
