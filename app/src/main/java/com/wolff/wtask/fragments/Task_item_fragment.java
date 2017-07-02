package com.wolff.wtask.fragments;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.wolff.wtask.R;
import com.wolff.wtask.localdb.DataLab;
import com.wolff.wtask.model.WTask;
import com.wolff.wtask.utilites.DateUtils;

import java.util.Date;

import static com.wolff.wtask.fragments.Category_list_dialog.TAG_CATEGORY_ID;
import static com.wolff.wtask.fragments.Category_list_dialog.TYPE_CATEGORY;

/**
 * Created by wolff on 29.06.2017.
 */

public class Task_item_fragment extends Item_fragment {
   // public static final int DIALOG_REQUEST_ACCOUNT = 1;
   // public static final int DIALOG_REQUEST_DATE = 2;
    public static final int DIALOG_REQUEST_CATEGORY = 3;

    private static final String ARG_TASK_ITEM = "WCredItem";
   // private static final String ARG_OPERATION_TYPE = "WItemType";
    private WTask mTaskItem;
    private int mTypeOperation;

    EditText edTask_Name;
    EditText edTask_Describe;
    Button btnTask_Category;
    Button btnTask_DateCreation;
    TextInputLayout edTask_Name_layout;

    public static Task_item_fragment newIntance(WTask item){
        Bundle args = new Bundle();
        args.putSerializable(ARG_TASK_ITEM,item);
        //args.putInt(ARG_OPERATION_TYPE,typeOperation);
        Task_item_fragment fragment = new Task_item_fragment();
        fragment.setArguments(args);
        return fragment;

    }
    public Task_item_fragment() {
        super();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTaskItem = (WTask)getArguments().getSerializable(ARG_TASK_ITEM);
        if(mTaskItem ==null){
            mTaskItem = new WTask();
            mIsNewItem=true;
            mIsEditable=true;
            mTaskItem.setDateCreation(new Date());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.task_item_fragment, container, false);
        edTask_Name = (EditText)view.findViewById(R.id.edTask_Name);
        edTask_Describe = (EditText)view.findViewById(R.id.edTask_describe);
        btnTask_Category = (Button) view.findViewById(R.id.btnTask_category);
        btnTask_DateCreation = (Button) view.findViewById(R.id.btnTask_DateCreation);
        edTask_Name_layout = (TextInputLayout)view.findViewById(R.id.edTask_Name_layout);
        setFieldsVisibility();
        super.onCreateView(inflater,container,savedInstanceState);
        return view;
    }

    @Override
    public void fields_fillData() {
        super.fields_fillData();
        edTask_Name.setText(mTaskItem.getName());
        edTask_Describe.setText(mTaskItem.getDescribe());
        if(mTaskItem.getCategory()!=0) {
            btnTask_Category.setText(getResources().getStringArray(R.array.WCategorys)[mTaskItem.getCategory()]);
        }else {
            btnTask_Category.setText("");
        }
        DateUtils dateUtils = new DateUtils();
        btnTask_DateCreation.setText(dateUtils.dateToString(mTaskItem.getDateCreation(),DateUtils.DATE_FORMAT_VID));

    }

    @Override
    public void fields_setListeners() {
        super.fields_setListeners();
        btnTask_Category.setOnClickListener(onClick_Category_listener);
        edTask_Name.addTextChangedListener(textChangedListener);
        edTask_Describe.addTextChangedListener(textChangedListener);
  //      edTask_Name.setOnFocusChangeListener(onFocusChanged_listener);
    }

    @Override
    public void setFieldsVisibility() {
        super.setFieldsVisibility();
        edTask_Name.setEnabled(mIsEditable);
        edTask_Describe.setEnabled(mIsEditable);
        btnTask_Category.setEnabled(mIsEditable);
    }

    @Override
    public void saveItem() {
        super.saveItem();
        if (!isFillingOk()) return;
        if(mIsNewItem){
            DataLab.get(getContext()).task_add(mTaskItem);
        }else {
            DataLab.get(getContext()).task_update(mTaskItem);
        }
        getActivity().finish();
    }

    @Override
    public boolean isFillingOk() {
        super.isFillingOk();
        StringBuilder sb = new StringBuilder();
        boolean isOk=true;
        if(mTaskItem.getName().isEmpty()){
            //sb.append(getResources().getString(R.string.credit_name_label)+", ");
            isOk=false;
        }else {
            edTask_Name_layout.setErrorEnabled(false);
        }
        if(mTaskItem.getCategory()==0){
            sb.append("Категория");
            //sb.append(getResources().getString(R.string.category_name_label));
            isOk=false;
        }
        if(!isOk){
            Snackbar.make(getView(), "Не заполнены необходимые поля: "+sb.toString(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return false;
        }
        return true;
    }

    @Override
    public void deleteItem() {
        super.deleteItem();
        DataLab.get(getContext()).task_delete(mTaskItem);
        getActivity().finish();
    }

    @Override
    public void updateItemFields() {
        super.updateItemFields();
        mTaskItem.setName(edTask_Name.getText().toString());
        mTaskItem.setDescribe(edTask_Describe.getText().toString());
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK){
            switch (requestCode){
                case DIALOG_REQUEST_CATEGORY:
                    int category = (int) data.getSerializableExtra(TAG_CATEGORY_ID);
                    mTaskItem.setCategory(category);
                    btnTask_Category.setText(getResources().getStringArray(R.array.WCategorys)[category]);
                    mIsDataChanged=true;
                    setOptionsMenuVisibility();
                    break;
            }
        }
    }
    View.OnClickListener onClick_Category_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DialogFragment dialog = new Category_list_dialog();
            dialog.setTargetFragment(Task_item_fragment.this,DIALOG_REQUEST_CATEGORY);
            Bundle args = new Bundle();
            args.putInt(TYPE_CATEGORY,mTypeOperation);
            dialog.setArguments(args);
            dialog.show(getFragmentManager(),dialog.getClass().getName());
        }
    };

/*    View.OnFocusChangeListener onFocusChanged_listener = new View.OnFocusChangeListener(){

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            Log.e("FOCUS",""+v.getId()+" = "+hasFocus);
            switch (v.getId()){
                case R.id.edTask_Name:
                    if(edTask_Name.length()<2){
                        edTask_Name_layout.setErrorEnabled(true);
                        //edOperation_Name_layout.setError("Не заполнено "+getResources().getString(R.string.operation_name_label)+" (min 2 символа)");
                        edTask_Name_layout.setError("Не заполнено имя "+" (min 2 символа)");
                    }else {
                        edTask_Name_layout.setErrorEnabled(false);
                    }
                    break;
            }
        }
    };
*/

}
