package kis.agh.edu.pl.childrensupervisiorandroid.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;

@Table(name = "Tasks")
public class Task extends Model {

    @Column(name = "child_id")
    public Integer childId;

    @Column(name = "summary")
    public String summary;

    @Column(name = "description")
    public String description;

    @Column(name = "status")
    public String status;

    @Column(name = "mark")
    public Double mark;

    @Column(name = "icon_id")
    public Integer iconId;

    @Column(name = "date")
    public Date date;

    public boolean isDone(){
        return status.equals("done");
    }

}
