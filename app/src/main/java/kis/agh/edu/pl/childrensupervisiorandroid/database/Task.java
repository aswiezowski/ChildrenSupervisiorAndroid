package kis.agh.edu.pl.childrensupervisiorandroid.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Tasks")
public class Task extends Model {
    @Column(name = "summary")
    public String summary;

    @Column(name = "description")
    public String description;

    @Column(name = "status")
    public String status;

    @Column(name = "mark")
    public Double mark;

}
