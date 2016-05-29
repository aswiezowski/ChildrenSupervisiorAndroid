package kis.agh.edu.pl.childrensupervisiorandroid.adapter;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import kis.agh.edu.pl.childrensupervisiorandroid.R;
import kis.agh.edu.pl.childrensupervisiorandroid.database.Task;
import kis.agh.edu.pl.childrensupervisiorandroid.database.TaskDAO;

public class TaskListAdapter extends BaseAdapter {

    Context context;
    List<Task> tasks;

    public TaskListAdapter(Context context){
        this.context = context;
        TaskDAO taskDAO = new TaskDAO();
        tasks = taskDAO.getTodoTasks();
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
            View view = inflater.inflate(R.layout.task_item_layout,parent);
            textView = (TextView) view.findViewById(R.id.taskSummary);
        } else {
            textView = (TextView) convertView;
        }

        textView.setText(getItem(position).summary);
        return textView;
    }
}
