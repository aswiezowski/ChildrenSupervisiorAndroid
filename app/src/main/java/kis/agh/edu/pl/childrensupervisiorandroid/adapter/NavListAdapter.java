package kis.agh.edu.pl.childrensupervisiorandroid.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.AdapterView;
import android.content.Context;
import android.view.View;

import kis.agh.edu.pl.childrensupervisiorandroid.MainActivity;
import kis.agh.edu.pl.childrensupervisiorandroid.R;

public class NavListAdapter implements AdapterView.OnItemClickListener {

    private Context context;

    public NavListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Fragment fragment = null;
        if(position==0){

        } else if(position == 1){

        }
        FragmentManager fragmentManager = ((MainActivity) context).getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}