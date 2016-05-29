package kis.agh.edu.pl.childrensupervisiorandroid.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;

public class Photo extends Model {

    @Column(name="task_id")
    public Task task;

    @Column(name = "photo")
    public byte[] photo;
}
