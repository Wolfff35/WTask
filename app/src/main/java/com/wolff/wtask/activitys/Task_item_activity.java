package com.wolff.wtask.activitys;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wolff.wtask.R;
import com.wolff.wtask.fragments.Task_item_fragment;
import com.wolff.wtask.model.WTask;

/**
 * Created by wolff on 29.06.2017.
 */

public class Task_item_activity extends AppCompatActivity {
    private WTask mTaskItem;
    public static final String EXTRA_TASK_ITEM = "TaskItem";

    public static Intent newIntent(Context context, WTask task){
        Intent intent = new Intent(context,Task_item_activity.class);
        intent.putExtra(EXTRA_TASK_ITEM,task);
        return intent;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        mTaskItem = (WTask)getIntent().getSerializableExtra(EXTRA_TASK_ITEM);
        Task_item_fragment task_itemFragment = Task_item_fragment.newIntance(mTaskItem);

        FragmentTransaction fragmentTransaction;
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.item_container, task_itemFragment);
        fragmentTransaction.commit();


    }

}
