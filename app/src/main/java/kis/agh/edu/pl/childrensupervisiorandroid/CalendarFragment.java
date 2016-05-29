package kis.agh.edu.pl.childrensupervisiorandroid;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CalendarFragment extends Fragment {

    private static CalendarFragment calendarFragment;

    public static synchronized CalendarFragment getInstance(){
        if(calendarFragment ==null){
            calendarFragment =new CalendarFragment();
        }
        return calendarFragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calendar_layout, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.tasks));
    }
}
