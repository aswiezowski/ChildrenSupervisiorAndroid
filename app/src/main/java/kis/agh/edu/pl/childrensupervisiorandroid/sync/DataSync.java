package kis.agh.edu.pl.childrensupervisiorandroid.sync;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kis.agh.edu.pl.childrensupervisiorandroid.SettingsFragment;
import kis.agh.edu.pl.childrensupervisiorandroid.database.Task;
import kis.agh.edu.pl.childrensupervisiorandroid.database.TaskDAO;
import kis.agh.edu.pl.childrensupervisiorandroid.rest.TaskAccess;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataSync {

    private Context context;
    private Date lastSync;
    private List<Task> serverTasks;
    private List<Task> localTasks;
    private Map<Integer,Task> localTasksToSyncMap;
    private Map<Integer,Task> serverTasksMap;

    TaskAccess taskAccess;

    public DataSync(Context context) {
        this.context = context;
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        lastSync = new Date(sharedPref.getLong(SettingsFragment.KEY_PREF_LAST_SYNC, 0));
    }

    public void sync(){
        loadLocalTasks();
        setupTaskAccess();
        loadServerTasksAndContinue();

    }

    private void loadLocalTasks(){
        TaskDAO taskDAO = new TaskDAO();
        localTasks = taskDAO.getAllTasks();
    }

    private void setupTaskAccess(){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        String childName = sharedPref.getString(SettingsFragment.KEY_PREF_CHILD_NAME, "");
        String parentName = sharedPref.getString(SettingsFragment.KEY_PREF_PARENT_NAME, "");
        taskAccess = new TaskAccess(parentName, childName);
    }

    private void loadServerTasksAndContinue(){
        Call<List<Task>> taskCall = taskAccess.getTasksCall();
        taskCall.enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                try {
                    if(!response.isSuccessful()){
                        Log.e("DataSync", response.errorBody().string());
                    } else{
                        Log.i("DataSync", response.message());
                        serverTasks = response.body();
                        continueSync();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
            }
        });
    }

    private void continueSync(){
        localTasksToSyncMap = generateMap(localTasks);
        serverTasksMap = generateMap(serverTasks);
        replaceLocalWithNewerTask();

    }

    private Map<Integer, Task> generateMap(List<Task> tasks){
        Map taskMap = new HashMap();
        for(Task task: tasks){
            taskMap.put(task.id, task);
        }
        return taskMap;
    }

    public void replaceLocalWithNewerTask(){
        Log.i("DataSync", Integer.toString(serverTasks.size()));
        for(Task serverTask: serverTasks){
            Task localTask = localTasksToSyncMap.get(serverTask.id);
            Log.i("DataSync", serverTask.summary);
            if(localTask==null){
                serverTask.save();
                Log.i("DataSync", "Save new "+ serverTask.summary);
            } else if(localTask.updated_at.before(serverTask.updated_at)){
                Log.i("DataSync", "Save update "+ serverTask.summary);
                serverTask.save();
            } else if(localTask.updated_at.after(serverTask.updated_at)){
                Log.i("DataSync", "Save to server "+ serverTask.summary);
                taskAccess.saveTask(localTask);
            }
            localTasksToSyncMap.remove(serverTask.id);
        }
    }



}
