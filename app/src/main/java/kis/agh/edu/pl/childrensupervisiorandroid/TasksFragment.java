package kis.agh.edu.pl.childrensupervisiorandroid;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import kis.agh.edu.pl.childrensupervisiorandroid.adapter.TaskListAdapter;
import kis.agh.edu.pl.childrensupervisiorandroid.sync.DataSync;

public class TasksFragment extends Fragment {

    private TaskListAdapter taskListAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;


    private ListView tasksView;

    private static TasksFragment tasksFragment;

    public static synchronized TasksFragment getInstance(){
        if(tasksFragment==null){
            tasksFragment=new TasksFragment();
        }
        return tasksFragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tasks_layout, container, false);
        tasksView = (ListView) view.findViewById(R.id.tasks_list);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.tasks_swipe_refresh_layout);

        Boolean taskStatus = this.getArguments().getBoolean("status");
        taskListAdapter = new TaskListAdapter(this.getActivity());
        taskListAdapter.setTaskStatus(taskStatus);
        taskListAdapter.reloadTasks();

        tasksView.setAdapter(taskListAdapter);

        tasksView.setOnItemClickListener(new TaskItemClickListener());
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });

        return view;
    }

    public void setTaskStatus(Boolean taskStatus){
        taskListAdapter.setTaskStatus(taskStatus);
        taskListAdapter.reloadTasks();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.tasks));
    }

    private void refreshContent(){
        new DataSync(getActivity()).sync();
        mSwipeRefreshLayout.setRefreshing(false);
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
