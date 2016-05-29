package kis.agh.edu.pl.childrensupervisiorandroid.rest;

import java.util.List;

import kis.agh.edu.pl.childrensupervisiorandroid.database.Task;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ChildrenSupervisorService {

    @Headers("Accept: application/json")
    @GET("/parents/{parent_name}/children/{child_name}/tasks")
    Call<List<Task>> getTasks(@Path("parent_name") String parentName, @Path("child_name") String childName);

    @Headers("Accept: application/json")
    @GET("/parents/{parent_name}/children/{child_name}/tasks/{task_id}")
    Call<Task> getTaskById(@Path("parent_name") String parentName, @Path("child_name") String childName, @Path("task_id") String taskId);

}
