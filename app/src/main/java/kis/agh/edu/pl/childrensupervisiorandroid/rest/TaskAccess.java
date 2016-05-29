package kis.agh.edu.pl.childrensupervisiorandroid.rest;

import java.io.IOException;
import java.util.List;

import kis.agh.edu.pl.childrensupervisiorandroid.database.Task;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TaskAccess {

    private ChildrenSupervisorService service;

    String childName;
    String parentName;

    public TaskAccess(String parentName, String childName) {
        this.childName = childName;
        this.parentName = parentName;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ancient-forest-46715.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ChildrenSupervisorService.class);
    }

    public Call<List<Task>> getTasksCall() {
        Call<List<Task>> tasksCall = service.getTasks(parentName, childName);
        List<Task> tasks = null;
        return tasksCall;
    }

    public void saveTask(Task task) {
        //TODO
    }

}
