package kis.agh.edu.pl.childrensupervisiorandroid.database;

import com.activeandroid.query.Select;

import java.util.List;

public class TaskDAO {

    public List<Task> getTodoTasks(){
        return new Select()
                .from(Task.class)
                .where("status = ?", "todo")
                .orderBy("date")
                .execute();
    }

    public List<Task> getDoneTasks(){
        return new Select()
                .from(Task.class)
                .where("status = ?", "done")
                .orderBy("date")
                .execute();
    }

    public void updateTask(Task task){
        task.save();
    }

    public Task getTaskById(Long id){
        return Task.load(Task.class, id);
    }
}
