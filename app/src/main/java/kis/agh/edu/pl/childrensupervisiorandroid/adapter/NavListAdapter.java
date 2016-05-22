package kis.agh.edu.pl.childrensupervisiorandroid.adapter;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.content.Context;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import kis.agh.edu.pl.childrensupervisiorandroid.MainActivity;
import kis.agh.edu.pl.childrensupervisiorandroid.R;
import kis.agh.edu.pl.childrensupervisiorandroid.RewardsFragment;
import kis.agh.edu.pl.childrensupervisiorandroid.SettingsFragment;
import kis.agh.edu.pl.childrensupervisiorandroid.TasksFragment;

public class NavListAdapter implements AdapterView.OnItemClickListener {

    private Context context;
    DrawerLayout mDrawerLayout;

    public NavListAdapter(Context context, DrawerLayout mDrawerLayout) {
        this.context = context;
        this.mDrawerLayout = mDrawerLayout;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mDrawerLayout.closeDrawers();
        Fragment fragment = null;

        FragmentManager fragmentManager = ((MainActivity) context).getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (position == 0 || position == 1) {
            fragmentManager.popBackStack(0, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else if (position == 2) {
            transaction.replace(R.id.fragment_container, RewardsFragment.getInstance());
            transaction.addToBackStack("Rewards");
        } else if (position == 3) {
            transaction.replace(R.id.fragment_container, SettingsFragment.getInstance());
            transaction.addToBackStack("Settings");
        }
        transaction.commit();
    }
}