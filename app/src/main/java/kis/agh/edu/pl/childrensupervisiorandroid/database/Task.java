package kis.agh.edu.pl.childrensupervisiorandroid.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;

@Table(name = "Tasks", id = "_id")
public class Task extends Model {

    @Column(name = "id")
    public Integer id;

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

    public boolean isDone(){
        return !status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Task task = (Task) o;

        return id.equals(task.id);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id.hashCode();
        return result;
    }
}
