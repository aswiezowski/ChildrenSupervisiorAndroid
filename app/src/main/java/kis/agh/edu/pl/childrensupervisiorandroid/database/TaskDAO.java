package kis.agh.edu.pl.childrensupervisiorandroid.database;

import com.activeandroid.query.Select;

import java.util.List;

public class TaskDAO {

    public List<Task> getTodoTasks(){
        return new Select()
                .from(Task.class)
                .where("status = ?", false)
                .orderBy("updated_at")
                .execute();
    }

    public List<Task> getDoneTasks(){
        return new Select()
                .from(Task.class)
                .where("status = ?", true)
                .orderBy("updated_at")
                .execute();
    }
    public List<Task> getAllTasks(){
        return new Select()
                .from(Task.class)
                .orderBy("updated_at")
                .execute();
    }

    public void updateTask(Task task){
        task.save();
    }

    public Task getTaskById(Long id){
        return Task.load(Task.class, id);
    }
}
