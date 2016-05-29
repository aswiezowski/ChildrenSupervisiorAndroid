package kis.agh.edu.pl.childrensupervisiorandroid;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import kis.agh.edu.pl.childrensupervisiorandroid.database.Task;
import kis.agh.edu.pl.childrensupervisiorandroid.database.TaskDAO;

public class TaskItemFragment extends Fragment {

    private MainActivity mainActivity;
    private static TaskDAO taskDAO;
    private Task task;
    private LinearLayout galleryLinearLayout;

    private static final int CAMERA_REQUEST = 1888;

    public static TaskItemFragment newInstance() {
        TaskItemFragment fragment = new TaskItemFragment();
        taskDAO = new TaskDAO();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.task_description_layout, container, false);

        retrieveTask();

        mainActivity=((MainActivity) getActivity());
        setHasOptionsMenu(true);
        return view;
    }

    private void retrieveTask(){
        Long taskId = this.getArguments().getLong("task_id");
        task = taskDAO.getTaskById(taskId);
    }

    private void prepareView(View view){
        TextView taskDescription = (TextView) view.findViewById(R.id.taskDescription);
        RatingBar rating = (RatingBar) view.findViewById(R.id.rating);
        FloatingActionButton checkButton = (FloatingActionButton) view.findViewById(R.id.checkButton);
        galleryLinearLayout = (LinearLayout) view.findViewById(R.id.galleryLinearLayout);

        taskDescription.setText(task.description);
        rating.setRating(task.mark.floatValue());

        if(!task.isDone()){
            checkButton.setBackgroundColor(getResources().getColor(R.color.colorUndone));
            checkButton.setImageResource(R.drawable.ic_clear);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if(task == null){
            retrieveTask();
        }
        mainActivity.getSupportActionBar().setTitle(task.summary);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.task_description_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.addPhotoAction:
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
                return true;
        }
        return false;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST) {
            ImageView imageView = new ImageView(getActivity());
            Bitmap photo = (Bitmap) data.getExtras().get("data");

            imageView.setImageBitmap(photo);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            galleryLinearLayout.addView(imageView);

        }
    }

}
