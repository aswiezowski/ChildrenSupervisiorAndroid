package kis.agh.edu.pl.childrensupervisiorandroid;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class SettingsFragment extends PreferenceFragment {

    public static final String KEY_PREF_SYNC = "pref_alarm_seq";
    public static final String KEY_PREF_PARENT_NAME = "pref_parent_name";
    public static final String KEY_PREF_CHILD_NAME = "pref_child_name";
    public static final String KEY_PREF_LAST_SYNC = "pref_last_sync";

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
