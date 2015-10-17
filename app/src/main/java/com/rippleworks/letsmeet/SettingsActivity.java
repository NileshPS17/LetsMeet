package com.rippleworks.letsmeet;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

public class SettingsActivity extends AppCompatPreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.global_preferences);




    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        if( key.equals(getString(R.string.Pref_Facebook)))
        {
            // If turned on , then Sync with facebook and verify E-MAil
            //If Turned off, then leave it as it is.
        }
        else if(key.equals(R.string.Pref_Email))
        {
            //Handle E-MAil Changes
        }
        else if(key.equals(getString(R.string.Pref_Hobby)))
        {
            //Handle Hobby CHange
        }
        else if(key.equals(getString(R.string.Pref_Whatsapp)))
        {
            //Update Status
        }
        else if(key.equals(getString(R.string.Pref_Profile)))
        {
            //change PRofile
        }

        new UpdateSettingTask().execute();


    }

    @Override
    protected void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onStop();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);

    }

    class UpdateSettingTask extends AsyncTask<Void, Void, Void>
    {
        ProgressDialog dialog = new ProgressDialog(SettingsActivity.this);
        @Override
        protected Void doInBackground(Void... params) {
            try{
                Thread.sleep(3000);
            }catch (Exception e){}
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setTitle("Please wait");
            dialog.setMessage("Saving your settings");

            dialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            try{
                if(dialog.isShowing())
                    dialog.cancel();
            }
            catch(NullPointerException e){

            }
        }
    }

}