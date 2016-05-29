package kis.agh.edu.pl.childrensupervisiorandroid.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

@Table(name = "tasks")
public class Task extends Model {

    @JsonProperty("id")
    @Column(name = "task_id")
    public Integer task_id;

    @Column(name = "child_id")
    public Integer child_id;

    @Column(name = "summary")
    public String summary;

    @Column(name = "description")
    public String description;

    @Column(name = "status")
    public Boolean status;

    @Column(name = "mark")
    public Double mark;

    @Column(name = "icon_id")
    public Integer iconId;

    @Column(name = "created_at")
    public Date created_at;

    @Column(name = "updated_at")
    public Date updated_at;

    public List<Photo> photos() {
        return getMany(Photo.class, "task_id");
    }


    public boolean isDone(){
        return !status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Task task = (Task) o;
        return task_id.equals(task.task_id);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + task_id.hashCode();
        return result;
    }
}
