package kis.agh.edu.pl.childrensupervisiorandroid;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import kis.agh.edu.pl.childrensupervisiorandroid.adapter.TaskListAdapter;

public class TasksFragment extends Fragment {

    TaskListAdapter taskListAdapter;

    private ListView tasksView;

    public static TasksFragment newInstance() {
        TasksFragment fragment = new TasksFragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tasks_layout, container, false);
        tasksView = (ListView) view;

        taskListAdapter = new TaskListAdapter(this.getActivity());

        tasksView.setAdapter(taskListAdapter);

        tasksView.setOnItemClickListener(new TaskItemClickListener());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.tasks));
    }

    class TaskItemClickListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view,
                                int position, long id) {
            Bundle bundle = new Bundle();
            bundle.putLong("task_id", taskListAdapter.getItem(position).getId());

            TaskItemFragment taskItemFragment = TaskItemFragment.newInstance();
            taskItemFragment.setArguments(bundle);

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragment_container, taskItemFragment);
            transaction.addToBackStack("To task list");
            transaction.commit();
        }

    }

}
