package kis.agh.edu.pl.childrensupervisiorandroid;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class SettingsFragment extends PreferenceFragment {

    private static SettingsFragment settingsFragment;

    public static synchronized SettingsFragment getInstance(){
        if(settingsFragment==null){
            settingsFragment=new SettingsFragment();
        }
        return settingsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.settings));
    }

}
