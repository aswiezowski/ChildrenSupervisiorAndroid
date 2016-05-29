package kis.agh.edu.pl.childrensupervisiorandroid.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;

@Table(name="comments")
public class Comment extends Model {

    @Column(name="task_id")
    public Task task;

    @Column(name="text")
    public String text;

    @Column(name="date")
    public Date date;
}
