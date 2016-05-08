package kis.agh.edu.pl.childrensupervisiorandroid.database;

import android.provider.BaseColumns;

public class TaskContract {

    public TaskContract() {}

    public static abstract class TaskEntry implements BaseColumns {
        public static final String TABLE_NAME = "task";
        public static final String COLUMN_NAME_TASK_ID = "taskid";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_PHOTO = "photo";
        public static final String COLUMN_NAME_STATUS = "status";
    }

}
