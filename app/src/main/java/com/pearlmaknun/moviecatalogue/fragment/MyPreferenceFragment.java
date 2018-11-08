package com.pearlmaknun.moviecatalogue.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.provider.Settings;
import android.support.v7.preference.PreferenceFragmentCompat;

import butterknife.BindString;
import butterknife.ButterKnife;

public class MyPreferenceFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {

    public MyPreferenceFragment(){

    }

    @BindString(com.pearlmaknun.moviecatalogue.R.string.key_setting_locale)
    String setting_locale;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(com.pearlmaknun.moviecatalogue.R.xml.preferences);

        ButterKnife.bind(this, getActivity());

        //findPreference(setting_locale).setOnPreferenceClickListener(this);

    }

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {

    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {

        return false;
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        String key = preference.getKey();

        if (key.equals(setting_locale)) {
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
            getActivity().finish();

            return true;
        }

        return false;
    }
}
