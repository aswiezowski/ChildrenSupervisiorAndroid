package kis.agh.edu.pl.childrensupervisiorandroid.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.AdapterView;
import android.content.Context;
import android.view.View;

import kis.agh.edu.pl.childrensupervisiorandroid.MainActivity;
import kis.agh.edu.pl.childrensupervisiorandroid.R;
import kis.agh.edu.pl.childrensupervisiorandroid.TasksFragment;

public class NavListAdapter implements AdapterView.OnItemClickListener {

    private Context context;

    public NavListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Fragment fragment = null;

        FragmentManager fragmentManager = ((MainActivity) context).getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (position == 0) {
            fragmentManager.popBackStack(0, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else if (position == 1) {
            transaction.replace(R.id.fragment_container, TasksFragment.newInstance());
            transaction.addToBackStack("tets");
        }
        transaction.commit();
    }
}