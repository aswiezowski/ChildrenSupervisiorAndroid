package kis.agh.edu.pl.childrensupervisiorandroid;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.TypedValue;
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

import java.io.ByteArrayOutputStream;
import java.util.Date;

import kis.agh.edu.pl.childrensupervisiorandroid.database.Photo;
import kis.agh.edu.pl.childrensupervisiorandroid.database.Task;
import kis.agh.edu.pl.childrensupervisiorandroid.database.TaskDAO;

public class TaskItemFragment extends Fragment {

    private MainActivity mainActivity;
    private static TaskDAO taskDAO;
    private Task task;
    private LinearLayout galleryLinearLayout;

    private static final int CAMERA_REQUEST = 1888;
    private FloatingActionButton checkButton;

    public static TaskItemFragment newInstance() {
        TaskItemFragment fragment = new TaskItemFragment();
        taskDAO = new TaskDAO();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.task_description_layout, container, false);

        retrieveTask();
        prepareView(view);
        mainActivity = ((MainActivity) getActivity());
        setHasOptionsMenu(true);
        return view;
    }

    private void retrieveTask() {
        Long taskId = this.getArguments().getLong("task_id");
        task = taskDAO.getTaskById(taskId);
    }

    private void prepareView(View view) {
        TextView taskDescription = (TextView) view.findViewById(R.id.taskDescription);
        RatingBar rating = (RatingBar) view.findViewById(R.id.rating);
        checkButton = (FloatingActionButton) view.findViewById(R.id.checkButton);
        galleryLinearLayout = (LinearLayout) view.findViewById(R.id.galleryLinearLayout);

        taskDescription.setText(task.description);
        if (task.mark != null) {
            rating.setRating(task.mark.floatValue());
        }

        setupCheckButton();

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (task.status == true) {
                    task.status = false;
                } else if (task.status == false) {
                    task.status = true;
                }
                task.updated_at=new Date();
                task.save();
                setupCheckButton();
            }
        });

        loadPhotoFromDatabase();
    }

    private void setupCheckButton(){
        if (!task.isDone()) {
            checkButton.setImageResource(R.drawable.ic_clear);
            checkButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorUndone)));
        }else{
            checkButton.setImageResource(R.drawable.ic_done);
            checkButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorDone)));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (task == null) {
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
        if (requestCode == CAMERA_REQUEST && data != null && data.getExtras() != null) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");

            addPhotoToView(photo);
            savePhotoToDatabase(photo);
        }
    }

    private void addPhotoToView(Bitmap photo) {
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 256, getResources().getDisplayMetrics());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
        layoutParams.setMargins(5, 0, 5, 0);

        ImageView imageView = new ImageView(getActivity());
        imageView.setImageBitmap(photo);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setAdjustViewBounds(true);
        imageView.setLayoutParams(layoutParams);

        galleryLinearLayout.addView(imageView);
    }

    private void savePhotoToDatabase(Bitmap photo) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.PNG, 100, stream);

        Photo taskPhoto = new Photo();
        taskPhoto.task = task;
        taskPhoto.photo = stream.toByteArray();
        taskPhoto.save();
    }

    private void loadPhotoFromDatabase() {
        for (Photo photo : task.photos()) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(photo.photo, 0, photo.photo.length);
            addPhotoToView(bitmap);
        }
    }

}
