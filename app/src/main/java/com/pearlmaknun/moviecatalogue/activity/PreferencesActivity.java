package com.pearlmaknun.moviecatalogue.activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.pearlmaknun.moviecatalogue.fragment.MyPreferenceFragment;

public class PreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}



