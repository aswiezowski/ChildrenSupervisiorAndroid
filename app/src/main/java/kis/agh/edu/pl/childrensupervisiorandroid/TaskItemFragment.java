package kis.agh.edu.pl.childrensupervisiorandroid;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TaskItemFragment extends Fragment {


    public static TaskItemFragment newInstance() {
        TaskItemFragment fragment = new TaskItemFragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity)getActivity()).setActivityTitle(getResources().getString(R.string.tasks));
        View view = inflater.inflate(R.layout.task_description_layout, container, false);

        return view;
    }

}
