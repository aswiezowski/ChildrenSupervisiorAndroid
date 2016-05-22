package kis.agh.edu.pl.childrensupervisiorandroid;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class RewardsFragment extends Fragment {

    private MainActivity mainActivity;
    private static RewardsFragment rewardsFragment;

    public static synchronized RewardsFragment getInstance(){
        if(rewardsFragment==null){
            rewardsFragment=new RewardsFragment();
        }
        return rewardsFragment;
    }


    public static RewardsFragment newInstance() {
        RewardsFragment fragment = new RewardsFragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rewards_layout, container, false);
        GridView gridview = (GridView) view.findViewById(R.id.reward_list);
        gridview.setAdapter(new ImageAdapter(getActivity()));
        mainActivity=((MainActivity) getActivity());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mainActivity.getSupportActionBar().setTitle(getString(R.string.rewards));

    }


}
