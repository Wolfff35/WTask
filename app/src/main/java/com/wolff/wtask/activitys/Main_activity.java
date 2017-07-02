package com.wolff.wtask.activitys;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.wolff.wtask.fragments.Task_list_fragment;
import com.wolff.wtask.R;
import com.wolff.wtask.localdb.Test_data;
import com.wolff.wtask.model.WTask;

public class Main_activity extends AppCompatActivity implements Task_list_fragment.Task_list_fragment_listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Test_data test_data = new Test_data();
        test_data.fillTestData(getApplicationContext());

        Task_list_fragment tasklistfragment = Task_list_fragment.newInstance();
        FragmentTransaction fragmentTransaction;
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.containerMain, tasklistfragment);
        fragmentTransaction.commit();
    }


    @Override
    public void onTaskItemClick(WTask task) {
        Intent intent = Task_item_activity.newIntent(getApplicationContext(),task);
        startActivity(intent);
        Log.e("TASK","name: "+task.getName()+"; id: "+task.getId());
    }

}
