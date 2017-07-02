package com.wolff.wtask.fragments;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.wolff.wtask.R;
import com.wolff.wtask.adapters.Adapter_task_list;
import com.wolff.wtask.localdb.DataLab;
import com.wolff.wtask.model.WTask;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class Task_list_fragment extends Fragment {
    private ArrayList<WTask> mTaskList11;
    private ArrayList<WTask> mTaskList12;
    private ArrayList<WTask> mTaskList21;
    private ArrayList<WTask> mTaskList22;
    private Task_list_fragment_listener listener;
    private ListView lv11;
    private ListView lv12;
    private ListView lv21;
    private ListView lv22;

    public interface Task_list_fragment_listener{
        void onTaskItemClick(WTask task);
    }

    public Task_list_fragment() {
    }
    public static Task_list_fragment newInstance(){
        Task_list_fragment fragment = new Task_list_fragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTaskList11 = DataLab.get(getContext()).getWTaskList(0);
        mTaskList12 = DataLab.get(getContext()).getWTaskList(1);
        mTaskList21 = DataLab.get(getContext()).getWTaskList(2);
        mTaskList22 = DataLab.get(getContext()).getWTaskList(3);
    }

    @Override
    public void onResume() {
        super.onResume();
        mTaskList11 = DataLab.get(getContext()).getWTaskList(0);
        mTaskList12 = DataLab.get(getContext()).getWTaskList(1);
        mTaskList21 = DataLab.get(getContext()).getWTaskList(2);
        mTaskList22 = DataLab.get(getContext()).getWTaskList(3);
        onActivityCreated(null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_main,container,false);
        lv11 = (ListView)v.findViewById(R.id.lv11);
        lv12 = (ListView)v.findViewById(R.id.lv12);
        lv21 = (ListView)v.findViewById(R.id.lv21);
        lv22 = (ListView)v.findViewById(R.id.lv22);
        ListAdapter adapter11 = new Adapter_task_list(getContext(),mTaskList11);
        lv11.setAdapter(adapter11);
        lv11.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.onTaskItemClick(mTaskList11.get(position));
            }
        });
        ListAdapter adapter12 = new Adapter_task_list(getContext(),mTaskList12);
        lv12.setAdapter(adapter12);
        lv12.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.onTaskItemClick(mTaskList12.get(position));
            }
        });
        ListAdapter adapter21 = new Adapter_task_list(getContext(),mTaskList21);
        lv21.setAdapter(adapter21);
        lv21.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.onTaskItemClick(mTaskList21.get(position));
            }
        });
        ListAdapter adapter22 = new Adapter_task_list(getContext(),mTaskList22);
        lv22.setAdapter(adapter22);
        lv22.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.onTaskItemClick(mTaskList22.get(position));
            }
        });
        registerForContextMenu(lv11);
        registerForContextMenu(lv12);
        registerForContextMenu(lv21);
        registerForContextMenu(lv22);
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (Task_list_fragment_listener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener=null;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.context_delete:
                //editItem(info.position); // метод, выполняющий действие при редактировании пункта меню
                Log.e("MENU","DELETE "+info.position);
                return true;
            case R.id.context_foget:
                //deleteItem(info.position); //метод, выполняющий действие при удалении пункта меню
                Log.e("MENU","FORGET "+info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
