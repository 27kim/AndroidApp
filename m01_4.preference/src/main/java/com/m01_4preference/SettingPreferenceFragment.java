package com.m01_4preference;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;

public class SettingPreferenceFragment extends PreferenceFragment {

    SwitchPreference roamingPreference;
    ListPreference networkTyepPreference;
    SwitchPreference lteModePreference;

    SharedPreferences prefs;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting_preference);

        roamingPreference = (SwitchPreference) findPreference("roaming");
        networkTyepPreference = (ListPreference)findPreference("network_type");
        lteModePreference = (SwitchPreference) findPreference("lte_mode");

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        prefs.registerOnSharedPreferenceChangeListener(prefsListener);

        if(!prefs.getString("network_type","").equals("")){
            networkTyepPreference.setSummary(prefs.getString("network_type","LTE(권장)"));
        }
    }

    SharedPreferences.OnSharedPreferenceChangeListener prefsListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if(key.equals("network_type")){
                networkTyepPreference.setSummary(prefs.getString("network_type","LTE(권장)"));
            }
        }
    };
}
