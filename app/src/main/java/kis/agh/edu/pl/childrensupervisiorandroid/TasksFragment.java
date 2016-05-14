package kis.agh.edu.pl.childrensupervisiorandroid;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import butterknife.ButterKnife;

public class TasksFragment extends Fragment {

    ArrayAdapter mAdapter;

    private ListView tasksView;

    public static TasksFragment newInstance() {
        TasksFragment fragment = new TasksFragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tasks_layout, container, false);
        tasksView = (ListView) view;
        mAdapter = new ArrayAdapter(this.getActivity(), R.layout.task_item_layout, R.id.taskSummary);
        mAdapter.addAll(this.getResources().getStringArray(R.array.sample_tasks));
        tasksView.setAdapter(mAdapter);

        tasksView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, TaskItemFragment.newInstance());
                transaction.addToBackStack("To task list");
                transaction.commit();
            }

        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.tasks));
    }

}
