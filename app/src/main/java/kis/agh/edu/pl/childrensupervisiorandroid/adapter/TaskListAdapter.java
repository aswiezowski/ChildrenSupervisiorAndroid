package kis.agh.edu.pl.childrensupervisiorandroid.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kis.agh.edu.pl.childrensupervisiorandroid.R;
import kis.agh.edu.pl.childrensupervisiorandroid.SettingsFragment;
import kis.agh.edu.pl.childrensupervisiorandroid.database.Task;
import kis.agh.edu.pl.childrensupervisiorandroid.database.TaskDAO;
import kis.agh.edu.pl.childrensupervisiorandroid.rest.TaskAccess;
import kis.agh.edu.pl.childrensupervisiorandroid.sync.DataSync;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskListAdapter extends BaseAdapter {

    private Context context;
    private List<Task> tasks = new ArrayList<>();
    private Boolean taskStatus = false;

    public TaskListAdapter(final Context context){
        this.context = context;

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        String childName = sharedPref.getString(SettingsFragment.KEY_PREF_CHILD_NAME, "");
        String parentName = sharedPref.getString(SettingsFragment.KEY_PREF_PARENT_NAME, "");
    }

    public void setTaskStatus(Boolean taskStatus){
        Log.e("TaskListAdpater", taskStatus.toString());
        this.taskStatus = taskStatus;
    }

    public void reloadTasks(){
        TaskDAO taskDAO = new TaskDAO();
        if(taskStatus){
            tasks = taskDAO.getDoneTasks();
        } else {
            tasks = taskDAO.getTodoTasks();
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Task getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return tasks.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.task_item_layout, parent, false);
            textView = (TextView) view.findViewById(R.id.taskSummary);
        } else {
            textView = (TextView) convertView;
        }

        textView.setText(getItem(position).summary);
        return textView;
    }
}
