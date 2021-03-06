package com.sharedPreference;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.permission_file_io.R;

public class SettingPreferenceFragment extends PreferenceFragment {

    SharedPreferences prefs;

    ListPreference soundPreference;
    ListPreference keywordSoundPreference;
    PreferenceScreen keywordScreen;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_preference);

        soundPreference = (ListPreference) findPreference("sound_list");
        keywordSoundPreference = (ListPreference) findPreference("keyword_sound_list");
        keywordScreen = (PreferenceScreen) findPreference("keyword_screen");


        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        if(!prefs.getString("sound_list","").equals("")){
            soundPreference.setSummary(prefs.getString("sound_list","카톡"));
        }

        if(!prefs.getString("keyword_sound_list","").equals("")){
            keywordSoundPreference.setSummary(prefs.getString("keyword_sound_list","카톡"));
        }

        if(prefs.getBoolean("keyword", false)){
            keywordScreen.setSummary("사용");
        }else{
            keywordScreen.setSummary("사용안함");
        }

        prefs.registerOnSharedPreferenceChangeListener(prefListener);
    }

    SharedPreferences.OnSharedPreferenceChangeListener prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if(key.equals("sound_list")){
                soundPreference.setSummary(prefs.getString("sound_list","카톡"));
            }

            if(key.equals("keyword_sound_list")){
                soundPreference.setSummary(prefs.getString("keyword_sound_list","카톡"));
            }

            if(prefs.getBoolean("keyword", false)){
                keywordScreen.setSummary("사용");
            }else{
                keywordScreen.setSummary("사용안함");
            }
        }
    };

}
