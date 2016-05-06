package kis.agh.edu.pl.childrensupervisiorandroid;

import android.app.Fragment;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import butterknife.ButterKnife;

/**
 * Created by swiezy on 06.05.16.
 */
public class TasksFragment extends Fragment {


    public static TasksFragment newInstance() {
        TasksFragment fragment = new TasksFragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity)getActivity()).setActivityTitle(getResources().getString(R.string.tasks));
        View view = inflater.inflate(R.layout.tasks_layout, container, false);
        return view;
    }

}
