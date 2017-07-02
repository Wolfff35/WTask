package com.wolff.wtask.localdb;

import android.content.Context;

import com.wolff.wtask.model.WTask;

import java.util.Date;

/**
 * Created by wolff on 23.05.2017.
 */

public class Test_data {
    public void fillTestData(Context context) {
        DataLab dataLab = DataLab.get(context);
        WTask task1 = new WTask();
        task1.setName("Name 1");
        task1.setDescribe("Describe1");
        task1.setDateCreation(new Date());
        task1.setCategory(0);
        dataLab.task_add(task1);

        WTask task2 = new WTask();
        task2.setName("Name 2");
        task2.setDescribe("Describe2");
        task2.setDateCreation(new Date());
        task2.setCategory(1);
        dataLab.task_add(task2);

        WTask task3 = new WTask();
        task3.setName("Name 3");
        task3.setDescribe("Describe3");
        task3.setDateCreation(new Date());
        task3.setCategory(2);
        dataLab.task_add(task3);

        WTask task4 = new WTask();
        task4.setName("Name 4");
        task4.setDescribe("Describe4");
        task4.setDateCreation(new Date());
        task4.setCategory(3);
        dataLab.task_add(task4);
    }
}



