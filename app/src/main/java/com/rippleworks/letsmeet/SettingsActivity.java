package com.rippleworks.letsmeet;

import android.os.Bundle;

public class SettingsActivity extends AppCompatPreferenceActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.global_preferences);

    }


}